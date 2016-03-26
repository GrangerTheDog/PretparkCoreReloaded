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

package nl.HorizonCraft.PretparkCore.HeadDatabase;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Base64;
import java.util.UUID;

/**
 * Created by Cooltimmetje on 2/29/2016 at 3:33 PM.
 */
public class HeadMenu implements Listener,CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

    public static void search() throws SQLException{
        Inventory inv = Bukkit.createInventory(null, 54, "Heads");
//        ResultSet set = getSet();

//        while (set.next()) {
//            ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
//            SkullMeta im = (SkullMeta) is.getItemMeta();
//
//            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
//            byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", set.getString("url")).getBytes());
//            profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
//            Field profileField = null;
//            try {
//                profileField = im.getClass().getDeclaredField("profile");
//                profileField.setAccessible(true);
//                profileField.set(im, profile);
//            } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
//                e1.printStackTrace();
//            }
//
//            is.setItemMeta(im);
//            ItemUtils.createDisplay(is,inv,1);
//        }

        ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta im = (SkullMeta) is.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/515dcb2da02cf734829e1e273e3025617d8071516f953251b52545da8d3e8db8").getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = im.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(im, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }

        is.setItemMeta(im);
        ItemUtils.createDisplay(is,inv,1);

    }

//    private static ResultSet getSet(){
//
//    }
}
