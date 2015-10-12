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

import nl.HorizonCraft.PretparkCore.Profiles.MysqlManager;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 10/09/2015 at 6:38 PM by Cooltimmetje.
 */
public class AddRide implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(sender instanceof Player){
            if(cmd.getLabel().equalsIgnoreCase("addride")){
                Player p = (Player) sender;
                if(p.hasPermission("pc.addride")){
                    if(args.length >= 1){
                        ChatUtils.sendMsgTag(p, "AddRide", "Attractie aanmaken... &oEven geduld...");
                        StringBuilder sbName = new StringBuilder();
                        for(String s : args){
                            sbName.append(s).append(" ");
                        }
                        MysqlManager.addRide(sbName.toString().trim(), String.valueOf(p.getLocation().getX()) + "," + p.getLocation().getY() + "," + p.getLocation().getZ() + "," + p.getLocation().getYaw() + "," + p.getLocation().getPitch());
                        RideVars.rides.clear();
                        MysqlManager.getRides();
                        ChatUtils.sendMsgTag(p, "AddRide", "Attractie aangemaakt!");
                    } else {
                        ChatUtils.sendMsgTag(p, "AddRide", "Geef deze attractie een naam.");
                    }
                } else {
                    ChatUtils.sendNoPremTag(p, "AddRide");
                }
            }
        }
        return false;
    }
}
