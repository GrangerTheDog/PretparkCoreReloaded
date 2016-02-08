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

package nl.HorizonCraft.PretparkCore.Profiles;

import com.zaxxer.hikari.HikariDataSource;
import nl.HorizonCraft.PretparkCore.Bundles.Gadgets.GadgetsEnum;
import nl.HorizonCraft.PretparkCore.Bundles.Mazes.MazeLeaderboards;
import nl.HorizonCraft.PretparkCore.Bundles.Navigation.NavigationPoint;
import nl.HorizonCraft.PretparkCore.Bundles.Navigation.PointState;
import nl.HorizonCraft.PretparkCore.Bundles.Navigation.PointType;
import nl.HorizonCraft.PretparkCore.Bundles.Wardrobe.PiecesEnum;
import nl.HorizonCraft.PretparkCore.Main;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Objects.Voucher;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class has been created on 09/9/11/2015/2015 at 9:02 PM by Cooltimmetje.
 */
public class MysqlManager {

    private static HikariDataSource hikari = null;

    public static void setupHikari(){
        hikari = new HikariDataSource();
        hikari.setMaximumPoolSize(10);

        hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        hikari.addDataSourceProperty("serverName", Main.getPlugin().getConfig().getString("serverName"));
        hikari.addDataSourceProperty("port", Main.getPlugin().getConfig().getString("port"));
        hikari.addDataSourceProperty("databaseName", Main.getPlugin().getConfig().getString("databaseName"));
        hikari.addDataSourceProperty("user", Main.getPlugin().getConfig().getString("user"));
        hikari.addDataSourceProperty("password", Main.getPlugin().getConfig().getString("password"));
    }

