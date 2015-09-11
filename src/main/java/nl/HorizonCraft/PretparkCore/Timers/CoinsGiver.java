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

package nl.HorizonCraft.PretparkCore.Timers;

import nl.HorizonCraft.PretparkCore.Utilities.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * This class has been created on 09/9/11/2015/2015 at 10:28 PM by Cooltimmetje.
 */
public class CoinsGiver {

    public static void start(){
        ScheduleUtils.repeatTask(20, 1200, new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (PlayerUtils.getCoinTime(p) == 0) {
                        int chance = MiscUtils.randomInt(1, 100);
                        if (chance <= Variables.DOUBLE_CHANCE) {
                            PlayerUtils.addCoins(p, Variables.COIN_GAIN * 2, "1 uur online, dubbel coins");
                            ChatUtils.bcMsgTag("Coins", "&c" + p.getName() + " &aheeft zojuist 2x coins ontvangen! Geluksvogel!");
                            PlayerUtils.setCoinTime(p, Variables.COIN_TIME);
                        } else {
                            PlayerUtils.addCoins(p, Variables.COIN_GAIN, "1 uur online");
                            PlayerUtils.setCoinTime(p, Variables.COIN_TIME);
                        }
                    } else {
                        PlayerUtils.setCoinTime(p, PlayerUtils.getCoinTime(p) - 1);
                    }
                }
            }
        });
    }

}
