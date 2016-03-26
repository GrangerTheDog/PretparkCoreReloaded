/*
 * Copyright (c) 2015-2016 Tim Medema
 *
 * This plugin has no licence on it. But that DOESN'T mean you can use it.
 * See the COPYRIGHT.txt for in the root for more information.
 *
 * You are allowed to:
 * - Read the code, and use it for educational purposes.
 * - Ask me questions about how this plugin works and what some of the components do.
 *
 * You are NOT allowed to:
 * - Use it without my explicit permission.
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
            loreList.add(MiscUtils.color("&3" + s));
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
            loreList.add(MiscUtils.color("&3" + s));
        }
        itemMeta.setLore(loreList);
        item.setItemMeta(itemMeta);
        inv.setItem(slot - 1, item);
    }

    public static ItemStack createItemstack(Material m, int amount, int data, String name, String... lore){
        ItemStack item = new ItemStack(m, amount, (byte)data);
        ItemMeta itemMeta = item.getItemMeta();
        if (name != null) {
            itemMeta.setDisplayName(MiscUtils.color(name));
        }
        ArrayList<String> loreList = new ArrayList<>();
        for(String s : lore){
            loreList.add(MiscUtils.color("&3" + s));
        }
        itemMeta.setLore(loreList);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack createItemstack(Material m, int amount, int data, String name){
        ItemStack item = new ItemStack(m, amount, (byte)data);
        ItemMeta itemMeta = item.getItemMeta();
        if (name != null) {
            itemMeta.setDisplayName(MiscUtils.color(name));
        }
        item.setItemMeta(itemMeta);
        return item;
    }

    public static void createToggle(Inventory inv, int slot, String name, boolean state){
        ItemStack is = createItemstack(Material.INK_SACK, 1, 8, name, "&7Klik om te togglen.");
        ItemMeta im = is.getItemMeta();

        if(state) {
            im.setDisplayName(MiscUtils.color("&a" + name));
            is.setDurability((short)10);
        } else {
            im.setDisplayName(MiscUtils.color("&c" + name));
            is.setDurability((short)8);
        }

        is.setItemMeta(im);
        inv.setItem(slot - 1, is);
    }




}
