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
