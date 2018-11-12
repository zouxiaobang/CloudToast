package com.cloud.toastc;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.cloud.toastc.base.IToastStyle;
import com.cloud.toastc.style.ToastBlackStyle;
import com.cloud.toastc.util.NotificationUtil;
import com.cloud.toastc.util.ViewUtil;

/**
 * @author xb.zou
 * @date 2018/11/12
 * @desc
 **/
public class ToastUtil {
    private static final int LONG_TEXT_LENGTH = 20;

    private static IToastStyle sToastStyle;
    private static Toast sToast;

    /**
     * 初始化ToastUtil，建议在Application类中进行初始化
     */
    public static void initToast(Application application) {
        //判断Toast的样式是否为空，如果为空，则默认使用透明黑样式
        if (sToastStyle == null) {
            sToastStyle = new ToastBlackStyle();
        }

        if (NotificationUtil.isNotificationEnable(application)) {
            sToast = new SuperToast(application);
        } else {
            sToast = new SupportToast(application);
        }

        sToast.setGravity(sToastStyle.getGravity(), sToastStyle.getXOffset(), sToastStyle.getYOffset());
        sToast.setView(createTextView(application));
    }

    /**
     * 显示Toast
     */
    public static void show(CharSequence text) {
        checkToastState();

        if (text == null || "".contentEquals(text)) {
            return;
        }

        //自定义功能
        //如果Toast的内容超过10个字，就显示长Toast，否则显示短Toast
        if (text.length() >= LONG_TEXT_LENGTH) {
            sToast.setDuration(Toast.LENGTH_LONG);
        } else {
            sToast.setDuration(Toast.LENGTH_SHORT);
        }

        sToast.setText(text);
        sToast.show();
    }

    public static void show(CharSequence text, int duration) {
        checkToastState();

        if (text == null || "".contentEquals(text)) {
            return;
        }

        sToast.setDuration(duration);
        sToast.setText(text);
        sToast.show();
    }

    /**
     * 显示string资源中的字符串
     */
    public static void show(int id) {
        checkToastState();

        try {
            show(sToast.getView().getContext().getResources().getString(id));
        } catch (Exception e) {
            show(String.valueOf(id));
        }
    }

    /**
     * 显示一个对象的Toast
     */
    public static void show(Object obj) {
        show(obj != null ? obj.toString() : "null");
    }

    /**
     * 取消显示Toast
     */
    public static void cancel() {
        checkToastState();

        sToast.cancel();
    }

    /**
     * 获取当前Toast对象
     */
    public static Toast getToast() {
        return sToast;
    }

    /**
     * 自定义布局
     */
    public static void setView(View view) {
        checkToastState();

        if (view == null) {
            throw new IllegalArgumentException("请传入您自定义的布局.");
        }

        if (sToast != null) {
            //先取消原先的Toast显示
            sToast.cancel();
            sToast.setView(view);
        }
    }

    /**
     * 自定义布局
     */
    public static void setView(Context context, int layoutId) {
        if (context != context.getApplicationContext()) {
            context = context.getApplicationContext();
        }

        setView(View.inflate(context, layoutId, null));
    }

    /**
     * 统一全局的样式
     * 建议在Application中设置
     */
    public static void initStyle(IToastStyle style) {
        sToastStyle = style;
        if (sToast != null) {
            sToast.cancel();
            sToast.setView(createTextView(sToast.getView().getContext().getApplicationContext()));
        }
    }


    /**
     * 创建一个TextView，用以显示Toast文本
     */
    private static View createTextView(Context context) {
        return ViewUtil.createTextViewWithStyle(context, sToastStyle);
    }

    private static void checkToastState() {
        if (sToast == null) {
            throw new IllegalStateException("请先初始化ToastUtil.");
        }
    }
}
