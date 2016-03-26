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
