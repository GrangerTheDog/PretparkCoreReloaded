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

package nl.HorizonCraft.PretparkCore.Bundles.Gadgets;

import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementsEnum;
import nl.HorizonCraft.PretparkCore.Bundles.Gadgets.GadgetsEnum;
import nl.HorizonCraft.PretparkCore.Menus.SwagMenu.MainSwag;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * This class has been created on 10/13/2015 at 1:50 PM by Cooltimmetje.
 */
public class GadgetsShop implements Listener{

   public static void open(Player p){
       Inventory inv = Bukkit.createInventory(null, 54, "Gadget Shop");
       CorePlayer cp = PlayerUtils.getProfile(p);
       char[] unlocks = cp.getGadgets();

       int slot = 1;
       for(GadgetsEnum gadget : GadgetsEnum.values()){
           int id = gadget.getId();
           boolean unlocked = unlocks[id] == 't';
           int cooldown = gadget.getCooldown();
           int cost = gadget.getCost();
           String name = "&" + gadget.getWeight().getColor() + gadget.getName();
           String[] lore = constuctLore(gadget.getLore(), unlocked, cooldown, cost);
           Material m = gadget.getMaterial();
           int data = gadget.getDmg();
           if(unlocked){
               m = Material.INK_SACK;
               data = 10;
           }

           ItemUtils.createDisplay(inv, slot, m, 1, data, name, lore);
           slot++;
       }

       p.openInventory(inv);
   }

    private static String[] constuctLore(String lore, boolean unlocked, int cooldown, int cost) {
        StringBuilder sb = new StringBuilder();

        sb.append("&3COOLDOWN: &b").append(MiscUtils.formatTime(cooldown));
        sb.append("\n \n");
        String[] loreArray = lore.split("\n");
        for(String loreS : loreArray) {
            sb.append("&3").append(loreS).append("\n");
        }
        sb.append(" \n");
        if(!unlocked){
            sb.append("&cLOCKED").append("\n");
            sb.append("&aPrijs: &6" + cost + " coins");
        } else {
            sb.append("&aUNLOCKED &8\u00BB &9Open je Swag Menu om te gebruiken!").append("\n")
                    .append("&8&mPrijs: " + cost + " coins");
        }

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
        Inventory inv = event.getInventory();
        if(ChatColor.stripColor(inv.getName()).contains("Gadget Shop")){
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            CorePlayer cp = PlayerUtils.getProfile(p);
            Material m = event.getCurrentItem().getType();
            switch (m){
                default:
                    GadgetsEnum gadget = GadgetsEnum.getFromMaterial(m);
                    if(cp.getCoins() >= gadget.getCost()){
                        cp.removeCoins(p, gadget.getCost(), "Gadget unlock: " + gadget.getName(), false);
                        cp.unlockGadget(gadget, p, true, true, true);
                        cp.awardAchievement(p, AchievementsEnum.UNLOCK_GADGET);
                        open(p);
                    } else {
                        ChatUtils.sendMsgTag(p, "GadgetShop", ChatUtils.error + "Je hebt niet genoeg coins!");
                    }
                case INK_SACK:
                    break;
                case ENDER_PEARL:
                    p.teleport(new Location(Bukkit.getWorld(Variables.WORLD_NAME), 68, 66, -262));
                    break;
                case ARROW:
                    MainSwag.open(p);
                    break;
            }
        }
    }

}
