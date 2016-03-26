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

package nl.HorizonCraft.PretparkCore.Menus.SwagMenu;

import nl.HorizonCraft.PretparkCore.Bundles.Gadgets.GadgetsMenu;
import nl.HorizonCraft.PretparkCore.Bundles.Pets.PetMenu;
import nl.HorizonCraft.PretparkCore.Bundles.Wardrobe.WardrobeMenu;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * This class has been created on 09/18/2015 at 5:48 PM by Cooltimmetje.
 */
public class MainSwag implements Listener, CommandExecutor {

    public static void open(Player p){
        Inventory inv = Bukkit.createInventory(null, 27, "Swag Menu");

        ItemUtils.createDisplay(inv, 14, Material.PISTON_BASE, 1, 0, "&aGadgets", "&7TODO"); //TODO: lore
        ItemUtils.createDisplay(inv, 13, Material.IRON_CHESTPLATE, 1, 0, "&aKledingkast", "&7TODO"); //TODO: lore
        ItemUtils.createDisplay(inv, 15, Material.BONE, 1, 0, "&aPets", "&7TODO"); //TODO: Lore

        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        if(ChatColor.stripColor(inv.getName()).contains("Swag Menu")){
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            Material m = event.getCurrentItem().getType();
            switch (m){
                default:
                    break;
                case BONE:
                    PetMenu.open(p);
                    break;
                case IRON_CHESTPLATE:
                    WardrobeMenu.open(p);
                    break;
                case PISTON_BASE:
                    GadgetsMenu.open(p);
                    break;
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getLabel().equalsIgnoreCase("swagmenu")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                open(p);
            }
        }
        return false;
    }

}
