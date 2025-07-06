package net.zusz.zcoffeecraft2.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.zusz.zcoffeecraft2.block.ModBlocks;
import net.zusz.zcoffeecraft2.compat.CoffeeMachineRecipe;
import org.jetbrains.annotations.Nullable;
import org.openjdk.nashorn.internal.ir.annotations.Ignore;

public class CoffeeMachineRecipeCategory implements IRecipeCategory<CoffeeMachineRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(ZCoffeeCraft2.MOD_ID, "coffee_machine");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(ZCoffeeCraft2.MOD_ID,
            "textures/gui/coffee_machine/coffee_machine_gui.png");

    public static final RecipeType<CoffeeMachineRecipe> COFFEE_MACHINE_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, CoffeeMachineRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public CoffeeMachineRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.COFFEE_MACHINE));
    }


    @Override
    public RecipeType<CoffeeMachineRecipe> getRecipeType() {
        return COFFEE_MACHINE_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.zcoffeecraft2.coffee_machine");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CoffeeMachineRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 54, 34).addIngredients(recipe.getInput1());
        builder.addSlot(RecipeIngredientRole.INPUT, 34, 34).addIngredients(recipe.getInput2());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 34).addItemStack(recipe.getOutput());
    }

    @Override
    @SuppressWarnings({"removal"})
    public IDrawable getBackground() {
        return background;
    }

    //@Override
    //public void draw(CoffeeMachineRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
       // IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
       // background.draw(guiGraphics);
    //}
}
