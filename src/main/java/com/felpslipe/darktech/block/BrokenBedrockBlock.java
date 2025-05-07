package com.felpslipe.darktech.block;

import com.felpslipe.darktech.block.entity.BrokenBedrockBlockEntity;
import com.felpslipe.darktech.registry.DTBlockEntities;
import com.felpslipe.darktech.registry.DTParticles;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.Nullable;

public class BrokenBedrockBlock extends BaseEntityBlock {
    public BrokenBedrockBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BrokenBedrockBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if(!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if(blockEntity instanceof BrokenBedrockBlockEntity brokenBedrockBlockEntity) {
                brokenBedrockBlockEntity.getTank(null).fill(new FluidStack(Fluids.WATER, 64000), IFluidHandler.FluidAction.EXECUTE);
            }
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if(blockEntity instanceof BrokenBedrockBlockEntity brokenBedrockBlockEntity) {
            if(!brokenBedrockBlockEntity.getFluid().isEmpty() && level.isEmptyBlock(pos.above())) {
                    level.playLocalSound((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5,
                            SoundEvents.WEATHER_RAIN, SoundSource.BLOCKS, random.nextFloat() * 0.25F + 0.25F, 2F, false);
                level.addParticle(
                        DTParticles.VOID_GAS_PARTICLES.get(), (double)pos.getX() + 0.5, (double)pos.getY() + 1.2, (double)pos.getZ() + 0.5, 0.5, 1.0, 0.0);
                level.addParticle(
                        DTParticles.VOID_GAS_PARTICLES.get(), (double)pos.getX() + 0.5, (double)pos.getY() + 1.2, (double)pos.getZ() + 0.5, -0.5, 1.0, 0.0);
                level.addParticle(
                        DTParticles.VOID_GAS_PARTICLES.get(), (double)pos.getX() + 0.5, (double)pos.getY() + 1.2, (double)pos.getZ() + 0.5, 0.0, 1.0, 0.5);
                level.addParticle(
                        DTParticles.VOID_GAS_PARTICLES.get(), (double)pos.getX() + 0.5, (double)pos.getY() + 1.2, (double)pos.getZ() + 0.5, 0.5, 1.0, -0.5);

            }
        }
    }


    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if(level.isClientSide()) {
            return null;
        }

        return createTickerHelper(blockEntityType, DTBlockEntities.BROKEN_BEDROCK_BE.get(),
                (level1, pos, state1, brokenBedrockBlockEntity) -> brokenBedrockBlockEntity.tick(level1, pos, state1));
    }
}
