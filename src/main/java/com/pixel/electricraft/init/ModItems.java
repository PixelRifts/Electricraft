package com.pixel.electricraft.init;

import com.pixel.electricraft.Electricraft;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModItems {
    static final Map<String, BlockItem> BLOCKS_TO_REGISTER = new LinkedHashMap<>();

    public static void registerAll(final RegistryEvent.Register<Item> e) {
        if (!e.getName().equals(ForgeRegistries.ITEMS.getRegistryName())) return;
        BLOCKS_TO_REGISTER.forEach(ModItems::register);
    }

    private static <T extends Item> T register(String name, T item) {
        item.setRegistryName(Electricraft.getID(name));
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
