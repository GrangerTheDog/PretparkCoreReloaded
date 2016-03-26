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

package nl.HorizonCraft.PretparkCore.Bundles.MysteryBox;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Cooltimmetje on 1/28/2016 at 7:46 PM.
 */
public enum RewardType {

    GAGDET(Material.PISTON_BASE, "Gadget"),
    PET(Material.BONE, "Pet"),
    CLOTHING(Material.IRON_CHESTPLATE, "Kleding");

    private Material material;
    private String name;

    RewardType(Material m, String s){
        this.material = m;
        this.name = s;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    private static final List<RewardType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static RewardType random()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
