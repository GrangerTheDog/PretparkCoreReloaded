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

package nl.HorizonCraft.PretparkCore.Bundles.Achievements;

/**
 * This class has been created on 09/18/2015 at 3:53 PM by Cooltimmetje.
 */
public enum AchievementsEnum {

    FIRST_TIME_JOIN(0, "To the Horizon and Beyond!", "Join de server voor de eerste keer!", 50, 1, 100, AchievementType.GENERAL),
    KOALA_SLAP(1, "I don't like koala's!", "Launch xBrandy!", 10, 1, 200, AchievementType.STAFFPUNCH),
    PEDOBEAR_SLAP(2, "PEDOBEAR!", "Launch 78wesley!", 10, 1, 200, AchievementType.STAFFPUNCH),
    MAZE_COMPLETE_1(3, "Into the maze!", "Haal het einde van doolhof 1!", 25, 2, 500, AchievementType.MAZES_PARKOUR),
    FE_RIDE(4, "Boer Harms op de Trekker!", "Maak een ritje op de Farm Expedition!", 25, 1, 600, AchievementType.RIDES),
    MELK_SLAP(5, "Maar ik lus geen melk :(", "Launch BekertjeZuivel!", 10, 1, 200, AchievementType.STAFFPUNCH),
//    LEVEL_UP(6, "Leveling Up...", "Haal level 5.", 100, 3, 750, AchievementType.GENERAL),
    COOL_SLAP(7, "Not so cool anymore, I guess!", "Launch Cooltimmetje", 100, 5, 1000, AchievementType.STAFFPUNCH),
    SVEN_SLAP(8, "BEERTJE!", "Launch SVENBEER!", 10, 1, 200, AchievementType.STAFFPUNCH),
    UNLOCK_GADGET(9, "FANCY TECHNOLOGY!", "Unlock je eerste gadget!", 25, 1, 500, AchievementType.UNLOCKABLES),
    UNLOCK_CLOTHING(10, "WEARING THE SWAG", "Unlock je eerste kledingstuk!", 25, 1, 500, AchievementType.UNLOCKABLES),
    UNLOCK_PET(11, "EEN VRIENDJE!", "Unlock je eerste pet!", 25, 1, 500, AchievementType.UNLOCKABLES),
    ROO_SLAP(12, "3..2..1.. LIFT OFF!", "Launch Roobein123", 10, 1, 200, AchievementType.STAFFPUNCH),
    GROT_FIND(13, "Het geheime kamertje...", "Vind het geheime kamertje in de berg.", 50, 1, 250, AchievementType.GENERAL);

    private int id;
    private String name;
    private String description;
    private int coinReward;
    private int keyReward;
    private int expReward;
    private AchievementType achievementType;

    AchievementsEnum(int i, String s, String s1, int i1, int i2, int i3, AchievementType at) {
        this.id = i;
        this.name = s;
        this.description = s1;
        this.coinReward = i1;
        this.keyReward = i2;
        this.expReward = i3;
        this.achievementType = at;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getCoinReward(){
        return coinReward;
    }

    public int getKeyReward(){
        return keyReward;
    }

    public AchievementType getType() {
        return achievementType;
    }

    public int getExpReward() {
        return expReward;
    }
}
