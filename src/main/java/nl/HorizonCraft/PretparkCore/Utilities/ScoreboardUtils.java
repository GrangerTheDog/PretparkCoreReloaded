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

package nl.HorizonCraft.PretparkCore.Utilities;

import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;

/**
 * This class has been created on 09/9/11/2015/2015 at 7:11 PM by Cooltimmetje.
 */
public class ScoreboardUtils {

    public static HashMap<String, Scoreboard> scoreboards = new HashMap<>();
    static ScoreboardManager manager = Bukkit.getScoreboardManager();

    private static String scoreboardName = MiscUtils.color("&e&lMy&3&lHorizon");

    public static void constructScoreboard(Player p) {
        CorePlayer cp = PlayerUtils.getProfile(p);

        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("mainboard", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(Variables.SERVER_NAME);
        int onlineAmount = Bukkit.getOnlinePlayers().size();


        if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) {
            String[] exp = cp.calculateExpString(p).split(",");
            int levelI, neededI, expI;
            levelI = Integer.parseInt(exp[2]);
            expI = Integer.parseInt(exp[1]);
            neededI = Integer.parseInt(exp[0]);
            String levelProgress = progress(expI, neededI);

            Score coins = objective.getScore(MiscUtils.color("&bCoins: &a" + MiscUtils.intFormat(cp.getCoins(), " ")));
            coins.setScore(9);
            Score boxes = objective.getScore(MiscUtils.color("&bMystery Boxes: &a" + cp.getBoxes()));
            boxes.setScore(8);
            Score keys = objective.getScore(MiscUtils.color("&bMystery Keys: &a" + cp.getKeys()));
            keys.setScore(7);
            Score dust = objective.getScore(MiscUtils.color("&bMystery Dust: &a" + cp.getDust()));
            dust.setScore(6);
            Score level = objective.getScore(MiscUtils.color("&bLevel: &a" + levelI));
            level.setScore(5);
            Score needed = objective.getScore(MiscUtils.color("&bLvlUp: " + levelProgress));
            needed.setScore(4);
            Score e1 = objective.getScore(" ");
            e1.setScore(3);
            Score online = objective.getScore(MiscUtils.color("&bNu Online: &a" + onlineAmount));
            online.setScore(2);
            Score unique = objective.getScore(MiscUtils.color("&bUnieke Spelers: &a" + Variables.uniquePlayers));
            unique.setScore(1);
        } else {
            Score coins = objective.getScore(MiscUtils.color("&bCoins: &a" + MiscUtils.intFormat(cp.getCoins(), " ")));
            coins.setScore(7);
            Score boxes = objective.getScore(MiscUtils.color("&bMystery Boxes: &a" + cp.getBoxes()));
            boxes.setScore(6);
            Score keys = objective.getScore(MiscUtils.color("&bMystery Keys: &a" + cp.getKeys()));
            keys.setScore(5);
            Score dust = objective.getScore(MiscUtils.color("&bMystery Dust: &a" + cp.getDust()));
            dust.setScore(4);
            Score e1 = objective.getScore(" ");
            e1.setScore(3);
            Score online = objective.getScore(MiscUtils.color("&bNu Online: &a" + onlineAmount));
            online.setScore(2);
            Score unique = objective.getScore(MiscUtils.color("&bUnieke Spelers: &a" + Variables.uniquePlayers));
            unique.setScore(1);
        }

        defaultTab(p, false);
        p.setScoreboard(board);
        scoreboards.put(p.getName(), board);
    }

    public static void destroyScoreboard(Player p) {
        Scoreboard board = scoreboards.get(p.getName());
        Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
        objective.unregister();
        scoreboards.remove(p.getName());
    }

