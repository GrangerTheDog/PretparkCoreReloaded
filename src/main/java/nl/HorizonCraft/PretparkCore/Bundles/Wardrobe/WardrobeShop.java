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

package nl.HorizonCraft.PretparkCore.Bundles.Wardrobe;

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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cooltimmetje on 12/9/2015 at 6:54 PM.
 */
public class WardrobeShop implements Listener{

    private static HashMap<Integer,PiecesEnum> slots = new HashMap<>();

    public static void open(Player p){
        if(!slots.isEmpty()){
            slots.clear();
        }
        Inventory inv = Bukkit.createInventory(null, 54, "Kleding Shop");
        HashMap<SuitsEnum,Integer> colums = new HashMap<>();
        CorePlayer cp = PlayerUtils.getProfile(p);
        char[] unlocks = cp.getPieces();

        int slot = 1;
        for(PiecesEnum piece : PiecesEnum.values()){
            boolean unlocked = unlocks[piece.getId()] == 't';
            if(!colums.containsKey(piece.getSuit())){
                colums.put(piece.getSuit(), slot);
                slot = slot + 1;
            }

            int slotNow = colums.get(piece.getSuit()) + (9 * piece.getSuitType().getMove());
            String name = MiscUtils.color("&" + piece.getWeight().getColor() + piece.getSuit().getName() + " " + piece.getSuitType().getName());
            ItemStack is = new ItemStack(piece.getMaterial(), 1, (byte)0);

            if(unlocked){
                is.setType(Material.INK_SACK);
                is.setDurability((short)10);

                ItemMeta sm = is.getItemMeta();
                String[] loreA = getLore(piece, unlocked);
                ArrayList<String> lore = new ArrayList<>();
                for(String loreS : loreA){
                    lore.add(MiscUtils.color(loreS));
                }
                sm.setLore(lore);
                sm.setDisplayName(name);
                is.setItemMeta(sm);

            } else if (piece.getCost() == 0 && !unlocked){
                is.setDurability((short) 0);
                is.setType(Material.ENDER_CHEST);

                ItemMeta sm = is.getItemMeta();
                String[] loreA = getLore(piece, unlocked);
                ArrayList<String> lore = new ArrayList<>();
                for(String loreS : loreA){
                    lore.add(MiscUtils.color(loreS));
                }
                sm.setLore(lore);
                sm.setDisplayName(name);
                is.setItemMeta(sm);

            } else {
                if(piece.getMaterial() == Material.SKULL_ITEM){
                    SkullMeta sm = (SkullMeta) is.getItemMeta();
                    sm.setOwner(piece.getSkullUUID());
                    String[] loreA = getLore(piece, unlocked);
                    ArrayList<String> lore = new ArrayList<>();
                    for(String loreS : loreA){
                        lore.add(MiscUtils.color(loreS));
                    }
                    sm.setLore(lore);
                    sm.setDisplayName(name);
                    is.setItemMeta(sm);
                    is.setDurability((short) SkullType.PLAYER.ordinal());
                } else {
                    LeatherArmorMeta lam = (LeatherArmorMeta) is.getItemMeta();
                    lam.setColor(piece.getColor());
                    String[] loreA = getLore(piece, unlocked);
                    ArrayList<String> lore = new ArrayList<>();
                    for(String loreS : loreA){
                        lore.add(MiscUtils.color(loreS));
                    }
                    lam.setLore(lore);
                    lam.setDisplayName(name);
                    is.setItemMeta(lam);
                }

                slots.put(slotNow - 1, piece);
            }

            ItemUtils.createDisplay(is, inv, slotNow);
        }

        p.openInventory(inv);
    }

    private static String[] getLore(PiecesEnum piece, boolean unlocked){
        StringBuilder sb = new StringBuilder();

        sb.append("&3").append(piece.getSuit().getLore()).append("\n \n");
        sb.append("&bFull Suit Ability:\n&3").append(piece.getSuit().getAbility());

        if(!unlocked) {
            sb.append("\n \n");
            sb.append("&cLOCKED").append("\n");
            if(piece.getCost() != 0) {
                sb.append("&aPrijs: &6").append(piece.getCost());
            } else {
                sb.append("&6&lMYSTERY BOX EXCLUSIVE");
            }
        } else {
            sb.append("\n \n&aUNLOCKED &8\u00BB &9Open je Swag Menu om te gebruiken!").append("\n").append("&8&mPrijs: ").append(piece.getCost()).append(" coins");
        }

        return sb.toString().trim().split("\n");
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        if(ChatColor.stripColor(inv.getName()).contains("Kleding Shop")) {
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            CorePlayer cp = PlayerUtils.getProfile(p);
            if (event.getCurrentItem() != null) {
                Material m = event.getCurrentItem().getType();
                switch (m) {
                    default:
                        PiecesEnum piece = slots.get(event.getSlot());

                        if (cp.getCoins() >= piece.getCost()) {
                            cp.removeCoins(p, piece.getCost(), "Kleding unlock: " + piece.getCost(), false);
                            cp.unlockClothing(piece, p, true, true, true);
                            cp.awardAchievement(p, AchievementsEnum.UNLOCK_CLOTHING);
                            open(p);
                        } else {
                            ChatUtils.sendMsgTag(p, "KledingShop", ChatUtils.error + "Je hebt niet genoeg coins!");
                        }
                        break;
                    case ENDER_CHEST:
                        ChatUtils.sendMsgTag(p, "KledingShop", ChatUtils.error + "Unlock dit in de Mystery Box!");
                        break;
                }
            }
        }
    }

}
