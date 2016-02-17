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

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * This class has been created on 09/9/11/2015/2015 at 10:33 PM by Cooltimmetje.
 */
public class ChatUtils {

    public static void sendMsg(Player p, String msg){
        p.sendMessage(MiscUtils.color(msg));
    }

    public static void sendMsgTag(Player p, String tag, String msg){
        p.sendMessage(MiscUtils.color("&9" + tag + "&9> &a" + msg));
    }

    public static void bcMsgTag(String tag, String msg){
        Bukkit.broadcastMessage(MiscUtils.color("&9" + tag + "&9> &a" + msg));
    }

    public static void sendNoPremTag(Player p, String tag){
        p.sendMessage(MiscUtils.color("&9" + tag + "&9> &a" + error + "Je mag dit niet doen!"));
    }

    public static void sendSoonTag(Player p, String tag){
        p.sendMessage(MiscUtils.color("&9" + tag + "&9> &a" + error + "SoonTM!"));
    }

    public static void sendArugmentsError(Player p, String tag, String usage) {
        p.sendMessage(MiscUtils.color("&9" + tag + "&9> &a" + error + "Ongeldige argumenten. &oGebruik: " + usage));
    }

    public static void sendFalseInt(Player p, String tag, String falseInt) {
        p.sendMessage(MiscUtils.color("&9" + tag + "&9> &a" + error + "Ongeldig getal: &c" + falseInt));
    }

    public static void sendFaslePlayer(Player p, String tag, String falsePlayer) {
        p.sendMessage(MiscUtils.color("&9" + tag + "&9> &a" + error + "Ongeldige speler: &c" + falsePlayer));
    }

    public static void sendRaw(Player p, String json){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + p.getName() + " " + json);
    }

    public static void clearChat(Player p){
        for(int i = 0; i < 100; i++){
            p.sendMessage(" ");
        }
    }


    public static String error = "&c&lERROR! &a";
    public static String success = "&2&lGELUKT! &a";

}
