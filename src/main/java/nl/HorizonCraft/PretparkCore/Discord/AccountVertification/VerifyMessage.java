/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2016 HorizonCraft
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

import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementsEnum;
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
    public void onMessageRecievedEvent(MessageReceivedEvent event){
        ConnectionManager.runSetup(event.getMessage().getChannel().getGuild());
        if(event.getMessage().getContent().startsWith("!verify")){
            IMessage message = event.getMessage();
            DiscordUtils.deleteMessage(message);

            String[] messageArgs = message.getContent().split(" ");
            if(messageArgs.length >= 2){
                if(VerifyCommand.codes.containsKey(messageArgs[1].toUpperCase())){
                    Player p = Bukkit.getPlayer(VerifyCommand.codes.get(messageArgs[1].toUpperCase()));
                    CorePlayer cp = PlayerUtils.getProfile(p);
                    cp.setDiscordID(message.getAuthor().getID());
                    cp.awardAchievement(p, AchievementsEnum.DISCORD);
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
        } else if(event.getMessage().getContent().equals("!sendtomc")){
            Bukkit.broadcastMessage("[Discord] " + event.getMessage().getAuthor().getName() + ": " + event.getMessage().getContent().replace("!sendtomc ", " ").trim());
        }
    }


}
