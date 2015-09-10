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
 * In additoin to the above:
 * All content in the repo/plugin is created by and owned by HorizonCraft, unless
 * stated otherwise. All content that is not created by us will be placed in their
 * original package, where they were found or that was set by the owner by default.
 * This will also be stated before the "public class".
 *
 * You are free to use the code anywhere you like, but we will not provide ANY support
 * unless you are on our server using this plugin.
 */

package nl.HorizonCraft.PretparkCore;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class has been created on 9/7/2015 at 21:27 by Cooltimmetje.
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
