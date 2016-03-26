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

package nl.HorizonCraft.PretparkCore.Utilities;

import nl.HorizonCraft.PretparkCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * This class has been created on 28-7-2015 at 21:16 by cooltimmetje.
 *
 * Note: This class has been copied from the last version of PretparkCore: https://github.com/Cooltimmetje/PretparkCore/blob/master/src/me/Cooltimmetje/PretparkCore/Utilities/ScheduleUtils.java
 * Class is owned by HorizonCraft.
 */
public class ScheduleUtils {

    public static void scheduleTask(long delayTicks, Runnable runnable){
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), runnable, delayTicks);
    }

    public static void repeatTask(long startDelay, long repeatDelay, Runnable runnable){
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), runnable, startDelay, repeatDelay);
    }

    public static void repeatTask(Plugin plugin, long startDelay, long repeatDelay, Runnable runnable) {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, runnable, startDelay, repeatDelay);
    }

}