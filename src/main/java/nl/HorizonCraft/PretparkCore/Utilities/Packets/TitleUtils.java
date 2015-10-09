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

package nl.HorizonCraft.PretparkCore.Utilities.Packets;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * This class has been created on 10/08/2015 at 7:45 PM by Cooltimmetje.
 */
public class TitleUtils {

    public static void sendTitle(Player p, String message, PacketPlayOutTitle.EnumTitleAction titlePos, int fadeIn, int stay, int fadeOut){
        CraftPlayer craftPlayer = (CraftPlayer) p;
        PacketPlayOutTitle title = new PacketPlayOutTitle(titlePos, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + MiscUtils.color(message) + "\"}"), fadeIn, stay, fadeOut);

        craftPlayer.getHandle().playerConnection.sendPacket(title);
    }

    public static void sendAction(Player p, String message){
        CraftPlayer craftPlayer = (CraftPlayer) p;
        PacketPlayOutChat action = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + MiscUtils.color(message) + "\"}"), (byte) 2);

        craftPlayer.getHandle().playerConnection.sendPacket(action);
    }

}
