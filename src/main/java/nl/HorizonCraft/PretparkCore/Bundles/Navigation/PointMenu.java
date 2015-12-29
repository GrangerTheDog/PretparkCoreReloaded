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

package nl.HorizonCraft.PretparkCore.Bundles.Navigation;

import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementType;
import nl.HorizonCraft.PretparkCore.Menus.MyHorizon.MyHorizonMenu;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PointUtils;
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
 * Created by Cooltimmetje on 12/29/2015 at 12:02 PM.
 */
public class PointMenu implements Listener {

    public static void open(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, "Warp Menu");

        int slot = 1;
        for(NavigationPoint navPoint : Variables.navigationPoints){
            ItemUtils.createDisplay(inv, slot, Material.STAINED_CLAY, 1, navPoint.getPointState().getColorData(), "&a" + navPoint.getName(),
                    "&bStatus: &" + navPoint.getPointState().getColorCode() + navPoint.getPointState().getFriendlyName(),
                    "&bSoort: &a" + navPoint.getPointType().getFriendlyName(),
                    "&bKlik om te teleporteren.", "&8ID: " + navPoint.getId());

            slot++;
        }

        ItemUtils.createDisplay(inv, 50, Material.GOLD_INGOT, 1, 0, "&aSorteer op: &bShops");
        ItemUtils.createDisplay(inv, 51, Material.ENDER_PEARL, 1, 0, "&aSorteer op: &bWarps");
        ItemUtils.createDisplay(inv, 52, Material.MINECART, 1, 0, "&aSorteer op: &bAttracties");
        ItemUtils.createDisplay(inv, 53, Material.LEAVES, 1, 0, "&aSorteer op: &bDoolhoven");
        ItemUtils.createDisplay(inv, 54, Material.FEATHER, 1, 0, "&aSorteer op: &bParkouren");

        p.openInventory(inv);
    }

    public static void open(Player p, PointType pt){
        Inventory inv = Bukkit.createInventory(null, 54, "Warp Menu");

        int slot = 1;
        for(NavigationPoint navPoint : Variables.navigationPoints){
            if(navPoint.getPointType() == pt) {
                ItemUtils.createDisplay(inv, slot, Material.STAINED_CLAY, 1, navPoint.getPointState().getColorData(), "&a" + navPoint.getName(),
                        "&bStatus: &" + navPoint.getPointState().getColorCode() + navPoint.getPointState().getFriendlyName(),
                        "&bSoort: &a" + navPoint.getPointType().getFriendlyName(),
                        "&bKlik om te teleporteren.", "&8ID: " + navPoint.getId());

            }
            slot++;
        }

        ItemUtils.createDisplay(inv, 49, Material.BARRIER, 1, 0, "&cWis filter.");
        ItemUtils.createDisplay(inv, 50, Material.GOLD_INGOT, 1, 0, "&aSorteer op: &bShops");
        ItemUtils.createDisplay(inv, 51, Material.ENDER_PEARL, 1, 0, "&aSorteer op: &bWarps");
        ItemUtils.createDisplay(inv, 52, Material.MINECART, 1, 0, "&aSorteer op: &bAttracties");
        ItemUtils.createDisplay(inv, 53, Material.LEAVES, 1, 0, "&aSorteer op: &bDoolhoven");
        ItemUtils.createDisplay(inv, 54, Material.FEATHER, 1, 0, "&aSorteer op: &bParkouren");

        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(ChatColor.stripColor(event.getInventory().getName()).contains("Warp Menu")){
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            Material m = event.getCurrentItem().getType();
            switch (m){
                case ENDER_PEARL:
                    open(p, PointType.WARP);
                    break;
                case GOLD_INGOT:
                    open(p, PointType.SHOP);
                    break;
                case MINECART:
                    open(p, PointType.RIDE);
                    break;
                case LEASH:
                    open(p, PointType.MAZE);
                    break;
                case FEATHER:
                    open(p, PointType.PARKOUR);
                    break;
                case BARRIER:
                    open(p);
                    break;
                case STAINED_CLAY:
                    NavigationPoint navPoint = PointUtils.getById(Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(3)).replace("ID: ", " ").trim()));
                    if (navPoint != null) {
                        p.teleport(navPoint.getLocation());
                    }

                    p.closeInventory();
                    break;
                default:
                    break;
            }
        }
    }

}
