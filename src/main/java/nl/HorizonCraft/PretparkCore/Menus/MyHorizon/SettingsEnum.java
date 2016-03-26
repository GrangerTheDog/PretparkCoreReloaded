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

package nl.HorizonCraft.PretparkCore.Menus.MyHorizon;

/**
 * Created by Cooltimmetje on 2/9/2016 at 7:54 PM.
 */
public enum SettingsEnum {

    STATISTICS(0);

    private int id;

    SettingsEnum(int i){
        this.id = i;
    }


    public int getId() {
        return id;
    }
}
