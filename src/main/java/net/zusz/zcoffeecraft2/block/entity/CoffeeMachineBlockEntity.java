package net.zusz.zcoffeecraft2.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.zusz.zcoffeecraft2.api.coffeerecipes.CoffeeRecipeRegistry;
import net.zusz.zcoffeecraft2.block.custom.CoffeeMachineBlock;
import net.zusz.zcoffeecraft2.component.ModDataComponents;
import net.zusz.zcoffeecraft2.api.coffeeingredients.CoffeeIngredientRegistry;
import net.zusz.zcoffeecraft2.screen.custom.CoffeeMachineMenu;
import org.jetbrains.annotations.Nullable;

import java.util.*;

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
    private static final int FLUID_INPUT_SLOT = 2;
    private static final int INGREDIENT_SLOT_1 = 3;
    private static final int INGREDIENT_SLOT_2 = 4;
    private static final int INGREDIENT_SLOT_3 = 5;
    private static final int INGREDIENT_SLOT_4 = 6;
    private static final int OUTPUT_SLOT = 7;
    private static final int FLUID_CONTAINER_OUTPUT_SLOT = 8;




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


    //start rewriting from here
    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if(hasRecipe()) {
            System.out.println("HASRECIPE");
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
        ItemStack output = CoffeeMachineMethods.getOutput(itemHandler.getStackInSlot(INPUT_SLOT).getItem(),
                itemHandler.getStackInSlot(CONTAINER_SLOT).getItem(),
                itemHandler.getStackInSlot(FLUID_INPUT_SLOT).getItem(),
                itemHandler.getStackInSlot(INGREDIENT_SLOT_1).getItem(),
                itemHandler.getStackInSlot(INGREDIENT_SLOT_2).getItem(),
                itemHandler.getStackInSlot(INGREDIENT_SLOT_3).getItem(),
                itemHandler.getStackInSlot(INGREDIENT_SLOT_4).getItem(),
                itemHandler.getStackInSlot(FLUID_INPUT_SLOT).getItem());

        output = output.copyWithCount(itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + 1);

        ItemStack fluidContainerOutput = getContainerForFluidInput(itemHandler.getStackInSlot(FLUID_INPUT_SLOT)).copyWithCount(itemHandler.getStackInSlot(FLUID_CONTAINER_OUTPUT_SLOT).getCount() + 1);

        itemHandler.extractItem(INPUT_SLOT, 1, false);
        itemHandler.extractItem(CONTAINER_SLOT, 1, false);
        itemHandler.extractItem(FLUID_INPUT_SLOT, 1, false);

        itemHandler.setStackInSlot(OUTPUT_SLOT, output);
        itemHandler.setStackInSlot(FLUID_CONTAINER_OUTPUT_SLOT, fluidContainerOutput);

        decreaseIngredient(INGREDIENT_SLOT_1);
        decreaseIngredient(INGREDIENT_SLOT_2);
        decreaseIngredient(INGREDIENT_SLOT_3);
        decreaseIngredient(INGREDIENT_SLOT_4);
    }

    private void resetProgress() {
        progress = 0;
        maxProgress = 600;
    }

    private boolean hasCraftingFinished() {
        return progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        ItemStack output = CoffeeMachineMethods.getOutput(itemHandler.getStackInSlot(INPUT_SLOT).getItem(),
                itemHandler.getStackInSlot(CONTAINER_SLOT).getItem(),
                itemHandler.getStackInSlot(FLUID_INPUT_SLOT).getItem(),
                itemHandler.getStackInSlot(INGREDIENT_SLOT_1).getItem(),
                itemHandler.getStackInSlot(INGREDIENT_SLOT_2).getItem(),
                itemHandler.getStackInSlot(INGREDIENT_SLOT_3).getItem(),
                itemHandler.getStackInSlot(INGREDIENT_SLOT_4).getItem(),
                itemHandler.getStackInSlot(FLUID_INPUT_SLOT).getItem());

        if (output != null) {
            return canInsertAmountIntoSlot(output.getCount(), OUTPUT_SLOT) &&
                    canInsertItemIntoSlot(output, OUTPUT_SLOT) &&
                    isFluidValid() && CoffeeMachineMethods.isValidCoffeeCombination(output.get(ModDataComponents.INGREDIENTS), output.get(ModDataComponents.FLUID));
        } else {
            System.out.println("NOOOOOO OUTPUTTTT");
            System.out.println(output);
            return false;
        }
    }


    private void decreaseIngredient(int slot) {
        Item item = itemHandler.getStackInSlot(slot).getItem();
        ItemStack remaining = null;
        if (CoffeeIngredientRegistry.getStringFromItem(item).isPresent()) {
            if (CoffeeIngredientRegistry.getRemaining(CoffeeIngredientRegistry.getStringFromItem(item).get()).isPresent() && CoffeeIngredientRegistry.getRemaining(CoffeeIngredientRegistry.getStringFromItem(item).get()).get() != Items.AIR) {
                remaining = new ItemStack (CoffeeIngredientRegistry.getRemaining(CoffeeIngredientRegistry.getStringFromItem(item).get()).get());
            }
        }

        /*if (item == Items.HONEY_BOTTLE) {
            remaining = new ItemStack(Items.GLASS_BOTTLE);
        } else if (item == Items.MILK_BUCKET) {
            remaining = new ItemStack(Items.BUCKET);
        } else if (item == ModItems.STEAMED_MILK.asItem()) {
            remaining = new ItemStack(Items.BUCKET);
        } else if (item == ModItems.MILK_FOAM.asItem()) {
            remaining = new ItemStack(Items.BUCKET);
        }*/

        itemHandler.extractItem(slot, 1, false);
        if (remaining != null) {
            itemHandler.setStackInSlot(slot, remaining);
        }
    }

    //Dont rewrite from here

    private boolean isFluidValid() {
        ItemStack input = itemHandler.getStackInSlot(FLUID_INPUT_SLOT);
        ItemStack container;
        ItemStack waterBottleStack = new ItemStack(Items.POTION);
        waterBottleStack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER));

        container = getContainerForFluidInput(input);

        if (container != null) {
            return canInsertItemIntoSlot(container, FLUID_CONTAINER_OUTPUT_SLOT) &&
                    canInsertAmountIntoSlot(1, FLUID_CONTAINER_OUTPUT_SLOT);
        } else {
            return false;
        }
    }

    private ItemStack getContainerForFluidInput(ItemStack input) {
        ItemStack container;
        ItemStack waterBottleStack = new ItemStack(Items.POTION);
        waterBottleStack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER));

        if (input.is(waterBottleStack.getItem())) {
            container = new ItemStack(Items.GLASS_BOTTLE);
        } else if (input.is(Items.WATER_BUCKET)) {
            container = new ItemStack(Items.BUCKET);
        } else {
            container = null;
        }
        return  container;
    }

    private boolean canInsertItemIntoSlot(ItemStack output, int slotid) {

        return itemHandler.getStackInSlot(slotid).isEmpty() ||
                itemHandler.getStackInSlot(slotid).getItem() == output.getItem() &&
                Objects.equals(itemHandler.getStackInSlot(slotid).get(ModDataComponents.ROAST), output.get(ModDataComponents.ROAST)) &&
                        Objects.equals(itemHandler.getStackInSlot(slotid).get(ModDataComponents.BEAN), output.get(ModDataComponents.BEAN)) &&
                        Objects.equals(itemHandler.getStackInSlot(slotid).get(ModDataComponents.INGREDIENTS), output.get(ModDataComponents.INGREDIENTS));


    }

    private boolean canInsertAmountIntoSlot(int count, int slotid) {
        int maxCount = itemHandler.getStackInSlot(slotid).isEmpty() ? 64 : itemHandler.getStackInSlot(slotid).getMaxStackSize();
        int currentCount = itemHandler.getStackInSlot(slotid).getCount();

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


    private float rotation;
    public float getRenderingRotation() {
        if (level == null) return 0f;

        Direction facing = getBlockState().getValue(CoffeeMachineBlock.FACING);

        return switch (facing) {
            case NORTH -> 0f;
            case SOUTH -> 180f;
            case WEST  -> 90f;
            case EAST  -> -90f;
            default -> 0f;

        };
    }
}