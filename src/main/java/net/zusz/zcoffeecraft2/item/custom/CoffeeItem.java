package net.zusz.zcoffeecraft2.item.custom;


import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.zusz.zcoffeecraft2.component.ModDataComponents;

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

        if(stack.get(ModDataComponents.ROAST) == null) {
            tooltipComponents.add(Component.literal("NAAAAY"));
        }
        if(Objects.equals(stack.get(ModDataComponents.ROAST), "light")) {
            tooltipComponents.add(Component.literal("LIGHT"));
        }
        if(Objects.equals(stack.get(ModDataComponents.ROAST), "medium")) {
            tooltipComponents.add(Component.literal("MEDIUM"));
        }
        if(Objects.equals(stack.get(ModDataComponents.ROAST), "dark")) {
            tooltipComponents.add(Component.literal("DAAAARK"));
        }

        tooltipComponents.add(Component.literal("YEEEEEAH"));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }
}