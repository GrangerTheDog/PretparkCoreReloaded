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

/**
 * This class has been created on 09/18/2015 at 9:17 PM by Cooltimmetje.
 */
public class AchievementMenu implements Listener{

    public static void open(Player p, AchievementType achievementType){
        Inventory inv = Bukkit.createInventory(null, 54, "Achievements");

        CorePlayer cp = PlayerUtils.getProfile(p);
        char[] unlocks = cp.getAchievements();

        int slot = 1;
        for(AchievementsEnum achievement : AchievementsEnum.values()){
            if(achievement.getType() == achievementType) {
                int id = achievement.getId();
                boolean unlocked = unlocks[id] == 't';
                int coins = achievement.getCoinReward();
                int keys = achievement.getKeyReward();
                String name = constructName(achievement.getName(), unlocked);
                String[] lore = constuctLore(achievement.getDescription(), unlocked, coins, keys, achievement.getType());
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
        ItemUtils.createDisplay(inv, 46, Material.ARROW, 1, 0, "&cTerug");
        ItemUtils.createDisplay(inv, 54, Material.LEAVES, 1, 0, "&aSorteer op: &bParkour/Doolhoven");
        ItemUtils.createDisplay(inv, 53, Material.MINECART, 1, 0, "&aSorteer op: &bAttracties");
        ItemUtils.createDisplay(inv, 52, Material.SLIME_BLOCK, 1, 0, "&aSorteer op: &bStaff Punching");
        ItemUtils.createDisplay(inv, 51, Material.BOOK, 1, 0, "&aSorteer op: &bAlgemeen");
        ItemUtils.createDisplay(inv, 50, Material.BARRIER, 1, 0, "&cWis Filter");

        p.openInventory(inv);

        p.openInventory(inv);
    }

    public static void open(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, "Achievements");

        CorePlayer cp = PlayerUtils.getProfile(p);
        char[] unlocks = cp.getAchievements();

        int slot = 1;
        for(AchievementsEnum achievement : AchievementsEnum.values()){
            int id = achievement.getId();
            boolean unlocked = unlocks[id] == 't';
            int coins = achievement.getCoinReward();
            int keys = achievement.getKeyReward();
            String name = constructName(achievement.getName(), unlocked);
            String[] lore = constuctLore(achievement.getDescription(), unlocked, coins, keys, achievement.getType());
            Material m;
            if (unlocked) {
                m = Material.DIAMOND;
            } else {
                m = Material.COAL;
            }

            ItemUtils.createDisplay(inv, slot, m, 1, 0, name, lore);
            slot++;
        }
        ItemUtils.createDisplay(inv, 46, Material.ARROW, 1, 0, "&cTerug");
        ItemUtils.createDisplay(inv, 54, Material.LEAVES, 1, 0, "&aSorteer op: &bParkour/Doolhoven");
        ItemUtils.createDisplay(inv, 53, Material.MINECART, 1, 0, "&aSorteer op: &bAttracties");
        ItemUtils.createDisplay(inv, 52, Material.SLIME_BLOCK, 1, 0, "&aSorteer op: &bStaff Punching");
        ItemUtils.createDisplay(inv, 51, Material.BOOK, 1, 0, "&aSorteer op: &bAlgemeen");

        p.openInventory(inv);

        p.openInventory(inv);
    }

    private static String[] constuctLore(String lore, boolean unlocked, int coins, int keys, AchievementType type) {
        StringBuilder sb = new StringBuilder();

        String[] loreArray = lore.split("\n");
        for(String loreS : loreArray) {
            sb.append("&3").append(loreS).append("\n");
        }
        sb.append("&3Categorie: &a").append(type.getFriendlyName());
        sb.append("\n");
        sb.append("&3Rewards: ");
        sb.append("\n");
        sb.append("&6").append(coins).append(" coins").append("\n").append("&d").append(keys).append(" Mystery Key(s)");

        return sb.toString().split("\n");
    }

    private static String constructName(String name, boolean unlocked) {
        if(unlocked) {
            return "&a" + name;
        } else {
            return "&c" + name;
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(ChatColor.stripColor(event.getInventory().getName()).contains("Achievements")){
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            Material m = event.getCurrentItem().getType();
            switch (m){
                case ARROW:
                    MyHorizonMenu.openMyHorizon(p, p, false);
                    break;
                case BOOK:
                    open(p, AchievementType.GENERAL);
                    break;
                case BARRIER:
                    open(p);
                    break;
                case SLIME_BLOCK:
                    open(p, AchievementType.STAFFPUNCH);
                    break;
                case LEAVES:
                    open(p, AchievementType.MAZES_PARKOUR);
                    break;
                case MINECART:
                    open(p, AchievementType.RIDES);
                    break;
                default:
                    break;
            }
        }
    }

}
