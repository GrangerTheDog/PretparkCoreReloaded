package me.Cooltimmetje.PretparkCore;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class has been created on 9/7/2015 at 21:27 by cooltimmetje.
 */
public class Main extends JavaPlugin {

    private long startTime;
    private static Plugin plugin;

    public void onEnable(){
        startTime = System.currentTimeMillis();
        getLogger().info("Enabling plugin... Please wait.");
        plugin = this; //Registering the plugin variable to allow other classes to access it.

        getLogger().info("Starting pre-setup...."); //For everything that will cause issues if it gets done after registering stuff
        //TODO: Make pre-setup

        getLogger().info("Registering Listeners..."); //Well, this registers the listeners.
        //TODO: Register listeners

        getLogger().info("Registering Commands..."); //Can you guess what this does? Yes! It registers the commands.
        //TODO: Register commands

        getLogger().info("Starting post-setup"); //For stuff like, loading arraylists and databases.
        //TODO: Make post-setup

        getLogger().info("Finishing up..."); //For stuff that needs to be done after everything.
        //TODO: Make Finishing

        getLogger().info("Plugin ready! (Loadtime: " + getLoad() + "ms)");
    }

    public void onDisable() {
        getLogger().info("Disabling plugin... Please wait.");

        //TODO: Make disable protocol

        plugin = null; //To prevent memory leaks
    }

    private long getLoad(){
        return System.currentTimeMillis() - startTime;
    }

    //Returns the plugin instance.
    public static Plugin getPlugin(){
        return plugin;
    }


}
