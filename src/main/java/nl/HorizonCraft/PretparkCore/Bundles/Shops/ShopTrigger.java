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

package nl.HorizonCraft.PretparkCore.Bundles.Shops;

import nl.HorizonCraft.PretparkCore.Bundles.Gadgets.GadgetsShop;
import nl.HorizonCraft.PretparkCore.Bundles.Pets.PetShop;
import nl.HorizonCraft.PretparkCore.Bundles.Wardrobe.WardrobeShop;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * Created by Cooltimmetje on 12/8/2015 at 4:17 PM.
 */
public class ShopTrigger implements Listener {

    @EventHandler
    public void onShopNpcClick(PlayerInteractEntityEvent event){
        Entity clicked = event.getRightClicked();
        Player p = event.getPlayer();

        if(clicked.getType() == EntityType.PLAYER){
            switch (clicked.getUniqueId().toString()){
                case "855293e0-bade-2989-a29d-0a20d3ebe374": //GADGET SHOP
                    GadgetsShop.open(p);
                    break;
                case "57a33322-4d9a-2725-a158-eca0738370b0": //WARDROBE SHOP
                    WardrobeShop.open(p);
                    break;
                case "e200cf10-5aee-2cea-9c6c-cf3ddb155852": //PET SHOP
                    PetShop.open(p);
                    break;
                case "4ac1403a-4111-2c61-f240ba5ef55f": // FIREWORK SHOP
                    //TODO Moet nog classes shit maken hier voor !
                    break;
                default:
                    break;
            }
        }
    }

}
