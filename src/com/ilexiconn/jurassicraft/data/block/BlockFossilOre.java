package com.ilexiconn.jurassicraft.data.block;

import com.ilexiconn.jurassicraft.Util;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class BlockFossilOre extends Block
{
    public BlockFossilOre()
    {
        super(Material.ground);
        setBlockName("fossil_ore");
        setBlockTextureName(Util.getModId() + "fossil_ore");
        setHardness(3.0F);
        setResistance(5.0F);
        setCreativeTab(Util.getCreativeTab(0));
        setStepSound(Block.soundTypeStone);
        setHarvestLevel("pickaxe", 0);
    }

    public Item getItemDropped(int value, Random random, int thing)
    {
        return Util.getItem(2);
    }

    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int h)
    {
        if (!world.isRemote)
        {
            ItemStack equippedByPlayer = player.getCurrentEquippedItem();
            if (equippedByPlayer != null && equippedByPlayer.getItem() instanceof ItemPickaxe && Enum.valueOf(Item.ToolMaterial.class, ((ItemPickaxe)equippedByPlayer.getItem()).getToolMaterialName()).getHarvestLevel() >= 2)
                super.harvestBlock(world, player, x, y, z, h);
        }
    }
}
