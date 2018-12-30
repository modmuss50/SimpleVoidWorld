package me.modmuss50.svw;

import me.modmuss50.api.PlayerPlacementHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class VoidPlacementHandlerHandler implements PlayerPlacementHandler {
	@Override
	public boolean placeInPortal(Entity entity, ServerWorld previousWorld, ServerWorld newWorld) {
		if (previousWorld.dimension.getType() == SimpleVoidWorld.VOID_WORLD) {
			if (newWorld.dimension.getType() == DimensionType.OVERWORLD) {
				BlockPos spawnLocation = getBedLocation((PlayerEntity) entity, newWorld);
				if (spawnLocation == null) {
					spawnLocation = newWorld.method_8395();
				}

				setEntityLocation(entity, spawnLocation);
				return true;
			}
		}

		if (newWorld.dimension.getType() == SimpleVoidWorld.VOID_WORLD) {
			BlockPos spawnPos = new BlockPos(0, 100, 0);
			spawnVoidPlatform(newWorld, spawnPos.down());
			entity.setPositionAndAngles(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);
			return true;
		}

		return false;
	}

	public void spawnVoidPlatform(World world, BlockPos pos) {
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

	public static BlockPos getBedLocation(PlayerEntity player, ServerWorld world) {
		BlockPos bedLocation = player.getSpawnPosition();
		if (bedLocation == null) {
			return null;
		}
		//method_7288 = getBedSpawn
		BlockPos bedSpawnLocation = PlayerEntity.method_7288(world, bedLocation, false);
		return bedSpawnLocation;
	}
}
