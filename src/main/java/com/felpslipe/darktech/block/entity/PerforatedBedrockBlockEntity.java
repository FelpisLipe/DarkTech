package com.felpslipe.darktech.block.entity;

import com.felpslipe.darktech.registry.DTBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.Nullable;

public class PerforatedBedrockBlockEntity extends BlockEntity {

    /*
    TODO: spilling when uncovered
          particle indicator
     */
    private final FluidTank FLUID_TANK = createFluidTank();

    private FluidTank createFluidTank() {
        return new FluidTank(64000) {
            @Override
            protected void onContentsChanged() {
                setChanged();
                if(!level.isClientSide()) {
                    level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                }
            }

            @Override
            public boolean isFluidValid(FluidStack stack) {
                return true;
            }
        };
    }



    public PerforatedBedrockBlockEntity(BlockPos pos, BlockState blockState) {
        super(DTBlockEntities.PERFORATED_BEDROCK_BE.get(), pos, blockState);
    }

    public FluidStack getFluid() {
        return FLUID_TANK.getFluid();
    }

    public IFluidHandler getTank(@Nullable Direction direction) {
        return FLUID_TANK;
    }


    public FluidTank getFTank() {
        return FLUID_TANK;
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        pushFluidToAboveNeighbor();
    }

    private void pushFluidToAboveNeighbor() {
        FluidUtil.getFluidHandler(level, worldPosition.above(), null).ifPresent(iFluidHandler -> {
            FluidUtil.tryFluidTransfer(iFluidHandler, this.FLUID_TANK, Integer.MAX_VALUE, true);
        });
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag = FLUID_TANK.writeToNBT(registries, tag);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        FLUID_TANK.readFromNBT(registries, tag);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookupProvider) {
        super.onDataPacket(net, pkt, lookupProvider);
    }
}
