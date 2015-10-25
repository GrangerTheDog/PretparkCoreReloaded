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

import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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

        if (cp.hasSpeed()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 0, false, false));
        } else {
            if(p.hasPotionEffect(PotionEffectType.SPEED)){
                p.removePotionEffect(PotionEffectType.SPEED);
            }
        }

        cp.calculateExp(p);
    }
}
