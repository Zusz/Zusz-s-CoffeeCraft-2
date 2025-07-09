package net.zusz.zcoffeecraft2.compat;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredItem;
import net.zusz.zcoffeecraft2.component.ModDataComponents;
import net.zusz.zcoffeecraft2.item.ModItems;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;


import java.util.List;

public class CoffeeMachineRecipeManager {
    public static List<CoffeeMachineRecipe> getAllRecipes() {
        ItemStack waterBottleStack = new ItemStack(Items.POTION);
        waterBottleStack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER));
        Ingredient water = Ingredient.of(waterBottleStack, new ItemStack(Items.WATER_BUCKET));
        Ingredient water_output = Ingredient.of(Items.GLASS_BOTTLE, Items.BUCKET);
        return List.of(

                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN),
                        Ingredient.of(ModItems.ARABICA_COFFEE_CHERRY),
                        water,
                        Ingredient.of(Items.AIR),
                        Ingredient.of(Items.AIR),
                        Ingredient.of(Items.AIR),
                        Ingredient.of(Items.AIR),
                        getResult(ModItems.ARABICA_COFFEE_CHERRY, ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN),
                        Ingredient.of(ModItems.ARABICA_COFFEE_CHERRY),
                        water,
                        Ingredient.of(Items.AIR),
                        Ingredient.of(Items.AIR),
                        Ingredient.of(Items.AIR),
                        Ingredient.of(Items.AIR),
                        getResult(ModItems.ARABICA_COFFEE_CHERRY, ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN),
                        Ingredient.of(ModItems.ARABICA_COFFEE_CHERRY),
                        water,
                        Ingredient.of(Items.AIR),
                        Ingredient.of(Items.AIR),
                        Ingredient.of(Items.AIR),
                        Ingredient.of(Items.AIR),
                        getResult(ModItems.ARABICA_COFFEE_CHERRY, ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN),
                        water_output
                )
        );

    }

    public static ItemStack getResult(DeferredItem ingredient1, DeferredItem ingredient2) {
        ItemStack toReturn = new ItemStack(ModItems.CUP_OF_COFFEE.get());
        if (ingredient2 == ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN) {
            toReturn.set(ModDataComponents.ROAST, "light");
        } else if (ingredient2 == ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN) {
            toReturn.set(ModDataComponents.ROAST, "medium");
        } else if (ingredient2 == ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN) {
            toReturn.set(ModDataComponents.ROAST, "dark");
        }
        return toReturn;
    }
}
