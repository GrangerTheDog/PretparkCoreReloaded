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

package nl.HorizonCraft.PretparkCore.Bundles.Pets;

import nl.HorizonCraft.PretparkCore.Bundles.MysteryBox.Weight;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * This class has been created on 10/13/2015 at 2:17 PM by Cooltimmetje.
 */
public enum PetType {

    CREEPER(0, "Creeper", "Nee, deze blaast niet op,\ndus KNUFFELEN MAAR!", 0, Weight.LEGENDARY, "MHF_Creeper", EntityType.CREEPER, Material.SKULL_ITEM, SkullType.PLAYER.ordinal()),
    SKELETON(1, "Skeleton", "Met of zonder boog, maar geef hem geen pijlen...", 0, Weight.EPIC, "MHF_Skeleton", EntityType.SKELETON, Material.SKULL_ITEM, SkullType.PLAYER.ordinal()),
    SPIDER(2, "Spider", "Het is maar waar je van houd als huisdier...", 800, Weight.EPIC, "MHF_Spider", EntityType.SPIDER, Material.SKULL_ITEM, SkullType.PLAYER.ordinal()),
    ZOMBIE(3, "Zombie", "Vriendjes voor het leven!\nOw wacht... Hij is al dood...", 0, Weight.LEGENDARY, "MHF_Zombie", EntityType.ZOMBIE, Material.SKULL_ITEM, SkullType.PLAYER.ordinal()),
    SLIME_S(4, "Kleine Slime", "AAAWWWWW.... ZO SCHATTIG EN FLUBBERIG!", 800, Weight.EPIC, "MHF_Slime", EntityType.SLIME, Material.SKULL_ITEM, SkullType.PLAYER.ordinal()),
    SLIME_M(5, "Grote Slime", "Niet zo schattig, maar wel flubberig!", 0, Weight.LEGENDARY, "MHF_Slime", EntityType.SLIME, Material.SKULL_ITEM, SkullType.PLAYER.ordinal()),
    ZOMBIE_PIGMAN(6, "Zombie Pigman", "Hij vond de nether niet meer leuk.", 0, Weight.LEGENDARY, "MHF_PigZombie", EntityType.PIG_ZOMBIE, Material.SKULL_ITEM, SkullType.PLAYER.ordinal()),
    CAVE_SPIDER(7, "Cave Spider", "Niet gifitg!", 0, Weight.LEGENDARY, "MHF_CaveSpider", EntityType.CAVE_SPIDER, Material.SKULL_ITEM, SkullType.PLAYER.ordinal()),
    SILVERFISH(8, "Silverfish", "Ieuw...", 650, Weight.EPIC, "none", EntityType.SILVERFISH, Material.MOSSY_COBBLESTONE, 0),
    BAT(9, "Bat", "Is het een vogel? Is het een vliegtuig?\nNee, het is maar een bat", 700, Weight.LEGENDARY, "none", EntityType.BAT, Material.STONE, 0),
    WITCH(10, "Witch", "Deze maakt aleen maar goede drankjes!", 0, Weight.LEGENDARY, "none", EntityType.WITCH, Material.POTION, 0),
    PIG(11, "Pig", "Knor...", 700, Weight.EPIC, "MHF_Pig", EntityType.PIG, Material.SKULL_ITEM, SkullType.PLAYER.ordinal()),
    SHEEP(12, "Sheep", "Beeeeh...", 0, Weight.LEGENDARY, "MHF_Sheep", EntityType.SHEEP, Material.SKULL_ITEM, SkullType.PLAYER.ordinal()),
    COW(13, "Cow", "Mooeeehh...", 0, Weight.EPIC, "MHF_Cow", EntityType.COW, Material.SKULL_ITEM, SkullType.PLAYER.ordinal()),
    CHICKEN(14, "Chicken", "Een tip: Hou hem uit de buurt van Brandy!", 0, Weight.LEGENDARY, "MHF_Chicken", EntityType.CHICKEN, Material.SKULL_ITEM, SkullType.PLAYER.ordinal()),
    WOLF(15, "Wolf", "Een trouw vriendje!", 800, Weight.EPIC, "none", EntityType.WOLF, Material.BONE, 0),
    OCELOT(16, "Ocelot", "Een kat, maar dan anders", 800, Weight.EPIC, "MHF_Ocelot", EntityType.OCELOT, Material.SKULL_ITEM, SkullType.PLAYER.ordinal()),
    RABBIT(17, "Rabbit", "Huppelt vrolijk achter je aan!", 900, Weight.EPIC, "none", EntityType.RABBIT, Material.RABBIT_HIDE, 0);

    private int id;
    private String name;
    private String lore;
    private int price;
    private Weight weight;
    private String skullName;
    private EntityType entityType;
    private Material material;
    private int data;

    PetType(int i, String s, String s1, int i1, Weight w, String s2, EntityType et, Material m, int i2) {
        this.id = i;
        this.name = s;
        this.lore = s1;
        this.price = i1;
        this.weight = w;
        this.skullName = s2;
        this.entityType = et;
        this.material = m;
        this.data = i2;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLore() {
        return lore;
    }

    public int getCost() {
        return price;
    }

    public Weight getWeight() {
        return weight;
    }

    public String getSkullName() {
        return skullName;
    }

    public boolean isMysteryBoxExclusive() {
        return getCost() == 0;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public static PetType getByItem(ItemStack is) {
        if (is.getType() == Material.SKULL_ITEM) {
            String name = ((SkullMeta) is.getItemMeta()).getOwner();
            for (PetType pet : PetType.values()) {
                if (pet.getSkullName().equals(name)) {
                    return pet;
                }
            }
            return null;
        } else {
            Material material = is.getType();
            for (PetType pet : PetType.values()) {
                if (pet.getMaterial() == material) {
                    return pet;
                }
            }
            return null;
        }
    }

    public Material getMaterial() {
        return material;
    }

    public int getData() {
        return data;
    }
}
