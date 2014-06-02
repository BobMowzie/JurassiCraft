package com.ilexiconn.jurassicraft.data.tile;

import com.ilexiconn.jurassicraft.Util;
import com.ilexiconn.jurassicraft.data.block.BlockAnalyzer;
import com.ilexiconn.jurassicraft.data.item.ItemDNA;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.Random;

public class TileAnalyzer extends TileEntity implements ISidedInventory
{
    public static ArrayList<ItemDNA> dnas = new ArrayList<ItemDNA>();

    private static final int[] slots_top = new int[] {}; // input
    private static final int[] slots_bottom = new int[] {10, 11, 12}; // output
    private static final int[] slots_sides = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8}; // fuel

    private ItemStack[] analyzerItemStacks;
    public int analyzerBurnTime = 0;
    public int currentItemBurnTime = 100;
    public int analyzerCookTime = 0;
    private int RawIndex = -1;
    private int SpaceIndex = -1;

    public TileAnalyzer()
    {
        analyzerItemStacks = new ItemStack[13];

        dnas = Util.getDNAArray();
    }

    private static int getItemBurnTime()
    {
        return 100;
    }

    public static boolean isItemFuel()
    {
        return getItemBurnTime() > 0;
    }

    public int[] getAccessibleSlotsFromSide(int par1)
    {
        return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
    }

    public boolean canInsertItem(int var1, ItemStack var2, int var3)
    {
        return this.isItemValidForSlot(var1, var2);
    }

    public boolean canExtractItem(int var1, ItemStack var2, int var3)
    {
        return var3 != 0 || var1 != 1 || var2.getItem() == Items.bucket;
    }

    public int getSizeInventory()
    {
        return this.analyzerItemStacks.length;
    }

    public ItemStack getStackInSlot(int var1)
    {
        return this.analyzerItemStacks[var1];
    }

    public ItemStack decrStackSize(int slot, int decrAmount)
    {
        if (this.analyzerItemStacks[slot] != null)
        {
            ItemStack var3;
            if (this.analyzerItemStacks[slot].stackSize <= decrAmount)
            {
                var3 = this.analyzerItemStacks[slot];
                this.analyzerItemStacks[slot] = null;
                return var3;
            }
            else
            {
                var3 = this.analyzerItemStacks[slot].splitStack(decrAmount);
                if (this.analyzerItemStacks[slot].stackSize == 0)
                    this.analyzerItemStacks[slot] = null;
                return var3;
            }
        }
        else
            return null;
    }

    public ItemStack getStackInSlotOnClosing(int var1)
    {
        if (this.analyzerItemStacks[var1] != null)
        {
            ItemStack itemstack = this.analyzerItemStacks[var1];
            this.analyzerItemStacks[var1] = null;
            return itemstack;
        }
        else
            return null;
    }

    public void setInventorySlotContents(int var1, ItemStack var2)
    {
        this.analyzerItemStacks[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
            var2.stackSize = this.getInventoryStackLimit();
    }

    public String getInventoryName() {
        return "Analyzer";
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && var1.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    public void openInventory() { }

    public void closeInventory() { }

    public boolean isItemValidForSlot(int var1, ItemStack var2)
    {
        return var1 != 2 && (var1 != 1 || isItemFuel());
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.analyzerItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i <  nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound var4 = nbttaglist.getCompoundTagAt(i);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.analyzerItemStacks.length)
                this.analyzerItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
        }

        this.analyzerBurnTime = compound.getShort("BurnTime");
        this.analyzerCookTime = compound.getShort("CookTime");
        this.currentItemBurnTime = 100;
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("BurnTime", (short) this.analyzerBurnTime);
        compound.setShort("CookTime", (short) this.analyzerCookTime);
        NBTTagList tagList = new NBTTagList();

        for (int i = 0; i < this.analyzerItemStacks.length; i++)
        {
            if (this.analyzerItemStacks[i] != null)
            {
                NBTTagCompound slotCompound = new NBTTagCompound();
                slotCompound.setByte("Slot", (byte) i);
                this.analyzerItemStacks[i].writeToNBT(slotCompound);
                tagList.appendTag(slotCompound);
            }
        }

        compound.setTag("Items", tagList);
    }

    public int getCookPrograssScaled(int par1)
    {
        return this.analyzerCookTime * par1 / 200;
    }

    public boolean isBurning()
    {
        return this.analyzerBurnTime > 0;
    }

    public void updateEntity()
    {
        boolean var1 = this.analyzerBurnTime > 0;
        boolean var2 = false;

        if (this.analyzerBurnTime > 0)
            --this.analyzerBurnTime;

        if (!this.worldObj.isRemote)
        {
            if (this.analyzerBurnTime == 0 && this.canSmelt())
            {
                this.currentItemBurnTime = this.analyzerBurnTime = 100;

                if (this.analyzerBurnTime > 0)
                    var2 = true;
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.analyzerCookTime;

                if (this.analyzerCookTime == 200)
                {
                    this.analyzerCookTime = 0;
                    this.smeltItem();
                    var2 = true;
                }
            }
            else
                this.analyzerCookTime = 0;

            if (var1 != this.analyzerBurnTime > 0)
            {
                var2 = true;
                BlockAnalyzer.updateAnalyzerBlockState(this.analyzerBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (var2)
            this.updateEntity();
    }

    private boolean canSmelt()
    {
        this.SpaceIndex = -1;
        this.RawIndex = -1;

        int var1;

        for (var1 = 0; var1 < 9; ++var1)
        {
            if (this.analyzerItemStacks[var1] != null)
            {
                Item var2 = this.analyzerItemStacks[var1].getItem();

                if (var2 == Util.getItem(2) || var2 == Util.getItem(1))
                {
                    this.RawIndex = var1;
                    break;
                }
            }
        }

        if (this.RawIndex == -1)
            return false;
        else
        {
            for (var1 = 12; var1 > 8; --var1)
            {
                if (this.analyzerItemStacks[var1] == null)
                {
                    this.SpaceIndex = var1;
                    break;
                }
            }

            return this.SpaceIndex != -1 && this.RawIndex != -1;
        }
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack var1 = null;

            if (this.analyzerItemStacks[this.RawIndex].getItem() == Util.getItem(2))
            {
                int dnaResult = (new Random()).nextInt(99);

                if (dnaResult > 74)
                    var1 = new ItemStack(this.getRandomDNA(new Random()));
                else
                {
                    int output = (new Random()).nextInt(3);

                    if (output == 0)
                        var1 = new ItemStack(Blocks.sand, 3);
                    else if (output == 1)
                        var1 = new ItemStack(Blocks.stone, 1);
                    else if (output == 2)
                        var1 = new ItemStack(Items.bone, 2);
                }
            }

            if (this.analyzerItemStacks[this.RawIndex].getItem() == Util.getItem(1))
                var1 = new ItemStack(this.getRandomDNA(new Random()));

            if (var1 != null)
            {
                if (var1.stackSize != 0 && this.analyzerItemStacks[this.SpaceIndex] == null)
                    this.analyzerItemStacks[this.SpaceIndex] = var1.copy();

                -- this.analyzerItemStacks[this.RawIndex].stackSize;

                if (this.analyzerItemStacks[this.RawIndex].stackSize == 0)
                    this.analyzerItemStacks[this.RawIndex] = null;
            }
        }
    }

    private Item getRandomDNA(Random rand)
    {
        int dna = rand.nextInt(dnas.size());

        return dnas.get(dna);
    }
}
