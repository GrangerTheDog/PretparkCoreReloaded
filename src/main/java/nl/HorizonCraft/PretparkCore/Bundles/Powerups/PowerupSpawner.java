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

package nl.HorizonCraft.PretparkCore.Bundles.Powerups;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.handler.PickupHandler;
import com.gmail.filoghost.holographicdisplays.api.line.ItemLine;
import nl.HorizonCraft.PretparkCore.Main;
import nl.HorizonCraft.PretparkCore.Managers.KarmaManager;
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
                                cp.addDust(p, MiscUtils.randomInt(powerupType.getMin(), powerupType.getMax()), "PowerUp gevonden!", false, false, true);
                                break;
                        }

                        KarmaManager.startKarma();
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
