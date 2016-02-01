/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2016 HorizonCraft
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

import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementType;
import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementTypes;
import nl.HorizonCraft.PretparkCore.Menus.MyHorizon.MyHorizonMenu;
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
