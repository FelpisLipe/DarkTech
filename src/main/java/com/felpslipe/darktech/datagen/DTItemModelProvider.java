package com.felpslipe.darktech.datagen;

import com.felpslipe.darktech.DarkTech;
import com.felpslipe.darktech.registry.DTFluids;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class DTItemModelProvider extends ItemModelProvider {
    public DTItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DarkTech.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(DTFluids.VOID_GAS_BUCKET.get());

    }
}
