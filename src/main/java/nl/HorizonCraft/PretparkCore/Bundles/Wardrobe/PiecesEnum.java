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

package nl.HorizonCraft.PretparkCore.Bundles.Wardrobe;

import nl.HorizonCraft.PretparkCore.Bundles.MysteryBox.Weight;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class has been created on 10/27/2015 at 10:32 by Cooltimmetje.
 */
public enum PiecesEnum {

    NINJA_H(0, SuitsEnum.NINJA, SuitType.HELMET ,Material.SKULL_ITEM, "hypixel", Weight.EPIC, 0),
    NINJA_C(1, SuitsEnum.NINJA, SuitType.CHESTPLATE, Material.LEATHER_CHESTPLATE, Color.fromRGB(0,0,0), Weight.RARE, 500),
    NINJA_L(2, SuitsEnum.NINJA, SuitType.LEGGINGS, Material.LEATHER_LEGGINGS, Color.fromRGB(0,0,0), Weight.LEGENDARY, 700),
    NINJA_B(3, SuitsEnum.NINJA, SuitType.BOOTS, Material.LEATHER_BOOTS, Color.fromRGB(0,0,0), Weight.COMMON, 300),

    OLAF_H(4, SuitsEnum.OLAF, SuitType.HELMET, Material.SKULL_ITEM, "Snowman_7", Weight.EPIC, 0),
    OLAF_C(5, SuitsEnum.OLAF, SuitType.CHESTPLATE, Material.LEATHER_CHESTPLATE, Color.fromRGB(255,250,250), Weight.RARE, 500),
    OLAF_L(6, SuitsEnum.OLAF, SuitType.LEGGINGS, Material.LEATHER_LEGGINGS, Color.fromRGB(255,250,250), Weight.LEGENDARY, 700),
    OLAF_B(7, SuitsEnum.OLAF, SuitType.BOOTS, Material.LEATHER_BOOTS, Color.fromRGB(255,250,250), Weight.COMMON, 300),

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
    SKELETON_B(19, SuitsEnum.SKELETON, SuitType.BOOTS, Material.LEATHER_BOOTS, Color.fromRGB(206,206,206), Weight.COMMON, 300),

    COWBOY_H(20, SuitsEnum.COWBOY, SuitType.HELMET, Material.SKULL_ITEM, "cowboy", Weight.EPIC, 0),
    COWBOY_C(21, SuitsEnum.COWBOY, SuitType.CHESTPLATE, Material.LEATHER_CHESTPLATE, Color.fromRGB(160,82,45), Weight.RARE, 500),
    COWBOY_L(22, SuitsEnum.COWBOY, SuitType.LEGGINGS, Material.LEATHER_LEGGINGS, Color.fromBGR(205,133,63), Weight.LEGENDARY, 700),
    COWBOY_B(23, SuitsEnum.COWBOY, SuitType.BOOTS, Material.LEATHER_BOOTS, Color.fromRGB(160,82,45), Weight.COMMON, 300),

    COOK_H(24, SuitsEnum.COOK, SuitType.HELMET, Material.LEATHER_HELMET, Color.fromRGB(255,255,255), Weight.EPIC, 0),
    COOK_C(25, SuitsEnum.COOK, SuitType.CHESTPLATE, Material.LEATHER_CHESTPLATE, Color.fromRGB(255,255,255), Weight.RARE, 500),
    COOK_L(26, SuitsEnum.COOK, SuitType.LEGGINGS, Material.LEATHER_LEGGINGS, Color.BLUE, Weight.LEGENDARY, 700),
    COOK_B(27, SuitsEnum.COOK, SuitType.BOOTS, Material.LEATHER_BOOTS, Color.BLACK, Weight.COMMON, 300),

    PIRATE_H(28, SuitsEnum.PIRATE, SuitType.HELMET, Material.SKULL_ITEM, "Rezzus", Weight.EPIC, 0),
    PIRATE_C(29, SuitsEnum.PIRATE, SuitType.CHESTPLATE, Material.LEATHER_CHESTPLATE, Color.fromRGB(38,21,5), Weight.RARE, 500),
    PIRATE_L(30, SuitsEnum.PIRATE, SuitType.LEGGINGS, Material.LEATHER_LEGGINGS, Color.GREEN, Weight.LEGENDARY, 700),
    PIRATE_B(31, SuitsEnum.PIRATE, SuitType.BOOTS, Material.LEATHER_BOOTS, Color.fromRGB(66,35,6), Weight.COMMON, 300),

//    CLOWN_H(32, SuitsEnum.CLOWN, SuitType.HELMET, Material.SKULL_ITEM, "clown", Weight.EPIC, 0),
//    CLOWN_C(33, SuitsEnum.CLOWN, SuitType.CHESTPLATE, Material.LEATHER_CHESTPLATE, Color.GREEN, Weight.RARE, 500),
//    CLOWN_L(34, SuitsEnum.CLOWN, SuitType.LEGGINGS, Material.LEATHER_LEGGINGS, Color.PURPLE, Weight.LEGENDARY, 700),
//    CLOWN_B(35, SuitsEnum.CLOWN, SuitType.BOOTS, Material.LEATHER_BOOTS, Color.YELLOW, Weight.COMMON, 300),

    CHICKEN_H(36, SuitsEnum.CHICKEN, SuitType.HELMET, Material.SKULL_ITEM, "MHF_Chicken", Weight.LEGENDARY, 0),
    CHICKEN_C(37, SuitsEnum.CHICKEN, SuitType.CHESTPLATE, Material.LEATHER_CHESTPLATE, Color.WHITE, Weight.LEGENDARY, 0),
    CHICKEN_L(39, SuitsEnum.CHICKEN, SuitType.LEGGINGS, Material.LEATHER_LEGGINGS, Color.WHITE, Weight.LEGENDARY, 0),
    CHICKEN_B(40, SuitsEnum.CHICKEN, SuitType.BOOTS, Material.LEATHER_BOOTS, Color.WHITE, Weight.LEGENDARY, 0);




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

    public ItemStack getItemStack(){
        ItemStack itemStack = new ItemStack(getMaterial());
        if(material == Material.SKULL_ITEM){
            SkullMeta sm = (SkullMeta) itemStack.getItemMeta();
            sm.setOwner(getSkullUUID());
            itemStack.setItemMeta(sm);
        } else {
            LeatherArmorMeta lm = (LeatherArmorMeta) itemStack.getItemMeta();
            lm.setColor(getColor());
            itemStack.setItemMeta(lm);
        }

        return itemStack;
    }

    private static final List<PiecesEnum> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static PiecesEnum random(){
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
