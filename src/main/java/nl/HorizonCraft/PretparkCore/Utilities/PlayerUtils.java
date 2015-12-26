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

package nl.HorizonCraft.PretparkCore.Utilities;

import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementsEnum;
import nl.HorizonCraft.PretparkCore.Bundles.Wardrobe.PiecesEnum;
import nl.HorizonCraft.PretparkCore.Bundles.Wardrobe.SuitType;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

/**
 * This class has been created on 09/9/11/2015/2015 at 9:11 PM by Cooltimmetje.
 */
public class PlayerUtils {


    public static CorePlayer getProfile(Player p){
        return Variables.profile.get(p.getName());
    }

    public static void createProfile(Player p){
        Variables.profile.put(p.getName(), new CorePlayer(p));
    }

    public static void configPlayer(Player p, boolean forceInv) {
        CorePlayer cp = getProfile(p);

        if(p.hasPermission("pc.bypassgm")){
            p.setGameMode(GameMode.CREATIVE);
        } else {
            p.setGameMode(GameMode.SURVIVAL);
        }

        if(!p.hasPermission("pc.bypassgm") || forceInv){
            p.getInventory().clear();

            ItemStack is = ItemUtils.createItemstack(Material.SKULL_ITEM, 1, SkullType.PLAYER.ordinal(), "&e&lMy&3&lHorizon " + Variables.RIGHT_CLICK, "&7Open je profiel hier, hier kun ",
                    "&7je alles vinden over jouw account.");
            SkullMeta sm = (SkullMeta) is.getItemMeta();
            sm.setOwner(p.getName());
            is.setItemMeta(sm);
            ItemUtils.createDisplay(p, is, 1);

            ItemUtils.createDisplay(p, 2, Material.MINECART, 1, 0, "&aAttracties " + Variables.RIGHT_CLICK, "&7Bekijk alle attracties en hun status.");
            ItemUtils.createDisplay(p, 9, Material.CHEST, 1, 0, "&aSwag Menu " + Variables.RIGHT_CLICK, "&7Wil je wat swag? Kijk hier!");

            if(p.isOp()) {
                ItemUtils.createDisplay(p, 7, Material.FLINT, 1, 0, "&aAdmin Menu " + Variables.RIGHT_CLICK, "&7Beheer de server, aleen voor OP's!");
            }
        }

        setPiece(cp.getHead(),p);
        setPiece(cp.getLegs(),p);
        setPiece(cp.getBoots(),p);
        setPiece(cp.getChest(),p);

        if (cp.hasSpeed()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 0, false, false));
        } else {
            if(p.hasPotionEffect(PotionEffectType.SPEED)){
                p.removePotionEffect(PotionEffectType.SPEED);
            }
        }

        cp.calculateExp(p, true);

        switch (p.getName()) {
//            case "xBrandy":
//                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.KOALA_SLAP);
//                break;
            case "78wesley":
                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.PEDOBEAR_SLAP);
                break;
            case "BekertjeZuivel":
                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.MELK_SLAP);
                break;
            case "Cooltimmetje":
                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.COOL_SLAP);
                break;
            case "SVENBEER":
                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.SVEN_SLAP);
                break;
            case "roobein123":
                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.ROO_SLAP);
            default:
                break;
        }
    }

    public static void setPiece(PiecesEnum piece, Player p){
        if(piece != null){
            CorePlayer cp = PlayerUtils.getProfile(p);
            String name = MiscUtils.color("&" + piece.getWeight().getColor() + piece.getSuit().getName() + " " + piece.getSuitType().getName());
            ItemStack is = new ItemStack(piece.getMaterial(), 1, (byte)0);


            if(piece.getSuitType() == SuitType.HELMET){
                SkullMeta sm = (SkullMeta) is.getItemMeta();
                sm.setOwner(piece.getSkullUUID());
                String[] loreA = getLore(piece);
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
                String[] loreA = getLore(piece);
                ArrayList<String> lore = new ArrayList<>();
                for(String loreS : loreA){
                    lore.add(MiscUtils.color(loreS));
                }
                lam.setLore(lore);
                lam.setDisplayName(name);
                is.setItemMeta(lam);
            }

            switch (piece.getSuitType()){
                case HELMET:
                    cp.setHead(piece);
                    p.getInventory().setHelmet(is);
                    break;
                case CHESTPLATE:
                    cp.setChest(piece);
                    p.getInventory().setChestplate(is);
                    break;
                case LEGGINGS:
                    cp.setLegs(piece);
                    p.getInventory().setLeggings(is);
                    break;
                case BOOTS:
                    cp.setBoots(piece);
                    p.getInventory().setBoots(is);
                    break;
            }
        }
    }

    private static String[] getLore(PiecesEnum piece){
        StringBuilder sb = new StringBuilder();

        sb.append("&3").append(piece.getSuit().getLore()).append("\n \n");
        sb.append("&bFull Suit Ability:\n&3").append(piece.getSuit().getAbility());

        return sb.toString().trim().split("\n");
    }
}
