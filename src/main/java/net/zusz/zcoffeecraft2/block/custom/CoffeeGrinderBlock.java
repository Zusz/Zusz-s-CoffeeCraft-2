package net.zusz.zcoffeecraft2.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.zusz.zcoffeecraft2.block.entity.CoffeeGrinderBlockEntity;
import net.zusz.zcoffeecraft2.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class CoffeeGrinderBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final MapCodec<CoffeeGrinderBlock> CODEC = simpleCodec(CoffeeGrinderBlock::new);
    public CoffeeGrinderBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CoffeeGrinderBlockEntity(pos, state);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof CoffeeGrinderBlockEntity coffeeGrinderBlockEntity) {
                coffeeGrinderBlockEntity.drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                              Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof CoffeeGrinderBlockEntity coffeeGrinderBlockEntity) {
                ((ServerPlayer) player).openMenu(
                        new SimpleMenuProvider(coffeeGrinderBlockEntity, Component.literal("Coffee Grinder")), pos
                );
            } else {
                throw new IllegalStateException("Missing CoffeeGrinderBlockEntity!");
            }
        }

        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;

        return createTickerHelper(type, ModBlockEntities.COFFEE_GRINDER_BE.get(),
                (lvl, pos, st, be) -> be.tick(lvl, pos, st));
    }

    @Override
    public boolean isOcclusionShapeFullBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    private static final VoxelShape COFFEE_GRINDER_SHAPE = Block.box(2, 0, 2, 14, 12, 14);
    private static final VoxelShape COFFEE_GRINDER_SHAPE_EAST = Block.box(0, 0, 4, 13, 16, 12);
    private static final VoxelShape COFFEE_GRINDER_SHAPE_NORTH =  Block.box(4, 0, 3, 12, 16, 16);
    private static final VoxelShape COFFEE_GRINDER_SHAPE_WEST = Block.box(3, 0, 4, 16, 16, 12);
    private static final VoxelShape COFFEE_GRINDER_SHAPE_SOUTH = Block.box(4, 0, 0, 12, 16, 13);


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        return switch (facing) {
            case DOWN, UP -> COFFEE_GRINDER_SHAPE;
            case NORTH -> COFFEE_GRINDER_SHAPE_NORTH;
            case SOUTH -> COFFEE_GRINDER_SHAPE_SOUTH;
            case WEST -> COFFEE_GRINDER_SHAPE_WEST;
            case EAST -> COFFEE_GRINDER_SHAPE_EAST;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        return switch (facing) {
            case DOWN, UP -> COFFEE_GRINDER_SHAPE;
            case NORTH -> COFFEE_GRINDER_SHAPE_NORTH;
            case SOUTH -> COFFEE_GRINDER_SHAPE_SOUTH;
            case WEST -> COFFEE_GRINDER_SHAPE_WEST;
            case EAST -> COFFEE_GRINDER_SHAPE_EAST;
        };
    }
}
