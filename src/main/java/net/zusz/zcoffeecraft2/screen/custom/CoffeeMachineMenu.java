package net.zusz.zcoffeecraft2.screen.custom;

import net.minecraft.commands.arguments.SlotsArgument;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.fml.ISystemReportExtender;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.zusz.zcoffeecraft2.block.ModBlocks;
import net.zusz.zcoffeecraft2.block.entity.CoffeeMachineBlockEntity;
import net.zusz.zcoffeecraft2.screen.ModMenuTypes;
import net.zusz.zcoffeecraft2.item.ModItems;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachineMenu extends AbstractContainerMenu {

    public final CoffeeMachineBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;



    public CoffeeMachineMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public CoffeeMachineMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.COFFEE_MACHINE_MENU.get(), pContainerId);
        this.blockEntity = ((CoffeeMachineBlockEntity) entity);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 0, 75, 24));//Input
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 1, 75, 45));//container
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 2, 14, 24));//Water IN
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 3, 34, 24));//Ingredient 1
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 4, 34, 45));//Ingredient 2
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 5, 54, 24));//Ingredient 3
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 6, 54, 45));//Ingredient 4
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 7, 124, 34));//Output
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 8, 14, 45));//Water OUT




        addDataSlots(data);

        System.out.println(blockEntity.itemHandler.getSlots());
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledArrowProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int arrowPixelSize = 24;

        return maxProgress != 0 && progress!= 0 ? progress * arrowPixelSize / maxProgress : 0;
    }


    private static final int INPUT_SLOT = 0;
    private static final int CONTAINER_SLOT = 1;
    private static final int FLUID_INPUT_SLOT = 2;
    private static final int INGREDIENT_SLOT_1 = 3;
    private static final int INGREDIENT_SLOT_2 = 4;
    private static final int INGREDIENT_SLOT_3 = 5;
    private static final int INGREDIENT_SLOT_4 = 6;
    private static final int OUTPUT_SLOT = 7;
    private static final int FLUID_CONTAINER_OUTPUT_SLOT = 8;

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    //amount of slots!!!
    private static final int TE_INVENTORY_SLOT_COUNT = 9;
    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;

        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // Moving from player to machine
            if (isValidForInputSlot(sourceStack)) {
                if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX + INPUT_SLOT, TE_INVENTORY_FIRST_SLOT_INDEX + INPUT_SLOT + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (isValidForContainerSlot(sourceStack)) {
                if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX + CONTAINER_SLOT, TE_INVENTORY_FIRST_SLOT_INDEX + CONTAINER_SLOT + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (isValidForFluidInputSlot(sourceStack)) {
                if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX + FLUID_INPUT_SLOT, TE_INVENTORY_FIRST_SLOT_INDEX + FLUID_INPUT_SLOT + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (isValidForIngredient(sourceStack)) {
                if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX + INGREDIENT_SLOT_1, TE_INVENTORY_FIRST_SLOT_INDEX + INGREDIENT_SLOT_4 + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                return ItemStack.EMPTY;
            }

        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // Moving from machine to player
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slot index: " + index);
            return ItemStack.EMPTY;
        }

        if (sourceStack.isEmpty()) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    private boolean isValidForIngredient(ItemStack sourceStack) {
        List<Item> validItems = new ArrayList<>(List.of(
                ModItems.WHIPPED_CREAM.asItem(),
                ModItems.STEAMED_MILK.asItem(),
                ModItems.MILK_FOAM.asItem(),
                Items.SUGAR
        ));

        return validItems.contains(sourceStack.getItem());
    }

    private boolean isValidForFluidInputSlot(ItemStack sourceStack) {
        ItemStack waterBottleStack = new ItemStack(Items.POTION);
        waterBottleStack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER));

        List<Item> validItems = new ArrayList<>(List.of(
                Items.WATER_BUCKET,
                waterBottleStack.getItem()
        ));

        return validItems.contains(sourceStack.getItem());
    }

    private boolean isValidForContainerSlot(ItemStack sourceStack) {
        List<Item> validItems = new ArrayList<>(List.of(
                ModItems.COFFEE_CUP.asItem()
        ));

        return validItems.contains(sourceStack.getItem());
    }

    private boolean isValidForInputSlot(ItemStack sourceStack) {
        List<Item> validItems = new ArrayList<>(List.of(
                ModItems.LIGHT_ARABICA_GROUND_COFFEE.asItem(),
                ModItems.MEDIUM_ARABICA_GROUND_COFFEE.asItem(),
                ModItems.DARK_ARABICA_GROUND_COFFEE.asItem(),
                ModItems.LIGHT_ROBUSTA_GROUND_COFFEE.asItem(),
                ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE.asItem(),
                ModItems.DARK_ROBUSTA_GROUND_COFFEE.asItem()
        ));

        return validItems.contains(sourceStack.getItem());
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.COFFEE_MACHINE.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public ItemStackHandler getItemHandler() {
        System.out.println(blockEntity.itemHandler.getSlots());
        return blockEntity.itemHandler;
    }
}
