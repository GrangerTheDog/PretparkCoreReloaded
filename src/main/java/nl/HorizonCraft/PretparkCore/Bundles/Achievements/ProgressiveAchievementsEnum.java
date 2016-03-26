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
 * Created by Cooltimmetje on 1/5/2016 at 3:31 PM.
 */
public enum ProgressiveAchievementsEnum {

    //format: NAME(id array, "Friendly Name", "Description (use %v for level value)", level1, level2, level3, level4, level5, baseCoin, baseExp, baseKeys, baseBoxes, AchievementType)
    COINS(new int[] {0,1,2,3,4}, "Coin Collector", "Verzamel %v coins!", 50, 250, 1, 1, AchievementType.UNLOCKABLES, new int[] {500, 5000, 10000, 20000, 100000}), //500, 5000, 10000, 20000, 100000
    EXP(new int[] {5,6,7,8,9}, "Experienced Player", "Haal level %v!", 100, 300, 1, 2, AchievementType.UNLOCKABLES, new int[] {5, 15, 50, 100, 200}); //5, 15, 50, 100, 200

    private int[] id;
    private String name;
    private String description;
    private int coins;
    private int exp;
    private int keys;
    private int boxes;
    private AchievementType achievementType;
    private int[] levels;

    ProgressiveAchievementsEnum(int[] iA, String s, String s2, int i, int i2, int i3, int i4, AchievementType at, int[] iA2){
        this.id = iA;
        this.name = s;
        this.description = s2;
        this.coins = i;
        this.exp = i2;
        this.keys = i3;
        this.boxes = i4;
        this.achievementType = at;
        this.levels = iA2;
    }

    public int[] getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCoins() {
        return coins;
    }

    public int getExp() {
        return exp;
    }

    public int getKeys() {
        return keys;
    }

    public int getBoxes() {
        return boxes;
    }

    public AchievementType getAchievementType() {
        return achievementType;
    }

    public int[] getLevels() {
        return levels;
    }
}
