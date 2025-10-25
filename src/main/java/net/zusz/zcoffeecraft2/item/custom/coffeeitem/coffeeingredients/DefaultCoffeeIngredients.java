package net.zusz.zcoffeecraft2.item.custom.coffeeitem.coffeeingredients;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.zusz.zcoffeecraft2.item.ModItems;

public class DefaultCoffeeIngredients {
    public static void registerCoffeeIngredients() {
        CoffeeIngredientRegistry.register(new CoffeeIngredient(
                Items.SUGAR, "sugar",
                Items.AIR,
                Component.translatable("coffeeingredient.zcoffeecraft2.sugar")
        ));
        CoffeeIngredientRegistry.register(new CoffeeIngredient(
                Items.HONEY_BOTTLE, "honey",
                Items.GLASS_BOTTLE,
                Component.translatable("coffeeingredient.zcoffeecraft2.honey")
        ));
        CoffeeIngredientRegistry.register(new CoffeeIngredient(
                Items.COCOA_BEANS, "chocolate",
                Items.AIR,
                Component.translatable("coffeeingredient.zcoffeecraft2.chocolate")
        ));
        CoffeeIngredientRegistry.register(new CoffeeIngredient(
                Items.MILK_BUCKET, "milk",
                Items.BUCKET,
                Component.translatable("coffeeingredient.zcoffeecraft2.milk")
        ));
        CoffeeIngredientRegistry.register(new CoffeeIngredient(
                ModItems.WHIPPED_CREAM.asItem(), "whipped_cream",
                Items.AIR,
                Component.translatable("coffeeingredient.zcoffeecraft2.whipped_cream")
        ));
        CoffeeIngredientRegistry.register(new CoffeeIngredient(
                ModItems.STEAMED_MILK.asItem(), "steamed_milk",
                Items.BUCKET,
                Component.translatable("coffeeingredient.zcoffeecraft2.steamed_milk")
        ));
        CoffeeIngredientRegistry.register(new CoffeeIngredient(
                ModItems.MILK_FOAM.asItem(), "milk_foam",
                Items.BUCKET,
                Component.translatable("coffeeingredient.zcoffeecraft2.milk_foam")
        ));
    }
}
