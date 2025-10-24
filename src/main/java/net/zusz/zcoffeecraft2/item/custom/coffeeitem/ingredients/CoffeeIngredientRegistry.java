package net.zusz.zcoffeecraft2.item.custom.coffeeitem.ingredients;

import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoffeeIngredientRegistry {
    private static final List<CoffeeIngredient> INGREDIENTS = new ArrayList<>();

    public static void register(CoffeeIngredient ingredient) {
        INGREDIENTS.add(ingredient);
    }

    public static Optional<ItemLike> getItemFromString(String ingredient) {
        if (ingredient != null) {
            for (CoffeeIngredient coffeeIngredient : INGREDIENTS) {
                if (ingredient.equals(coffeeIngredient.string())) {
                    return Optional.of(coffeeIngredient.item().asItem());
                }
            }
        }
        return Optional.empty();
    }

    public static Optional<String> getStringFromItem(ItemLike ingredient) {
        if (ingredient != null) {
            for (CoffeeIngredient coffeeIngredient : INGREDIENTS) {
                if (ingredient.asItem().equals(coffeeIngredient.item().asItem())) {
                    return Optional.of(coffeeIngredient.string());
                }
            }
        }
        return Optional.empty();
    }

    public static Optional<ItemLike> getRemaining(String ingredient) {
        if (ingredient != null) {
            for (CoffeeIngredient coffeeIngredient : INGREDIENTS) {
                if (coffeeIngredient.string().equals(ingredient)) {
                    return Optional.of(coffeeIngredient.remaining().asItem());
                }
            }
        }
        return Optional.empty();
    }
}
