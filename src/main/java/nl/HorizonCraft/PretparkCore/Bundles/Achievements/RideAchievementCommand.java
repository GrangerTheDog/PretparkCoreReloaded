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

package nl.HorizonCraft.PretparkCore.Bundles.Achievements;

import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 10/12/2015 at 7:44 PM by Cooltimmetje.
 */
public class RideAchievementCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getName().equalsIgnoreCase("achievementride")){
            if(!(sender instanceof Player)){
                switch (args[0]){
                    default:
                        break;
                    case "fe":
                        for(Player p : Bukkit.getOnlinePlayers()){
                            Location pLoc = p.getLocation();
                            if(pLoc.getX() <= -43 && pLoc.getX() >= -53){
                                if(pLoc.getY() <= 70 && pLoc.getY() >= 65){
                                    if(pLoc.getZ() <= -597 && pLoc.getZ() >= -599) {
                                        if (p.isInsideVehicle()) {
                                            CorePlayer cp = PlayerUtils.getProfile(p);
                                            cp.awardAchievement(p, AchievementsEnum.FE_RIDE);
                                            cp.addExp(p, MiscUtils.randomInt(10, 50), "Farm Expediton ritje", true, true);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "dc":
                        for(Player p : Bukkit.getOnlinePlayers()){
                            Location pLoc = p.getLocation();
                            if(pLoc.getX() <= -166 && pLoc.getX() >= -176){
                                if(pLoc.getY() <= 74 && pLoc.getY() >= 69){
                                    if(pLoc.getZ() <= -616 && pLoc.getZ() >= -618) {
                                        if (p.isInsideVehicle()) {
                                            CorePlayer cp = PlayerUtils.getProfile(p);
                                            cp.awardAchievement(p, AchievementsEnum.DC_RIDE);
                                            cp.addExp(p, MiscUtils.randomInt(10, 50), "Dive Coaster ritje", true, true);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "ts": // -514,68,-552 en -500,72,-550
                        for(Player p : Bukkit.getOnlinePlayers()){
                            Location pLoc = p.getLocation();
                            if(pLoc.getX() <= -500 && pLoc.getX() >= -514){
                                if(pLoc.getY() <= 72 && pLoc.getY() >= 68){
                                    if(pLoc.getZ() <= -550 && pLoc.getZ() >= -552) {
                                        if (p.isInsideVehicle()) {
                                            CorePlayer cp = PlayerUtils.getProfile(p);
                                            cp.awardAchievement(p, AchievementsEnum.TS_RIDE);
                                            cp.addExp(p, MiscUtils.randomInt(10, 50), "The Swinger ritje", true, true);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "cr": // 257 64 -474 | 255 69 -466
                        for(Player p : Bukkit.getOnlinePlayers()){
                            Location pLoc = p.getLocation();
                            if(pLoc.getX() <= 257 && pLoc.getX() >= 255){
                                if(pLoc.getY() <= 69 && pLoc.getY() >= 64){
                                    if(pLoc.getZ() <= -466 && pLoc.getZ() >= -474) {
                                        if (p.isInsideVehicle()) {
                                            CorePlayer cp = PlayerUtils.getProfile(p);
                                            cp.awardAchievement(p, AchievementsEnum.CR_RIDE);
                                            cp.addExp(p, MiscUtils.randomInt(10, 50), "The Crush ritje", true, true);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                }
            } else {
                ChatUtils.sendNoPremTag((Player) sender, "SetRide");
            }
        }
        return true;
    }

}