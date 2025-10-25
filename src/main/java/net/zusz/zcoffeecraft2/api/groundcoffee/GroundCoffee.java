package net.zusz.zcoffeecraft2.api.groundcoffee;

import net.minecraft.world.level.ItemLike;

public record GroundCoffee (
        ItemLike item,
        String bean,
        String roast
){}