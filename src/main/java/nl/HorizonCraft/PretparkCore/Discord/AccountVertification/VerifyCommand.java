/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2016 Tim Medema
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

package nl.HorizonCraft.PretparkCore.Discord.AccountVertification;

import mkremins.fanciful.FancyMessage;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Cooltimmetje on 2/16/2016 at 5:51 PM.
 */
public class VerifyCommand implements CommandExecutor {

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd = new Random();
    public static HashMap<String,String> codes = new HashMap<>();

    private static String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getLabel().equalsIgnoreCase("discord")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                CorePlayer cp = PlayerUtils.getProfile(p);
                if(cp.getDiscordID().equals("0")){
                    if(cp.getDiscordVerifyCode() != null){
                        ChatUtils.sendMsgTag(p, "Discord", "Om je Discord account te linken met je Minecraft account typ je het volgende in elk kanaal in onze Discord Server: \"&l!verify " + cp.getDiscordVerifyCode() + "&a\". Zit je nog niet op de Discord? Ga dan naar:");
                        ChatUtils.sendRaw(p, new FancyMessage("discord.horizoncraft.nl").color(ChatColor.RED).link("http://discord.horizoncraft.nl/").toJSONString());
                    } else {
                        cp.setDiscordVerifyCode(randomString(6).toUpperCase());
                        codes.put(cp.getDiscordVerifyCode(),p.getName());
                        ChatUtils.sendMsgTag(p, "Discord", "Om je Discord account te linken met je Minecraft account typ je het volgende in elk kanaal in onze Discord Server: \"&l!verify " + cp.getDiscordVerifyCode() + "&a\". Zit je nog niet op de Discord? Ga dan naar:");
                        ChatUtils.sendRaw(p, new FancyMessage("discord.horizoncraft.nl").color(ChatColor.RED).link("http://discord.horizoncraft.nl/").toJSONString());
                    }
                } else {
                    ChatUtils.sendMsgTag(p, "Discord", "Je Minecraft account is a gelinkt met Discord! Opnieuw toegang nodig tot de Discord server? Klik hier:");
                    ChatUtils.sendRaw(p, new FancyMessage("discord.horizoncraft.nl").color(ChatColor.RED).link("http://discord.horizoncraft.nl/").toJSONString());
                }
            }
        }
        return false;
    }
}
