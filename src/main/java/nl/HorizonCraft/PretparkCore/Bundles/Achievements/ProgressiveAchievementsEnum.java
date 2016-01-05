/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 HorizonCraft
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
