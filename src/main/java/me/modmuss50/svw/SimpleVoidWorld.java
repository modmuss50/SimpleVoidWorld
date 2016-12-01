package me.modmuss50.svw;

import me.modmuss50.svw.blocks.BlockPortal;
import me.modmuss50.svw.world.VoidWorldProvider;
import net.minecraft.block.Block;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import reborncore.RebornRegistry;

@Mod(modid = "simplevoidworld", name = "SimpleVoidWorld", version = "@MODVERSION@")
public class SimpleVoidWorld {

	public static Block portal;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		Config.load(event.getSuggestedConfigurationFile());
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		portal = new BlockPortal();
		RebornRegistry.registerBlock(portal, "simplevoidworld:portal");

		DimensionType type = DimensionType.register("simplevoidworld", "void", Config.dimID, VoidWorldProvider.class, false);
		DimensionManager.registerDimension(Config.dimID, type);
	}
}
