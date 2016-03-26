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

package nl.HorizonCraft.PretparkCore.Menus.AdminMenu;

import nl.HorizonCraft.PretparkCore.Menus.MyHorizon.MyHorizonMenu;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * This class has been created on 09/13/2015 at 12:23 PM by Cooltimmetje.
 */
public class PlayerAdmin implements Listener {

    public static void openPlayer(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, "Player Informatie");
        int slot = 1;

        for(Player pl : Bukkit.getOnlinePlayers()){
            ItemStack is = ItemUtils.createItemstack(Material.SKULL_ITEM, 1, SkullType.PLAYER.ordinal(), pl.getDisplayName(), "&7Klik om te bekijken.");
            SkullMeta im = (SkullMeta) is.getItemMeta();
            im.setOwner(pl.getName());
            is.setItemMeta(im);
            ItemUtils.createDisplay(is, inv, slot);
            slot = slot + 1;
        }

        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getInventory().getName().equals("Player Informatie")){
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            switch (event.getCurrentItem().getType()){
                default:
                    break;
                case SKULL_ITEM:
                    SkullMeta sm = (SkullMeta) event.getCurrentItem().getItemMeta();
                    String owner = sm.getOwner();
                    Player pTarget = Bukkit.getPlayer(owner);

                    if(pTarget != null){
                        MyHorizonMenu.openMyHorizon(p, pTarget, true);
                    }

                    break;
            }
        }
    }

}
