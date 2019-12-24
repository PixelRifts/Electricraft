package com.pixel.electricraft.init;

import com.pixel.electricraft.Electricraft;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class ModBlocks {

    public static Block copper_block;

    public static void registerAll(final RegistryEvent.Register<Block> e) {
        if (!e.getName().equals(ForgeRegistries.BLOCKS.getRegistryName())) return;

        copper_block = register("block_copper",
                new Block(Block.Properties.create(Material.ROCK)
                        .hardnessAndResistance(2f, 7f)
                        .sound(SoundType.METAL)
                )
        );
    }

    private static <T extends Block> T register(String name, T block) {
        return register(name, block, new BlockItem(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    }

    private static <T extends Block> T register(String name, T block, @Nullable BlockItem item) {
        ResourceLocation id = Electricraft.getID(name);
        block.setRegistryName(Electricraft.MOD_ID, name );
        ForgeRegistries.BLOCKS.register(block);
        if (item != null) {
            ModItems.BLOCKS_TO_REGISTER.put(name, item);
        }
        return block;
    }
}
