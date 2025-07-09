package net.zusz.zcoffeecraft2.block.entity;

import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.zusz.zcoffeecraft2.component.ModDataComponents;
import net.zusz.zcoffeecraft2.item.ModItems;
import net.zusz.zcoffeecraft2.screen.custom.CoffeeMachineMenu;
import org.jetbrains.annotations.Nullable;

import java.io.Console;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CoffeeMachineBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(9) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 4);
            }
        }
    };



    private static final int INPUT_SLOT = 0;
    private static final int CONTAINER_SLOT = 1;
    private static final int WATER_INPUT_SLOT = 2;
    private static final int INGREDIENT_SLOT_1 = 3;
    private static final int INGREDIENT_SLOT_2 = 4;
    private static final int INGREDIENT_SLOT_3 = 5;
    private static final int INGREDIENT_SLOT_4 = 6;
    private static final int OUTPUT_SLOT = 7;
    private static final int WATER_OUTPUT_SLOT = 8;




    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 20;



    public CoffeeMachineBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.COFFEE_MACHINE_BE.get(), pos, blockState);

        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> CoffeeMachineBlockEntity.this.progress;
                    case 1 -> CoffeeMachineBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0: CoffeeMachineBlockEntity.this.progress = value;
                    case 1: CoffeeMachineBlockEntity.this.maxProgress = value;
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
        return new CoffeeMachineMenu(i, inventory, this, this.data);
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
        pTag.putInt("coffee_machine.progress", progress);
        pTag.putInt("coffee_machine.max_progress", maxProgress);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        progress = pTag.getInt("coffee_machine.progress");
        maxProgress = pTag.getInt("coffee_machine.max_progress");

    }


    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if(hasRecipe()) {
           increaseCraftingProgress();
           setChanged(level, blockPos, blockState);

           if(hasCraftingFinished()) {
               craftItem();
               resetProgress();
           }
        } else {
            resetProgress();
        }
    }

    private void craftItem() {
        ItemStack output = new ItemStack(ModItems.CUP_OF_COFFEE.get(), 1);

        output = new ItemStack(output.getItem(),
                itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount());

        if (itemHandler.getStackInSlot(INPUT_SLOT).getItem() == ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN.get()) {
            output.set(ModDataComponents.ROAST, "light");
        }
        if (itemHandler.getStackInSlot(INPUT_SLOT).getItem() == ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN.get()) {
            output.set(ModDataComponents.ROAST, "medium");
        }
        if (itemHandler.getStackInSlot(INPUT_SLOT).getItem() == ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN.get()) {
            output.set(ModDataComponents.ROAST, "dark");
        }


        itemHandler.extractItem(INPUT_SLOT, 1, false);
        itemHandler.extractItem(CONTAINER_SLOT, 1, false);

        itemHandler.setStackInSlot(OUTPUT_SLOT, output);
    }

    private void resetProgress() {
        progress = 0;
        maxProgress = 20;
    }


    private boolean hasCraftingFinished() {
        return progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        ItemStack output = new ItemStack(ModItems.CUP_OF_COFFEE.get(), 1);


        List<Item> validIngredients = Arrays.asList(
                ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN.get(),
                ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN.get(),
                ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN.get()
        );
        if (itemHandler.getStackInSlot(INPUT_SLOT).getItem() == ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN.get()) {
            output.set(ModDataComponents.ROAST, "light");
        }
        if (itemHandler.getStackInSlot(INPUT_SLOT).getItem() == ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN.get()) {
            output.set(ModDataComponents.ROAST, "medium");
        }
        if (itemHandler.getStackInSlot(INPUT_SLOT).getItem() == ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN.get()) {
            output.set(ModDataComponents.ROAST, "dark");
        }

        return validIngredients.contains(itemHandler.getStackInSlot(INPUT_SLOT).getItem()) &&
                itemHandler.getStackInSlot(CONTAINER_SLOT).is(ModItems.ARABICA_COFFEE_CHERRY) &&
                canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {



        return itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                itemHandler.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem() &&
                Objects.equals(itemHandler.getStackInSlot(OUTPUT_SLOT).get(ModDataComponents.ROAST), output.get(ModDataComponents.ROAST));


    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = itemHandler.getStackInSlot(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
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
}
