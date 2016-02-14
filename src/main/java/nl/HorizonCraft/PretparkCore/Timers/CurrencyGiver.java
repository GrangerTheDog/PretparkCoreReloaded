/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2016 HorizonCraft
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

import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementsEnum;
import nl.HorizonCraft.PretparkCore.Menus.MyHorizon.SettingsEnum;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * This class has been created on 09/9/11/2015/2015 at 10:28 PM by Cooltimmetje.
 */
public class CurrencyGiver {

    private static boolean showStat = true;

    public static void start(Plugin plugin) {
        ScheduleUtils.repeatTask(plugin, 20, 1200, new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    CorePlayer cp = PlayerUtils.getProfile(p);
                    if (cp.getCoinTime() == 0) {
                        int chance = MiscUtils.randomInt(1, 100);
                        if (chance <= Variables.DOUBLE_CHANCE) {
                            cp.addCoins(p, Variables.COIN_GAIN * 2, "1 uur online, dubbel coins", true, true);
                            ChatUtils.bcMsgTag("Coins", "&c" + p.getName() + " &aheeft zojuist 2x coins ontvangen! Geluksvogel!");
                            cp.setCoinTime(Variables.COIN_TIME);
                            cp.awardAchievement(p, AchievementsEnum.DOUBLE);
                        } else {
                            cp.addCoins(p, Variables.COIN_GAIN, "1 uur online", true, true);
                            cp.setCoinTime(Variables.COIN_TIME);
                        }
                    } else {
                        cp.setCoinTime(cp.getCoinTime() - 1);
                    }


                    if (cp.getBoxTime() == 0) {
                        int chance = MiscUtils.randomInt(1, 100);
                        if (chance <= Variables.CHEST_DOUBLE) {
                            cp.addBoxes(p, Variables.CHEST_GAIN * 2, "2 uur online, dubbel Mystery Boxes", false, true);
                            ChatUtils.bcMsgTag("MysteryBoxes", "&c" + p.getName() + " &aheeft zojuist 2x Mystery Boxes ontvangen! Geluksvogel!");
                            cp.setBoxTime(Variables.CHEST_TIME);
                            cp.awardAchievement(p, AchievementsEnum.DOUBLE);
                        } else {
                            cp.addBoxes(p, Variables.CHEST_GAIN, "2 uur online", false, true);
                            cp.setBoxTime(Variables.CHEST_TIME);
                        }
                    } else {
                        cp.setBoxTime(cp.getBoxTime() - 1);
                    }


                    if (cp.getExperienceTime() == 0) {
                        int chance = MiscUtils.randomInt(1, 100);
                        if (chance <= Variables.EXPERIENCE_DOUBLE) {
                            cp.addExp(p, Variables.EXPERIENCE_GAIN * 2, "30 minuten online, dubbel experience", false, true);
                            ChatUtils.bcMsgTag("Experience", "&c" + p.getName() + " &aheeft zojuist 2x experience ontvangen! Geluksvogel!");
                            cp.setExperienceTime(Variables.EXPERIENCE_TIME);
                            cp.awardAchievement(p, AchievementsEnum.DOUBLE);
                        } else {
                            cp.addExp(p, Variables.EXPERIENCE_GAIN, "30 minuten online", false, true);
                            cp.setExperienceTime(Variables.EXPERIENCE_TIME);
                        }
                    } else {
                        cp.setExperienceTime(cp.getExperienceTime() - 1);
                    }

                    showStat = !showStat;
                    if(showStat) {
                        if(cp.getSetting(SettingsEnum.STATISTICS)) {
                            PlayerUtils.showStat(p);
                        }
                    }
                }
            }
        });
    }

}
