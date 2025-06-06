//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.zusz.zcoffeecraft2.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import net.zusz.zcoffeecraft2.item.ModItems;

public class CoffeeBushBlock extends BushBlock implements BonemealableBlock {
    public static final MapCodec<CoffeeBushBlock> CODEC = simpleCodec(CoffeeBushBlock::new);
    private static final float HURT_SPEED_THRESHOLD = 0.003F;
    public static final int MAX_AGE = 7;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 7);
    private static final VoxelShape SAPLING_SHAPE;
    private static final VoxelShape MID_GROWTH_SHAPE;

    public MapCodec<CoffeeBushBlock> codec() {
        return CODEC;
    }

    public CoffeeBushBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(AGE, 0));
    }

    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack((ItemLike) ModItems.ARABICA_COFFEE_CHERRY);
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if ((Integer)state.getValue(AGE) == 0) {
            return SAPLING_SHAPE;
        } else {
            return MID_GROWTH_SHAPE;
        }
    }

    protected boolean isRandomlyTicking(BlockState state) {
        return (Integer)state.getValue(AGE) < 7;
    }

    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int i = (Integer)state.getValue(AGE);
        if (i < 7 && level.getRawBrightness(pos.above(), 0) >= 9 && CommonHooks.canCropGrow(level, pos, state, random.nextInt(5) == 0)) {
            BlockState blockstate = (BlockState)state.setValue(AGE, i + 1);
            level.setBlock(pos, blockstate, 2);
            CommonHooks.fireCropGrowPost(level, pos, state);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, Context.of(blockstate));
        }

    }

    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE) {
            entity.makeStuckInBlock(state, new Vec3(0.800000011920929, 0.75, 0.800000011920929));
            if (!level.isClientSide && (Integer)state.getValue(AGE) > 0 && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
                double d0 = Math.abs(entity.getX() - entity.xOld);
                double d1 = Math.abs(entity.getZ() - entity.zOld);
                if (d0 >= 0.003000000026077032 || d1 >= 0.003000000026077032) {
                    entity.hurt(level.damageSources().sweetBerryBush(), 1.0F);
                }
            }
        }

    }

    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        int i = (Integer)state.getValue(AGE);
        boolean flag = i == 7;
        return !flag && stack.is(Items.BONE_MEAL) ? ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION : super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int i = (Integer)state.getValue(AGE);
        boolean flag = i == 7;
        if (i > 5) {
            int j = 1 + level.random.nextInt(2);
            popResource(level, pos, new ItemStack((Holder<Item>) ModItems.ARABICA_COFFEE_CHERRY, j + (flag ? 1 : 0)));
            level.playSound((Player)null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            BlockState blockstate = (BlockState)state.setValue(AGE, 4);
            level.setBlock(pos, blockstate, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, Context.of(player, blockstate));
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AGE});
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return (Integer)state.getValue(AGE) < 7;
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int i = Math.min(7, (Integer)state.getValue(AGE) + 1);
        level.setBlock(pos, (BlockState)state.setValue(AGE, i), 2);
    }

    static {
        SAPLING_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 8.0, 13.0);
        MID_GROWTH_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
    }
}
