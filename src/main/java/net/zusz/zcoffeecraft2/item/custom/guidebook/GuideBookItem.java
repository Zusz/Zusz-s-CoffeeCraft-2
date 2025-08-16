package net.zusz.zcoffeecraft2.item.custom.guidebook;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.zusz.zcoffeecraft2.screen.custom.GuideBookScreen;

public class GuideBookItem extends Item {
    public GuideBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }

        // Client only: open the book GUI
        Minecraft.getInstance().setScreen(new GuideBookScreen());
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
    }
}
