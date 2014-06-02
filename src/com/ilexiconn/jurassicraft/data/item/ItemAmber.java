package com.ilexiconn.jurassicraft.data.item;

import com.ilexiconn.jurassicraft.Util;
import net.minecraft.item.Item;

public class ItemAmber extends Item
{
    public ItemAmber()
    {
        super();
        setUnlocalizedName("amber");
        setTextureName(Util.getModId() + "Amber");
        setCreativeTab(Util.getCreativeTab(0));
    }
}
