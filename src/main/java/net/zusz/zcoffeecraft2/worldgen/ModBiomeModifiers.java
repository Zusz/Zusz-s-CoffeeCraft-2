package net.zusz.zcoffeecraft2.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_ARABICA_COFFEE_BUSH = registerKey("add_arabica_coffee_bush");
    public static final ResourceKey<BiomeModifier> ADD_ROBUSTA_COFFEE_BUSH = registerKey("add_robusta_coffee_bush");
    public static final ResourceKey<BiomeModifier> ADD_LIBERICA_COFFEE_BUSH = registerKey("add_liberica_coffee_bush");
    public static final ResourceKey<BiomeModifier> ADD_EXCELSA_COFFEE_BUSH = registerKey("add_excelsa_coffee_bush");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_ARABICA_COFFEE_BUSH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.JUNGLE)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ARABICA_COFFEE_BUSH_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_ROBUSTA_COFFEE_BUSH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.TAIGA), biomes.getOrThrow(Biomes.OLD_GROWTH_PINE_TAIGA), biomes.getOrThrow(Biomes.OLD_GROWTH_SPRUCE_TAIGA)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ROBUSTA_COFFEE_BUSH_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_LIBERICA_COFFEE_BUSH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SAVANNA), biomes.getOrThrow(Biomes.WINDSWEPT_SAVANNA), biomes.getOrThrow(Biomes.SAVANNA_PLATEAU)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LIBERICA_COFFEE_BUSH_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_EXCELSA_COFFEE_BUSH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.JUNGLE), biomes.getOrThrow(Biomes.BAMBOO_JUNGLE), biomes.getOrThrow(Biomes.SPARSE_JUNGLE)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.EXCELSA_COFFEE_BUSH_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(ZCoffeeCraft2.MOD_ID, name));
    }
}