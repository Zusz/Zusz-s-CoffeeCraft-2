package net.zusz.zcoffeecraft2.api.coffeefluids;

import net.minecraft.world.item.Items;

public class DefaultCoffeeFluids {

    public static void registerCoffeeFluids () {
        CoffeeFluidRegistry.register(new CoffeeFluid("water", Items.WATER_BUCKET, Items.BUCKET));
        CoffeeFluidRegistry.register(new CoffeeFluid("water", Items.POTION, Items.GLASS_BOTTLE));
        CoffeeFluidRegistry.register(new CoffeeFluid("milk", Items.MILK_BUCKET, Items.BUCKET));
    }

}
