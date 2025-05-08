package com.felpslipe.darktech.datagen;

import com.felpslipe.darktech.DarkTech;
import com.felpslipe.darktech.registry.DTFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DTFluidTagProvider extends FluidTagsProvider {

    public DTFluidTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, DarkTech.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(FluidTags.WATER)
                .add(DTFluids.FLOWING_VOID_GAS.get())
                .add(DTFluids.VOID_GAS.get());
    }
}
