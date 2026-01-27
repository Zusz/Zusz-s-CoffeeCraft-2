package net.zusz.zcoffeecraft2.api.groundcoffee;

import net.zusz.zcoffeecraft2.item.ModItems;

public class DefaultGroundCoffees {
    public static void registerGroundCoffees() {
        //Arabica
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.LIGHT_ARABICA_GROUND_COFFEE,
                        "arabica",
                        "light",
                        ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN
                ));

        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.MEDIUM_ARABICA_GROUND_COFFEE,
                        "arabica",
                        "medium",
                        ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN
                ));
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.DARK_ARABICA_GROUND_COFFEE,
                        "arabica",
                        "dark",
                        ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN
                ));

        //Robusta
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.LIGHT_ROBUSTA_GROUND_COFFEE,
                        "robusta",
                        "light",
                        ModItems.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN
                ));
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE,
                        "robusta",
                        "medium",
                        ModItems.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN
                ));
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.DARK_ROBUSTA_GROUND_COFFEE,
                        "robusta",
                        "dark",
                        ModItems.DARK_ROASTED_ROBUSTA_COFFEE_BEAN
                ));

        //Liberica
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.LIGHT_LIBERICA_GROUND_COFFEE,
                        "liberica",
                        "light",
                        ModItems.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN
                ));
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.MEDIUM_LIBERICA_GROUND_COFFEE,
                        "liberica",
                        "medium",
                        ModItems.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN
                ));
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.DARK_LIBERICA_GROUND_COFFEE,
                        "liberica",
                        "dark",
                        ModItems.DARK_ROASTED_LIBERICA_COFFEE_BEAN
                ));
        //Excelsa
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.LIGHT_EXCELSA_GROUND_COFFEE,
                        "excelsa",
                        "light",
                        ModItems.LIGHT_ROASTED_EXCELSA_COFFEE_BEAN
                ));
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.MEDIUM_EXCELSA_GROUND_COFFEE,
                        "excelsa",
                        "medium",
                        ModItems.MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN
                ));
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.DARK_EXCELSA_GROUND_COFFEE,
                        "excelsa",
                        "dark",
                        ModItems.DARK_ROASTED_EXCELSA_COFFEE_BEAN
                ));
    }
}
