package me.modmuss50.svw.world;

import me.modmuss50.svw.Config;
import me.modmuss50.svw.SimpleVoidWorld;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkGenerator;

import javax.annotation.Nullable;

/**
 * Created by modmuss50 on 01/12/16.
 */
public class VoidWorldProvider extends WorldProvider {
	@Override
	public DimensionType getDimensionType() {
		return SimpleVoidWorld.type;
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new VoidChunkGenerator(world);
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
		if (Config.darkSky) {
			return new Vec3d(0D, 0D, 0D);
		}
		return super.getFogColor(p_76562_1_, p_76562_2_);
	}

	@Override
	public Vec3d getSkyColor(Entity cameraEntity, float partialTicks) {
		if (Config.darkSky) {
			return new Vec3d(0D, 0D, 0D);
		}
		return super.getSkyColor(cameraEntity, partialTicks);
	}

	@Override
	public long getWorldTime() {
		if (Config.eternalDay) {
			return 6000;
		}
		return super.getWorldTime();
	}

	@Override
	public boolean isDaytime() {
		if (Config.eternalDay) {
			return true;
		}
		return super.isDaytime();
	}

	@Nullable
	@Override
	public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
		if (Config.darkSky) {
			return null;
		}
		return super.calcSunriseSunsetColors(celestialAngle, partialTicks);
	}


}
