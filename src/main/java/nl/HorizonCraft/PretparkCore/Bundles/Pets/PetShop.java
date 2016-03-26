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

package nl.HorizonCraft.PretparkCore.Bundles.Pets;

import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementsEnum;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cooltimmetje on 12/12/2015 at 3:49 PM.
 */
public class PetShop implements Listener {

    private static HashMap<Integer,PetType> slots = new HashMap<>();

    public static void open(Player p){
        if(!slots.isEmpty()){
            slots.clear();
        }
        Inventory inv = Bukkit.createInventory(null, 54, "Pets Shop");
        CorePlayer cp = PlayerUtils.getProfile(p);
        char[] unlocks = cp.getPets();

        int slot = 1;
        for(PetType pet : PetType.values()){
            int id = pet.getId();
            boolean unlocked = unlocks[id] == 't';
            int cost = pet.getCost();
            String name = "&" + pet.getWeight().getColor() + pet.getName();
            String[] lore = constructLore(pet, unlocked);
            Material m = pet.getMaterial();
            int data = 0;

            if(pet.getCost() == 0){
                m = Material.ENDER_CHEST;
            }

            if(unlocked){
                m = Material.INK_SACK;
                data = 10;
            }


            if(m == Material.SKULL_ITEM){
                ItemStack is = new ItemStack(m);
                SkullMeta sm = (SkullMeta) is.getItemMeta();
                sm.setOwner(pet.getSkullName());
                String[] loreA = constructLore(pet, unlocked);
                ArrayList<String> loreAL = new ArrayList<>();
                for(String loreS : loreA){
                    loreAL.add(MiscUtils.color(loreS));
                }
                sm.setLore(loreAL);
                sm.setDisplayName(MiscUtils.color(name));
                is.setItemMeta(sm);
                is.setDurability((short) SkullType.PLAYER.ordinal());

                ItemUtils.createDisplay(is, inv, slot);
            } else {
                ItemUtils.createDisplay(inv, slot, m, 1, data, name, lore);
            }
            slots.put(slot - 1, pet);
            slot++;
        }

        p.openInventory(inv);
    }

    private static String[] constructLore(PetType pet, boolean unlocked) {
        StringBuilder sb = new StringBuilder();

        String[] loreArray = pet.getLore().split("\n");
        for(String loreS : loreArray) {
            sb.append("&3").append(loreS).append("\n");
        }
        sb.append(" \n");
        if(!unlocked){
            sb.append("&cLOCKED").append("\n");
            if(pet.getCost() != 0) {
                sb.append("&aPrijs: &6" + pet.getCost() + " coins");
            } else {
                sb.append("&6&lMYSTERY BOX EXCLUSIVE");
            }
        } else {
            sb.append("&aUNLOCKED &8\u00BB &9Open je Swag Menu om te gebruiken!").append("\n");
            if(pet.getCost() != 0) {
                sb.append("&8&mPrijs: " + pet.getCost() + " coins");
            } else {
                sb.append("&8&mMYSTERY BOX EXCLUSIVE");
            }
        }

        return sb.toString().split("\n");
    }


    @EventHandler
    public void onClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        if(ChatColor.stripColor(inv.getName()).contains("Pets Shop")) {
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            CorePlayer cp = PlayerUtils.getProfile(p);
            if (event.getCurrentItem() != null) {
                Material m = event.getCurrentItem().getType();
                switch (m) {
                    default:
                        PetType pet = slots.get(event.getSlot());

                        if (cp.getCoins() >= pet.getCost()) {
                            cp.removeCoins(p, pet.getCost(), "Pet unlock: " + pet.getName(), false);
                            cp.unlockPet(pet, p, true, true, true);
                            cp.awardAchievement(p, AchievementsEnum.UNLOCK_PET);
                            open(p);
                        } else {
                            ChatUtils.sendMsgTag(p, "PetsShop", ChatUtils.error + "Je hebt niet genoeg coins!");
                        }
                        break;
                    case ENDER_CHEST:
                        ChatUtils.sendMsgTag(p, "PetsShop", ChatUtils.error + "Unlock dit in de Mystery Box!");
                        break;
                }
            }
        }
    }


}
