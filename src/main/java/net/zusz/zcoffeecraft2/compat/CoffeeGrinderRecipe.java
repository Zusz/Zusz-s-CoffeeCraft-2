package net.zusz.zcoffeecraft2.compat;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class CoffeeGrinderRecipe {

    private final ItemStack input;
    private final ItemStack output;


    public CoffeeGrinderRecipe(ItemStack input, ItemStack output) {this.input = input; this.output = output;}

    public ItemStack getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }
}
