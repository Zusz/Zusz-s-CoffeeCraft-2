package net.zusz.zcoffeecraft2.api.coffeerecipes;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.ItemLike;
import net.zusz.zcoffeecraft2.api.secondaryeffect.SecondaryEffect;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Optional;

public record CoffeeRecipe(
        String name,
        List<String> ingredients,
        Holder<MobEffect> effect,
        int baseDuration,
        int baseAmplifier,
        int baseDelay,
        List<SecondaryEffect> secondaryEffects,
        ItemLike inputItem,
        String fluid,
        ItemLike outputItem,
        boolean requiresBean
) {}

