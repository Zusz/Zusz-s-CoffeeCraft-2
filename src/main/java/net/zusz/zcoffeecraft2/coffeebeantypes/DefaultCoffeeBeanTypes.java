package net.zusz.zcoffeecraft2.coffeebeantypes;


import net.minecraft.network.chat.Component;

public class DefaultCoffeeBeanTypes {
    public static void registerCoffeeBeanTypes(){
        CoffeeBeanTypeRegistry.register(new CoffeeBeanType(
                "arabica",
                Component.translatable("coffeeingredient.zcoffeecraft2.arabica"),
                1200,
                0,
                false,
                0,
                0
        ));
        CoffeeBeanTypeRegistry.register(new CoffeeBeanType(
                "robusta",
                Component.translatable("coffeeingredient.zcoffeecraft2.robusta"),
                0,
                1,
                false,
                0,
                0
        ));
        CoffeeBeanTypeRegistry.register(new CoffeeBeanType(
                "liberica",
                Component.translatable("coffeeingredient.zcoffeecraft2.liberica"),
                -600,
                0,
                true,
                0,
                0
        ));
    }
}
