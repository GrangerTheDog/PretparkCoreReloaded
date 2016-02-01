/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2016 HorizonCraft
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

    public static String SERVER_NAME = MiscUtils.color("&3&lHorizon&6&lCraft &c&lALPHA");
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

    public static HashMap<String, CorePlayer> profile = new HashMap<>();
    public static ArrayList<Hologram> holograms = new ArrayList<>();
    public static ArrayList<Voucher> vouchers = new ArrayList<>();
    public static ArrayList<NavigationPoint> navigationPoints = new ArrayList<>();
    public static HashMap<Integer, Location> powerupLocations = new HashMap<>();
    public static ArrayList<Hologram> powerups = new ArrayList<>();

    public static int uniquePlayers = 0;
}