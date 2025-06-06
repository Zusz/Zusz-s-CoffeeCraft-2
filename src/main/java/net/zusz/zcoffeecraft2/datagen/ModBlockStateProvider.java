package net.zusz.zcoffeecraft2.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.block.CoffeeBushBlock;
import net.zusz.zcoffeecraft2.block.ModBlocks;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, ZCoffeeCraft2.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        cubeBottomTopBlockWithItem(ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK);
        makeBush(((BushBlock) ModBlocks.ARABICA_COFFEE_BUSH.get()), "arabica_coffee_bush_stage", "arabica_coffee_Bush_stage");
    }

    public void makeBush(BushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, "arabica_coffee_bush_stage", "arabica_coffee_bush_stage");

        getVariantBuilder(block).forAllStates(function);
    }
    private ConfiguredModel[] states(BlockState state, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(CoffeeBushBlock.AGE),
                ResourceLocation.fromNamespaceAndPath(ZCoffeeCraft2.MOD_ID, "block/" + textureName + state.getValue(CoffeeBushBlock.AGE))).renderType("cutout"));

        return models;
    }
    private void cubeAllBlockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
    private void cubeBottomTopBlockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), models().cubeBottomTop(
                name(deferredBlock),
                blockTexture(deferredBlock, "_side"),
                blockTexture(deferredBlock, "_bottom"),
                blockTexture(deferredBlock, "_top")
                ));
    }

    private String name(DeferredBlock<?> block) {
        return block.getId().getPath();
    }
    private ResourceLocation blockTexture(DeferredBlock<?> block, String suffix) {
        return modLoc("block/" + name(block) + suffix);
    }
}
