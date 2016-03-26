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

import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementsEnum;
import nl.HorizonCraft.PretparkCore.Managers.KarmaManager;
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
                            KarmaManager.startKarma();
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
                            KarmaManager.startKarma();
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
                            KarmaManager.startKarma();
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
