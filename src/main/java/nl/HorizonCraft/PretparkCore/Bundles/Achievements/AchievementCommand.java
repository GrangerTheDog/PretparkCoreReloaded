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

import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import nl.HorizonCraft.PretparkCore.Utilities.ScheduleUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Cooltimmetje on 12/27/2015 at 1:53 PM.
 */
public class AchievementCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (cmd.getName().equalsIgnoreCase("awardachievement")) {
            if(sender instanceof Player && !sender.getName().equals("Cooltimmetje") && !sender.getName().equals("Jordy010NL")){
                ChatUtils.sendNoPremTag((Player)sender, "AwardAchievement");
                return false;
            } else {
                if(Bukkit.getPlayer(args[1]) != null){
                    if(args.length >= 2){
                        if(!MiscUtils.isInt(args[0])){
                            switch (args[0]){
                                default:
                                    if(sender instanceof Player) {
                                        ChatUtils.sendMsgTag((Player) sender, "AwardAchievement", ChatUtils.error + "Deze achievement bestaat niet!");
                                    }
                                    break;
                                case "grot":
                                    PlayerUtils.getProfile(Bukkit.getPlayer(args[1])).awardAchievement(Bukkit.getPlayer(args[1]), AchievementsEnum.GROT_FIND);

                                    final String[] argsF = args;
                                    ScheduleUtils.scheduleTask(30, new Runnable() {
                                        @Override
                                        public void run() {
                                            Bukkit.getPlayer(argsF[1]).playSound(Bukkit.getPlayer(argsF[1]).getLocation(), Sound.VILLAGER_YES, 100, 1);
                                            ChatUtils.sendMsg(Bukkit.getPlayer(argsF[1]), "&8[&aNPC&8] &aOlaf&8: &fOh, je hebt me gevonden, ik zit hier al een paar jaar vast en heb dit in die tijd een beetje opgebouwd" +
                                                    " zodat ik me een beetje thuis voel. Je mag hier zolang blijven als je wilt, maar ik wil hier niet meer weg, want dit is nu mijn thuis.");
                                        }
                                    });
                                    break;
                            }
                        } else {

                            AchievementsEnum achievement = AchievementsEnum.getById(Integer.parseInt(args[0]));
                            if(achievement != null) {
                                PlayerUtils.getProfile(Bukkit.getPlayer(args[1])).awardAchievement(Bukkit.getPlayer(args[1]), achievement);
                                ChatUtils.sendMsgTag((Player)sender, "AwardAchievement", "Achievement &c" + achievement.getName()+ "&a toegekend aan &c" + args[1] + "&a!");
                            } else {
                                ChatUtils.sendMsgTag((Player)sender, "AwardAchievement", ChatUtils.error + "Deze achievement bestaat niet!");
                            }
                        }
                    }
                } else {
                    ChatUtils.sendFaslePlayer((Player)sender, "AwardAchievement", args[1]);
                }
            }
        }
        return false;
    }

}
