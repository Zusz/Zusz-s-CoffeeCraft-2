package net.zusz.zcoffeecraft2.item.custom.guidebook;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.zusz.zcoffeecraft2.screen.custom.GuideBookScreen;

public class GuideBookItem extends Item {
    public GuideBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        // Client only: open the book GUI
        Minecraft.getInstance().setScreen(new GuideBookScreen());
        return InteractionResult.SUCCESS;
    }
}
