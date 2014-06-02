package com.ilexiconn.jurassicraft.data.block;

import com.ilexiconn.jurassicraft.JurassiCraft;
import com.ilexiconn.jurassicraft.Util;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GhostBlock extends Block
{
    public int[] blocksToBreak;
    public int guiToOpen, guiId;
    public boolean openGui;

    public GhostBlock(String name, int[] blocks)
    {
        super(Material.cloth);
        setBlockName(name);
        setBlockTextureName(Util.getModId() + name);
        blocksToBreak = blocks;
        setCreativeTab(null);
    }

    public GhostBlock(String name, int[] blocks, int guiBlock, int guiID)
    {
        this(name, blocks);
        guiToOpen = guiBlock;
        guiId = guiID;
        openGui = true;
    }

    public GhostBlock(String name, int[] blocks, float x, float y, float z, float x1, float y1, float z1)
    {
        this(name, blocks);
        setBlockBounds(x, y, z, x1, y1, z1);
    }

    public GhostBlock(String name, int[] blocks, int guiBlock, int guiID, float x, float y, float z, float x1, float y1, float z1)
    {
        this(name, blocks, guiBlock, guiID);
        setBlockBounds(x, y, z, x1, y1, z1);
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
        return -1;
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int side)
    {
        for (int thing : blocksToBreak)
        {
            world.setBlockToAir(x, y + thing, z);
        }
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int o, float i, float d, float k)
    {
        if (!openGui) return false;
        player.openGui(JurassiCraft.instance, guiId, world, x, y + guiToOpen, z);
        return true;
    }
}
