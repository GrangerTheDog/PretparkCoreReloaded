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

import nl.HorizonCraft.PretparkCore.Bundles.Powerups.PowerupSpawner;
import nl.HorizonCraft.PretparkCore.Profiles.MysqlManager;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.ScheduleUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * This class has been created on 09/9/11/2015/2015 at 10:14 PM by Cooltimmetje.
 */
public class DataSaver {

    public static void start(Plugin plugin) {
        ScheduleUtils.repeatTask(plugin, 12000, 12000, new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    MysqlManager.saveData(p);
                    MysqlManager.savePrefs(p);
                    MysqlManager.saveRecords(p);

//                    PlayerUtils.configPlayer(p, false);
                }

                MiscUtils.updateVouchers();
                PowerupSpawner.spawn();
            }
        });
    }

}
