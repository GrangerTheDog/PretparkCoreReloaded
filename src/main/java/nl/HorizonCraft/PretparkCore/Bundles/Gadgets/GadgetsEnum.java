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

package nl.HorizonCraft.PretparkCore.Bundles.Gadgets;

import nl.HorizonCraft.PretparkCore.Bundles.MysteryBox.Weight;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class has been created on 09/18/2015 at 6:41 PM by Cooltimmetje.
 */
public enum GadgetsEnum {

    FIREWORK(0, 100, "Vuurwerkje", "Gewoon een vuurwerkje, niet veel bijzonder.", Material.FIREWORK_CHARGE, 0, 15, Weight.COMMON),
    STAFF_LAUNCHER(1, 500, "Staff Launcher", "The sky is the limit, letterlijk... \nStuur de staff naar de lucht en weer terug!", Material.SLIME_BLOCK, 0, 60, Weight.LEGENDARY),
    BOEM_CHICKEN(2, 750, "Exploderende Kip", "Uuh... Kip *boem* Geen kip :D", Material.COOKED_CHICKEN, 0, 45, Weight.EPIC),
    SNOWBALL_GUN(3, 600, "Snowball Gun", "Pew pew pew...", Material.IRON_BARDING, 0, 2, Weight.RARE),
    CLUSTER_CHICKEN(4, 0, "Cluster Chicken", "Een kip met kippen? O.o", Material.EGG, 0, 60, Weight.LEGENDARY);

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

    public static GadgetsEnum getFromMaterial(Material m){
        for(GadgetsEnum gadget : GadgetsEnum.values()){
            if(gadget.getMaterial() == m){
                return gadget;
            }
        }
        return null;
    }

    private static final List<GadgetsEnum> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static GadgetsEnum random()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
