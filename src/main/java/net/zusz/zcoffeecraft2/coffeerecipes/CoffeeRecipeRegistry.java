package net.zusz.zcoffeecraft2.coffeerecipes;


import java.util.*;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.zusz.zcoffeecraft2.item.ModItems;

public class CoffeeRecipeRegistry {
    private static final List<CoffeeRecipe> RECIPES = new ArrayList<>();

    public static void register(CoffeeRecipe recipe) {
        RECIPES.add(recipe);
    }

    public static Optional<CoffeeRecipe> getRecipe(List<String> ingredients) {
        if (ingredients != null) {
            for (CoffeeRecipe recipe : RECIPES) {
                if (recipe.ingredients().equals(ingredients)) {
                    return Optional.of(recipe);
                }
            }
        }
        return Optional.empty();
    }

    public static List<String> getIngredients (CoffeeRecipe recipe) {
        return recipe.ingredients();
    }

    public static final ItemLike convertStringIngredientToItem(String ingredient) {
        return switch (ingredient) {
            case "sugar" -> Items.SUGAR;
            case "honey" -> Items.HONEY_BOTTLE;
            case "milk" -> Items.MILK_BUCKET;
            case "chocolate" -> Items.COCOA_BEANS;
            case "whipped_cream" -> ModItems.WHIPPED_CREAM;
            case "milk_foam" -> ModItems.MILK_FOAM;
            case "steamed_milk" -> ModItems.STEAMED_MILK;


            default -> Items.AIR;
        };
    }

    public static List<ItemLike>getIngredientsAsItems (CoffeeRecipe recipe) {
         List<ItemLike> toReturn = new ArrayList<>(
                 List.of()
         );
         for (String ingredient : recipe.ingredients()) {
             toReturn.add(convertStringIngredientToItem(ingredient));
         }
         return toReturn;
    }



    public static boolean isValid(List<String> ingredients) {
        return getRecipe(ingredients).isPresent();
    }

    public static String getName(List<String> ingredients) {
        if (ingredients != null) {
            for (CoffeeRecipe recipe : RECIPES) {
                if (new HashSet<>(recipe.ingredients()).equals(new HashSet<>(ingredients))) {
                    return recipe.name();
                }
            }
        }
        return "coffeetype.zcoffeecraft2.notype";
    }

    public static List<CoffeeRecipe> getAll() {
        return Collections.unmodifiableList(RECIPES);
    }

    public static void clear() {
        RECIPES.clear();
    }
}