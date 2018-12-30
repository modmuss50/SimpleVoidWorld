package me.modmuss50.svw;

import me.modmuss50.api.DimAPI;
import me.modmuss50.dims.FabricDimenstionType;
import me.modmuss50.svw.blocks.BlockPortal;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class SimpleVoidWorld implements ModInitializer {

	public static DimensionType VOID_WORLD;

	public static BlockPortal PORTAL_BLOCK;

	public static ItemGroup SVW_GROUP = FabricItemGroupBuilder.build(new Identifier("simplevoidworld", "simplevoidworld"), new Supplier<ItemStack>() {
		@Override
		public ItemStack get() {
			return new ItemStack(PORTAL_BLOCK);
		}
	});

	public static ChunkGeneratorType<ChunkGeneratorSettings, VoidChunkGenerator> VOID_CHUNK_GENERATOR;

	@Override
	public void onInitialize() {
		initWorlds();
		initBlocks();

		SimpleVoidWorld.VOID_CHUNK_GENERATOR = new ChunkGeneratorTypeWorkaround().getChunkGeneratorType(ChunkGeneratorSettings::new);

		DimAPI.playerPlacementHandlerList.add(new VoidPlacementHandlerHandler());
		DimAPI.customDimenstions.add(VOID_WORLD);
	}

	public static void initWorlds() {
		VOID_WORLD = new FabricDimenstionType(new Identifier("simplevoidworld", "void"), 5, VoidDimension::new);
	}

	public void initBlocks() {
		PORTAL_BLOCK = new BlockPortal();
		Registry.register(Registry.BLOCK, new Identifier("simplevoidworld", "void_portal"), PORTAL_BLOCK);

		BlockItem blockItem = new BlockItem(PORTAL_BLOCK, new Item.Settings().itemGroup(SVW_GROUP));
		blockItem.registerBlockItemMap(Item.BLOCK_ITEM_MAP, blockItem);

		Registry.register(Registry.ITEM, new Identifier("simplevoidworld", "void_portal"), blockItem);
	}

	public static BlockState randomTerracotta() {
		Random random = new Random();

		List<BlockState> terracottaBlocks = new ArrayList<>();

		//TODO use tags for this?!
		Registry.BLOCK.forEach(block -> {
			if (Registry.BLOCK.getId(block).getPath().endsWith("_terracotta") && !Registry.BLOCK.getId(block).getPath().endsWith("_glazed_terracotta")) {
				terracottaBlocks.add(block.getDefaultState());
			}
		});

		return terracottaBlocks.get(random.nextInt(terracottaBlocks.size()));
	}

}
