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
        Ingredient waterBottle = Ingredient.of(waterBottleStack);
        return List.of(

                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN),
                        Ingredient.of(ModItems.ARABICA_COFFEE_CHERRY),
                        waterBottle,
                        Ingredient.of(Items.AIR),
                        Ingredient.of(Items.AIR),
                        Ingredient.of(Items.AIR),
                        Ingredient.of(Items.AIR),
                        getResult(ModItems.ARABICA_COFFEE_CHERRY, ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN),
                        new ItemStack(Items.GLASS_BOTTLE)
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN),
                        Ingredient.of(ModItems.ARABICA_COFFEE_CHERRY),
                        waterBottle,
                        Ingredient.of(Items.AIR),
                        Ingredient.of(Items.AIR),
                        Ingredient.of(Items.AIR),
                        Ingredient.of(Items.AIR),
                        getResult(ModItems.ARABICA_COFFEE_CHERRY, ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN),
                        new ItemStack(Items.GLASS_BOTTLE)
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
