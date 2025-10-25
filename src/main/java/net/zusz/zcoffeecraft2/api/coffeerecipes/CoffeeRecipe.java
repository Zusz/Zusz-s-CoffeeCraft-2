package net.zusz.zcoffeecraft2.api.coffeerecipes;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public record CoffeeRecipe(
        String name,
        List<String> ingredients,
        Holder<MobEffect> effect,
        int baseDuration,
        int baseAmplifier,
        int baseDelay,
        @Nullable Holder<MobEffect> secondaryEffect,
        int secondaryDuration
) {}