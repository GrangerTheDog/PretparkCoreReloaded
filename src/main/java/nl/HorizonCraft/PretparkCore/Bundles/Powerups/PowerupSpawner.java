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

package nl.HorizonCraft.PretparkCore.Bundles.Powerups;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.handler.PickupHandler;
import com.gmail.filoghost.holographicdisplays.api.line.ItemLine;
import nl.HorizonCraft.PretparkCore.Main;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.*;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

/**
 * Created by Cooltimmetje on 1/31/2016 at 2:52 PM.
 */
public class PowerupSpawner {

    public static void spawn(){
        if(Bukkit.getOnlinePlayers().size() > 0){
            if(!Variables.powerupLocations.isEmpty()){
                Random generator = new Random();
                Object[] objects = Variables.powerupLocations.keySet().toArray();

                int id = (int) objects[generator.nextInt(objects.length)];
                final PowerupTypes powerupType = PowerupTypes.random();
                Location location = Variables.powerupLocations.get(id);
                final Hologram hologram = HologramsAPI.createHologram(Main.getPlugin(), location);
                hologram.teleport(hologram.getLocation().add(0,2,0));

                hologram.appendTextLine(MiscUtils.color("&" + powerupType.getColor() + powerupType.getName() + " PowerUp"));
                hologram.appendTextLine(MiscUtils.color("&b&lPAK MIJ OP!"));
                ItemLine icon = hologram.appendItemLine(new ItemStack(powerupType.getMaterial()));

                Variables.powerups.add(hologram);

                ChatUtils.bcMsgTag("PowerUp", "Er is een &" + powerupType.getColor() + powerupType.getName() + " PowerUp &agespawned! Vind hem als eerste om de reward te claimen!");

                icon.setPickupHandler(new PickupHandler() {
                    @Override
                    public void onPickup(Player p) {
                        CorePlayer cp = PlayerUtils.getProfile(p);
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1F, 2F);
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1F, 2F);
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1F, 2F);
                        p.playEffect(hologram.getLocation(), Effect.MOBSPAWNER_FLAMES, null);
                        hologram.delete();
                        Variables.powerups.remove(hologram);
                        ChatUtils.bcMsgTag("PowerUp", "&c" + p.getName() + " &aheeft een &" + powerupType.getColor() + powerupType.getName() + " PowerUp &agevonden!");

                        switch (powerupType){
                            case COINS:
                                cp.addCoins(p, MiscUtils.randomInt(powerupType.getMin(), powerupType.getMax()), "PowerUp gevonden!", false, false);
                                break;
                            case EXP:
                                cp.addExp(p, MiscUtils.randomInt(powerupType.getMin(), powerupType.getMax()), "PowerUp gevonden!", false, false);
                                break;
                            case BOXES:
                                cp.addBoxes(p, MiscUtils.randomInt(powerupType.getMin(), powerupType.getMax()), "PowerUp gevonden!", false, false);
                                break;
                            case KEYS:
                                cp.addKeys(p, MiscUtils.randomInt(powerupType.getMin(), powerupType.getMax()), "PowerUp gevonden!", false, false);
                                break;
                            case DUST:
                                cp.addDust(p, MiscUtils.randomInt(powerupType.getMin(), powerupType.getMax()), "PowerUp gevonden!", false, false);
                                break;
                        }
                    }
                });
            }
        }
    }

    public static void activateDebug() {
        for(int i : Variables.powerupLocations.keySet()){
            final Hologram hologram = HologramsAPI.createHologram(Main.getPlugin(), Variables.powerupLocations.get(i));
            hologram.teleport(hologram.getLocation().add(0,2,0));

            hologram.appendTextLine(MiscUtils.color("&7Debug Powerup - ID: " + i));
            hologram.appendTextLine(MiscUtils.color("&7&lGEEN REWARD"));
            hologram.appendItemLine(new ItemStack(Material.BARRIER));
            Variables.holograms.add(hologram);

            ScheduleUtils.scheduleTask(200, new Runnable() {
                @Override
                public void run() {
                    hologram.delete();
                    Variables.holograms.remove(hologram);
                }
            });
        }
    }
}
