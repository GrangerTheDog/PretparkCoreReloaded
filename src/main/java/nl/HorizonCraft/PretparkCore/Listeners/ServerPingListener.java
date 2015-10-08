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

package nl.HorizonCraft.PretparkCore.Listeners;

import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.ArrayList;

/**
 * This class has been created on 10/07/2015 at 9:02 PM by Cooltimmetje.
 */
public class ServerPingListener implements Listener {

    private static ArrayList<String> sayings = new ArrayList<>();

    @EventHandler
    public void onPing(ServerListPingEvent event){
        String saying = sayings.get(MiscUtils.randomInt(0, sayings.size()));
        event.setMotd(MiscUtils.color(Variables.SERVER_NAME  + "&8\u00BB &aIn aanbouw.\n&3&lH&6&lC &8\u00BB &b&o" + saying));
    }

    public static void setup(){
        sayings.add("Vet vrij!");
        sayings.add("Bevat koala's! :o");
        sayings.add("JAAAA VRIEEEEEEND!");
        sayings.add("Faaabaaahhh Faabaaaah");
        sayings.add("NEEEE VRIEEEEENDDD!");
        sayings.add("Nu met een gratis sticker!");
        sayings.add("Druk niet op de rode knop!");
        sayings.add("Discord is Bae <3");
        sayings.add("Like als je Jordy dik vindt");
        sayings.add("Brandy = Koala #CONFIRMED");
        sayings.add("KOEKJES!");
        sayings.add("Jeremy heeft een trekker fetish [lenny]");
    }

}