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
 * Created by Cooltimmetje on 12/28/2015 at 6:54 PM.
 */
public enum PointState {

    OPEN("2", 13, "Geopend"),
    CLOSED("c", 14, "Gesloten"),
    MAINTENANCE("6", 1, "Onderhoud"),
    BROKEN("7", 8, "Storing");

    private String colorCode;
    private int colorData;
    private String friendlyName;

    PointState(String s, int i, String s1){
        this.colorCode = s;
        this.colorData = i;
        this.friendlyName = s1;
    }

    public String getColorCode() {
        return colorCode;
    }

    public int getColorData() {
        return colorData;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
