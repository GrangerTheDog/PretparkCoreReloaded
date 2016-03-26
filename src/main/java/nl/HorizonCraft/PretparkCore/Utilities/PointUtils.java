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

package nl.HorizonCraft.PretparkCore.Utilities;

import nl.HorizonCraft.PretparkCore.Bundles.Navigation.NavigationPoint;

/**
 * Created by Cooltimmetje on 12/28/2015 at 7:50 PM.
 */
public class PointUtils {

    public static NavigationPoint getById(int id){

        for(NavigationPoint navPoint : Variables.navigationPoints){
            if(navPoint.getId() == id){
                return navPoint;
            }
        }

        return null;
    }

    public static void saveAll(){
        for(NavigationPoint navPoint : Variables.navigationPoints){
            navPoint.save();
        }
    }

}
