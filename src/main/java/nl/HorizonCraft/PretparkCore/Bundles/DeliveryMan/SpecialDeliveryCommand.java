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

package nl.HorizonCraft.PretparkCore.Bundles.DeliveryMan;

import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Profiles.MysqlManager;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Cooltimmetje on 2/8/2016 at 4:49 PM.
 */
public class SpecialDeliveryCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getLabel().equalsIgnoreCase("specialdelivery") || cmd.getLabel().equalsIgnoreCase("sd")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.getName().equals("Jordy010NL") || p.getName().equals("Cooltimmetje")) {
                    if(args.length >= 7){
                        int[] currencies = {0,0,0,0,0,0};
                        for(int i=1;i < 6;i++){
                            if(MiscUtils.isInt(args[i])){
                                currencies[i-1] = Integer.parseInt(args[i]);
                            } else {
                                ChatUtils.sendFalseInt(p, "SpecialDelivery", args[i]);
                                return false;
                            }
                        }
                        Player target = Bukkit.getPlayer(args[0]);
                        if(target == null){ //Write to database
                            MysqlManager.loadForDelivery(p, args[0], currencies);
                        } else { //Add to cached profile
                            CorePlayer cp = PlayerUtils.getProfile(target);
                            cp.setCoinDelivery(cp.getCoinDelivery() + currencies[0]);
                            cp.setExpDelivery(cp.getExpDelivery() + currencies[1]);
                            cp.setBoxDelivery(cp.getBoxDelivery() + currencies[2]);
                            cp.setKeyDelivery(cp.getKeyDelivery() + currencies[3]);
                            cp.setDustDelivery(cp.getDustDelivery() + currencies[4]);
                            cp.setKarmaDelivery(cp.getKarmaDelivery() + currencies[5]);

                            ChatUtils.sendMsgTag(p, "SpecialDelivery", ChatUtils.success + "Deze speciale bezorging ligt nu op het postkantoor, en kan worden opgehaald!");
                            DeliveryNotifier.notify(target);
                        }
                    } else {
                        ChatUtils.sendArugmentsError(p, "SpecialDelivery", "/specialdelivery <player> <coins> <exp> <boxes> <keys> <dust> <karma>");
                    }
                } else {
                    ChatUtils.sendNoPremTag(p,"SpecialDelivery");
                }
            } else {
                sender.sendMessage("Only players can execute this command!");
            }
        }
        return false;
    }
}
