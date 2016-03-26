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

package nl.HorizonCraft.PretparkCore.Commands.Admin;

import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 10/27/2015 at 16:00 by Cooltimmetje.
 */
public class ExperienceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (cmd.getLabel().equalsIgnoreCase("exp")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.getName().equals("Jordy010NL") || p.getName().equals("Cooltimmetje")) {
                    if (args.length >= 1) {
                        switch (args[0]) {
                            case "add":
                                if (args.length >= 3) {
                                    Player target = Bukkit.getPlayer(args[1]);
                                    if (target != null) {
                                        CorePlayer cp = PlayerUtils.getProfile(target);
                                        if (MiscUtils.isInt(args[2])) {
                                            int add = Integer.parseInt(args[2]);
                                            if (add >= 1) {
                                                cp.addExp(p, add, "Manuele transactie door: " + p.getDisplayName() + "&6", false, true);
                                            } else {
                                                ChatUtils.sendFalseInt(p, "ExpAdmin", args[2]);
                                            }
                                        } else {
                                            ChatUtils.sendFalseInt(p, "ExpAdmin", args[2]);
                                        }
                                    } else {
                                        ChatUtils.sendFaslePlayer(p, "ExpAdmin", args[1]);
                                    }
                                } else {
                                    ChatUtils.sendArugmentsError(p, "ExpAdmin", "/exp <add/remove/set> <player> <amount> &aof &o/exp mass <amount>");
                                }
                                break;
                            case "remove":
                                if (args.length >= 3) {
                                    Player target = Bukkit.getPlayer(args[1]);
                                    if (target != null) {
                                        CorePlayer cp = PlayerUtils.getProfile(target);
                                        if (MiscUtils.isInt(args[2])) {
                                            int remove = Integer.parseInt(args[2]);
                                            if (remove >= 1) {
                                                cp.removeExp(p, remove, "Manuele transactie door: " + p.getDisplayName() + "&6", true);
                                            } else {
                                                ChatUtils.sendFalseInt(p, "ExpAdmin", args[2]);
                                            }
                                        } else {
                                            ChatUtils.sendFalseInt(p, "ExpAdmin", args[2]);
                                        }
                                    } else {
                                        ChatUtils.sendFaslePlayer(p, "ExpAdmin", args[1]);
                                    }
                                } else {
                                    ChatUtils.sendArugmentsError(p, "ExpAdmin", "/exp <add/remove/set> <player> <amount> &aof &o/exp mass <amount>");
                                }
                                break;
                            case "set":
                                if (args.length >= 3) {
                                    Player target = Bukkit.getPlayer(args[1]);
                                    if (target != null) {
                                        CorePlayer cp = PlayerUtils.getProfile(target);
                                        if (MiscUtils.isInt(args[2])) {
                                            int set = Integer.parseInt(args[2]);
                                            if (set >= 1) {
                                                cp.setExp(p, set, "Manuele transactie door: " + p.getDisplayName() + "&6", true);
                                            } else {
                                                ChatUtils.sendFalseInt(p, "ExpAdmin", args[2]);
                                            }
                                        } else {
                                            ChatUtils.sendFalseInt(p, "ExpAdmin", args[2]);
                                        }
                                    } else {
                                        ChatUtils.sendFaslePlayer(p, "ExpAdmin", args[1]);
                                    }
                                } else {
                                    ChatUtils.sendArugmentsError(p, "ExpAdmin", "/exp <add/remove/set> <player> <amount> &aof &o/exp mass <amount>");
                                }
                                break;
                            case "mass":
                                if (args.length >= 2) {
                                    if (MiscUtils.isInt(args[1])) {
                                        int set = Integer.parseInt(args[1]);
                                        if (set >= 1) {
                                            for (Player target : Bukkit.getOnlinePlayers()) {
                                                CorePlayer cp = PlayerUtils.getProfile(target);
                                                cp.addExp(p, set, "Gekregen van: " + p.getDisplayName() + "&6", false, true);
                                            }
                                        } else {
                                            ChatUtils.sendFalseInt(p, "ExpAdmin", args[1]);
                                        }
                                    } else {
                                        ChatUtils.sendFalseInt(p, "ExpAdmin", args[1]);
                                    }
                                } else {
                                    ChatUtils.sendArugmentsError(p, "ExpAdmin", "/exp <add/remove/set> <player> <amount> &aof &o/exp mass <amount>");
                                }
                                break;
                            default:
                                ChatUtils.sendArugmentsError(p, "ExpAdmin", "/exp <add/remove/set> <player> <amount> &aof &o/exp mass <amount>");
                                break;
                        }
                    } else {
                        ChatUtils.sendArugmentsError(p, "ExpAdmin", "/exp <add/remove/set> <player> <amount> &aof &o/exp mass <amount>");
                    }
                } else if (p.getName().equals("xBrandy")) {
                    ChatUtils.sendMsgTag(p, "AntiBrandy", "Nee Brandy... Nee.");
                } else {
                    ChatUtils.sendNoPremTag(p, "ExpAdmin");
                }
            }
        }
        return false;
    }


}
