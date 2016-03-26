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

package nl.HorizonCraft.PretparkCore.Listeners;

import nl.HorizonCraft.PretparkCore.Bundles.Ranks.RanksEnum;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * This class has been created on 10/09/2015 at 6:19 PM by Cooltimmetje.
 */
public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        CorePlayer cp = PlayerUtils.getProfile(event.getPlayer());

        if(RanksEnum.hasPermission(cp, RanksEnum.MANAGER)){
            event.setFormat(MiscUtils.color("%s&8: &b%s"));
        } else {
            event.setFormat(MiscUtils.color("%s&8: &f%s"));
        }

        if (RanksEnum.hasPermission(cp, RanksEnum.VIP)) {
            event.setMessage(MiscUtils.color(event.getMessage()));
        }

        if(!RanksEnum.hasPermission(cp, RanksEnum.TD)) {
            event.setMessage(event.getMessage().replaceAll("(?i)skype", "Discord"));
        }
    }

}
