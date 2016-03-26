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

package nl.HorizonCraft.PretparkCore.Bundles.Mazes;

import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.block.Sign;

/**
 * Created by Cooltimmetje on 1/17/2016 at 6:00 PM.
 */
public enum MazeLeaderboardsEnum {

    MAZE_1_1(new int[]{-75,68,-371}),
    MAZE_1_2(new int[]{-75,67,-371}),
    MAZE_1_3(new int[]{-75,66,-371}),
    MAZE_1_TIME(new int[]{-75,66,-370});
//    MAZE_2_1(new int[]{-283,82,-273}),
//    MAZE_2_2(new int[]{-283,81,-273}),
//    MAZE_2_3(new int[]{-283,80,-273}),
//    MAZE_2_TIME(new int[]{-281,80,-273});

    private int[] xyz;

    MazeLeaderboardsEnum(int[] ints) {
        this.xyz = ints;
    }

    public Sign getSign(){
        return (Sign)Variables.WORLD.getBlockAt(xyz[0],xyz[1],xyz[2]).getState();
    }
}
