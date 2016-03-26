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

import nl.HorizonCraft.PretparkCore.Menus.SwagMenu.MainSwag;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
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
 * This class has been created on 09/18/2015 at 6:06 PM by Cooltimmetje.
 */
public class GadgetsMenu implements Listener {

    public static void open(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, "Gadgets");
        CorePlayer cp = PlayerUtils.getProfile(p);
        char[] unlocks = cp.getGadgets();

        int slot = 1;
        for(GadgetsEnum gadget : GadgetsEnum.values()){
            int id = gadget.getId();
            boolean unlocked = unlocks[id] == 't';
            int cooldown = gadget.getCooldown();
            String name = "&" + gadget.getWeight().getColor() + gadget.getName();
            String[] lore = constuctLore(gadget.getLore(), unlocked, cooldown);
            Material m = gadget.getMaterial();
            int data = gadget.getDmg();
            if(!unlocked){
                m = Material.INK_SACK;
                data = 8;
            }

            ItemUtils.createDisplay(inv, slot, m, 1, data, name, lore);
            slot++;
        }
        ItemUtils.createDisplay(inv, 54, Material.ENDER_PEARL, 1, 0, "&aNaar de shop.", "&3Meer gadgets kopen? Klik hier!");
        ItemUtils.createDisplay(inv, 46, Material.ARROW, 1, 0, "&cTerug");

        p.openInventory(inv);
    }

    private static String[] constuctLore(String lore, boolean unlocked, int cooldown) {
        StringBuilder sb = new StringBuilder();

        sb.append("&3COOLDOWN: &b").append(MiscUtils.formatTime(cooldown));
        sb.append("\n \n");
        String[] loreArray = lore.split("\n");
        for(String loreS : loreArray) {
            sb.append("&3").append(loreS).append("\n");
        }
        if(!unlocked){
            sb.append(" \n");
            sb.append("&cLOCKED").append("\n");
            sb.append("&aKoop dit item in de shop!");
        }

        return sb.toString().split("\n");
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        if(ChatColor.stripColor(inv.getName()).contains("Gadgets")){
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            CorePlayer cp = PlayerUtils.getProfile(p);
            Material m = event.getCurrentItem().getType();
            switch (m){
                default:
                    setGadget(GadgetsEnum.getFromMaterial(m), p, cp);
                    break;
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

    private void setGadget(GadgetsEnum gadget, Player p, CorePlayer cp){
        int id = gadget.getId();
        int cooldown = gadget.getCooldown();
        String name = "&" + gadget.getWeight().getColor() + gadget.getName();
        String[] lore = constuctLore(gadget.getLore(), true, cooldown);
        Material m = gadget.getMaterial();
        int data = gadget.getDmg();
        cp.setGadget(gadget);

        ItemUtils.createDisplay(p, 8, m, 1, data, name, lore);
    }

}
