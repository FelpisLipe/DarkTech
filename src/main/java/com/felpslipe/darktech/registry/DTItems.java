package com.felpslipe.darktech.registry;

import com.felpslipe.darktech.DarkTech;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DTItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DarkTech.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
