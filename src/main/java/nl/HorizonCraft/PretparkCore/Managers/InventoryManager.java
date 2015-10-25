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

package nl.HorizonCraft.PretparkCore.Managers;

import nl.HorizonCraft.PretparkCore.Bundles.Rides.RideMenu;
import nl.HorizonCraft.PretparkCore.Menus.AdminMenu.MainAdmin;
import nl.HorizonCraft.PretparkCore.Menus.MyHorizon.MyHorizonMenu;
import nl.HorizonCraft.PretparkCore.Menus.SwagMenu.MainSwag;
import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * This class has been created on 09/12/2015 at 10:20 AM by Cooltimmetje.
 */
public class InventoryManager implements Listener {

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event){
        Player p = event.getPlayer();
        if(!p.hasPermission("pc.bypassgm")){
           event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        Player p = event.getPlayer();
        if(!p.hasPermission("pc.bypassgm")){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(event.getAction().toString().contains("RIGHT")){
            if(event.getItem() != null){
                if(event.getItem().hasItemMeta()){
                    Material m = event.getMaterial();
                    ItemStack is = event.getItem();
                    switch (m){
                        case SKULL_ITEM:
                            SkullMeta sm = (SkullMeta) is.getItemMeta();
                            if(sm.getOwner().equals(p.getName())) {
                                MyHorizonMenu.openMyHorizon(p, p, false);
                            }
                            break;
                        case MINECART:
                            RideMenu.openRide(p);
                            break;
                        case FLINT:
                            if (!p.isSneaking()) {
                                MainAdmin.openAdminMain(p);
                            }
                            break;
                        case CHEST:
                            MainSwag.open(p);
                            break;
                        default:
                            break;
                    }

                }
            }
        }
    }

    @EventHandler
    @SuppressWarnings("all")
    public void onRightClick(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Minecart) && (event.getRightClicked() instanceof Player)) {
            if (event.getPlayer().getItemInHand() != null) {
                if (event.getPlayer().getItemInHand().getType() == Material.FLINT) {
                    if (event.getPlayer().getItemInHand().hasItemMeta()) {
                        event.setCancelled(true);
                        Player p = event.getPlayer();
                        if (p.isSneaking()) {
                            if (event.getRightClicked() instanceof Player) {
                                Player target = (Player) event.getRightClicked();
                                MyHorizonMenu.openMyHorizon(p, target, true);
                            }
                        }
                    }
                }
            }
        }
    }

}
