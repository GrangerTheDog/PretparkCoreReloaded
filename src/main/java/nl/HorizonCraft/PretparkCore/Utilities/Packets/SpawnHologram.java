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

package nl.HorizonCraft.PretparkCore.Utilities.Packets;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.VisibilityManager;
import nl.HorizonCraft.PretparkCore.Main;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by Cooltimmetje on 1/30/2016 at 4:02 PM.
 */
public class SpawnHologram {

    public static void spawn(Player p){
        CorePlayer cp = PlayerUtils.getProfile(p);

        Hologram hologram = HologramsAPI.createHologram(Main.getPlugin(), new Location(Variables.WORLD, 64.5,67.75,-19.5));
        VisibilityManager visiblityManager = hologram.getVisibilityManager();

        visiblityManager.showTo(p);
        visiblityManager.setVisibleByDefault(false);

        hologram.appendTextLine(MiscUtils.color("&aWelkom, &e" + p.getName() + " &aop"));
        hologram.appendTextLine(MiscUtils.color(Variables.SERVER_NAME));

        cp.setSpawnHologram(hologram);
    }

    public static void despawn(Player p){
        Hologram hologram = PlayerUtils.getProfile(p).getSpawnHologram();
        if(hologram != null) {
            hologram.delete();
        }
    }
}
