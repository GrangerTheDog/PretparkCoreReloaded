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

import nl.HorizonCraft.PretparkCore.Bundles.Ranks.RanksEnum;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import nl.HorizonCraft.PretparkCore.Utilities.ScheduleUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

/**
 * This class has been created on 09/24/2015 at 8:31 PM by Cooltimmetje.
 */
public class BoxMenu implements Listener {

    public static boolean inUse = false;

    @EventHandler
    public void onBoxClick(PlayerInteractEvent event){
        if(event.getClickedBlock() != null){
            if (event.getClickedBlock().getType() == Material.ENDER_CHEST) {
                if (event.getClickedBlock().getLocation().getBlockX() == 98) {
                    if(event.getClickedBlock().getLocation().getBlockY() == 60) {
                        if (event.getClickedBlock().getLocation().getBlockZ() == -313) {
                            event.setCancelled(true);
                            openBoxMenu(event.getPlayer());
                        }
                    }
                }
            }
        }
    }

    private void openBoxMenu(Player p){
        CorePlayer cp = PlayerUtils.getProfile(p);
        Inventory inv = Bukkit.createInventory(null, 54, "Mystery Vault");
        int boxes = cp.getBoxes();

        if(boxes == 0){
            ItemUtils.createDisplay(inv, 23, Material.STAINED_GLASS_PANE, 1, 15, "&cERROR!", "&7Je hebt geen Mystery Boxes!");
        } else {
            for(int i = 0; i < boxes; i++){
                if(i == 45){
                    break;
                }
                ItemUtils.createDisplay(inv, i+1, Material.ENDER_CHEST, 1, 0, "&aMystery Box", "&7Klik om te openen!", "&aDit kost: &d1 Mystery Key");
            }

            ItemUtils.createDisplay(inv, 50, Material.WORKBENCH, 1, 0, "&aCrafting",
                    "&3Wanneer je een item al hebt, ontvang je Mystery Dust!",
                    "&3Hiermee kun je exclusive boxes craften",
                    "&3en daaruit exclusieve items ontvangen!",
                    "&3Of craft een normale box.",
                    " ",
                    "&3&bMystery Dust: &a" + PlayerUtils.getProfile(p).getDust());

            if(boxes > 4){
                if(RanksEnum.hasPermission(cp, RanksEnum.BOUWER)){
                    ItemUtils.createDisplay(inv, 53, Material.ENDER_CHEST, 5, 0, "&a5 Mystery Boxes", "&7Klik om te openen!", "&aDit kost: &d5 Mystery Keys");
                    ItemUtils.createDisplay(inv, 54, Material.ENDER_CHEST, 11, 0, "&a11 Mystery Boxes", "&7Klik om te openen!", "&aDit kost: &d11 Mystery Keys");
                }
            }
        }

        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(ChatColor.stripColor(event.getInventory().getName()).contains("Mystery Vault")){
            event.setCancelled(true);
            final Player p = (Player) event.getWhoClicked();
            Material m = event.getCurrentItem().getType();
            switch (m){
                case ENDER_CHEST:
                    if(!inUse){
                        if(event.getCurrentItem().getAmount() == 5){
                            if(PlayerUtils.getProfile((Player)event.getWhoClicked()).getKeys() > 4){
                                for(int x=96;x<101;x++) {
                                    final int xf = x;
                                    ScheduleUtils.scheduleTask(5 * (x - 95), new Runnable() {
                                        @Override
                                        public void run() {
                                            BoxAnimation.openBox(xf, p, null);
                                        }
                                    });
                                }
                                p.closeInventory();
                            } else {
                                ChatUtils.sendMsgTag((Player)event.getWhoClicked(), "MysteryVault", ChatUtils.error + "Je hebt niet genoeg keys!");
                            }
                        } else if (event.getCurrentItem().getAmount() == 11){
                            if(PlayerUtils.getProfile((Player)event.getWhoClicked()).getKeys() > 10){
                                for(int x=93;x<104;x++) {
                                    final int xf = x;
                                    ScheduleUtils.scheduleTask(5 * (x - 95), new Runnable() {
                                        @Override
                                        public void run() {
                                            BoxAnimation.openBox(xf, p, null);
                                        }
                                    });
                                }
                                p.closeInventory();
                            } else {
                                ChatUtils.sendMsgTag((Player)event.getWhoClicked(), "MysteryVault", ChatUtils.error + "Je hebt niet genoeg keys!");
                            }
                        } else if(PlayerUtils.getProfile((Player)event.getWhoClicked()).getKeys() > 0){
                            p.closeInventory();
                            BoxAnimation.openBox((Player)event.getWhoClicked(), null);
                        } else {
                            ChatUtils.sendMsgTag((Player)event.getWhoClicked(), "MysteryVault", ChatUtils.error + "Je hebt niet genoeg keys!");
                        }
                    } else {
                        ChatUtils.sendMsgTag((Player)event.getWhoClicked(), "MysteryVault", ChatUtils.error + "Maar een persoon kan de Mystery Vault tegelijk gebruiken!");
                    }
                    break;
                case WORKBENCH:
                    BoxCrafting.open(p);
                    break;
                default:
                    break;
            }
        }
    }


}
