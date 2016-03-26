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

package nl.HorizonCraft.PretparkCore.Bundles.Navigation;

/**
 * Created by Cooltimmetje on 12/28/2015 at 6:47 PM.
 */
public enum PointType {

    SHOP("Shop"),
    WARP("Warp"),
    RIDE("Attractie"),
    MAZE("Doolhof"),
    PARKOUR("Parkour");

    private String friendlyName;

    PointType(String s){
        this.friendlyName = s;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
