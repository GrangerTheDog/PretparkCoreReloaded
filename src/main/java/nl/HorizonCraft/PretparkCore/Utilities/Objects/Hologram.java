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

package nl.HorizonCraft.PretparkCore.Utilities.Objects;

import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.HashMap;

/**
 * This class has been created on 09/23/2015 at 7:37 PM by Cooltimmetje.
 */
public class Hologram {

    private Location loc;
    private int lineCount;
    private HashMap<Integer, String> lines = new HashMap<>();
    private HashMap<Integer, ArmorStand> armorStands = new HashMap<>();

    public Hologram(Location loc, String firstLine){
        this.loc = loc;
        this.lines.put(1, firstLine);
        this.lineCount = 1;


    }

    public void appendLine(String newLine){
        setLineCount(getLineCount() + 1);
        this.lines.put(getLineCount(), newLine);
    }

    public void setLine(int index, String newText){
        this.lines.put(index, newText);
    }

    public void spawn(){
        for(int i : lines.keySet()){
            String text = lines.get(i);
            Location locTemp = loc;
            locTemp = locTemp.add(0,-1,0);

            ArmorStand as = (ArmorStand) Bukkit.getWorlds().get(0).spawnEntity(locTemp, EntityType.ARMOR_STAND);
            as.setGravity(false);
//            as.setBasePlate(false);
//            as.setVisible(false);
            as.setSmall(true);
            as.setCustomName(MiscUtils.color(text));
            armorStands.put(i, as);

            locTemp = locTemp.add(0,-0.375,0);
        }
    }

    public void updateHologram(){
        for(int i : armorStands.keySet()){
            armorStands.get(i).remove();
        }

        spawn();
    }

    public void despawn(){
        for(int i : armorStands.keySet()){
            armorStands.get(i).remove();
        }
    }

    public HashMap<Integer, ArmorStand> getAllArmorStands() {
        return armorStands;
    }

    private int getLineCount() {
        return lineCount;
    }

    private void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }



}
