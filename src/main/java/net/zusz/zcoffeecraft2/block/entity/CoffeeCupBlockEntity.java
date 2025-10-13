package net.zusz.zcoffeecraft2.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.zusz.zcoffeecraft2.block.custom.enums.RoastType;
import net.zusz.zcoffeecraft2.block.entity.ModBlockEntities;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.jetbrains.annotations.NotNull;

public class CoffeeCupBlockEntity extends BlockEntity {

    public final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 4);
            }
        }

    };

    private ItemStack coffeeStack = ItemStack.EMPTY;

    public CoffeeCupBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COFFEE_CUP_BE.get(), pos, state);
    }

    public void setCoffeeStack(@NotNull ItemStack stack) {
        this.coffeeStack = stack.copy();
        itemHandler.setStackInSlot(0, coffeeStack);
        setChanged();
    }

    public ItemStack getCoffeeStack() {
        return coffeeStack;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));

        super.saveAdditional(pTag, pRegistries);
    }


    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));

    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    public void decreaseCoffeeStack(int i) {
        itemHandler.setStackInSlot(0, new ItemStack(itemHandler.getStackInSlot(0).getItem(), itemHandler.getStackInSlot(0).getCount()-1));
        coffeeStack = itemHandler.getStackInSlot(0);
    }
}