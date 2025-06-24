package net.zusz.zcoffeecraft2.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.zusz.zcoffeecraft2.block.ModBlocks;
import net.zusz.zcoffeecraft2.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> RAW_ARABICA_COFFEE_BEAN_SMELT = List.of(ModItems.RAW_ARABICA_COFFEE_BEAN);
        List<ItemLike> LIGHT_ROASTED_ARABICA_COFFEE_BEAN_SMELT = List.of(ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN);
        List<ItemLike> MEDIUM_ROASTED_ARABICA_COFFEE_BEAN_SMELT = List.of(ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.RAW_ARABICA_COFFEE_BEAN.get())
                .unlockedBy("has_raw_arabica_coffee_bean", has(ModItems.RAW_ARABICA_COFFEE_BEAN)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_ARABICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_raw_arabica_coffee_bean_sack", has(ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK)).save(recipeOutput, "arabica_coffee_bean_from_sack");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_ARABICA_COFFEE_BEAN.get(), 1)
                .requires(ModItems.ARABICA_COFFEE_CHERRY)
                .unlockedBy("has_arabica_coffee_cherry", has(ModItems.ARABICA_COFFEE_CHERRY)).save(recipeOutput, "arabica_coffee_bean_from_cherry");

        //Uncomment for smelting
        oreSmelting(recipeOutput, RAW_ARABICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN.get(), 0.25f, 200, "coffee");
        oreSmelting(recipeOutput, LIGHT_ROASTED_ARABICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN.get(), 0.25f, 200, "coffee");
        oreSmelting(recipeOutput, MEDIUM_ROASTED_ARABICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.DARK_ROASTED_COFFEE_BEAN.get(), 0.25f, 200, "coffee");
    }
}
