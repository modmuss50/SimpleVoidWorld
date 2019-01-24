package me.modmuss50.svw;

import net.minecraft.sortme.structures.StructureManager;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;

public class VoidChunkGenerator extends ChunkGenerator<ChunkGeneratorConfig> {

	public VoidChunkGenerator(IWorld iWorld_1, BiomeSource biomeSource_1, ChunkGeneratorConfig chunkGeneratorSettings_1) {
		super(iWorld_1, biomeSource_1, chunkGeneratorSettings_1);
	}

	@Override
	public void buildSurface(Chunk var1) {

	}

	@Override
	public int getSpawnHeight() {
		return 64;
	}

	@Override
	public void populateNoise(IWorld var1, Chunk var2) {

	}

	@Override
	public int produceHeight(int var1, int var2, Heightmap.Type var3) {
		return 0;
	}

	@Override
	public void setStructureStarts(Chunk chunk_1, ChunkGenerator<?> chunkGenerator_1, StructureManager structureManager_1) {

	}

	@Override
	public void carve(Chunk chunk_1, GenerationStep.Carver generationStep$Carver_1) {

	}

	@Override
	protected Biome getDecorationBiome(Chunk chunk_1) {
		return Biomes.PLAINS;
	}

	@Override
	public void generateFeatures(ChunkRegion class_3233_1) {

	}

}
