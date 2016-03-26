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

/**
 * This class has been created on 10/13/2015 at 2:21 PM by Cooltimmetje.
 */
public enum Weight {

    COMMON('e',4,1,5),
    RARE('9',11,10,20),
    EPIC('5',10,50,100),
    LEGENDARY('6',1,100,200);

    private char color;
    private int data;
    private int min;
    private int max;

    Weight(char c, int i, int i2, int i3) {
        this.color = c;
        this.data = i;
        this.min = i2;
        this.max = i3;
    }

    public char getColor() {
        return color;
    }

    public int getData() {
        return data;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
