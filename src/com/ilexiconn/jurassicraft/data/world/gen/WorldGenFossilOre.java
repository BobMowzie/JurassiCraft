package com.ilexiconn.jurassicraft.data.world.gen;

import com.ilexiconn.jurassicraft.Util;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class WorldGenFossilOre implements IWorldGenerator
{
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.provider.dimensionId != 1 && world.provider.dimensionId != -1)
		{
			int x, y, z;
			
			for (int i = 0; i < 20; i++)
			{
				x = random.nextInt(16) + (chunkX * 16);
				y = random.nextInt(64);
				z = random.nextInt(16) + (chunkZ * 16);
				
				(new WorldGenMinable(Util.getBlock(5), 7 + random.nextInt(3))).generate(world, random, x, y, z);
			}
		}
	}
}
