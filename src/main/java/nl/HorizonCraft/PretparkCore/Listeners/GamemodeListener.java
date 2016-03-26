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

import nl.HorizonCraft.PretparkCore.Utilities.ScheduleUtils;
import nl.HorizonCraft.PretparkCore.Utilities.ScoreboardUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

/**
 * This class has been created on 10/25/2015 at 17:15 by Cooltimmetje.
 */
public class GamemodeListener implements Listener {

    @EventHandler
    public void onGameModeChange(PlayerGameModeChangeEvent event) {
        final Player p = event.getPlayer();
        ScheduleUtils.scheduleTask(20, new Runnable() {
            @Override
            public void run() {
                ScoreboardUtils.updateScoreboard(p, false);
            }
        });
    }

}
