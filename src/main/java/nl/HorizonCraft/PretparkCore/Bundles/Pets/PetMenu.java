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

package nl.HorizonCraft.PretparkCore.Bundles.Pets;

import nl.HorizonCraft.PretparkCore.Bundles.MysteryBox.Weight;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * This class has been created on 10/25/2015 at 13:25 by Cooltimmetje.
 */
public class PetMenu implements Listener {

    public static void open(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, "Pets");
        CorePlayer cp = PlayerUtils.getProfile(p);

        int slot = 1;
        for (PetType pet : PetType.values()) {
            String name, lore, skullName;
            int data, price;
            Weight weight;
            String[] loreArray;
            char color;
            boolean isMysteryBoxExclusive, unlocked;
            Material m;

            lore = pet.getLore();
            unlocked = cp.hasPet(pet);
            isMysteryBoxExclusive = pet.isMysteryBoxExclusive();
            price = pet.getCost();

            StringBuilder loreBuilder = new StringBuilder();
            for (String string : lore.split("\n")) {
                loreBuilder.append("&3").append(string).append("\n");
            }
            loreBuilder.append(" \n");

            if (!unlocked) {
                loreBuilder.append("&cLOCKED").append("\n").append("&aKoop dit item in de shop");
            }

            loreArray = loreBuilder.toString().trim().split("\n");

            weight = pet.getWeight();
            color = weight.getColor();
            name = "&" + color + pet.getName();

            m = pet.getMaterial();
            skullName = pet.getSkullName();
            data = pet.getData();

            if (!unlocked) {
                m = Material.INK_SACK;
                data = 8;
            }

            if (m == Material.SKULL_ITEM) {
                ItemStack is = ItemUtils.createItemstack(m, 1, data, name, loreArray);
                SkullMeta sm = (SkullMeta) is.getItemMeta();
                sm.setOwner(skullName);
                is.setItemMeta(sm);
                ItemUtils.createDisplay(is, inv, slot);
            } else {
                ItemUtils.createDisplay(inv, slot, m, 1, data, name, loreArray);
            }

            slot = slot + 1;
        }
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getName().equals("Pets")) {
            event.setCancelled(true);
            if(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equals("Chicken")){
                Entity entity = ((Player)event.getWhoClicked()).getWorld().spawnEntity(((Player)event.getWhoClicked()).getLocation(), EntityType.CHICKEN);
                entity.setCustomName(MiscUtils.color("&a" + ((Player)event.getWhoClicked()).getName() + "'s Chicken"));
            }
        }
    }


}
