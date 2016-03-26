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

package nl.HorizonCraft.PretparkCore.Timers;

import nl.HorizonCraft.PretparkCore.Bundles.Mazes.MazeLeaderboards;
import nl.HorizonCraft.PretparkCore.Utilities.ScheduleUtils;
import org.bukkit.plugin.Plugin;

/**
 * Created by Cooltimmetje on 1/17/2016 at 7:58 PM.
 */
public class LeaderboardUpdater {

    public static void start(Plugin plugin){

        ScheduleUtils.repeatTask(plugin, 36000, 36000, new Runnable() {
            @Override
            public void run() {
                MazeLeaderboards.load(false);
            }
        });

    }

}
