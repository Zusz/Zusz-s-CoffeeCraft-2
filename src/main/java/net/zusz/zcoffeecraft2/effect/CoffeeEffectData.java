package net.zusz.zcoffeecraft2.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import java.util.*;

public class CoffeeEffectData {
    private static final Map<UUID, List<CoffeeEffectInstance>> queuedEffects = new HashMap<>();

    public static void addEffect(ServerPlayer player, CoffeeEffectInstance instance) {
        queuedEffects
                .computeIfAbsent(player.getUUID(), uuid -> new ArrayList<>())
                .add(instance);
    }

    public static void tick(ServerLevel level) {
        // Use lookupOrThrow instead of registryOrThrow
        var mobEffectRegistry = level.registryAccess().lookupOrThrow(Registries.MOB_EFFECT);

        for (ServerPlayer player : level.players()) {
            List<CoffeeEffectInstance> queue = queuedEffects.get(player.getUUID());
            if (queue == null || queue.isEmpty()) continue;

            Iterator<CoffeeEffectInstance> iterator = queue.iterator();
            while (iterator.hasNext()) {
                CoffeeEffectInstance effect = iterator.next();
                effect.ticksUntilApply--;

                if (effect.ticksUntilApply <= 0) {
                    // Primary effect
                    if (effect.effect != null) {
                        ResourceKey<MobEffect> effectKey = mobEffectRegistry.getResourceKey(effect.effect)
                                .orElseThrow(() -> new IllegalStateException("Effect not registered: " + effect.effect));

                        // Use getOrThrow, returns Holder<MobEffect>
                        Holder<MobEffect> holder = mobEffectRegistry.getOrThrow(effectKey);
                        player.addEffect(new MobEffectInstance(holder, effect.duration, effect.amplifier));
                    }

                    // Secondary effect
                    if (effect.secondaryEffect != null && effect.secondaryEffectDuration > 0) {
                        player.addEffect(new MobEffectInstance(
                                effect.secondaryEffect,
                                effect.secondaryEffectDuration,
                                effect.secondaryEffectAmplifier
                        ));
                    }

                    iterator.remove();
                }
            }

            if (queue.isEmpty()) {
                queuedEffects.remove(player.getUUID());
            }
        }
    }
}