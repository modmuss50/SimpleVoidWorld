package me.modmuss50.svw.world;

import me.modmuss50.svw.Config;
import me.modmuss50.svw.SimpleVoidWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

/**
 * Created by modmuss50 on 01/12/16.
 */
public class WorldTeleporter extends Teleporter {

	BlockPos pos;
	WorldServer world;

	public WorldTeleporter(WorldServer worldIn, BlockPos pos) {
		super(worldIn);
		this.pos = pos;
		world = worldIn;
	}

	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) {
		if (world.provider.getDimension() == 0 && entityIn instanceof EntityPlayer) {
			BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(pos.getX(), 0, pos.getZ());
			for (int y = 0; y < 256; y++) {
				mutableBlockPos.setY(y);
				if (world.getBlockState(mutableBlockPos).getBlock() == SimpleVoidWorld.portal) {
					pos = new BlockPos(pos.getX(), y + 1, pos.getZ());
					break;
				}
			}
			pos = new BlockPos(pos.getX(), world.getTopSolidOrLiquidBlock(pos).getY() + 1, pos.getZ());
		}
		if (world.provider.getDimension() == Config.dimID) {
			//TODO look around for a free space so it doesnt place in a base?
			pos = new BlockPos(pos.getX(), 64, pos.getZ());
			if (world.getBlockState(pos).getBlock() != SimpleVoidWorld.portal) {
				for (int x = -3; x < 4; x++) {
					for (int z = -3; z < 4; z++) {
						if (world.isAirBlock(pos.add(x, 0, z))) {
							world.setBlockState(pos.add(x, 0, z), Blocks.STAINED_HARDENED_CLAY.getDefaultState());
						}

					}
				}
				world.setBlockState(pos, SimpleVoidWorld.portal.getDefaultState());
				world.setBlockState(pos.up(), Blocks.TORCH.getDefaultState());
			}
		}

		entityIn.setLocationAndAngles((double) pos.getX() + 0.5, (double) pos.getY() + 1, (double) pos.getZ() + 0.5, entityIn.rotationYaw, 0.0F);
		entityIn.motionX = 0.0D;
		entityIn.motionY = 0.0D;
		entityIn.motionZ = 0.0D;

	}
}
