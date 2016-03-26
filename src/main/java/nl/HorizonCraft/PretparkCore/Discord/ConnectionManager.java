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
