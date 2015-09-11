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

    private static String scoreboardName = MiscUtils.color("&e&lMy&3&lHorizon");

    public static void constructScoreboard(Player p) {
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("mainboard", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(Variables.SERVER_NAME);

        Score e1 = objective.getScore(" ");
        e1.setScore(5);
        Score coins = objective.getScore(MiscUtils.color("&bCoins: &a" + PlayerUtils.getCoins(p)));
        coins.setScore(4);
        Score e2 = objective.getScore("  ");
        e2.setScore(3);
        Score online = objective.getScore(MiscUtils.color("&bNu Online: &a" + Bukkit.getOnlinePlayers().size()));
        online.setScore(2);
        Score unique = objective.getScore(MiscUtils.color("&bUnieke Spelers: &cN/A"));
        unique.setScore(1);

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
        if (board != null) {
            scoreboards.remove(p.getName());
            Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
            objective.unregister();
            objective = board.registerNewObjective("mainboard", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(Variables.SERVER_NAME);

            Score e1 = objective.getScore(" ");
            e1.setScore(5);
            Score coins = objective.getScore(MiscUtils.color("&bCoins: &a" + PlayerUtils.getCoins(p)));
            coins.setScore(4);
            Score e2 = objective.getScore("  ");
            e2.setScore(3);
            Score online = objective.getScore(MiscUtils.color("&bNu Online: &a" + Bukkit.getOnlinePlayers().size()));
            online.setScore(2);
            Score unique = objective.getScore(MiscUtils.color("&bUnieke Spelers: &cN/A"));
            unique.setScore(1);

            scoreboards.put(p.getName(), board);
        } else {
            constructScoreboard(p);
        }
    }
}
