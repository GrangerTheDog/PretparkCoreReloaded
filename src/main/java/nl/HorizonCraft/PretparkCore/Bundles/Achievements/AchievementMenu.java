/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2016 Tim Medema
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

import nl.HorizonCraft.PretparkCore.Menus.MyHorizon.MyHorizonMenu;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * This class has been created on 09/18/2015 at 9:17 PM by Cooltimmetje.
 */
public class AchievementMenu implements Listener{

    public static void open(Player p, Player target, AchievementTypes type){
        Inventory inv = Bukkit.createInventory(null, 54, "Achievements \u00BB " + p.getName());
        CorePlayer cp = PlayerUtils.getProfile(p);

        if(type == AchievementTypes.NORMAL){
            int slot = 1;
            for(AchievementsEnum achievement : AchievementsEnum.values()){
                ItemUtils.createDisplay(constructNormal(achievement, cp), inv, slot);
                slot++;
            }
            ItemUtils.createDisplay(inv, 47,Material.DIAMOND_BLOCK,1,0, "&aBekijk Progressive Achievements");
        } else if(type == AchievementTypes.PROGRESSIVE) {
            int slot = 1;
            for(ProgressiveAchievementsEnum achievement : ProgressiveAchievementsEnum.values()) {
                for(int i=0; i < achievement.getId().length; i++) {
                    if (i == 5) {
                        break;
                    }
                    ItemUtils.createDisplay(constructProgressive(achievement,cp,i+1),inv,slot+((i*9)));
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

    public static void open(Player p, Player target, AchievementTypes type, AchievementType category){
        Inventory inv = Bukkit.createInventory(null, 54, "Achievements \u00BB " + p.getName());
        CorePlayer cp = PlayerUtils.getProfile(p);

        if(type == AchievementTypes.NORMAL){
            int slot = 1;
            for(AchievementsEnum achievement : AchievementsEnum.values()){
                if(achievement.getType() == category) {
                    ItemUtils.createDisplay(constructNormal(achievement, cp), inv, slot);
                    slot++;
                }
            }
            ItemUtils.createDisplay(inv, 47,Material.DIAMOND_BLOCK,1,0, "&aBekijk Progressive Achievements");
        } else if(type == AchievementTypes.PROGRESSIVE) {
            int slot = 1;
            for(ProgressiveAchievementsEnum achievement : ProgressiveAchievementsEnum.values()) {
                if (achievement.getAchievementType() == category) {
                    for(int i=0; i < achievement.getId().length; i++) {
                        if (i == 5) {
                            break;
                        }
                        ItemUtils.createDisplay(constructProgressive(achievement,cp,i+1),inv,slot+(i*9));
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

    private static ItemStack constructNormal(AchievementsEnum achievement, CorePlayer cp){
        String name;
        Material m;

        if(cp.hasAchievementUnlocked(achievement)){
            name = "&a" + achievement.getName();
            m = Material.DIAMOND;
        } else if(achievement.isMysteryAchievement()){
            name = "&c???";
            m = Material.COAL;
        } else {
            name = "&c" + achievement.getName();
            m = Material.COAL;
        }

        StringBuilder loreBuilder = new StringBuilder();
        if(!cp.hasAchievementUnlocked(achievement) && achievement.isMysteryAchievement()){
            loreBuilder.append("???").append("\n");
        } else {
            loreBuilder.append(achievement.getDescription()).append("\n");
        }
        loreBuilder.append("Categorie: &a").append(achievement.getType().getFriendlyName());

        loreBuilder.append("\n \n");
        loreBuilder.append("Rewards: \n");
        loreBuilder.append("&6").append(achievement.getCoinReward()).append(" coins").append("\n")
                .append("&d").append(achievement.getKeyReward()).append(" Mystery Key(s)").append("\n")
                .append("&9").append(achievement.getExpReward()).append(" experience");

        return ItemUtils.createItemstack(m,1,0,name,loreBuilder.toString().trim().split("\n"));
    }

    private static ItemStack constructProgressive(ProgressiveAchievementsEnum achievement, CorePlayer cp, int level){
        StringBuilder sbName = new StringBuilder();
        boolean unlocked = cp.hasAchievementUnlocked(achievement,level);
        Material m;
        if(unlocked){
            sbName.append("&a");
            m = Material.DIAMOND_BLOCK;
        } else {
            sbName.append("&c");
            m = Material.COAL_BLOCK;
        }

        sbName.append(achievement.getName()).append(" ");

        switch (level){
            case 1:
                sbName.append("I");
                break;
            case 2:
                sbName.append("II");
                break;
            case 3:
                sbName.append("III");
                break;
            case 4:
                sbName.append("IV");
                break;
            case 5:
                sbName.append("V");
                break;
        }

        String sbLore = achievement.getDescription().replace("%v", achievement.getLevels()[level - 1] + "") +
                "\n&3Categorie: &a" + achievement.getAchievementType().getFriendlyName() +
                "\n \n" +
                "&3Rewards: " +
                "\n" +
                "&6" + achievement.getCoins() * level + " coins" + "\n" +
                "&d" + achievement.getKeys() * level + " Mystery Key(s)" + "\n" +
                "&9" + achievement.getExp() * level + " experience" + "\n" +
                "&3" + achievement.getBoxes() * level + " Mystery Box(es)";

        return ItemUtils.createItemstack(m,1,0,sbName.toString(), sbLore.trim().split("\n"));
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
                    MyHorizonMenu.openMyHorizon(target, p, p != target);
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
