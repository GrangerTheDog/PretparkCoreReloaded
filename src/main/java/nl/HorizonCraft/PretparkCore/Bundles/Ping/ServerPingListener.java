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

package nl.HorizonCraft.PretparkCore.Bundles.Ping;

import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * This class has been created on 10/07/2015 at 9:02 PM by Cooltimmetje.
 */
public class ServerPingListener implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent event){
        event.setMotd(MiscUtils.color(Variables.SERVER_NAME + " &8\u00BB &a" + Variables.SERVER_PING_MESSAGE + "\n" + Variables.SERVER_NAME_SHORT + " &8\u00BB &b&o" + getSaying()));
    }

    private String getSaying(){
        String saying = "Unable to load...";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());

        switch(date) {
            case "21/10":
                saying = "Gefeliciteerd Cooltimmetje!";
                break;
            case "30/05":
                saying = "Gefeliciteerd BekertjeZuivel!";
                break;
            case "25/12":
                saying = "Gefeliciteerd Jordy010NL!";
                break;
            case "06/07":
                saying = "Gefeliciteerd ";
                break;
            case "26/10":
                saying = "Gefeliciteerd SVENBEER!";
                break;
            case "21/02":
                saying = "Gefeliciteerd Jordyvz01";
                break;
            case "08/02":
                saying = "Gefeliciteerd jecontior!";
                break;
            case "1/12":
                saying = "Gefeliciteerd TheSwagDJ!";
                break;
            case "01/01":
                saying = "Gelukkig nieuwjaar!";
                break;
            case "28/12":
            case "29/12":
            case "30/12":
            case "31/12":
                int SECONDS_IN_A_DAY = 24 * 60 * 60;
                Calendar thatDay = Calendar.getInstance();
                thatDay.setTime(new Date(0)); /* reset */
                thatDay.set(Calendar.DAY_OF_MONTH,1);
                thatDay.set(Calendar.MONTH,0); // 0-11 so 1 less
                thatDay.set(Calendar.YEAR, 2016);

                Calendar today = Calendar.getInstance();
                long diff =  thatDay.getTimeInMillis() - today.getTimeInMillis();
                long diffSec = diff / 1000;

                long days = diffSec / SECONDS_IN_A_DAY;
                long secondsDay = diffSec % SECONDS_IN_A_DAY;
                long seconds = secondsDay % 60;
                long minutes = (secondsDay / 60) % 60;
                long hours = (secondsDay / 3600); // % 24 not needed

                saying = "&6&lNog &b" + days + "d " + hours + "h " + minutes + "m " + seconds + "s &6&ltot 2016!";
                break;
            default:
                saying = SayingsEnum.randomMessage().getSaying();
                break;
        }

        return saying;
    }

}