package com.pixel.electricraft.init;

import com.pixel.electricraft.Electricraft;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.LazyLoadBase;

import java.util.Locale;

public enum MaterialType {
    COPPER,
    SILVER;

    private final LazyLoadBase<Block> storageBlock;
    private final LazyLoadBase<Block> storageOreBlock;
    private final LazyLoadBase<Item> storageItem;

    MaterialType() {
        storageBlock = new LazyLoadBase<>(() -> new Block(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(5, 6)
                .sound(SoundType.METAL)
        ));
        storageOreBlock = new LazyLoadBase<>(() -> new Block(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(5, 6)
                .sound(SoundType.METAL)
        ));
        storageItem = new LazyLoadBase<>(() -> new Item(new Item.Properties().group(Electricraft.ITEM_GROUP)));
    }

    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public Block getStorageBlock() {
        return storageBlock.getValue();
    }

    public Block getStorageOreBlock() {
        return storageOreBlock.getValue();
    }

    public Item getStorageItem() {
        return storageItem.getValue();
    }
}
