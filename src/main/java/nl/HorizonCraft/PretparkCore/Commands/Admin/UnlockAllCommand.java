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

package nl.HorizonCraft.PretparkCore.Commands.Admin;

import nl.HorizonCraft.PretparkCore.Bundles.Gadgets.GadgetsEnum;
import nl.HorizonCraft.PretparkCore.Bundles.Pets.PetType;
import nl.HorizonCraft.PretparkCore.Bundles.Wardrobe.PiecesEnum;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * This class has been created on 10/27/2015 at 15:57 by Cooltimmetje.
 */
public class UnlockAllCommand implements CommandExecutor {

    private HashMap<Player, Player> confirmMap = new HashMap<>();

    @Override
        public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (cmd.getLabel().equalsIgnoreCase("unlockall")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.getName().equals("Jordy010NL") || p.getName().equals("Cooltimmetje")) {
                    if (args.length >= 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            if (confirmMap.containsKey(p) && confirmMap.get(p) == target) {
                                ChatUtils.sendMsgTag(p, "UnlockAll", "Alles unlocken voor: &c" + target.getDisplayName() + "&a...");

                                CorePlayer cp = PlayerUtils.getProfile(target);

                                for (GadgetsEnum gadget : GadgetsEnum.values()) {
                                    cp.unlockGadget(gadget, target, false, false, false);
                                }

                                for (PetType pet : PetType.values()) {
                                    cp.unlockPet(pet, target, false, false, false);
                                }

                                for (PiecesEnum piece : PiecesEnum.values()){
                                    cp.unlockClothing(piece, target, false, false, false);
                                }

                                ChatUtils.sendMsgTag(target, "UnlockAll", p.getDisplayName() + " &aheeft alles voor je geunlockt!");
                                MiscUtils.shootFirework(target.getLocation(), Variables.WORLD_NAME, true);
                                confirmMap.remove(p);
                            } else {
                                ChatUtils.sendMsgTag(p, "UnlockAll", "Weet je het zeker? Run de command nog eens om te bevestigen.");
                                confirmMap.put(p, target);
                            }
                        } else {
                            ChatUtils.sendFaslePlayer(p, "UnlockAll", args[0]);
                        }
                    } else {
                        ChatUtils.sendArugmentsError(p, "UnlockAll", "/unlockall <player>");
                    }
                } else if (p.getName().equals("xBrandy")) {
                    ChatUtils.sendMsgTag(p, "AntiBrandy", "Nee Brandy... Nee.");
                } else {
                    ChatUtils.sendNoPremTag(p, "UnlockAll");
                }
            }
        }
        return false;
    }

}
