package net.zusz.zcoffeecraft2.api.coffeefluids;

import net.minecraft.world.level.ItemLike;

public record CoffeeFluid (
        String fluidName,
        ItemLike item,
        ItemLike remainingItem

        ){
}
