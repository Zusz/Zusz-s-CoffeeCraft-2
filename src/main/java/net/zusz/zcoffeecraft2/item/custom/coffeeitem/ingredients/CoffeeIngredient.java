package net.zusz.zcoffeecraft2.item.custom.coffeeitem.ingredients;

import net.minecraft.world.level.ItemLike;

public record CoffeeIngredient (
        ItemLike item,
        String string,
        ItemLike remaining
) {}


