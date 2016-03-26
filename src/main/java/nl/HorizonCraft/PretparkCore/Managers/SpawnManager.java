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

package nl.HorizonCraft.PretparkCore.Managers;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import nl.HorizonCraft.PretparkCore.Main;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.Location;

/**
 * Created by Cooltimmetje on 1/28/2016 at 5:20 PM.
 */
public class SpawnManager {

    public static void setup(){
        Hologram holoTwitter = HologramsAPI.createHologram(Main.getPlugin(), new Location(Variables.WORLD,60.5,68.5,-17.5));
        Hologram holoInsta = HologramsAPI.createHologram(Main.getPlugin(), new Location(Variables.WORLD,68.5,68.5,-17.5));

        holoTwitter.appendTextLine(MiscUtils.color("&b&lVolg ons op Twitter:"));
        holoTwitter.appendTextLine(MiscUtils.color("&b@Horizon_Craft"));
        Variables.holograms.add(holoTwitter);

        holoInsta.appendTextLine(MiscUtils.color("&7&lVolg ons op Instagram:"));
        holoInsta.appendTextLine(MiscUtils.color("&7@horizoncraft"));
        Variables.holograms.add(holoInsta);
    }
}
