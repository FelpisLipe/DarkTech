package com.felpslipe.darktech.registry;

import com.felpslipe.darktech.DarkTech;
import com.felpslipe.darktech.fluid.BaseFluidType;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.joml.Vector3f;

import java.util.function.Supplier;

public class DTFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = ResourceLocation.parse("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = ResourceLocation.parse("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = ResourceLocation.parse("block/water_overlay");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, DarkTech.MOD_ID);

    public static final Supplier<FluidType> VOID_GAS_FLUID_TYPE= registerFluidType("void_gas_fluid",
            new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL, 0xFF000000,
                    new Vector3f(0.0f, 0.0f, 0.0f), FluidType.Properties.create()));

    private static Supplier<FluidType> registerFluidType(String name, FluidType fluidType) {
        return FLUID_TYPES.register(name, () -> fluidType);
    }


    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
