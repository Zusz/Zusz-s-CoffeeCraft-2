package net.zusz.zcoffeecraft2.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.block.custom.ArabicaCoffeeBushBlock;
import net.zusz.zcoffeecraft2.block.ModBlocks;
import net.zusz.zcoffeecraft2.block.custom.LibericaCoffeeBushBlock;
import net.zusz.zcoffeecraft2.block.custom.RobustaCoffeeBushBlock;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> ARABICA_COFFEE_BUSH_KEY = registerKey("arabica_coffee_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROBUSTA_COFFEE_BUSH_KEY = registerKey("robusta_coffee_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LIBERICA_COFFEE_BUSH_KEY = registerKey("liberica_coffee_bush");
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        register(context, ARABICA_COFFEE_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ARABICA_COFFEE_BUSH.get()
                                .defaultBlockState().setValue(ArabicaCoffeeBushBlock.AGE, Integer.valueOf(6)))
                        ), List.of(Blocks.GRASS_BLOCK)
                )
        );
        register(context, ROBUSTA_COFFEE_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ROBUSTA_COFFEE_BUSH.get()
                                .defaultBlockState().setValue(RobustaCoffeeBushBlock.AGE, Integer.valueOf(6)))
                        ), List.of(Blocks.GRASS_BLOCK)
                )
        );
        register(context, LIBERICA_COFFEE_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.LIBERICA_COFFEE_BUSH.get()
                                .defaultBlockState().setValue(LibericaCoffeeBushBlock.AGE, Integer.valueOf(6)))
                        ), List.of(Blocks.GRASS_BLOCK)
                )
        );
    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(ZCoffeeCraft2.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}