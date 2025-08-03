package net.zusz.zcoffeecraft2.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
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
        List<ItemLike> MILK_BUCKET_SMELT = List.of(Items.MILK_BUCKET);


        //Arabica Coffee Beans and Sacks
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.RAW_ARABICA_COFFEE_BEAN.get())
                .unlockedBy("has_raw_arabica_coffee_bean", has(ModItems.RAW_ARABICA_COFFEE_BEAN)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_ARABICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_raw_arabica_coffee_bean_sack", has(ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK)).save(recipeOutput, "raw_arabica_coffee_bean_from_sack");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_ARABICA_COFFEE_BEAN.get(), 1)
                .requires(ModItems.ARABICA_COFFEE_CHERRY)
                .unlockedBy("has_arabica_coffee_cherry", has(ModItems.ARABICA_COFFEE_CHERRY)).save(recipeOutput, "raw_arabica_coffee_bean_from_cherry");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LIGHT_ROASTED_ARABICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN.get())
                .unlockedBy("has_light_roasted_arabica_coffee_bean", has(ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.LIGHT_ROASTED_ARABICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_light_roasted_arabica_coffee_bean_sack", has(ModBlocks.LIGHT_ROASTED_ARABICA_COFFEE_BEAN_SACK)).save(recipeOutput, "light_roasted_arabica_coffee_bean_from_sack");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN.get())
                .unlockedBy("has_medium_roasted_arabica_coffee_bean", has(ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_medium_roasted_arabica_coffee_bean_sack", has(ModBlocks.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN_SACK)).save(recipeOutput, "medium_roasted_arabica_coffee_bean_from_sack");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DARK_ROASTED_ARABICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN.get())
                .unlockedBy("has_dark_roasted_arabica_coffee_bean", has(ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.DARK_ROASTED_ARABICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_dark_roasted_arabica_coffee_bean_sack", has(ModBlocks.DARK_ROASTED_ARABICA_COFFEE_BEAN_SACK)).save(recipeOutput, "dark_roasted_arabica_coffee_bean_from_sack");


        //Ground Coffees
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LIGHT_ARABICA_GROUND_COFFEE.get(), 1)
                .requires(ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN, 6)
                .unlockedBy("has_light_roasted_arabica_coffee_bean", has(ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN)).save(recipeOutput, "light_arabica_ground_coffee");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MEDIUM_ARABICA_GROUND_COFFEE.get(), 1)
                .requires(ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN, 6)
                .unlockedBy("has_medium_roasted_arabica_coffee_bean", has(ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN)).save(recipeOutput, "medium_arabica_ground_coffee");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DARK_ARABICA_GROUND_COFFEE.get(), 1)
                .requires(ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN, 6)
                .unlockedBy("has_dark_roasted_arabica_coffee_bean", has(ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN)).save(recipeOutput, "dark_arabica_ground_coffee");

        //Whisk
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WHISK.get())
                .pattern(" I ")
                .pattern("I I")
                .pattern(" S ")
                .define('I', Items.IRON_NUGGET)
                .define('S', Items.STICK)
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .save(recipeOutput);

        //Milk foam
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MILK_FOAM.get(), 1)
                .requires(ModItems.STEAMED_MILK, 1)
                .requires(ModItems.WHISK, 1)
                .unlockedBy("has_whisk", has(ModItems.WHISK)).save(recipeOutput, "milk_foam");

        //Smelting
        oreSmelting(recipeOutput, RAW_ARABICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN.get(), 0.25f, 200, "coffee");
        oreSmelting(recipeOutput, LIGHT_ROASTED_ARABICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN.get(), 0.25f, 200, "coffee");
        oreSmelting(recipeOutput, MEDIUM_ROASTED_ARABICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN.get(), 0.25f, 200, "coffee");
        oreSmelting(recipeOutput, MILK_BUCKET_SMELT, RecipeCategory.MISC, ModItems.STEAMED_MILK.get(), 0.25f, 2400, "coffee");
    }
}
