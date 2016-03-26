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

import nl.HorizonCraft.PretparkCore.Discord.ConnectionManager;
import nl.HorizonCraft.PretparkCore.Discord.DiscordUtils;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import sx.blah.discord.handle.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

/**
 * Created by Cooltimmetje on 2/16/2016 at 5:51 PM.
 */
public class VerifyMessage {

    @EventSubscriber
    public synchronized void onMessageRecievedEvent(MessageReceivedEvent event){
        ConnectionManager.runSetup(event.getMessage().getChannel().getGuild());
        if(event.getMessage().getContent().startsWith("!verify")){
            IMessage message = event.getMessage();
            DiscordUtils.deleteMessage(message);
            if(!event.getMessage().getAuthor().getRolesForGuild(ConnectionManager.server.getID()).contains(message.getChannel().getGuild().getRoleForID(ConnectionManager.getRole("Geverifieerd")))) {
                String[] messageArgs = message.getContent().split(" ");
                if (messageArgs.length >= 2) {
                    if (VerifyCommand.codes.containsKey(messageArgs[1].toUpperCase())) {
                        Player p = Bukkit.getPlayer(VerifyCommand.codes.get(messageArgs[1].toUpperCase()));
                        CorePlayer cp = PlayerUtils.getProfile(p);
                        cp.setDiscordID(message.getAuthor().getID());
//                        cp.awardAchievement(p, AchievementsEnum.DISCORD);
                        ChatUtils.sendMsgTag(p, "Discord", "Je Minecraft account is nu aan het Discord account &c" + message.getAuthor().getName() + " &agelinkt!");

                        VerifyCommand.codes.remove(messageArgs[1].toUpperCase());
                        DiscordUtils.sendReply(message, ":star: Je Discord account is nu aan het Minecraft account `" + p.getName() + "` gelinkt!");
                        cp.updateRank(p);
                    } else {
                        DiscordUtils.sendReply(message, ":x: Code niet gevonden. Zorg dat je online bent op de server en controleer je code. Heb je nog geen code, of wil je een nieuwe? Typ `/discord` op onze MC Server!");
                    }
                } else {
                    DiscordUtils.sendReply(message, ":x: Geef een vertificatie code op! Verkrijg een code door `/discord` op onze MC Server te typen.");
                }
            }
        } else if(event.getMessage().getContent().startsWith("!sendtomc")){
            Bukkit.broadcastMessage("[Discord] " + event.getMessage().getAuthor().getName() + ": " + event.getMessage().getContent().replace("!sendtomc ", " ").trim());
        }
    }


}
