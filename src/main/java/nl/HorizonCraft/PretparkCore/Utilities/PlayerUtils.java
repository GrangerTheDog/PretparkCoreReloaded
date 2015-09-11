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

import com.evilmidget38.UUIDFetcher;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * This class has been created on 09/9/11/2015/2015 at 9:11 PM by Cooltimmetje.
 */
public class PlayerUtils {

    public static String getUUID(Player p) {
        String name, uuid = null;
        name = p.getName();

        try {
            uuid = new UUIDFetcher(Arrays.asList(name)).call().get(name).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uuid;
    }

    public static int getCoins(Player p){
        return Variables.coins.get(p.getName());
    }

    public static int getCoinTime(Player p) {
        return Variables.coinTime.get(p.getName());
    }

    public static void addCoins(Player p, int add, String reason) {
        int curCoins = getCoins(p);
        Variables.coins.remove(p.getName());
        Variables.coins.put(p.getName(), add + curCoins);
        ChatUtils.sendMsg(p, "&6+" + add + " coins! (" + reason + ")");
        p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        ScoreboardUtils.updateScoreboard(p, false);
    }

    public static void setCoinTime(Player p, int time){
        Variables.coinTime.remove(p.getName());
        Variables.coinTime.put(p.getName(), time);
    }
}
