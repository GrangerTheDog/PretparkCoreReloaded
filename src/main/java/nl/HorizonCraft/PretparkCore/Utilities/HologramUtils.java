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
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import nl.HorizonCraft.PretparkCore.Main;
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
        hologram.appendTextLine(MiscUtils.color("&bTyp /audio"));
        hologram.appendItemLine(new ItemStack(Material.NOTE_BLOCK));

        Variables.holograms.add(hologram);
    }


}
