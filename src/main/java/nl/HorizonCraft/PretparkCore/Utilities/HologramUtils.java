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
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

/**
 * Created by Cooltimmetje on 1/28/2016 at 5:21 PM.
 */
public class HologramUtils {

    public static void removeAll(){
        for (Hologram holo : Variables.holograms) {
            holo.delete();
        }
        for (Hologram holo : Variables.powerups) {
            holo.delete();
        }
    }

    public static void spawnAudio(Plugin plugin, Location location){
        Hologram hologram = HologramsAPI.createHologram(plugin, location);
        hologram.appendTextLine(MiscUtils.color("&c&lLET OP!"));
        hologram.appendTextLine(MiscUtils.color("&eDeze attractie gebruikt"));
        hologram.appendTextLine(MiscUtils.color("&ede Audio Server!"));
        hologram.appendTextLine(MiscUtils.color("&bTyp /jukebox"));
        hologram.appendItemLine(new ItemStack(Material.NOTE_BLOCK));

        Variables.holograms.add(hologram);
    }


}
