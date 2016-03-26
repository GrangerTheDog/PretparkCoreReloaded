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