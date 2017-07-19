package me.modmuss50.svw.proxy;

import me.modmuss50.svw.SimpleVoidWorld;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {

	@Override
	public void init() {
		super.init();
		registerItemModel(Item.getItemFromBlock(SimpleVoidWorld.portal), 0);
	}

	static void registerItemModel(Item item, int meta) {
		ResourceLocation loc = item.getRegistryName();
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(loc, "inventory"));
	}

}
