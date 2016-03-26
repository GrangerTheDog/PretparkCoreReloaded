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

import nl.HorizonCraft.PretparkCore.Bundles.Ranks.RanksEnum;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * This class has been created on 09/12/2015 at 5:37 PM by Cooltimmetje.
 */
public class MainAdmin implements Listener,CommandExecutor {

    public static void openAdminMain(Player p){
        Inventory inv = Bukkit.createInventory(null, 27, "Admin Menu");

        ItemUtils.createDisplay(inv, 13, Material.WATCH, 1, 0, "&aVerander Tijd", "&7Verander de tijd van de server.");
        ItemUtils.createDisplay(inv, 15, Material.SKULL_ITEM, 1,SkullType.PLAYER.ordinal(), "&aPlayer Informatie", "&7Bekijk informatie over de spelers die online zijn.");

        p.openInventory(inv);
    }

    @EventHandler
    public static void onInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getName().equals("Admin Menu")){
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            Material m = event.getCurrentItem().getType();
            switch (m){
                default:
                    break;
                case WATCH:
                    TimeAdmin.openTime(p);
                    break;
                case SKULL_ITEM:
                    PlayerAdmin.openPlayer(p);
                    break;
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getLabel().equalsIgnoreCase("adminmenu")){
            if(sender instanceof Player){
                if(RanksEnum.hasPermission(((Player)sender),RanksEnum.MANAGER)){
                    Player p = (Player) sender;
                    openAdminMain(p);
                }
            }
        }
        return false;
    }

}
