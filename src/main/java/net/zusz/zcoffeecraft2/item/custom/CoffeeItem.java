package net.zusz.zcoffeecraft2.item.custom;


import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.zusz.zcoffeecraft2.component.ModDataComponents;
import org.jetbrains.annotations.NotNull;
import org.openjdk.nashorn.internal.ir.annotations.Ignore;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class CoffeeItem extends Item {

    public CoffeeItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {

        return super.finishUsingItem(stack, level, livingEntity);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {

        tooltipComponents.add(Component.literal("Beans:").withStyle(ChatFormatting.BLUE));
        if(stack.get(ModDataComponents.BEAN) == null) {
            tooltipComponents.add(Component.literal("   -None (Depends on Ingredient)").withStyle(ChatFormatting.GRAY));
        } else if (Objects.equals(stack.get(ModDataComponents.BEAN), "arabica")) {
            tooltipComponents.add(Component.literal("   -Arabica").withStyle(ChatFormatting.GRAY));
        }

        tooltipComponents.add(Component.literal("Roast:").withStyle(ChatFormatting.BLUE));
        if(stack.get(ModDataComponents.ROAST) == null) {
            tooltipComponents.add(Component.literal("   -None (Depends on Ingredients)").withStyle(ChatFormatting.GRAY));
        } else {
            switch (stack.get(ModDataComponents.ROAST)) {
                case "light" -> tooltipComponents.add(Component.literal("   -Light").withStyle(ChatFormatting.GRAY));
                case "medium" -> tooltipComponents.add(Component.literal("  -Medium").withStyle(ChatFormatting.GRAY));
                case "dark" -> tooltipComponents.add(Component.literal("    -Dark").withStyle(ChatFormatting.GRAY));
            }
        }

        if (stack.get(ModDataComponents.INGREDIENTS) == null) {
        }
        else if (stack.get(ModDataComponents.INGREDIENTS).isEmpty()) {
        }
        else {
            tooltipComponents.add(Component.literal("Ingredients:").withStyle(ChatFormatting.BLUE));
            if (stack.get(ModDataComponents.INGREDIENTS).contains("sugar")) {
                tooltipComponents.add(Component.literal("   -Sugar").withStyle(ChatFormatting.GRAY));
            }
        }



        //tooltipComponents.add(Component.literal("YEEEEEAH"));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.DRINK;
    }
}