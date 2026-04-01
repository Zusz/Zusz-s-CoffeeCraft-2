package net.zusz.zcoffeecraft2.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;

public class CoffeeGrinderScreen extends AbstractContainerScreen<CoffeeGrinderMenu> {

    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ZCoffeeCraft2.MOD_ID,"textures/gui/coffee_grinder/coffee_grinder_gui_with_inventory.png");
    private static final ResourceLocation ARROW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ZCoffeeCraft2.MOD_ID,"textures/gui/arrow_progress.png");

    public CoffeeGrinderScreen(CoffeeGrinderMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(RenderType.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight, 256, 256);

        renderProgressArrow(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            int progress = menu.getScaledArrowProgress();

            guiGraphics.blit(RenderType.GUI_TEXTURED,
                    ARROW_TEXTURE,
                    x + 73, y + 35,   // screen position
                    0, 0,             // texture start (u, v)
                    progress, 16,     // width grows, height stays fixed
                    24, 16            // full texture size
            );
        }
    }


    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        //System.out.println(menu.getItemHandler().getSlots());
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);

    }
}
