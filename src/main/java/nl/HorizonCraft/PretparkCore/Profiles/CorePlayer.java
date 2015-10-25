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

package nl.HorizonCraft.PretparkCore.Profiles;

import nl.HorizonCraft.PretparkCore.Bundles.Gadgets.GadgetsEnum;
import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementsEnum;
import nl.HorizonCraft.PretparkCore.Bundles.Pets.PetType;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.ScoreboardUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * This class has been created on 09/17/2015 at 8:32 PM by Cooltimmetje.
 */

public class CorePlayer {

    private UUID uuid;
    private String name;

    private int coins;
    private int coinTime;
    private int boxes;
    private int boxTime;
    private int keys;
    private int experience;
    private int experienceTime;

    private char[] achievements;
    private char[] gadgets;
    private char[] pets;

    private boolean speed;

    private GadgetsEnum gadget;

    public CorePlayer(Player p){
        this.uuid = p.getUniqueId();
        this.name = p.getName();
    }

    /* --START COINS-- */

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getCoinTime() {
        return coinTime;
    }

    public void setCoinTime(int coinTime) {
        this.coinTime = coinTime;
    }

    public void addCoins(Player p, int add, String reason, boolean allowMultiplier, boolean playSound) {
        if (p.hasPermission("pc.coinmultiplier.2") && allowMultiplier) {
            add = add * 2;
        }

        setCoins(getCoins() + add);

        ChatUtils.sendMsg(p, "&6+" + add + " coins! (" + reason + ")");
        if (playSound) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        }
        ScoreboardUtils.updateScoreboard(p, false);
    }

    /* --END COINS-- */


    /* --START BOXES-- */

    public int getBoxes() {
        return boxes;
    }

    public void setBoxes(int boxes) {
        this.boxes = boxes;
    }

    public int getBoxTime() {
        return boxTime;
    }

    public void setBoxTime(int boxTime) {
        this.boxTime = boxTime;
    }

    public int getKeys() {
        return keys;
    }

    public void setKeys(int keys) {
        this.keys = keys;
    }

    public void addKeys(Player p, int add, String reason, boolean playSound){
        setKeys(getKeys() + add);

        ChatUtils.sendMsg(p, "&d+" + add + " Mystery Keys! (" + reason + ")");
        if(playSound) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        }
        ScoreboardUtils.updateScoreboard(p, false);
    }

    public void addBoxes(Player p, int add, String reason, boolean allowMultiplier, boolean playSound){
        if(p.hasPermission("pc.boxmultiplier.2") && allowMultiplier) {
            add = add * 2;
        }

        setBoxes(getBoxes() + add);

        ChatUtils.sendMsg(p, "&3+" + add + " MysteryBoxes! (" + reason + ")");
        if(playSound){
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        }
        ScoreboardUtils.updateScoreboard(p, false);
    }

    /* --END BOXES-- */


    /* --START ACHIEVEMENTS-- */

    public char[] getAchievements() {
        return achievements;
    }

    public void setAchievements(char[] achievements) {
        this.achievements = achievements;
    }

    public void awardAchievement(Player p, AchievementsEnum achievement){
        if(achievements[achievement.getId()] == 'f') {
            achievements[achievement.getId()] = 't';

            p.playSound(p.getLocation(), Sound.NOTE_PLING, 100, 1);
            MiscUtils.shootFirework(p.getLocation(), p.getWorld().getName(), true);

            ChatUtils.sendMsg(p, "&8-------- &a&lACHIEVEMENT GET! &8--------");
            ChatUtils.sendMsg(p, "&3Naam: &a" + achievement.getName());
            ChatUtils.sendMsg(p, "&3Beschrijving: &a" + achievement.getDescription());
            ChatUtils.sendMsg(p, "&3Rewards:");
            addCoins(p, achievement.getCoinReward(), "Achievement: " + achievement.getName(), false, false);
            addKeys(p, achievement.getKeyReward(), "Achievement: " + achievement.getName(), false);
            addExp(p, achievement.getExpReward(), "Achievement: " + achievement.getName(), false, false);
            ChatUtils.sendMsg(p, "&8-------- &a&lACHIEVEMENT GET! &8--------");
        }
    }

    /* --END ACHIEVEMENTS-- */


    /* --START GADGETS-- */

    public char[] getGadgets() {
        return gadgets;
    }

    public void setGadgets(char[] gadgets) {
        this.gadgets = gadgets;
    }

    /* --END GADGETS-- */


    /* --START PETS-- */

    public char[] getPets() {
        return pets;
    }

    public void setPets(char[] pets) {
        this.pets = pets;
    }

    public boolean hasPet(PetType petType) {
        return getPets()[petType.getId()] == 't';
    }

    /* --END PETS-- */


    /* --START PREFS-- */

    public boolean hasSpeed() {
        return speed;
    }

    public void setSpeed(boolean speed) {
        this.speed = speed;
    }

    /* --END PREFS-- */


    /* --START EXPERIENCE-- */

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getExperienceTime() {
        return experienceTime;
    }

    public void setExperienceTime(int experienceTime) {
        this.experienceTime = experienceTime;
    }

    public void addExp(Player p, int add, String reason, boolean allowMultiplier, boolean playSound) {
        setExperience(getExperience() + add);

        ChatUtils.sendMsg(p, "&9+" + add + " experience! (" + reason + ")");
        if (playSound) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        }
        ScoreboardUtils.updateScoreboard(p, false);

        calculateExp(p);
    }

    public void calculateExp(Player p) {
        int exp, level, needed;
        exp = getExperience();
        level = 1;
        needed = Variables.EXP_BASE_LEVEL;

        while (exp >= needed) {
            exp = exp - needed;
            level++;
            needed = (int) (Variables.EXP_BASE_LEVEL * Math.pow(Variables.EXP_MODIFIER, level));
        }

        p.setLevel(level);
        p.setExp((float) ((double) exp / (double) needed));

        if (level >= 5) {
            awardAchievement(p, AchievementsEnum.LEVEL_UP);
        }

    }

    public String calculateExpString(Player p) {
        int exp, level, needed;
        exp = getExperience();
        level = 1;
        needed = Variables.EXP_BASE_LEVEL;

        while (exp >= needed) {
            exp = exp - needed;
            level++;
            needed = (int) (Variables.EXP_BASE_LEVEL * Math.pow(Variables.EXP_MODIFIER, level));
        }

        return needed + "," + exp + "," + level;
    }

    /* --END EXPERIENCE-- */

}
