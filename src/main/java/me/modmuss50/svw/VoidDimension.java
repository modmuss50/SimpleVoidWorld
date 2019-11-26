package me.modmuss50.svw;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.BiomeSourceType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class VoidDimension extends Dimension {


	public VoidDimension(World world, DimensionType type) {
		super(world, type, 0.0F);
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator() {
		return SimpleVoidWorld.VOID_CHUNK_GENERATOR.create(world,
				BiomeSourceType.FIXED.applyConfig(BiomeSourceType.FIXED.getConfig(world.getLevelProperties())
						.setBiome(Biomes.PLAINS)),
				SimpleVoidWorld.VOID_CHUNK_GENERATOR.createSettings());
	}

	@Override
	public BlockPos getSpawningBlockInChunk(ChunkPos chunkPos, boolean b) {
		return null;
	}

	@Override
	public BlockPos getTopSpawningBlockPosition(int i, int i1, boolean b) {
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
	public Vec3d getFogColor(float v, float v1) {
		return new Vec3d(0, 0, 0);
	}

	@Override
	public boolean canPlayersSleep() {
		return false;
	}

	@Override
	public boolean isFogThick(int x, int z) {
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
