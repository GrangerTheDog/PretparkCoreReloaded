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

package nl.HorizonCraft.PretparkCore.Bundles.Wardrobe;

/**
 * This class has been created on 10/27/2015 at 10:30 by Cooltimmetje.
 */
public enum SuitsEnum {

    NINJA("Ninja", "Overal en nergens teglijk", "Gooi werpsterren"),
    OLAF("Olaf", "Do you wanna build a snowman?", "Spawn Snowmans"),
    LOVE("Love", "It's so lovely!", "Spread the love!"),
    VAMPIRE("Vampier", "Niet bijten!", "Blood Bite"),
    SKELETON("Skeleton", "Beetje mager, of niet dan?", "Explosive arrows"),
    COWBOY("Cowboy", "Yeeeaaahaaa!!!", "Binnenkort..."),
    COOK("Kok", "Nom nom nom...", "Gooi eten"),
    PIRATE("Piraat","ARRRRRR...","Gooi schatten"),
    CLOWN("Clown","Nee deze is niet eng!", "Spuit water"),
    BUNNY("Konijn", "Mag ik een wortel?", "Gooi wortels"),
    CHICKEN("Kip", "Tok tok tok.", "Leg eieren");

    //COWBOY KOK PIRAAT CLOWN KONIJN KIP PAPEGAAI
    private String name;
    private String lore;
    private String ability;

    SuitsEnum(String s, String s1, String s2) {
        this.name = s;
        this.lore = s1;
        this.ability = s2;
    }

    public String getName() {
        return name;
    }

    public String getLore() {
        return lore;
    }

    public String getAbility() {
        return ability;
    }
}
