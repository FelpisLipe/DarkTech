package com.felpslipe.darktech.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class VoidGasParticles extends TextureSheetParticle {

    protected VoidGasParticles(ClientLevel level, SpriteSet spriteSet, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);

        this.lifetime = 20;
        this.setSpriteFromAge(spriteSet);


        this.gravity = 0.25f;
        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
    }






    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new VoidGasParticles(level, this.spriteSet, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}
