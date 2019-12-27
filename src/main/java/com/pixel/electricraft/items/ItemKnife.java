package com.pixel.electricraft.items;

import com.pixel.electricraft.Electricraft;
import net.minecraft.block.BlockState;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.HashSet;

public class ItemKnife extends ToolItem {
    public ItemKnife() {
        super(0.5f, 1, new KnifeTier(), new HashSet<>(), new Item.Properties().group(Electricraft.ITEM_GROUP));
    }

    @Override
    public boolean canHarvestBlock(BlockState state) {
        return false;
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack stackToRepair, ItemStack material) {
        return material.getTag().equals(Tags.Items.INGOTS_IRON);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    private static class KnifeTier implements IItemTier {
        @Override
        public int getMaxUses() {
            return 255;
        }

        @Override
        public float getEfficiency() {
            return 0;
        }

        @Override
        public float getAttackDamage() {
            return 1;
        }

        @Override
        public int getHarvestLevel() {
            return 0;
        }

        @Override
        public int getEnchantability() {
            return 2;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return Ingredient.fromTag(Tags.Items.INGOTS_IRON);
        }
    }
}
