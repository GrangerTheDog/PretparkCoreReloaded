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
        Hologram holoTwitter = HologramsAPI.createHologram(Main.getPlugin(), new Location(Variables.WORLD,62.5,68.5,-18.5));
        Hologram holoInsta = HologramsAPI.createHologram(Main.getPlugin(), new Location(Variables.WORLD,66.5,68.5,-18.5));

        holoTwitter.appendTextLine(MiscUtils.color("&b&lVolg ons op Twitter:"));
        holoTwitter.appendTextLine(MiscUtils.color("&b@Horizon_Craft"));
        Variables.holograms.add(holoTwitter);

        holoInsta.appendTextLine(MiscUtils.color("&7&lVolg ons op Instagram:"));
        holoInsta.appendTextLine(MiscUtils.color("&7@horizoncraft"));
        Variables.holograms.add(holoInsta);
    }
}
