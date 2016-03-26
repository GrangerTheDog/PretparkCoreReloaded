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

import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Cooltimmetje on 2/13/2016 at 3:10 PM.
 */
public enum RanksEnum {

    BEZOEKER    (0, false, "bezoeker",   "Bezoeker",            '7', ChatColor.GRAY        , "Bezoeker",                    "&7"                ),
    VIP         (1, true,  "vip",        "VIP",                 'a', ChatColor.GREEN       , "VIP",                         "&a[VIP] "          ),
    VIP_LIFE    (1, false, "vip",        "VIP",                 'a', ChatColor.GREEN       , "VIP",                         "&a[VIP] "          ),
    MEDIA       (2, false, "media",      "Media",               'd', ChatColor.LIGHT_PURPLE, "Media",                       "&d[Media] "        ), //CUSTOM PREFIXES
    MEDEWERKER  (3, false, "medewerker", "Medewerker",          '3', ChatColor.DARK_AQUA   , "Medewerker-Staff",            "&3[Medewerker] "   ),
    BOUWER      (4, false, "bouwer",     "Bouwer",              '2', ChatColor.DARK_GREEN  , "Bouwer-Staff",                "&2[Bouwer] "       ),
    TD          (5, false, "td",         "Technische Dienst",   'b', ChatColor.AQUA        , "Technische Dienst-Staff",     "&b[TD] "           ),
    JR_DEV      (6, false, "jrdev",      "Junior Developer",    '6', ChatColor.GOLD        , "Junior Developer-Staff",      "&6[Jr. Dev] "      ),
    MANAGER     (7, false, "manager",    "Manager",             'e', ChatColor.YELLOW      , "Hoofd Staff",                 "&e[Manager] "      ), //CUSTOM PREFIXES
    DEVELOPER   (8, false, "developer",  "Developer",           '6', ChatColor.GOLD        , "Developer-Hoofd Staff",       "&6[Developer] "    ),
    DIRECTEUR   (8, false, "directeur",  "Directeur",           'c', ChatColor.RED         , "Directeur-Hoofd Staff",       "&c[Directeur] "    );

    private int power;
    private boolean canExpire;
    private String gmName;
    private String friendlyName;
    private char color;
    private ChatColor chatColor;
    private String discordRoles;
    private String nametag;

    RanksEnum(int i, boolean b, String s, String s1, char c, ChatColor cc, String s2, String s3){
        this.power = i;
        this.canExpire = b;
        this.gmName = s;
        this.friendlyName = s1;
        this.color = c;
        this.chatColor = cc;
        this.discordRoles = s2;
        this.nametag = s3;
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

    public String getDiscordRoles() {
        return discordRoles;
    }

    public String getNametag() {
        return nametag;
    }

    public void setNametag(String nametag) {
        this.nametag = nametag;
    }
}
