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

package nl.HorizonCraft.PretparkCore.Bundles.Gadgets;

import com.darkblade12.particleeffect.ParticleEffect;
import nl.HorizonCraft.PretparkCore.Enums.AchievementsEnum;
import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;

/**
 * This class has been created on 09/18/2015 at 7:52 PM by Cooltimmetje.
 */
public class GadgetTriggers implements Listener {

    private HashMap<String,Long> cdPunch = new HashMap<>();
    private HashMap<String,Long> cdPunchStaff = new HashMap<>();
    private HashMap<String,Long> cdFirework = new HashMap<>();
    private int cdPunchSec = GadgetsEnum.STAFF_LAUNCHER.getCooldown();
    private int cdFireworkSec = GadgetsEnum.FIREWORK.getCooldown();

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(event.getAction().toString().contains("RIGHT")){
            if(event.getItem() != null){
                if(event.getItem().hasItemMeta()) {
                    event.setCancelled(true);
                    Material m = event.getMaterial();
                    switch (m) {
                        default:
                            break;
                        case FIREWORK_CHARGE:
                            shootFirework(p);
                            break;
                    }
                }
            }
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
                                        ParticleEffect.EXPLOSION_LARGE.display(5, 5, 5, 1, 47, target.getLocation(), 16);
                                        Bukkit.getWorld(target.getWorld().getName()).playSound(target.getLocation(), Sound.EXPLODE, 20, 1);
                                        target.setFlying(false);
                                        target.setVelocity(new Vector(0, 3, 0));
                                        cdPunch.put(p.getName(), System.currentTimeMillis());
                                        cdPunchStaff.put(target.getName(), System.currentTimeMillis());
                                        if(target.getName().equals("xBrandy")) {
                                            PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.KOALA_SLAP);
                                        } else if(target.getName().equals("klapklap980")){
                                            PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.CREEPER_SLAP);
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
        if(event.getPlayer().getItemInHand().getType() == Material.PISTON_STICKY_BASE || event.getPlayer().getItemInHand().getType() == Material.SLIME_BLOCK){
            if(event.getPlayer().getItemInHand().hasItemMeta()){
                event.setCancelled(true);
            }
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

}
