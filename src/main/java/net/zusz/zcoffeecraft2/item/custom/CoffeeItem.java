package net.zusz.zcoffeecraft2.item.custom;


import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
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
import net.zusz.zcoffeecraft2.screen.custom.CoffeeMachineScreen;
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
        List<String> ingredients = stack.get(ModDataComponents.INGREDIENTS);

        Holder<MobEffect> effect = getEffect(ingredients);
        int duration = getDuration(stack.get(ModDataComponents.BEAN), stack.get(ModDataComponents.ROAST));
        int amplifier = getAmplifier(stack.get(ModDataComponents.BEAN));
        int delay = 0;

        // Check if on server and is a player
        if (!level.isClientSide && entity instanceof Player player) {
            // Give the effect
            player.addEffect(new MobEffectInstance(effect, duration, amplifier));
        }

        return super.finishUsingItem(stack, level, entity);
    }


    @Override
    public Component getName(ItemStack stack) {
        List<String> ingredients = stack.get(ModDataComponents.INGREDIENTS);
        String roast = stack.get(ModDataComponents.ROAST);

        Component coffeeComponent = Component.translatable("coffeetype.zcoffeecraft2.notype");
        Component roastComponent = Component.translatable("coffeeroast.zcoffeecraft2.noroast");

        Holder<MobEffect> effect = getEffect(ingredients);

        if (effect == MobEffects.MOVEMENT_SPEED) {
            coffeeComponent = Component.translatable("coffeetype.zcoffeecraft2.espresso");
        } else if (effect == MobEffects.JUMP) {
            coffeeComponent = Component.translatable("coffeetype.zcoffeecraft2.macchiato");
        } else if (effect == MobEffects.ABSORPTION) {
            coffeeComponent = Component.translatable("coffeetype.zcoffeecraft2.con_panna");
        } else if (effect == MobEffects.DAMAGE_RESISTANCE) {
            coffeeComponent = Component.translatable("coffeetype.zcoffeecraft2.flat_white");
        }

        if (roast != null) {
            if (Objects.equals(roast, "light")) {
                roastComponent = Component.translatable("coffeeroast.zcoffeecraft2.light");
            } else if (Objects.equals(roast, "medium")) {
                roastComponent = Component.translatable("coffeeroast.zcoffeecraft2.medium");
            } else if (Objects.equals(roast, "dark")) {
                roastComponent = Component.translatable("coffeeroast.zcoffeecraft2.dark");
            }
        }

        return Component.translatable("coffeetype.zcoffeecraft2.base", roastComponent, coffeeComponent);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {

        if(Screen.hasShiftDown() && Minecraft.getInstance().screen instanceof CoffeeMachineScreen || isInJEIContext()) {

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
                        case "milk_foam" -> tooltipComponents.add(Component.literal("   -Milk Foam").withStyle(ChatFormatting.GRAY));
                        case "steamed_milk" -> tooltipComponents.add(Component.literal("   -Steamed Milk").withStyle(ChatFormatting.GRAY));
                        case "whipped_cream" -> tooltipComponents.add(Component.literal("   -Whipped Cream").withStyle(ChatFormatting.GRAY));
                    }
                }
            }
            //tooltipComponents.add(Component.literal("YEEEEEAH"));


        } else if (Minecraft.getInstance().screen instanceof CoffeeMachineScreen ) {
            tooltipComponents.add(Component.translatable("§7Hold §eShift§7 for more Information"));
        }

        //add effect and amplifier, duration yet to be made
        List<String> ingredients = stack.get(ModDataComponents.INGREDIENTS);
        Holder<MobEffect> effect = getEffect(ingredients);
        int duration = getDuration(stack.get(ModDataComponents.BEAN), stack.get(ModDataComponents.ROAST));
        int amplifier = getAmplifier(stack.get(ModDataComponents.BEAN));

        Component effectName = Component.literal("Effect Name Error");

        if (effect == MobEffects.MOVEMENT_SPEED) {
            effectName = Component.translatable("effect.minecraft.speed");
        } else if (effect == MobEffects.JUMP) {
            effectName = Component.translatable("effect.minecraft.jump_boost");
        } else if (effect == MobEffects.ABSORPTION) {
            effectName = Component.translatable("effect.minecraft.absorption");
        } else if (effect == MobEffects.DAMAGE_RESISTANCE) {
            effectName = Component.translatable("effect.minecraft.resistance");
        }

        Component potency = Component.translatable("potion.potency." + amplifier);

        int durationSeconds = duration / 20;
        int minutes = durationSeconds / 60;
        int seconds = durationSeconds % 60;
        String formattedDuration = String.format("%d:%02d", minutes, seconds);

        Component durationComponent = Component.literal(" (" + formattedDuration + ")");

        Component full = Component.literal("").withStyle(ChatFormatting.BLUE)
                .append(effectName).withStyle(ChatFormatting.BLUE)
                .append(" ").withStyle(ChatFormatting.BLUE)
                .append(potency).withStyle(ChatFormatting.BLUE)
                .append(durationComponent).withStyle(ChatFormatting.BLUE);

        tooltipComponents.add(full);

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
    private boolean isInJEIContext() {
        return Arrays.stream(Thread.currentThread().getStackTrace())
                .anyMatch(element -> element.getClassName().contains("mezz.jei"));
    }

    private Holder<MobEffect> getEffect( List<String> ingredients ) {
        Holder<MobEffect> effect = MobEffects.MOVEMENT_SPEED;
        if (ingredients != null) {
            if ((ingredients.size() == 0) || ingredients.size() == 1 && ingredients.contains("sugar")) { //Espresso
                effect = MobEffects.MOVEMENT_SPEED;
            } else if (ingredients.size() == 1 && ingredients.contains("milk_foam") || ingredients.size() == 2 && ingredients.contains("milk_foam)") && ingredients.contains("sugar")) { //Macchiato
                effect = MobEffects.JUMP;
            } else if (ingredients.size() == 1 && ingredients.contains("whipped_cream") || ingredients.size() == 2 && ingredients.contains("whipped_cream") && ingredients.contains("sugar")) { //Con Panna
                effect = MobEffects.ABSORPTION;
            } else if (ingredients.size() == 1 && ingredients.contains("steamed_milk") || ingredients.size() == 2 && ingredients.contains("steamed_milk") && ingredients.contains("sugar")) { //Flat White
                effect = MobEffects.DAMAGE_RESISTANCE;
            }
        }
        return effect;
    }

    private int getDuration(String bean, String roast) {
        int duration = 0;

        switch (bean) {
            case "arabica" -> {
                duration = 4800;
            }
            case "robusta" -> {
                duration = 3000;
            }

            case null -> {}
            default -> throw new IllegalStateException("Unexpected value: " + bean);
        }

        switch (roast) {
            case "light" -> duration = duration;
            case "medium" -> {
                if (duration == 3000) {
                    duration = 4800;
                } else if (duration == 4800) {
                    duration = 6600;
                }
            }
            case "dark" -> {
                if (duration == 3000) {
                    duration = 5400;
                } else if (duration == 4800) {
                    duration = 7200;
                }
            }

            case null -> {}
            default -> throw new IllegalStateException("Unexpected value: " + roast);
        }
        return duration;
    }

    private int getAmplifier(String bean) {
        int amplifier = 0;
        switch (bean) {
            case "arabica" -> {
                amplifier = 1;
            }
            case "robusta" -> {
                amplifier = 2;
            }

            case null -> {}
            default -> throw new IllegalStateException("Unexpected value: " + bean);
        }
        return amplifier;
    }
}