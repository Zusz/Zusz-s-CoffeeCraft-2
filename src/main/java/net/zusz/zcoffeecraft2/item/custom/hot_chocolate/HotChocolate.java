package net.zusz.zcoffeecraft2.item.custom.hot_chocolate;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.zusz.zcoffeecraft2.api.coffeebeantypes.CoffeeBeanTypeRegistry;
import net.zusz.zcoffeecraft2.api.coffeeingredients.CoffeeIngredientRegistry;
import net.zusz.zcoffeecraft2.api.coffeerecipes.CoffeeRecipe;
import net.zusz.zcoffeecraft2.api.coffeerecipes.CoffeeRecipeRegistry;
import net.zusz.zcoffeecraft2.block.ModBlocks;
import net.zusz.zcoffeecraft2.block.custom.CoffeeCupBlock;
import net.zusz.zcoffeecraft2.block.custom.enums.RoastType;
import net.zusz.zcoffeecraft2.block.entity.CoffeeCupBlockEntity;
import net.zusz.zcoffeecraft2.component.ModDataComponents;
import net.zusz.zcoffeecraft2.effect.CoffeeEffectData;
import net.zusz.zcoffeecraft2.effect.CoffeeEffectInstance;
import net.zusz.zcoffeecraft2.effect.ModEffects;
import net.zusz.zcoffeecraft2.screen.custom.CoffeeMachineScreen;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static net.zusz.zcoffeecraft2.api.coffeerecipes.CoffeeRecipeRegistry.getRecipe;

public class HotChocolate extends Item {

    public HotChocolate(Properties properties){super(properties);}





    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, @NotNull LivingEntity entity) {
        if (!(entity instanceof ServerPlayer player) || level.isClientSide) return stack;

        Optional<CoffeeRecipe> recipeOpt = getRecipe(stack.get(ModDataComponents.INGREDIENTS));
        if (recipeOpt.isEmpty()) return stack;

        CoffeeRecipe recipe = recipeOpt.get();
        int duration = recipe.baseDuration();
        int amplifier = recipe.baseAmplifier();
        int delay = recipe.baseDelay();
        Holder<MobEffect> secondaryEffect = null;
        int secondaryDuration = 0;
        int secondaryAmplifier = 0;
        CoffeeEffectInstance ceffect = new CoffeeEffectInstance(
                recipe.effect().value(),
                duration,
                amplifier,
                delay,
                secondaryEffect,
                secondaryDuration,
                secondaryAmplifier
        );

        CoffeeEffectData.addEffect(player, ceffect);
        if (delay > 0)
            player.addEffect(new MobEffectInstance(ModEffects.CAFFEINATED_EFFECT, delay, 0));

        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {
        return Component.translatable("coffeetype.zcoffeecraft2.hot_chocolate");
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
            List<String> ingredients = stack.get(ModDataComponents.INGREDIENTS);
            if (ingredients != null && !ingredients.isEmpty()) {
                tooltipComponents.add(Component.translatable("coffeedescription.zcoffeecraft2.ingredients").withStyle(ChatFormatting.BLUE));

                for (String item : ingredients) {

                    if(CoffeeIngredientRegistry.getTooltip(item).isPresent()) {
                        tooltipComponents.add(CoffeeIngredientRegistry.getTooltip(item).get().withStyle(ChatFormatting.GRAY));
                    }
                }
            }
        }
        else if (inCoffeeMachine) {
            tooltipComponents.add(Component.translatable("coffeedescription.zcoffeecraft2.shifttoseemoreinfo"));
        }

        // --- Recipe & Effects ---
        List<String> ingredients = stack.get(ModDataComponents.INGREDIENTS);
        Optional<CoffeeRecipe> recipeOpt = getRecipe(ingredients != null ? ingredients : List.of());
        if (recipeOpt.isEmpty()) {
            super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
            return;
        }

        CoffeeRecipe recipe = recipeOpt.get();

        int duration = recipe.baseDuration();
        int amplifier = recipe.baseAmplifier();
        int delay = recipe.baseDelay();
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
        if (effect == MobEffects.HEAL) return Component.translatable("effect.minecraft.instant_health");
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

}
