package net.zusz.zcoffeecraft2.api.groundcoffee;

import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroundCoffeeRegistry {
    private static final List<GroundCoffee> GROUND_COFFEE_LIST = new ArrayList<>();

    public static void register(GroundCoffee groundCoffee) {
        GROUND_COFFEE_LIST.add(groundCoffee);
    }

    public static Optional<GroundCoffee> getGroundCoffee(ItemLike groundCoffeeItem) {
        for (GroundCoffee groundCoffee : GROUND_COFFEE_LIST) {
            if (groundCoffee.item().asItem() == groundCoffeeItem.asItem()) {
                return  Optional.of(groundCoffee);
            }
        }
        return Optional.empty();
    }
}
