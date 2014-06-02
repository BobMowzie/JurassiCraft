package com.ilexiconn.jurassicraft.data;

import com.ilexiconn.jurassicraft.Util;
import com.ilexiconn.jurassicraft.data.block.BlockAmberOre;
import com.ilexiconn.jurassicraft.data.block.BlockAnalyzer;
import com.ilexiconn.jurassicraft.data.block.BlockFossilOre;
import com.ilexiconn.jurassicraft.data.gui.GuiHandler;
import com.ilexiconn.jurassicraft.data.item.ItemAmber;
import com.ilexiconn.jurassicraft.data.item.ItemDNA;
import com.ilexiconn.jurassicraft.data.item.ItemFossil;
import com.ilexiconn.jurassicraft.data.tile.TileAnalyzer;
import com.ilexiconn.jurassicraft.data.world.gen.WorldGenAmberOre;
import com.ilexiconn.jurassicraft.data.world.gen.WorldGenFossilOre;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public final class Data extends Util
{
    public void init(FMLPreInitializationEvent event)
    {
        { /** Creativetabs */
            addCreativeTab(0, new CreativeTabs("fossilTab")
            {
                public Item getTabIconItem()
                {
                    return Items.arrow;
                }
            });
        }
        { /** Blocks */
            addBlockWithTileEntity(2, new BlockAnalyzer(true), TileAnalyzer.class, true);
            addBlockWithTileEntity(3, new BlockAnalyzer(false), TileAnalyzer.class, false);
            addBlock(4, new BlockAmberOre());
            addBlock(5, new BlockFossilOre());
        }
        { /** Items */
            addItem(1, new ItemAmber());
            addItem(2, new ItemFossil());
        }
        { /** DNAs */
            addDNA(new ItemDNA("base"));
        }
        { /** Entities */

        }
        { /** World Gens */
            addWorldGenerator(new WorldGenFossilOre(), 1);
            addWorldGenerator(new WorldGenAmberOre(), 2);
        }
        { /** Renderers */

        }
        { /** Other stuff */
            addGuiHandler(new GuiHandler());
        }
    }
}
