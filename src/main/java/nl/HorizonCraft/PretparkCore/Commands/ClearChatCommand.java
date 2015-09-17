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

package nl.HorizonCraft.PretparkCore.Commands;

import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 09/9/14/2015/2015 at 8:20 PM by 78wesley.
 */

public class ClearChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
       if (cmd.getLabel().equalsIgnoreCase("cc"))  {
            if(!(sender instanceof Player)){
                return false;
            }
            Player p = (Player) sender;

           if(args.length == 0){
               if(p.hasPermission("pc.cc")){
                    clearChatAll(p);
                   return true;
               } else {
                    clearChat(p);
                   return true;
               }
           }

           if(args.length >= 1){
               if(args[0].equalsIgnoreCase("-help")){
                   if(p.hasPermission("pc.cc")){
                       ChatUtils.sendMsgTag(p, "ClearChat", "Command gebruik: &o/cc [-help | -own | player]");
                       return true;
                   } else {
                       ChatUtils.sendMsgTag(p, "ClearChat", "Command gebruik: &o/cc [-help]");
                       return true;
                   }
               } else if(p.hasPermission("pc.cc")){
                   if(args[0].equalsIgnoreCase("-own")){
                       clearChat(p);
                       return true;
                   } else {
                       Player target = Bukkit.getPlayer(args[0]);
                       if(target != null){
                           clearChat(p, target);
                           return true;
                       } else {
                           ChatUtils.sendMsgTag(p, "ClearChat", ChatUtils.error + "Deze speler bestaat niet.");
                           return false;
                       }
                   }
               } else {
                   clearChat(p);
                   return true;
               }
           }
       }
        return false;
    }

    //Clears own chat
    private void clearChat(Player p){
        ChatUtils.clearChat(p);
        ChatUtils.sendMsgTag(p, "ClearChat", "Je hebt je eigen chat gecleard.");
    }

    //Clears target chat
    private void clearChat(Player p, Player target){
        ChatUtils.sendMsgTag(p, "ClearChat", "Je hebt de chat van " + target.getDisplayName() + " &agecleard");
        ChatUtils.clearChat(target);
        ChatUtils.bcMsgTag("ClearChat", "Jouw chat werd gecleard door: " + p.getDisplayName() + "&a!");
    }

    //Clears all chat, except those with permission
    private void clearChatAll(Player p){
        for(Player pl : Bukkit.getOnlinePlayers()){
            if(!pl.hasPermission("pc.cc")){
                ChatUtils.clearChat(pl);
            } else {
                ChatUtils.sendMsgTag(p, "ClearChat", "Jouw chat word niet gecleard omdat je hiertoe permissie hebt, wil je alsnog je chat clearen? &o/cc -own");
            }
        }

        ChatUtils.bcMsgTag("ClearChat", "Alle chats werden gecleard door: " + p.getDisplayName() + "&a!");
    }
}
