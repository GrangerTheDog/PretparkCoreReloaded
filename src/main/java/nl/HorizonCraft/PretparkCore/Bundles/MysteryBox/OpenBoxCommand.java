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

package nl.HorizonCraft.PretparkCore.Bundles.MysteryBox;

import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Cooltimmetje on 1/31/2016 at 8:37 PM.
 */
public class OpenBoxCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getLabel().equalsIgnoreCase("openbox")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(p.getName().equals("Jordy010NL") || p.getName().equals("Cooltimmetje")){
                    if(args.length >= 1){
                        Weight weight = Weight.valueOf(args[0].toUpperCase());
                        if(weight != null){
                            if(args.length == 1) {
                                BoxAnimation.openBox(p, weight);
                            }
                        } else {
                            ChatUtils.sendMsgTag(p, "OpenBox", "Ongeldige weight.");
                        }
                        if(args.length >= 2){
                            if(args[1].equals("-all")){
                                if(args.length >= 3){
                                    Player target = Bukkit.getPlayer(args[2]);
                                    if(target != null){
                                        BoxAnimation.openBox(93,target,weight);
                                        BoxAnimation.openBox(94,target,weight);
                                        BoxAnimation.openBox(95,target,weight);
                                        BoxAnimation.openBox(96,target,weight);
                                        BoxAnimation.openBox(97,target,weight);
                                        BoxAnimation.openBox(98,target,weight);
                                        BoxAnimation.openBox(99,target,weight);
                                        BoxAnimation.openBox(100,target,weight);
                                        BoxAnimation.openBox(101,target,weight);
                                        BoxAnimation.openBox(102,target,weight);
                                        BoxAnimation.openBox(103,target,weight);
                                        return true;
                                    } else {
                                        ChatUtils.sendFaslePlayer(p, "OpenBox", args[2]);
                                        return true;
                                    }
                                }
                                BoxAnimation.openBox(93,p,weight);
                                BoxAnimation.openBox(94,p,weight);
                                BoxAnimation.openBox(95,p,weight);
                                BoxAnimation.openBox(96,p,weight);
                                BoxAnimation.openBox(97,p,weight);
                                BoxAnimation.openBox(98,p,weight);
                                BoxAnimation.openBox(99,p,weight);
                                BoxAnimation.openBox(100,p,weight);
                                BoxAnimation.openBox(101,p,weight);
                                BoxAnimation.openBox(102,p,weight);
                                BoxAnimation.openBox(103,p,weight);
                                return true;
                            }
                            Player target = Bukkit.getPlayer(args[1]);
                            if(target != null){
                                BoxAnimation.openBox(target, weight);
                            } else {
                                ChatUtils.sendFaslePlayer(p, "OpenBox", args[1]);
                            }
                        }
                    } else {
                        ChatUtils.sendArugmentsError(p, "OpenBox", "/openbox <weight>");
                    }
                } else {
                    ChatUtils.sendNoPremTag(p, "OpenBox");
                }
            } else {
                sender.sendMessage("Only players can execute this command!");
            }
        }
        return false;
    }

}
