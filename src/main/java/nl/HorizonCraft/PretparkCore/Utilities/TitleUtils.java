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
