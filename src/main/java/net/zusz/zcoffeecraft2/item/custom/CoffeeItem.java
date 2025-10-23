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
import net.zusz.zcoffeecraft2.coffeerecipes.CoffeeRecipe;
import net.zusz.zcoffeecraft2.coffeerecipes.CoffeeRecipeRegistry;
import net.zusz.zcoffeecraft2.component.ModDataComponents;
import net.zusz.zcoffeecraft2.effect.CoffeeEffectData;
import net.zusz.zcoffeecraft2.effect.CoffeeEffectInstance;
import net.zusz.zcoffeecraft2.effect.ModEffects;
import net.zusz.zcoffeecraft2.item.ModItems;
import net.zusz.zcoffeecraft2.screen.custom.CoffeeMachineScreen;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static net.zusz.zcoffeecraft2.coffeerecipes.CoffeeRecipeRegistry.getRecipe;

public class CoffeeItem extends Item {

    public CoffeeItem(Properties properties) {
        super(properties);
    }




    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, @NotNull LivingEntity entity) {
        if (!(entity instanceof ServerPlayer player) || level.isClientSide) return stack;

        Optional<CoffeeRecipe> recipeOpt = getRecipe(stack.get(ModDataComponents.INGREDIENTS));
        if (recipeOpt.isEmpty()) return stack;

        CoffeeRecipe recipe = recipeOpt.get();
        String bean = stack.get(ModDataComponents.BEAN);
        String roast = stack.get(ModDataComponents.ROAST);

        int duration = adjustDuration(recipe.baseDuration(), bean, roast);
        int amplifier = recipe.baseAmplifier() + getBeanAmplifier(bean);
        int delay = recipe.baseDelay() + getRoastDelay(roast);
        Holder<MobEffect> secondaryEffect = null;
        if (bean.equals("liberica")) {secondaryEffect = recipe.secondaryEffect();}

        CoffeeEffectInstance ceffect = new CoffeeEffectInstance(
                recipe.effect().value(),
                duration,
                amplifier,
                delay,
                secondaryEffect,
                recipe.secondaryDuration(),
                0
        );

        CoffeeEffectData.addEffect(player, ceffect);
        if (delay > 0)
            player.addEffect(new MobEffectInstance(ModEffects.CAFFEINATED_EFFECT, delay, 0));

        return super.finishUsingItem(stack, level, entity);
    }

    private int adjustDuration(int base, String bean, String roast) {
        int duration = base;
        if ("arabica".equals(bean)) duration += 1200;
        if ("robusta".equals(bean)) duration -= 0;
        if("liberica".equals(bean)) duration -= 600;
        if ("dark".equals(roast)) duration += 2400;
        if ("medium".equals(roast)) duration += 1200;
        return duration;
    }

    private int getBeanAmplifier(@Nullable String bean) {
        if (bean == null) return 0; // 🩹 safe default
        return switch (bean) {
            case "robusta" -> 1;
            default -> 0;
        };
    }

    private int getRoastDelay(@Nullable String roast) {
        if (roast == null) return 0;
        return switch (roast) {
            case "light" -> 0;
            case "medium" -> 200;
            case "dark" -> 500;
            default -> 0;
        };
    }




    @Override
    public @NotNull Component getName(ItemStack stack) {

        List<String> ingredients = stack.get(ModDataComponents.INGREDIENTS);
        String roast = stack.get(ModDataComponents.ROAST);

        Component coffeeComponent = Component.translatable(CoffeeRecipeRegistry.getName(ingredients));
        Component roastComponent = Component.translatable("coffeeroast.zcoffeecraft2.noroast");


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

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {

        boolean inJEI = isInJEIContext();
        boolean inCoffeeMachine = Minecraft.getInstance().screen instanceof CoffeeMachineScreen;
        boolean showDetails = (Screen.hasShiftDown() && inCoffeeMachine) || inJEI;

        // --- Detailed info: Beans, Roast, Ingredients ---
        if (showDetails) {
            tooltipComponents.add(Component.literal("Beans:").withStyle(ChatFormatting.BLUE));
            String bean = stack.get(ModDataComponents.BEAN);
            if (bean == null) {
                tooltipComponents.add(Component.literal("   -Depends on Bean type").withStyle(ChatFormatting.GRAY));
            } else {
                switch (bean) {
                    case "arabica" -> tooltipComponents.add(Component.literal("   -Arabica").withStyle(ChatFormatting.GRAY));
                    case "robusta" -> tooltipComponents.add(Component.literal("   -Robusta").withStyle(ChatFormatting.GRAY));
                    case "liberica" -> tooltipComponents.add(Component.literal("   -Liberica").withStyle(ChatFormatting.GRAY));
                    default -> tooltipComponents.add(Component.literal("   -Unknown Bean").withStyle(ChatFormatting.GRAY));
                }
            }

            tooltipComponents.add(Component.literal("Roast:").withStyle(ChatFormatting.BLUE));
            String roast = stack.get(ModDataComponents.ROAST);
            if (roast == null) {
                tooltipComponents.add(Component.literal("   -Depends on Bean roast").withStyle(ChatFormatting.GRAY));
            } else {
                switch (roast) {
                    case "light" -> tooltipComponents.add(Component.literal("   -Light").withStyle(ChatFormatting.GRAY));
                    case "medium" -> tooltipComponents.add(Component.literal("   -Medium").withStyle(ChatFormatting.GRAY));
                    case "dark" -> tooltipComponents.add(Component.literal("   -Dark").withStyle(ChatFormatting.GRAY));
                    default -> tooltipComponents.add(Component.literal("   -Unknown Roast").withStyle(ChatFormatting.GRAY));
                }
            }

            List<String> ingredients = stack.get(ModDataComponents.INGREDIENTS);
            if (ingredients != null && !ingredients.isEmpty()) {
                tooltipComponents.add(Component.literal("Ingredients:").withStyle(ChatFormatting.BLUE));
                for (String item : ingredients) {
                    switch (item) {
                        case "sugar" -> tooltipComponents.add(Component.literal("   -Sugar").withStyle(ChatFormatting.GRAY));
                        case "milk" -> tooltipComponents.add(Component.literal("   -Milk").withStyle(ChatFormatting.GRAY));
                        case "honey" -> tooltipComponents.add(Component.literal("   -Honey").withStyle(ChatFormatting.GRAY));
                        case "chocolate" -> tooltipComponents.add(Component.literal("   -Chocolate").withStyle(ChatFormatting.GRAY));
                        case "milk_foam" -> tooltipComponents.add(Component.literal("   -Milk Foam").withStyle(ChatFormatting.GRAY));
                        case "steamed_milk" -> tooltipComponents.add(Component.literal("   -Steamed Milk").withStyle(ChatFormatting.GRAY));
                        case "whipped_cream" -> tooltipComponents.add(Component.literal("   -Whipped Cream").withStyle(ChatFormatting.GRAY));
                        default -> tooltipComponents.add(Component.literal("   -" + item).withStyle(ChatFormatting.GRAY));
                    }
                }
            }
        }
        else if (inCoffeeMachine) {
            tooltipComponents.add(Component.translatable("§7Hold §eShift§7 for more Information"));
        }

        // --- Recipe & Effects ---
        List<String> ingredients = stack.get(ModDataComponents.INGREDIENTS);
        Optional<CoffeeRecipe> recipeOpt = getRecipe(ingredients != null ? ingredients : List.of());
        if (recipeOpt.isEmpty()) {
            super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
            return;
        }

        CoffeeRecipe recipe = recipeOpt.get();
        String bean = stack.get(ModDataComponents.BEAN);
        String roast = stack.get(ModDataComponents.ROAST);

        int duration = adjustDuration(recipe.baseDuration(), bean, roast);
        int amplifier = recipe.baseAmplifier() + getBeanAmplifier(bean);
        int delay = recipe.baseDelay() + getRoastDelay(roast);
        Holder<MobEffect> effect = recipe.effect();

        // --- Primary Effect ---
        Component effectName = getEffectNameComponent(effect);
        Component potency = amplifier != 0 ? Component.translatable("potion.potency." + amplifier)
                : (effect != null ? Component.literal("I") : Component.literal(""));
        Component durationComponent = !getFormattedDuration(duration).isEmpty()
                ? Component.literal(" (" + getFormattedDuration(duration) + ")")
                : Component.literal("");
        Component delayComponent = !getFormattedDuration(delay).isEmpty()
                ? Component.literal(" [" + getFormattedDuration(delay) + "]")
                : Component.literal("");

        tooltipComponents.add(Component.literal("")
                .append(effectName.copy().withStyle(ChatFormatting.BLUE))
                .append(Component.literal(" ").withStyle(ChatFormatting.BLUE))
                .append(potency.copy().withStyle(ChatFormatting.BLUE))
                .append(durationComponent.copy().withStyle(ChatFormatting.BLUE))
                .append(delayComponent.copy().withStyle(ChatFormatting.GOLD))
        );

        // --- Secondary Effect ---
        Holder<MobEffect> sEffect = recipe.secondaryEffect();
        if (sEffect != null) {
            String sFormattedDuration = getFormattedDuration(recipe.secondaryDuration());
            Component sDurationComponent = !sFormattedDuration.isEmpty()
                    ? Component.literal(" (" + sFormattedDuration + ")")
                    : Component.literal("");
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