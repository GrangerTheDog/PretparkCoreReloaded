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

package nl.HorizonCraft.PretparkCore.Bundles.Navigation;

import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PointUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Cooltimmetje on 12/29/2015 at 11:24 AM.
 */
public class ChangePointStateCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (cmd.getLabel().equalsIgnoreCase("warpstate")) {
            if(args.length >= 2){
                if(MiscUtils.isInt(args[0])){
                    NavigationPoint navPoint = PointUtils.getById(Integer.parseInt(args[0]));
                    if(navPoint != null){
                        PointState ps = PointState.valueOf(args[1].toUpperCase());
                        if(ps != null){
                            navPoint.setPointState(ps);
//                            ChatUtils.bcMsgTag("Omroep", navPoint.getName() + " is nu &" + ps.getColorCode() + ps.getFriendlyName() + "&a!");
                            Bukkit.broadcastMessage(MiscUtils.color("&9&lOmroep&9&l> &a" + navPoint.getName() + " is nu &" + ps.getColorCode() + ps.getFriendlyName() + "&a!"));
                        } else {
                            if(sender instanceof Player){
                                Player p = (Player) sender;
                                ChatUtils.sendMsgTag(p, "WarpState", ChatUtils.error + "Deze Warp Status bestaat niet!");
                            } else {
                                sender.sendMessage("Unknown warp state: " + args[1]);
                            }
                        }
                    } else {
                        if(sender instanceof Player){
                            Player p = (Player) sender;
                            ChatUtils.sendMsgTag(p, "WarpState", ChatUtils.error + "Deze warp bestaat niet!");
                        } else {
                            sender.sendMessage("Unknown warp id: " + args[0]);
                        }
                    }
                } else {
                    if(sender instanceof Player){
                        Player p = (Player) sender;
                        ChatUtils.sendFalseInt(p, "WarpState", args[0]);
                    } else {
                        sender.sendMessage("This is not a integer: " + args[0]);
                    }
                }
            } else {
                if(sender instanceof Player){
                    Player p = (Player) sender;
                    ChatUtils.sendArugmentsError(p, "WarpState", "/warpstate <id> <state>");
                } else {
                    sender.sendMessage("Usage: /warpstate <id> <state>");
                }
            }
        }
        return false;
    }

}
