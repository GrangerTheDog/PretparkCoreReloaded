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
