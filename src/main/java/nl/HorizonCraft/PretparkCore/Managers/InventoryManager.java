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

package nl.HorizonCraft.PretparkCore.Managers;

import nl.HorizonCraft.PretparkCore.Bundles.Navigation.PointMenu;
import nl.HorizonCraft.PretparkCore.Bundles.Ranks.RanksEnum;
import nl.HorizonCraft.PretparkCore.Menus.AdminMenu.MainAdmin;
import nl.HorizonCraft.PretparkCore.Menus.MyHorizon.MyHorizonMenu;
import nl.HorizonCraft.PretparkCore.Menus.SwagMenu.MainSwag;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * This class has been created on 09/12/2015 at 10:20 AM by Cooltimmetje.
 */
public class InventoryManager implements Listener {

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event){
        Player p = event.getPlayer();
        if(!RanksEnum.hasPermission(p,RanksEnum.BOUWER)){
           event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        Player p = event.getPlayer();
        if(!RanksEnum.hasPermission(p,RanksEnum.BOUWER)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(event.getAction().toString().contains("RIGHT")){
            if(event.getItem() != null){
                if(event.getItem().hasItemMeta() && !event.getItem().getType().toString().contains("BANNER")){
                    if(event.getItem().getType() == Material.SKULL_ITEM && !ChatColor.stripColor(event.getItem().getItemMeta().getDisplayName()).contains("MyHorizon")){
                        return;
                    }

                    Material m = event.getMaterial();
                    switch (m){
                        case SKULL_ITEM:
                            MyHorizonMenu.openMyHorizon(p, p, false);
//                            try {
//                                HeadMenu.search();
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
                            event.setCancelled(true);
                            break;
                        case MINECART:
                            PointMenu.open(p);
                            event.setCancelled(true);
                            break;
                        case FLINT:
                            if (!p.isSneaking()) {
                                MainAdmin.openAdminMain(p);
                                event.setCancelled(true);
                            }
                            break;
                        case CHEST:
                            MainSwag.open(p);
                            event.setCancelled(true);
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
