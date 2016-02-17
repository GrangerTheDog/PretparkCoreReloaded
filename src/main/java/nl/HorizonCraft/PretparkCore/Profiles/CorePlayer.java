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

package nl.HorizonCraft.PretparkCore.Profiles;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import mkremins.fanciful.FancyMessage;
import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementsEnum;
import nl.HorizonCraft.PretparkCore.Bundles.Achievements.ProgressiveAchievementsEnum;
import nl.HorizonCraft.PretparkCore.Bundles.Gadgets.GadgetsEnum;
import nl.HorizonCraft.PretparkCore.Bundles.Pets.PetType;
import nl.HorizonCraft.PretparkCore.Bundles.Ranks.RanksEnum;
import nl.HorizonCraft.PretparkCore.Bundles.Wardrobe.PiecesEnum;
import nl.HorizonCraft.PretparkCore.Discord.ConnectionManager;
import nl.HorizonCraft.PretparkCore.Discord.DiscordUtils;
import nl.HorizonCraft.PretparkCore.Menus.MyHorizon.SettingsEnum;
import nl.HorizonCraft.PretparkCore.Utilities.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * This class has been created on 09/17/2015 at 8:32 PM by Cooltimmetje.
 */

public class CorePlayer {

    private int id;
    private UUID uuid;
    private String name;

    private int coins;
    private int coinTime;
    private int boxes;
    private int boxTime;
    private int keys;
    private int experience;
    private int experienceTime;
    private int level;
    private int dust;
    private int karma;

    private char[] achievements;
    private char[] progressiveAchievements;
    private char[] gadgets;
    private char[] pets;
    private char[] pieces;

    private PiecesEnum head;
    private PiecesEnum chest;
    private PiecesEnum legs;
    private PiecesEnum boots;
    private GadgetsEnum gadget;

    private int maze_1_record;
    private int maze_2_record;
    private int daily_streak_record;

    private int coinDelivery;
    private int expDelivery;
    private int boxDelivery;
    private int keyDelivery;
    private int dustDelivery;
    private int karmaDelivery;
    private int current_daily_streak;
    private long last_daily_claim;
    private boolean claimedSpecialDay;

    private boolean speed;
    private char[] prefs;

    private Hologram spawnHologram;

    private RanksEnum rank;
    private long rankExpire;

    private String discordID;
    private String discordVerifyCode;

    public CorePlayer(Player p){
        this.uuid = p.getUniqueId();
        this.name = p.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer(){
        return Bukkit.getPlayer(name);
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
        if (RanksEnum.hasPermission(this,RanksEnum.VIP) && allowMultiplier) {
            add = add * 2;
        }

        setCoins(getCoins() + add);

        ChatUtils.sendMsg(p, "&6+" + add + " coins! (" + reason + ")");
        if (playSound) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        }
        ScoreboardUtils.updateScoreboard(p, false);
        awardProgressive();
    }

