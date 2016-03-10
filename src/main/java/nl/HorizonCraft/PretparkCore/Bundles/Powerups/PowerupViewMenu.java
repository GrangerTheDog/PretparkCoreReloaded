/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2016 Tim Medema
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
