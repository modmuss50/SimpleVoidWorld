package me.modmuss50.svw.mixin;

import me.modmuss50.api.DimAPI;
import net.minecraft.class_3949;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.SecondaryServerWorld;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.world.ServerWorldListener;
import net.minecraft.util.profiler.DisableableProfiler;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.WorldSaveHandler;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.level.LevelInfo;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.concurrent.ExecutorService;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {
	@Shadow
	@Final
	private ExecutorService field_17200;

	@Shadow
	public abstract DisableableProfiler getProfiler();

	@Shadow
	public abstract ServerWorld getWorld(DimensionType dimensionType_1);

	@Shadow
	@Final
	private Map<DimensionType, ServerWorld> worlds;

	@Inject(method = "createWorlds", at = @At("RETURN"))
	private void createWorlds(WorldSaveHandler saveHandler, PersistentStateManager persistentStateManager, LevelProperties levelProperties, LevelInfo levelInfo, class_3949 newClass, CallbackInfo info) {
		for (DimensionType dimensionType : DimAPI.customDimenstions) {
			SecondaryServerWorld serverWorld = (new SecondaryServerWorld((MinecraftServer) (Object) this, field_17200, saveHandler, dimensionType, getWorld(DimensionType.OVERWORLD), getProfiler(), newClass)).initializeAsSecondaryWorld();
			worlds.put(dimensionType, serverWorld);
			serverWorld.registerListener(new ServerWorldListener((MinecraftServer) (Object) this, serverWorld));
		}

	}
}
