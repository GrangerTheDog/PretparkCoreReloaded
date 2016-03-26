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

package nl.HorizonCraft.PretparkCore.Bundles.Ping;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Cooltimmetje on 12/28/2015 at 11:16 AM.
 */
public enum SayingsEnum {

    FAT_FREE        ("Vet vrij!"),
    YES_FRIEND      ("JAAAA VRIEEEEEEND!"),
    FAABAAH         ("Faaabaaahhh Faabaaaah"),
    NO_FRIEND       ("NEEEE VRIEEEEENDDD!"),
    STICKER         ("Nu met een gratis sticker!"),
    RED_BUTTON      ("Druk niet op de rode knop!"),
    DISCORD         ("Discord is Bae <3"),
    COOKIE          ("KOEKJES!"),
    NOU_NEE         ("Nooouuu.... Nee."),
    HEALTHY         ("Nu 101% gezonder."),
    TREKKER         ("Tim en Bram hebben een trekker."),
    BUGS            ("Bevat bugs."),
    BUTTON          ("What does this button do?"),
    BMW             ("Dikke BMW jongens, DIKKE BMW."),
    FRIETJES        ("Gratis frietjes!"),
    MOTTO           ("Plezier tot aan de horizon en terug."),
    SOKKEN          ("Niet okken of ik steel je sokken."),
    TEAMAF          ("#TeamAfzuigkap"),
    AFBAE           ("Remember kids: \"Afzuigkap is BAE! <3\""),
    MOOI            ("ik vind het illemal moi"),
    TIM_BAE         ("Tim is Bae <3"),
    KOALA_MARRY     ("Koala's trouwen niet."),
    MAND            ("MAND!"),
    KIPPEN          ("Wij zijn niet verantwoordelijk voor dode kippen!"),
    BANAAN          ("Ontmoet nu de dansende banaan!");

    private String saying;

    SayingsEnum(String s) {
        this.saying = s;
    }

    public String getSaying() {
        return saying;
    }

    private static final List<SayingsEnum> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static SayingsEnum randomMessage()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
