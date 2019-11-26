package me.modmuss50.svw;

import net.minecraft.structure.StructureManager;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;

public class VoidChunkGenerator extends ChunkGenerator<ChunkGeneratorConfig> {

	public VoidChunkGenerator(IWorld world, BiomeSource biomeSource, ChunkGeneratorConfig chunkGeneratorConfig) {
		super(world, biomeSource, chunkGeneratorConfig);
	}

	@Override
	public void buildSurface(ChunkRegion chunkRegion, Chunk chunk) {

	}

	@Override
	public int getSpawnHeight() {
		return 64;
	}

	@Override
	public void populateNoise(IWorld var1, Chunk var2) {

	}

	@Override
	public int getHeightOnGround(int i, int i1, Heightmap.Type type) {
		return 0;
	}

	@Override
	public void generateFeatures(ChunkRegion class_3233_1) {

	}

}
