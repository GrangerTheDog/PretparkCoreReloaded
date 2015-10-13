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

package nl.HorizonCraft.PretparkCore.Listeners;

import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementsEnum;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Profiles.MysqlManager;
import nl.HorizonCraft.PretparkCore.Utilities.*;
import nl.HorizonCraft.PretparkCore.Utilities.Packets.TitleUtils;
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

        ScheduleUtils.scheduleTask(20, new Runnable() {
            @Override
            public void run() {
                PlayerUtils.configPlayer(pfinal, false);

                TitleUtils.sendTitle(pfinal, "&eWelkom op " + Variables.SERVER_NAME, PacketPlayOutTitle.EnumTitleAction.TITLE, 20, 60, 20);
            }
        });

        ScheduleUtils.scheduleTask(40, new Runnable() {
            @Override
            public void run() {
                TitleUtils.sendTitle(pfinal, Variables.SERVER_NAME_SHORT  + " &8\u00BB &e" + Variables.SERVER_PING_MESSAGE, PacketPlayOutTitle.EnumTitleAction.SUBTITLE, 20, 60, 20);
            }
        });


        for(Player pl : Bukkit.getOnlinePlayers()){
            if(pl != p){
                ScoreboardUtils.updateScoreboard(pl, false);
            }
        }
        ScoreboardUtils.constructScoreboard(p);

        cp.awardAchievement(p, AchievementsEnum.FIRST_TIME_JOIN);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        Player p = event.getPlayer();
        event.setQuitMessage(MiscUtils.color("&9RageQuit> &e" + p.getName()));

        MysqlManager.saveData(p);
        MysqlManager.savePrefs(p);

        Variables.profile.remove(p.getName());

        for(Player pl : Bukkit.getOnlinePlayers()){
            if(pl != p){
                ScoreboardUtils.updateScoreboard(pl, true);
            }
        }
        ScoreboardUtils.destroyScoreboard(p);

        Variables.profile.remove(p.getName());
    }
}
