package net.zusz.zcoffeecraft2.compat;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredItem;
import net.zusz.zcoffeecraft2.component.ModDataComponents;
import net.zusz.zcoffeecraft2.item.ModItems;

import java.util.List;

public class CoffeeMachineRecipeManager {
    public static List<CoffeeMachineRecipe> getAllRecipes() {
        return List.of(
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN),
                        Ingredient.of(ModItems.ARABICA_COFFEE_CHERRY),
                        getResult(ModItems.ARABICA_COFFEE_CHERRY, ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN)
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN),
                        Ingredient.of(ModItems.ARABICA_COFFEE_CHERRY),
                        getResult(ModItems.ARABICA_COFFEE_CHERRY, ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN)
                )
        );

    }

    public static ItemStack getResult(DeferredItem ingredient1, DeferredItem ingredient2) {
        ItemStack toReturn = new ItemStack(ModItems.CUP_OF_COFFEE.get());
        if (ingredient2 == ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN) {
            toReturn.set(ModDataComponents.ROAST, "light");
        } else if (ingredient2 == ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN) {
            toReturn.set(ModDataComponents.ROAST, "medium");
        }

        return toReturn;
    }
}