    public static void loadProfile(Player p){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String uuid = p.getUniqueId().toString();
        String load = "SELECT * FROM playerdata WHERE uuid = '" + uuid + "';";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(load);
            rs = ps.executeQuery();

            if(rs.next()){
                setData(rs, p);
            } else {
                createProfile(p);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void amountUnique(){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String load = "SELECT COUNT(*) FROM playerdata;";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(load);
            rs = ps.executeQuery();

            if(rs.next()){
                Variables.uniquePlayers = rs.getInt(1);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void createProfile(Player p) {
        Main.getPlugin().getLogger().info("Creating profile for: " + p.getName());
        Connection c = null;
        PreparedStatement ps = null;
        String uuid = p.getUniqueId().toString();
        String create = "INSERT INTO playerdata VALUES(null,?,?,0,?,?,0,?,0,?,?,0,?,?,?,0,0,0,0,0,0,0,0)";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(create);

            ps.setString(1, uuid);
            ps.setString(2, p.getName());
            ps.setInt(3, Variables.COIN_TIME);
            ps.setString(4, StringUtils.repeat("f", 100));
            ps.setString(5, StringUtils.repeat("f", 100));
            ps.setInt(6, Variables.CHEST_TIME);
            ps.setString(7, StringUtils.repeat("f", 100));
            ps.setInt(8, Variables.EXPERIENCE_TIME);
            ps.setString(9, StringUtils.repeat("f", 100));
            ps.setString(10, StringUtils.repeat("f", 100));

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        loadProfile(p);

        Variables.uniquePlayers++;
    }

    private static void setData(ResultSet rs, Player p) {
        CorePlayer cp = PlayerUtils.getProfile(p);
        try {
            cp.setAchievements(rs.getString("achievements").toCharArray());
            cp.setProgressiveAchievements(rs.getString("progressive_achievements").toCharArray());
            cp.setCoins(rs.getInt("coins"));
            cp.setCoinTime(rs.getInt("coin_time"));
            cp.setPieces(rs.getString("wardrobe").toCharArray());
            cp.setKeys(rs.getInt("mkeys"));
            cp.setGadgets(rs.getString("gadgets").toCharArray());
            cp.setBoxes(rs.getInt("boxes"));
            cp.setBoxTime(rs.getInt("box_time"));
            cp.setPets(rs.getString("pets").toCharArray());
            cp.setExperience(rs.getInt("exp"));
            cp.setExperienceTime(rs.getInt("exp_time"));
            cp.setId(rs.getInt("id"));
            cp.setDust(rs.getInt("dust"));
            cp.setLast_daily_claim(rs.getLong("last_daily_claim"));
            cp.setCurrent_daily_streak(rs.getInt("current_daily_streak"));
            cp.setCoinDelivery(rs.getInt("del_coins"));
            cp.setExpDelivery(rs.getInt("del_exp"));
            cp.setBoxDelivery(rs.getInt("del_boxes"));
            cp.setKeyDelivery(rs.getInt("del_keys"));
            cp.setDustDelivery(rs.getInt("del_dust"));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void saveData(Player p){
        Connection c = null;
        PreparedStatement ps = null;
        String uuid = p.getUniqueId().toString();
        String updateData = "UPDATE playerdata SET name=?,coins=?,coin_time=?,achievements=?,mkeys=?,gadgets=?,boxes=?,box_time=?,pets=?,exp=?,exp_time=?," +
                "wardrobe=?,progressive_achievements=?,dust=?,last_daily_claim=?,current_daily_streak=?,del_coins=?,del_exp=?,del_boxes=?,del_keys=?,del_dust=? WHERE uuid=?";
        CorePlayer cp = PlayerUtils.getProfile(p);

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(updateData);

            ps.setString(1, p.getName());
            ps.setInt(2, cp.getCoins());
            ps.setInt(3, cp.getCoinTime());
            ps.setString(4, new String(cp.getAchievements()));
            ps.setInt(5, cp.getKeys());
            ps.setString(6, new String(cp.getGadgets()));
            ps.setInt(7, cp.getBoxes());
            ps.setInt(8, cp.getBoxTime());
            ps.setString(9, new String(cp.getPets()));
            ps.setInt(10, cp.getExperience());
            ps.setInt(11, cp.getExperienceTime());
            ps.setString(12, new String(cp.getPieces()));
            ps.setString(13, new String(cp.getProgressiveAchievements()));
            ps.setInt(14, cp.getDust());
            ps.setLong(15, cp.getLast_daily_claim());
            ps.setInt(16, cp.getCurrent_daily_streak());
            ps.setInt(17, cp.getCoinDelivery());
            ps.setInt(18, cp.getExpDelivery());
            ps.setInt(19, cp.getBoxDelivery());
            ps.setInt(20, cp.getKeyDelivery());
            ps.setInt(21, cp.getDustDelivery());
            ps.setString(22, uuid);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void loadPrefs(Player p){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String uuid = p.getUniqueId().toString();
        String load = "SELECT * FROM playerprefs WHERE uuid = '" + uuid + "';";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(load);
            rs = ps.executeQuery();

            if(rs.next()){
                setPrefs(rs, p);
            } else {
                createPrefs(p);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void createPrefs(Player p){
        Main.getPlugin().getLogger().info("Creating preferences for: " + p.getName());
        Connection c = null;
        PreparedStatement ps = null;
        String uuid = p.getUniqueId().toString();
        String create = "INSERT INTO playerprefs VALUES(?,?,0,?,?,?,?,?)";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(create);

            ps.setString(1, uuid);
            ps.setString(2, p.getName());
            ps.setString(3, "NOTHING");
            ps.setString(4, "NOTHING");
            ps.setString(5, "NOTHING");
            ps.setString(6, "NOTHING");
            ps.setString(7, "NOTHING");

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void setPrefs(ResultSet rs, Player p){
        CorePlayer cp = PlayerUtils.getProfile(p);
        try{
            if(rs.getInt("speed") == 1){
                cp.setSpeed(true);
            } else {
                cp.setSpeed(false);
            }

            if(rs.getString("head").equals("NOTHING")){
                cp.setHead(null);
            } else {
                cp.setHead(PiecesEnum.valueOf(rs.getString("head")));
            }
            if(rs.getString("chest").equals("NOTHING")){
                cp.setChest(null);
            } else {
                cp.setChest(PiecesEnum.valueOf(rs.getString("chest")));
            }
            if(rs.getString("legs").equals("NOTHING")){
                cp.setLegs(null);
            } else {
                cp.setLegs(PiecesEnum.valueOf(rs.getString("legs")));
            }
            if(rs.getString("boots").equals("NOTHING")){
                cp.setBoots(null);
            } else {
                cp.setBoots(PiecesEnum.valueOf(rs.getString("boots")));
            }
            if(rs.getString("gadget").equals("NOTHING")){
                cp.setGadget(null);
            } else {
                cp.setGadget(GadgetsEnum.valueOf(rs.getString("gadget")));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void savePrefs(Player p){
        Connection c = null;
        PreparedStatement ps = null;
        String uuid = p.getUniqueId().toString();
        String updateData = "UPDATE playerprefs SET name=?,speed=?,head=?,chest=?,legs=?,boots=?,gadget=? WHERE uuid=?";
        CorePlayer cp = PlayerUtils.getProfile(p);

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(updateData);

            ps.setString(1, p.getName());

            if (cp.hasSpeed()) {
                ps.setInt(2, 1);
            } else {
                ps.setInt(2, 0);
            }

            if(cp.getHead() == null){
                ps.setString(3, "NOTHING");
            } else {
                ps.setString(3, cp.getHead().toString());
            }
            if(cp.getChest() == null){
                ps.setString(4, "NOTHING");
            } else {
                ps.setString(4, cp.getChest().toString());
            }
            if(cp.getLegs() == null){
                ps.setString(5, "NOTHING");
            } else {
                ps.setString(5, cp.getLegs().toString());
            }
            if(cp.getBoots() == null){
                ps.setString(6, "NOTHING");
            } else {
                ps.setString(6, cp.getBoots().toString());
            }
            if(cp.getGadget() == null){
                ps.setString(7, "NOTHING");
            } else {
                ps.setString(7, cp.getGadget().toString());
            }

            ps.setString(8, uuid);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void getWarps(){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String load = "SELECT * FROM warps;";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(load);
            rs = ps.executeQuery();

            setWarp(rs);

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void setWarp(ResultSet rs) {
        try {
            while(rs.next()){
                String[] locList = rs.getString("location").split(",");
                Location loc = new Location(Bukkit.getWorld(Variables.WORLD_NAME), Double.parseDouble(locList[0]), Double.parseDouble(locList[1]), Double.parseDouble(locList[2]));
                loc.setYaw(Float.parseFloat(locList[3]));
                loc.setPitch(Float.parseFloat(locList[4]));
                new NavigationPoint(rs.getInt("id"), rs.getString("name"), loc, PointType.valueOf(rs.getString("point_type").toUpperCase()), PointState.valueOf(rs.getString("point_state").toUpperCase()));
//                Main.sendDebug(MiscUtils.color("&7&o[Server: " + "&7&oRide created: " + ride.getId() + "," + ride.getName() + "," + ride.getLocation() + "," + ride.getRideState() + "]"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveWarp(NavigationPoint point) {
        Connection c = null;
        PreparedStatement ps = null;
        String updateData = "UPDATE warps SET point_state=? WHERE id=?";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(updateData);

            ps.setString(1, point.getPointState().toString());
            ps.setInt(2, point.getId());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void addWarp(String name, String location, PointType pt){
        Main.getPlugin().getLogger().info("Creating new warp: " + name);
        Connection c = null;
        PreparedStatement ps = null;
        String create = "INSERT INTO warps VALUES(null,?,?,?,?)";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(create);

            ps.setString(1, name);
            ps.setString(2, location);
            ps.setString(3, PointState.CLOSED.toString());
            ps.setString(4, pt.toString());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deleteVoucher(Voucher voucher) {
        Connection c = null;
        PreparedStatement ps = null;
        String updateData = "DELETE FROM vouchers WHERE code=?";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(updateData);

            ps.setString(1, voucher.getCode());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void updateVoucher(Voucher voucher) {
        Connection c = null;
        PreparedStatement ps = null;
        String updateData = "INSERT INTO vouchers VALUES(?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE uses_left=?,used_by=?";
        String uses = voucher.getUses();

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(updateData);

            ps.setString(1, voucher.getCode());
            ps.setInt(2, voucher.getCoins());
            ps.setInt(3, voucher.getExp());
            ps.setInt(4, voucher.getBoxes());
            ps.setInt(5, voucher.getKeys());
            ps.setInt(6, voucher.getUses_left());
            ps.setString(7, "0");

            ps.setInt(8, voucher.getUses_left());
            ps.setString(9, uses);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void getVouchers(){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String load = "SELECT * FROM vouchers;";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(load);
            rs = ps.executeQuery();

            setVouchers(rs);

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void setVouchers(ResultSet rs) {
        try {
            while(rs.next()){
                Voucher voucher = new Voucher(rs.getString("code"),rs.getInt("coins"),rs.getInt("exp"),rs.getInt("box"),rs.getInt("mkeys"),rs.getInt("uses_left"));
//                voucher.addUsers(rs.getString("used_by"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void revokeAchievement(int id){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String load = "SELECT * FROM playerdata;";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(load);
            rs = ps.executeQuery();

            while(rs.next()) {
                if (Bukkit.getPlayer(rs.getString("name")) == null) {
                    revokeAchievementInDatabase(rs, id);
                } else {
                    revokeAchievementInCache(Bukkit.getPlayer(rs.getString("name")), id);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void revokeAchievementInDatabase(ResultSet rs, int id) {
        Connection c = null;
        PreparedStatement ps = null;
        String updateData = "UPDATE playerdata SET achievements=? WHERE uuid=?";

        try {
            String uuid = rs.getString("uuid");
            char[] achievements = rs.getString("achievements").toCharArray();
            c = hikari.getConnection();
            ps = c.prepareStatement(updateData);
            achievements[id] = 'f';

            ps.setString(1, new String(achievements));
            ps.setString(2, uuid);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void revokeAchievementInCache(Player p, int id) {
        CorePlayer cp = PlayerUtils.getProfile(p);
        cp.revokeAchievement(id);
    }

    public static void loadRecords(Player p){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String uuid = p.getUniqueId().toString();
        String load = "SELECT * FROM player_records WHERE uuid = '" + uuid + "';";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(load);
            rs = ps.executeQuery();

            if(rs.next()){
                setRecords(rs, p);
            } else {
                createRecords(p);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void createRecords(Player p){
        Main.getPlugin().getLogger().info("Creating records for: " + p.getName());
        Connection c = null;
        PreparedStatement ps = null;
        String uuid = p.getUniqueId().toString();
        String create = "INSERT INTO player_records VALUES(?,?,0,0,0)";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(create);

            ps.setString(1, uuid);
            ps.setString(2, p.getName());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void setRecords(ResultSet rs, Player p){
        CorePlayer cp = PlayerUtils.getProfile(p);
        try{

            cp.setMaze_1_record(rs.getInt("maze_1"));
            cp.setMaze_2_record(rs.getInt("maze_2"));
            cp.setDaily_streak_record(rs.getInt("daily_streak_record"));

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void saveRecords(Player p){
        Connection c = null;
        PreparedStatement ps = null;
        String uuid = p.getUniqueId().toString();
        String updateData = "UPDATE player_records SET name=?,maze_1=?,maze_2=?,daily_streak_record=? WHERE uuid=?";
        CorePlayer cp = PlayerUtils.getProfile(p);

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(updateData);

            ps.setString(1, p.getName());
            ps.setInt(2, cp.getMaze_1_record());
            ps.setInt(3, cp.getMaze_2_record());
            ps.setInt(4, cp.getDaily_streak_record());
            ps.setString(5, uuid);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void loadLeadMaze1(){
        Main.getPlugin().getLogger().info("Loading maze 1");
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String load = "SELECT * FROM player_records ORDER BY maze_1 ASC;";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(load);
            rs = ps.executeQuery();

            int i = 0;
            while(rs.next()) {
                if(rs.getInt("maze_1") != 0){
                    if(i > 4){
                        break;
                    }

                    MazeLeaderboards.maze1Name[i] = rs.getString("name");
                    MazeLeaderboards.maze1Time[i] = rs.getInt("maze_1");
                    i++;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void loadLeadMaze2(){
        Main.getPlugin().getLogger().info("Loading maze 2");
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String load = "SELECT * FROM player_records ORDER BY maze_2 ASC;";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(load);
            rs = ps.executeQuery();

            int i = 0;
            while(rs.next()) {
                if(rs.getInt("maze_2") != 0){
                    if(i > 4){
                        break;
                    }

                    MazeLeaderboards.maze2Name[i] = rs.getString("name");
                    MazeLeaderboards.maze2Time[i] = rs.getInt("maze_2");
                    i++;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createPowerupLocation(String location){
        Connection c = null;
        PreparedStatement ps = null;
        String create = "INSERT INTO powerup_locations VALUES(null,?)";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(create);

            ps.setString(1, location);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void loadPowerupLocations(){
        Main.getPlugin().getLogger().info("Loading PowerUp locations...");
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String load = "SELECT * FROM powerup_locations;";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(load);
            rs = ps.executeQuery();

            int i = 0;
            while(rs.next()) {
                Variables.powerupLocations.put(rs.getInt("id"), MiscUtils.stringToLocation(rs.getString("location")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void removePowerupLocation(int id){
        Connection c = null;
        PreparedStatement ps = null;
        String updateData = "DELETE FROM powerup_locations WHERE id=?";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(updateData);

            ps.setInt(1, id);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void loadForDelivery(Player p, String target, int[] currencies){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String load = "SELECT * FROM playerdata WHERE name = '" + target + "';";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(load);
            rs = ps.executeQuery();

            if(rs.next()){
                uploadDelivery(p,rs,currencies);
            } else {
                ChatUtils.sendFaslePlayer(p, "SpecialDelivery", target);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void uploadDelivery(Player p, ResultSet rs, int[] currencies) {
        Connection c = null;
        PreparedStatement ps = null;
        String updateData = "UPDATE playerdata SET del_coins=?,del_exp=?,del_boxes=?,del_keys=?,del_dust=? WHERE uuid=?";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(updateData);

            ps.setInt(1, rs.getInt("del_coins") + currencies[0]);
            ps.setInt(2, rs.getInt("del_exp") + currencies[1]);
            ps.setInt(3, rs.getInt("del_boxes") + currencies[2]);
            ps.setInt(4, rs.getInt("del_keys") + currencies[3]);
            ps.setInt(5, rs.getInt("del_dust") + currencies[4]);
            ps.setString(6, rs.getString("uuid"));

            ps.execute();
            ChatUtils.sendMsgTag(p, "SpecialDelivery", ChatUtils.success + "Deze speciale bezorging ligt nu op het postkantoor, en kan worden opgehaald!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
