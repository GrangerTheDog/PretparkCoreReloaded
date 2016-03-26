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

package nl.HorizonCraft.PretparkCore.Bundles.Mazes;

import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementsEnum;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * This class has been created on 09/19/2015 at 6:47 PM by Cooltimmetje.
 */
public class MazeCommand implements CommandExecutor {

    private HashMap<String,Long> mazeTime = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getName().equalsIgnoreCase("maze")){
            if(!(sender instanceof Player)){
                if(args.length == 3){
                    Player p = Bukkit.getPlayer(args[1]);
                    if(p != null){
                        CorePlayer cp = PlayerUtils.getProfile(p);
                        switch (args[0]) {
                            case "complete":
                                if (mazeTime.containsKey(p.getName())) {

                                    switch (args[2]){
                                        case "1":
                                            cp.awardAchievement(p, AchievementsEnum.MAZE_COMPLETE_1);
                                            cp.addCoins(p, MiscUtils.randomInt(5, 10), "Cooltimmetje's Maze of Evil opgelost!", true, true);
                                            cp.addExp(p, MiscUtils.randomInt(10, 30), "Cooltimmetje's Maze of Evil opgelost!", true, true);
                                            Location loc = new Location(p.getWorld(), -78, 66, -381).add(0.5, 0, 0.5);
                                            p.teleport(loc);

                                            if(cp.getMaze_1_record() == 0 || cp.getMaze_1_record() > getPlainTime(p)){
                                                cp.setMaze_1_record(getPlainTime(p));
                                                ChatUtils.sendMsgTag(p, "Doolhof", "&lNIEUW RECORD! &aJe tijd is: &c" + getTime(p) + "&a!");
                                                MiscUtils.shootFirework(p.getLocation(), Variables.WORLD_NAME, true);
                                            } else {
                                                ChatUtils.sendMsgTag(p, "Doolhof", "Goed gedaan! Je tijd is: &c" + getTime(p) + "&a! &oJe snelste tijd is: &c&o" + MiscUtils.formatTime(cp.getMaze_1_record()));
                                            }

                                            break;
//                                        case "2":
//                                            cp.awardAchievement(p, AchievementsEnum.MAZE_COMPLETE_2);
//                                            cp.addCoins(p, MiscUtils.randomInt(5, 10), "Haunted Mansion opgelost!", true, true);
//                                            cp.addExp(p, MiscUtils.randomInt(10, 30), "Haunted Mansion opgelost!", true, true);
//                                            Location loc2 = new Location(p.getWorld(), -279, 80, -270);
//                                            loc2.setYaw(-90);
//                                            p.teleport(loc2);
//
//                                            if(cp.getMaze_2_record() == 0 || cp.getMaze_2_record() > getPlainTime(p)){
//                                                cp.setMaze_2_record(getPlainTime(p));
//                                                ChatUtils.sendMsgTag(p, "Doolhof", "&lNIEUW RECORD! &aJe tijd is: &c" + getTime(p) + "&a!");
//                                                MiscUtils.shootFirework(p.getLocation(), Variables.WORLD_NAME, true);
//                                            } else {
//                                                ChatUtils.sendMsgTag(p, "Doolhof", "Goed gedaan! Je tijd is: &c" + getTime(p) + "&a! &oJe snelste tijd is: &c&o" + MiscUtils.formatTime(cp.getMaze_2_record()));
//                                            }
//
//                                            break;
                                    }

                                    mazeTime.remove(p.getName());
                                } else {
                                    ChatUtils.sendMsgTag(p, "Doolhof", ChatUtils.error + "Je hebt geen challenge actief!");
                                }
                                break;

                            case "start":
                                Location loc;
                                    switch (args[2]) {
                                        case "1":
                                            loc = new Location(p.getWorld(), -76, 66, -384).add(0.5, 0, 0.5);
                                            loc.setYaw(180);
                                            p.teleport(loc);
                                            break;
//                                        case "2":
//                                            loc = new Location(p.getWorld(), -283, 80, -280).add(0.5, 0, 0.5);
//                                            loc.setYaw(90);
//                                            p.teleport(loc);
//                                            break;
                                    }

                                mazeTime.put(p.getName(), System.currentTimeMillis());
                                ChatUtils.sendMsgTag(p, "Doolhof", "Challenge gestart! Vind snel de uitgang!");
                                break;

                            case "stop":

                                switch (args[2]){
                                    case "1":
                                        p.teleport(new Location(p.getWorld(), -76, 66, -378).add(0.5, 0, 0.5));
                                        break;
//                                    case "2":
//                                        Location loc2 = new Location(p.getWorld(), -279, 80, -273);
//                                        loc2.setYaw(-90);
//                                        p.teleport(loc2);
                                }

                                ChatUtils.sendMsgTag(p, "Doolhof", "Challenge gestopt!");
                                mazeTime.remove(p.getName());
                                break;
                        }
                    }
                }
            } else {
                sender.sendMessage("nee...");
            }
        }
        return true;
    }

    private String getTime(Player p) {
        int time = (int) (System.currentTimeMillis() - mazeTime.get(p.getName()))/1000;
        return MiscUtils.formatTime(time);
    }

    private int getPlainTime(Player p) {
        return (int) (System.currentTimeMillis() - mazeTime.get(p.getName()))/1000;
    }

}
