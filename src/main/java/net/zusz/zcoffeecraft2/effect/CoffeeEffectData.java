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

    // Queue a new coffee effect for a player
    public static void addEffect(ServerPlayer player, CoffeeEffectInstance instance) {
        queuedEffects
                .computeIfAbsent(player.getUUID(), uuid -> new ArrayList<>())
                .add(instance);
    }

    // Tick queued effects every server tick
    public static void tick(ServerLevel level) {
        var mobEffectRegistry = level.registryAccess().registryOrThrow(Registries.MOB_EFFECT);

        for (ServerPlayer player : level.players()) {
            List<CoffeeEffectInstance> queue = queuedEffects.get(player.getUUID());
            if (queue == null || queue.isEmpty()) continue;

            Iterator<CoffeeEffectInstance> iterator = queue.iterator();
            while (iterator.hasNext()) {
                CoffeeEffectInstance effect = iterator.next();
                effect.ticksUntilApply--;

                if (effect.ticksUntilApply <= 0) {
                    // Apply primary effect safely
                    if (effect.effect != null) {
                        Optional<ResourceKey<MobEffect>> keyOpt = mobEffectRegistry.getResourceKey(effect.effect);
                        if (keyOpt.isEmpty()) {
                            throw new IllegalStateException("Effect not registered: " + effect.effect);
                        }
                        ResourceKey<MobEffect> effectKey = keyOpt.get();
                        Holder<MobEffect> effectHolder = mobEffectRegistry.getHolder(effectKey)
                                .orElseThrow(() -> new IllegalStateException("Holder not found for effect key: " + effectKey));

                        player.addEffect(new MobEffectInstance(effectHolder, effect.duration, effect.amplifier));
                    }

                    // Apply secondary effect safely
                    if (effect.secondaryEffect != null && effect.secondaryEffectDuration > 0) {
                        player.addEffect(new MobEffectInstance(
                                effect.secondaryEffect,
                                effect.secondaryEffectDuration,
                                effect.secondaryEffectAmplifier
                        ));
                    }

                    iterator.remove(); // remove from queue after applying
                }
            }

            // Clean up empty queues
            if (queue.isEmpty()) {
                queuedEffects.remove(player.getUUID());
            }
        }
    }
}