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

package nl.HorizonCraft.PretparkCore.Bundles.Ping;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Cooltimmetje on 12/28/2015 at 11:16 AM.
 */
public enum SayingsEnum {

    FAT_FREE("Vet vrij!"),
    YES_FRIEND("JAAAA VRIEEEEEEND!"),
    FAABAAH("Faaabaaahhh Faabaaaah"),
    NO_FRIEND("NEEEE VRIEEEEENDDD!"),
    STICKER("Nu met een gratis sticker!"),
    RED_BUTTON("Druk niet op de rode knop!"),
    DISCORD("Discord is Bae <3"),
    COOKIE("KOEKJES!"),
    NOU_NEE("Nooouuu.... Nee."),
    HEALTHY("Nu 101% gezonder."),
    TREKKER("Tim en Bram hebben een trekker."),
    BUGS("Bevat bugs."),
    BUTTON("What does this button do?"),
    BMW("Dikke BMW jongens, DIKKE BMW."),
    FRIETJES("Gratis frietjes!"),
    MOTTO("Plezier tot aan de horizon en terug.");

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
