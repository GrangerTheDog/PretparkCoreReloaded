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
