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

package nl.HorizonCraft.PretparkCore.Bundles.Navigation;

import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PointUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;

/**
 * Created by Cooltimmetje on 12/29/2015 at 11:24 AM.
 */
public class ChangePointStateCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (cmd.getLabel().equalsIgnoreCase("warpstate")) {
            if(args.length >= 2){
                if(MiscUtils.isInt(args[0])){
                    NavigationPoint navPoint = PointUtils.getById(Integer.parseInt(args[0]));
                    if(navPoint != null){
                        PointState ps = PointState.valueOf(args[1].toUpperCase());
                        if(ps != null){
                            navPoint.setPointState(ps);
                            ChatUtils.bcMsgTag("Omroep", navPoint.getName() + " is nu &" + ps.getColorCode() + ps.getFriendlyName() + "&a!");
                        } else {
                            if(sender instanceof Player){
                                Player p = (Player) sender;
                                ChatUtils.sendMsgTag(p, "WarpState", ChatUtils.error + "Deze Warp Status bestaat niet!");
                            } else {
                                sender.sendMessage("Unknown warp state: " + args[1]);
                            }
                        }
                    } else {
                        if(sender instanceof Player){
                            Player p = (Player) sender;
                            ChatUtils.sendMsgTag(p, "WarpState", ChatUtils.error + "Deze warp bestaat niet!");
                        } else {
                            sender.sendMessage("Unknown warp id: " + args[0]);
                        }
                    }
                } else {
                    if(sender instanceof Player){
                        Player p = (Player) sender;
                        ChatUtils.sendFalseInt(p, "WarpState", args[0]);
                    } else {
                        sender.sendMessage("This is not a integer: " + args[0]);
                    }
                }
            } else {
                if(sender instanceof Player){
                    Player p = (Player) sender;
                    ChatUtils.sendArugmentsError(p, "WarpState", "/warpstate <id> <state>");
                } else {
                    sender.sendMessage("Usage: /warpstate <id> <state>");
                }
            }
        }
        return false;
    }

}
