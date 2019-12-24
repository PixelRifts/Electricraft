package com.pixel.electricraft;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.stream.Collectors;

@Mod(value = Electricraft.MOD_ID)
public class Electricraft {

    public static final String MOD_ID = "electricraft";
    public static final Logger LOGGER = LogManager.getLogger();

    public Electricraft() {
        DistExecutor.runForDist(
                () -> () -> new SideProxy.Client(),
                () -> () -> new SideProxy.Server()
        );
    }

    public static String getVersion() {
        Optional<? extends ModContainer> o = ModList.get().getModContainerById(MOD_ID);
        if (o.isPresent())
            return o.get().getModInfo().getVersion().toString();
        return "NONE";
    }

    public static boolean isDevBuild() {
        String version = getVersion();
        return "NONE".equals(version);
    }

    public static ResourceLocation getID(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
