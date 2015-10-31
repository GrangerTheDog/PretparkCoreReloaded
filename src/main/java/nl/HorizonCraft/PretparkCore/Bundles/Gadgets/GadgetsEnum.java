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

package nl.HorizonCraft.PretparkCore.Bundles.Gadgets;

import nl.HorizonCraft.PretparkCore.Bundles.MysteryBox.Weight;
import org.bukkit.Material;

/**
 * This class has been created on 09/18/2015 at 6:41 PM by Cooltimmetje.
 */
public enum GadgetsEnum {

    FIREWORK(0, 100, "Vuurwerkje", "Gewoon een vuurwerkje, niet veel bijzonder.", Material.FIREWORK_CHARGE, 0, 15, Weight.COMMON),
    STAFF_LAUNCHER(1, 500, "Staff Launcher", "The sky is the limit, letterlijk... \nStuur de staff naar de lucht en weer terug!", Material.SLIME_BLOCK, 0, 60, Weight.LEGENDARY),
    BOEM_CHICKEN(2, 750, "Exploderende Kip", "Uuh... Kip *boem* Geen kip :D", Material.COOKED_CHICKEN, 0, 45, Weight.EPIC);

    private int id;
    private int cost;
    private String name;
    private String lore;
    private Material m;
    private int dmg;
    private int cooldown;
    private Weight weight;

    GadgetsEnum(int id, int cost, String name, String lore, Material m, int dmg, int cooldown, Weight w) {
        this.id = id;
        this.cost = cost;
        this.name = name;
        this.lore = lore;
        this.m = m;
        this.dmg = dmg;
        this.cooldown = cooldown;
        this.weight = w;
    }

    public String getLore() {
        return lore;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return m;
    }

    public int getDmg() {
        return dmg;
    }

    public int getCooldown() {
        return cooldown;
    }

    public Weight getWeight() {
        return weight;
    }
}
