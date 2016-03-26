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
