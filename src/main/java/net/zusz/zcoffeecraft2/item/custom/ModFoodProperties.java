package net.zusz.zcoffeecraft2.item.custom;

import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties CUP_OF_COFFEE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.5f)
            .build();
}
