/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2016 HorizonCraft
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * In addition to the above:
 * All content in the repo/plugin is created by and owned by HorizonCraft, unless
 * stated otherwise. All content that is not created by us will be placed in their
 * original package, where they were found or that was set by the owner by default.
 *
 * You are free to use the code anywhere you like, but we will not provide ANY support
 * unless you are on our server using this plugin.
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
