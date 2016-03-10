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
