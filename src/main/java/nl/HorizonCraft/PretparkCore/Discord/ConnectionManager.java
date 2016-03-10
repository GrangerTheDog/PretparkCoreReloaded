/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2016 Tim Medema
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

package nl.HorizonCraft.PretparkCore.Discord;

import nl.HorizonCraft.PretparkCore.Discord.AccountVertification.VerifyMessage;
import nl.HorizonCraft.PretparkCore.Main;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.DiscordException;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.EventDispatcher;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Cooltimmetje on 2/16/2016 at 4:45 PM.
 */
public class ConnectionManager {

    public static IDiscordClient discordClient;
    private static ArrayList<Object> listeners = new ArrayList<>();
    private static HashMap<String,String> roles = new HashMap<>();
    public static boolean setupComplete;
    public static IGuild server;

    public static void openConnection(String email, String password){
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withLogin(email,password);
        try {
            discordClient = clientBuilder.login();
            Main.sendDebug(MiscUtils.color("&9HookAPI> &8[&aDiscord4J&8] \u00BB &a&lVerbonden met Discord!"));
            setupComplete = false;
        } catch (DiscordException e) {
            e.printStackTrace();
            Main.sendDebug(MiscUtils.color("&9HookAPI> &8[&aDiscord4J&8] \u00BB &c&lKon niet verbinden met Discord!"));
        }

        EventDispatcher dispatcher = discordClient.getDispatcher();
        registerListeners(dispatcher, new VerifyMessage());
    }

    private static void registerRoles(List<IRole> roles){
        for(IRole role : roles){
            ConnectionManager.roles.put(role.getName(),role.getID());
        }
    }

    public static void runSetup(IGuild guild){
        if(!setupComplete){
            server = guild;
            registerRoles(guild.getRoles());

            setupComplete = true;
        }
    }

    public static String getRole(String name){
        return roles.get(name);
    }

    private static void registerListeners(EventDispatcher dispatcher, Object... objects){
        for (Object object : objects){
            dispatcher.registerListener(object);
            listeners.add(object);
        }
    }

    public static void closeConnection(){
        EventDispatcher dispatcher = discordClient.getDispatcher();

        for(Object object : listeners){
            dispatcher.unregisterListener(object);
        }
    }

}
