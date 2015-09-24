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

package nl.HorizonCraft.PretparkCore;

import nl.HorizonCraft.PretparkCore.Bundles.Gadgets.GadgetTriggers;
import nl.HorizonCraft.PretparkCore.Bundles.Gadgets.GadgetsMenu;
import nl.HorizonCraft.PretparkCore.Bundles.MysteryBox.BoxListener;
import nl.HorizonCraft.PretparkCore.Bundles.MysteryBox.BoxSetup;
import nl.HorizonCraft.PretparkCore.Commands.ClearChatCommand;
import nl.HorizonCraft.PretparkCore.Commands.FixGamemodeCommand;
import nl.HorizonCraft.PretparkCore.Commands.MazeCommand;
import nl.HorizonCraft.PretparkCore.Commands.ResetInventoryCommand;
import nl.HorizonCraft.PretparkCore.Enums.AchievementsEnum;
import nl.HorizonCraft.PretparkCore.Listeners.ArmorStandListener;
import nl.HorizonCraft.PretparkCore.Listeners.JoinQuitListener;
import nl.HorizonCraft.PretparkCore.Listeners.WeatherChangeListener;
import nl.HorizonCraft.PretparkCore.Managers.InventoryManager;
import nl.HorizonCraft.PretparkCore.Menus.AdminMenu.MainAdmin;
import nl.HorizonCraft.PretparkCore.Menus.AdminMenu.PlayerAdmin;
import nl.HorizonCraft.PretparkCore.Menus.AdminMenu.TimeAdmin;
import nl.HorizonCraft.PretparkCore.Menus.MyHorizon.AchievementMenu;
import nl.HorizonCraft.PretparkCore.Menus.MyHorizon.MyHorizonMenu;
import nl.HorizonCraft.PretparkCore.Menus.MyHorizon.PreferencesMenu;
import nl.HorizonCraft.PretparkCore.Menus.SwagMenu.MainSwag;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Profiles.MysqlManager;
import nl.HorizonCraft.PretparkCore.Timers.CoinsGiver;
import nl.HorizonCraft.PretparkCore.Timers.DataSaver;
import nl.HorizonCraft.PretparkCore.Utilities.*;
import nl.HorizonCraft.PretparkCore.Utilities.Objects.Hologram;
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
        sendDebug("&9Debug> &aStarting plugin load... &oPlease wait.");

        plugin = this; //Registering the plugin variable to allow other classes to access it.
        this.saveDefaultConfig(); //Saves the config to be used.

        getLogger().info("Starting pre-setup...."); //For everything that will cause issues if it gets done after registering stuff
        MysqlManager.setupHikari();

        getLogger().info("Registering Listeners..."); //Well, this registers the listeners.
        registerListeners(this
                , new WeatherChangeListener(), new JoinQuitListener(), new InventoryManager(), new MainAdmin()
                , new PlayerAdmin(), new TimeAdmin(), new MyHorizonMenu(), new PreferencesMenu(), new MainSwag()
                , new GadgetsMenu(), new GadgetTriggers(), new AchievementMenu(), new ArmorStandListener()
                , new BoxListener()
        );

        getLogger().info("Registering Commands..."); //Can you guess what this does? Yes! It registers the commands.
        //TODO: Register commands
        registerCommand("fixgm", new FixGamemodeCommand());
        registerCommand("resetinv", new ResetInventoryCommand());
        registerCommand("cc", new ClearChatCommand());
        registerCommand("maze", new MazeCommand());
        //format: registerCommand("cmd", new ExecutorClass);

        getLogger().info("Starting setup"); //For stuff like, loading arraylists and databases.
        for(Player p : Bukkit.getOnlinePlayers()){
            PlayerUtils.createProfile(p);

            MysqlManager.loadProfile(p);
            MysqlManager.loadPrefs(p);

            PlayerUtils.configPlayer(p, false);
        }

        getLogger().info("Starting Timers..."); //Well, starts timers. Duh...
        DataSaver.start(this);
        CoinsGiver.start(this);

        getLogger().info("Starting post-setup"); //For frontend stuff, like scoreboards.
        for(Player p : Bukkit.getOnlinePlayers()){
            ScoreboardUtils.constructScoreboard(p);
        }
        BoxSetup.setup();

        getLogger().info("Finishing up..."); //For stuff that needs to be done after everything.
        for(Player p : Bukkit.getOnlinePlayers()){
            CorePlayer cp = PlayerUtils.getProfile(p);
            cp.awardAchievement(p, AchievementsEnum.FIRST_TIME_JOIN);
        }

        getLogger().info("Plugin ready! (Loadtime: " + getLoad() + "ms)");
        sendDebug("&9Debug> &aPlugin load finished! &c(" + getLoad() + "ms) &3&oYou can take a look in the console for more load information.");
    }

    public void onDisable() {
        getLogger().info("Disabling plugin... Please wait.");
        HologramUtils.removeHolos();
        BoxSetup.witch.remove();

        for(Player p : Bukkit.getOnlinePlayers()){
            ScoreboardUtils.destroyScoreboard(p);

            MysqlManager.saveData(p);
            MysqlManager.savePrefs(p);

            Variables.profile.remove(p.getName());
        }

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
        if (getServer().getPluginManager().getPlugin(api) != null && getServer().getPluginManager().getPlugin(api).isEnabled())
            getLogger().info("Successfully hooked into " + api + "!");
        else {
            getLogger().warning("Failed to hook into " + api + ", disabling plugin!");
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
