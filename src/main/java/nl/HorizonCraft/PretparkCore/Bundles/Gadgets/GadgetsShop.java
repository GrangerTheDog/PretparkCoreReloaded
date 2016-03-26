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

package nl.HorizonCraft.PretparkCore.Bundles.Gadgets;

import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementsEnum;
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
           } else {
               if(cost == 0){
                   m = Material.ENDER_CHEST;
                   data = 0;
               }
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
            if(cost != 0) {
                sb.append("&aPrijs: &6" + cost + " coins");
            } else {
                sb.append("&6&lMYSTERYBOX EXCLUSIVE!");
            }
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
