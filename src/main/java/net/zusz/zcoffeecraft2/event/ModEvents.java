package net.zusz.zcoffeecraft2.event;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.zusz.zcoffeecraft2.ZCoffeeCraft2;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.zusz.zcoffeecraft2.effect.CoffeeEffectData;
import net.zusz.zcoffeecraft2.item.ModItems;
import net.zusz.zcoffeecraft2.screen.custom.CoffeeMachineScreen;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = ZCoffeeCraft2.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        for (ServerLevel level : event.getServer().getAllLevels()) {
            CoffeeEffectData.tick(level);
        }
    }

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        Screen screen = Minecraft.getInstance().screen;

        if (!(screen instanceof CoffeeMachineScreen)) {
            return;
        }

        ItemStack stack = event.getItemStack();
        ItemStack waterBottleStack = new ItemStack(Items.POTION);
        waterBottleStack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER));

        final List<Item> usedToMakeCoffee = List.of(
                ModItems.LIGHT_ARABICA_GROUND_COFFEE.asItem(),
                ModItems.MEDIUM_ARABICA_GROUND_COFFEE.asItem(),
                ModItems.DARK_ARABICA_GROUND_COFFEE.asItem(),

                ModItems.LIGHT_ROBUSTA_GROUND_COFFEE.asItem(),
                ModItems.MEDIUM_ROBUSTA_GROUND_COFFEE.asItem(),
                ModItems.DARK_ROBUSTA_GROUND_COFFEE.asItem(),

                ModItems.LIGHT_LIBERICA_GROUND_COFFEE.asItem(),
                ModItems.MEDIUM_LIBERICA_GROUND_COFFEE.asItem(),
                ModItems.DARK_LIBERICA_GROUND_COFFEE.asItem(),

                ModItems.MILK_FOAM.asItem(),
                ModItems.WHIPPED_CREAM.asItem(),
                ModItems.STEAMED_MILK.asItem(),

                Items.HONEY_BOTTLE.asItem(),
                Items.COCOA_BEANS.asItem()
        );

        if (stack.getItem() == Items.WATER_BUCKET) {
            List<Component> tooltip = event.getToolTip();
            tooltip.add(Component.literal("☕Every type of Coffee needs Water!☕").withStyle(ChatFormatting.GOLD));
        } else if (stack.getItem() == waterBottleStack.getItem()) {
            List<Component> tooltip = event.getToolTip();
            tooltip.add(Component.literal("☕Every type of Coffee needs Water!☕").withStyle(ChatFormatting.GOLD));
        } else if (stack.getItem() == ModItems.COFFEE_CUP.asItem()){
            List<Component> tooltip = event.getToolTip();
            tooltip.add(Component.literal("☕A Cup to put your Coffee into!☕").withStyle(ChatFormatting.GOLD));
        } else if (usedToMakeCoffee.contains(stack.getItem())){
            List<Component> tooltip = event.getToolTip();
            tooltip.add(Component.literal("☕This item can be used to make coffee!☕").withStyle(ChatFormatting.GOLD));
        }
    }
}

//☕