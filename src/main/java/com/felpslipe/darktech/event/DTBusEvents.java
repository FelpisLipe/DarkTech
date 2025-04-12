package com.felpslipe.darktech.event;

import com.felpslipe.darktech.DarkTech;
import com.felpslipe.darktech.block.entity.PerforatedBedrockBlockEntity;
import com.felpslipe.darktech.registry.DTBlockEntities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = DarkTech.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DTBusEvents {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, DTBlockEntities.PERFORATED_BEDROCK_BE.get(), PerforatedBedrockBlockEntity::getTank);
    }

}
