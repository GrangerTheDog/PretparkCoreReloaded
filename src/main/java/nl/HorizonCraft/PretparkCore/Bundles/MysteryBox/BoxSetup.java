/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 HorizonCraft
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

package nl.HorizonCraft.PretparkCore.Bundles.MysteryBox;

import nl.HorizonCraft.PretparkCore.Utilities.EntityUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Objects.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Witch;
import org.bukkit.inventory.ItemStack;

/**
 * This class has been created on 09/24/2015 at 7:45 PM by Cooltimmetje.
 */
public class BoxSetup {

    public static Witch witch;

    public static void setup(){
        Hologram hologram = new Hologram(new Location(Bukkit.getWorlds().get(0), 98.5,61,-312.5), "&aMystery Vault");
        hologram.appendLine("&7&lBINNENKORT");
        hologram.spawn();


        witch = (Witch) Bukkit.getWorlds().get(0).spawnEntity(new Location(Bukkit.getWorlds().get(0),96.5,59,-316), EntityType.WITCH);
        witch.getEquipment().setItemInHand(new ItemStack(Material.ENDER_CHEST));
        Location loc = witch.getLocation();
        loc.setYaw(60);
        witch.teleport(loc);
        EntityUtils.noAI(witch);

        Hologram hologram1 = new Hologram(loc.add(0,2.5,0), "&dKees de Tovenaar");
        hologram1.appendLine("&7&lBINNENKORT");
        hologram1.spawn();
    }

    public static void destroy(){
        witch.remove();
    }

}
