/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2016 Tim Medema
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
