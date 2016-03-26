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

package nl.HorizonCraft.PretparkCore.Utilities;

import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;
import io.puharesource.mc.titlemanager.api.TabTitleObject;
import io.puharesource.mc.titlemanager.api.TitleObject;
import io.puharesource.mc.titlemanager.api.animations.FrameSequence;
import io.puharesource.mc.titlemanager.api.animations.TitleAnimation;
import org.bukkit.entity.Player;

/**
 * This class has been created on 10/08/2015 at 7:45 PM by Cooltimmetje.
 */
public class TitleUtils {

    public static void sendTitle(Player p, String title, String subTitle, int fadeIn, int stay, int fadeOut){
        new TitleObject(MiscUtils.color(title), MiscUtils.color(subTitle)).setFadeIn(fadeIn).setStay(stay).setFadeOut(fadeOut).send(p);
    }

    public static void sendTitle(Player p, String title, TitleObject.TitleType titleType, int fadeIn, int stay, int fadeOut){
        new TitleObject(MiscUtils.color(title), titleType).setFadeIn(fadeIn).setStay(stay).setFadeOut(fadeOut).send(p);
    }

    public static void setTab(Player p, String header, String footer){
        new TabTitleObject(MiscUtils.color(header), MiscUtils.color(footer)).send(p);
    }

    public static void sendAction(Player p, String message){
        new ActionbarTitleObject(MiscUtils.color(message)).send(p);
    }

    public static void sendAction(final Player p, final String message, int seconds){
        for(int i=0; i < seconds; i++){
            if(i == 0){
                new ActionbarTitleObject(MiscUtils.color(message)).send(p);
            } else {
                ScheduleUtils.scheduleTask(20 * i, new Runnable() {
                    @Override
                    public void run() {
                        new ActionbarTitleObject(MiscUtils.color(message)).send(p);
                    }
                });
            }
        }
    }

    public static void sendTitle(Player p, FrameSequence title, FrameSequence subTitle){
        new TitleAnimation(title, subTitle).send(p);
    }




}
