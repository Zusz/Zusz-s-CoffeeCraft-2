package net.zusz.zcoffeecraft2.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.block.custom.LibericaCoffeeBushBlock;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> ARABICA_COFFEE_BUSH_PLACED_KEY = registerKey("arabica_coffee_bush_placed");
    public static final ResourceKey<PlacedFeature> ROBUSTA_COFFEE_BUSH_PLACED_KEY = registerKey("robusta_coffee_bush_placed");
    public static final ResourceKey<PlacedFeature> LIBERICA_COFFEE_BUSH_PLACED_KEY = registerKey("liberica_coffee_bush_placed");
    public static final ResourceKey<PlacedFeature> EXCELSA_COFFEE_BUSH_PLACED_KEY = registerKey("excelsa_coffee_bush_placed");
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ARABICA_COFFEE_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ARABICA_COFFEE_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(48), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, ROBUSTA_COFFEE_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ROBUSTA_COFFEE_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(12), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, LIBERICA_COFFEE_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LIBERICA_COFFEE_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(12), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, EXCELSA_COFFEE_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.EXCELSA_COFFEE_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(ZCoffeeCraft2.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}