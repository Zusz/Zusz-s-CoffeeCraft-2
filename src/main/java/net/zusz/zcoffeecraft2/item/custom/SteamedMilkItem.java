package net.zusz.zcoffeecraft2.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.zusz.zcoffeecraft2.item.ModItems;

import java.util.Properties;

public class SteamedMilkItem extends Item {
    public SteamedMilkItem(Properties properties) {super(properties.stacksTo(1));}

    @Override
    public ItemStack getCraftingRemainder(ItemStack itemStack) {
        return new ItemStack(Items.BUCKET);
    }
}
