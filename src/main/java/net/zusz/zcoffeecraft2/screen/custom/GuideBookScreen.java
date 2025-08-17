package net.zusz.zcoffeecraft2.screen.custom;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.zusz.zcoffeecraft2.block.ModBlocks;
import net.zusz.zcoffeecraft2.item.ModItems;

public class GuideBookScreen extends Screen {
    public int currentPage = 0;
    private int maxPage = 2; //actually 1 less than the last page
    private static final ResourceLocation BACKGROUND_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("zcoffeecraft2", "textures/gui/guidebook_bg.png");

    public GuideBookScreen() {
        super(Component.translatable("guide.zcoffeecraft2.title"));
    }

    @Override
    protected void init() {
        int bgWidth = 250;
        int bgHeight = 308;
        int x = (this.width - bgWidth) / 2;
        int y = (this.height - bgHeight) / 2;

        // Previous Page Button
        this.addRenderableWidget(Button.builder(Component.literal(""), btn -> {
            if (currentPage > 0) currentPage--;
        }).bounds(x + 10, y + bgHeight - 60, 40, 40).build());

        // Next Page Button
        this.addRenderableWidget(Button.builder(Component.literal(""), btn -> {
            if (currentPage < maxPage) currentPage++;
        }).bounds(x + bgWidth - 50, y + bgHeight - 60, 40, 40).build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);

        int bgWidth = 250;
        int bgHeight = 308;
        int x = (this.width - bgWidth) / 2;
        int y = (this.height - bgHeight) / 2;

        // --- Render background ---
        graphics.blit(BACKGROUND_TEXTURE, x, y, 0, 0, bgWidth, bgHeight, bgWidth, bgHeight);

        ItemStack stack1 = new ItemStack(ModItems.CUP_OF_COFFEE.asItem());
        ResourceLocation textureToDraw = null;
        switch (currentPage) {
            case 0 -> {
                stack1 = new ItemStack(ModItems.CUP_OF_COFFEE.asItem());
            }
            case 1 -> {
                stack1 = new ItemStack(ModItems.ARABICA_COFFEE_CHERRY.asItem());
                textureToDraw = ResourceLocation.fromNamespaceAndPath("zcoffeecraft2", "textures/block/arabica_coffee_bush_stage7.png");
            }
        }

        drawItemStack(graphics, stack1, x + 25, y + 20);
        if (textureToDraw == null) {
            drawItemStack(graphics, stack1, x + bgWidth - 25 - 32, y + 20);
        } else {
            graphics.blit(textureToDraw, x + bgWidth - 25 - 32, y + 20, 0, 0, 32, 32, 32, 32);
        }

        // --- Title ---
        //Its important to add linebreaks when the text wont fit, otherwise this method breaks
        drawLargeText(graphics, Component.translatable("guide.zcoffeecraft2.title"), this.width / 2, y + 30);

        // --- Content ---
        Component content = Component.translatable("guide.zcoffeecraft2.page" + (currentPage + 1));
        drawContent(graphics, content, x, y, bgWidth - 50);

        graphics.drawWordWrap(this.font, content, x + 25, y + 70, bgWidth - 50, 0xE0E0E0);

        Component indicator = Component.literal((currentPage + 1) + " / " + (maxPage + 1));
        drawCenteredStringNoShadow(graphics, this.font, indicator, this.width / 2, y + bgHeight - 40, 0xAAAAAA);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void drawCenteredStringNoShadow(GuiGraphics graphics, Font font, Component text, int x, int y, int color) {
        int textWidth = font.width(text);
        graphics.drawString(font, text, x - textWidth / 2, y, color, false);
    }


    //ChatGPT magic, dont touch
    private void drawItemStack(GuiGraphics graphics, ItemStack stack, int x, int y) {
        graphics.pose().pushPose();
        graphics.pose().translate(x, y, 35); // move to where to draw
        graphics.pose().scale(2.0f, 2.0f, 1.0f); // 2x size
        graphics.renderItem(stack, 0, 0); // render at 0,0 inside scaled space
        graphics.pose().popPose();
    }

    private void drawLargeText(GuiGraphics graphics, Component toDisplay, int x, int y) {
        graphics.pose().pushPose();
        graphics.pose().translate(x, y, 35); // move to where to draw
        graphics.pose().scale(1.5f, 1.5f, 1.0f); // 1.5x size
        drawCenteredStringNoShadow(graphics, this.font, toDisplay, 0, 0, 0xAAAAAA); //render at 0,0 inside scaled space
        graphics.pose().popPose();
    }

    private void drawContent(GuiGraphics graphics, Component content, int x, int y, int lineWidth) {
        String text = content.getString(); // get raw string

        // Split on linebreak
        String[] lines = text.split("\n");

        // Draw each line manually
        int lineHeight = this.font.lineHeight;
        for (int i = 0; i < lines.length; i++) {
            graphics.drawWordWrap(this.font, Component.literal(lines[i]), x + 25, y + 70 + i * lineHeight, lineWidth, 0xE0E0E0);
        }

    }
}