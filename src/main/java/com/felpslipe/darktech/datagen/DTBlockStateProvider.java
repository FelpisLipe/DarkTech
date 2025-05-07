package com.felpslipe.darktech.datagen;

import com.felpslipe.darktech.DarkTech;
import com.felpslipe.darktech.registry.DTBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class DTBlockStateProvider extends BlockStateProvider {
    public DTBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, DarkTech.MOD_ID, exFileHelper);
    }
        // Block shit
    @Override
    protected void registerStatesAndModels() {
        blockWithItem(DTBlocks.BROKEN_BEDROCK);
    }


    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("darktech:block/" + deferredBlock.getId().getPath()));
    }
    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("darktech:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
