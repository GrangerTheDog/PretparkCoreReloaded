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

package nl.HorizonCraft.PretparkCore;

import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementCommand;
import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementMenu;
import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementsEnum;
import nl.HorizonCraft.PretparkCore.Bundles.Achievements.RevokeAchievementCommand;
import nl.HorizonCraft.PretparkCore.Bundles.Gadgets.GadgetTriggers;
import nl.HorizonCraft.PretparkCore.Bundles.Gadgets.GadgetsMenu;
import nl.HorizonCraft.PretparkCore.Bundles.Gadgets.GadgetsShop;
import nl.HorizonCraft.PretparkCore.Bundles.Mazes.MazeCommand;
import nl.HorizonCraft.PretparkCore.Bundles.Mazes.MazeLeaderboards;
import nl.HorizonCraft.PretparkCore.Bundles.MysteryBox.BoxCrafting;
import nl.HorizonCraft.PretparkCore.Bundles.MysteryBox.BoxMenu;
import nl.HorizonCraft.PretparkCore.Bundles.MysteryBox.BoxSetup;
import nl.HorizonCraft.PretparkCore.Bundles.MysteryBox.OpenBoxCommand;
import nl.HorizonCraft.PretparkCore.Bundles.Navigation.ChangePointStateCommand;
import nl.HorizonCraft.PretparkCore.Bundles.Navigation.CreatePointCommand;
import nl.HorizonCraft.PretparkCore.Bundles.Navigation.PointMenu;
import nl.HorizonCraft.PretparkCore.Bundles.Pets.PetMenu;
import nl.HorizonCraft.PretparkCore.Bundles.Pets.PetShop;
import nl.HorizonCraft.PretparkCore.Bundles.Ping.ServerPingListener;
import nl.HorizonCraft.PretparkCore.Bundles.Powerups.Commands.PowerupCommand;
import nl.HorizonCraft.PretparkCore.Bundles.Powerups.PowerupViewMenu;
import nl.HorizonCraft.PretparkCore.Bundles.Shops.ShopTrigger;
import nl.HorizonCraft.PretparkCore.Bundles.Shops.Test;
import nl.HorizonCraft.PretparkCore.Bundles.Wardrobe.WardrobeMenu;
import nl.HorizonCraft.PretparkCore.Bundles.Wardrobe.WardrobeShop;
import nl.HorizonCraft.PretparkCore.Commands.Admin.CreateVoucherCommand;
import nl.HorizonCraft.PretparkCore.Commands.Admin.RideAchievementCommand;
import nl.HorizonCraft.PretparkCore.Commands.Admin.UnlockAllCommand;
import nl.HorizonCraft.PretparkCore.Commands.Admin.UpdateLeaderboardsCommand;
import nl.HorizonCraft.PretparkCore.Commands.ClearChatCommand;
import nl.HorizonCraft.PretparkCore.Commands.FixGamemodeCommand;
import nl.HorizonCraft.PretparkCore.Commands.RedeemVoucherCommand;
import nl.HorizonCraft.PretparkCore.Commands.ResetInventoryCommand;
import nl.HorizonCraft.PretparkCore.Listeners.*;
import nl.HorizonCraft.PretparkCore.Managers.InventoryManager;
import nl.HorizonCraft.PretparkCore.Managers.SpawnManager;
import nl.HorizonCraft.PretparkCore.Menus.AdminMenu.MainAdmin;
import nl.HorizonCraft.PretparkCore.Menus.AdminMenu.PlayerAdmin;
import nl.HorizonCraft.PretparkCore.Menus.AdminMenu.TimeAdmin;
import nl.HorizonCraft.PretparkCore.Menus.MyHorizon.MyHorizonMenu;
import nl.HorizonCraft.PretparkCore.Menus.MyHorizon.PreferencesMenu;
import nl.HorizonCraft.PretparkCore.Menus.SwagMenu.MainSwag;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Profiles.MysqlManager;
import nl.HorizonCraft.PretparkCore.Timers.CurrencyGiver;
import nl.HorizonCraft.PretparkCore.Timers.DataSaver;
import nl.HorizonCraft.PretparkCore.Timers.LeaderboardUpdater;
import nl.HorizonCraft.PretparkCore.Utilities.*;
import nl.HorizonCraft.PretparkCore.Utilities.Packets.SpawnHologram;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class has been created on 9/7/2015 at 21:27 by Cooltimmetje.
 */
public class Main extends JavaPlugin {

    private long startTime;
    private static Plugin plugin;

