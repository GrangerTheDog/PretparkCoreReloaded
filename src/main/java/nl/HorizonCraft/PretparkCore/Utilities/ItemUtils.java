/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 HorizonCraft
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * In addition to the above:
 * All content in the repo/plugin is created by and owned by HorizonCraft, unless
 * stated otherwise. All content that is not created by us will be placed in their
 * original package, where they were found or that was set by the owner by default.
 *
 * You are free to use the code anywhere you like, but we will not provide ANY support
 * unless you are on our server using this plugin.
 */

package nl.HorizonCraft.PretparkCore.Utilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * This class has been created on 09/12/2015 at 9:49 AM by Cooltimmetje.
 */
public class ItemUtils {

    public static void createDisplay(Player p, int slot, Material m, int amount, int data, String name, String... lore){
        ItemStack item = new ItemStack(m, amount, (byte) data);
        ItemMeta itemMeta = item.getItemMeta();
        if (name != null) {
            itemMeta.setDisplayName(MiscUtils.color(name));
        }
        ArrayList<String> loreList = new ArrayList<>();
        for(String s : lore){
            loreList.add(MiscUtils.color(s));
        }
        itemMeta.setLore(loreList);
        item.setItemMeta(itemMeta);
        p.getInventory().setItem(slot - 1, item);
    }

    public static void createDisplay(Player p, int slot, Material m, int amount, int data, String name){
        ItemStack item = new ItemStack(m, amount, (byte) data);
        ItemMeta itemMeta = item.getItemMeta();
        if (name != null) {
            itemMeta.setDisplayName(MiscUtils.color(name));
        }
        item.setItemMeta(itemMeta);
        p.getInventory().setItem(slot - 1, item);
    }

    public static void createDisplay(Player p, ItemStack is, int slot){
        p.getInventory().setItem(slot - 1, is);
    }

    public static void createDisplay(Inventory inv, int slot, Material m, int amount, int data, String name){
        ItemStack item = new ItemStack(m, amount,(byte) data);
        ItemMeta itemMeta = item.getItemMeta();
        if(!(name == null)){
            itemMeta.setDisplayName(MiscUtils.color(name));
        }
        item.setItemMeta(itemMeta);
        inv.setItem(slot - 1, item);
    }

    public static void createDisplay(ItemStack is, Inventory inv, int slot){
        inv.setItem(slot - 1, is);
    }

    public static void createDisplay(Inventory inv, int slot, Material m, int amount, int data, String name, String... lore){
        ItemStack item = new ItemStack(m, amount,(byte) data);
        ItemMeta itemMeta = item.getItemMeta();
        if(!(name == null)){
            itemMeta.setDisplayName(MiscUtils.color(name));
        }
        ArrayList<String> loreList = new ArrayList<>();
        for(String s : lore){
            loreList.add(MiscUtils.color(s));
        }
        itemMeta.setLore(loreList);
        item.setItemMeta(itemMeta);
        inv.setItem(slot - 1, item);
    }

    public static ItemStack createItemstack(Material m, int amount, int data, String name, String... lore){
        ItemStack item = new ItemStack(m, amount, (byte) data);
        ItemMeta itemMeta = item.getItemMeta();
        if (name != null) {
            itemMeta.setDisplayName(MiscUtils.color(name));
        }
        ArrayList<String> loreList = new ArrayList<>();
        for(String s : lore){
            loreList.add(MiscUtils.color(s));
        }
        itemMeta.setLore(loreList);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack createItemstack(Material m, int amount, byte data, String name){
        ItemStack item = new ItemStack(m, amount, data);
        ItemMeta itemMeta = item.getItemMeta();
        if (name != null) {
            itemMeta.setDisplayName(MiscUtils.color(name));
        }
        item.setItemMeta(itemMeta);
        return item;
    }




}
