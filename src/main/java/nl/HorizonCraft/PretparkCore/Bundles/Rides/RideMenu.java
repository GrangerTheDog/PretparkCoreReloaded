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

package nl.HorizonCraft.PretparkCore.Bundles.Rides;

import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * This class has been created on 10/09/2015 at 6:37 PM by Cooltimmetje.
 */
public class RideMenu implements Listener {

    public static void openRide(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, "Attracties");
        int slot = 1;

        for(int i : RideVars.rides.keySet()){
            Ride ride = RideVars.rides.get(i);
            ItemStack is = ItemUtils.createItemstack(Material.STAINED_CLAY, 1, ride.getRideState().getClayColor(), "&a" + ride.getName(), "&bStatus: " + ride.getRideState().getStateString(), "&b> Klik om te teleporteren", "&8ID: " + ride.getId());

            ItemUtils.createDisplay(is, inv, slot);

            slot++;
        }

        p.openInventory(inv);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event){
        if(ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Attracties")){
            if(event.getCurrentItem().getType() == Material.STAINED_CLAY){
                String idS = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(2).replace("ID: ", " ")).trim();
                int id = Integer.parseInt(idS);
                event.getWhoClicked().teleport(RideVars.rides.get(id).getLocation());
                event.setCancelled(true);
                event.getWhoClicked().closeInventory();
            }
        }
    }



}
