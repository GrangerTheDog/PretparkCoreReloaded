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

package nl.HorizonCraft.PretparkCore.Menus.MyHorizon;

import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementMenu;
import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementTypes;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class has been created on 09/13/2015 at 12:39 PM by Cooltimmetje.
 */
public class MyHorizonMenu implements Listener,CommandExecutor{

    public static void openMyHorizon(Player p, Player pTarget, boolean admin){
        CorePlayer cp = PlayerUtils.getProfile(pTarget);

        Inventory inv = Bukkit.createInventory(null, 45, MiscUtils.color("MyHorizon &8\u00BB " + pTarget.getName()));
        ItemStack is;

        if(cp.getRankExpire() == 0) {
            is = ItemUtils.createItemstack(Material.SKULL_ITEM, 1, SkullType.PLAYER.ordinal(), "&e&lMy&3&lHorizon &8\u00BB " + pTarget.getDisplayName(), "Rank: &" + cp.getRank().getColor() + cp.getRank().getFriendlyName());
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date(cp.getRankExpire());
            String friendlyDate = sdf.format(date);
            is = ItemUtils.createItemstack(Material.SKULL_ITEM, 1, SkullType.PLAYER.ordinal(), "&e&lMy&3&lHorizon &8\u00BB " + pTarget.getDisplayName(), "Rank: &" + cp.getRank().getColor() + cp.getRank().getFriendlyName(), "Verloopt op: &b" + friendlyDate);
        }
        SkullMeta im = (SkullMeta) is.getItemMeta();
        im.setOwner(pTarget.getName());
        is.setItemMeta(im);
        ItemUtils.createDisplay(is, inv, 13);

        ItemUtils.createDisplay(inv, 14, Material.DIAMOND, 1, 0, "&aAchievements", "&7Klik om te openen.");

        if(!admin) { //TODO: MAKE COMPATIBLE WITH ADMIN
            ItemUtils.createDisplay(inv, 15, Material.REDSTONE_COMPARATOR, 1, 0, "&aInstellingen", "&7Verander je instellingen.");
        }

        if(!admin) {
            ItemUtils.createDisplay(inv, 22, Material.GOLD_NUGGET, 1, 0, "&6" + MiscUtils.intFormat(cp.getCoins(), " ") + " coins", "&7Verdien coins door online te zijn, deze", "&7kun je uitgeven aan allerlei spulletjes op de server.");
        } else {
            ItemUtils.createDisplay(inv, 22, Material.GOLD_NUGGET, 1, 0, "&6" + MiscUtils.intFormat(cp.getCoins(), " ") + " coins", "&7+30 coins in: " + cp.getCoinTime());
        }

        ItemUtils.createDisplay(inv, 23, Material.ENDER_CHEST, 1, 0, "&3Mystery Boxes: &c" + cp.getBoxes());
        ItemUtils.createDisplay(inv, 24, Material.TRIPWIRE_HOOK, 1, 0, "&dMystery Keys: &c" + cp.getKeys());

        if(admin){
            ItemUtils.createDisplay(inv, 26, Material.BARRIER, 1, 0, "&cKick");
        }

        String[] expInfo = cp.calculateExpString(p).split(",");
        int needed = Integer.parseInt(expInfo[0]);
        int exp = Integer.parseInt(expInfo[1]);

        ItemUtils.createDisplay(inv, 31, Material.EXP_BOTTLE, 1, 0, "&9Experience:", "&3Level: &a" + cp.getLevel(), "&3Totaal EXP: &a" + MiscUtils.intFormat(cp.getExperience(), " "), "&3Nodig tot volgend level: &a" + MiscUtils.intFormat((needed - exp), " "), "&3Progress: " + progress(exp, needed));
        ItemUtils.createDisplay(inv, 32, Material.EMERALD, 1, 0, "&2Karma: " + cp.getKarma());
        ItemUtils.createDisplay(inv, 33, Material.SUGAR, 1, 0, "&bMystery Dust: &c" + cp.getDust());

        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(ChatColor.stripColor(event.getInventory().getName()).contains("MyHorizon")){
            event.setCancelled(true);
            Player p = Bukkit.getPlayer(ChatColor.stripColor(event.getInventory().getName().split(" ")[2]));
            Player target = (Player) event.getWhoClicked();
            Material m = event.getCurrentItem().getType();
            switch (m){
                case REDSTONE_COMPARATOR:
                    PreferencesMenu.openPrefs(p);
                    break;
                case DIAMOND:
                    AchievementMenu.open(p, target, AchievementTypes.NORMAL);
                    break;
                case BARRIER:
                    p.kickPlayer("Rekt");
                    target.closeInventory();
                    break;
                default:
                    break;
            }
        }
    }

    private static String progress(int exp, int needed) {
        double d = (double) exp / (double) needed;
        double d1 = d * 100;
        double d2 = Math.round(d1);
        int i = (int) d2;

        return "&8[&a" + i + "%&8]";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getLabel().equalsIgnoreCase("myhorizon")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                openMyHorizon(p,p,false);
            }
        }
        return false;
    }
}
