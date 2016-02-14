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

package nl.HorizonCraft.PretparkCore.Bundles.Ranks;

import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Cooltimmetje on 2/13/2016 at 3:10 PM.
 */
public enum RanksEnum {

    BEZOEKER    (0, false, "bezoeker",   "Bezoeker",            '7', ChatColor.GRAY         ),
    VIP         (1, true,  "vip",        "VIP",                 'a', ChatColor.GREEN        ),
    VIP_LIFE    (1, false, "vip",        "VIP",                 'a', ChatColor.GREEN        ),
    MEDIA       (2, false, "media",      "Media",               'd', ChatColor.LIGHT_PURPLE ), //CUSTOM PREFIXES
    MEDEWERKER  (3, false, "medewerker", "Medewerker",          '3', ChatColor.DARK_AQUA    ),
    BOUWER      (4, false, "bouwer",     "Bouwer",              '2', ChatColor.DARK_GREEN   ),
    TD          (5, false, "td",         "Technische Dienst",   'b', ChatColor.AQUA         ),
    JR_DEV      (6, false, "jrdev",      "Junior Developer",    '6', ChatColor.GOLD         ),
    MANAGER     (7, false, "manager",    "Manager",             'e', ChatColor.YELLOW       ), //CUSTOM PREFIXES
    DEVELOPER   (8, false, "developer",  "Developer",           '6', ChatColor.GOLD         ),
    DIRECTEUR   (8, false, "directeur",  "Directeur",           'c', ChatColor.RED          );

    private int power;
    private boolean canExpire;
    private String gmName;
    private String friendlyName;
    private char color;
    private ChatColor chatColor;

    RanksEnum(int i, boolean b, String s, String s1, char c, ChatColor cc){
        this.power = i;
        this.canExpire = b;
        this.gmName = s;
        this.friendlyName = s1;
        this.color = c;
        this.chatColor = cc;
    }

    public int getPower(){
        return power;
    }

    public boolean doesExpire(){
        return canExpire;
    }

    public String getGmName() {
        return gmName;
    }

    public char getColor() {
        return color;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public static boolean hasPermission(CorePlayer cp, RanksEnum requiredRank){
        return (cp.getRank() == DIRECTEUR || cp.getRank() == DEVELOPER) || cp.getRank().getPower() >= requiredRank.getPower();
    }

    public static boolean hasPermission(Player p, RanksEnum requiredRank){
        CorePlayer cp = PlayerUtils.getProfile(p);
        return (cp.getRank() == DIRECTEUR || cp.getRank() == DEVELOPER) || cp.getRank().getPower() >= requiredRank.getPower();
    }
}
