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

package nl.HorizonCraft.PretparkCore.Bundles.DeliveryMan;

import mkremins.fanciful.FancyMessage;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Cooltimmetje on 2/9/2016 at 4:58 PM.
 */
public class DeliveryNotifier {

    public static void notify(Player p){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());
        CorePlayer cp = PlayerUtils.getProfile(p);
        int pendingDeliveries = 0;

        if(cp.hasDelivery()){
            pendingDeliveries++;
        }
        if(cp.hasDaily()){
            pendingDeliveries++;
        }
        if(date.equals("14/02")){
            if(!cp.hasClaimedSpecialDay()){
                pendingDeliveries++;
            }
        }

        if(pendingDeliveries != 0) {
            String json = new FancyMessage(
                    "[").color(ChatColor.DARK_GRAY).then("NPC").color(ChatColor.GREEN).then("]").color(ChatColor.DARK_GRAY).then(" Pieter Post").color(ChatColor.GREEN).then(": ").color(ChatColor.DARK_GRAY)
                    .then("Hey, ").color(ChatColor.WHITE)
                    .then(p.getName()).color(ChatColor.WHITE)
                    .then(MiscUtils.color("! Ik heb nog ")).color(ChatColor.WHITE)
                    .then(pendingDeliveries + "").color(ChatColor.DARK_AQUA)
                    .then(" bezorgingen ").color(ChatColor.DARK_AQUA)
                    .then("voor je! Kom ze halen op het postkantoor! ").color(ChatColor.WHITE)
                    .then("[").color(ChatColor.DARK_GRAY).then("Klik hier").color(ChatColor.YELLOW).command("/pieterpost").tooltip("Klik op mij!").then("]").color(ChatColor.DARK_GRAY)
                    .toJSONString()
            ;
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + p.getName() + " " + json);
                    //            ChatUtils.sendMsg(p, "&8[&aNPC&8] &aPieter Post&8: &fHey, " + p.getName() + "! Ik heb nog &3" + pendingDeliveries + " bezorgingen &fvoor je! Kom ze halen op het postkantoor!");
        }
    }

}
