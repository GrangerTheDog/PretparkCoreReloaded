/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2016 HorizonCraft
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

package nl.HorizonCraft.PretparkCore.Bundles.Mazes;

import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.block.Sign;

/**
 * Created by Cooltimmetje on 1/17/2016 at 6:00 PM.
 */
public enum MazeLeaderboardsEnum {

    MAZE_1_SHOW_1(new int[]{-75,68,-371}),
    MAZE_1_SHOW_2(new int[]{-75,67,-371}),
    MAZE_1_SHOW_3(new int[]{-75,66,-371}),
    MAZE_2_1(new int[]{-283,82,-273}),
    MAZE_2_2(new int[]{-283,81,-273}),
    MAZE_2_3(new int[]{-283,80,-273});

    private int[] xyz;

    MazeLeaderboardsEnum(int[] ints) {
        this.xyz = ints;
    }

    public Sign getSign(){
        return (Sign)Variables.WORLD.getBlockAt(xyz[0],xyz[1],xyz[2]).getState();
    }
}
