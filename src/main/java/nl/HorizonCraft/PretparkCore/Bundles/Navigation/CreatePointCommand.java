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

import nl.HorizonCraft.PretparkCore.Bundles.Ranks.RanksEnum;
import nl.HorizonCraft.PretparkCore.Profiles.MysqlManager;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Cooltimmetje on 12/28/2015 at 7:08 PM.
 */
public class CreatePointCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getLabel().equalsIgnoreCase("createwarp")) {
                if(RanksEnum.hasPermission(p,RanksEnum.BOUWER)){
                    if (args.length >= 2) {

                        if(PointType.valueOf(args[0].toUpperCase()) != null){
                            ChatUtils.sendMsgTag(p, "CreateWarp", "Warp aanmaken...");

                            StringBuilder sb = new StringBuilder();
                            for (int i=1; i < args.length; i++){
                                sb.append(args[i]).append(" ");
                            }

                            String sb1 = String.valueOf(p.getLocation().getX()) + "," +
                                    p.getLocation().getY() + "," +
                                    p.getLocation().getZ() + "," +
                                    p.getLocation().getYaw() + "," +
                                    p.getLocation().getPitch();

                            MysqlManager.addWarp(sb.toString().trim(), sb1.trim(), PointType.valueOf(args[0].toUpperCase()));
                            Variables.navigationPoints.clear();
                            MysqlManager.getWarps();
                            ChatUtils.sendMsgTag(p, "CreateWarp", "Warp aangemaakt!");
                        } else {
                            ChatUtils.sendMsgTag(p, "CreateWarp", ChatUtils.error + "Ongeldig Type! Kies uit: SHOP, WARP, RIDE, MAZE, PARKOUR");
                        }

                    } else {
                        ChatUtils.sendArugmentsError(p, "CreateWarp", "/createpoint <type> <name...>");
                    }
                } else {
                    ChatUtils.sendNoPremTag(p, "CreateWarp");
                }
            }
        }
        return false;
    }

}
