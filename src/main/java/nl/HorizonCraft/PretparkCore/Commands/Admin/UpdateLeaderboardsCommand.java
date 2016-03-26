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

import nl.HorizonCraft.PretparkCore.Bundles.Mazes.MazeLeaderboards;
import nl.HorizonCraft.PretparkCore.Profiles.MysqlManager;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Cooltimmetje on 1/17/2016 at 8:00 PM.
 */
public class UpdateLeaderboardsCommand implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (cmd.getLabel().equals("updateleaderboards")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.getName().equals("Jordy010NL") || p.getName().equals("Cooltimmetje")) {
                    ChatUtils.sendMsgTag(p, "Leaderboards", "Leaderboards updaten...");
                    for(Player pl : Bukkit.getOnlinePlayers()){
                        MysqlManager.saveRecords(pl);
                    }
                    MazeLeaderboards.load(true);
                    ChatUtils.sendMsgTag(p, "Leaderboards", "&2&lGeslaagd! &aLeaderboards geupdated!");
                } else {
                    ChatUtils.sendNoPremTag(p, "Leaderboards");
                }
            }
        }
        return false;
    }

}
