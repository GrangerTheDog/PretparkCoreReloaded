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

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;

/**
 * This class has been created on 09/9/11/2015/2015 at 7:11 PM by Cooltimmetje.
 */
public class ScoreboardUtils {

    public static HashMap<String, Scoreboard> scoreboards = new HashMap<>();
    static ScoreboardManager manager = Bukkit.getScoreboardManager();

    //Gathers the required information and displays it on scoreboard. Used on a player when he/she joins, and when the plugin enables.
    public static void constructScoreboard(Player p){
        Scoreboard sc = manager.getNewScoreboard();
        Objective objective = sc.registerNewObjective("mainboard", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(Variables.SERVER_NAME);

        Score e1 = objective.getScore(" ");
        e1.setScore(5);
        Score coins = objective.getScore(MiscUtils.color("&bCoins: &cN/A"));
        coins.setScore(4);
        Score e2 = objective.getScore("  ");
        e2.setScore(3);
        Score online = objective.getScore(MiscUtils.color("&bNu Online: &a" + Bukkit.getOnlinePlayers().size()));
        online.setScore(2);
        Score unique = objective.getScore(MiscUtils.color("&bUnieke Spelers: &cN/A"));
        unique.setScore(1);

        p.setScoreboard(sc);
        scoreboards.put(p.getName(), sc);
    }

    //Destroys the scoreboard of a player, used when he/she leaves, and when the plugin disables.
    public static void destroyScoreboard(Player p){
        Scoreboard sc = scoreboards.get(p.getName());
        Objective objective = sc.getObjective(DisplaySlot.SIDEBAR);
        objective.unregister();
        scoreboards.remove(p.getName());
    }

    //Updates the current scoreboard of a player used whenever needed to.
    public static void updateScoreboard(Player p, boolean leave){
        Scoreboard sc = scoreboards.get(p.getName());
        if(sc != null){
            scoreboards.remove(p.getName());
            Objective objective = sc.getObjective(DisplaySlot.SIDEBAR);
            objective.unregister();
            objective = sc.registerNewObjective("mainboard", "dummy");

            Score e1 = objective.getScore(" ");
            e1.setScore(5);
            Score coins = objective.getScore(MiscUtils.color("&bCoins: &cN/A"));
            coins.setScore(4);
            Score e2 = objective.getScore("  ");
            e2.setScore(3);
            int onlineAmount;
            if(leave) {
                onlineAmount = Bukkit.getOnlinePlayers().size() - 1;
            } else {
                onlineAmount = Bukkit.getOnlinePlayers().size();
            }
            Score online = objective.getScore(MiscUtils.color("&bNu Online: &a" + onlineAmount));
            online.setScore(2);
            Score unique = objective.getScore(MiscUtils.color("&bUnieke Spelers: &cN/A"));
            unique.setScore(1);

            scoreboards.put(p.getName(), sc);
        } else {
            constructScoreboard(p);
        }
    }

}
