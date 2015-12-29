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

package nl.HorizonCraft.PretparkCore.Bundles.Firework;

import nl.HorizonCraft.PretparkCore.Bundles.Gadgets.GadgetsEnum;
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
 * This class has been created on 12/12/27/2015/2015 at 5:14 PM by 78wesley.
 */

public class FIreworkMenu implements Listener {
    public static void open(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, "Vuurwerk");
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
        ItemUtils.createDisplay(inv, 54, Material.ENDER_PEARL, 1, 0, "&aNaar de shop.", "&3Meer vuurwerkjes kopen? Klik hier!");
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


        ItemUtils.createDisplay(p, 8, m, 1, data, name, lore);
    }

}
