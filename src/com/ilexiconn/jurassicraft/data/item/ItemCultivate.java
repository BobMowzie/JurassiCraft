package com.ilexiconn.jurassicraft.data.item;

import com.ilexiconn.jurassicraft.Util;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCultivate extends Item
{
    public ItemCultivate()
    {
        setUnlocalizedName("cultivate_placer");
        setTextureName(Util.getModId() + "cultivate_placer");
        setCreativeTab(Util.getCreativeTab(0));
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float i, float d, float k)
    {
        if (!world.isRemote)
        {
            if (side == 1 && world.getBlock(x, y + 1, z) == Block.getBlockById(0) && world.getBlock(x, y + 2, z) == Block.getBlockById(0))
            {
                world.setBlock(x, y + 1, z, Util.getBlock(0));
                world.setBlock(x, y + 2, z, Util.getBlock(1));

                return true;
            }
        }
        return false;
    }
}
