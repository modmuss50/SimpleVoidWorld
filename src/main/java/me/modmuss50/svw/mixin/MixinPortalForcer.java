package me.modmuss50.svw.mixin;

import me.modmuss50.svw.SimpleVoidWorld;
import me.modmuss50.svw.VoidPlacementHandlerHandler;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PortalForcer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PortalForcer.class)
public class MixinPortalForcer {

	@Shadow @Final private ServerWorld world;

	@Inject(method = "usePortal", at = @At("HEAD"), cancellable = true)
	public void method_8653(Entity entity, float float_1, CallbackInfoReturnable<Boolean> infoReturnable) {
		//If going to the void world
		if(world.getDimension().getType() == SimpleVoidWorld.VOID_WORLD){
			VoidPlacementHandlerHandler.enterVoid(entity, (ServerWorld) entity.getEntityWorld(), world);
			infoReturnable.setReturnValue(true);
			infoReturnable.cancel();
		}

		//Coming from the void world
		if(entity.getEntityWorld().getDimension().getType() == SimpleVoidWorld.VOID_WORLD){
			VoidPlacementHandlerHandler.leaveVoid(entity, (ServerWorld) entity.getEntityWorld(), world);
			infoReturnable.setReturnValue(true);
			infoReturnable.cancel();
		}
	}



}
