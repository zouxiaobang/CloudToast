package com.cloud.toastc.style;

import android.view.Gravity;

import com.cloud.toastc.base.IToastStyle;

/**
 * @author xb.zou
 * @date 2018/11/12
 * @desc
 **/
public class ToastBlackStyle implements IToastStyle {
    @Override
    public int getGravity() {
        return Gravity.CENTER;
    }

    @Override
    public int getXOffset() {
        return 0;
    }

    @Override
    public int getYOffset() {
        return 0;
    }

    @Override
    public int getZ() {
        return 30;
    }

    @Override
    public int getCornerRadius() {
        return 6;
    }

    @Override
    public int getBackgroudColor() {
        return 0X88000000;
    }

    @Override
    public int getTextColor() {
        return 0XEEFFFFFF;
    }

    @Override
    public int getTextSize() {
        return 14;
    }

    @Override
    public int getMaxLine() {
        return 3;
    }

    @Override
    public int getPaddingLeft() {
        return 24;
    }

    @Override
    public int getPaddingTop() {
        return 16;
    }

    @Override
    public int getPaddingRight() {
        return getPaddingLeft();
    }

    @Override
    public int getPaddingBottom() {
        return getPaddingTop();
    }
}
