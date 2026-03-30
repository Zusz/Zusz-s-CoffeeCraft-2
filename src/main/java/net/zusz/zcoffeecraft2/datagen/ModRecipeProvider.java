package net.zusz.zcoffeecraft2.datagen;

import net.minecraft.core.Holder;
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
    public ModRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "Zusz's CoffeeCraft2 Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
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
        shapeless(RecipeCategory.MISC, ModItems.RAW_ARABICA_COFFEE_BEAN.get(), 1)
                .requires(ModItems.ARABICA_COFFEE_CHERRY)
                .unlockedBy("has_arabica_coffee_cherry", has(ModItems.ARABICA_COFFEE_CHERRY)).save(output, "raw_arabica_coffee_bean_from_cherry");

        shaped(RecipeCategory.MISC, ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.RAW_ARABICA_COFFEE_BEAN.get())
                .unlockedBy("has_raw_arabica_coffee_bean", has(ModItems.RAW_ARABICA_COFFEE_BEAN)).save(output);

        shapeless(RecipeCategory.MISC, ModItems.RAW_ARABICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_raw_arabica_coffee_bean_sack", has(ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK)).save(output, "raw_arabica_coffee_bean_from_sack");


        shaped(RecipeCategory.MISC, ModBlocks.LIGHT_ROASTED_ARABICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN.get())
                .unlockedBy("has_light_roasted_arabica_coffee_bean", has(ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN)).save(output);

        shapeless(RecipeCategory.MISC, ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.LIGHT_ROASTED_ARABICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_light_roasted_arabica_coffee_bean_sack", has(ModBlocks.LIGHT_ROASTED_ARABICA_COFFEE_BEAN_SACK)).save(output, "light_roasted_arabica_coffee_bean_from_sack");

        shaped(RecipeCategory.MISC, ModBlocks.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN.get())
                .unlockedBy("has_medium_roasted_arabica_coffee_bean", has(ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN)).save(output);

        shapeless(RecipeCategory.MISC, ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_medium_roasted_arabica_coffee_bean_sack", has(ModBlocks.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN_SACK)).save(output, "medium_roasted_arabica_coffee_bean_from_sack");

        shaped(RecipeCategory.MISC, ModBlocks.DARK_ROASTED_ARABICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN.get())
                .unlockedBy("has_dark_roasted_arabica_coffee_bean", has(ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN)).save(output);

        shapeless(RecipeCategory.MISC, ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.DARK_ROASTED_ARABICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_dark_roasted_arabica_coffee_bean_sack", has(ModBlocks.DARK_ROASTED_ARABICA_COFFEE_BEAN_SACK)).save(output, "dark_roasted_arabica_coffee_bean_from_sack");

        //Robusta Coffee Beans and Sacks
        shapeless(RecipeCategory.MISC, ModItems.RAW_ROBUSTA_COFFEE_BEAN.get(), 1)
                .requires(ModItems.ROBUSTA_COFFEE_CHERRY)
                .unlockedBy("has_robusta_coffee_cherry", has(ModItems.ROBUSTA_COFFEE_CHERRY)).save(output, "raw_robusta_coffee_bean_from_cherry");

        shaped(RecipeCategory.MISC, ModBlocks.RAW_ROBUSTA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.RAW_ROBUSTA_COFFEE_BEAN.get())
                .unlockedBy("has_raw_robusta_coffee_bean", has(ModItems.RAW_ROBUSTA_COFFEE_BEAN)).save(output);

        shapeless(RecipeCategory.MISC, ModItems.RAW_ROBUSTA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.RAW_ROBUSTA_COFFEE_BEAN_SACK)
                .unlockedBy("has_raw_robusta_coffee_bean_sack", has(ModBlocks.RAW_ROBUSTA_COFFEE_BEAN_SACK)).save(output, "raw_robusta_coffee_bean_from_sack");


        shaped(RecipeCategory.MISC, ModBlocks.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN.get())
                .unlockedBy("has_light_roasted_robusta_coffee_bean", has(ModItems.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN)).save(output);

        shapeless(RecipeCategory.MISC, ModItems.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN_SACK)
                .unlockedBy("has_light_roasted_robusta_coffee_bean_sack", has(ModBlocks.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN_SACK)).save(output, "light_roasted_robusta_coffee_bean_from_sack");

        shaped(RecipeCategory.MISC, ModBlocks.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN.get())
                .unlockedBy("has_medium_roasted_robusta_coffee_bean", has(ModItems.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN)).save(output);

        shapeless(RecipeCategory.MISC, ModItems.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN_SACK)
                .unlockedBy("has_medium_roasted_robusta_coffee_bean_sack", has(ModBlocks.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN_SACK)).save(output, "medium_roasted_robusta_coffee_bean_from_sack");

        shaped(RecipeCategory.MISC, ModBlocks.DARK_ROASTED_ROBUSTA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.DARK_ROASTED_ROBUSTA_COFFEE_BEAN.get())
                .unlockedBy("has_dark_roasted_robusta_coffee_bean", has(ModItems.DARK_ROASTED_ROBUSTA_COFFEE_BEAN)).save(output);

        shapeless(RecipeCategory.MISC, ModItems.DARK_ROASTED_ROBUSTA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.DARK_ROASTED_ROBUSTA_COFFEE_BEAN_SACK)
                .unlockedBy("has_dark_roasted_robusta_coffee_bean_sack", has(ModBlocks.DARK_ROASTED_ROBUSTA_COFFEE_BEAN_SACK)).save(output, "dark_roasted_robusta_coffee_bean_from_sack");

        //Liberica Coffee Beans and Sacks
        shapeless(RecipeCategory.MISC, ModItems.RAW_LIBERICA_COFFEE_BEAN.get(), 1)
                .requires(ModItems.LIBERICA_COFFEE_CHERRY)
                .unlockedBy("has_liberica_coffee_cherry", has(ModItems.LIBERICA_COFFEE_CHERRY)).save(output, "raw_liberica_coffee_bean_from_cherry");

        shaped(RecipeCategory.MISC, ModBlocks.RAW_LIBERICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.RAW_LIBERICA_COFFEE_BEAN.get())
                .unlockedBy("has_raw_liberica_coffee_bean", has(ModItems.RAW_LIBERICA_COFFEE_BEAN)).save(output);

        shapeless(RecipeCategory.MISC, ModItems.RAW_LIBERICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.RAW_LIBERICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_raw_liberica_coffee_bean_sack", has(ModBlocks.RAW_LIBERICA_COFFEE_BEAN_SACK)).save(output, "raw_liberica_coffee_bean_from_sack");

        shaped(RecipeCategory.MISC, ModBlocks.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN.get())
                .unlockedBy("has_light_roasted_liberica_coffee_bean", has(ModItems.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN)).save(output);

        shapeless(RecipeCategory.MISC, ModItems.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_light_roasted_liberica_coffee_bean_sack", has(ModBlocks.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN_SACK)).save(output, "light_roasted_liberica_coffee_bean_from_sack");

        shaped(RecipeCategory.MISC, ModBlocks.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN.get())
                .unlockedBy("has_medium_roasted_liberica_coffee_bean", has(ModItems.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN)).save(output);

        shapeless(RecipeCategory.MISC, ModItems.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_medium_roasted_liberica_coffee_bean_sack", has(ModBlocks.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN_SACK)).save(output, "medium_roasted_liberica_coffee_bean_from_sack");

        shaped(RecipeCategory.MISC, ModBlocks.DARK_ROASTED_LIBERICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.DARK_ROASTED_LIBERICA_COFFEE_BEAN.get())
                .unlockedBy("has_dark_roasted_liberica_coffee_bean", has(ModItems.DARK_ROASTED_LIBERICA_COFFEE_BEAN)).save(output);

        shapeless(RecipeCategory.MISC, ModItems.DARK_ROASTED_LIBERICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.DARK_ROASTED_LIBERICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_dark_roasted_liberica_coffee_bean_sack", has(ModBlocks.DARK_ROASTED_LIBERICA_COFFEE_BEAN_SACK)).save(output, "dark_roasted_liberica_coffee_bean_from_sack");

        //Excelsa Coffee Beans and Sacks
        shapeless(RecipeCategory.MISC, ModItems.RAW_EXCELSA_COFFEE_BEAN.get(), 1)
                .requires(ModItems.EXCELSA_COFFEE_CHERRY)
                .unlockedBy("has_excelsa_coffee_cherry", has(ModItems.EXCELSA_COFFEE_CHERRY)).save(output, "raw_excelsa_coffee_bean_from_cherry");

        shaped(RecipeCategory.MISC, ModBlocks.RAW_EXCELSA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.RAW_EXCELSA_COFFEE_BEAN.get())
                .unlockedBy("has_raw_excelsa_coffee_bean", has(ModItems.RAW_EXCELSA_COFFEE_BEAN)).save(output);

        shapeless(RecipeCategory.MISC, ModItems.RAW_EXCELSA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.RAW_EXCELSA_COFFEE_BEAN_SACK)
                .unlockedBy("has_raw_excelsa_coffee_bean_sack", has(ModBlocks.RAW_EXCELSA_COFFEE_BEAN_SACK)).save(output, "raw_excelsa_coffee_bean_from_sack");

        shaped(RecipeCategory.MISC, ModBlocks.LIGHT_ROASTED_EXCELSA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.LIGHT_ROASTED_EXCELSA_COFFEE_BEAN.get())
                .unlockedBy("has_light_roasted_excelsa_coffee_bean", has(ModItems.LIGHT_ROASTED_EXCELSA_COFFEE_BEAN)).save(output);

        shapeless(RecipeCategory.MISC, ModItems.LIGHT_ROASTED_EXCELSA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.LIGHT_ROASTED_EXCELSA_COFFEE_BEAN_SACK)
                .unlockedBy("has_light_roasted_excelsa_coffee_bean_sack", has(ModBlocks.LIGHT_ROASTED_EXCELSA_COFFEE_BEAN_SACK)).save(output, "light_roasted_excelsa_coffee_bean_from_sack");

        shaped(RecipeCategory.MISC, ModBlocks.MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN.get())
                .unlockedBy("has_medium_roasted_excelsa_coffee_bean", has(ModItems.MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN)).save(output);

        shapeless(RecipeCategory.MISC, ModItems.MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN_SACK)
                .unlockedBy("has_medium_roasted_excelsa_coffee_bean_sack", has(ModBlocks.MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN_SACK)).save(output, "medium_roasted_excelsa_coffee_bean_from_sack");

        shaped(RecipeCategory.MISC, ModBlocks.DARK_ROASTED_EXCELSA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.DARK_ROASTED_EXCELSA_COFFEE_BEAN.get())
                .unlockedBy("has_dark_roasted_excelsa_coffee_bean", has(ModItems.DARK_ROASTED_EXCELSA_COFFEE_BEAN)).save(output);

        shapeless(RecipeCategory.MISC, ModItems.DARK_ROASTED_EXCELSA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.DARK_ROASTED_EXCELSA_COFFEE_BEAN_SACK)
                .unlockedBy("has_dark_roasted_excelsa_coffee_bean_sack", has(ModBlocks.DARK_ROASTED_EXCELSA_COFFEE_BEAN_SACK)).save(output, "dark_roasted_excelsa_coffee_bean_from_sack");

        //Ground Coffees from Sacks
        shapeless(RecipeCategory.MISC, ModItems.LIGHT_ARABICA_GROUND_COFFEE.get(), 9)
                .requires(ModBlocks.LIGHT_ROASTED_ARABICA_GROUND_COFFEE_SACK, 1)
                .unlockedBy("has_light_roasted_arabica_ground_coffee_sack", has(ModBlocks.LIGHT_ROASTED_ARABICA_GROUND_COFFEE_SACK)).save(output, "light_arabica_ground_coffee");

        shapeless(RecipeCategory.MISC, ModItems.MEDIUM_ARABICA_GROUND_COFFEE.get(), 9)
                .requires(ModBlocks.MEDIUM_ROASTED_ARABICA_GROUND_COFFEE_SACK, 1)
                .unlockedBy("has_medium_roasted_arabica_ground_coffee_sack", has(ModBlocks.MEDIUM_ROASTED_ARABICA_GROUND_COFFEE_SACK)).save(output, "medium_arabica_ground_coffee");

        shapeless(RecipeCategory.MISC, ModItems.DARK_ARABICA_GROUND_COFFEE.get(), 9)
                .requires(ModBlocks.DARK_ROASTED_ARABICA_GROUND_COFFEE_SACK, 1)
                .unlockedBy("has_dark_roasted_arabica_ground_coffee_sack", has(ModBlocks.DARK_ROASTED_ARABICA_GROUND_COFFEE_SACK)).save(output, "dark_arabica_ground_coffee");


        shapeless(RecipeCategory.MISC, ModItems.LIGHT_ROBUSTA_GROUND_COFFEE.get(), 9)
                .requires(ModBlocks.LIGHT_ROASTED_ROBUSTA_GROUND_COFFEE_SACK, 1)
                .unlockedBy("has_light_roasted_robusta_ground_coffee_sack", has(ModBlocks.LIGHT_ROASTED_ROBUSTA_GROUND_COFFEE_SACK)).save(output, "light_robusta_ground_coffee");

        shapeless(RecipeCategory.MISC, ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE.get(), 9)
                .requires(ModBlocks.MEDIUM_ROASTED_ROBUSTA_GROUND_COFFEE_SACK, 1)
                .unlockedBy("has_medium_roasted_robusta_ground_coffee_sack", has(ModBlocks.MEDIUM_ROASTED_ROBUSTA_GROUND_COFFEE_SACK)).save(output, "medium_robusta_ground_coffee");

        shapeless(RecipeCategory.MISC, ModItems.DARK_ROBUSTA_GROUND_COFFEE.get(), 9)
                .requires(ModBlocks.DARK_ROASTED_ROBUSTA_GROUND_COFFEE_SACK, 1)
                .unlockedBy("has_dark_roasted_robusta_ground_coffee_sack", has(ModBlocks.DARK_ROASTED_ROBUSTA_GROUND_COFFEE_SACK)).save(output, "dark_robusta_ground_coffee");


        shapeless(RecipeCategory.MISC, ModItems.LIGHT_LIBERICA_GROUND_COFFEE.get(), 9)
                .requires(ModBlocks.LIGHT_ROASTED_LIBERICA_GROUND_COFFEE_SACK, 1)
                .unlockedBy("has_light_roasted_liberica_ground_coffee_sack", has(ModBlocks.LIGHT_ROASTED_LIBERICA_GROUND_COFFEE_SACK)).save(output, "light_liberica_ground_coffee");

        shapeless(RecipeCategory.MISC, ModItems.MEDIUM_LIBERICA_GROUND_COFFEE.get(), 9)
                .requires(ModBlocks.MEDIUM_ROASTED_LIBERICA_GROUND_COFFEE_SACK, 1)
                .unlockedBy("has_medium_roasted_liberica_ground_coffee_sack", has(ModBlocks.MEDIUM_ROASTED_LIBERICA_GROUND_COFFEE_SACK)).save(output, "medium_liberica_ground_coffee");

        shapeless(RecipeCategory.MISC, ModItems.DARK_LIBERICA_GROUND_COFFEE.get(), 9)
                .requires(ModBlocks.DARK_ROASTED_LIBERICA_GROUND_COFFEE_SACK, 1)
                .unlockedBy("has_dark_roasted_liberica_ground_coffee_sack", has(ModBlocks.DARK_ROASTED_LIBERICA_GROUND_COFFEE_SACK)).save(output, "dark_liberica_ground_coffee");


        shapeless(RecipeCategory.MISC, ModItems.LIGHT_EXCELSA_GROUND_COFFEE.get(), 9)
                .requires(ModBlocks.LIGHT_ROASTED_EXCELSA_GROUND_COFFEE_SACK, 1)
                .unlockedBy("has_light_roasted_excelsa_ground_coffee_sack", has(ModBlocks.LIGHT_ROASTED_EXCELSA_GROUND_COFFEE_SACK)).save(output, "light_excelsa_ground_coffee");

        shapeless(RecipeCategory.MISC, ModItems.MEDIUM_EXCELSA_GROUND_COFFEE.get(), 9)
                .requires(ModBlocks.MEDIUM_ROASTED_EXCELSA_GROUND_COFFEE_SACK, 1)
                .unlockedBy("has_medium_roasted_excelsa_ground_coffee_sack", has(ModBlocks.MEDIUM_ROASTED_EXCELSA_GROUND_COFFEE_SACK)).save(output, "medium_excelsa_ground_coffee");

        shapeless(RecipeCategory.MISC, ModItems.DARK_EXCELSA_GROUND_COFFEE.get(), 9)
                .requires(ModBlocks.DARK_ROASTED_EXCELSA_GROUND_COFFEE_SACK, 1)
                .unlockedBy("has_dark_roasted_excelsa_ground_coffee_sack", has(ModBlocks.DARK_ROASTED_EXCELSA_GROUND_COFFEE_SACK)).save(output, "dark_excelsa_ground_coffee");


        //Sacks from Ground Coffees
        shapeless(RecipeCategory.MISC, ModBlocks.LIGHT_ROASTED_ARABICA_GROUND_COFFEE_SACK.get(), 1)
                .requires(ModItems.LIGHT_ARABICA_GROUND_COFFEE, 9)
                .unlockedBy("has_light_arabica_ground_coffee", has(ModItems.LIGHT_ARABICA_GROUND_COFFEE)).save(output, "light_arabica_ground_coffee_sack");

        shapeless(RecipeCategory.MISC, ModBlocks.MEDIUM_ROASTED_ARABICA_GROUND_COFFEE_SACK.get(), 1)
                .requires(ModItems.MEDIUM_ARABICA_GROUND_COFFEE, 9)
                .unlockedBy("has_medium_arabica_ground_coffee", has(ModItems.MEDIUM_ARABICA_GROUND_COFFEE)).save(output, "medium_arabica_ground_coffee_sack");

        shapeless(RecipeCategory.MISC, ModBlocks.DARK_ROASTED_ARABICA_GROUND_COFFEE_SACK.get(), 1)
                .requires(ModItems.DARK_ARABICA_GROUND_COFFEE, 9)
                .unlockedBy("has_dark_arabica_ground_coffee", has(ModItems.DARK_ARABICA_GROUND_COFFEE)).save(output, "dark_arabica_ground_coffee_sack");


        shapeless(RecipeCategory.MISC, ModBlocks.LIGHT_ROASTED_ROBUSTA_GROUND_COFFEE_SACK.get(), 1)
                .requires(ModItems.LIGHT_ROBUSTA_GROUND_COFFEE, 9)
                .unlockedBy("has_light_robusta_ground_coffee", has(ModItems.LIGHT_ROBUSTA_GROUND_COFFEE)).save(output, "light_robusta_ground_coffee_sack");

        shapeless(RecipeCategory.MISC, ModBlocks.MEDIUM_ROASTED_ROBUSTA_GROUND_COFFEE_SACK.get(), 1)
                .requires(ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE, 9)
                .unlockedBy("has_medium_robusta_ground_coffee", has(ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE)).save(output, "medium_robusta_ground_coffee_sack");

        shapeless(RecipeCategory.MISC, ModBlocks.DARK_ROASTED_ROBUSTA_GROUND_COFFEE_SACK.get(), 1)
                .requires(ModItems.DARK_ROBUSTA_GROUND_COFFEE, 9)
                .unlockedBy("has_dark_robusta_ground_coffee", has(ModItems.DARK_ROBUSTA_GROUND_COFFEE)).save(output, "dark_robusta_ground_coffee_sack");


        shapeless(RecipeCategory.MISC, ModBlocks.LIGHT_ROASTED_LIBERICA_GROUND_COFFEE_SACK.get(), 1)
                .requires(ModItems.LIGHT_LIBERICA_GROUND_COFFEE, 9)
                .unlockedBy("has_light_liberica_ground_coffee", has(ModItems.LIGHT_LIBERICA_GROUND_COFFEE)).save(output, "light_liberica_ground_coffee_sack");

        shapeless(RecipeCategory.MISC, ModBlocks.MEDIUM_ROASTED_LIBERICA_GROUND_COFFEE_SACK.get(), 1)
                .requires(ModItems.MEDIUM_LIBERICA_GROUND_COFFEE, 9)
                .unlockedBy("has_medium_liberica_ground_coffee", has(ModItems.MEDIUM_LIBERICA_GROUND_COFFEE)).save(output, "medium_liberica_ground_coffee_sack");

        shapeless(RecipeCategory.MISC, ModBlocks.DARK_ROASTED_LIBERICA_GROUND_COFFEE_SACK.get(), 1)
                .requires(ModItems.DARK_LIBERICA_GROUND_COFFEE, 9)
                .unlockedBy("has_dark_liberica_ground_coffee", has(ModItems.DARK_LIBERICA_GROUND_COFFEE)).save(output, "dark_liberica_ground_coffee_sack");


        shapeless(RecipeCategory.MISC, ModBlocks.LIGHT_ROASTED_EXCELSA_GROUND_COFFEE_SACK.get(), 1)
                .requires(ModItems.LIGHT_EXCELSA_GROUND_COFFEE, 9)
                .unlockedBy("has_light_excelsa_ground_coffee", has(ModItems.LIGHT_EXCELSA_GROUND_COFFEE)).save(output, "light_excelsa_ground_coffee_sack");

        shapeless(RecipeCategory.MISC, ModBlocks.MEDIUM_ROASTED_EXCELSA_GROUND_COFFEE_SACK.get(), 1)
                .requires(ModItems.MEDIUM_EXCELSA_GROUND_COFFEE, 9)
                .unlockedBy("has_medium_excelsa_ground_coffee", has(ModItems.MEDIUM_EXCELSA_GROUND_COFFEE)).save(output, "medium_excelsa_ground_coffee_sack");

        shapeless(RecipeCategory.MISC, ModBlocks.DARK_ROASTED_EXCELSA_GROUND_COFFEE_SACK.get(), 1)
                .requires(ModItems.DARK_EXCELSA_GROUND_COFFEE, 9)
                .unlockedBy("has_dark_excelsa_ground_coffee", has(ModItems.DARK_EXCELSA_GROUND_COFFEE)).save(output, "dark_excelsa_ground_coffee_sack");

        //Coffee Cherry Sacks
        shapeless(RecipeCategory.MISC, ModBlocks.ARABICA_COFFEE_CHERRY_SACK.get(), 1)
                .requires(ModItems.ARABICA_COFFEE_CHERRY, 9)
                .unlockedBy("has_arabica_coffee_cherry", has(ModItems.ARABICA_COFFEE_CHERRY)).save(output, "arabica_coffee_cherry_sack");
        shapeless(RecipeCategory.MISC, ModItems.ARABICA_COFFEE_CHERRY.get(), 9)
                .requires(ModBlocks.ARABICA_COFFEE_CHERRY_SACK, 1)
                .unlockedBy("has_arabica_coffee_cherry_sack", has(ModBlocks.ARABICA_COFFEE_CHERRY_SACK)).save(output, "arabica_coffee_cherry");

        shapeless(RecipeCategory.MISC, ModBlocks.ROBUSTA_COFFEE_CHERRY_SACK.get(), 1)
                .requires(ModItems.ROBUSTA_COFFEE_CHERRY, 9)
                .unlockedBy("has_robusta_coffee_cherry", has(ModItems.ROBUSTA_COFFEE_CHERRY)).save(output, "robusta_coffee_cherry_sack");
        shapeless(RecipeCategory.MISC, ModItems.ROBUSTA_COFFEE_CHERRY.get(), 9)
                .requires(ModBlocks.ROBUSTA_COFFEE_CHERRY_SACK, 1)
                .unlockedBy("has_robusta_coffee_cherry_sack", has(ModBlocks.ROBUSTA_COFFEE_CHERRY_SACK)).save(output, "robusta_coffee_cherry");

        shapeless(RecipeCategory.MISC, ModBlocks.LIBERICA_COFFEE_CHERRY_SACK.get(), 1)
                .requires(ModItems.LIBERICA_COFFEE_CHERRY, 9)
                .unlockedBy("has_liberica_coffee_cherry", has(ModItems.LIBERICA_COFFEE_CHERRY)).save(output, "liberica_coffee_cherry_sack");
        shapeless(RecipeCategory.MISC, ModItems.LIBERICA_COFFEE_CHERRY.get(), 9)
                .requires(ModBlocks.LIBERICA_COFFEE_CHERRY_SACK, 1)
                .unlockedBy("has_liberica_coffee_cherry_sack", has(ModBlocks.LIBERICA_COFFEE_CHERRY_SACK)).save(output, "liberica_coffee_cherry");

        shapeless(RecipeCategory.MISC, ModBlocks.EXCELSA_COFFEE_CHERRY_SACK.get(), 1)
                .requires(ModItems.EXCELSA_COFFEE_CHERRY, 9)
                .unlockedBy("has_excelsa_coffee_cherry", has(ModItems.EXCELSA_COFFEE_CHERRY)).save(output, "excelsa_coffee_cherry_sack");
        shapeless(RecipeCategory.MISC, ModItems.EXCELSA_COFFEE_CHERRY.get(), 9)
                .requires(ModBlocks.EXCELSA_COFFEE_CHERRY_SACK, 1)
                .unlockedBy("has_excelsa_coffee_cherry_sack", has(ModBlocks.EXCELSA_COFFEE_CHERRY_SACK)).save(output, "excelsa_coffee_cherry");



        //Whisk
        shaped(RecipeCategory.MISC, ModItems.WHISK.get())
                .pattern(" I ")
                .pattern("I I")
                .pattern(" S ")
                .define('I', Items.IRON_NUGGET)
                .define('S', Items.STICK)
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .save(output);

        //Milk Foam
        shapeless(RecipeCategory.MISC, ModItems.MILK_FOAM.get(), 1)
                .requires(ModItems.STEAMED_MILK, 1)
                .requires(ModItems.WHISK, 1)
                .unlockedBy("has_whisk", has(ModItems.WHISK)).save(output, "milk_foam");

        //Whipped Cream
        shapeless(RecipeCategory.MISC, ModItems.WHIPPED_CREAM.get(), 2)
                .requires(Items.MILK_BUCKET, 1)
                .requires(ModItems.WHISK, 1)
                .requires(Items.SUGAR, 1)
                .unlockedBy("has_whisk", has(ModItems.WHISK)).save(output, "whipped_cream");

        //Coffee Cup
        shaped(RecipeCategory.MISC, ModItems.COFFEE_CUP.get())
                .pattern("   ")
                .pattern("G G")
                .pattern(" G ")
                .define('G', Items.GLASS_BOTTLE)
                .unlockedBy("has_iron_nugget", has(Items.GLASS_BOTTLE))
                .save(output);

        //Coffee Machine
        shaped(RecipeCategory.MISC, ModBlocks.COFFEE_MACHINE.get())
                .pattern("IRI")
                .pattern(" C ")
                .pattern("III")
                .define('C', ModItems.COFFEE_CUP)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_coffee_cup", has(ModItems.COFFEE_CUP))
                .save(output);

        //Coffee Grinder
        shaped(RecipeCategory.MISC, ModBlocks.COFFEE_GRINDER.get())
                .pattern("IRI")
                .pattern(" G ")
                .pattern("III")
                .define('G', Items.GLASS_BOTTLE)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_coffee_cup", has(ModItems.COFFEE_CUP))
                .save(output);


        //Guide book
        shapeless(RecipeCategory.MISC, ModItems.COFFEE_GUIDE.get(), 1)
                .requires(Items.BOOK, 1)
                .requires(ModItems.COFFEE_CUP, 1)
                .unlockedBy("has_coffee_cup", has(ModItems.COFFEE_CUP)).save(output, "coffee_guide");



        //Smelting
        oreSmelting(RAW_ARABICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.LIGHT_ROASTED_ARABICA_COFFEE_BEAN.get(), 0.25f, 200, "arabica");
        oreSmelting(LIGHT_ROASTED_ARABICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.MEDIUM_ROASTED_ARABICA_COFFEE_BEAN.get(), 0.25f, 200, "arabica");
        oreSmelting(MEDIUM_ROASTED_ARABICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.DARK_ROASTED_ARABICA_COFFEE_BEAN.get(), 0.25f, 200, "arabica");

        oreSmelting(RAW_ROBUSTA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN.get(), 0.25f, 200, "robusta");
        oreSmelting(LIGHT_ROASTED_ROBUSTA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN.get(), 0.25f, 200, "robusta");
        oreSmelting(MEDIUM_ROASTED_ROBUSTA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.DARK_ROASTED_ROBUSTA_COFFEE_BEAN.get(), 0.25f, 200, "robusta");

        oreSmelting(RAW_LIBERICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.LIGHT_ROASTED_LIBERICA_COFFEE_BEAN.get(), 0.25f, 200, "liberica");
        oreSmelting(LIGHT_ROASTED_LIBERICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN.get(), 0.25f, 200, "liberica");
        oreSmelting(MEDIUM_ROASTED_LIBERICA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.DARK_ROASTED_LIBERICA_COFFEE_BEAN.get(), 0.25f, 200, "liberica");

        oreSmelting(RAW_EXCELSA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.LIGHT_ROASTED_EXCELSA_COFFEE_BEAN.get(), 0.25f, 200, "excelsa");
        oreSmelting(LIGHT_ROASTED_EXCELSA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN.get(), 0.25f, 200, "excelsa");
        oreSmelting(MEDIUM_ROASTED_EXCELSA_COFFEE_BEAN_SMELT, RecipeCategory.MISC, ModItems.DARK_ROASTED_EXCELSA_COFFEE_BEAN.get(), 0.25f, 200, "excelsa");

        oreSmelting(MILK_BUCKET_SMELT, RecipeCategory.MISC, ModItems.STEAMED_MILK.get(), 0.25f, 2400, "steamed_milk");
    }
}
