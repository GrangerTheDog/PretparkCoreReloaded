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
