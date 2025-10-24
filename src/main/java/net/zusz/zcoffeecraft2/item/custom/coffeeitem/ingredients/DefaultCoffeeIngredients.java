package net.zusz.zcoffeecraft2.item.custom.coffeeitem.ingredients;

import net.minecraft.world.item.Items;
import net.zusz.zcoffeecraft2.item.ModItems;

public class DefaultCoffeeIngredients {
    public static void registerCoffeeIngredients() {
        CoffeeIngredientRegistry.register(new CoffeeIngredient(
                Items.SUGAR, "sugar",
                Items.AIR
        ));
        CoffeeIngredientRegistry.register(new CoffeeIngredient(
                Items.HONEY_BOTTLE, "honey",
                Items.GLASS_BOTTLE
        ));
        CoffeeIngredientRegistry.register(new CoffeeIngredient(
                Items.COCOA_BEANS, "chocolate",
                Items.AIR
        ));
        CoffeeIngredientRegistry.register(new CoffeeIngredient(
                Items.MILK_BUCKET, "milk",
                Items.BUCKET
        ));
        CoffeeIngredientRegistry.register(new CoffeeIngredient(
                ModItems.WHIPPED_CREAM.asItem(), "whipped_cream",
                Items.AIR
        ));
        CoffeeIngredientRegistry.register(new CoffeeIngredient(
                ModItems.STEAMED_MILK.asItem(), "steamed_milk",
                Items.BUCKET
        ));
        CoffeeIngredientRegistry.register(new CoffeeIngredient(
                ModItems.MILK_FOAM.asItem(), "milk_foam",
                Items.BUCKET
        ));
    }
}
