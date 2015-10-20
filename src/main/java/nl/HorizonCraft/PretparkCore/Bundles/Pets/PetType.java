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

package nl.HorizonCraft.PretparkCore.Bundles.Pets;

import nl.HorizonCraft.PretparkCore.Bundles.MysteryBox.Weight;

/**
 * This class has been created on 10/13/2015 at 2:17 PM by Cooltimmetje.
 */
public enum PetType {

    CREEPER(0, "Creeper", "Nee, deze blaast niet op,\n dus KNUFFELEN MAAR!", 0, Weight.LEGENDARY),
    SKELETON(1, "Skeleton", "Met of zonder boog, maar geef hem geen pijlen...", 0, Weight.EPIC, false),
    SPIDER(2, "Spider", "Het is maar waar je van houd als huisdier...", 800, Weight.EPIC),
    ZOMBIE(3, "Zombie", "Vriendjes voor het leven!\nOw wacht... Hij is al dood...", 0, Weight.LEGENDARY),
    SLIME_S(4, "Kleine Slime", "AAAWWWWW.... ZO SCHATTIG EN FLUBBERIG!", 800, Weight.LEGENDARY, 1);


    private int id;
    private String name;
    private String lore;
    private int price;
    private Weight weight;
    private int size;
    private boolean wither;

    PetType(int i, String s, String s1, int i1, Weight w) {
        this.id = i;
        this.name = s;
        this.lore = s1;
        this.price = i1;
        this.weight = w;
    }

    PetType(int i, String s, String s1, int i1, Weight w, boolean b) {
        this.id = i;
        this.name = s;
        this.lore = s1;
        this.price = i1;
        this.weight = w;
        this.wither = b;
    }

    PetType(int i, String s, String s1, int i1, Weight w, int i2) {
        this.id = i;
        this.name = s;
        this.lore = s1;
        this.price = i1;
        this.weight = w;
        this.size = i2;
    }
}
