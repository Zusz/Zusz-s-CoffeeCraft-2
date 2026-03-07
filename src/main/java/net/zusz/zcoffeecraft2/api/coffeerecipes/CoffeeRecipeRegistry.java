package net.zusz.zcoffeecraft2.api.coffeerecipes;


import java.util.*;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.ItemLike;
import net.zusz.zcoffeecraft2.api.coffeeingredients.CoffeeIngredientRegistry;
import net.zusz.zcoffeecraft2.api.secondaryeffect.SecondaryEffect;

public class CoffeeRecipeRegistry {
    private static final List<CoffeeRecipe> RECIPES = new ArrayList<>();

    public static void register(CoffeeRecipe recipe) {
        RECIPES.add(recipe);
    }

    public static Optional<CoffeeRecipe> getRecipe(List<String> ingredients, String fluid) {
        if (fluid == null) {fluid = "water";}
        if (ingredients != null) {
            for (CoffeeRecipe recipe : RECIPES) {
                if (compareListIgnoreOrder(recipe.ingredients(), ingredients)) {
                    return Optional.of(recipe);
                }
            }
        }
        return Optional.empty();
    }

    public static List<String> getIngredients (CoffeeRecipe recipe) {
        return recipe.ingredients();
    }


    public static List<ItemLike>getIngredientsAsItems (CoffeeRecipe recipe) {
         List<ItemLike> toReturn = new ArrayList<>(
                 List.of()
         );
         for (String ingredient : recipe.ingredients()) {
             ItemLike toAdd = null;
             if (CoffeeIngredientRegistry.getItemFromString(ingredient).isPresent()) {
                 toAdd = CoffeeIngredientRegistry.getItemFromString(ingredient).get();
                 toReturn.add(toAdd);
             }

         }
         return toReturn;
    }


    public static ItemLike getOutputItem(CoffeeRecipe recipe) {return recipe.outputItem();}
    public static boolean isValid(List<String> ingredients, String fluid) {
        return getRecipe(ingredients, fluid).isPresent();
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

    public static SecondaryEffect getSecondaryEffect(CoffeeRecipe recipe, String bean) {
        for (SecondaryEffect secondaryEffect : recipe.secondaryEffects()) {
            if (secondaryEffect.bean().equals(bean)) {
                return secondaryEffect;
            }
        }
        return null;
    }

    public static List<CoffeeRecipe> getAll() {
        return Collections.unmodifiableList(RECIPES);
    }

    public static void clear() {
        RECIPES.clear();
    }

    public static boolean compareListIgnoreOrder(List<String> a, List<String> b) {
        if (a.size() != b.size()) return false;

        List<String> aCopy = new ArrayList<>(a);
        List<String> bCopy = new ArrayList<>(b);

        Collections.sort(aCopy);
        Collections.sort(bCopy);

        return aCopy.equals(bCopy);
    }
}