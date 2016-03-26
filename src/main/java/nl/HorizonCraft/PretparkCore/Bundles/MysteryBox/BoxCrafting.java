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

package nl.HorizonCraft.PretparkCore.Bundles.MysteryBox;

import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
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
 * Created by Cooltimmetje on 1/30/2016 at 5:14 PM.
 */
public class BoxCrafting implements Listener {

    public static void open(Player p){
        Inventory inv = Bukkit.createInventory(null, 27, "Mystery Crafting");

        ItemUtils.createDisplay(inv, 13, Material.ENDER_CHEST, 1, 0, "&aCraft:", "&31 Mystery Box", " ", "&aDit kost: &b50 Mystery Dust", "&c> &aKlik om te craften.");
        ItemUtils.createDisplay(inv, 15, Material.TRIPWIRE_HOOK, 1, 0, "&aCraft:", "&d1 Mystery Key", " ", "&aDit kost: &b150 Mystery Dust", "&c> &aKlik om te craften.");

        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(ChatColor.stripColor(event.getInventory().getName()).contains("Mystery Crafting")){
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            CorePlayer cp = PlayerUtils.getProfile(p);
            Material m = event.getCurrentItem().getType();
            switch (m){
                case TRIPWIRE_HOOK:
                    if(cp.getDust() >= 150){
                        cp.addKeys(p, 1, "key gecraft", false, true);
                        cp.removeDust(p, 150, "key gecraft",false);
                    } else {
                        ChatUtils.sendMsgTag(p, "MysteryCrafting", ChatUtils.error + "Je heb niet genoeg Mystery Dust!");
                    }
                break;
                case ENDER_CHEST:
                    if(cp.getDust() >= 50){
                        cp.addBoxes(p, 1, "box gecraft", false, true);
                        cp.removeDust(p, 50, "box gecraft",false);
                    } else {
                        ChatUtils.sendMsgTag(p, "MysteryCrafting", ChatUtils.error + "Je heb niet genoeg Mystery Dust!");
                    }
                default:
                    break;
            }
        }
    }

}
