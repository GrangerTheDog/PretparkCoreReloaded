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

package nl.HorizonCraft.PretparkCore.Managers;

import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

/**
 * Created by Cooltimmetje on 2/15/2016 at 9:02 PM.
 */
public class KarmaManager implements Listener {

    private static boolean karmaActive = false;
    private static ArrayList<String> gotKarma = new ArrayList<>();
    private static long karmaStarted = 0;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        if(karmaActive){
            if(((System.currentTimeMillis() - karmaStarted)/1000) < 15){
                Player p = event.getPlayer();
                if(!gotKarma.contains(p.getName())){
                    CorePlayer cp = PlayerUtils.getProfile(p);
                    if(event.getMessage().toLowerCase().contains("gg")){
                        cp.addKarma(p, 5, "GG!", true, true);
                    }
                    if(event.getMessage().toLowerCase().contains("hax")) {
                        cp.removeKarma(p, 5, "HAX!", true);
                    }
                    gotKarma.add(p.getName());
                }
            } else {
                karmaActive = false;
            }
        }
    }

    public static void startKarma(){
        karmaActive = true;
        gotKarma.clear();
        karmaStarted = System.currentTimeMillis();
    }

}