    public static void updateScoreboard(Player p, boolean leave) {
        Scoreboard board = scoreboards.get(p.getName());
        CorePlayer cp = PlayerUtils.getProfile(p);

        if (board != null) {
            scoreboards.remove(p.getName());
            Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
            objective.unregister();
            objective = board.registerNewObjective("mainboard", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(Variables.SERVER_NAME);
            int onlineAmount = Bukkit.getOnlinePlayers().size();
            if(leave){
                onlineAmount = onlineAmount - 1;
            }

            if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) {
                String[] exp = cp.calculateExpString(p).split(",");
                int levelI, neededI, expI;
                levelI = Integer.parseInt(exp[2]);
                expI = Integer.parseInt(exp[1]);
                neededI = Integer.parseInt(exp[0]);
                String levelProgress = progress(expI, neededI);

                Score coins = objective.getScore(MiscUtils.color("&bCoins: &a" + MiscUtils.intFormat(cp.getCoins(), " ")));
                coins.setScore(9);
                Score boxes = objective.getScore(MiscUtils.color("&bMystery Boxes: &a" + cp.getBoxes()));
                boxes.setScore(8);
                Score keys = objective.getScore(MiscUtils.color("&bMystery Keys: &a" + cp.getKeys()));
                keys.setScore(7);
                Score dust = objective.getScore(MiscUtils.color("&bMystery Dust: &a" + cp.getDust()));
                dust.setScore(6);
                Score level = objective.getScore(MiscUtils.color("&bLevel: &a" + levelI));
                level.setScore(5);
                Score needed = objective.getScore(MiscUtils.color("&bLvlUp: " + levelProgress));
                needed.setScore(4);
                Score e1 = objective.getScore(" ");
                e1.setScore(3);
                Score online = objective.getScore(MiscUtils.color("&bNu Online: &a" + onlineAmount));
                online.setScore(2);
                Score unique = objective.getScore(MiscUtils.color("&bUnieke Spelers: &a" + Variables.uniquePlayers));
                unique.setScore(1);
            } else {
                Score coins = objective.getScore(MiscUtils.color("&bCoins: &a" + MiscUtils.intFormat(cp.getCoins(), " ")));
                coins.setScore(7);
                Score boxes = objective.getScore(MiscUtils.color("&bMystery Boxes: &a" + cp.getBoxes()));
                boxes.setScore(6);
                Score keys = objective.getScore(MiscUtils.color("&bMystery Keys: &a" + cp.getKeys()));
                keys.setScore(5);
                Score dust = objective.getScore(MiscUtils.color("&bMystery Dust: &a" + cp.getDust()));
                dust.setScore(4);
                Score e1 = objective.getScore(" ");
                e1.setScore(3);
                Score online = objective.getScore(MiscUtils.color("&bNu Online: &a" + onlineAmount));
                online.setScore(2);
                Score unique = objective.getScore(MiscUtils.color("&bUnieke Spelers: &a" + Variables.uniquePlayers));
                unique.setScore(1);
            }

            defaultTab(p, leave);
            scoreboards.put(p.getName(), board);
        } else {
            constructScoreboard(p);
        }
    }

    public static void defaultTab(Player p, boolean leave){
        CorePlayer cp = PlayerUtils.getProfile(p);

        String[] exp = cp.calculateExpString(p).split(",");
        int levelI, neededI, expI;
        levelI = Integer.parseInt(exp[2]);
        expI = Integer.parseInt(exp[1]);
        neededI = Integer.parseInt(exp[0]);
        String levelProgress = progress(expI, neededI);

        int online = Bukkit.getOnlinePlayers().size();
        p.setPlayerListName(p.getDisplayName());
        if(leave){
            online = online - 1;
        }

        TitleUtils.setTab(p, Variables.SERVER_NAME + "\n&aWelkom,\n" + p.getDisplayName().trim() + "\n \n&6Nu online &b- &8(&e" + online + "&8/&e" + Bukkit.getMaxPlayers() + "&8)",
                "\n&eMy&3Horizon \n&6" + MiscUtils.intFormat(cp.getCoins()," ") + " coins &8- &9Level " + cp.getLevel() + " " + levelProgress + "\n&3" + cp.getBoxes() + " Mystery Boxes &8- &d" + cp.getKeys() + " Mystery Keys\n&b"
        + cp.getDust() + " Mystery Dust\n&8------\n&awww.horizoncraft.nl");
    }

    private static String progress(int exp, int needed) {
        double d = (double) exp / (double) needed;
        double d1 = d * 100;
        double d2 = Math.round(d1);
        int i = (int) d2;

        return "&8[&a" + i + "%&8]";
    }
}
