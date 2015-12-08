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

import com.mysql.jdbc.DatabaseMetaDataUsingInfoSchema;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Objects.Voucher;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

/**
 * Created by Cooltimmetje on 12/8/2015 at 6:18 PM.
 */
public class CreateVoucherCommand implements CommandExecutor {

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd = new Random();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getLabel().equals("createvoucher")){
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.getName().equals("Jordy010NL") || p.getName().equals("Cooltimmetje")) {

                    if(args[0].equals("-list")){
                        for(Voucher voucher : Variables.vouchers){
                            ChatUtils.sendMsg(p, "&aCode: " + voucher.getCode());
                            ChatUtils.sendMsg(p, "&6Coins: " + voucher.getCoins());
                            ChatUtils.sendMsg(p, "&9EXP: " + voucher.getExp());
                            ChatUtils.sendMsg(p, "&3Boxes: " + voucher.getBoxes());
                            ChatUtils.sendMsg(p, "&dKeys: " + voucher.getKeys());
                            ChatUtils.sendMsg(p, "&aAantal: " + voucher.getUses_left());
                            p.sendMessage(" ");
                        }
                    } else {
                        if (args.length >= 5) {
                            if (MiscUtils.isInt(args[0]) && MiscUtils.isInt(args[1]) && MiscUtils.isInt(args[2]) && MiscUtils.isInt(args[3]) && MiscUtils.isInt(args[4])) {
                                String code = randomString(6);
                                if (MiscUtils.getVoucher(code) == null) {
                                    Voucher voucher = new Voucher(code, Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                                    ChatUtils.sendMsg(p, "&8&l--- &a&lVOUCHER AANGEMAAKT &8&l---");
                                    ChatUtils.sendMsg(p, "&aCode: " + voucher.getCode());
                                    ChatUtils.sendMsg(p, "&6Coins: " + voucher.getCoins());
                                    ChatUtils.sendMsg(p, "&9EXP: " + voucher.getExp());
                                    ChatUtils.sendMsg(p, "&3Boxes: " + voucher.getBoxes());
                                    ChatUtils.sendMsg(p, "&dKeys: " + voucher.getKeys());
                                    ChatUtils.sendMsg(p, "&aAantal: " + voucher.getUses_left());
                                } else {
                                    ChatUtils.sendMsgTag(p, "Vouchers", ChatUtils.error + "Er ging iets mis, probeer het nog eens.");
                                }
                            } else {
                                ChatUtils.sendMsgTag(p, "Vouchers", ChatUtils.error + "Gebruik: /createvoucher <coins> <exp> <boxes> <keys> <uses>");
                            }
                        } else {
                            ChatUtils.sendMsgTag(p, "Vouchers", ChatUtils.error + "Gebruik: /createvoucher <coins> <exp> <boxes> <keys> <uses>");
                        }
                    }
                } else {
                    ChatUtils.sendNoPremTag(p, "Vouchers");
                }
            }
        }
        return true;
    }

    private static String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }


}
