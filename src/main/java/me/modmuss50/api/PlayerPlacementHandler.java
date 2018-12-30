package me.modmuss50.api;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public interface PlayerPlacementHandler {

	/**
	 *
	 * This is used to create a portal if needed, as well as set the player location in the new dim
	 *
	 * when you return true no other placement handlers will be called when the player is moving
	 *
	 * @param entity the entity that is traveling between 2 dims
	 * @param previousWorld the world that the entity is traveling from
	 * @param newWorld the world that the entity is traveling true
	 * @return if the placement handler handled this movement
	 */
	boolean placeInPortal(Entity entity, ServerWorld previousWorld, ServerWorld newWorld);

	//TODO move out of this?
	default void setEntityLocation(Entity entity, BlockPos pos){
		if (entity instanceof ServerPlayerEntity) {
			((ServerPlayerEntity)entity).networkHandler.method_14363(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
			((ServerPlayerEntity)entity).networkHandler.method_14372();
		} else {
			entity.setPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
		}
	}

}
