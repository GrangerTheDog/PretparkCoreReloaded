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

import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementsEnum;
import nl.HorizonCraft.PretparkCore.Bundles.DeliveryMan.DeliveryNotifier;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Profiles.MysqlManager;
import nl.HorizonCraft.PretparkCore.Utilities.*;
import nl.HorizonCraft.PretparkCore.Utilities.Packets.SpawnHologram;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * This class has been created on 09/9/11/2015/2015 at 7:18 PM by Cooltimmetje.
 */
public class JoinQuitListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        final Player pfinal = p;
        event.setJoinMessage(MiscUtils.color("&9Join> &e" + p.getName()));

        PlayerUtils.createProfile(p);
        CorePlayer cp = PlayerUtils.getProfile(p);

        MysqlManager.loadProfile(p);
        MysqlManager.loadPrefs(p);
        MysqlManager.loadRecords(p);

        ScheduleUtils.scheduleTask(20, new Runnable() {
            @Override
            public void run() {
                PlayerUtils.configPlayer(pfinal, false);

                TitleUtils.sendTitle(pfinal, "&aWelkom op " + Variables.SERVER_NAME, Variables.SERVER_NAME_SHORT + " &8\u00BB &a" + Variables.SERVER_PING_MESSAGE, 20, 80, 20);
            }
        });

        for(Player pl : Bukkit.getOnlinePlayers()){
            if(pl != p){
                ScoreboardUtils.updateScoreboard(pl, false);
            }
        }
        ScoreboardUtils.constructScoreboard(p);
        SpawnHologram.spawn(p);

        ScheduleUtils.scheduleTask(200, new Runnable() {
            @Override
            public void run() {
                DeliveryNotifier.notify(pfinal);
            }
        });

        cp.awardAchievement(p, AchievementsEnum.FIRST_TIME_JOIN);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        Player p = event.getPlayer();
        event.setQuitMessage(MiscUtils.color("&9RageQuit> &e" + p.getName()));

        MysqlManager.saveData(p);
        MysqlManager.savePrefs(p);
        MysqlManager.saveRecords(p);

        Variables.profile.remove(p.getName());

        for(Player pl : Bukkit.getOnlinePlayers()){
            if(pl != p){
                ScoreboardUtils.updateScoreboard(pl, true);
            }
        }
        ScoreboardUtils.destroyScoreboard(p);
        SpawnHologram.despawn(p);

        Variables.profile.remove(p.getName());
    }
}
