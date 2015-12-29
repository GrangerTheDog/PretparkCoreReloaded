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

import nl.HorizonCraft.PretparkCore.Profiles.MysqlManager;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Cooltimmetje on 12/28/2015 at 7:08 PM.
 */
public class CreatePointCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getLabel().equalsIgnoreCase("createwarp")) {
                if(p.hasPermission("pc.createwarp")){
                    if (args.length >= 2) {

                        if(PointType.valueOf(args[0].toUpperCase()) != null){
                            ChatUtils.sendMsgTag(p, "CreateWarp", "Warp aanmaken...");

                            StringBuilder sb = new StringBuilder();
                            for (int i=1; i < args.length; i++){
                                sb.append(args[i]);
                            }

                            String sb1 = String.valueOf(p.getLocation().getX()) + "," +
                                    p.getLocation().getY() + "," +
                                    p.getLocation().getZ() + "," +
                                    p.getLocation().getYaw() + "," +
                                    p.getLocation().getPitch();

                            MysqlManager.addWarp(sb.toString().trim(), sb1.trim(), PointType.valueOf(args[0].toUpperCase()));
                            Variables.navigationPoints.clear();
                            MysqlManager.getWarps();
                            ChatUtils.sendMsgTag(p, "CreateWarp", "Warp aangemaakt!");
                        } else {
                            ChatUtils.sendMsgTag(p, "CreateWarp", ChatUtils.error + "Ongeldig Type! Kies uit: SHOP, WARP, RIDE, MAZE, PARKOUR");
                        }

                    } else {
                        ChatUtils.sendArugmentsError(p, "CreateWarp", "/createpoint <type> <name...>");
                    }
                } else {
                    ChatUtils.sendNoPremTag(p, "CreateWarp");
                }
            }
        }
        return false;
    }

}
