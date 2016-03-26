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

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import nl.HorizonCraft.PretparkCore.Bundles.Navigation.NavigationPoint;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.Objects.Voucher;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class has been created on 09/10/2015 at 18:47 by Cooltimmetje.
 */
public class Variables {

    public static String SERVER_NAME = MiscUtils.color("&3&lHorzion&6&lCraft &c&lALPHA");
    public static String SERVER_NAME_SHORT = MiscUtils.color("&3&lH&6&lC");
    public static String SERVER_PING_MESSAGE = MiscUtils.color("In aanbouw.");
    public static String RIGHT_CLICK = MiscUtils.color("&3(Rechter Klik)");
    public static String WORLD_NAME = "world";
    public static World WORLD = Bukkit.getWorld(WORLD_NAME);

    public static int COIN_TIME = 60;
    public static int COIN_GAIN = 20;
    public static int DOUBLE_CHANCE = 5;

    public static int CHEST_TIME = 120;
    public static int CHEST_GAIN = 1;
    public static int CHEST_DOUBLE = 5;

    public static int EXPERIENCE_TIME = 30;
    public static int EXPERIENCE_GAIN = 100;
    public static int EXPERIENCE_DOUBLE = 5;
    public static int EXP_BASE_LEVEL = 4500;
    public static double EXP_MODIFIER = 1.1;

    public static int RARE_CHANCE = 20;
    public static int EPIC_CHANCE = 5;
    public static int LEGENDARY_CHANCE = 1;

    public static int DAILY_COINS = 20;
    public static int DAILY_EXP = 250;
    public static int DAILY_BOXES = 0;
    public static int DAILY_KEYS = 0;
    public static double DAILY_MODIFIER = 1.1;
    public static int DAILY_MODIFIER_CAP = 30;

    public static HashMap<String, CorePlayer> profile = new HashMap<>();
    public static ArrayList<Hologram> holograms = new ArrayList<>();
    public static ArrayList<Voucher> vouchers = new ArrayList<>();
    public static ArrayList<NavigationPoint> navigationPoints = new ArrayList<>();
    public static HashMap<Integer, Location> powerupLocations = new HashMap<>();
    public static ArrayList<Hologram> powerups = new ArrayList<>();

    public static int uniquePlayers = 0;

    public static String discordServerID = "91580382159314944";
}