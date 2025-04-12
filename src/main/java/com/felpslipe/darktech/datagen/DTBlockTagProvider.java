package com.felpslipe.darktech.datagen;

import com.felpslipe.darktech.DarkTech;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DTBlockTagProvider extends BlockTagsProvider {
    public DTBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, DarkTech.MOD_ID, existingFileHelper);
    }
    // Block tags (duh)
    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }

}
