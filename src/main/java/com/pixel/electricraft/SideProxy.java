package com.pixel.electricraft;

import com.pixel.electricraft.commands.SimpleGiveCommand;
import com.pixel.electricraft.init.ModBlocks;
import com.pixel.electricraft.init.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class SideProxy {
    public SideProxy() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(SideProxy::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(SideProxy::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(SideProxy::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModBlocks::registerAll);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModItems::registerAll);

        MinecraftForge.EVENT_BUS.addListener(SideProxy::serverStarting);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static void commonSetup(final FMLCommonSetupEvent e) {
        Electricraft.LOGGER.debug("Common Setup for Electricraft");
    }

    public static void enqueueIMC(final InterModEnqueueEvent event) {

    }

    public static void processIMC(final InterModProcessEvent event) {

    }

    public static void serverStarting(FMLServerStartingEvent e) {
        Electricraft.LOGGER.debug("SERVER STARTING METHOD CALLED");
        SimpleGiveCommand.register(e.getCommandDispatcher());
    }

    static class Client extends SideProxy {

        public Client() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(Client::clientSetup);
        }

        public static void clientSetup(final FMLClientSetupEvent e) {

        }
    }

    static  class Server extends SideProxy {

        public Server() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(Server::serverSetup);
        }

        public static void serverSetup(final FMLDedicatedServerSetupEvent e) {

        }
    }
}
