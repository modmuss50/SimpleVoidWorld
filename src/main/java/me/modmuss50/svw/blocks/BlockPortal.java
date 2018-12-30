package me.modmuss50.svw.blocks;

import me.modmuss50.svw.SimpleVoidWorld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class BlockPortal extends Block {

    public BlockPortal() {
        super(Settings.of(Material.AIR));
    }

    @Override
    public boolean activate(BlockState stateBlock, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, Direction facing, float v, float v1, float v2) {
        if (!world.isClient) {
            if (playerEntity.dimension == SimpleVoidWorld.VOID_WORLD) {
                playerEntity.changeDimension(DimensionType.OVERWORLD);
            } else {
                playerEntity.changeDimension(SimpleVoidWorld.VOID_WORLD);
            }
        }
        return true;
    }
}
