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

/**
 * Created by Cooltimmetje on 12/9/2015 at 4:12 PM.
 */
public enum SuitType {

    HELMET(0,"Helmet"),
    CHESTPLATE(1,"Chestplate"),
    LEGGINGS(2,"Leggings"),
    BOOTS(3,"Boots");

    private int move;
    private String name;

    SuitType(int move, String name){
        this.move = move;
        this.name = name;
    }

    public int getMove(){
        return move;
    }


    public String getName() {
        return name;
    }
}
