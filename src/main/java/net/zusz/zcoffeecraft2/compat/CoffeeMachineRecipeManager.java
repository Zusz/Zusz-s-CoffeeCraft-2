package net.zusz.zcoffeecraft2.compat;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredItem;
import net.zusz.zcoffeecraft2.block.entity.CoffeeMachineMethods;
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
        Ingredient allGrounds = Ingredient.of(ModItems.LIGHT_ARABICA_GROUND_COFFEE, ModItems.MEDIUM_ARABICA_GROUND_COFFEE, ModItems.DARK_ARABICA_GROUND_COFFEE);

        return List.of(

                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.LIGHT_ARABICA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.LIGHT_ARABICA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, null, null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.MEDIUM_ARABICA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.MEDIUM_ARABICA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, null, null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        Ingredient.of(ModItems.DARK_ARABICA_GROUND_COFFEE),
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(ModItems.DARK_ARABICA_GROUND_COFFEE.asItem(), ModItems.COFFEE_CUP.asItem(), null, null, null, null, null),
                        water_output
                ),
                new CoffeeMachineRecipe(
                        allGrounds,
                        Ingredient.of(ModItems.COFFEE_CUP),
                        water,
                        Ingredient.of(ModItems.MILK_FOAM),
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        getResult(null, ModItems.COFFEE_CUP.asItem(), null, ModItems.MILK_FOAM.asItem(), null, null, null),
                        water_output
                )

        );

    }

    public static ItemStack getResult(Item input, Item container, Item fluidInputItem, Item ingredient1, Item ingredient2, Item ingredient3, Item ingredient4) {
        ItemStack output = setOutputItemBasedOnContainer(container);

        output = addBeanAndRoastToOutput(output, input);

        List<String> ingredients = new ArrayList<>(List.of());

        ingredients = addToIngredientsList(ingredients, ingredient1);
        ingredients = addToIngredientsList(ingredients, ingredient2);
        ingredients = addToIngredientsList(ingredients, ingredient3);
        ingredients = addToIngredientsList(ingredients, ingredient4);

        output.set(ModDataComponents.INGREDIENTS, ingredients);

        return output;
    }


    //methods from CoffeeMachineMethods, slightly modified (now null inputs don't set output to null, just don't do anything)
    public static List<String> addToIngredientsList(List<String> ingredients, Item item) {
        if (item == Items.SUGAR) {
            ingredients.add("sugar");
        } else if (item == Items.HONEY_BOTTLE) {
            ingredients.add("honey");
        } else if (item == Items.MILK_BUCKET) {
            ingredients.add("milk");
        } else if (item == Items.COCOA_BEANS) {
            ingredients.add("cocoa");
        } else if (item == ModItems.WHIPPED_CREAM.asItem()) {
            ingredients.add("whipped_cream");
        } else if (item == ModItems.MILK_FOAM.asItem()) {
            ingredients.add("milk_foam");
        } else if (item == ModItems.STEAMED_MILK.asItem()) {
            ingredients.add("steamed_milk");
        }
        return ingredients;
    }

    public static ItemStack addBeanAndRoastToOutput(ItemStack output, Item bean) {
        if (bean == ModItems.LIGHT_ARABICA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "light");
            output.set(ModDataComponents.BEAN, "arabica");
        } else if (bean == ModItems.MEDIUM_ARABICA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "medium");
            output.set(ModDataComponents.BEAN, "arabica");
        } else if (bean == ModItems.DARK_ARABICA_GROUND_COFFEE.get()) {
            output.set(ModDataComponents.ROAST, "dark");
            output.set(ModDataComponents.BEAN, "arabica");
        }
        return output;
    }

    public static ItemStack setOutputItemBasedOnContainer(Item containter) {
        ItemStack output = null;
        if (containter == ModItems.COFFEE_CUP.get()){
            output = new ItemStack(ModItems.CUP_OF_COFFEE.get(), 1);
        }
        return output;
    }
}
