package net.zusz.zcoffeecraft2.item.custom;


import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.zusz.zcoffeecraft2.block.ModBlocks;
import net.zusz.zcoffeecraft2.block.custom.CoffeeCupBlock;
import net.zusz.zcoffeecraft2.block.custom.enums.RoastType;
import net.zusz.zcoffeecraft2.block.entity.CoffeeCupBlockEntity;
import net.zusz.zcoffeecraft2.component.ModDataComponents;
import net.zusz.zcoffeecraft2.effect.CoffeeEffectData;
import net.zusz.zcoffeecraft2.effect.CoffeeEffectInstance;
import net.zusz.zcoffeecraft2.effect.ModEffects;
import net.zusz.zcoffeecraft2.item.ModItems;
import net.zusz.zcoffeecraft2.screen.custom.CoffeeMachineScreen;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CoffeeItem extends Item {

    public CoffeeItem(Properties properties) {
        super(properties);
    }




    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, @NotNull LivingEntity entity) {
        List<String> ingredients = stack.get(ModDataComponents.INGREDIENTS);

        Holder<MobEffect> effect = getEffect(ingredients);
        int duration = getDuration(stack.get(ModDataComponents.BEAN), stack.get(ModDataComponents.ROAST), ingredients);
        int amplifier = getAmplifier(stack.get(ModDataComponents.BEAN));
        int delay = getDelay(stack.get(ModDataComponents.ROAST));
        Holder<MobEffect> secondaryEffect = getSecodaryEffect(stack.get(ModDataComponents.BEAN), ingredients);
        //System.out.println(secondaryEffect.value());
        int secondaryEffectDuration = 0;
        if (secondaryEffect != null) {
            secondaryEffectDuration = getSecodaryEffectDuration(secondaryEffect.value(), ingredients);
        }
        if (!level.isClientSide && entity instanceof ServerPlayer player) {
            if (effect != null) {
                CoffeeEffectInstance ceffect = new CoffeeEffectInstance(
                        effect.value(),
                        duration,
                        amplifier,
                        delay,
                        secondaryEffect,
                        secondaryEffectDuration,
                        0
                );
                CoffeeEffectData.addEffect(player, ceffect);
                if (delay != 0) {
                    player.addEffect(new MobEffectInstance(ModEffects.CAFFEINATED_EFFECT, delay, 0));
                }
            }

        }
        /* Check if on server and is a player
        if (!level.isClientSide && entity instanceof Player player) {
             Give the effect
            player.addEffect(new MobEffectInstance(effect, duration, amplifier));
        }*/

        super.finishUsingItem(stack, level, entity);

        if (entity instanceof Player player) {
            // If they are not in creative, give them the cup back
            if (!player.getAbilities().instabuild) {
                ItemStack cupStack = new ItemStack(ModItems.COFFEE_CUP.asItem());

                if (stack.isEmpty()) {
                    // If the coffee stack is now empty, just return the cup directly
                    return cupStack;
                } else {
                    // If the stack still exists (e.g., stacked coffee?), try to add cup to inventory
                    if (!player.getInventory().add(cupStack)) {
                        // If inventory is full, drop it
                        player.drop(cupStack, false);
                    }
                }
            }
        }
        System.out.println(stack.get(ModDataComponents.ROAST));
        System.out.println();
        return stack;
    }

    private Holder<MobEffect> getSecodaryEffect(String bean, List<String> ingredients) {
        if (Objects.equals(bean, "liberica")) {
            //System.out.println("Isliberica");
            if (((ingredients.size() == 0) || ingredients.size() == 1 && ingredients.contains("sugar"))) {
                //System.out.println("ISMOVEMENTSPEED");
                return MobEffects.JUMP;
            } else {
                //System.out.println("ISNTMOVEMENTSPEED");
                return MobEffects.MOVEMENT_SPEED;
            }
        }
        return null;
    }

    private int getSecodaryEffectDuration(MobEffect secondaryEffect, List<String> ingredients) {
        if (secondaryEffect == MobEffects.MOVEMENT_SPEED) {
            return 1200;
        }
        else return 2400;
    }


    @Override
    public @NotNull Component getName(ItemStack stack) {
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
        } else if (effect == MobEffects.REGENERATION) {
            coffeeComponent = Component.translatable("coffeetype.zcoffeecraft2.latte");
        } else if (effect == MobEffects.HEALTH_BOOST) {
            coffeeComponent = Component.translatable("coffeetype.zcoffeecraft2.honey_raf");
        } else if (effect == MobEffects.DAMAGE_BOOST) {
            coffeeComponent = Component.translatable("coffeetype.zcoffeecraft2.mocha");
        } else if (effect == MobEffects.NIGHT_VISION) {
            coffeeComponent = Component.translatable("coffeetype.zcoffeecraft2.marocchino");
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
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    private boolean isInJEIContext() {
        return Arrays.stream(Thread.currentThread().getStackTrace())
                .anyMatch(element -> element.getClassName().contains("mezz.jei"));
    }

    private int getDelay(String roast) {
        return switch (roast) {
            case "light" -> 0;
            case "medium" -> 200;
            case "dark" -> 500;
            case null -> 0;
            default -> throw new IllegalStateException("Unexpected value: " + roast);
        };
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {

        // Show detailed info if Shift is held or in JEI
        if (Screen.hasShiftDown() && Minecraft.getInstance().screen instanceof CoffeeMachineScreen || isInJEIContext()) {
            if(Screen.hasShiftDown() && Minecraft.getInstance().screen instanceof CoffeeMachineScreen || isInJEIContext()) {

                tooltipComponents.add(Component.literal("Beans:").withStyle(ChatFormatting.BLUE));
                if(stack.get(ModDataComponents.BEAN) == null) {
                    tooltipComponents.add(Component.literal("   -Depends on Bean type").withStyle(ChatFormatting.GRAY));
                } else if (Objects.equals(stack.get(ModDataComponents.BEAN), "arabica")) {
                    tooltipComponents.add(Component.literal("   -Arabica").withStyle(ChatFormatting.GRAY));
                } else if (Objects.equals(stack.get(ModDataComponents.BEAN), "robusta")) {
                    tooltipComponents.add(Component.literal("   -Robusta").withStyle(ChatFormatting.GRAY));
                } else if (Objects.equals(stack.get(ModDataComponents.BEAN), "liberica")) {
                    tooltipComponents.add(Component.literal("   -Liberica").withStyle(ChatFormatting.GRAY));
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
                            case "chocolate" -> tooltipComponents.add(Component.literal("   -Chocolate").withStyle(ChatFormatting.GRAY));
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
        } else if (Minecraft.getInstance().screen instanceof CoffeeMachineScreen) {
            tooltipComponents.add(Component.translatable("§7Hold §eShift§7 for more Information"));
        }

        List<String> ingredients = stack.get(ModDataComponents.INGREDIENTS);
        Holder<MobEffect> effect = getEffect(ingredients);
        int duration = getDuration(stack.get(ModDataComponents.BEAN), stack.get(ModDataComponents.ROAST), ingredients);
        int amplifier = getAmplifier(stack.get(ModDataComponents.BEAN));
        int delay = getDelay(stack.get(ModDataComponents.ROAST));

        // Main effect tooltip
        Component effectName = getEffectNameComponent(effect);
        Component potency = amplifier != 0 ? Component.translatable("potion.potency." + amplifier) : effect != null ? Component.literal("I") : Component.literal("");
        Component durationComponent = !getFormattedDuration(duration).isEmpty() ? Component.literal(" (" + getFormattedDuration(duration) + ")") : Component.literal("");
        Component delayComponent = !getFormattedDuration(delay).isEmpty() ? Component.literal(" [" + getFormattedDuration(delay) + "]") : Component.literal("");

        tooltipComponents.add(Component.literal("")
                .append(effectName.copy().withStyle(ChatFormatting.BLUE))
                .append(Component.literal(" ").withStyle(ChatFormatting.BLUE))
                .append(potency.copy().withStyle(ChatFormatting.BLUE))
                .append(durationComponent.copy().withStyle(ChatFormatting.BLUE))
                .append(delayComponent.copy().withStyle(ChatFormatting.GOLD))
        );

        // Secondary effect tooltip (safe null check)
        Holder<MobEffect> sEffect = getSecodaryEffect(stack.get(ModDataComponents.BEAN), ingredients);
        if (sEffect != null) {
            String sFormattedDuration = getFormattedDuration(getSecodaryEffectDuration(sEffect.value(), ingredients));
            Component sDurationComponent = !sFormattedDuration.isEmpty() ? Component.literal(" (" + sFormattedDuration + ")") : Component.literal("");
            Component sPotency = Component.literal("I");
            Component sEffectName = getEffectNameComponent(sEffect);

            tooltipComponents.add(Component.literal("")
                    .append(sEffectName.copy().withStyle(ChatFormatting.BLUE))
                    .append(Component.literal(" ").withStyle(ChatFormatting.BLUE))
                    .append(sPotency.copy().withStyle(ChatFormatting.BLUE))
                    .append(sDurationComponent.copy().withStyle(ChatFormatting.BLUE))
                    .append(delayComponent.copy().withStyle(ChatFormatting.GOLD))
            );
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    // Helper to get effect name
    private Component getEffectNameComponent(Holder<MobEffect> effect) {
        if (effect == null) return Component.literal("");
        if (effect == MobEffects.MOVEMENT_SPEED) return Component.translatable("effect.minecraft.speed");
        if (effect == MobEffects.JUMP) return Component.translatable("effect.minecraft.jump_boost");
        if (effect == MobEffects.ABSORPTION) return Component.translatable("effect.minecraft.absorption");
        if (effect == MobEffects.DAMAGE_RESISTANCE) return Component.translatable("effect.minecraft.resistance");
        if (effect == MobEffects.REGENERATION) return Component.translatable("effect.minecraft.regeneration");
        if (effect == MobEffects.HEALTH_BOOST) return Component.translatable("effect.minecraft.health_boost");
        if (effect == MobEffects.DAMAGE_BOOST) return Component.translatable("effect.minecraft.strength");
        if (effect == MobEffects.NIGHT_VISION) return Component.translatable("effect.minecraft.night_vision");
        return Component.literal("");
    }

    private Holder<MobEffect> getEffect( List<String> ingredients ) {
        Holder<MobEffect> effect = null;
        if (ingredients != null) {
            if ((ingredients.size() == 0) || ingredients.size() == 1 && ingredients.contains("sugar")) { //Espresso
                effect = MobEffects.MOVEMENT_SPEED;
            } else if (ingredients.size() == 1 && ingredients.contains("milk_foam") ||
                    ingredients.size() == 2 && ingredients.contains("milk_foam)") && ingredients.contains("sugar")) { //Macchiato
                effect = MobEffects.JUMP;
            } else if (ingredients.size() == 1 && ingredients.contains("whipped_cream") ||
                    ingredients.size() == 2 && ingredients.contains("whipped_cream") && ingredients.contains("sugar")) { //Con Panna
                effect = MobEffects.ABSORPTION;
            } else if (ingredients.size() == 1 && ingredients.contains("steamed_milk") ||
                    ingredients.size() == 2 && ingredients.contains("steamed_milk") && ingredients.contains("sugar")) { //Flat White
                effect = MobEffects.DAMAGE_RESISTANCE;
            } else if (ingredients.size() == 2 && ingredients.contains("steamed_milk") && ingredients.contains("milk_foam") ||
                    ingredients.size() == 3 && ingredients.contains("steamed_milk") && ingredients.contains("milk_foam") && ingredients.contains("sugar")) { //Flat White
                effect = MobEffects.REGENERATION;
            } else if (ingredients.size() == 2 && ingredients.contains("honey") && ingredients.contains("milk_foam") ||
                    ingredients.size() == 3 && ingredients.contains("honey") && ingredients.contains("milk_foam") && ingredients.contains("sugar")) { //Honey Raf
                effect = MobEffects.HEALTH_BOOST;
            } else if (ingredients.size() == 3 && ingredients.contains("chocolate") && ingredients.contains("steamed_milk") && ingredients.contains("whipped_cream") || //Mocha
                    ingredients.size() == 4 && ingredients.contains("chocolate") && ingredients.contains("steamed_milk") && ingredients.contains("whipped_cream") && ingredients.contains("sugar")) {
                effect = MobEffects.DAMAGE_BOOST;
            } else if (ingredients.size() == 2 && ingredients.contains("chocolate") && ingredients.contains("milk_foam") ||
                    ingredients.size() == 3 && ingredients.contains("chocolate") && ingredients.contains("milk_foam") && ingredients.contains("sugar")) {  //Marocchino
                effect = MobEffects.NIGHT_VISION;
            }
        }
        return effect;
    }

    private int getDuration(@Nullable String bean, @Nullable String roast, @Nullable List<String> ingredients) {
        int duration = 0;

        switch (bean) {
            case "arabica" -> {
                duration = 4800;
            }
            case "robusta" -> {
                duration = 3000;
            } case "liberica" -> {
                duration = 2400;
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
                } else if (duration == 2400) {
                    duration = 3600;
                }
            }
            case "dark" -> {
                if (duration == 3000) {
                    duration = 5400;
                } else if (duration == 4800) {
                    duration = 7200;
                } else if (duration == 2400) {
                    duration = 4800;
                }
            }

            case null -> {}
            default -> throw new IllegalStateException("Unexpected value: " + roast);
        }
        if (ingredients != null) {
            if (ingredients.contains("sugar")) {
                duration = duration + 1200;
            }
        }
        return duration;
    }

    private int getAmplifier(String bean) {
        int amplifier = 0;
        switch (bean) {
            case "arabica" -> {
                amplifier = 0;
            }
            case "robusta" -> {
                amplifier = 1;
            }
            case "liberica" -> {
                amplifier = 0;
            }

            case null -> {}
            default -> throw new IllegalStateException("Unexpected value: " + bean);
        }
        return amplifier;
    }

    private String getFormattedDuration(int duration) {
        if (duration >= 1) {
            int durationSeconds = duration / 20;
            int minutes = durationSeconds / 60;
            int seconds = durationSeconds % 60;
            String formattedDuration = String.format("%d:%02d", minutes, seconds);
            return formattedDuration;
        }
        else {
            return "";
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        InteractionHand hand = context.getHand();
        ItemStack stack = context.getItemInHand();

        // Only place when sneaking
        if (player == null || !player.isShiftKeyDown()) {
            return InteractionResult.PASS;
        }

        BlockPos placePos = clickedPos.relative(context.getClickedFace());

        //Check if block can be placed
        BlockState existingState = level.getBlockState(placePos);
        if (!existingState.canBeReplaced()) {
            return InteractionResult.FAIL;
        }

        BlockState state = ModBlocks.COFFEE_CUP_BLOCK.get().defaultBlockState()
                .setValue(BlockStateProperties.HORIZONTAL_FACING, player.getDirection().getOpposite());
        String roast = stack.get(ModDataComponents.ROAST);
        if (roast != null) {
            switch (roast) {
                case "medium" -> state = state.setValue(CoffeeCupBlock.ROAST, RoastType.MEDIUM);
                case "dark" -> state = state.setValue(CoffeeCupBlock.ROAST, RoastType.DARK);
                default -> state = state.setValue(CoffeeCupBlock.ROAST, RoastType.LIGHT);
            }
        }
        //System.out.println(state.getValue(CoffeeCupBlock.ROAST));


        // Place the block
        if (!level.isClientSide) {
            level.setBlock(placePos, state, Block.UPDATE_ALL_IMMEDIATE);

            if (!player.isCreative()) {
                stack.shrink(1);
            }
        }

        if (level.getBlockEntity(placePos) instanceof CoffeeCupBlockEntity cupEntity) {
            ItemStack toSet = new ItemStack(stack.getItem());
            toSet.set(ModDataComponents.ROAST, stack.get(ModDataComponents.ROAST));
            toSet.set(ModDataComponents.BEAN, stack.get(ModDataComponents.BEAN));
            toSet.set(ModDataComponents.INGREDIENTS, stack.get(ModDataComponents.INGREDIENTS));

            cupEntity.setCoffeeStack(toSet);
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }


}