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

package nl.HorizonCraft.PretparkCore.Bundles.Achievements;

/**
 * This class has been created on 10/12/2015 at 9:14 PM by Cooltimmetje.
 */
public enum AchievementType {

    GENERAL("Algemeen"),
    RIDES("Attracties"),
    STAFFPUNCH("Staff Launches"),
    MAZES_PARKOUR("Parkouren/Doolhoven"),
    UNLOCKABLES("Unlockables");

    private String friendlyName;

    AchievementType(String s) {
        this.friendlyName = s;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
