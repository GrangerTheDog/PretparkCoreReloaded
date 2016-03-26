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

package nl.HorizonCraft.PretparkCore.Bundles.Powerups;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Cooltimmetje on 1/31/2016 at 3:01 PM.
 */
public enum PowerupTypes {

    COINS("Coins", "6", 1, 150, Material.GOLD_NUGGET),
    EXP("Experience", "9", 1, 500, Material.EXP_BOTTLE),
    BOXES("Mystery Boxes", "3", 1, 10, Material.ENDER_CHEST),
    KEYS("Mystery Keys", "d", 1, 5, Material.TRIPWIRE_HOOK),
    DUST("Mystery Dust", "b", 1, 15, Material.SUGAR);

    private String name;
    private String color;
    private int min;
    private int max;
    private Material material;

    PowerupTypes(String s, String s1, int i, int i2, Material m) {
        this.name = s;
        this.color = s1;
        this.min = i;
        this.max = i2;
        this.material = m;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    private static final List<PowerupTypes> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static PowerupTypes random()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public Material getMaterial() {
        return material;
    }
}
