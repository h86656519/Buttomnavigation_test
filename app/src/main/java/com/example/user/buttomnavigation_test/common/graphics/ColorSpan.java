/*
 *  ===========================================================================
 *  * QuMedia Confidential
 *  *
 *  * (C) Copyright QuMedia Corp. 2014.
 *  * ===========================================================================
 */

package com.example.user.buttomnavigation_test.common.graphics;

import android.os.Parcel;
import android.text.TextPaint;
import android.text.style.CharacterStyle;

/**
 * <p>ColorSpan
 * <p/>
 * </p>
 *
 * @author brian
 * @version 2012/11/21上午 11:20
 * @see
 */
public class ColorSpan extends CharacterStyle {

    private int color;

    public ColorSpan(int color) {
        this.color=color;
    }

    public ColorSpan(Parcel src) {
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(color);
    }
}
