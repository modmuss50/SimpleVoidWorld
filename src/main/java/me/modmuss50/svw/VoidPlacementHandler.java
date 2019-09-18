package me.modmuss50.svw;

import net.fabricmc.fabric.api.dimension.v1.EntityPlacer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Optional;

public class VoidPlacementHandler {

	public static final EntityPlacer ENTERING = (teleported, destination, portalDir, horizontalOffset, verticalOffset) -> {
		BlockPos pos = enterVoid(teleported, (ServerWorld) teleported.getEntityWorld(), destination);
		return new BlockPattern.TeleportTarget(new Vec3d(pos), Vec3d.ZERO, 0);
	};

	public static final EntityPlacer LEAVING = (teleported, destination, portalDir, horizontalOffset, verticalOffset) -> {
		BlockPos pos = leaveVoid(teleported, (ServerWorld) teleported.getEntityWorld(), destination);
		return new BlockPattern.TeleportTarget(new Vec3d(pos), Vec3d.ZERO, 0);
	};

	private static BlockPos enterVoid(Entity entity, ServerWorld previousWorld, ServerWorld newWorld) {
		BlockPos spawnPos = new BlockPos(0, 100, 0);
		spawnVoidPlatform(newWorld, spawnPos.down());
		entity.setPositionAndAngles(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);

		return spawnPos;
	}

	private static BlockPos leaveVoid(Entity entity, ServerWorld previousWorld, ServerWorld newWorld) {
		BlockPos spawnLocation = getBedLocation((PlayerEntity) entity, newWorld);
		if (spawnLocation == null) {
			spawnLocation = newWorld.getSpawnPos();
		}

		return spawnLocation;
	}

	private static void spawnVoidPlatform(World world, BlockPos pos) {
		if (world.getBlockState(pos).getBlock() != SimpleVoidWorld.PORTAL_BLOCK) {
			BlockState platformBlock = SimpleVoidWorld.randomTerracotta();
			for (int x = -3; x < 4; x++) {
				for (int z = -3; z < 4; z++) {
					if (world.isAir(pos.add(x, 0, z))) {
						world.setBlockState(pos.add(x, 0, z), platformBlock);
					}

				}
			}
			world.setBlockState(pos, SimpleVoidWorld.PORTAL_BLOCK.getDefaultState());
			for (Direction facing : Direction.values()) {
				if (facing.getAxis().isHorizontal()) {
					world.setBlockState(pos.up().offset(facing), Blocks.TORCH.getDefaultState());
				}
			}

		}
	}

	private static BlockPos getBedLocation(PlayerEntity player, ServerWorld world) {
		BlockPos bedLocation = player.getSpawnPosition();
		if (bedLocation == null) {
			return null;
		}
		//method_7288 = getBedSpawn
		Optional<Vec3d> bedSpawnLocation = PlayerEntity.method_7288(world, bedLocation, false);
		BlockPos pos = null;
		if (bedSpawnLocation.isPresent()) {
			pos = new BlockPos(bedSpawnLocation.get());
		}
		return pos;
	}

}
