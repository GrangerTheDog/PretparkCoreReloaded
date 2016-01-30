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

package nl.HorizonCraft.PretparkCore.Bundles.MysteryBox;

import com.darkblade12.particleeffect.ParticleEffect;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementsEnum;
import nl.HorizonCraft.PretparkCore.Bundles.Gadgets.GadgetsEnum;
import nl.HorizonCraft.PretparkCore.Bundles.Pets.PetType;
import nl.HorizonCraft.PretparkCore.Bundles.Wardrobe.PiecesEnum;
import nl.HorizonCraft.PretparkCore.Main;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import nl.HorizonCraft.PretparkCore.Utilities.ScheduleUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Cooltimmetje on 1/28/2016 at 7:40 PM.
 */
public class BoxAnimation {

    public static void openBox(final Player p){
        BoxMenu.inUse = true;
        p.teleport(new Location(Variables.WORLD, 94.5, 59, -312.5, -90, 0));
        final CorePlayer cp = PlayerUtils.getProfile(p);

        PlayerUtils.getProfile(p).removeBoxes(p, 1, "MysteryBox geopend", true);
        PlayerUtils.getProfile(p).removeKeys(p, 1, "MysteryBox geopend", true);
        cp.awardAchievement(p, AchievementsEnum.MYSTERYBOX_OPEN);

        Variables.WORLD.getBlockAt(96, 61, -315).setType(Material.BARRIER);
        Variables.WORLD.getBlockAt(96, 61, -313).setType(Material.BARRIER);
        Variables.WORLD.getBlockAt(96, 61, -311).setType(Material.BARRIER);
        Variables.WORLD.getBlockAt(96, 59, -315).setType(Material.BARRIER);
        Variables.WORLD.getBlockAt(96, 59, -313).setType(Material.BARRIER);
        Variables.WORLD.getBlockAt(96, 59, -311).setType(Material.BARRIER);

        final Hologram itemHolo = HologramsAPI.createHologram(Main.getPlugin(), new Location(Variables.WORLD, 96.5, 61, -312.5));
        final Hologram weightHolo = HologramsAPI.createHologram(Main.getPlugin(), new Location(Variables.WORLD, 96.5, 61, -310.5));
        final Hologram typeHolo = HologramsAPI.createHologram(Main.getPlugin(), new Location(Variables.WORLD, 96.5, 61, -314.5));
        final Hologram costHolo = HologramsAPI.createHologram(Main.getPlugin(), new Location(Variables.WORLD, 95.5, 59.75, -312.5));

        Weight weight;
        final RewardType rewardType = RewardType.random();

        GadgetsEnum gadget = null;
        PetType pet = null;
        PiecesEnum piece = null;

        int weightChooser = MiscUtils.randomInt(0, 100);
        if(weightChooser < Variables.LEGENDARY_CHANCE){
            weight = Weight.LEGENDARY;
        } else if(weightChooser < Variables.EPIC_CHANCE) {
            weight = Weight.EPIC;
        } else if(weightChooser < Variables.RARE_CHANCE) {
            weight = Weight.RARE;
        } else {
            weight = Weight.COMMON;
        }

        final Weight weightf = weight;

        if(rewardType == RewardType.GAGDET){
            boolean invalidWeight = true;
            while (invalidWeight){
                gadget = GadgetsEnum.random();
                if(gadget.getWeight() == weight){
                    invalidWeight = false;
                }
            }
        } else if (rewardType == RewardType.CLOTHING){
            boolean invalidWeight = true;
            while (invalidWeight){
                piece = PiecesEnum.random();
                if(piece.getWeight() == weight){
                    invalidWeight = false;
                }
            }
        } else if (rewardType == RewardType.PET){
            boolean invalidWeight = true;
            while (invalidWeight){
                pet = PetType.random();
                if(pet.getWeight() == weight){
                    invalidWeight = false;
                }
            }
        }

        final GadgetsEnum gadgetf = gadget;
        final PiecesEnum piecef = piece;
        final PetType petf = pet;

        ScheduleUtils.scheduleTask(20, new Runnable() {
            @Override
            public void run() {
                Variables.WORLD.getBlockAt(96, 59, -313).setType(Material.QUARTZ_BLOCK);
                Variables.WORLD.getBlockAt(96, 59, -313).setData((byte)2);
                Variables.WORLD.getBlockAt(96, 59, -313).getWorld().playEffect(Variables.WORLD.getBlockAt(96, 59, -313).getLocation(), Effect.STEP_SOUND, Variables.WORLD.getBlockAt(96, 59, -313).getTypeId());
            }
        });
        ScheduleUtils.scheduleTask(60, new Runnable() {
            @Override
            public void run() {
                Variables.WORLD.getBlockAt(96, 59, -315).setType(Material.QUARTZ_BLOCK);
                Variables.WORLD.getBlockAt(96, 59, -315).setData((byte)2);
                Variables.WORLD.getBlockAt(96, 59, -315).getWorld().playEffect(Variables.WORLD.getBlockAt(96, 59, -315).getLocation(), Effect.STEP_SOUND, Variables.WORLD.getBlockAt(96, 59, -315).getTypeId());
                Variables.WORLD.getBlockAt(96, 59, -311).setType(Material.QUARTZ_BLOCK);
                Variables.WORLD.getBlockAt(96, 59, -311).setData((byte)2);
                Variables.WORLD.getBlockAt(96, 59, -311).getWorld().playEffect(Variables.WORLD.getBlockAt(96, 59, -311).getLocation(), Effect.STEP_SOUND, Variables.WORLD.getBlockAt(96, 59, -311).getTypeId());
            }
        });

        ScheduleUtils.scheduleTask(80, new Runnable() {
            @Override
            public void run() {
                ParticleEffect.ENCHANTMENT_TABLE.display(2,2,2,1,50, new Location(Variables.WORLD, 96.5, 60.5, -310.5), 16);
                Variables.WORLD.playSound(new Location(Variables.WORLD, 96.5, 60.5, -310.5), Sound.FIREWORK_TWINKLE, 50, 1);
            }
        });
        ScheduleUtils.scheduleTask(120, new Runnable() {
            @Override
            public void run() {
                weightHolo.appendTextLine(MiscUtils.color("&" + weightf.getColor() + "&l" + weightf.toString()));
                weightHolo.appendItemLine(new ItemStack(Material.STAINED_CLAY,1, (short) weightf.getData()));
                Variables.WORLD.playSound(new Location(Variables.WORLD, 96.5, 60.5, -310.5), Sound.CHICKEN_EGG_POP, 50, 1);
                switch (weightf){
                    default:
                        break;
                    case COMMON:
                        cp.awardAchievement(p, AchievementsEnum.COMMON);
                        break;
                    case RARE:
                        cp.awardAchievement(p, AchievementsEnum.RARE);
                        break;
                    case EPIC:
                        cp.awardAchievement(p, AchievementsEnum.EPIC);
                        break;
                    case LEGENDARY:
                        cp.awardAchievement(p, AchievementsEnum.LEGENDARY);
                        break;
                }
            }
        });

        ScheduleUtils.scheduleTask(140, new Runnable() {
            @Override
            public void run() {
                ParticleEffect.ENCHANTMENT_TABLE.display(2,2,2,1,50, new Location(Variables.WORLD, 96.5, 60.5, -314.5), 16);
                Variables.WORLD.playSound(new Location(Variables.WORLD, 96.5, 60.5, -314.5), Sound.FIREWORK_TWINKLE, 50, 1);
        }
        });
        ScheduleUtils.scheduleTask(180, new Runnable() {
            @Override
            public void run() {
                typeHolo.appendTextLine(MiscUtils.color("&a&l" + rewardType.getName()));
                typeHolo.appendItemLine(new ItemStack(rewardType.getMaterial()));
                Variables.WORLD.playSound(new Location(Variables.WORLD, 96.5, 60.5, -314.5), Sound.CHICKEN_EGG_POP, 50, 1);
            }
        });

        ScheduleUtils.scheduleTask(200, new Runnable() {
            @Override
            public void run() {
                ParticleEffect.ENCHANTMENT_TABLE.display(2,2,2,1,50, new Location(Variables.WORLD, 96.5, 60.5, -312.5), 16);
                Variables.WORLD.playSound(new Location(Variables.WORLD, 96.5, 60.5, -312.5), Sound.FIREWORK_TWINKLE, 50, 1);
            }
        });
        ScheduleUtils.scheduleTask(240, new Runnable() {
            @Override
            public void run() {
                switch (rewardType) {
                    case GAGDET:
                        if(gadgetf.getCost() == 0){
                            cp.awardAchievement(p, AchievementsEnum.EXCLUSIVE);
                            costHolo.appendTextLine(MiscUtils.color("&aDIT IS EEN"));
                            costHolo.appendTextLine(MiscUtils.color("&6&lMYSTERYBOX EXCLUSIVE!"));
                        } else {
                            costHolo.appendTextLine(MiscUtils.color("&aDit item kost in de shop:"));
                            costHolo.appendTextLine(MiscUtils.color("&6" + gadgetf.getCost() + " coins"));
                        }
                        itemHolo.appendTextLine(MiscUtils.color("&" + weightf.getColor() + "&l" + gadgetf.getName()));
                        itemHolo.appendItemLine(new ItemStack(gadgetf.getMaterial()));
                        break;
                    case CLOTHING:
                        if(piecef.getCost() == 0){
                            cp.awardAchievement(p, AchievementsEnum.EXCLUSIVE);
                            costHolo.appendTextLine(MiscUtils.color("&aDIT IS EEN"));
                            costHolo.appendTextLine(MiscUtils.color("&6&lMYSTERYBOX EXCLUSIVE!"));
                        } else {
                            costHolo.appendTextLine(MiscUtils.color("&aDit item kost in de shop:"));
                            costHolo.appendTextLine(MiscUtils.color("&6" + piecef.getCost() + " coins"));
                        }
                        itemHolo.appendTextLine(MiscUtils.color("&" + weightf.getColor() + "&l" + piecef.getSuit().getName() + " " + piecef.getSuitType().getName()));
                        itemHolo.appendItemLine(piecef.getItemStack());
                        break;
                    case PET:
                        if (petf != null) {
                            if(petf.getCost() == 0){
                                cp.awardAchievement(p, AchievementsEnum.EXCLUSIVE);
                                costHolo.appendTextLine(MiscUtils.color("&aDIT IS EEN"));
                                costHolo.appendTextLine(MiscUtils.color("&6&lMYSTERYBOX EXCLUSIVE!"));
                            } else {
                                costHolo.appendTextLine(MiscUtils.color("&aDit item kost in de shop:"));
                                costHolo.appendTextLine(MiscUtils.color("&6" + petf.getCost() + " coins"));
                            }
                            itemHolo.appendTextLine(MiscUtils.color("&" + weightf.getColor() + "&l" + petf.getName()));
                            itemHolo.appendItemLine(petf.getItemStack());
                        }
                        break;
                }
                MiscUtils.shootFirework(itemHolo.getLocation(), Variables.WORLD_NAME, true);
            }
        });

        ScheduleUtils.scheduleTask(260, new Runnable() {
            @Override
            public void run() {
                switch (rewardType) {
                    case GAGDET:
                        if(PlayerUtils.getProfile(p).getGadgets()[gadgetf.getId()] == 't'){
                            PlayerUtils.getProfile(p).addDust(p, MiscUtils.randomInt(weightf.getMin(), weightf.getMax()),  "Item already owned [PLACEHOLDER]", false, true); //TODO: PLACEHOLDER
                        } else {
                            PlayerUtils.getProfile(p).unlockGadget(gadgetf,p,true,true,true);
                        }
                        break;
                    case CLOTHING:
                        if(PlayerUtils.getProfile(p).getPieces()[piecef.getId()] == 't'){
                            PlayerUtils.getProfile(p).addDust(p, MiscUtils.randomInt(weightf.getMin(), weightf.getMax()),  "Item already owned [PLACEHOLDER]", false, true); //TODO: PLACEHOLDER
                        } else {
                            PlayerUtils.getProfile(p).unlockClothing(piecef,p,true,true,true);
                        }
                        break;
                    case PET:
                        if (petf != null && PlayerUtils.getProfile(p).getPets()[petf.getId()] == 't') {
                            PlayerUtils.getProfile(p).addDust(p, MiscUtils.randomInt(weightf.getMin(), weightf.getMax()),  "Item already owned [PLACEHOLDER]", false, true); //TODO: PLACEHOLDER
                        } else {
                            PlayerUtils.getProfile(p).unlockPet(petf,p,true,true,true);
                        }
                        break;
                }

            }
        });

        ScheduleUtils.scheduleTask(300, new Runnable() {
            @Override
            public void run() {
                itemHolo.delete();
                weightHolo.delete();
                typeHolo.delete();
                costHolo.delete();

                Variables.WORLD.getBlockAt(96, 61, -315).setType(Material.AIR);
                Variables.WORLD.getBlockAt(96, 61, -313).setType(Material.AIR);
                Variables.WORLD.getBlockAt(96, 61, -311).setType(Material.AIR);
                Variables.WORLD.getBlockAt(96, 59, -315).setType(Material.AIR);
                Variables.WORLD.getBlockAt(96, 59, -313).setType(Material.AIR);
                Variables.WORLD.getBlockAt(96, 59, -311).setType(Material.AIR);

                BoxMenu.inUse = false;
            }
        });

    }

}
