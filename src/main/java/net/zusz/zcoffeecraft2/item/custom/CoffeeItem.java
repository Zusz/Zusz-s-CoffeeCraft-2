package net.zusz.zcoffeecraft2.item.custom;


import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.zusz.zcoffeecraft2.component.ModDataComponents;
import org.jetbrains.annotations.NotNull;
import org.openjdk.nashorn.internal.ir.annotations.Ignore;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CoffeeItem extends Item {

    public CoffeeItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        Holder<MobEffect> effect = MobEffects.MOVEMENT_SPEED;
        int duration = 0;
        int amplifier = 0;

        if (stack.get(ModDataComponents.BEAN) == null) {
            duration = 20;
            amplifier = 1;
        } else if (Objects.equals(stack.get(ModDataComponents.BEAN), "arabica")) {
            duration = 4800;
            amplifier = 1;
        }

        if (stack.get(ModDataComponents.ROAST) == null ) {
            effect = MobEffects.MOVEMENT_SPEED;
        } else if (Objects.equals(stack.get(ModDataComponents.ROAST), "light")) {
            effect = MobEffects.MOVEMENT_SPEED;
        } else if (Objects.equals(stack.get(ModDataComponents.ROAST), "medium")) {
            effect = MobEffects.DIG_SPEED;
        } else if (Objects.equals(stack.get(ModDataComponents.ROAST), "dark")) {
            effect = MobEffects.DAMAGE_BOOST;
        }

        if (stack.get(ModDataComponents.INGREDIENTS) == null) {
        }
        else if (stack.get(ModDataComponents.INGREDIENTS).isEmpty()) {
        }
        else {
            for (String item : Objects.requireNonNull(stack.get(ModDataComponents.INGREDIENTS))) {
                if (Objects.equals(item, "sugar")) {
                  duration = duration + 600;
                }
            }
        }

        // Check if on server and is a player
        if (!level.isClientSide && entity instanceof Player player) {
            // Give the effect
            player.addEffect(new MobEffectInstance(effect, duration, amplifier));
        }

        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {

        if(Screen.hasShiftDown() || isInJEIContext()) {

            tooltipComponents.add(Component.literal("Beans:").withStyle(ChatFormatting.BLUE));
            if(stack.get(ModDataComponents.BEAN) == null) {
                tooltipComponents.add(Component.literal("   -Depends on Bean type").withStyle(ChatFormatting.GRAY));
            } else if (Objects.equals(stack.get(ModDataComponents.BEAN), "arabica")) {
                tooltipComponents.add(Component.literal("   -Arabica").withStyle(ChatFormatting.GRAY));
            }

            tooltipComponents.add(Component.literal("Roast:").withStyle(ChatFormatting.BLUE));
            if(stack.get(ModDataComponents.ROAST) == null) {
                tooltipComponents.add(Component.literal("   -Depends on Bean roast").withStyle(ChatFormatting.GRAY));
            } else {
                switch (stack.get(ModDataComponents.ROAST)) {
                    case "light" -> tooltipComponents.add(Component.literal("   -Light").withStyle(ChatFormatting.GRAY));
                    case "medium" -> tooltipComponents.add(Component.literal("   -Medium").withStyle(ChatFormatting.GRAY));
                    case "dark" -> tooltipComponents.add(Component.literal("   -Dark").withStyle(ChatFormatting.GRAY));
                }
            }

            if (stack.get(ModDataComponents.INGREDIENTS) == null) {
            }
            else if (stack.get(ModDataComponents.INGREDIENTS).isEmpty()) {
            }
            else {
                tooltipComponents.add(Component.literal("Ingredients:").withStyle(ChatFormatting.BLUE));
                for (String item : Objects.requireNonNull(stack.get(ModDataComponents.INGREDIENTS))) {
                    switch (item) {
                        case "sugar" -> tooltipComponents.add(Component.literal("   -Sugar").withStyle(ChatFormatting.GRAY));
                        case "milk" -> tooltipComponents.add(Component.literal("   -Milk").withStyle(ChatFormatting.GRAY));
                        case "honey" -> tooltipComponents.add(Component.literal("   -Honey").withStyle(ChatFormatting.GRAY));
                        case "cocoa" -> tooltipComponents.add(Component.literal("   -Cocoa").withStyle(ChatFormatting.GRAY));
                    }
                }
            }
            //tooltipComponents.add(Component.literal("YEEEEEAH"));


        } else {
            tooltipComponents.add(Component.translatable("§7Hold §eShift§7 for more Information"));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
    private boolean isInJEIContext() {
        return Arrays.stream(Thread.currentThread().getStackTrace())
                .anyMatch(element -> element.getClassName().contains("mezz.jei"));
    }
}