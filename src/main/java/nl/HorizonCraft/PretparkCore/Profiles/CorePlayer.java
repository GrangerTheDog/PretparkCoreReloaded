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
import nl.HorizonCraft.PretparkCore.Enums.AchievementsEnum;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.ScoreboardUtils;
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

    private char[] achievements;
    private char[] gadgets;

    private boolean speed;

    private GadgetsEnum gadget;

    public CorePlayer(Player p){
        this.uuid = p.getUniqueId();
        this.name = p.getName();
    }

    public int getCoins(){
        return coins;
    }

    public int getCoinTime(){
        return coinTime;
    }

    public int getBoxes(){
        return boxes;
    }

    public int getKeys(){
        return keys;
    }

    public boolean getSpeed(){
        return speed;
    }

    public void setCoins(int value){
        this.coins = value;
    }

    public void setCoinTime(int value){
        this.coinTime = value;
    }

    public void setSpeed(boolean value){
        this.speed = value;
    }

    public void setBoxes(int value){
        this.boxes = value;
    }

    public void setAchievements(char[] value){
        this.achievements = value;
    }

    public char[] getAchievements(){
        return achievements;
    }

    public void setKeys(int value){
        this.keys = value;
    }

    public void addKeys(Player p, int add, String reason, boolean playSound){
        setKeys(getKeys() + add);

        ChatUtils.sendMsg(p, "&d+" + add + " Mystery Keys! (" + reason + ")");
        if(playSound) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        }
        ScoreboardUtils.updateScoreboard(p, false);
    }

    public void addCoins(Player p, int add, String reason, boolean allowMultiplier, boolean playSound) {
        if(p.hasPermission("pc.coinmultiplier.2") && allowMultiplier) {
            add = add * 2;
        }

        setCoins(getCoins() + add);

        ChatUtils.sendMsg(p, "&6+" + add + " coins! (" + reason + ")");
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
            ChatUtils.sendMsg(p, "&8-------- &a&lACHIEVEMENT GET! &8--------");
        }
    }

    public void addGadget(Player p, GadgetsEnum gadget){
        gadgets[gadget.getId()] = 't';
    }

    public char[] getGadgets() {
        return gadgets;
    }

    public void setGadgets(char[] gadgets) {
        this.gadgets = gadgets;
    }

    public GadgetsEnum getGadget() {
        return gadget;
    }

    public void setGadget(GadgetsEnum gadget) {
        this.gadget = gadget;
    }

    public int getBoxTime() {
        return boxTime;
    }

    public void setBoxTime(int boxTime) {
        this.boxTime = boxTime;
    }
}
