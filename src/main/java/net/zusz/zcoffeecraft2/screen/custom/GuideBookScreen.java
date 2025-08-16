package net.zusz.zcoffeecraft2.screen.custom;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class GuideBookScreen extends Screen {
    private int currentPage = 0;
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
        this.addRenderableWidget(Button.builder(Component.literal("<"), btn -> {
            if (currentPage > 0) currentPage--;
        }).bounds(x + 10, y + bgHeight - 30, 20, 20).build());

        // Next Page Button
        this.addRenderableWidget(Button.builder(Component.literal(">"), btn -> {
            if (currentPage < 2) currentPage++;
        }).bounds(x + bgWidth - 30, y + bgHeight - 30, 20, 20).build());
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

        // --- Title ---
        Component title = Component.translatable("guide.zcoffeecraft2.title");
        graphics.drawCenteredString(this.font, title, this.width / 2, y + 30, 0xFFFFFF);

        // --- Content ---
        Component content = Component.translatable("guide.zcoffeecraft2.page" + (currentPage + 1));
        graphics.drawWordWrap(this.font, content, x + 25, y + 40, bgWidth - 30, 0xE0E0E0);

        String indicator = ((currentPage + 1) + " / ");
        graphics.drawCenteredString(this.font, indicator, this.width / 2, y + bgHeight - 10, 0xAAAAAA);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}