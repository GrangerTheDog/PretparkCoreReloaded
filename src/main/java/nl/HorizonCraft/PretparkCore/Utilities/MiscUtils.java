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

import nl.HorizonCraft.PretparkCore.Utilities.Objects.Voucher;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * This class has been created on 9/11/2015 at 4:30 PM by Cooltimmetje.
 *
 * This class contains all sorts of stuff just to make life easier!
 */
public class MiscUtils {

    public static String color(String s){
        return s.replace('&', '\u00A7');
    }

    public static int randomInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static void shootFirework(Location loc, String world, boolean instant){
        //Spawn the Firework, get the FireworkMeta.
        final Firework fw = (Firework) Bukkit.getWorld(world).spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        //Our random generator
        Random r = new Random();

        //Get the type
        int rt = r.nextInt(4) + 1;
        FireworkEffect.Type type = FireworkEffect.Type.BALL;
        if (rt == 1) type = FireworkEffect.Type.BALL;
        if (rt == 2) type = FireworkEffect.Type.BURST;
        if (rt == 3) type = FireworkEffect.Type.STAR;
        if (rt == 4) type = FireworkEffect.Type.CREEPER;
        if (rt == 5) type = FireworkEffect.Type.BALL_LARGE;

        //Get our random colours
        int r1i = r.nextInt(17) + 1;
        int r2i = r.nextInt(17) + 1;
        Color c1 = getColor(r1i);
        Color c2 = getColor(r2i);

        //Create our effect with this
        FireworkEffect effect = FireworkEffect.builder().flicker(false).withColor(c1).withFade(c2).with(type).trail(true).build();

        //Then apply the effect to the meta
        fwm.addEffect(effect);

        //Generate some random power and set it
        int rp = r.nextInt(2) + 1;
        fwm.setPower(rp);

        //Then apply this to our rocket
        fw.setFireworkMeta(fwm);

        if(instant){
            ScheduleUtils.scheduleTask(2, new Runnable() {
                @Override
                public void run() {
                    fw.detonate();
                }
            });
        }
    }

    private static Color getColor(int i) {
        Color c = null;
        if(i==1){
            c=Color.AQUA;
        }
        if(i==2){
            c=Color.BLACK;
        }
        if(i==3){
            c=Color.BLUE;
        }
        if(i==4){
            c=Color.FUCHSIA;
        }
        if(i==5){
            c=Color.GRAY;
        }
        if(i==6){
            c=Color.GREEN;
        }
        if(i==7){
            c=Color.LIME;
        }
        if(i==8){
            c=Color.MAROON;
        }
        if(i==9){
            c=Color.NAVY;
        }
        if(i==10){
            c=Color.OLIVE;
        }
        if(i==11){
            c=Color.ORANGE;
        }
        if(i==12){
            c=Color.PURPLE;
        }
        if(i==13){
            c=Color.RED;
        }
        if(i==14){
            c=Color.SILVER;
        }
        if(i==15){
            c=Color.TEAL;
        }
        if(i==16){
            c=Color.WHITE;
        }
        if(i==17){
            c= Color.YELLOW;
        }

        return c;
    }

    public static String formatTime(int cooldown) {
        int sec,min,hour,day,week;
        sec = cooldown;
        min = 0;
        hour = 0;
        day = 0;
        week = 0;
        while (sec >= 60){
            min = min + 1;
            sec = sec - 60;
        }
        while (min >= 60){
            hour = hour + 1;
            min = min - 60;
        }
        while (hour >= 24){
            day = day + 1;
            hour = hour - 24;
        }
        while (day >= 7){
            week = week + 1;
            day = day - 7;
        }

        StringBuilder sb = new StringBuilder();
        if(week != 0){
            sb.append(week + "w");
        }
        if(day != 0){
            sb.append(day + "d");
        }
        if(hour != 0) {
            sb.append(hour + "u");
        }
        if (min != 0){
            sb.append(min + "m");
        }
        if (sec != 0){
            sb.append(sec + "s");
        }

        return sb.toString();
    }

    public static boolean cooldownCheck(long lastused, int cdtime){
        long currentTime = System.currentTimeMillis();
        int cdmillis = cdtime * 1000;
        return currentTime - lastused >= cdmillis;
    }

    public static int getTimeRemaining(long lastUsed, int cdtime){
        long currentTime = System.currentTimeMillis();

        return (int) (((currentTime - lastUsed ) / 1000) - cdtime) * -1;
    }

    public static boolean isInt(String str){
        try{
            int num = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void updateVouchers(){
        for(Voucher voucher : Variables.vouchers){
            voucher.update();
        }
    }

    public static Voucher getVoucher(String code){
        for(Voucher voucher : Variables.vouchers){
            if(voucher.getCode().equals(code)){
                return voucher;
            }
        }
        return null;
    }

    public static String locationToString(Location location, boolean inlcudeYawPitch, boolean round){
        StringBuilder sb = new StringBuilder();
        if(round){
            sb.append((int)location.getX()).append(",").append((int)location.getY()).append(",").append((int)location.getZ());
        } else {
            sb.append(location.getX()).append(",").append(location.getY()).append(",").append(location.getZ());
        }
        if(inlcudeYawPitch){
            if(round){
                sb.append(",").append((int)location.getYaw()).append(",").append((int)location.getPitch());
            } else {
                sb.append(",").append(location.getYaw()).append(",").append(location.getPitch());
            }
        }
        return sb.toString().trim();
    }

    public static Location stringToLocation(String locationS){
        String[] locationA = locationS.split(",");
        if(!(locationA.length >= 3)){
            return null;
        }
        Location location = new Location(Variables.WORLD, Double.parseDouble(locationA[0]), Double.parseDouble(locationA[1]), Double.parseDouble(locationA[2]));
        if(locationA.length >= 5){
            location.setYaw(Float.parseFloat(locationA[3]));
            location.setPitch(Float.parseFloat(locationA[4]));
        }
        return location;
    }

    public static String intFormat(int i, String separator){
        return new DecimalFormat("#,###,###").format(i).replace(",",separator);
    }
}
