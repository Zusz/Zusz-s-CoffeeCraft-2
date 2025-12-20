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
        List<ItemLike> RAW_ROBUSTA_COFFEE_BEAN_SMELT = List.of(ModItems.RAW_ROBUSTA_COFFEE_BEAN);
        List<ItemLike> LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN_SMELT = List.of(ModItems.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN);
        List<ItemLike> MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN_SMELT = List.of(ModItems.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN);
        List<ItemLike> RAW_LIBERICA_COFFEE_BEAN_SMELT = List.of(ModItems.RAW_LIBERICA_COFFEE_BEAN);
        List<ItemLike> LIGHT_ROASTED_LIBERICA_COFFEE_BEAN_SMELT = List.of(ModItems.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN);
        List<ItemLike> MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN_SMELT = List.of(ModItems.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN);
        List<ItemLike> RAW_EXCELSA_COFFEE_BEAN_SMELT = List.of(ModItems.RAW_EXCELSA_COFFEE_BEAN);
        List<ItemLike> LIGHT_ROASTED_EXCELSA_COFFEE_BEAN_SMELT = List.of(ModItems.LIGHT_ROASTED_EXCELSA_COFFEE_BEAN);
        List<ItemLike> MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN_SMELT = List.of(ModItems.MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN);

        List<ItemLike> MILK_BUCKET_SMELT = List.of(Items.MILK_BUCKET);


        //Arabica Coffee Beans and Sacks
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_ARABICA_COFFEE_BEAN.get(), 1)
                .requires(ModItems.ARABICA_COFFEE_CHERRY)
                .unlockedBy("has_arabica_coffee_cherry", has(ModItems.ARABICA_COFFEE_CHERRY)).save(recipeOutput, "raw_arabica_coffee_bean_from_cherry");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.RAW_ARABICA_COFFEE_BEAN.get())
                .unlockedBy("has_raw_arabica_coffee_bean", has(ModItems.RAW_ARABICA_COFFEE_BEAN)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_ARABICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_raw_arabica_coffee_bean_sack", has(ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK)).save(recipeOutput, "raw_arabica_coffee_bean_from_sack");


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

        //Robusta Coffee Beans and Sacks
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_ROBUSTA_COFFEE_BEAN.get(), 1)
                .requires(ModItems.ROBUSTA_COFFEE_CHERRY)
                .unlockedBy("has_robusta_coffee_cherry", has(ModItems.ROBUSTA_COFFEE_CHERRY)).save(recipeOutput, "raw_robusta_coffee_bean_from_cherry");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_ROBUSTA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.RAW_ROBUSTA_COFFEE_BEAN.get())
                .unlockedBy("has_raw_robusta_coffee_bean", has(ModItems.RAW_ROBUSTA_COFFEE_BEAN)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_ROBUSTA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.RAW_ROBUSTA_COFFEE_BEAN_SACK)
                .unlockedBy("has_raw_robusta_coffee_bean_sack", has(ModBlocks.RAW_ROBUSTA_COFFEE_BEAN_SACK)).save(recipeOutput, "raw_robusta_coffee_bean_from_sack");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN.get())
                .unlockedBy("has_light_roasted_robusta_coffee_bean", has(ModItems.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN_SACK)
                .unlockedBy("has_light_roasted_robusta_coffee_bean_sack", has(ModBlocks.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN_SACK)).save(recipeOutput, "light_roasted_robusta_coffee_bean_from_sack");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN.get())
                .unlockedBy("has_medium_roasted_robusta_coffee_bean", has(ModItems.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN_SACK)
                .unlockedBy("has_medium_roasted_robusta_coffee_bean_sack", has(ModBlocks.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN_SACK)).save(recipeOutput, "medium_roasted_robusta_coffee_bean_from_sack");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DARK_ROASTED_ROBUSTA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.DARK_ROASTED_ROBUSTA_COFFEE_BEAN.get())
                .unlockedBy("has_dark_roasted_robusta_coffee_bean", has(ModItems.DARK_ROASTED_ROBUSTA_COFFEE_BEAN)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DARK_ROASTED_ROBUSTA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.DARK_ROASTED_ROBUSTA_COFFEE_BEAN_SACK)
                .unlockedBy("has_dark_roasted_robusta_coffee_bean_sack", has(ModBlocks.DARK_ROASTED_ROBUSTA_COFFEE_BEAN_SACK)).save(recipeOutput, "dark_roasted_robusta_coffee_bean_from_sack");

        //Liberica Coffee Beans and Sacks
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_LIBERICA_COFFEE_BEAN.get(), 1)
                .requires(ModItems.LIBERICA_COFFEE_CHERRY)
                .unlockedBy("has_liberica_coffee_cherry", has(ModItems.LIBERICA_COFFEE_CHERRY)).save(recipeOutput, "raw_liberica_coffee_bean_from_cherry");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_LIBERICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.RAW_LIBERICA_COFFEE_BEAN.get())
                .unlockedBy("has_raw_liberica_coffee_bean", has(ModItems.RAW_LIBERICA_COFFEE_BEAN)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_LIBERICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.RAW_LIBERICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_raw_liberica_coffee_bean_sack", has(ModBlocks.RAW_LIBERICA_COFFEE_BEAN_SACK)).save(recipeOutput, "raw_liberica_coffee_bean_from_sack");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN.get())
                .unlockedBy("has_light_roasted_liberica_coffee_bean", has(ModItems.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_light_roasted_liberica_coffee_bean_sack", has(ModBlocks.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN_SACK)).save(recipeOutput, "light_roasted_liberica_coffee_bean_from_sack");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN.get())
                .unlockedBy("has_medium_roasted_liberica_coffee_bean", has(ModItems.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_medium_roasted_liberica_coffee_bean_sack", has(ModBlocks.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN_SACK)).save(recipeOutput, "medium_roasted_liberica_coffee_bean_from_sack");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DARK_ROASTED_LIBERICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.DARK_ROASTED_LIBERICA_COFFEE_BEAN.get())
                .unlockedBy("has_dark_roasted_liberica_coffee_bean", has(ModItems.DARK_ROASTED_LIBERICA_COFFEE_BEAN)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DARK_ROASTED_LIBERICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.DARK_ROASTED_LIBERICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_dark_roasted_liberica_coffee_bean_sack", has(ModBlocks.DARK_ROASTED_LIBERICA_COFFEE_BEAN_SACK)).save(recipeOutput, "dark_roasted_liberica_coffee_bean_from_sack");

        //Excelsa Coffee Beans and Sacks
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_EXCELSA_COFFEE_BEAN.get(), 1)
                .requires(ModItems.EXCELSA_COFFEE_CHERRY)
                .unlockedBy("has_excelsa_coffee_cherry", has(ModItems.EXCELSA_COFFEE_CHERRY)).save(recipeOutput, "raw_excelsa_coffee_bean_from_cherry");

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


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LIGHT_ROBUSTA_GROUND_COFFEE.get(), 1)
                .requires(ModItems.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN, 6)
                .unlockedBy("has_light_roasted_robusta_coffee_bean", has(ModItems.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN)).save(recipeOutput, "light_robusta_ground_coffee");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE.get(), 1)
                .requires(ModItems.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN, 6)
                .unlockedBy("has_medium_roasted_robusta_coffee_bean", has(ModItems.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN)).save(recipeOutput, "medium_robusta_ground_coffee");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DARK_ROBUSTA_GROUND_COFFEE.get(), 1)
                .requires(ModItems.DARK_ROASTED_ROBUSTA_COFFEE_BEAN, 6)
                .unlockedBy("has_dark_roasted_robusta_coffee_bean", has(ModItems.DARK_ROASTED_ROBUSTA_COFFEE_BEAN)).save(recipeOutput, "dark_robusta_ground_coffee");


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LIGHT_LIBERICA_GROUND_COFFEE.get(), 1)
                .requires(ModItems.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN, 6)
                .unlockedBy("has_light_roasted_liberica_coffee_bean", has(ModItems.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN)).save(recipeOutput, "light_liberica_ground_coffee");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MEDIUM_LIBERICA_GROUND_COFFEE.get(), 1)
                .requires(ModItems.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN, 6)
                .unlockedBy("has_medium_roasted_liberica_coffee_bean", has(ModItems.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN)).save(recipeOutput, "medium_liberica_ground_coffee");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DARK_LIBERICA_GROUND_COFFEE.get(), 1)
                .requires(ModItems.DARK_ROASTED_LIBERICA_COFFEE_BEAN, 6)
                .unlockedBy("has_dark_roasted_liberica_coffee_bean", has(ModItems.DARK_ROASTED_LIBERICA_COFFEE_BEAN)).save(recipeOutput, "dark_liberica_ground_coffee");


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LIGHT_EXCELSA_GROUND_COFFEE.get(), 1)
                .requires(ModItems.LIGHT_ROASTED_EXCELSA_COFFEE_BEAN, 6)
                .unlockedBy("has_light_roasted_excelsa_coffee_bean", has(ModItems.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN)).save(recipeOutput, "light_excelsa_ground_coffee");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MEDIUM_EXCELSA_GROUND_COFFEE.get(), 1)
                .requires(ModItems.MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN, 6)
                .unlockedBy("has_medium_roasted_excelsa_coffee_bean", has(ModItems.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN)).save(recipeOutput, "medium_excelsa_ground_coffee");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DARK_EXCELSA_GROUND_COFFEE.get(), 1)
                .requires(ModItems.DARK_ROASTED_EXCELSA_COFFEE_BEAN, 6)
                .unlockedBy("has_dark_roasted_excelsa_coffee_bean", has(ModItems.DARK_ROASTED_LIBERICA_COFFEE_BEAN)).save(recipeOutput, "dark_excelsa_ground_coffee");

        //Whisk
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WHISK.get())
                .pattern(" I ")
                .pattern("I I")
                .pattern(" S ")
                .define('I', Items.IRON_NUGGET)
                .define('S', Items.STICK)
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .save(recipeOutput);

        //Milk Foam
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MILK_FOAM.get(), 1)
                .requires(ModItems.STEAMED_MILK, 1)
                .requires(ModItems.WHISK, 1)
                .unlockedBy("has_whisk", has(ModItems.WHISK)).save(recipeOutput, "milk_foam");

        //Whipped Cream
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.WHIPPED_CREAM.get(), 2)
                .requires(Items.MILK_BUCKET, 1)
                .requires(ModItems.WHISK, 1)
                .requires(Items.SUGAR, 1)
                .unlockedBy("has_whisk", has(ModItems.WHISK)).save(recipeOutput, "whipped_cream");

        //Coffee Cup
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COFFEE_CUP.get())
                .pattern("   ")
                .pattern("G G")
                .pattern(" G ")
                .define('G', Items.GLASS_BOTTLE)
                .unlockedBy("has_iron_nugget", has(Items.GLASS_BOTTLE))
                .save(recipeOutput);

        //Coffee Machine
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COFFEE_MACHINE.get())
                .pattern("IRI")
                .pattern(" C ")
                .pattern("III")
                .define('C', ModItems.COFFEE_CUP)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_coffee_cup", has(ModItems.COFFEE_CUP))
                .save(recipeOutput);

        //Guide book
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.COFFEE_GUIDE.get(), 1)
                .requires(Items.BOOK, 1)
                .requires(ModItems.COFFEE_CUP, 1)
                .unlockedBy("has_coffee_cup", has(ModItems.COFFEE_CUP)).save(recipeOutput, "coffee_guide");



        //Smelting
        oreSmelting(recipeOutput, RAW_ARABICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN.get(), 0.25f, 200, "coffee");
        oreSmelting(recipeOutput, LIGHT_ROASTED_ARABICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN.get(), 0.25f, 200, "coffee");
        oreSmelting(recipeOutput, MEDIUM_ROASTED_ARABICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN.get(), 0.25f, 200, "coffee");

        oreSmelting(recipeOutput, RAW_ROBUSTA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN.get(), 0.25f, 200, "coffee");
        oreSmelting(recipeOutput, LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN.get(), 0.25f, 200, "coffee");
        oreSmelting(recipeOutput, MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.DARK_ROASTED_ROBUSTA_COFFEE_BEAN.get(), 0.25f, 200, "coffee");

        oreSmelting(recipeOutput, RAW_LIBERICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN.get(), 0.25f, 200, "coffee");
        oreSmelting(recipeOutput, LIGHT_ROASTED_LIBERICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN.get(), 0.25f, 200, "coffee");
        oreSmelting(recipeOutput, MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.DARK_ROASTED_LIBERICA_COFFEE_BEAN.get(), 0.25f, 200, "coffee");

        oreSmelting(recipeOutput, RAW_EXCELSA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.LIGHT_ROASTED_EXCELSA_COFFEE_BEAN.get(), 0.25f, 200, "coffee");
        oreSmelting(recipeOutput, LIGHT_ROASTED_EXCELSA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN.get(), 0.25f, 200, "coffee");
        oreSmelting(recipeOutput, MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.DARK_ROASTED_EXCELSA_COFFEE_BEAN.get(), 0.25f, 200, "coffee");

        oreSmelting(recipeOutput, MILK_BUCKET_SMELT, RecipeCategory.MISC, ModItems.STEAMED_MILK.get(), 0.25f, 2400, "coffee");
    }
}
