package net.zusz.zcoffeecraft2.item.custom.coffeeitem.ingredients;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.ItemLike;

import java.awt.*;

public record CoffeeIngredient (
        ItemLike item,
        String string,
        ItemLike remaining,
        MutableComponent toolTip
) {}


