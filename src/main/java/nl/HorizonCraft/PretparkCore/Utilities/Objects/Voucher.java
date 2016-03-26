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
