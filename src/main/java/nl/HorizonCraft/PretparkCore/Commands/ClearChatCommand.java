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

package nl.HorizonCraft.PretparkCore.Commands;

import nl.HorizonCraft.PretparkCore.Bundles.Ranks.RanksEnum;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 09/9/14/2015/2015 at 8:20 PM by 78wesley.
 */

public class ClearChatCommand implements CommandExecutor {

    private RanksEnum permLevel = RanksEnum.MANAGER;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) { //TODO: Fix this.
       if (cmd.getLabel().equalsIgnoreCase("cc")) {
           ChatUtils.sendMsgTag(((Player)sender), "ClearChat", "&cDeprecated, need to fix");
       }
        return false;
    }

}
