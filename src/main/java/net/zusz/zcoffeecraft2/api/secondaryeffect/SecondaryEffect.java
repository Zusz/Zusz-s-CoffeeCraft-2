package net.zusz.zcoffeecraft2.api.secondaryeffect;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

import java.util.Optional;

public record SecondaryEffect (
        String bean,
        Optional<Holder<MobEffect>> secondaryEffect,
        Optional<Integer> secondaryDuration,
        Optional<Integer> secondaryAmplifier
){}