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

import nl.HorizonCraft.PretparkCore.Main;
import nl.HorizonCraft.PretparkCore.Menus.MyHorizon.MyHorizonMenu;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * This class has been created on 09/18/2015 at 9:17 PM by Cooltimmetje.
 */
public class AchievementMenu implements Listener{

    public static void open(Player p, Player target, AchievementTypes type){
        Inventory inv = Bukkit.createInventory(null, 54, "Achievements \u00BB " + p.getName());
        CorePlayer cp = PlayerUtils.getProfile(p);

        if(type == AchievementTypes.NORMAL){
            char[] unlocks = cp.getAchievements();

            int slot = 1;
            for(AchievementsEnum achievement : AchievementsEnum.values()){
                int id = achievement.getId();
                boolean unlocked = unlocks[id] == 't';
                int coins = achievement.getCoinReward();
                int keys = achievement.getKeyReward();
                int exp = achievement.getExpReward();
                String name = constructNameNormal(achievement.getName(), unlocked);
                String[] lore = constructLoreNormal(achievement.getDescription(), unlocked, coins, keys, exp, achievement.getType());
                Material m;
                if (unlocked) {
                    m = Material.DIAMOND;
                } else {
                    m = Material.COAL;
                }

                ItemUtils.createDisplay(inv, slot, m, 1, 0, name, lore);
                slot++;
            }
            ItemUtils.createDisplay(inv, 47,Material.DIAMOND_BLOCK,1,0, "&aBekijk Progressive Achievements");
        } else if (type == AchievementTypes.PROGRESSIVE) {
            char[] unlocks = cp.getProgressiveAchievements();
            int slot = 1;

            for(ProgressiveAchievementsEnum progressiveAchievement : ProgressiveAchievementsEnum.values()){
                int coins = progressiveAchievement.getCoins();
                int exp = progressiveAchievement.getExp();
                int boxes = progressiveAchievement.getBoxes();
                int keys = progressiveAchievement.getKeys();

                for(int i1=0; i1 < progressiveAchievement.getId().length; i1++){
                    int i = i1;
                    if(i1 == 5){
                        break;
                    }
                    boolean unlocked = unlocks[progressiveAchievement.getId()[i]] == 't';
                    String name = constructNameProgressive(unlocked, progressiveAchievement, i+1);
                    String[] lore = constructLoreProgressive(progressiveAchievement.getDescription(), progressiveAchievement.getLevels()[i], coins*(i+1), keys*(i+1), exp*(i+1), boxes*(i+1), progressiveAchievement.getAchievementType());
                    Material m;
                    if (unlocked) {
                        m = Material.DIAMOND_BLOCK;
                    } else {
                        m = Material.COAL_BLOCK;
                    }

                    ItemUtils.createDisplay(inv,slot+(i*9),m,1,0,name,lore);
                }
                slot++;
            }

            ItemUtils.createDisplay(inv, 47,Material.DIAMOND,1,0, "&aBekijk Normale Achievements");
        }

        ItemUtils.createDisplay(inv, 46, Material.ARROW, 1, 0, "&cTerug");
        ItemUtils.createDisplay(inv, 54, Material.LEAVES, 1, 0, "&aSorteer op: &bParkour/Doolhoven");
        ItemUtils.createDisplay(inv, 53, Material.MINECART, 1, 0, "&aSorteer op: &bAttracties");
        ItemUtils.createDisplay(inv, 52, Material.SLIME_BLOCK, 1, 0, "&aSorteer op: &bStaff Punching");
        ItemUtils.createDisplay(inv, 51, Material.GOLD_INGOT, 1, 0, "&aSorteer op: &bUnlockables");
        ItemUtils.createDisplay(inv, 50, Material.BOOK, 1, 0, "&aSorteer op: &bAlgemeen");


        target.openInventory(inv);
    }

    public static void open(Player p, Player target, AchievementTypes type, AchievementType achievementType){
        Inventory inv = Bukkit.createInventory(null, 54, "Achievements \u00BB " + p.getName());
        CorePlayer cp = PlayerUtils.getProfile(p);

        if(type == AchievementTypes.NORMAL){
            char[] unlocks = cp.getAchievements();

            int slot = 1;
            for(AchievementsEnum achievement : AchievementsEnum.values()){
                if(achievement.getType() == achievementType){
                    int id = achievement.getId();
                    boolean unlocked = unlocks[id] == 't';
                    int coins = achievement.getCoinReward();
                    int keys = achievement.getKeyReward();
                    int exp = achievement.getExpReward();
                    String name = constructNameNormal(achievement.getName(), unlocked);
                    String[] lore = constructLoreNormal(achievement.getDescription(), unlocked, coins, keys, exp, achievement.getType());
                    Material m;
                    if (unlocked) {
                        m = Material.DIAMOND;
                    } else {
                        m = Material.COAL;
                    }

                    ItemUtils.createDisplay(inv, slot, m, 1, 0, name, lore);
                    slot++;
                }
            }
            ItemUtils.createDisplay(inv, 47,Material.DIAMOND_BLOCK,1,0, "&aBekijk Progressive Achievements");
        } else if (type == AchievementTypes.PROGRESSIVE) {
            char[] unlocks = cp.getProgressiveAchievements();
            int slot = 1;

            for(ProgressiveAchievementsEnum progressiveAchievement : ProgressiveAchievementsEnum.values()){
                if(progressiveAchievement.getAchievementType() == achievementType){
                    int coins = progressiveAchievement.getCoins();
                    int exp = progressiveAchievement.getExp();
                    int boxes = progressiveAchievement.getBoxes();
                    int keys = progressiveAchievement.getKeys();

                    for(int i1=0; i1 < progressiveAchievement.getId().length; i1++){
                        if(i1 == 5){
                            break;
                        }
                        int i = i1;
                        boolean unlocked = unlocks[progressiveAchievement.getId()[i]] == 't';
                        String name = constructNameProgressive(unlocked, progressiveAchievement, i+1);
                        String[] lore = constructLoreProgressive(progressiveAchievement.getDescription(), progressiveAchievement.getLevels()[i], coins*(i+1), keys*(i+1), exp*(i+1), boxes*(i+1), progressiveAchievement.getAchievementType());
                        Material m;
                        if (unlocked) {
                            m = Material.DIAMOND_BLOCK;
                        } else {
                            m = Material.COAL_BLOCK;
                        }

                        ItemUtils.createDisplay(inv,slot+(i*9),m,1,0,name,lore);
                    }
                    slot++;
                }
            }

            ItemUtils.createDisplay(inv, 47,Material.DIAMOND,1,0, "&aBekijk Normale Achievements");
        }

        ItemUtils.createDisplay(inv, 46, Material.ARROW, 1, 0, "&cTerug");
        ItemUtils.createDisplay(inv, 54, Material.LEAVES, 1, 0, "&aSorteer op: &bParkour/Doolhoven");
        ItemUtils.createDisplay(inv, 53, Material.MINECART, 1, 0, "&aSorteer op: &bAttracties");
        ItemUtils.createDisplay(inv, 52, Material.SLIME_BLOCK, 1, 0, "&aSorteer op: &bStaff Punching");
        ItemUtils.createDisplay(inv, 51, Material.GOLD_INGOT, 1, 0, "&aSorteer op: &bUnlockables");
        ItemUtils.createDisplay(inv, 50, Material.BOOK, 1, 0, "&aSorteer op: &bAlgemeen");
        ItemUtils.createDisplay(inv, 49, Material.BARRIER, 1, 0, "&cWis Filter");


        target.openInventory(inv);
    }

