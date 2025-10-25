package net.zusz.zcoffeecraft2.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.MutableDataComponentHolder;
import net.zusz.zcoffeecraft2.block.custom.enums.RoastType;
import net.zusz.zcoffeecraft2.block.entity.CoffeeCupBlockEntity;
import net.minecraft.core.Direction;
import net.zusz.zcoffeecraft2.block.entity.CoffeeMachineBlockEntity;
import org.jetbrains.annotations.Nullable;

public class CoffeeCupBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    private static final MapCodec<CoffeeCupBlock> CODEC = simpleCodec(CoffeeCupBlock::new);

    public static final EnumProperty<RoastType> ROAST = EnumProperty.create("roast", RoastType.class);

    private static Component name = Component.literal("");


    public CoffeeCupBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH) // Default facing
                .setValue(ROAST, RoastType.LIGHT)); // Default roast
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, ROAST);
    }


    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Block.box(6.5, 0, 6.5, 9.5, 3, 9.5);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public void onBlockStateChange(LevelReader level, BlockPos pos, BlockState oldState, BlockState newState) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof CoffeeCupBlockEntity coffeeCupBlockEntity) {
                ItemStack coffeeStack = coffeeCupBlockEntity.getCoffeeStack();
                if (!coffeeStack.isEmpty()) {
                    name = coffeeStack.getDisplayName(); // literal
                } else {
                    name = Component.translatable("block.zcoffeecraft2.coffee_cup_block"); // fallback translatable
                }
            }
        }
        super.onBlockStateChange(level, pos, oldState, newState);
    }

    @Override
    public MutableComponent getName() {
        return name.copy();
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CoffeeCupBlockEntity(blockPos, blockState);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof CoffeeCupBlockEntity coffeeCupBlockEntity) {
                coffeeCupBlockEntity.drops();
            }
        }
        System.out.println(ROAST);
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof CoffeeCupBlockEntity coffeeCupBlockEntity) {
            ItemStack coffeeStack = coffeeCupBlockEntity.getCoffeeStack();
            if (coffeeStack.isEmpty()) {
                System.out.println("COFFEESTACKISEMPTYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
                // Return the coffee-filled cup item as the "picked" block
            } else {
                return coffeeStack;
            }
        }
        // Fallback: return a new block item if the cup is empty
        return new ItemStack(this);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                              Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof CoffeeCupBlockEntity coffeeCupBlockEntity) {
                ItemStack coffeeStack = coffeeCupBlockEntity.getCoffeeStack();

                if (!coffeeStack.isEmpty()) {
                    // Try to give to player
                    if (!player.isCreative()) {
                        if (!player.addItem(coffeeStack.copy())) {
                            // Drop if inventory full
                            player.drop(coffeeStack.copy(), false);
                        }
                    }
                    // Remove block from world
                    coffeeCupBlockEntity.decreaseCoffeeStack(1);
                    level.removeBlock(pos, false);

                }
            } else {
                throw new IllegalStateException("Missing CoffeeCupBlockEntity!");
            }
        }

        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }
}