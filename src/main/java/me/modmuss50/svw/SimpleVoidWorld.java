package me.modmuss50.svw;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleVoidWorld implements ModInitializer {
	public static BlockPortal PORTAL_BLOCK;
	public static ItemGroup SVW_GROUP = FabricItemGroupBuilder.build(new Identifier("simplevoidworld", "simplevoidworld"), () -> new ItemStack(PORTAL_BLOCK));
	public static final RegistryKey<World> VOID_WORLD = RegistryKey.of(Registry.DIMENSION, new Identifier("simplevoidworld", "void"));

	@Override
	public void onInitialize() {
		PORTAL_BLOCK = new BlockPortal();
		Registry.register(Registry.BLOCK, new Identifier("simplevoidworld", "void_portal"), PORTAL_BLOCK);

		BlockItem blockItem = new BlockItem(PORTAL_BLOCK, new Item.Settings().group(SVW_GROUP));
		blockItem.appendBlocks(Item.BLOCK_ITEMS, blockItem);

		Registry.register(Registry.ITEM, new Identifier("simplevoidworld", "void_portal"), blockItem);

		Registry.register(Registry.CHUNK_GENERATOR, new Identifier("simplevoidworld", "void"), VoidChunkGenerator.CODEC);
	}

	public static BlockState randomTerracotta() {
		Random random = new Random();

		List<BlockState> terracottaBlocks = new ArrayList<>();
		Registry.BLOCK.forEach(block -> {
			if (Registry.BLOCK.getId(block).getPath().endsWith("_terracotta") && !Registry.BLOCK.getId(block).getPath().endsWith("_glazed_terracotta")) {
				terracottaBlocks.add(block.getDefaultState());
			}
		});

		return terracottaBlocks.get(random.nextInt(terracottaBlocks.size()));
	}

}
