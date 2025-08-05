package net.zusz.zcoffeecraft2.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, ZCoffeeCraft2.MOD_ID);

    public  static final Holder<MobEffect> CAFFEINATED_EFFECT = MOB_EFFECTS.register("caffeinated",
            ()-> new CaffeinatedEffect(MobEffectCategory.NEUTRAL, 0xAB5111));
    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

}
