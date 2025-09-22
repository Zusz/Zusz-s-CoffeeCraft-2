package net.zusz.zcoffeecraft2.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.block.custom.ArabicaCoffeeBushBlock;
import net.zusz.zcoffeecraft2.block.ModBlocks;
import net.zusz.zcoffeecraft2.block.custom.LibericaCoffeeBushBlock;
import net.zusz.zcoffeecraft2.block.custom.RobustaCoffeeBushBlock;

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
        coffeeBeanSack(ModBlocks.RAW_ROBUSTA_COFFEE_BEAN_SACK, "raw_robusta_coffee_bean_sack_top");
        coffeeBeanSack(ModBlocks.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN_SACK, "light_roasted_robusta_coffee_bean_sack_top");
        coffeeBeanSack(ModBlocks.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN_SACK, "medium_roasted_robusta_coffee_bean_sack_top");
        coffeeBeanSack(ModBlocks.DARK_ROASTED_ROBUSTA_COFFEE_BEAN_SACK, "dark_roasted_robusta_coffee_bean_sack_top");
        coffeeBeanSack(ModBlocks.RAW_LIBERICA_COFFEE_BEAN_SACK, "raw_liberica_coffee_bean_sack_top");
        coffeeBeanSack(ModBlocks.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN_SACK, "light_roasted_liberica_coffee_bean_sack_top");
        coffeeBeanSack(ModBlocks.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN_SACK, "medium_roasted_liberica_coffee_bean_sack_top");
        coffeeBeanSack(ModBlocks.DARK_ROASTED_LIBERICA_COFFEE_BEAN_SACK, "dark_roasted_liberica_coffee_bean_sack_top");

        makeBush(((BushBlock) ModBlocks.ARABICA_COFFEE_BUSH.get()), ArabicaCoffeeBushBlock.AGE, "arabica_coffee_bush_stage", "arabica_coffee_bush_stage");
        makeBush(((BushBlock) ModBlocks.ROBUSTA_COFFEE_BUSH.get()), RobustaCoffeeBushBlock.AGE, "robusta_coffee_bush_stage", "robusta_coffee_bush_stage");
        makeBush(((BushBlock) ModBlocks.LIBERICA_COFFEE_BUSH.get()), LibericaCoffeeBushBlock.AGE, "liberica_coffee_bush_stage", "liberica_coffee_bush_stage");

    }

    //not absolute, but magic. please dont touch
    public void makeBush(BushBlock block, IntegerProperty ageProperty, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, ageProperty, modelName, textureName);
        getVariantBuilder(block).forAllStates(function);
    }
    private ConfiguredModel[] states(BlockState state, IntegerProperty ageProperty, String modelName, String textureName) {
        int age = state.getValue(ageProperty);
        return new ConfiguredModel[] {
                new ConfiguredModel(models().cross(
                        modelName + age,
                        modLoc("block/" + textureName + age)
                ).renderType("cutout"))
        };
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
