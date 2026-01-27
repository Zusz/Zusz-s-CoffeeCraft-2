package net.zusz.zcoffeecraft2.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.zusz.zcoffeecraft2.api.groundcoffee.GroundCoffeeRegistry;
import net.zusz.zcoffeecraft2.block.custom.CoffeeGrinderBlock;
import net.zusz.zcoffeecraft2.component.ModDataComponents;
import net.zusz.zcoffeecraft2.screen.custom.CoffeeGrinderMenu;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public class CoffeeGrinderBlockEntity extends BlockEntity implements MenuProvider {

    public final ItemStackHandler itemHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 4);
            }
        }
    };

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 1200;

    public CoffeeGrinderBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.COFFEE_GRINDER_BE.get(), pos, blockState);

        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> CoffeeGrinderBlockEntity.this.progress;
                    case 1 -> CoffeeGrinderBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0: CoffeeGrinderBlockEntity.this.progress = value;
                    case 1: CoffeeGrinderBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.zcoffeecraft2.coffee_machine");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new CoffeeGrinderMenu(i, inventory, this, this.data);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("coffee_grinder.progress", progress);
        pTag.putInt("coffee_grinder.max_progress", maxProgress);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        progress = pTag.getInt("coffee_grinder.progress");
        maxProgress = pTag.getInt("coffee_grinder.max_progress");

    }


    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if(hasRecipe()) {
            increaseCraftingProgress();

            if(hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }

        setChanged(level, blockPos, blockState);
    }

    private void craftItem() {

        int amountToCraft = Math.floorDiv(itemHandler.getStackInSlot(INPUT_SLOT).getCount(), 16);
        Item groundItem = GroundCoffeeRegistry.getGroundCoffeeItemFromBeanItem(itemHandler.getStackInSlot(INPUT_SLOT).getItem()).get().asItem();

        if (canInsertAmountIntoSlot(amountToCraft, OUTPUT_SLOT) &&
                canInsertItemIntoSlot(new ItemStack(groundItem), OUTPUT_SLOT)) {

            ItemStack output = itemHandler.getStackInSlot(OUTPUT_SLOT);
            output = new ItemStack(groundItem, output.getCount() + amountToCraft);

            itemHandler.setStackInSlot(OUTPUT_SLOT, output);

            itemHandler.extractItem(INPUT_SLOT, amountToCraft*16, false);

        }
    }

    private boolean canInsertItemIntoSlot(ItemStack output, int slotid) {

        return itemHandler.getStackInSlot(slotid).isEmpty() ||
                itemHandler.getStackInSlot(slotid).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoSlot(int count, int slotid) {
        int maxCount = itemHandler.getStackInSlot(slotid).isEmpty() ? 64 : itemHandler.getStackInSlot(slotid).getMaxStackSize();
        int currentCount = itemHandler.getStackInSlot(slotid).getCount();

        return maxCount >= currentCount + count;
    }

    private void resetProgress() {
        progress = 0;
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<ItemLike> beanItemOpt =  GroundCoffeeRegistry.getGroundCoffeeItemFromBeanItem(itemHandler.getStackInSlot(INPUT_SLOT).getItem());


        int amountToCraft = Math.floorDiv(itemHandler.getStackInSlot(INPUT_SLOT).getCount(), 16);

        if (beanItemOpt.isPresent()) {
            return itemHandler.getStackInSlot(INPUT_SLOT).getCount() >= 16 &&
                canInsertAmountIntoSlot(amountToCraft, OUTPUT_SLOT) && canInsertItemIntoSlot(new ItemStack(beanItemOpt.get().asItem()), 1);
        }

        return false;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }


    private float rotation;
    public float getRenderingRotation() {
        if (level == null) return 0f;

        Direction facing = getBlockState().getValue(CoffeeGrinderBlock.FACING);

        return switch (facing) {
            case NORTH -> 0f;
            case SOUTH -> 180f;
            case WEST -> 90f;
            case EAST -> -90f;
            default -> 0f;

        };
    }

    public Direction getFacing() {
        return getBlockState().getValue(CoffeeGrinderBlock.FACING);
    }
}
