package net.zusz.zcoffeecraft2.compat;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
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


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoffeeMachineRecipeManager {
    public static List<CoffeeMachineRecipe> getAllRecipes() {
        ItemStack waterBottleStack = new ItemStack(Items.POTION);
        waterBottleStack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER));
        Ingredient water = Ingredient.of(waterBottleStack, new ItemStack(Items.WATER_BUCKET));
        Ingredient water_output = Ingredient.of(Items.GLASS_BOTTLE, Items.BUCKET);
        Ingredient allBeans = Ingredient.of(ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN, ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN, ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN);

        return List.of(

                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.COFFEE_CUP, ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.COFFEE_CUP, ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.COFFEE_CUP, ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        allBeans,
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(Items.SUGAR),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.COFFEE_CUP, null, Items.SUGAR),
                        water_output
                )

        );

    }

    public static ItemStack getResult(DeferredItem ingredient1, DeferredItem ingredient2, Item ingredient3) {
        ItemStack toReturn = new ItemStack(ModItems.ARABICA_COFFEE_CHERRY.get());
        if (ingredient1.is(ModItems.COFFEE_CUP)) {
            toReturn = new ItemStack(ModItems.CUP_OF_COFFEE.get());
        }
        if (ingredient2 == ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN) {
            toReturn.set(ModDataComponents.ROAST, "light");
            toReturn.set(ModDataComponents.BEAN, "arabica");
        } else if (ingredient2 == ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN) {
            toReturn.set(ModDataComponents.ROAST, "medium");
            toReturn.set(ModDataComponents.BEAN, "arabica");
        } else if (ingredient2 == ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN) {
            toReturn.set(ModDataComponents.ROAST, "dark");
            toReturn.set(ModDataComponents.BEAN, "arabica");
        }
        if (ingredient3 == Items.SUGAR) {
            toReturn.set(ModDataComponents.INGREDIENTS, new ArrayList<String>(Collections.singleton("sugar")));
        }
        return toReturn;
    }

    public static ItemStack getResult(DeferredItem ingredient1, DeferredItem ingredient2) {
        ItemStack toReturn = new ItemStack(ModItems.ARABICA_COFFEE_CHERRY.get());
        if (ingredient1.is(ModItems.COFFEE_CUP)) {
            toReturn = new ItemStack(ModItems.CUP_OF_COFFEE.get());
        }
        if (ingredient2 == ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN) {
            toReturn.set(ModDataComponents.ROAST, "light");
            toReturn.set(ModDataComponents.BEAN, "arabica");
        } else if (ingredient2 == ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN) {
            toReturn.set(ModDataComponents.ROAST, "medium");
            toReturn.set(ModDataComponents.BEAN, "arabica");
        } else if (ingredient2 == ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN) {
            toReturn.set(ModDataComponents.ROAST, "dark");
            toReturn.set(ModDataComponents.BEAN, "arabica");
        }
        return toReturn;
    }
}
