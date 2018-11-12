package com.cloud.toastc.util;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cloud.toastc.R;
import com.cloud.toastc.base.IToastStyle;

/**
 * @author xb.zou
 * @date 2018/11/12
 * @desc
 **/
public class ViewUtil {
    /**
     * 查找并返回ViewGroup中是否包含有TextView
     */
    public static TextView findTextView(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof TextView) {
                return (TextView) view;
            } else if (view instanceof ViewGroup) {
                TextView textView = findTextView((ViewGroup) view);
                if (textView != null) {
                    return textView;
                }
            }
        }
        return null;
    }

    /**
     * 根据Style创建一个新的TextView
     */
    public static TextView createTextViewWithStyle(Context context, IToastStyle style) {
        GradientDrawable drawable = new GradientDrawable();
        // 设置背景色
        drawable.setColor(style.getBackgroudColor());
        // 设置圆角
        drawable.setCornerRadius(dp2px(context, style.getCornerRadius()));

        TextView textView = new TextView(context);
        textView.setId(R.id.toast_main_text_view_id);
        textView.setTextColor(style.getTextColor());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, sp2px(context, style.getTextSize()));
        textView.setPadding(
                dp2px(context, style.getPaddingLeft()),
                dp2px(context, style.getPaddingTop()),
                dp2px(context, style.getPaddingRight()),
                dp2px(context, style.getPaddingBottom()));
        textView.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        // setBackground API版本兼容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView.setBackground(drawable);
        }else {
            textView.setBackgroundDrawable(drawable);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 设置 Z 轴阴影
            textView.setZ(style.getZ());
        }

        if (style.getMaxLine() > 0) {
            // 设置最大显示行数
            textView.setMaxLines(style.getMaxLine());
        }

        return textView;
    }

    /**
     * 将dp转换成px
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将sp转换成px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
