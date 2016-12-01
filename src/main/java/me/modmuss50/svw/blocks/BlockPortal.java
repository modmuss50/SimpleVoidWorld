package me.modmuss50.svw.blocks;

import me.modmuss50.svw.Config;
import me.modmuss50.svw.world.WorldTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nullable;

/**
 * Created by modmuss50 on 01/12/16.
 */
public class BlockPortal extends Block {
	public BlockPortal() {
		super(Material.PORTAL);
		setCreativeTab(CreativeTabs.MISC);
		setUnlocalizedName("simplevoidworld:portal");
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand,
	                                @Nullable
		                                ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			if (worldIn.provider.getDimension() == 0) {
				FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn, Config.dimID, new WorldTeleporter(playerIn.getServer().worldServerForDimension(Config.dimID), pos));
			} else {
				FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn, 0, new WorldTeleporter(playerIn.getServer().worldServerForDimension(0), pos));
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
}
