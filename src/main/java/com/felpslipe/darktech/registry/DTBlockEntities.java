package com.felpslipe.darktech.registry;

import com.felpslipe.darktech.DarkTech;
import com.felpslipe.darktech.block.entity.BrokenBedrockBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class DTBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, DarkTech.MOD_ID);

    public static final Supplier<BlockEntityType<BrokenBedrockBlockEntity>> BROKEN_BEDROCK_BE = BLOCK_ENTITIES.register("broken_bedrock_be",
            () -> BlockEntityType.Builder.of(BrokenBedrockBlockEntity::new, DTBlocks.BROKEN_BEDROCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