    private static String constructNameProgressive(boolean unlocked, ProgressiveAchievementsEnum progressiveAchievements, int tier) {
        StringBuilder sb = new StringBuilder();
        if(unlocked){
            sb.append("&a");
        } else {
            sb.append("&c");
        }

        sb.append(progressiveAchievements.getName()).append(" ");

        switch (tier){
            case 1:
                sb.append("I");
                break;
            case 2:
                sb.append("II");
                break;
            case 3:
                sb.append("III");
                break;
            case 4:
                sb.append("IV");
                break;
            case 5:
                sb.append("V");
                break;
        }

        return sb.toString();
    }

    private static String[] constructLoreNormal(String lore, boolean unlocked, int coins, int keys, int exp, AchievementType type) {
        StringBuilder sb = new StringBuilder();

        String[] loreArray = lore.split("\n");
        for(String loreS : loreArray) {
            sb.append("&3").append(loreS).append("\n");
        }
        sb.append("&3Categorie: &a").append(type.getFriendlyName());
        sb.append("\n \n");
        sb.append("&3Rewards: ");
        sb.append("\n");
        sb.append("&6").append(coins).append(" coins").append("\n")
                .append("&d").append(keys).append(" Mystery Key(s)").append("\n")
                .append("&9").append(exp).append(" experience");

        return sb.toString().split("\n");
    }

    private static String constructNameNormal(String name, boolean unlocked) {
        if (unlocked) {
            return "&a" + name;
        } else {
            return "&c" + name;
        }
    }

    private static String[] constructLoreProgressive(String lore, int level, int coins, int keys, int exp, int boxes, AchievementType type) {
        StringBuilder sb = new StringBuilder();

        String[] loreArray = lore.split("\n");
        for(String loreS : loreArray) {
            sb.append("&3").append(loreS.replace("%v",""+level)).append("\n");
        }
        sb.append("&3Categorie: &a").append(type.getFriendlyName());
        sb.append("\n \n");
        sb.append("&3Rewards: ");
        sb.append("\n");
        sb.append("&6").append(coins).append(" coins").append("\n")
                .append("&d").append(keys).append(" Mystery Key(s)").append("\n")
                .append("&9").append(exp).append(" experience").append("\n")
                .append("&3").append(boxes).append(" Mystery Box(es)");

        return sb.toString().split("\n");
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(ChatColor.stripColor(event.getInventory().getName()).contains("Achievements")){
            event.setCancelled(true);
            Player p = Bukkit.getPlayer(ChatColor.stripColor(event.getInventory().getName().split(" ")[2]));
            Player target = (Player) event.getWhoClicked();
            Material m = event.getCurrentItem().getType();
            AchievementTypes at;
            if(event.getInventory().getItem(46).getType() == Material.DIAMOND){
                at = AchievementTypes.PROGRESSIVE;
            } else {
                at = AchievementTypes.NORMAL;
            }
            switch (m){
                case ARROW:
                    MyHorizonMenu.openMyHorizon(p, target, false);
                    break;
                case BOOK:
                    open(p, target, at, AchievementType.GENERAL);
                    break;
                case BARRIER:
                    open(p, target, at);
                    break;
                case SLIME_BLOCK:
                    open(p, target, at, AchievementType.STAFFPUNCH);
                    break;
                case LEAVES:
                    open(p, target, at, AchievementType.MAZES_PARKOUR);
                    break;
                case MINECART:
                    open(p, target, at, AchievementType.RIDES);
                    break;
                case GOLD_INGOT:
                    open(p, target, at, AchievementType.UNLOCKABLES);
                    break;
                case DIAMOND_BLOCK:
                    if(at != AchievementTypes.PROGRESSIVE){
                        open(p, target, AchievementTypes.PROGRESSIVE);
                    }
                    break;
                case DIAMOND:
                    if(at != AchievementTypes.NORMAL){
                        open(p, target, AchievementTypes.NORMAL);
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
