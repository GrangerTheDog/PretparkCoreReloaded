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
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 09/12/2015 at 10:29 AM by Cooltimmetje.
 */
public class FixGamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getLabel().equalsIgnoreCase("fixgm")){
            if(!(sender instanceof Player) || RanksEnum.hasPermission(((Player)sender),RanksEnum.BOUWER)){
                sender.sendMessage(MiscUtils.color("&9FixGamemodes> &aAlle gamemodes zijn goedgezet!"));
                for(Player p : Bukkit.getOnlinePlayers()){
                    PlayerUtils.configPlayer(p, false);
                }
            } else {
                ChatUtils.sendNoPremTag((Player) sender, "FixGamemodes");
            }
        }
        return false;
    }

}
