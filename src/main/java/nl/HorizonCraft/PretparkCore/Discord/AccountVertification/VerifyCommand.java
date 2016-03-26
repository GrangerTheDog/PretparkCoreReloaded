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
