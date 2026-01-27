package net.zusz.zcoffeecraft2.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.item.ItemStack;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.block.ModBlocks;
import net.zusz.zcoffeecraft2.block.entity.ModBlockEntities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoffeeGrinderRecipeCategory implements IRecipeCategory<CoffeeGrinderRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(ZCoffeeCraft2.MOD_ID, "coffee_grinder");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(ZCoffeeCraft2.MOD_ID,
            "textures/gui/coffee_grinder/coffee_grinder_gui_without_inventory.png");

    public static final RecipeType<CoffeeGrinderRecipe> COFFEE_GRINDER_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, CoffeeGrinderRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public CoffeeGrinderRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack((ModBlocks.COFFEE_GRINDER)));
    }

    @Override
    public RecipeType<CoffeeGrinderRecipe> getRecipeType() {
        return COFFEE_GRINDER_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.zcoffeecraft2.coffee_grinder");
    }


    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CoffeeGrinderRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 54, 34).addItemStack(new ItemStack (recipe.getInput().getItem(), 16));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 34).addItemStack(recipe.getOutput());
    }

    @Override
    @SuppressWarnings({"removal"})
    public IDrawable getBackground() {
        return background;
    }

}
