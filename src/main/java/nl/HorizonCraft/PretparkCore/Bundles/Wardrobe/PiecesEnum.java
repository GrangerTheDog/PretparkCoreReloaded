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

package nl.HorizonCraft.PretparkCore.Bundles.Wardrobe;

import nl.HorizonCraft.PretparkCore.Bundles.MysteryBox.Weight;
import org.bukkit.Color;
import org.bukkit.Material;

/**
 * This class has been created on 10/27/2015 at 10:32 by Cooltimmetje.
 */
public enum PiecesEnum {

    NINJA_H(0, SuitsEnum.NINJA, SuitType.HELMET ,Material.SKULL_ITEM, "hypixel", Weight.EPIC, 0),
    NINJA_C(1, SuitsEnum.NINJA, SuitType.CHESTPLATE, Material.LEATHER_CHESTPLATE, Color.fromRGB(0,0,0), Weight.RARE, 500),
    NINJA_L(2, SuitsEnum.NINJA, SuitType.LEGGINGS, Material.LEATHER_LEGGINGS, Color.fromRGB(0,0,0), Weight.LEGENDARY, 700),
    NINJA_B(3, SuitsEnum.NINJA, SuitType.BOOTS, Material.LEATHER_BOOTS, Color.fromRGB(0,0,0), Weight.COMMON, 300),

    OLAF_H(4, SuitsEnum.OLAF, SuitType.HELMET, Material.SKULL_ITEM, "Snowman_7", Weight.EPIC, 0),
    OLAF_C(5, SuitsEnum.OLAF, SuitType.CHESTPLATE, Material.LEATHER_CHESTPLATE, Color.fromRGB(255,255,255), Weight.RARE, 500),
    OLAF_L(6, SuitsEnum.OLAF, SuitType.LEGGINGS, Material.LEATHER_LEGGINGS, Color.fromRGB(255,255,255), Weight.LEGENDARY, 700),
    OLAF_B(7, SuitsEnum.OLAF, SuitType.BOOTS, Material.LEATHER_BOOTS, Color.fromRGB(255,255,255), Weight.COMMON, 300),

    LOVE_H(8, SuitsEnum.LOVE, SuitType.HELMET, Material.SKULL_ITEM, "IM_", Weight.EPIC, 0),
    LOVE_C(9, SuitsEnum.LOVE, SuitType.CHESTPLATE, Material.LEATHER_CHESTPLATE, Color.fromRGB(255,102,255), Weight.RARE, 500),
    LOVE_L(10, SuitsEnum.LOVE, SuitType.LEGGINGS, Material.LEATHER_LEGGINGS, Color.fromRGB(255,102,255), Weight.LEGENDARY, 700),
    LOVE_B(11, SuitsEnum.LOVE, SuitType.BOOTS, Material.LEATHER_BOOTS, Color.fromRGB(255,102,255), Weight.COMMON, 300),

    VAMPIRE_H(12, SuitsEnum.VAMPIRE, SuitType.HELMET, Material.SKULL_ITEM, "GMP", Weight.EPIC, 0),
    VAMPIRE_C(13, SuitsEnum.VAMPIRE, SuitType.CHESTPLATE, Material.LEATHER_CHESTPLATE, Color.fromRGB(0,0,0), Weight.RARE, 500),
    VAMPIRE_L(14, SuitsEnum.VAMPIRE, SuitType.LEGGINGS, Material.LEATHER_LEGGINGS, Color.fromRGB(255,0,0), Weight.LEGENDARY, 700),
    VAMPIRE_B(15, SuitsEnum.VAMPIRE, SuitType.BOOTS, Material.LEATHER_BOOTS, Color.fromRGB(0,0,0), Weight.COMMON, 300),

    SKELETON_H(16, SuitsEnum.SKELETON, SuitType.HELMET, Material.SKULL_ITEM, "MHF_Skeleton", Weight.EPIC, 0),
    SKELETON_C(17, SuitsEnum.SKELETON, SuitType.CHESTPLATE, Material.LEATHER_CHESTPLATE, Color.fromRGB(206,206,206), Weight.RARE, 500),
    SKELETON_L(18, SuitsEnum.SKELETON, SuitType.LEGGINGS, Material.LEATHER_LEGGINGS, Color.fromRGB(206,206,206), Weight.LEGENDARY, 700),
    SKELETON_B(19, SuitsEnum.SKELETON, SuitType.BOOTS, Material.LEATHER_BOOTS, Color.fromRGB(206,206,206), Weight.COMMON, 300);

    private int id;
    private SuitsEnum suit;
    private SuitType suitType;
    private Material material;
    private String skullUUID;
    private Color color;
    private Weight weight;
    private int cost;

    PiecesEnum(int id, SuitsEnum suitsEnum, SuitType suitType, Material material, String skullUUID, Weight weight, int cost) {
        this.id = id;
        this.suit = suitsEnum;
        this.suitType = suitType;
        this.material = material;
        this.skullUUID = skullUUID;
        this.weight = weight;
        this.cost = cost;
    }

    PiecesEnum(int id, SuitsEnum suitsEnum, SuitType suitType, Material material, Color color, Weight weight, int cost) {
        this.id = id;
        this.suit = suitsEnum;
        this.suitType = suitType;
        this.material = material;
        this.color = color;
        this.weight = weight;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public SuitsEnum getSuit() {
        return suit;
    }

    public SuitType getSuitType() {
        return suitType;
    }

    public Material getMaterial() {
        return material;
    }

    public String getSkullUUID() {
        return skullUUID;
    }

    public Color getColor() {
        return color;
    }

    public Weight getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }
}
