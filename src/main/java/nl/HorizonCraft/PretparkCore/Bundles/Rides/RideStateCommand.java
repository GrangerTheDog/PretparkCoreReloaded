/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 HorizonCraft
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

package nl.HorizonCraft.PretparkCore.Bundles.Rides;

import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 10/09/2015 at 8:10 PM by Cooltimmetje.
 */
public class RideStateCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getName().equalsIgnoreCase("setride")){
            if(!(sender instanceof Player)){
                if(args.length >= 2){
                    if(MiscUtils.isInt(args[0])){
                        int id = Integer.parseInt(args[0]);
                        Ride ride = RideVars.rides.get(id);
                        switch (args[1]){
                            case "c":
                                ride.setRideState(RideState.CLOSED);
                                ChatUtils.bcMsgTag("Omroep", "De &c" + ride.getName() + " &ais nu &cgesloten&a.");
                                break;
                            case "o":
                                ride.setRideState(RideState.OPEN);
                                ChatUtils.bcMsgTag("Omroep", "De &2" + ride.getName() + " &ais nu &2geopend&a! &oOpen je AttractieMenu om te teleporteren.");
                                break;
                            case "m":
                                ride.setRideState(RideState.MAINTENANCE);
                                ChatUtils.bcMsgTag("Omroep", "De &6" + ride.getName() + " &ais nu &6 in onderhoud&a.");
                                break;
                        }
                    }
                }
            } else {
                ChatUtils.sendNoPremTag((Player) sender, "SetRide");
            }
        }
        return true;
    }

}
