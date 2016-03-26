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

import nl.HorizonCraft.PretparkCore.Profiles.MysqlManager;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Cooltimmetje on 1/5/2016 at 2:36 PM.
 */
public class RevokeAchievementCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (cmd.getName().equalsIgnoreCase("revokeachievement")) {
            if(sender.getName().equals("Cooltimmetje")){
                Player p = (Player) sender;
                if(MiscUtils.isInt(args[0])){
                    int id = Integer.parseInt(args[0]);
                    ChatUtils.bcMsgTag("Database", "&c&lLET OP! &aDatabase update gestart! Dit &lkan &aeven lagg veroorzaken!");
                    MysqlManager.revokeAchievement(id);
                    ChatUtils.bcMsgTag("Database", "Database update voltooid!");
                }
            } else {
                ChatUtils.sendNoPremTag((Player)sender, "RevokeAchievement");
            }
        }
        return false;
    }
}
