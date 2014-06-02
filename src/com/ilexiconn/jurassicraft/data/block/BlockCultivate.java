package com.ilexiconn.jurassicraft.data.block;

import com.ilexiconn.jurassicraft.JurassiCraft;
import com.ilexiconn.jurassicraft.Util;
import com.ilexiconn.jurassicraft.data.tile.TileCultivate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCultivate extends BlockContainer
{
    public BlockCultivate()
    {
        super(Material.glass);
        setBlockName("cultivate");
        setBlockTextureName(Util.getModId() + "cultivate");
        setHardness(1.5f);
        setCreativeTab(null);
        setBlockBounds(0f, 0f, 0f, 1f, 2f, 1f);
    }

    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new TileCultivate();
    }

    public int getRenderType()
    {
        return -1;
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int side)
    {
        super.breakBlock(world, x, y ,z, block, side);
        world.setBlockToAir(x, y + 1, z);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int o, float i, float d, float k)
    {
        player.openGui(JurassiCraft.instance, 0, world, x, y, z);
        return true;
    }
}
