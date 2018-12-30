package me.modmuss50.svw;

import net.minecraft.sortme.structures.StructureManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.BiomeSourceType;
import net.minecraft.world.biome.source.FixedBiomeSourceConfig;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPos;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

public class VoidDimension extends Dimension {
	public VoidDimension(World world, DimensionType type) {
		super(world, type);
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator() {
		return SimpleVoidWorld.VOID_CHUNK_GENERATOR.create(world, BiomeSourceType.FIXED.applyConfig(BiomeSourceType.FIXED.getConfig().method_8782(Biomes.PLAINS)), new ChunkGeneratorSettings());
	}

	@Override
	public BlockPos method_12452(ChunkPos chunkPos, boolean b) {
		return null;
	}

	@Override
	public BlockPos method_12444(int i, int i1, boolean b) {
		return null;
	}

	@Override
	public float getSkyAngle(long l, float v) {
		return 0;
	}

	@Override
	public boolean hasVisibleSky() {
		return false;
	}

	@Override
	public Vec3d method_12445(float v, float v1) {
		return new Vec3d(0, 0, 0);
	}

	@Override
	public boolean method_12448() {
		return false;
	}

	@Override
	public boolean method_12453(int i, int i1) {
		return false;
	}

	@Override
	public DimensionType getType() {
		return SimpleVoidWorld.VOID_WORLD;
	}

	@Override
	public BlockPos getForcedSpawnPoint() {
		return new BlockPos(0, 100, 0);
	}


}
