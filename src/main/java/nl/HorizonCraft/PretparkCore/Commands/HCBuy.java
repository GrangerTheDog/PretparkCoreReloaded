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

import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Cooltimmetje on 3/3/2016 at 7:43 PM.
 */
public class HCBuy implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getLabel().equalsIgnoreCase("hcbuy")){
            if(!(sender instanceof Player)){
                if(args.length >= 2){
                    Player p = Bukkit.getPlayer(args[1]);
                    CorePlayer cp = PlayerUtils.getProfile(p);
                    switch (args[0]){
                        case "eend":
                            if(cp.getGerritFood() >= 10){
                                ChatUtils.sendMsgTag(p, "Gerrit", "Je kan maar maximaal 10 zakjes met Eenden Voer dragen!");
                            } else {
                                cp.removeCoins(p,2,"Eendenvoer Gekocht",true);
                                cp.setGerritFood(cp.getGerritFood() + 1);
                                ItemUtils.createDisplay(p, 5, Material.SEEDS, cp.getGerritFood(), 0, "&bEenden Voer", "&bGeef dit aan Gerrit de Eend.");
                            }
                            break;
                    }
                }
            }
        }
        return false;
    }
}
