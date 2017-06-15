package me.modmuss50.svw;

import me.modmuss50.svw.blocks.BlockPortal;
import me.modmuss50.svw.proxy.CommonProxy;
import me.modmuss50.svw.world.VoidWorldProvider;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import reborncore.RebornRegistry;
import reborncore.common.util.RebornCraftingHelper;

@Mod(modid = "simplevoidworld", name = "SimpleVoidWorld", version = "@MODVERSION@", dependencies = "required-after:reborncore")
public class SimpleVoidWorld {

	public static Block portal;
	public static VoidTab creativeTab;

	@SidedProxy(clientSide = "me.modmuss50.svw.proxy.ClientProxy", serverSide = "me.modmuss50.svw.proxy.CommonProxy")
	public static CommonProxy proxy;
	public static DimensionType type;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		Config.load(event.getSuggestedConfigurationFile());

		creativeTab = new VoidTab();
		portal = new BlockPortal();
		RebornRegistry.registerBlock(portal, "simplevoidworld:portal");

		proxy.init();

		type = DimensionType.register("simplevoidworld", "void", Config.dimID, VoidWorldProvider.class, false);
		DimensionManager.registerDimension(Config.dimID, type);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		RebornCraftingHelper.addShapedOreRecipe(new ItemStack(portal), "OEO", "EDE", "OEO", 'O',
			Blocks.OBSIDIAN, 'E', Items.ENDER_EYE, 'D', Blocks.DIAMOND_BLOCK);
	}

	public static class VoidTab extends CreativeTabs {

		public VoidTab() {
			super("simplevoidworld.creative.tab");
		}

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(portal);
		}
	}
}
