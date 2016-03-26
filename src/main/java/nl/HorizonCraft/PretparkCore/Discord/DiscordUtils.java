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

package nl.HorizonCraft.PretparkCore.Discord;

import nl.HorizonCraft.PretparkCore.Bundles.Ranks.RanksEnum;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import sx.blah.discord.api.DiscordException;
import sx.blah.discord.api.MissingPermissionsException;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.HTTP429Exception;

/**
 * Created by Cooltimmetje on 2/17/2016 at 4:18 PM.
 */
public class DiscordUtils {

    public static void sendMessage(IChannel channel, String message){
        try {
            channel.sendMessage(message);
        } catch (MissingPermissionsException | HTTP429Exception | DiscordException e) {
            e.printStackTrace();
        }
    }

    public static void sendReply(IMessage message, String reply){
        try {
            message.reply(reply);
        } catch (MissingPermissionsException | HTTP429Exception | DiscordException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMessage(IMessage message){
        try {
            message.delete();
        } catch (MissingPermissionsException | HTTP429Exception | DiscordException e) {
            e.printStackTrace();
        }
    }

    public static void updateRoles(CorePlayer cp, IUser user){
        if(ConnectionManager.setupComplete){
            if (!cp.getDiscordID().equals("0")) {
                StringBuilder sb = new StringBuilder();
                sb.append(ConnectionManager.getRole("Geverifieerd")).append("-");
                String[] roles = cp.getRank().getDiscordRoles().split("-");
                for(int i=0; i < roles.length; i++){
                    sb.append(ConnectionManager.getRole(roles[i])).append("-");
                }
                if(cp.getRank() == RanksEnum.MANAGER){
                    if(cp.getPlayer().getDisplayName().contains("TD")){
                        sb.append(ConnectionManager.getRole("Technische Diesnt"));
                    } else if (cp.getPlayer().getDisplayName().contains("Bouwer")){
                        sb.append(ConnectionManager.getRole("Bouwer"));
                    }
                }

                try {
                    ConnectionManager.server.editUserRoles(user.getID(), sb.toString().split("-"));
                } catch (MissingPermissionsException | HTTP429Exception | DiscordException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
