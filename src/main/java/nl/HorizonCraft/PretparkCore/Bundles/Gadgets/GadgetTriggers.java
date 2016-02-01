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

package nl.HorizonCraft.PretparkCore.Bundles.Gadgets;

import com.darkblade12.particleeffect.ParticleEffect;
import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementsEnum;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import nl.HorizonCraft.PretparkCore.Utilities.ScheduleUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashMap;

/**
 * This class has been created on 09/18/2015 at 7:52 PM by Cooltimmetje.
 */
public class GadgetTriggers implements Listener {

    private HashMap<String,Long> cdPunch = new HashMap<>();
    private HashMap<String,Long> cdPunchStaff = new HashMap<>();
    private HashMap<String,Long> cdFirework = new HashMap<>();
    private HashMap<String,Long> cdChicken = new HashMap<>();
    private HashMap<String,Long> cdSnowball = new HashMap<>();
    private int cdPunchSec = GadgetsEnum.STAFF_LAUNCHER.getCooldown();
    private int cdFireworkSec = GadgetsEnum.FIREWORK.getCooldown();
    private int cdChickenSec = GadgetsEnum.BOEM_CHICKEN.getCooldown();
    private int cdSnowballSec = GadgetsEnum.SNOWBALL_GUN.getCooldown();

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(event.getAction().toString().contains("RIGHT")){
            if(event.getItem() != null){
                if(event.getItem().hasItemMeta() && event.getItem().getType() != Material.SKULL_ITEM) {
                    Material m = event.getMaterial();
                    switch (m) {
                        default:
                            break;
                        case FIREWORK_CHARGE:
                            event.setCancelled(true);
                            shootFirework(p);
                            PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.FIREWORK);
                            break;
                        case COOKED_CHICKEN:
                            event.setCancelled(true);
                            shootChicken(p);
                            break;
                        case IRON_BARDING:
                            event.setCancelled(true);
                            shootSnowball(p);
                            break;
                        case EGG:
                            event.setCancelled(true);
                            shootClusterChicken(p);
                    }
                }
            }
        }
    }

    private void shootClusterChicken(Player p) {
        p.sendMessage("Soon...");
        //TODO: MAKE THIS GADGET
    }

    private void shootChicken(Player p) {
        if(!cdChicken.containsKey(p.getName()) || MiscUtils.cooldownCheck(cdChicken.get(p.getName()), cdChickenSec)) {
            ChatUtils.sendMsgTag(p, "ExplodingChicken", "KIIIIIIIIIP! :D");
            cdChicken.put(p.getName(), System.currentTimeMillis());

            final Player pFinal = p;
            final Entity chicken = p.getWorld().spawnEntity(p.getLocation(), EntityType.CHICKEN);
            chicken.setCustomName(MiscUtils.color("&aBrandy's Suicidal Chicken"));
            chicken.setCustomNameVisible(true);

            ScheduleUtils.scheduleTask(150, new Runnable() {
                @Override
                public void run() {
                    ParticleEffect.EXPLOSION_LARGE.display(2,2,2,1,25,chicken.getLocation(),16);
                    pFinal.getWorld().playSound(chicken.getLocation(), Sound.EXPLODE, 30, 1);
                    pFinal.getWorld().playSound(chicken.getLocation(), Sound.CHICKEN_HURT, 30, 1);
                    pFinal.getWorld().playSound(chicken.getLocation(), Sound.CHICKEN_EGG_POP, 30, 1);
                    chicken.getWorld().dropItem(chicken.getLocation(), new ItemStack(Material.RAW_CHICKEN,1)).setVelocity(Vector.getRandom().multiply(-0.5));
                    chicken.getWorld().dropItem(chicken.getLocation(), new ItemStack(Material.EGG, 1)).setVelocity(Vector.getRandom().multiply(0.5));
                    chicken.getWorld().dropItem(chicken.getLocation(), new ItemStack(Material.FEATHER, 1)).setVelocity(Vector.getRandom().multiply(-0.5));
                    chicken.getWorld().dropItem(chicken.getLocation(), new ItemStack(Material.BONE, 1)).setVelocity(Vector.getRandom().multiply(0.5));
                    ((Chicken) chicken).setHealth(0);
                    ChatUtils.sendMsgTag(pFinal, "ExplodingChicken", "RIP KIP ;-;");

                    //TODO: MAKE ITEMS DESPAWN
                }
            });

        } else {
            ChatUtils.sendMsgTag(p, "ExplodingChicken", ChatUtils.error + "Je moet nog &c" + MiscUtils.formatTime(MiscUtils.getTimeRemaining(cdChicken.get(p.getName()), cdChickenSec)) +
                    " &awachten voordat je dit weer mag gebruiken.");
        }
    }

    @EventHandler
    @SuppressWarnings("all")
    public void onRightClick(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Minecart)) {
            if (event.getPlayer().getItemInHand() != null) {
                if (event.getPlayer().getItemInHand().getType() == Material.SLIME_BLOCK) {
                    if (event.getPlayer().getItemInHand().hasItemMeta()) {
                        event.setCancelled(true);
                        Player p = event.getPlayer();
                        if (event.getRightClicked() instanceof Player) {
                            Player target = (Player) event.getRightClicked();
                            if (target.hasPermission("pretparkcore.staffbepunch") || target.isOp()) {
                                if (!cdPunch.containsKey(p.getName()) || MiscUtils.cooldownCheck(cdPunch.get(p.getName()), cdPunchSec)) {
                                    if (!cdPunchStaff.containsKey(target.getName()) || MiscUtils.cooldownCheck(cdPunchStaff.get(target.getName()), cdPunchSec)) {
                                        if(p.getName().equals("78wesley") && target.getName().equals("Cooltimmetje")){
                                            p.kickPlayer("PAYBACK BITCH");
                                            ChatUtils.bcMsgTag("Rekt", "Wesley tried, but failed. gg");
                                        } else {
                                            ParticleEffect.EXPLOSION_LARGE.display(5, 5, 5, 1, 47, target.getLocation(), 16);
                                            Bukkit.getWorld(target.getWorld().getName()).playSound(target.getLocation(), Sound.EXPLODE, 20, 1);
                                            target.setFlying(false);
                                            target.setVelocity(new Vector(0, 3, 0));
                                            cdPunch.put(p.getName(), System.currentTimeMillis());
                                            cdPunchStaff.put(target.getName(), System.currentTimeMillis());

                                            switch (target.getName()) {
                                                case "xBrandy":
                                                    PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.KOALA_SLAP);
                                                    break;
                                                case "78wesley":
                                                    PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.PEDOBEAR_SLAP);
                                                    break;
                                                case "BekertjeZuivel":
                                                    PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.MELK_SLAP);
                                                    break;
                                                case "Cooltimmetje":
                                                    PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.COOL_SLAP);
                                                    break;
                                                case "SvenTijger":
                                                    PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.SVEN_SLAP);
                                                    break;
                                                case "Destiny_VG":
                                                    PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.DESTINY_SLAP);
                                                    break;
                                                case "Jordy010NL":
                                                    PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.JORDY_SLAP);
                                                    break;
                                                case "jordyvz01":
                                                    PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.JORDY2_SLAP);
                                                    break;
                                                case "MAETJE":
                                                    PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.MAE_SLAP);
                                                    break;
                                                case "Toptim24":
                                                    PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.TIM_SLAP);
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }

                                    } else {
                                        ChatUtils.sendMsgTag(p, "StaffPunch", ChatUtils.error + "Je moet nog &c" + MiscUtils.formatTime(MiscUtils.getTimeRemaining(cdPunchStaff.get(p.getName()), cdPunchSec)) +
                                                " &awachten voordat&c " + target.getName() + " &aje weer kan punchen!");
                                    }
                                } else {
                                    ChatUtils.sendMsgTag(p, "StaffPunch", ChatUtils.error + "Je moet nog &c" + MiscUtils.formatTime(MiscUtils.getTimeRemaining(cdPunch.get(p.getName()), cdPunchSec)) +
                                               " &awachten voordat je dit weer mag gebruiken.");
                                }
                            } else {
                                ChatUtils.sendMsgTag(p, "StaffPunch", ChatUtils.error + "Dit is geen staff member!");
                            }
                        } else {
                            ChatUtils.sendMsgTag(p, "StaffPunch", ChatUtils.error + "Dit is geen staff member!");
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if(event.getPlayer().getItemInHand().getType() == Material.SLIME_BLOCK){
            if(event.getPlayer().getItemInHand().hasItemMeta()){
                event.setCancelled(true);
            }
        } else {
            event.setCancelled(false);
        }
    }

    private void shootFirework(Player p) {
        if(!cdFirework.containsKey(p.getName()) || MiscUtils.cooldownCheck(cdFirework.get(p.getName()), cdFireworkSec)) {
            MiscUtils.shootFirework(p.getLocation(), p.getWorld().getName(), false);
            ChatUtils.sendMsgTag(p, "Firework", "Je stak een vuurwerkje af! &lWAT EEN MOOI DING.");
            cdFirework.put(p.getName(), System.currentTimeMillis());
//            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "playsound custom.Vuurwerk " + p.getName() + " " + p.getLocation().getBlockX() + " " + p.getLocation().getBlockY() + " " + p.getLocation().getBlockZ());
        } else {
            ChatUtils.sendMsgTag(p, "Firework", ChatUtils.error + "Je moet nog &c" + MiscUtils.formatTime(MiscUtils.getTimeRemaining(cdFirework.get(p.getName()), cdFireworkSec)) +
                    " &awachten voordat je dit weer mag gebruiken.");
        }
    }

    private void shootSnowball(Player p) {
        if(!cdSnowball.containsKey(p.getName()) || MiscUtils.cooldownCheck(cdSnowball.get(p.getName()), cdSnowballSec)) {
            Snowball sb = p.launchProjectile(Snowball.class);
            sb.setShooter(p);
            sb.setVelocity(p.getLocation().getDirection().multiply(1.7));
            p.playSound(p.getLocation(), Sound.IRONGOLEM_THROW, 100, 1);
            ChatUtils.sendMsgTag(p, "SnowballGun", "Pew pew!");
            cdSnowball.put(p.getName(), System.currentTimeMillis());
        } else {
            ChatUtils.sendMsgTag(p, "SnowballGun", ChatUtils.error + "Je moet nog &c" + MiscUtils.formatTime(MiscUtils.getTimeRemaining(cdSnowball.get(p.getName()), cdSnowballSec)) +
                    " &awachten voordat je dit weer mag gebruiken.");
        }
    }

}
