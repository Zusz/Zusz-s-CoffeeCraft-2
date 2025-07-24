package net.zusz.zcoffeecraft2.event;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.zusz.zcoffeecraft2.screen.custom.CoffeeMachineScreen;

import java.util.List;

@EventBusSubscriber(modid = ZCoffeeCraft2.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        Screen screen = Minecraft.getInstance().screen;

        if (!(screen instanceof CoffeeMachineScreen)) {
            return;
        }

        ItemStack stack = event.getItemStack();
        ItemStack waterBottleStack = new ItemStack(Items.POTION);
        waterBottleStack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER));

        if (stack.getItem() == Items.WATER_BUCKET) {
            List<Component> tooltip = event.getToolTip();
            tooltip.add(Component.literal("☕Every type of Coffee needs Water!☕").withStyle(ChatFormatting.GOLD));
        } else if (stack.getItem() == waterBottleStack.getItem()) {
            List<Component> tooltip = event.getToolTip();
            tooltip.add(Component.literal("☕Every type of Coffee needs Water!☕").withStyle(ChatFormatting.GOLD));
        }
    }
}

//☕