    public void removeCoins(Player p, int remove, String reason, boolean playSound) {
        setCoins(getCoins() - remove);

        ChatUtils.sendMsg(p, "&6-" + remove + " coins! (" + reason + ")");
        if (playSound) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        }
        ScoreboardUtils.updateScoreboard(p, false);
    }

    public void setCoinsTrans(Player p, int set, String reason, boolean playSound) {
        setCoins(set);

        ChatUtils.sendMsg(p, "&6=" + set + " coins! (" + reason + ")");
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

    public void addKeys(Player p, int add, String reason, boolean allowMultiplier, boolean playSound){
        if(RanksEnum.hasPermission(this,RanksEnum.VIP) && allowMultiplier){
            add = add * 2;
        }

        setKeys(getKeys() + add);

        ChatUtils.sendMsg(p, "&d+" + add + " Mystery Keys! (" + reason + ")");
        if(playSound) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        }
        ScoreboardUtils.updateScoreboard(p, false);
    }

    public void removeKeys(Player p, int add, String reason, boolean playSound){
        setKeys(getKeys() - add);

        ChatUtils.sendMsg(p, "&d-" + add + " Mystery Keys! (" + reason + ")");
        if(playSound) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        }
        ScoreboardUtils.updateScoreboard(p, false);
    }

    public void addBoxes(Player p, int add, String reason, boolean allowMultiplier, boolean playSound){
        if(RanksEnum.hasPermission(this,RanksEnum.VIP) && allowMultiplier) {
            add = add * 2;
        }

        setBoxes(getBoxes() + add);

        ChatUtils.sendMsg(p, "&3+" + add + " MysteryBoxes! (" + reason + ")");
        if(playSound){
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        }
        ScoreboardUtils.updateScoreboard(p, false);
    }

    public void removeBoxes(Player p, int add, String reason, boolean playSound){
        setBoxes(getBoxes() - add);

        ChatUtils.sendMsg(p, "&3-" + add + " MysteryBoxes! (" + reason + ")");
        if(playSound){
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        }
        ScoreboardUtils.updateScoreboard(p, false);
    }

    public int getDust() {
        return dust;
    }

    public void setDust(int dust) {
        this.dust = dust;
    }

    public void addDust(Player p, int add, String reason, boolean allowMultiplier, boolean playSound){
        if(RanksEnum.hasPermission(this,RanksEnum.VIP) && allowMultiplier) {
            add = add * 2;
        }

        setDust(getDust() + add);

        ChatUtils.sendMsg(p, "&b+" + add + " MysteryDust! (" + reason + ")");
        if(playSound){
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        }
        ScoreboardUtils.updateScoreboard(p, false);
    }

    public void removeDust(Player p, int add, String reason, boolean playSound){

        setDust(getDust() - add);

        ChatUtils.sendMsg(p, "&b-" + add + " MysteryDust! (" + reason + ")");
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
            addKeys(p, achievement.getKeyReward(), "Achievement: " + achievement.getName(), false, false);
            addExp(p, achievement.getExpReward(), "Achievement: " + achievement.getName(), false, false);
        }
    }

    public void revokeAchievement(int id){
        achievements[id] = 'f';
    }

    public char[] getProgressiveAchievements() {
        return progressiveAchievements;
    }

    public void setProgressiveAchievements(char[] progressiveAchievements) {
        this.progressiveAchievements = progressiveAchievements;
    }

    public void awardProgressiveAchievement(Player p, ProgressiveAchievementsEnum achievement, int level){
        if(progressiveAchievements[achievement.getId()[level-1]] == 'f') {
            progressiveAchievements[achievement.getId()[level-1]] = 't';

            p.playSound(p.getLocation(), Sound.NOTE_PLING, 100, 1);
            MiscUtils.shootFirework(p.getLocation(), p.getWorld().getName(), true);

            ChatUtils.sendMsg(p, "&8-------- &a&lACHIEVEMENT GET! &8--------");
            ChatUtils.sendMsg(p, "&3Naam: &a" + achievement.getName() + " " + levelString(level));
            ChatUtils.sendMsg(p, "&3Beschrijving: &a" + achievement.getDescription().replace("%v", "" + achievement.getLevels()[level-1]));
            ChatUtils.sendMsg(p, "&3Rewards:");
            addCoins(p, achievement.getCoins()*level, "Achievement: " + achievement.getName() + " " + levelString(level), false, false);
            addKeys(p, achievement.getKeys()*level, "Achievement: " + achievement.getName() + " " + levelString(level), false, false);
            addExp(p, achievement.getExp()*level, "Achievement: " + achievement.getName() + " " + levelString(level), false, false);
            addBoxes(p, achievement.getBoxes()*level, "Achievement: " + achievement.getName() + " " + levelString(level), false, false);
        }
    }

    private String levelString(int level){
        switch (level){
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
        }
        return "null";
    }

    public void awardProgressive(){
        Player p = Bukkit.getPlayer(uuid);
        if(level >= 200) {
            awardProgressiveAchievement(p, ProgressiveAchievementsEnum.EXP, 5);
        }
        if (level >= 100) {
            awardProgressiveAchievement(p, ProgressiveAchievementsEnum.EXP, 4);
        }
        if (level >= 50) {
            awardProgressiveAchievement(p, ProgressiveAchievementsEnum.EXP, 3);
        }
        if (level >= 15) {
            awardProgressiveAchievement(p, ProgressiveAchievementsEnum.EXP, 2);
        }
        if (level >= 5) {
            awardProgressiveAchievement(p, ProgressiveAchievementsEnum.EXP, 1);
        }

        if(coins >= 100000) {
            awardProgressiveAchievement(p, ProgressiveAchievementsEnum.COINS, 5);
        }
        if (coins >= 20000) {
            awardProgressiveAchievement(p, ProgressiveAchievementsEnum.COINS, 4);
        }
        if (coins >= 10000) {
            awardProgressiveAchievement(p, ProgressiveAchievementsEnum.COINS, 3);
        }
        if (coins >= 5000) {
            awardProgressiveAchievement(p, ProgressiveAchievementsEnum.COINS, 2);
        }
        if (coins >= 500) {
            awardProgressiveAchievement(p, ProgressiveAchievementsEnum.COINS, 1);
        }
    }

    public boolean hasAchievementUnlocked(AchievementsEnum achievement){
        return getAchievements()[achievement.getId()] == 't';
    }

    public boolean hasAchievementUnlocked(ProgressiveAchievementsEnum achievement, int level){
        return getProgressiveAchievements()[achievement.getId()[level-1]] == 't';
    }

    /* --END ACHIEVEMENTS-- */


    /* --START GADGETS-- */

    public char[] getGadgets() {
        return gadgets;
    }

    public void setGadgets(char[] gadgets) {
        this.gadgets = gadgets;
    }

    public void unlockGadget(GadgetsEnum gadget, Player p, boolean playSound, boolean playFirework, boolean playChat) {
        if (gadgets[gadget.getId()] == 'f') {
            gadgets[gadget.getId()] = 't';

            if (playSound)
                p.playSound(p.getLocation(), Sound.NOTE_PLING, 100, 1);

            if (playFirework)
                MiscUtils.shootFirework(p.getLocation(), p.getWorld().getName(), true);

            if (playChat) {
                ChatUtils.sendMsg(p, "&8-------- &a&lGADGET UNLOCKED! &8--------");
                ChatUtils.sendMsg(p, "&" + gadget.getWeight().getColor() + gadget.getName());
            }
        }
        awardAchievement(p, AchievementsEnum.UNLOCK_GADGET);
    }

    public GadgetsEnum getGadget() {
        return gadget;
    }

    public void setGadget(GadgetsEnum gadget) {
        this.gadget = gadget;
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

    public void unlockPet(PetType petType, Player p, boolean playSound, boolean playFirework, boolean playChat) {
        if (pets[petType.getId()] == 'f') {
            pets[petType.getId()] = 't';

            if (playSound)
                p.playSound(p.getLocation(), Sound.NOTE_PLING, 100, 1);

            if (playFirework)
                MiscUtils.shootFirework(p.getLocation(), p.getWorld().getName(), true);

            if (playChat) {
                ChatUtils.sendMsg(p, "&8-------- &a&lPET UNLOCKED! &8--------");
                ChatUtils.sendMsg(p, "&" + petType.getWeight().getColor() + petType.getName());
            }
        }
        awardAchievement(p, AchievementsEnum.UNLOCK_PET);
    }

    /* --END PETS-- */


    /* --START PREFS-- */

    public boolean hasSpeed() {
        return speed;
    }

    public void setSpeed(boolean speed) {
        this.speed = speed;
    }

    public void setPrefs(char[] prefs){
        this.prefs = prefs;
    }

    public char[] getPrefs(){
        return prefs;
    }

    public boolean getSetting(SettingsEnum setting){
        return getPrefs()[setting.getId()] == 't';
    }

    public void toggleSetting(SettingsEnum setting){
        prefs[setting.getId()] = prefs[setting.getId()] == 'f' ? 't' : 'f';
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
        if(RanksEnum.hasPermission(this,RanksEnum.VIP) && allowMultiplier){
            add = add * 2;
        }
        setExperience(getExperience() + add);

        ChatUtils.sendMsg(p, "&9+" + add + " experience! (" + reason + ")");
        if (playSound) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        }
        ScoreboardUtils.updateScoreboard(p, false);

        calculateExp(p, false);
    }

    public void calculateExp(Player p, boolean join) {
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

        if (join) {
            setLevel(level);
        } else {
            if (level != getLevel()) {
                setLevel(level);

                TitleUtils.sendTitle(p, "&e&lLEVEL UP!", "&eJe bent nu &9level " + getLevel() + "&a!", 20, 80, 20);
            }
        }

        awardProgressive();
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

    public int getLevel() {
        return level;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    public void removeExp(Player p, int remove, String reason, boolean playSound) {
        setExperience(getExperience() - remove);

        ChatUtils.sendMsg(p, "&9-" + remove + " experience! (" + reason + ")");
        if (playSound) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        }
        ScoreboardUtils.updateScoreboard(p, false);

        calculateExp(p, false);
    }

    public void setExp(Player p, int set, String reason, boolean playSound) {
        setExperience(set);

        ChatUtils.sendMsg(p, "&9=" + set + " experience! (" + reason + ")");
        if (playSound) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        }
        ScoreboardUtils.updateScoreboard(p, false);

        calculateExp(p, false);
    }

    /* --END EXPERIENCE-- */


    /* --START WARDROBE-- */

    public char[] getPieces() {
        return pieces;
    }

    public void setPieces(char[] pieces) {
        this.pieces = pieces;
    }

    public void unlockClothing(PiecesEnum piece, Player p, boolean playSound, boolean playFirework, boolean playChat) {
        if (pieces[piece.getId()] == 'f') {
            pieces[piece.getId()] = 't';

            if (playSound)
                p.playSound(p.getLocation(), Sound.NOTE_PLING, 100, 1);

            if (playFirework)
                MiscUtils.shootFirework(p.getLocation(), p.getWorld().getName(), true);

            if (playChat) {
                ChatUtils.sendMsg(p, "&8-------- &a&lKLEDING UNLOCKED! &8--------");
                ChatUtils.sendMsg(p, "&" + piece.getWeight().getColor() + piece.getSuit().getName() + " " + piece.getSuitType().getName());
            }
        }
        awardAchievement(p, AchievementsEnum.UNLOCK_CLOTHING);
    }

    public void setHead(PiecesEnum head) {
        this.head = head;
    }

    public void setChest(PiecesEnum chest) {
        this.chest = chest;
    }

    public void setLegs(PiecesEnum legs) {
        this.legs = legs;
    }

    public PiecesEnum getBoots() {
        return boots;
    }

    public void setBoots(PiecesEnum boots) {
        this.boots = boots;
    }

    public PiecesEnum getLegs() {
        return legs;
    }

    public PiecesEnum getChest() {
        return chest;
    }

    public PiecesEnum getHead() {
        return head;
    }

    /* --END WARDROBE-- */


    /* --START RECORDS-- */

    public int getMaze_1_record() {
        return maze_1_record;
    }

    public void setMaze_1_record(int maze_1_record) {
        this.maze_1_record = maze_1_record;
    }

    public int getMaze_2_record() {
        return maze_2_record;
    }

    public void setMaze_2_record(int maze_2_record) {
        this.maze_2_record = maze_2_record;
    }

    public int getDaily_streak_record() {
        return daily_streak_record;
    }

    public void setDaily_streak_record(int daily_streak_record) {
        this.daily_streak_record = daily_streak_record;
    }

    /* --END RECORDS-- */


    /* --START HOLOGRAMS-- */

    public Hologram getSpawnHologram() {
        return spawnHologram;
    }

    public void setSpawnHologram(Hologram spawnHologram) {
        this.spawnHologram = spawnHologram;
    }

    /* --END HOLOGRAMS-- */


     /* --START DELIVERY-- */

    public int getCurrent_daily_streak() {
        return current_daily_streak;
    }

    public void setCurrent_daily_streak(int current_daily_streak) {
        this.current_daily_streak = current_daily_streak;
    }

    public long getLast_daily_claim() {
        return last_daily_claim;
    }

    public void setLast_daily_claim(long last_daily_claim) {
        this.last_daily_claim = last_daily_claim;
    }

    public int getCoinDelivery() {
        return coinDelivery;
    }

    public void setCoinDelivery(int coinDelivery) {
        this.coinDelivery = coinDelivery;
    }

    public int getExpDelivery() {
        return expDelivery;
    }

    public void setExpDelivery(int expDelivery) {
        this.expDelivery = expDelivery;
    }

    public int getBoxDelivery() {
        return boxDelivery;
    }

    public void setBoxDelivery(int boxDelivery) {
        this.boxDelivery = boxDelivery;
    }

    public int getKeyDelivery() {
        return keyDelivery;
    }

    public void setKeyDelivery(int keyDelivery) {
        this.keyDelivery = keyDelivery;
    }

    public int getDustDelivery() {
        return dustDelivery;
    }

    public void setDustDelivery(int dustDelivery) {
        this.dustDelivery = dustDelivery;
    }

    public int getKarmaDelivery(){
        return this.karmaDelivery;
    }

    public void setKarmaDelivery(int karmaDelivery){
        this.karmaDelivery = karmaDelivery;
    }

    public boolean hasDelivery(){
        return coinDelivery != 0 || expDelivery != 0 || boxDelivery != 0 || keyDelivery != 0 || dustDelivery != 0 || karmaDelivery != 0;
    }

    public boolean hasDaily() {
        long lastDailyClaim = getLast_daily_claim();
        long curTime = System.currentTimeMillis();
        long nextClaim = lastDailyClaim + 86400000;

        return (nextClaim < curTime) || (lastDailyClaim == 0);
    }

    public boolean hasClaimedSpecialDay() {
        return claimedSpecialDay;
    }

    public void setClaimedSpecialDay(boolean claimedSpecialDay) {
        this.claimedSpecialDay = claimedSpecialDay;
    }

     /* --END DELIVERY-- */


    /* --START RANKS-- */

    public RanksEnum getRank() {
        return rank;
    }

    public void setRank(RanksEnum rank) {
        this.rank = rank;
    }

    public long getRankExpire() {
        return rankExpire;
    }

    public void setRankExpire(long rankExpire) {
        this.rankExpire = rankExpire;
    }

    public void updateRank(Player p){
        if(!getRank().doesExpire()){
            setRankExpire(0);
        }
        if((getRankExpire() < System.currentTimeMillis()) && (getRankExpire() != 0) && getRank().doesExpire()){
            FancyMessage msg = new FancyMessage("Rang> ").color(ChatColor.BLUE);
            msg.then("Je ").color(ChatColor.GREEN).then(getRank().getFriendlyName()).color(getRank().getChatColor()).then(" is verlopen! Wil je langer genieten van je voordelen? Ga dan naar de shop: ").color(ChatColor.GREEN);
            msg.then("[").color(ChatColor.DARK_GRAY).then("Klik hier om naar de webstore te gaan").color(ChatColor.DARK_AQUA).link("http://store.horizoncraft.nl/").then("]").color(ChatColor.DARK_GRAY);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + p.getName() + " " + msg.toJSONString());

            setRank(RanksEnum.BEZOEKER);
            setRankExpire(0);
        }

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + getRank().getGmName());
        DiscordUtils.updateRoles(this, ConnectionManager.discordClient.getUserByID(getDiscordID()));
    }

    /* --END RANKS-- */


    /* --START KARMA-- */

    public void addKarma(Player p, int add, String reason, boolean allowMultiplier, boolean playSound) {
        if(RanksEnum.hasPermission(this,RanksEnum.VIP) && allowMultiplier){
            add = add * 2;
        }
        reason = "???";
        setKarma(getKarma() + add);

//        ChatUtils.sendMsg(p, "&2+" + add + " karma! (" + reason + ")");
        TitleUtils.sendAction(p, "&2+" + add + " ???! (" + reason + ")",3);
        if (playSound) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        }
//        ScoreboardUtils.updateScoreboard(p, false);
    }

    public void removeKarma(Player p, int remove, String reason, boolean playSound) {
        setKarma(getKarma() - remove);
        reason = "???";

//        ChatUtils.sendMsg(p, "&2-" + remove + " karma! (" + reason + ")");
        TitleUtils.sendAction(p, "&2-" + remove + " ???! (" + reason + ")",3);
        if (playSound) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        }
//        ScoreboardUtils.updateScoreboard(p, false);
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    /* --END KARMA-- */

    /* --START DISCORD-- */

    public String getDiscordID() {
        return discordID;
    }

    public void setDiscordID(String discordID) {
        this.discordID = discordID;
    }

    public String getDiscordVerifyCode() {
        return discordVerifyCode;
    }

    public void setDiscordVerifyCode(String discordVerifyCode) {
        this.discordVerifyCode = discordVerifyCode;
    }

    /* --END DISCORD-- */

}
