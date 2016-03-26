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

package nl.HorizonCraft.PretparkCore.Bundles.MysteryBox;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import nl.HorizonCraft.PretparkCore.Main;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * This class has been created on 09/24/2015 at 7:45 PM by Cooltimmetje.
 */
public class BoxSetup {

    public static void setup(){
        Hologram hologram = HologramsAPI.createHologram(Main.getPlugin(), new Location(Bukkit.getWorlds().get(0), 98.5,61.5,-312.5));
        hologram.appendTextLine(MiscUtils.color("&aMystery Vault"));
        hologram.appendTextLine(MiscUtils.color("&3&lRECHTER KLIK!"));

        Variables.holograms.add(hologram);
    }

}
