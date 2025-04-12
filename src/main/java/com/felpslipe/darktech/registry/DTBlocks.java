package com.felpslipe.darktech.registry;

import com.felpslipe.darktech.DarkTech;
import com.felpslipe.darktech.block.PerforatedBedrockBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class DTBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(DarkTech.MOD_ID);

    public static final DeferredBlock<Block> PERFORATED_BEDROCK = registerBlock("perforated_bedrock",
            () -> new PerforatedBedrockBlock(BlockBehaviour.Properties.of()
                    .strength(-1)
                    .sound(SoundType.STONE)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        DTItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
