package com.pixel.electricraft.init;

import com.pixel.electricraft.Electricraft;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class ModBlocks {
    public static Block block_silver;

    public static void registerAll(final RegistryEvent.Register<Block> e) {
        if (!e.getName().equals(ForgeRegistries.BLOCKS.getRegistryName())) return;

        for (MaterialType materialType : MaterialType.values()) {
            if (materialType.getName().equals("silver")) {
                block_silver = register( "block_" + materialType.getName(), materialType.getStorageBlock());
                continue;
            }
            register( "block_" + materialType.getName(), materialType.getStorageBlock());
        }
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
