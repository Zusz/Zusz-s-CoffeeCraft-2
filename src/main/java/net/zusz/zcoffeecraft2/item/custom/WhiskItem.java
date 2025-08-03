package net.zusz.zcoffeecraft2.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class WhiskItem extends Item {
    public WhiskItem(Properties properties) {
        super(properties.stacksTo(1)); // Usually not stackable
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        return new ItemStack(this); // Or another item if it degrades or changes
    }
}