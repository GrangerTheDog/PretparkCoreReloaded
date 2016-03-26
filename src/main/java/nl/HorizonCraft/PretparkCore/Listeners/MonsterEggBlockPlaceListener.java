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

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static org.bukkit.ChatColor.RED;

/**
 * This class has been created on 12/12/13/2015/2015 at 8:09 PM by 78wesley.
 */

public class MonsterEggBlockPlaceListener implements Listener{

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (e.getBlock().getType() == Material.MONSTER_EGGS) {
            e.setCancelled(true);
            p.sendMessage(RED + "Dit Block mag niet geplaast worden!");
        } else {
            e.setCancelled(false);
        }
    }
}