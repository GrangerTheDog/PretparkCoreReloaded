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

package nl.HorizonCraft.PretparkCore.Bundles.Navigation;

import nl.HorizonCraft.PretparkCore.Bundles.Mazes.MazeLeaderboards;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PointUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * Created by Cooltimmetje on 12/29/2015 at 12:02 PM.
 */
public class PointMenu implements Listener,CommandExecutor {

    public static void open(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, "Warp Menu");

        int slot = 1;
        for(NavigationPoint navPoint : Variables.navigationPoints){
            if(navPoint.getId() == 16){
                ItemUtils.createDisplay(inv, slot, Material.STAINED_CLAY, 1, navPoint.getPointState().getColorData(), "&a" + navPoint.getName().replace("Het", " ").replace("De ", " ").trim(),
                        "&bStatus: &" + navPoint.getPointState().getColorCode() + navPoint.getPointState().getFriendlyName(),
                        "&bSoort: &a" + navPoint.getPointType().getFriendlyName(),
                        "&bKlik om te teleporteren.", "&8ID: " + navPoint.getId(),
                        " ",
                        "&6&lLEADERBOARDS:",
                        "&a1. &b" + MazeLeaderboards.maze1Name[0] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze1Time[0]),
                        "&a2. &b" + MazeLeaderboards.maze1Name[1] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze1Time[1]),
                        "&a3. &b" + MazeLeaderboards.maze1Name[2] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze1Time[2]),
                        "&a4. &b" + MazeLeaderboards.maze1Name[3] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze1Time[3]),
                        "&a5. &b" + MazeLeaderboards.maze1Name[4] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze1Time[4]));
            } else if(navPoint.getId() == 26){
                ItemUtils.createDisplay(inv, slot, Material.STAINED_CLAY, 1, navPoint.getPointState().getColorData(), "&a" + navPoint.getName().replace("Het", " ").replace("De ", " ").trim(),
                        "&bStatus: &" + navPoint.getPointState().getColorCode() + navPoint.getPointState().getFriendlyName(),
                        "&bSoort: &a" + navPoint.getPointType().getFriendlyName(),
                        "&bKlik om te teleporteren.", "&8ID: " + navPoint.getId(),
                        " ",
                        "&6&lLEADERBOARDS:",
                        "&a1. &b" + MazeLeaderboards.maze2Name[0] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze2Time[0]),
                        "&a2. &b" + MazeLeaderboards.maze2Name[1] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze2Time[1]),
                        "&a3. &b" + MazeLeaderboards.maze2Name[2] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze2Time[2]),
                        "&a4. &b" + MazeLeaderboards.maze2Name[3] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze2Time[3]),
                        "&a5. &b" + MazeLeaderboards.maze2Name[4] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze2Time[4]));
            } else {
                ItemUtils.createDisplay(inv, slot, Material.STAINED_CLAY, 1, navPoint.getPointState().getColorData(), "&a" + navPoint.getName().replace("Het", " ").replace("De ", " ").trim(),
                        "&bStatus: &" + navPoint.getPointState().getColorCode() + navPoint.getPointState().getFriendlyName(),
                        "&bSoort: &a" + navPoint.getPointType().getFriendlyName(),
                        "&bKlik om te teleporteren.", "&8ID: " + navPoint.getId());
            }

            slot++;
        }

        ItemUtils.createDisplay(inv, 50, Material.GOLD_INGOT, 1, 0, "&aSorteer op: &bShops");
        ItemUtils.createDisplay(inv, 51, Material.ENDER_PEARL, 1, 0, "&aSorteer op: &bWarps");
        ItemUtils.createDisplay(inv, 52, Material.MINECART, 1, 0, "&aSorteer op: &bAttracties");
        ItemUtils.createDisplay(inv, 53, Material.LEAVES, 1, 0, "&aSorteer op: &bDoolhoven");
        ItemUtils.createDisplay(inv, 54, Material.FEATHER, 1, 0, "&aSorteer op: &bParkouren");

        p.openInventory(inv);
    }

    public static void open(Player p, PointType pt){
        Inventory inv = Bukkit.createInventory(null, 54, "Warp Menu");

        int slot = 1;
        for(NavigationPoint navPoint : Variables.navigationPoints){
            if(navPoint.getPointType() == pt) {
                if(navPoint.getId() == 16){
                    ItemUtils.createDisplay(inv, slot, Material.STAINED_CLAY, 1, navPoint.getPointState().getColorData(), "&a" + navPoint.getName().replace("Het", " ").replace("De ", " ").trim(),
                            "&bStatus: &" + navPoint.getPointState().getColorCode() + navPoint.getPointState().getFriendlyName(),
                            "&bSoort: &a" + navPoint.getPointType().getFriendlyName(),
                            "&bKlik om te teleporteren.", "&8ID: " + navPoint.getId(),
                            " ",
                            "&6&lLEADERBOARDS:",
                            "&a1. &b" + MazeLeaderboards.maze1Name[0] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze1Time[0]),
                            "&a2. &b" + MazeLeaderboards.maze1Name[1] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze1Time[1]),
                            "&a3. &b" + MazeLeaderboards.maze1Name[2] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze1Time[2]),
                            "&a4. &b" + MazeLeaderboards.maze1Name[3] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze1Time[3]),
                            "&a5. &b" + MazeLeaderboards.maze1Name[4] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze1Time[4]));
                } else if(navPoint.getId() == 26){
                    ItemUtils.createDisplay(inv, slot, Material.STAINED_CLAY, 1, navPoint.getPointState().getColorData(), "&a" + navPoint.getName().replace("Het", " ").replace("De ", " ").trim(),
                            "&bStatus: &" + navPoint.getPointState().getColorCode() + navPoint.getPointState().getFriendlyName(),
                            "&bSoort: &a" + navPoint.getPointType().getFriendlyName(),
                            "&bKlik om te teleporteren.", "&8ID: " + navPoint.getId(),
                            " ",
                            "&6&lLEADERBOARDS:",
                            "&a1. &b" + MazeLeaderboards.maze2Name[0] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze2Time[0]),
                            "&a2. &b" + MazeLeaderboards.maze2Name[1] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze2Time[1]),
                            "&a3. &b" + MazeLeaderboards.maze2Name[2] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze2Time[2]),
                            "&a4. &b" + MazeLeaderboards.maze2Name[3] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze2Time[3]),
                            "&a5. &b" + MazeLeaderboards.maze2Name[4] + " &4&l" + MiscUtils.formatTime(MazeLeaderboards.maze2Time[4]));
                } else {
                    ItemUtils.createDisplay(inv, slot, Material.STAINED_CLAY, 1, navPoint.getPointState().getColorData(), "&a" + navPoint.getName().replace("Het", " ").replace("De ", " ").trim(),
                            "&bStatus: &" + navPoint.getPointState().getColorCode() + navPoint.getPointState().getFriendlyName(),
                            "&bSoort: &a" + navPoint.getPointType().getFriendlyName(),
                            "&bKlik om te teleporteren.", "&8ID: " + navPoint.getId());
                }
                slot++;
            }
        }

        ItemUtils.createDisplay(inv, 49, Material.BARRIER, 1, 0, "&cWis filter.");
        ItemUtils.createDisplay(inv, 50, Material.GOLD_INGOT, 1, 0, "&aSorteer op: &bShops");
        ItemUtils.createDisplay(inv, 51, Material.ENDER_PEARL, 1, 0, "&aSorteer op: &bWarps");
        ItemUtils.createDisplay(inv, 52, Material.MINECART, 1, 0, "&aSorteer op: &bAttracties");
        ItemUtils.createDisplay(inv, 53, Material.LEAVES, 1, 0, "&aSorteer op: &bDoolhoven");
        ItemUtils.createDisplay(inv, 54, Material.FEATHER, 1, 0, "&aSorteer op: &bParkouren");

        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(ChatColor.stripColor(event.getInventory().getName()).contains("Warp Menu")){
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            Material m = event.getCurrentItem().getType();
            switch (m){
                case ENDER_PEARL:
                    open(p, PointType.WARP);
                    break;
                case GOLD_INGOT:
                    open(p, PointType.SHOP);
                    break;
                case MINECART:
                    open(p, PointType.RIDE);
                    break;
                case LEAVES:
                    open(p, PointType.MAZE);
                    break;
                case FEATHER:
                    open(p, PointType.PARKOUR);
                    break;
                case BARRIER:
                    open(p);
                    break;
                case STAINED_CLAY:
                    NavigationPoint navPoint = PointUtils.getById(Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(3)).replace("ID: ", " ").trim()));
                    if (navPoint != null) {
                        p.teleport(navPoint.getLocation());
                    }

                    p.closeInventory();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getLabel().equalsIgnoreCase("warpmenu")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                open(p);
            }
        }
        return false;
    }

}
