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

package nl.HorizonCraft.PretparkCore.Menus.MyHorizon;

import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * This class has been created on 09/14/2015 at 7:36 PM by Cooltimmetje.
 */
public class PreferencesMenu implements Listener{

    public static void openPrefs(Player p){
        CorePlayer cp = PlayerUtils.getProfile(p);

        Inventory inv = Bukkit.createInventory(null, 36, "Instellingen \u00BB " + p.getName());

        ItemUtils.createDisplay(inv, 14, Material.PAPER, 1, 0, "&aStatistics", "Vind je de statistics onder in je scherm irritant?", "Zet ze hier dan uit.");
        ItemUtils.createToggle(inv, 23, "Statistics", cp.getSetting(SettingsEnum.STATISTICS));

        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        if(ChatColor.stripColor(inv.getName()).contains("Instellingen")){
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            CorePlayer cp = PlayerUtils.getProfile(p);
            int slot = event.getSlot() + 1;
            short data = event.getCurrentItem().getDurability();
            switch (slot){
                default:
                    break;
                case 23:
                    cp.toggleSetting(SettingsEnum.STATISTICS);
                    ItemUtils.createToggle(inv, slot, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()), data == 8);
                    break;
            }
        }
    }

}
