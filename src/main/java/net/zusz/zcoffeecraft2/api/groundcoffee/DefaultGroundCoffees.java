package net.zusz.zcoffeecraft2.api.groundcoffee;

import net.zusz.zcoffeecraft2.item.ModItems;

public class DefaultGroundCoffees {
    public static void registerGroundCoffees() {
        //Arabica
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.LIGHT_ARABICA_GROUND_COFFEE,
                        "arabica",
                        "light")
        );
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.MEDIUM_ARABICA_GROUND_COFFEE,
                        "arabica",
                        "medium")
        );
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.DARK_ARABICA_GROUND_COFFEE,
                        "arabica",
                        "dark")
        );

        //Robusta
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.LIGHT_ROBUSTA_GROUND_COFFEE,
                        "robusta",
                        "light")
        );
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE,
                        "robusta",
                        "medium")
        );
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.DARK_ROBUSTA_GROUND_COFFEE,
                        "robusta",
                        "dark")
        );

        //Liberica
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.LIGHT_LIBERICA_GROUND_COFFEE,
                        "liberica",
                        "light")
        );
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.MEDIUM_LIBERICA_GROUND_COFFEE,
                        "liberica",
                        "medium")
        );
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.DARK_LIBERICA_GROUND_COFFEE,
                        "liberica",
                        "dark")
        );
        //Excelsa
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.LIGHT_EXCELSA_GROUND_COFFEE,
                        "excelsa",
                        "light")
        );
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.MEDIUM_EXCELSA_GROUND_COFFEE,
                        "excelsa",
                        "medium")
        );
        GroundCoffeeRegistry.register(
                new GroundCoffee(
                        ModItems.DARK_EXCELSA_GROUND_COFFEE,
                        "excelsa",
                        "dark")
        );
    }
}
