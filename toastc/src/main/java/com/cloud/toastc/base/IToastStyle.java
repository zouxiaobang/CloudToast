package com.cloud.toastc.base;

/**
 * @author xb.zou
 * @date 2018/11/12
 * @desc Toast的默认样式接口
 **/
public interface IToastStyle {
    /** Toast重心 **/
    int getGravity();
    /** Toast在X轴上的偏移 **/
    int getXOffset();
    /** Toast在Y轴上的偏移 **/
    int getYOffset();
    /** Toast的Z轴 **/
    int getZ();

    /** Toast圆角大小 **/
    int getCornerRadius();
    /** Toast背景颜色 **/
    int getBackgroudColor();
    /** Toast文字颜色 **/
    int getTextColor();
    /** Toast文字大小 **/
    int getTextSize();
    /** Toast显示的最大行数 **/
    int getMaxLine();

    /** Toast左边距 **/
    int getPaddingLeft();
    /** Toast上边距 **/
    int getPaddingTop();
    /** Toast右边距 **/
    int getPaddingRight();
    /** Toast下边距 **/
    int getPaddingBottom();
}
