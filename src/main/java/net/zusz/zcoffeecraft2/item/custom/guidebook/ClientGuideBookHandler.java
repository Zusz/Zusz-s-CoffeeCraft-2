package net.zusz.zcoffeecraft2.item.custom.guidebook;

import net.minecraft.client.Minecraft;
import net.zusz.zcoffeecraft2.screen.custom.GuideBookScreen;

public class ClientGuideBookHandler {
    public static void openScreen() {
        Minecraft.getInstance().setScreen(new GuideBookScreen());
    }
}