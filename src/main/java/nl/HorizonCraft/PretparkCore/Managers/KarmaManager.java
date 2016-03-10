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
