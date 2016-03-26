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

package nl.HorizonCraft.PretparkCore.Bundles.Shops;

import nl.HorizonCraft.PretparkCore.Bundles.DeliveryMan.DeliveryMenu;
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
                case "c4217df2-5e51-2d49-8d98-cebe41f84ce2":
                    DeliveryMenu.open(p);
                    break;
                default:
                    break;
            }
        }
    }

}
