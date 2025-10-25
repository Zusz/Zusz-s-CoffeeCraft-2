package net.zusz.zcoffeecraft2.api.coffeerecipes;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

import java.util.List;

public class DefaultCoffeeRecipes {
    static Holder<MobEffect> speed = BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.withDefaultNamespace("speed")).get();
    static Holder<MobEffect> jump = BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.withDefaultNamespace("jump_boost")).get();
    static Holder<MobEffect> absorption = BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.withDefaultNamespace("absorption")).get();
    static Holder<MobEffect> resistance = BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.withDefaultNamespace("resistance")).get();
    static Holder<MobEffect> regeneration = BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.withDefaultNamespace("regeneration")).get();
    static Holder<MobEffect> strength = BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.withDefaultNamespace("strength")).get();
    static Holder<MobEffect> night_vision = BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.withDefaultNamespace("night_vision")).get();
    static Holder<MobEffect> health_boost = MobEffects.HEALTH_BOOST;
    public static void registerCoffeeRecipes() {
        //Espresso
        CoffeeRecipeRegistry.register(new CoffeeRecipe(
                "coffeetype.zcoffeecraft2.espresso",
                List.of(),
                speed,
                3600,
                0,
                0,
                jump,
                1200
        ));
        CoffeeRecipeRegistry.register(new CoffeeRecipe(
                "coffeetype.zcoffeecraft2.espresso",
                List.of("sugar"),
                speed,
                4800,
                0,
                0,
                jump,
                1200
        ));
        //Macchiato
        CoffeeRecipeRegistry.register(new CoffeeRecipe(
                "coffeetype.zcoffeecraft2.macchiato",
                List.of("milk_foam"),
                jump,
                3600,
                0,
                0,
                speed,
                1200
        ));
        CoffeeRecipeRegistry.register(new CoffeeRecipe(
                "coffeetype.zcoffeecraft2.macchiato",
                List.of("milk_foam", "sugar"),
                jump,
                4800,
                0,
                0,
                speed,
                1200
        ));
        //Con Panna
        CoffeeRecipeRegistry.register(new CoffeeRecipe(
                "coffeetype.zcoffeecraft2.con_panna",
                List.of("whipped_cream"),
                absorption,
                3600,
                0,
                0,
                strength,
                1200
        ));
        CoffeeRecipeRegistry.register(new CoffeeRecipe(
                "coffeetype.zcoffeecraft2.con_panna",
                List.of("whipped_cream", "sugar"),
                absorption,
                4800,
                0,
                0,
                strength,
                1200
        ));
        //Flat White
        CoffeeRecipeRegistry.register(new CoffeeRecipe(
                "coffeetype.zcoffeecraft2.flat_white",
                List.of("steamed_milk"),
                resistance,
                3600,
                0,
                0,
                strength,
                1200
        ));
        CoffeeRecipeRegistry.register(new CoffeeRecipe(
                "coffeetype.zcoffeecraft2.flat_white",
                List.of("steamed_milk", "sugar"),
                resistance,
                4800,
                0,
                0,
                strength,
                1200
        ));
        //Latte
        CoffeeRecipeRegistry.register(new CoffeeRecipe(
                "coffeetype.zcoffeecraft2.latte",
                List.of("steamed_milk", "milk_foam"),
                regeneration,
                3600,
                0,
                0,
                speed,
                1200
        ));
        CoffeeRecipeRegistry.register(new CoffeeRecipe(
                "coffeetype.zcoffeecraft2.latte",
                List.of("steamed_milk", "milk_foam", "sugar"),
                regeneration,
                4800,
                0,
                0,
                speed,
                1200
        ));
        //Marocchino
        CoffeeRecipeRegistry.register(new CoffeeRecipe(
                "coffeetype.zcoffeecraft2.marocchino",
                List.of("chocolate", "milk_foam"),
                night_vision,
                3600,
                0,
                0,
                speed,
                1200
        ));
        CoffeeRecipeRegistry.register(new CoffeeRecipe(
                "coffeetype.zcoffeecraft2.marocchino",
                List.of("chocolate", "milk_foam", "sugar"),
                night_vision,
                4800,
                0,
                0,
                speed,
                1200
        ));
        //Mocha
        CoffeeRecipeRegistry.register(new CoffeeRecipe(
                "coffeetype.zcoffeecraft2.mocha",
                List.of("chocolate","steamed_milk", "whipped_cream"),
                strength,
                3600,
                0,
                0,
                absorption,
                1200
        ));
        CoffeeRecipeRegistry.register(new CoffeeRecipe(
                "coffeetype.zcoffeecraft2.mocha",
                List.of("chocolate", "steamed_milk","whipped_cream", "sugar"),
                strength,
                4800,
                0,
                0,
                absorption,
                1200
        ));
        //Honey Raf
        CoffeeRecipeRegistry.register(new CoffeeRecipe(
                "coffeetype.zcoffeecraft2.honey_raf",
                List.of("honey", "milk_foam"),
                health_boost,
                3600,
                0,
                0,
                regeneration,
                1200
        ));
        CoffeeRecipeRegistry.register(new CoffeeRecipe(
                "coffeetype.zcoffeecraft2.honey_raf",
                List.of("honey", "milk_foam", "sugar"),
                health_boost,
                4800,
                0,
                0,
                regeneration,
                1200
        ));

    }


}