    public void onEnable(){
        startTime = System.currentTimeMillis(); //Registers the plugin startTime to measure loading time afterwards...
        getLogger().info("Enabling plugin... Please wait.");
        sendDebug("&3Pretpark&6Core&9> &aStarting plugin load... &oPlease wait...");

        plugin = this; //Registering the plugin variable to allow other classes to access it.
        this.saveDefaultConfig(); //Saves the config to be used.

        getLogger().info("Starting pre-setup...."); //For everything that will cause issues if it gets done after registering stuff
        MysqlManager.setupHikari();

        getLogger().info("Registering Listeners..."); //Well, this registers the listeners.
        registerListeners(this
                , new WeatherChangeListener(), new JoinQuitListener(), new InventoryManager(), new MainAdmin()
                , new PlayerAdmin(), new TimeAdmin(), new MyHorizonMenu(), new PreferencesMenu(), new MainSwag()
                , new GadgetsMenu(), new GadgetTriggers(), new AchievementMenu(), new BoxCrafting()
                , new BoxMenu(), new ServerPingListener(), new ChatListener(), new PointMenu(), new PowerupViewMenu()
                , new HealthHungerListener(), new PetMenu(), new GamemodeListener(), new Test(), new ShopTrigger()
                , new GadgetsShop(), new WardrobeMenu(), new WardrobeShop(), new PetShop(), new MonsterEggBlockPlaceListener()
        );

        getLogger().info("Registering Commands..."); //Can you guess what this does? Yes! It registers the commands.
        registerCommand("fixgm", new FixGamemodeCommand());
        registerCommand("resetinv", new ResetInventoryCommand());
        registerCommand("rejoin", new ResetInventoryCommand());
        registerCommand("cc", new ClearChatCommand());
        registerCommand("maze", new MazeCommand());
        registerCommand("createwarp", new CreatePointCommand());
        registerCommand("warpstate", new ChangePointStateCommand());
        registerCommand("achievementride", new RideAchievementCommand());
        registerCommand("unlockall", new UnlockAllCommand());
        registerCommand("createvoucher", new CreateVoucherCommand());
        registerCommand("redeem", new RedeemVoucherCommand());
        registerCommand("awardachievement", new AchievementCommand());
        registerCommand("revokeachievement", new RevokeAchievementCommand());
        registerCommand("updateleaderboards", new UpdateLeaderboardsCommand());
        registerCommand("powerup", new PowerupCommand());
        registerCommand("openbox", new OpenBoxCommand());
//        registerCommand("coins", new CoinsCommand());
//        registerCommand("exp", new ExperienceCommand());
        //format: registerCommand("cmd", new ExecutorClass);

        getLogger().info("Starting setup"); //For stuff like, loading arraylists and databases.
        for(Player p : Bukkit.getOnlinePlayers()){
            PlayerUtils.createProfile(p);

            MysqlManager.loadProfile(p);
            MysqlManager.loadPrefs(p);
            MysqlManager.loadRecords(p);

            PlayerUtils.configPlayer(p, false);
        }
        MysqlManager.getWarps();
        MysqlManager.getVouchers();
        MysqlManager.amountUnique();
        MysqlManager.loadPowerupLocations();
        MazeLeaderboards.load(false);

        getLogger().info("Opening API hooks..."); //Checking if the API's that we need are running.
        hookApi("HolographicDisplays");

        getLogger().info("Starting Timers..."); //Well, starts timers. Duh...
        DataSaver.start(this);
        CurrencyGiver.start(this);
        LeaderboardUpdater.start(this);

        getLogger().info("Starting post-setup"); //For frontend stuff, like scoreboards.
        for(Player p : Bukkit.getOnlinePlayers()){
            ScoreboardUtils.constructScoreboard(p);
        }
        BoxSetup.setup();
        SpawnManager.setup();

        getLogger().info("Finishing up..."); //For stuff that needs to be done after everything.
        for(Player p : Bukkit.getOnlinePlayers()){
            CorePlayer cp = PlayerUtils.getProfile(p);
            cp.awardAchievement(p, AchievementsEnum.FIRST_TIME_JOIN);

            cp.awardProgressive();

            SpawnHologram.spawn(p);
        }

        getLogger().info("Plugin ready! (Loadtime: " + getLoad() + "ms)");
        sendDebug("&3Pretpark&6Core&9> &aPlugin load finished! &c(" + getLoad() + "ms)");
    }

    public void onDisable() {
        startTime = System.currentTimeMillis();
        sendDebug("&3Pretpark&6Core&9> &aStarting plugin unload... &oPlease wait...");
        getLogger().info("Disabling plugin... Please wait.");
        MiscUtils.updateVouchers();
        PointUtils.saveAll();
        HologramUtils.removeAll();

        for(Player p : Bukkit.getOnlinePlayers()){
            ScoreboardUtils.destroyScoreboard(p);
            SpawnHologram.despawn(p);

            MysqlManager.saveData(p);
            MysqlManager.savePrefs(p);
            MysqlManager.saveRecords(p);

            Variables.profile.remove(p.getName());
        }

        sendDebug("&3Pretpark&6Core&9> &aPlugin unload finished! &c(" + getLoad() + "ms)");
        plugin = null; //To prevent memory leaks
    }

    //Used to register Listeners.
    private void registerListeners(Plugin plugin, Listener... listeners){
        for(Listener listener : listeners){
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
            getLogger().info("Registered listener: " + listener.toString());
        }
    }

    //Used to resister commands.
    private void registerCommand(String cmd, CommandExecutor executor){
        getCommand(cmd).setExecutor(executor);
        getLogger().info("Registerd command \"" + cmd + "\" with executor\"" + executor.toString() + "\"");
    }

    //Used to hook API's
    private void hookApi(String api){
        if (getServer().getPluginManager().getPlugin(api) != null && getServer().getPluginManager().getPlugin(api).isEnabled()) {
            getLogger().info("Successfully hooked into " + api + "!");
            sendDebug(MiscUtils.color("&9HookAPI> &8[&a" + api + "&8] \u00BB &2&lHooking success!"));
        } else {
            getLogger().warning("Failed to hook into " + api + ", disabling plugin!");
            sendDebug(MiscUtils.color("&9HookAPI> &8[&a" + api + "&8] \u00BB &c&lHooking failed!"));
            getPluginLoader().disablePlugin(this);
        }
    }

    //Used to show the load time.
    private long getLoad(){
        return System.currentTimeMillis() - startTime;
    }

     //Used to send debug messages.
    public static void sendDebug(String msg){
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.isOp()) {
                p.sendMessage(MiscUtils.color(msg));
            }
        }
    }

    //Returns the plugin instance.
    public static Plugin getPlugin() {
        return plugin;
    }
}
