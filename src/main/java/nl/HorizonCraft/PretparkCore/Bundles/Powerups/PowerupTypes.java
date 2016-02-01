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

package nl.HorizonCraft.PretparkCore.Bundles.Powerups;

import nl.HorizonCraft.PretparkCore.Bundles.Gadgets.GadgetsEnum;
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
