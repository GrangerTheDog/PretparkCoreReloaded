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
