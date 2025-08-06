package net.zusz.zcoffeecraft2.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.block.custom.ArabicaCoffeeBushBlock;
import net.zusz.zcoffeecraft2.block.ModBlocks;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, ZCoffeeCraft2.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        coffeeBeanSack(ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK, "raw_arabica_coffee_bean_sack_top");
        coffeeBeanSack(ModBlocks.LIGHT_ROASTED_ARABICA_COFFEE_BEAN_SACK, "light_roasted_arabica_coffee_bean_sack_top");
        coffeeBeanSack(ModBlocks.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN_SACK, "medium_roasted_arabica_coffee_bean_sack_top");
        coffeeBeanSack(ModBlocks.DARK_ROASTED_ARABICA_COFFEE_BEAN_SACK, "dark_roasted_arabica_coffee_bean_sack_top");

        makeBush(((BushBlock) ModBlocks.ARABICA_COFFEE_BUSH.get()), "arabica_coffee_bush_stage", "arabica_coffee_bush_stage");
        makeBush(((BushBlock) ModBlocks.ROBUSTA_COFFEE_BUSH.get()), "robusta_coffee_bush_stage", "arabica_coffee_bush_stage");

    }

    public void makeBush(BushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }
    private ConfiguredModel[] states(BlockState state, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(ArabicaCoffeeBushBlock.AGE),
                ResourceLocation.fromNamespaceAndPath(ZCoffeeCraft2.MOD_ID, "block/" + textureName + state.getValue(ArabicaCoffeeBushBlock.AGE))).renderType("cutout"));

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

    //this is just a cubebottomtop for coffee bean sacks, because the sides and bottom stay the same
    private void coffeeBeanSack(DeferredBlock<?> deferredBlock, String topTextureName) {
        simpleBlockWithItem(deferredBlock.get(), models().cubeBottomTop(
                name(deferredBlock),
                modLoc("block/coffee_bean_sack_side"),   // shared side texture
                modLoc("block/coffee_bean_sack_bottom"), // shared bottom texture
                modLoc("block/" + topTextureName)        // unique top texture
        ));
    }


    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private String name(DeferredBlock<?> block) {
        return block.getId().getPath();
    }
    private ResourceLocation blockTexture(DeferredBlock<?> block, String suffix) {
        return modLoc("block/" + name(block) + suffix);
    }
}
