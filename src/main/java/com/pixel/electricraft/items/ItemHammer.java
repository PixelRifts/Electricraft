package com.pixel.electricraft.items;

import com.pixel.electricraft.Electricraft;
import net.minecraft.block.BlockState;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.HashSet;

public class ItemHammer extends ToolItem {
    public ItemHammer() {
        super(4, 3, new HammerTier(), new HashSet<>(), new Properties().group(Electricraft.ITEM_GROUP));
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

    private static class HammerTier implements IItemTier {
        @Override
        public int getMaxUses() {
            return 1023;
        }

        @Override
        public float getEfficiency() {
            return 0;
        }

        @Override
        public float getAttackDamage() {
            return 4;
        }

        @Override
        public int getHarvestLevel() {
            return 0;
        }

        @Override
        public int getEnchantability() {
            return 3;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return Ingredient.fromTag(Tags.Items.INGOTS_IRON);
        }
    }
}
