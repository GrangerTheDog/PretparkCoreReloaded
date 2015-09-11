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

package nl.HorizonCraft.PretparkCore.Database;

import com.zaxxer.hikari.HikariDataSource;
import nl.HorizonCraft.PretparkCore.Main;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
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

    private static void createProfile(Player p) {
        Main.getPlugin().getLogger().info("Creating profile for: " + p.getName());
        Connection c = null;
        PreparedStatement ps = null;
        String uuid = p.getUniqueId().toString();
        String create = "INSERT INTO playerdata VALUES(null,?,?,0,?)";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(create);

            ps.setString(1, uuid);
            ps.setString(2, p.getName());
            ps.setInt(3, Variables.COIN_TIME);

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

        //TODO: Make unique players system.
    }

    private static void setData(ResultSet rs, Player p) {
        try {
            Variables.coins.put(p.getName(), rs.getInt("coins"));
            Variables.coinTime.put(p.getName(), rs.getInt("coin_time"));
            Variables.playerId.put(p.getName(), rs.getInt("id"));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void saveData(Player p, boolean leave){
        Connection c = null;
        PreparedStatement ps = null;
        String uuid = p.getUniqueId().toString();
        String updateData = "UPDATE playerdata SET name=?,coins=?,coin_time=? WHERE uuid=?";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(updateData);

            ps.setString(1, p.getName());
            ps.setInt(2, PlayerUtils.getCoins(p));
            ps.setInt(3, PlayerUtils.getCoinTime(p));
            ps.setString(4, uuid);

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

        if (leave) {
            Variables.coins.remove(p.getName());
            Variables.playerId.remove(p.getName());
            Variables.coinTime.remove(p.getName());
        }
    }

}
