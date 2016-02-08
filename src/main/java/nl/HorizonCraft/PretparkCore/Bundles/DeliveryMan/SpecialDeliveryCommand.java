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

import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Profiles.MysqlManager;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Cooltimmetje on 2/8/2016 at 4:49 PM.
 */
public class SpecialDeliveryCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getLabel().equalsIgnoreCase("specialdelivery") || cmd.getLabel().equalsIgnoreCase("sd")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.getName().equals("Jordy010NL") || p.getName().equals("Cooltimmetje")) {
                    if(args.length >= 6){
                        int[] currencies = {0,0,0,0,0};
                        for(int i=1;i < 6;i++){
                            if(MiscUtils.isInt(args[i])){
                                currencies[i-1] = Integer.parseInt(args[i]);
                            } else {
                                ChatUtils.sendFalseInt(p, "SpecialDelivery", args[i]);
                                return false;
                            }
                        }
                        Player target = Bukkit.getPlayer(args[0]);
                        if(target == null){ //Write to database
                            MysqlManager.loadForDelivery(p, args[0], currencies);
                        } else { //Add to cached profile
                            CorePlayer cp = PlayerUtils.getProfile(target);
                            cp.setCoinDelivery(cp.getCoinDelivery() + currencies[0]);
                            cp.setExpDelivery(cp.getExpDelivery() + currencies[1]);
                            cp.setBoxDelivery(cp.getBoxDelivery() + currencies[2]);
                            cp.setKeyDelivery(cp.getKeyDelivery() + currencies[3]);
                            cp.setDustDelivery(cp.getDustDelivery() + currencies[4]);

                            ChatUtils.sendMsgTag(p, "SpecialDelivery", ChatUtils.success + "Deze speciale bezorging ligt nu op het postkantoor, en kan worden opgehaald!");
                        }
                    } else {
                        ChatUtils.sendArugmentsError(p, "SpecialDelivery", "/specialdelivery <player> <coins> <exp> <boxes> <keys> <dust>");
                    }
                } else {
                    ChatUtils.sendNoPremTag(p,"SpecialDelivery");
                }
            } else {
                sender.sendMessage("Only players can execute this command!");
            }
        }
        return false;
    }
}
