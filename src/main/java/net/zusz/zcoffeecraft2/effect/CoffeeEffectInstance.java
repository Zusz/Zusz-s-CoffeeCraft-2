package net.zusz.zcoffeecraft2.effect;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

public class CoffeeEffectInstance {
    public final MobEffect effect;
    public final int duration;
    public final int amplifier;
    public int ticksUntilApply;

    public final Holder<MobEffect> secondaryEffect;
    public final int secondaryEffectDuration;
    public final int secondaryEffectAmplifier;

    public CoffeeEffectInstance(
            MobEffect effect,
            int duration,
            int amplifier,
            int delayTicks,
            Holder<MobEffect> secondaryEffect,
            int secondaryEffectDuration,
            int secondaryEffectAmplifier
    ) {
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.ticksUntilApply = delayTicks;

        this.secondaryEffect = secondaryEffect;
        this.secondaryEffectDuration = secondaryEffectDuration;
        this.secondaryEffectAmplifier = secondaryEffectAmplifier;
    }
}