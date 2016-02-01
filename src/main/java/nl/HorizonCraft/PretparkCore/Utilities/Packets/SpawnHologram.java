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
