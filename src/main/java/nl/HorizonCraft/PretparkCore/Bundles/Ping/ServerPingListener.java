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

package nl.HorizonCraft.PretparkCore.Bundles.Ping;

import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class has been created on 10/07/2015 at 9:02 PM by Cooltimmetje.
 */
public class ServerPingListener implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent event){
        String saying = getSaying();
        if(saying.contains("MAND!")){
            try {
                event.setServerIcon(Bukkit.loadServerIcon(new File("mand.png")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                event.setServerIcon(Bukkit.loadServerIcon(new File("hc.png")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        event.setMotd(MiscUtils.color(Variables.SERVER_NAME + " &8\u00BB &a" + Variables.SERVER_PING_MESSAGE + "\n" + Variables.SERVER_NAME_SHORT + " &8\u00BB &b&o" + saying));
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
            case "07/06":
                saying = "Gefeliciteerd 78wesley!";
                break;
            case "26/10":
                saying = "Gefeliciteerd B0EF!";
                break;
            case "21/02":
                saying = "Gefeliciteerd Jordyvz01";
                break;
            case "03/05":
                saying = "Gefeliciteerd Destiny_VG!";
                break;
            case "24/08":
                saying = "Gefeliciteerd Toptim24!";
                break;
            case "27/11":
                saying = "Gefeliciteerd RickBult!";
                break;
            case "":
                saying = "Gefeliciteerd W1nd0x!";
                break;
            case "15/08":
                saying = "Gefeliciteerd xBrandy!";
                break;
            case "01/01":
                saying = "Gelukkig nieuwjaar!";
                break;
            case "14/02":
                saying = "Fijne valentijnsdag!";
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
                thatDay.set(Calendar.YEAR, 2017);

                Calendar today = Calendar.getInstance();
                today.add(Calendar.HOUR, 1);
                long diff =  thatDay.getTimeInMillis() - today.getTimeInMillis();
                long diffSec = diff / 1000;

                long days = diffSec / SECONDS_IN_A_DAY;
                long secondsDay = diffSec % SECONDS_IN_A_DAY;
                long seconds = secondsDay % 60;
                long minutes = (secondsDay / 60) % 60;
                long hours = (secondsDay / 3600); // % 24 not needed

                saying = "&6&lNog &b" + days + "d " + hours + "h " + minutes + "m " + seconds + "s &6&ltot 2017!";
                break;
            default:
                saying = SayingsEnum.randomMessage().getSaying();
                break;
        }

        return saying;
    }

}