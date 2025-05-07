package com.felpslipe.darktech.registry;

import com.felpslipe.darktech.DarkTech;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class DTParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, DarkTech.MOD_ID);

    public static final Supplier<SimpleParticleType> VOID_GAS_PARTICLES = PARTICLE_TYPES.register("void_gas_particles", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
