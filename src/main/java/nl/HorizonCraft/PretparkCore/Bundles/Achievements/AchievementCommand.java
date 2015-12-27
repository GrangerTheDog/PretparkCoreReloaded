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

package nl.HorizonCraft.PretparkCore.Bundles.Achievements;

import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
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
            if(sender instanceof Player){
                ChatUtils.sendNoPremTag((Player)sender, "AwardAchievement");
                return false;
            } else {
                if(args.length >= 2){
                    switch (args[0]){
                        default:
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
                }
            }
        }
        return false;
    }

}
