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

package nl.HorizonCraft.PretparkCore.Bundles.Wardrobe;

import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
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
public class WardrobeMenu implements Listener{

    private static HashMap<Integer,PiecesEnum> slots = new HashMap<>();

    public static void open(Player p){
        if(!slots.isEmpty()){
            slots.clear();
        }
        Inventory inv = Bukkit.createInventory(null, 54, "Kledingkast");
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

            if(!unlocked){
                is.setType(Material.INK_SACK);
                is.setDurability((short)8);

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
            sb.append("&aKoop dit item in de shop!");
        }

        return sb.toString().trim().split("\n");
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        if(ChatColor.stripColor(inv.getName()).contains("Kledingkast")){
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            CorePlayer cp = PlayerUtils.getProfile(p);
            Material m = event.getCurrentItem().getType();
            switch (m){
                default:
                    PiecesEnum piece = slots.get(event.getSlot());
                    switch (piece.getSuitType()){
                        case HELMET:
                            cp.setHead(piece);
                            break;
                        case CHESTPLATE:
                            cp.setChest(piece);
                            break;
                        case LEGGINGS:
                            cp.setLegs(piece);
                            break;
                        case BOOTS:
                            cp.setBoots(piece);
                            break;
                    }

                    PlayerUtils.configPlayer(p, false);
                    break;
            }
        }
    }

}
