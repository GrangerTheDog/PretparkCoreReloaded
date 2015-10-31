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

package nl.HorizonCraft.PretparkCore.Bundles.MysteryBox;

import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

/**
 * This class has been created on 09/24/2015 at 8:31 PM by Cooltimmetje.
 */
public class BoxListener implements Listener {

    @EventHandler
    public void onBoxClick(PlayerInteractEvent event){
        if(event.getClickedBlock().getType() == Material.ENDER_CHEST){
            if (event.getClickedBlock().getLocation() == new Location(Bukkit.getWorld(Variables.WORLD_NAME), 98, 60, -313)) {
                event.setCancelled(true);
                ChatUtils.sendSoonTag(event.getPlayer(), "MysteryBox");
                //                openBox(event.getPlayer());
            }
        }
    }

    private void openBox(Player p){
        CorePlayer cp = PlayerUtils.getProfile(p);
        Inventory inv = Bukkit.createInventory(null, 54, "Mystery Vault");
        int boxes = cp.getBoxes();

        if(boxes == 0){
            ItemUtils.createDisplay(inv, 23, Material.STAINED_GLASS_PANE, 1, 15, "&cERROR!", "&7Je hebt geen Mystery Boxes!");
        } else {
            for(int i = 0; i < boxes; i++){
                ItemUtils.createDisplay(inv, i+1, Material.ENDER_CHEST, 1, 0, "&aMystery Box", "&7Klik om te openen!", "&aDit kost: &d1 Mystery Key");
            }
        }
    }

}
