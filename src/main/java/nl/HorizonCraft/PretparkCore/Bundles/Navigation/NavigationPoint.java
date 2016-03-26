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

import nl.HorizonCraft.PretparkCore.Profiles.MysqlManager;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.Location;

/**
 * Created by Cooltimmetje on 12/28/2015 at 6:44 PM at 7:07 PM.
 */
public class NavigationPoint {

    private int id;
    private String name;
    private Location location;
    private PointType pointType;
    private PointState pointState;

    public NavigationPoint(int i, String s, Location l, PointType pt, PointState ps){
        this.id = i;
        this.name = s;
        this.location = l;
        this.pointType = pt;
        this.pointState = ps;

        Variables.navigationPoints.add(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PointType getPointType() {
        return pointType;
    }

    public Location getLocation() {
        return location;
    }

    public PointState getPointState() {
        return pointState;
    }

    public void setPointState(PointState pointState) {
        this.pointState = pointState;
    }

    public void save(){
        MysqlManager.saveWarp(this);
    }
}
