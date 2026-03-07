package net.zusz.zcoffeecraft2.api.coffeefluids;


import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoffeeFluidRegistry {

    private static final List<CoffeeFluid>FLUIDS = new ArrayList<>();

    public static void register(CoffeeFluid recipe) {
        FLUIDS.add(recipe);
    }

    public static Optional<CoffeeFluid> getFluid(ItemLike item) {
        if (item != null) {
            for (CoffeeFluid fluid : FLUIDS) {
                if (fluid.item().asItem() == item.asItem()) {
                    return Optional.of(fluid);
                }
            }
        }
        return Optional.empty();
    }

    public static Optional<List<CoffeeFluid>> getFluidsForString(String string) {
        if (string != null) {
            List <CoffeeFluid> validFluids = new ArrayList<>();
            for (CoffeeFluid fluid : FLUIDS) {
                if (fluid.fluidName() == string) {
                    validFluids.add(fluid);
                }
            }
            return Optional.of(validFluids);
        }
        return Optional.empty();
    }

    public static List<CoffeeFluid> getAll() {
        return List.copyOf(FLUIDS);
    }

}
