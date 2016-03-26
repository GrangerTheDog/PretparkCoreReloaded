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

package nl.HorizonCraft.PretparkCore.Bundles.Powerups.Commands;

import nl.HorizonCraft.PretparkCore.Bundles.Powerups.PowerupSpawner;
import nl.HorizonCraft.PretparkCore.Bundles.Powerups.PowerupViewMenu;
import nl.HorizonCraft.PretparkCore.Bundles.Ranks.RanksEnum;
import nl.HorizonCraft.PretparkCore.Profiles.MysqlManager;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Cooltimmetje on 1/31/2016 at 1:35 PM.
 */
public class PowerupCommand implements CommandExecutor {

    private HashMap<String,Integer> confirm = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getLabel().equalsIgnoreCase("powerup")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(RanksEnum.hasPermission(p,RanksEnum.BOUWER)){
                    if(args.length >= 1){
                        switch (args[0]){
                            case "-add":

                                Location location = p.getLocation();
                                String locationString = MiscUtils.locationToString(location, false, false);
                                ChatUtils.sendMsgTag(p, "AddPowerup", "Powerup toevoegen...");
                                Variables.powerupLocations.clear();
                                MysqlManager.createPowerupLocation(locationString);
                                MysqlManager.loadPowerupLocations();
                                ChatUtils.sendMsgTag(p, "AddPowerup", ChatUtils.success + "Powerup toegevoegd!");

                                break;
                            case "-remove":
                                if(args.length >= 2){
                                    if(MiscUtils.isInt(args[1])){
                                        int id = Integer.parseInt(args[1]);
                                        if(!confirm.containsKey(p.getName()) || confirm.get(p.getName()) != id){
                                            confirm.put(p.getName(), id);
                                            ChatUtils.sendMsgTag(p, "RemovePowerup", "Zeker weten dat je de powerup met ID &c" + id + " &awilt verwijderen? &oHerhaal de command om te bevestigen!");
                                        } else {

                                            ChatUtils.sendMsgTag(p, "RemovePowerup", "Powerup verwijderen...");
                                            Variables.powerupLocations.remove(id);
                                            MysqlManager.removePowerupLocation(id);
                                            ChatUtils.sendMsgTag(p, "RemovePowerup", ChatUtils.success + "Powerup verwijderd!");

                                        }
                                    } else {
                                        ChatUtils.sendFalseInt(p, "RemovePowerup", args[1]);
                                    }
                                } else {
                                    ChatUtils.sendArugmentsError(p, "RemovePowerup", "/powerup -remove <id>");
                                }
                                break;
                            case "-view":
                                PowerupViewMenu.open(p);
                                break;
                            case "-debug":
                                PowerupSpawner.activateDebug();
                                ChatUtils.sendMsgTag(p, "PowerUpDebug", "Debug holograms zijn nu zichtbaar voor 10 seconden!");
                                break;
                            case "-spawn":
                                if(p.getName().equals("Jordy010NL") || p.getName().equals("Cooltimmetje")){
                                    PowerupSpawner.spawn();
                                } else {
                                    ChatUtils.sendNoPremTag(p, "SpawnPowerup");
                                }
                                break;
                            default:
                                ChatUtils.sendArugmentsError(p, "Powerup", "/powerup <-add/-remove/-view/-debug>");
                                break;
                        }
                    } else {
                        ChatUtils.sendArugmentsError(p, "Powerup", "/powerup <-add/-remove/-view/-debug>");
                    }
                } else {
                    ChatUtils.sendNoPremTag(p, "Powerup");
                }
            } else {
                sender.sendMessage("Only players can execute this command.");
            }
        }
        return false;
    }

}
