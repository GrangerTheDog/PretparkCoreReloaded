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

package nl.HorizonCraft.PretparkCore.Menus.MyHorizon;

import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementMenu;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
 * This class has been created on 09/13/2015 at 12:39 PM by Cooltimmetje.
 */
public class MyHorizonMenu implements Listener{

    public static void openMyHorizon(Player p, Player pTarget, boolean admin){
        CorePlayer cp = PlayerUtils.getProfile(pTarget);

        Inventory inv = Bukkit.createInventory(null, 36, MiscUtils.color("MyHorizon &8\u00BB &7" + pTarget.getName()));

        ItemStack is = ItemUtils.createItemstack(Material.SKULL_ITEM, 1, SkullType.PLAYER.ordinal(), "&e&lMy&3&lHorizon &8\u00BB " + pTarget.getDisplayName());
        SkullMeta im = (SkullMeta) is.getItemMeta();
        im.setOwner(pTarget.getName());
        is.setItemMeta(im);
        ItemUtils.createDisplay(is, inv, 13);

        ItemUtils.createDisplay(inv, 14, Material.DIAMOND, 1, 0, "&aAchievements", "&7Klik om te openen.");

        ItemUtils.createDisplay(inv, 15, Material.REDSTONE_COMPARATOR, 1, 0, "&aInstellingen", "&7Verander je instellingen.");

        if(!admin) {
            ItemUtils.createDisplay(inv, 22, Material.GOLD_NUGGET, 1, 0, "&6" + cp.getCoins() + " coins", "&7Verdien coins door online te zijn, deze", "&7kun je uitgeven aan allerlei spulletjes op de server.");
        } else {
            ItemUtils.createDisplay(inv, 22, Material.GOLD_NUGGET, 1, 0, "&6" + cp.getCoins() + " coins", "&7+30 coins in: " + cp.getCoinTime());
        }

        ItemUtils.createDisplay(inv, 23, Material.ENDER_CHEST, 1, 0, "&dMystery Boxes: &cN/A");
        ItemUtils.createDisplay(inv, 24, Material.TRIPWIRE_HOOK, 1, 0, "&dMystery Keys: " + cp.getKeys());

        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(ChatColor.stripColor(event.getInventory().getName()).contains("MyHorizon")){
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            Material m = event.getCurrentItem().getType();
            switch (m){
                case REDSTONE_COMPARATOR:
                    PreferencesMenu.openPrefs(p);
                    break;
                case DIAMOND:
                    AchievementMenu.open(p);
                    break;
                default:
                    break;
            }
        }
    }

}
