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

package nl.HorizonCraft.PretparkCore.Bundles.Shops;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * Created by Cooltimmetje on 12/7/2015 at 9:08 PM.
 */
public class Test implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEntityEvent event){
        if(event.getPlayer().getName().equals("Cooltimmetje")){
            if(event.getPlayer().isSneaking()){
                Player p = event.getPlayer();
                Entity e = event.getRightClicked();

                p.sendMessage(e.getUniqueId().toString());
                p.sendMessage(e.getName());
            }
        }
    }

}
