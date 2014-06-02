package com.ilexiconn.jurassicraft.data.item;

import com.ilexiconn.jurassicraft.Util;
import net.minecraft.item.Item;

public class ItemDNA extends Item
{
    public ItemDNA(String name)
    {
        super();
        setUnlocalizedName("dna_" + name);
        setTextureName(Util.getModId() + "DNA_" + name);
        setCreativeTab(Util.getCreativeTab(0));
    }
}
