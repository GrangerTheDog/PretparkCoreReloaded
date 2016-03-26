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

package nl.HorizonCraft.PretparkCore.Listeners;

import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Cooltimmetje on 3/3/2016 at 8:05 PM.
 */
public class FeedDuckListener implements Listener {

    @EventHandler
    @SuppressWarnings("all")
    public void onRightClick(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Minecart)) {
            if (event.getPlayer().getItemInHand() != null) {
                if (event.getPlayer().getItemInHand().getType() == Material.SEEDS) {
                    if (event.getPlayer().getItemInHand().hasItemMeta()) {
                        event.setCancelled(true);
                        Player p = event.getPlayer();
                        if(event.getRightClicked().getName().equals("Gerrit de Eend") && event.getRightClicked().getType() == EntityType.CHICKEN && event.getPlayer().getInventory().getHeldItemSlot() == 4){
                            CorePlayer cp = PlayerUtils.getProfile(p);
                            if(cp.getGerritFood() > 0){
                                cp.addKarma(p,1,"Gerrit de eend gevoerd!",true,true);
                                cp.setGerritFood(cp.getGerritFood() - 1);
                                if(cp.getGerritFood() > 0) {
                                    ItemUtils.createDisplay(p, 5, Material.SEEDS, cp.getGerritFood(), 0, "&bEenden Voer", "&bGeef dit aan Gerrit de Eend.");
                                } else {
                                    ItemUtils.createDisplay(p, new ItemStack(Material.AIR), 5);
                                }
                            } else {
                                ChatUtils.sendMsgTag(p, "Gerrit", "WTF JIJ MIJ DEZE ROMMEL GEVEN?");
                            }
                        }
                    }
                }
            }
        }
    }
}
