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

package nl.HorizonCraft.PretparkCore.Enums;

import org.bukkit.Statistic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Cooltimmetje on 2/6/2016 at 8:36 PM.
 */
public enum Stats {

    JOINED(Statistic.LEAVE_GAME, "Je bent de server %n keer gejoined!", StatTypes.NORMAL),
    PLAYED(Statistic.PLAY_ONE_TICK, "Je hebt in totaal %n op deze server gespeeld!", StatTypes.TIME),
    WALKED(Statistic.WALK_ONE_CM, "%n te voet.", StatTypes.DISTANCE),
    CROUCHED(Statistic.CROUCH_ONE_CM, "%n sluipend.", StatTypes.DISTANCE),
    SPRINTED(Statistic.SPRINT_ONE_CM, "%n sprintend.", StatTypes.DISTANCE),
    SWUM(Statistic.SWIM_ONE_CM, "%n zwemmend.", StatTypes.DISTANCE),
    FALLEN(Statistic.CLIMB_ONE_CM, "%n klimmend.", StatTypes.DISTANCE),
    FLOWN(Statistic.FLY_ONE_CM, "%n vliegend.", StatTypes.DISTANCE),
    DOVE(Statistic.DIVE_ONE_CM, "%n duikend.", StatTypes.DISTANCE),
    MINECART(Statistic.MINECART_ONE_CM, "%n in een attractie.", StatTypes.DISTANCE),
    JUMPED(Statistic.JUMP, "Je hebt in totaal %n keer gesprongen op deze server.", StatTypes.NORMAL);

    private Statistic statistic;
    private String line;
    private StatTypes statType;

    Stats(Statistic stat, String s, StatTypes st){
        this.statistic = stat;
        this.line = s;
        this.statType = st;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public String getLine() {
        return line;
    }

    public StatTypes getStatType() {
        return statType;
    }

    private static final List<Stats> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Stats random()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
