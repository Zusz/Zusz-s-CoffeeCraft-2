package net.zusz.zcoffeecraft2.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, ZCoffeeCraft2.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        cubeBottomTopBlockWithItem(ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK);
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
