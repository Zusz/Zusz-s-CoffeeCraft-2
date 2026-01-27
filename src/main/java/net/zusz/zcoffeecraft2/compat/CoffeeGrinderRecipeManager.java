package net.zusz.zcoffeecraft2.compat;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.zusz.zcoffeecraft2.api.groundcoffee.GroundCoffee;
import net.zusz.zcoffeecraft2.api.groundcoffee.GroundCoffeeRegistry;

import java.util.ArrayList;
import java.util.List;

public class CoffeeGrinderRecipeManager {

    public static List<CoffeeGrinderRecipe> getAllRecipes() {

        List<CoffeeGrinderRecipe> coffeeGrinderRecipes = new ArrayList<>();

        for (GroundCoffee groundCoffee: GroundCoffeeRegistry.getAll()) {
            coffeeGrinderRecipes.add(new CoffeeGrinderRecipe(new ItemStack(groundCoffee.beanItem()), new ItemStack(groundCoffee.item())));
        }

        return coffeeGrinderRecipes;

    }
}
