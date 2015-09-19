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

package nl.HorizonCraft.PretparkCore.Commands;

import nl.HorizonCraft.PretparkCore.Enums.AchievementsEnum;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
                        if(args[0].equals("complete")){
                            if(args[2].equals("1")){
                                cp.awardAchievement(p, AchievementsEnum.MAZE_COMPLETE_1);
                                cp.addCoins(p, 5, "Doolhof 1 opgelost!", true, true);
                                p.teleport(new Location(p.getWorld(), -78, 66, -381).add(0.5, 0, 0.5));
                            }
                            ChatUtils.sendMsgTag(p, "Doolhof", "Goed gedaan! Je tijd is: &c" + getTime(p) + "&a!");
                        } else if (args[0].equals("start")){
                            if(args[2].equals("1")){
                                p.teleport(new Location(p.getWorld(), -76, 66, -384).add(0.5, 0, 0.5));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 2));
                            }
                            mazeTime.put(p.getName(), System.currentTimeMillis());
                            ChatUtils.sendMsgTag(p, "Doolhof", "Challenge gestart! Vind snel de uitgang!");
                        } else if (args[0].equals("stop")){
                            if(args[2].equals("1")){
                                p.teleport(new Location(p.getWorld(), -76, 66, -378).add(0.5, 0, 0.5));
                            }
                            ChatUtils.sendMsgTag(p, "Doolhof", "Challenge gestopt!");
                            mazeTime.remove(p.getName());
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

}
