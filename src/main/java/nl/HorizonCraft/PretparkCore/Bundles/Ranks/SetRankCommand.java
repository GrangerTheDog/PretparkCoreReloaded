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

package nl.HorizonCraft.PretparkCore.Bundles.Ranks;

import mkremins.fanciful.FancyMessage;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Cooltimmetje on 2/13/2016 at 5:14 PM.
 */
public class SetRankCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getLabel().equalsIgnoreCase("setrank")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                CorePlayer cp = PlayerUtils.getProfile(p);
                if(RanksEnum.hasPermission(cp, RanksEnum.MANAGER)){
                    if(args.length >= 2){
                        Player target = Bukkit.getPlayer(args[0]);
                        if(target != null){
                            CorePlayer cpTarget = PlayerUtils.getProfile(target);
                            if((cpTarget.getRank().getPower() < cp.getRank().getPower()) || (cp.getRank() == RanksEnum.DIRECTEUR || cp.getRank() == RanksEnum.DEVELOPER)){
                                RanksEnum rank;
                                try {
                                    rank = RanksEnum.valueOf(args[1].toUpperCase());
                                } catch (IllegalArgumentException e){
                                    ChatUtils.sendMsgTag(p, "SetRank", ChatUtils.error + "Deze rang bestaat niet: " + args[1]);
                                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + p.getName() + " " + getRanksRaw(target.getName()));
                                    return false;
                                }
                                if(rank != null){
                                    if(!rank.doesExpire()){
                                        if(rank.getPower() < cp.getRank().getPower() || (cp.getRank() == RanksEnum.DIRECTEUR || cp.getRank() == RanksEnum.DEVELOPER)){
                                            RanksEnum rankOld = cpTarget.getRank();
                                            cpTarget.setRank(rank);
                                            for(Player pl : Bukkit.getOnlinePlayers()){
                                                if(pl != p && pl != target){
                                                    CorePlayer cpl = PlayerUtils.getProfile(pl);
                                                    if(RanksEnum.hasPermission(cpl, RanksEnum.MANAGER)){
                                                        ChatUtils.sendMsgTag(pl, "Rank", "&c" + p.getName() + " &aheeft de rang van &c" + target.getName() + " &averanderd van &" + rankOld.getColor() + rankOld.getFriendlyName() +
                                                                " &anaar &" + rank.getColor() + rank.getFriendlyName() + "&a.");
                                                    }
                                                }
                                            }

                                            ChatUtils.sendMsgTag(target, "Rank", "Je rang is veranderd van &" + rankOld.getColor() + rankOld.getFriendlyName() + " &a naar &" + rank.getColor() + rank.getFriendlyName() +
                                                    " &adoor &c" + p.getName() +"&a.");
                                            ChatUtils.sendMsgTag(p, "SetRank", "Je hebt de rang van &c" + target.getName() + " &averanderd van &" + rankOld.getColor() + rankOld.getFriendlyName() + " &anaar &" +
                                                    rank.getColor() + rank.getFriendlyName() + "&a.");

                                            cpTarget.updateRank(target);
                                        } else {
                                            ChatUtils.sendMsgTag(p, "SetRank", ChatUtils.error + "Je kunt alleen rangen toewijzen die lager zijn dan je eigen rank.");
                                        }
                                    } else {
                                        ChatUtils.sendMsgTag(p, "SetRank", ChatUtils.error + "Deze rang mag je niet toewijzen.");
                                    }
                                } else {
                                    ChatUtils.sendMsgTag(p, "SetRank", ChatUtils.error + "Deze rang bestaat niet: " + args[1]);
                                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + p.getName() + " " + getRanksRaw(target.getName()));
                                }
                            } else {
                                ChatUtils.sendMsgTag(p, "SetRank", ChatUtils.error + "Je kan alleen de rang van spelers, die een lagere rang dan jij hebben, veranderen.");
                            }
                        } else {
                            ChatUtils.sendFaslePlayer(p, "SetRank", args[0]);
                        }
                    } else if(args.length == 1) {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + p.getName() + " " + getRanksRaw(args[0]));
                    } else {
                        ChatUtils.sendArugmentsError(p, "SetRank", "/setrank <naam> <rang>");
                    }
                } else {
                    ChatUtils.sendNoPremTag(p, "SetRank");
                }
            } else {
                sender.sendMessage("Only players can execute this command.");
            }
        }
        return false;
    }

    private String getRanksRaw(String targetName){
        FancyMessage message = new FancyMessage("Geldige ranks:").color(ChatColor.GREEN);

        for(RanksEnum rank : RanksEnum.values()){
            message.then(" ").then(rank.getFriendlyName()).color(rank.getChatColor());
            if(rank.doesExpire()){
                message.style(ChatColor.STRIKETHROUGH).tooltip(MiscUtils.color("&7Kan niet worden toegewezen."));
            } else {
                message.tooltip("Klik op mij om te autocompleten!").suggest("/setrank " + targetName + " " + rank.toString());
            }

            message.then(" -").color(ChatColor.DARK_GRAY);
        }


        return message.toJSONString();
    }

}
