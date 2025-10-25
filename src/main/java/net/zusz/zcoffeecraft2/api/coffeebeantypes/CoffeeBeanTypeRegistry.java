package net.zusz.zcoffeecraft2.api.coffeebeantypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoffeeBeanTypeRegistry {
    public static final List<CoffeeBeanType> COFFEE_BEAN_TYPES = new ArrayList<CoffeeBeanType>();

    public static void register(CoffeeBeanType coffeeBeanType) {COFFEE_BEAN_TYPES.add(coffeeBeanType);}

    public static Optional<CoffeeBeanType> getBeanTypeFromString(String string) {
        for (CoffeeBeanType coffeeBeanType : COFFEE_BEAN_TYPES) {
            if (coffeeBeanType.name().equals(string)) {
                return Optional.of(coffeeBeanType);
            }
        }
        return Optional.empty();
    }
}
