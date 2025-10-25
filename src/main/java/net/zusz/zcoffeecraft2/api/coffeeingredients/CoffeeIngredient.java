package net.zusz.zcoffeecraft2.api.coffeeingredients;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.ItemLike;

public record CoffeeIngredient (
        ItemLike item,
        String string,
        ItemLike remaining,
        MutableComponent toolTip
) {}


