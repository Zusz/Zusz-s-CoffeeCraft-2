package net.zusz.zcoffeecraft2.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.zusz.zcoffeecraft2.block.entity.CoffeeGrinderBlockEntity;
import net.zusz.zcoffeecraft2.block.entity.CoffeeMachineBlockEntity;

public class CoffeeGrinderBlockEntityRenderer implements BlockEntityRenderer<CoffeeGrinderBlockEntity> {
    public CoffeeGrinderBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(CoffeeGrinderBlockEntity pBlockEntity, float pPartialTick, PoseStack poseStack,
                       MultiBufferSource multiBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        if (pBlockEntity.getFacing() == Direction.SOUTH) {

            renderSlotItem(pBlockEntity, poseStack, multiBufferSource, itemRenderer, 1, 0.5f, 0.2f, 0.5725f, 0.45f);
            renderSlotItem(pBlockEntity, poseStack, multiBufferSource, itemRenderer, 0, 0.5f, 0.875f, 0.2f, 0.25f);

        } else if (pBlockEntity.getFacing() == Direction.NORTH) {

            renderSlotItem(pBlockEntity, poseStack, multiBufferSource, itemRenderer, 1, 0.5f, 0.2f, 1.0f - 0.5725f, 0.45f);
            renderSlotItem(pBlockEntity, poseStack, multiBufferSource, itemRenderer, 0, 0.5f, 0.875f, 1.0f - 0.2f, 0.25f);

        } else if (pBlockEntity.getFacing() == Direction.EAST) {

            renderSlotItem(pBlockEntity, poseStack, multiBufferSource, itemRenderer, 1, 0.5725f, 0.2f, 1.0f - 0.5f, 0.45f);
            renderSlotItem(pBlockEntity, poseStack, multiBufferSource, itemRenderer, 0, 0.2f, 0.875f, 1.0f - 0.5f, 0.25f);
            
        } else if (pBlockEntity.getFacing() == Direction.WEST) {

            renderSlotItem(pBlockEntity, poseStack, multiBufferSource, itemRenderer, 1, 1.0f - 0.5725f, 0.2f, 0.5f, 0.45f);
            renderSlotItem(pBlockEntity, poseStack, multiBufferSource, itemRenderer, 0, 1.0f - 0.2f, 0.875f, 0.5f, 0.25f);

        }

    }

    private void renderSlotItem(CoffeeGrinderBlockEntity be, PoseStack poseStack, MultiBufferSource buffer,
                                ItemRenderer renderer, int slot, float x, float y, float z, float scale) {
        ItemStack stack = be.itemHandler.getStackInSlot(slot);
        if (stack.isEmpty()) return;

        poseStack.pushPose();
        poseStack.translate(x, y, z);
        poseStack.scale(scale, scale, scale);
        poseStack.mulPose(Axis.YP.rotationDegrees(be.getRenderingRotation()));

        renderer.renderStatic(
                stack,
                ItemDisplayContext.FIXED,
                getLightLevel(be.getLevel(), be.getBlockPos()),
                OverlayTexture.NO_OVERLAY,
                poseStack,
                buffer,
                be.getLevel(),
                1
        );
        poseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}