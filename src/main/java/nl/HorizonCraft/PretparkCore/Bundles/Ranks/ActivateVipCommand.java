/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2016 Tim Medema
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

package nl.HorizonCraft.PretparkCore.Bundles.Ranks;

import mkremins.fanciful.FancyMessage;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Profiles.MysqlManager;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Cooltimmetje on 2/14/2016 at 3:19 PM.
 */
public class ActivateVipCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getLabel().equals("activatevip")){
            if(!(sender instanceof Player)){
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null){
                    ChatUtils.sendMsgTag(target, "Store", "Betaling ontvangen! Bezig met verwerken...");
                    int hours = Integer.parseInt(args[1]);
                    CorePlayer cp = PlayerUtils.getProfile(target);
                    if(hours == 0){
                        cp.setRank(RanksEnum.VIP_LIFE);
                        cp.updateRank(target);
                    } else {
                        long expire = cp.getRankExpire();
                        if(expire == 0){
                            expire = System.currentTimeMillis();
                        }
                        cp.setRank(RanksEnum.VIP);
                        cp.setRankExpire(expire + (hours*3600*1000));
                        cp.updateRank(target);
                    }
                    ChatUtils.sendMsgTag(target, "Store", "Bedankt voor het supporten van onze server! Geniet van je VIP rang!");
                    ChatUtils.sendMsgTag(target, "Rang", "Je bent nu een VIP!");
                    for(Player p : Bukkit.getOnlinePlayers()){
                        if(p != target) {
                            FancyMessage msg = new FancyMessage("Store> ").color(ChatColor.BLUE);
                            msg.then(target.getName()).color(ChatColor.RED).then(" heeft zojuist de server gesupport in de store door VIP te kopen! Support onze server ook! ").color(ChatColor.GREEN);
                            msg.then("Ga naar store.horizoncraft.nl voor meer informatie!").color(ChatColor.AQUA).link("http://store.horizoncraft.nl/").tooltip(MiscUtils.color("&BKlik op mij!"));
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + p.getName() + " " + msg.toJSONString());
                        }
                    }
                } else {
                    int hours = Integer.parseInt(args[1]);
                    MysqlManager.updateRank(args[0],hours == 0 ? RanksEnum.VIP_LIFE : RanksEnum.VIP, hours);
                    for(Player p : Bukkit.getOnlinePlayers()){
                            FancyMessage msg = new FancyMessage("Store> ").color(ChatColor.BLUE);
                            msg.then(args[0]).color(ChatColor.RED).then(" heeft zojuist de server gesupport in de store door VIP te kopen! Support onze server ook! ").color(ChatColor.GREEN);
                            msg.then("Ga naar store.horizoncraft.nl voor meer informatie!").color(ChatColor.AQUA).link("http://store.horizoncraft.nl/").tooltip(MiscUtils.color("&BKlik op mij!"));
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + p.getName() + " " + msg.toJSONString());
                    }
                }
            } else {
                Player p = (Player) sender;
                ChatUtils.sendNoPremTag(p,"ActivateVip");
            }
        }
        return false;
    }
}
