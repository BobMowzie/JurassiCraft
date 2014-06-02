package com.ilexiconn.jurassicraft.data.block;

import com.ilexiconn.jurassicraft.Util;
import com.ilexiconn.jurassicraft.data.tile.TileCultivate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCultivate extends BlockContainer
{
    public boolean active;

    public BlockCultivate(boolean active)
    {
        super(Material.glass);
        this.active = active;
        setBlockName("cultivate_" + (active ? "active" : "idle"));
        setBlockTextureName(Util.getModId() + "cultivate");
        setCreativeTab(active ? null : Util.getCreativeTab(0));
        setHardness(1.0f);
        setBlockBounds(0.1f, 0.01f, 0.1f, 0.9f, 1.99f, 0.9f);
    }

    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new TileCultivate();
    }

    public static void updateCultivateBlockState(boolean isActive, World worldObj, int xCoord, int yCoord, int zCoord)
    {
        int metadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        TileEntity analyzerTileEntity = worldObj.getTileEntity(xCoord, yCoord, zCoord);

        if (analyzerTileEntity != null)
        {
            if (isActive)
            {
                worldObj.setBlockToAir(xCoord, yCoord + 1, zCoord);
                worldObj.setBlock(xCoord, yCoord, zCoord, Util.getBlock(0));
                worldObj.setBlock(xCoord, yCoord + 1, zCoord, Util.getBlock(6));
            }
            else
            {
                worldObj.setBlockToAir(xCoord, yCoord + 1, zCoord);
                worldObj.setBlock(xCoord, yCoord, zCoord, Util.getBlock(1));
                worldObj.setBlock(xCoord, yCoord + 1, zCoord, Util.getBlock(6));
            }


            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, metadata, 2);
            analyzerTileEntity.validate();
            worldObj.setTileEntity(xCoord, yCoord, zCoord, analyzerTileEntity);
        }
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return 0;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase livingBase, ItemStack itemStack)
    {
        world.setBlock(x, y + 1, z, Util.getBlock(6));
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int side)
    {
        world.setBlockToAir(x, y + 1, z);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float i, float d, float k)
    {
        updateCultivateBlockState(!active, world, x, y, z);
        return true;
    }
}
