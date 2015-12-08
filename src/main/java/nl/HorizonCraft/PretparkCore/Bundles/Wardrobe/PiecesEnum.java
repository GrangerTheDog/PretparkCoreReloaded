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

package nl.HorizonCraft.PretparkCore.Bundles.Wardrobe;

/**
 * This class has been created on 10/27/2015 at 10:32 by Cooltimmetje.
 */
public enum PiecesEnum {

    NINJA_H(0, SuitsEnum.NINJA),
    NINJA_C(1, SuitsEnum.NINJA),
    NINJA_L(2, SuitsEnum.NINJA),
    NINJA_B(3, SuitsEnum.NINJA),

    OLAF_H(4, SuitsEnum.OLAF),
    OLAF_C(5, SuitsEnum.OLAF),
    OLAF_L(6, SuitsEnum.OLAF),
    OLAF_B(7, SuitsEnum.OLAF),

    LOVE_H(8, SuitsEnum.LOVE),
    LOVE_C(9, SuitsEnum.LOVE),
    LOVE_L(10, SuitsEnum.LOVE),
    LOVE_B(11, SuitsEnum.LOVE),

    VAMPIRE_H(12, SuitsEnum.VAMPIRE),
    VAMPIRE_C(13, SuitsEnum.VAMPIRE),
    VAMPIRE_L(14, SuitsEnum.VAMPIRE),
    VAMPIRE_B(15, SuitsEnum.VAMPIRE),

    SKELETON_H(16, SuitsEnum.SKELETON),
    SKELETON_C(17, SuitsEnum.SKELETON),
    SKELETON_L(18, SuitsEnum.SKELETON),
    SKELETON_B(19, SuitsEnum.SKELETON);

    private int id;

    PiecesEnum(int i, SuitsEnum skeleton) {
        this.id = i;
    }

}
