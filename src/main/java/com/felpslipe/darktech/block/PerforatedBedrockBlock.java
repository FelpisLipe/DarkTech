package com.felpslipe.darktech.block;

import com.felpslipe.darktech.block.entity.PerforatedBedrockBlockEntity;
import com.felpslipe.darktech.registry.DTBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.Nullable;

public class PerforatedBedrockBlock extends BaseEntityBlock {
    public PerforatedBedrockBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PerforatedBedrockBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if(!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if(blockEntity instanceof PerforatedBedrockBlockEntity perforatedBedrockBlockEntity) {
                perforatedBedrockBlockEntity.getFTank().fill(new FluidStack(Fluids.WATER, 64000), IFluidHandler.FluidAction.EXECUTE);
            }
        }
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if(level.isClientSide()) {
            return null;
        }

        return createTickerHelper(blockEntityType, DTBlockEntities.PERFORATED_BEDROCK_BE.get(),
                (level1, pos, state1, perforatedBedrockBlockEntity) -> perforatedBedrockBlockEntity.tick(level1, pos, state1));
    }
}
