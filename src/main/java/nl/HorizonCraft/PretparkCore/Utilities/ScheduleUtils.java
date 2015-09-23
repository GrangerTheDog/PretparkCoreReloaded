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