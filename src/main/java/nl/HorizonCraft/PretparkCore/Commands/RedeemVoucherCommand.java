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

package nl.HorizonCraft.PretparkCore.Commands;

import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Objects.Voucher;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Cooltimmetje on 12/8/2015 at 6:18 PM.
 */
public class RedeemVoucherCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getLabel().equals("redeem")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(args.length >= 1){
                    ChatUtils.sendMsgTag(p, "Voucher", "Voucher inwisselen...");

                    Voucher voucher = MiscUtils.getVoucher(args[0].toUpperCase());
                    if(voucher != null){
                        voucher.redeem(p, PlayerUtils.getProfile(p));
                    } else {
                        ChatUtils.sendMsgTag(p, "Voucher", ChatUtils.error + "Dit is een ongeldige voucher!");
                    }
                } else {
                    ChatUtils.sendMsgTag(p, "Voucher", ChatUtils.error + "Vul een code in!");
                }
            }
        }
        return true;
    }

}
