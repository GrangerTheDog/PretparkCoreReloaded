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

package nl.HorizonCraft.PretparkCore.Bundles.Pets;

import nl.HorizonCraft.PretparkCore.Bundles.MysteryBox.Weight;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * This class has been created on 10/25/2015 at 13:25 by Cooltimmetje.
 */
public class PetMenu implements Listener {

    public static void open(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, "Pets");
        CorePlayer cp = PlayerUtils.getProfile(p);

        int slot = 1;
        for (PetType pet : PetType.values()) {
            String name, lore, skullName;
            int data, price;
            Weight weight;
            String[] loreArray;
            char color;
            boolean isMysteryBoxExclusive, unlocked;
            Material m;

            lore = pet.getLore();
            unlocked = cp.hasPet(pet);
            isMysteryBoxExclusive = pet.isMysteryBoxExclusive();
            price = pet.getCost();

            StringBuilder loreBuilder = new StringBuilder();
            for (String string : lore.split("\n")) {
                loreBuilder.append("&3").append(string).append("\n");
            }
            loreBuilder.append(" \n");

            if (!unlocked) {
                loreBuilder.append("&cLOCKED").append("\n").append("&aKoop dit item in de shop");
            }

            loreArray = loreBuilder.toString().trim().split("\n");

            weight = pet.getWeight();
            color = weight.getColor();
            name = "&" + color + pet.getName();

            m = pet.getMaterial();
            skullName = pet.getSkullName();
            data = pet.getData();

            if (!unlocked) {
                m = Material.INK_SACK;
                data = 8;
            }

            if (m == Material.SKULL_ITEM) {
                ItemStack is = ItemUtils.createItemstack(m, 1, data, name, loreArray);
                SkullMeta sm = (SkullMeta) is.getItemMeta();
                sm.setOwner(skullName);
                is.setItemMeta(sm);
                ItemUtils.createDisplay(is, inv, slot);
            } else {
                ItemUtils.createDisplay(inv, slot, m, 1, data, name, loreArray);
            }

            slot = slot + 1;
        }
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getName().equals("Pets")) {
            event.setCancelled(true);
        }
    }


}
