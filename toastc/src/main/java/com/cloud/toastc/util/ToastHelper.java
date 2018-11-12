package com.cloud.toastc.util;

import android.app.Application;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.Toast;

import com.cloud.toastc.R;

/**
 * @author xb.zou
 * @date 2018/11/12
 * @desc Toast显示辅助类
 **/
public class ToastHelper implements Runnable {
    /**
     * 短Toast显示时间
     */
    private static final long SHORT_TIME_DELAY = 2000;
    /**
     * 长Toast显示时间
     */
    private static final long LONG_TIME_DELAY = 4000;

    private final Handler mHandler = new Handler(Looper.getMainLooper());
    /**
     * 当前的Toast对象
     */
    private final Toast mToast;
    /**
     * WindowManager辅助类
     */
    private final WindowHelper mWindowHelper;

    public ToastHelper(Toast toast, Application application) {
        mToast = toast;
        mWindowHelper = new WindowHelper(application);
    }

    @Override
    public void run() {
        cancel();
    }

    /**
     * 显示Toast
     */
    public void show() {
        if (mWindowHelper.getWindowManager() != null) {
            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && Settings.canDrawOverlays(mToast.getView().getContext())) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                } else {
                    params.type = WindowManager.LayoutParams.TYPE_PHONE;
                }
            }

            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.format = PixelFormat.TRANSLUCENT;
            params.windowAnimations = R.style.ToastAnimation;
            params.setTitle(Toast.class.getSimpleName());
            params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            params.gravity = mToast.getGravity();
            params.x = mToast.getXOffset();
            params.y = mToast.getYOffset();

            try {
                mWindowHelper.getWindowManager().addView(mToast.getView(), params);
            } catch (Exception e) {

            }

            //去掉上一个移除Toast的任务
            mHandler.removeCallbacks(this);
            //添加一个移除上一个Toast的任务
            mHandler.postDelayed(this,
                    mToast.getDuration() == Toast.LENGTH_LONG ? LONG_TIME_DELAY : SHORT_TIME_DELAY);

        }
    }

    public void cancel() {
        if (mWindowHelper.getWindowManager() != null) {
            try {
                mWindowHelper.getWindowManager().removeView(mToast.getView());
            } catch (Exception e) {

            }
        }
    }
}
