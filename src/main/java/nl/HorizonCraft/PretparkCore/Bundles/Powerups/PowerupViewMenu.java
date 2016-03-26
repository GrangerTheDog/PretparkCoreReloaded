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

package nl.HorizonCraft.PretparkCore.Bundles.Powerups;

import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * Created by Cooltimmetje on 1/31/2016 at 2:23 PM.
 */
public class PowerupViewMenu implements Listener {

    public static void open(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, "Powerup Locations");

        int slot = 1;
        for(int id : Variables.powerupLocations.keySet()){

            ItemUtils.createDisplay(inv, slot, Material.FIREWORK_CHARGE, 1, 0, "&aPowerup: " + id, "&3Locatie: &b&l(" + MiscUtils.locationToString(Variables.powerupLocations.get(id), false, true) + ")",
                    "&9> Klik om te teleporteren");

            slot++;
        }

        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        if(ChatColor.stripColor(inv.getName()).contains("Powerup Locations")) {
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            if (event.getCurrentItem() != null) {
                int id = Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).replace("Powerup: ", " ").trim());
                p.closeInventory();
                p.teleport(Variables.powerupLocations.get(id));
            }
        }
    }

}
