package net.zusz.zcoffeecraft2.effect;


import net.minecraft.world.effect.MobEffect;

public class CoffeeEffectInstance {
        public final MobEffect effect;
        public final int duration;
        public final int amplifier;
        public int ticksUntilApply;

        public CoffeeEffectInstance(MobEffect effect, int duration, int amplifier, int delayTicks) {
            this.effect = effect;
            this.duration = duration;
            this.amplifier = amplifier;
            this.ticksUntilApply = delayTicks;
        }
}
