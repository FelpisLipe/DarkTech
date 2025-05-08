package com.felpslipe.darktech.registry;

import com.felpslipe.darktech.DarkTech;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class DTFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, DarkTech.MOD_ID);

    public static final Supplier<FlowingFluid> VOID_GAS = FLUIDS.register("void_gas",
            () -> new BaseFlowingFluid.Source(DTFluids.VOID_GAS_PROPERTIES));
    public static final Supplier<FlowingFluid> FLOWING_VOID_GAS = FLUIDS.register("flowing_void_gas",
            () -> new BaseFlowingFluid.Flowing(DTFluids.VOID_GAS_PROPERTIES));

    public static final DeferredBlock<LiquidBlock> VOID_GAS_BLOCK = DTBlocks.BLOCKS.register("void_gas_block",
            () -> new LiquidBlock(DTFluids.VOID_GAS.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredItem<Item> VOID_GAS_BUCKET = DTItems.ITEMS.registerItem("void_gas_bucket",
            properties -> new BucketItem(DTFluids.VOID_GAS.get(), properties.craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final BaseFlowingFluid.Properties VOID_GAS_PROPERTIES = new BaseFlowingFluid.Properties(DTFluidTypes.VOID_GAS_FLUID_TYPE, VOID_GAS, FLOWING_VOID_GAS)
            .slopeFindDistance(2).levelDecreasePerBlock(1)
            .block(DTFluids.VOID_GAS_BLOCK).bucket(DTFluids.VOID_GAS_BUCKET);



    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
