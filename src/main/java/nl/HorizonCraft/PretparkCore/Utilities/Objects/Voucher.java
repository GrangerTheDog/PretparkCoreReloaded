/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2016 HorizonCraft
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

package nl.HorizonCraft.PretparkCore.Utilities.Objects;

import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Profiles.MysqlManager;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Cooltimmetje on 12/8/2015 at 6:04 PM.
 */
public class Voucher {

    private String code;

    private int coins;
    private int exp;
    private int boxes;
    private int keys;

    private int uses_left;
    private ArrayList<Integer> used_by = new ArrayList<>();

    public Voucher(String code, int coins, int exp, int boxes, int keys, int uses){
        this.code = code;
        this.coins = coins;
        this.exp = exp;
        this.boxes = boxes;
        this.keys = keys;
        this.uses_left = uses;

        Variables.vouchers.add(this);
    }

    public String getCode() {
        return code;
    }

    public int getCoins() {
        return coins;
    }

    public int getExp() {
        return exp;
    }

    public int getBoxes() {
        return boxes;
    }

    public int getKeys() {
        return keys;
    }

    public int getUses_left() {
        return uses_left;
    }

    public boolean isUsed(int id){
        return used_by.contains(id);
    }

    public String getUses(){
        if(hasUses()) {
            StringBuilder sb = new StringBuilder();
            for(int i : used_by){
                sb.append(i).append(",");
            }
            return sb.toString();
        } else {
            return "0";
        }
    }

    public boolean hasUses(){
        return used_by.isEmpty();
    }

    public void redeem(Player p, CorePlayer cp){
        if(uses_left > 0) {
            if (!used_by.contains(cp.getId())) {
                used_by.add(cp.getId());

                if (getCoins() != 0) {
                    cp.addCoins(p, getCoins(), "Voucher: " + getCode(), false, true);
                }
                if (getExp() != 0) {
                    cp.addExp(p, getExp(), "Voucher: " + getCode(), false, true);
                }
                if (getBoxes() != 0) {
                    cp.addBoxes(p, getBoxes(), "Voucher: " + getCode(), false, true);
                }
                if (getKeys() != 0) {
                    cp.addKeys(p, getKeys(), "Voucher: " + getCode(), false, true);
                }

                this.uses_left = getUses_left() - 1;
            } else {
                ChatUtils.sendMsgTag(p, "Voucher", ChatUtils.error + "Je kunt elke voucher maar 1x gebruiken!");
            }
        } else {
            ChatUtils.sendMsgTag(p, "Voucher", ChatUtils.error + "Deze voucher is al het maximaal aantal keren gebruikt!");
        }
    }

    public void update(){
        if(uses_left == 0){
            MysqlManager.deleteVoucher(this);
            Variables.vouchers.remove(this);
        } else {
            MysqlManager.updateVoucher(this);
        }
    }

    public void addUsers(String s){
        String[] sArray = s.split(",");

        for(String s1 : sArray) {
            used_by.add(Integer.parseInt(s1));
        }
    }

}
