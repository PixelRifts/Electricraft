package com.pixel.electricraft.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.arguments.ItemInput;
import net.minecraft.command.arguments.ResourceLocationArgument;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;

public class SimpleGiveCommand {
    private static final SuggestionProvider<CommandSource> ITEM_ID_SUGGESTIONS = (context, builder) ->
            ISuggestionProvider.func_212476_a(ForgeRegistries.ITEMS.getKeys().stream(), builder);

    private SimpleGiveCommand() {
    }

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("sgive").requires(source -> source.hasPermissionLevel(2))
                .then(Commands.argument("targets", EntityArgument.players())
                        .then(Commands.argument("itemID", ResourceLocationArgument.resourceLocation())
                                .suggests(ITEM_ID_SUGGESTIONS)
                                .executes(context -> giveItem(
                                        context.getSource(),
                                        ResourceLocationArgument.getResourceLocation(context, "itemID"),
                                        EntityArgument.getPlayers(context, "targets"),
                                        1
                                ))
                                .then(Commands.argument("count", IntegerArgumentType.integer())
                                        .executes(context -> giveItem(
                                                context.getSource(),
                                                ResourceLocationArgument.getResourceLocation(context, "itemID"),
                                                EntityArgument.getPlayers(context, "targets"),
                                                IntegerArgumentType.getInteger(context, "count")
                                        ))
                                )
                        )
                )
        );
    }

    private static int giveItem(CommandSource source, ResourceLocation itemId, Collection<ServerPlayerEntity> targets, int count) {
        Item item = ForgeRegistries.ITEMS.getValue(itemId);
        if (item == null) {
            source.sendErrorMessage(new StringTextComponent("Item '" + itemId + "' does not exist?"));
            return 0;
        }

        for (ServerPlayerEntity player : targets) {
            int remainingCount = count;

            while (remainingCount > 0) {
                @SuppressWarnings("deprecation") int stackCount = Math.min(item.getMaxStackSize(), remainingCount);
                remainingCount -= stackCount;
                ItemStack stack = new ItemStack(item, stackCount);
                boolean addedToInventory = player.inventory.addItemStackToInventory(stack);
                if (addedToInventory && stack.isEmpty()) {
                    stack.setCount(1);
                    ItemEntity entityItem = player.dropItem(stack, false);
                    if (entityItem != null) {
                        entityItem.makeFakeItem();
                    }

                    player.world.playSound(
                            null, player.posX, player.posY, player.posZ,
                            SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS,
                            0.2F,
                            ((player.getRNG().nextFloat() - player.getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    player.container.detectAndSendChanges();
                } else {
                    ItemEntity entityItem = player.dropItem(stack, false);
                    if (entityItem != null) {
                        entityItem.setNoPickupDelay();
                        entityItem.setOwnerId(player.getUniqueID());
                    }
                }
            }
        }

        ITextComponent itemText = new ItemStack(item, count).getTextComponent();
        if (targets.size() == 1) {
            source.sendFeedback(new TranslationTextComponent("commands.give.success.single", count, itemText, targets.iterator().next().getDisplayName()), true);
        } else {
            source.sendFeedback(new TranslationTextComponent("commands.give.success.single", count, itemText, targets.size()), true);
        }

        return targets.size();
    }
}
