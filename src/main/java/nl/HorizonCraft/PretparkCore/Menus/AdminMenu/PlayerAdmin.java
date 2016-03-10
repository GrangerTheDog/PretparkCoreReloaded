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
