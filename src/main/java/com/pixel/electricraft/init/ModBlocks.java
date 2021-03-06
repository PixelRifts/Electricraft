package com.pixel.electricraft.init;

import com.pixel.electricraft.Electricraft;
import com.pixel.electricraft.blocks.BlockMaterialGenerator;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class ModBlocks {
    private static Block material_generator;

    public static void registerAll(final RegistryEvent.Register<Block> e) {
        if (!e.getName().equals(ForgeRegistries.BLOCKS.getRegistryName())) return;

        for (MaterialType materialType : MaterialType.values()) {
            register( "block_" + materialType.getName(), materialType.getStorageBlock());
            register( "ore_" + materialType.getName(), materialType.getStorageOreBlock());
        }

        material_generator = register("material_generator", new BlockMaterialGenerator());
    }

    private static <T extends Block> T register(String name, T block) {
        BlockItem item = new BlockItem(block, new Item.Properties().group(Electricraft.ITEM_GROUP));
        return register(name, block, item);
    }

    private static <T extends Block> T register(String name, T block, @Nullable BlockItem item) {
        ResourceLocation id = Electricraft.getID(name);
        block.setRegistryName(id);
        ForgeRegistries.BLOCKS.register(block);
        if (item != null) {
            ModItems.BLOCKS_TO_REGISTER.put(name, item);
        }
        return block;
    }
}
