package me.modmuss50.svw;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class SimpleVoidWorld implements ModInitializer {

	public static FabricDimensionType VOID_WORLD;

	public static BlockPortal PORTAL_BLOCK;

	public static ItemGroup SVW_GROUP = FabricItemGroupBuilder.build(new Identifier("simplevoidworld", "simplevoidworld"), new Supplier<ItemStack>() {
		@Override
		public ItemStack get() {
			return new ItemStack(PORTAL_BLOCK);
		}
	});

	public static ChunkGeneratorType<ChunkGeneratorConfig, VoidChunkGenerator> VOID_CHUNK_GENERATOR;

	@Override
	public void onInitialize() {
		initWorlds();
		initBlocks();

		SimpleVoidWorld.VOID_CHUNK_GENERATOR = FabricChunkGeneratorType.register(new Identifier("simplevoidworld", "simplevoidworld"), VoidChunkGenerator::new, ChunkGeneratorConfig::new, false);
	}

	public static void initWorlds() {
		VOID_WORLD = FabricDimensionType.builder()
				.factory(VoidDimension::new)
				.skyLight(true)
				.defaultPlacer(VoidPlacementHandler.ENTERING)
				.buildAndRegister(new Identifier("simplevoidworld", "void"));
	}

	public void initBlocks() {
		PORTAL_BLOCK = new BlockPortal();
		Registry.register(Registry.BLOCK, new Identifier("simplevoidworld", "void_portal"), PORTAL_BLOCK);

		BlockItem blockItem = new BlockItem(PORTAL_BLOCK, new Item.Settings().group(SVW_GROUP));
		blockItem.appendBlocks(Item.BLOCK_ITEMS, blockItem);

		Registry.register(Registry.ITEM, new Identifier("simplevoidworld", "void_portal"), blockItem);
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
