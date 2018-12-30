package me.modmuss50.svw;

import net.minecraft.class_3233;
import net.minecraft.sortme.structures.StructureManager;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;

public class VoidChunkGenerator extends ChunkGenerator<ChunkGeneratorSettings> {

	public VoidChunkGenerator(IWorld iWorld_1, BiomeSource biomeSource_1, ChunkGeneratorSettings chunkGeneratorSettings_1) {
		super(iWorld_1, biomeSource_1, chunkGeneratorSettings_1);
	}

	@Override
	public void buildSurface(Chunk var1) {

	}

	@Override
	public int method_12100() {
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
	public void method_16129(Chunk chunk_1, ChunkGenerator<?> chunkGenerator_1, StructureManager structureManager_1) {

	}

	@Override
	public void carve(Chunk chunk_1, GenerationStep.Carver generationStep$Carver_1) {

	}

	@Override
	protected Biome getDecorationBiome(Chunk chunk_1) {
		return Biomes.PLAINS;
	}

	@Override
	public void generateFeatures(class_3233 class_3233_1) {

	}

}
