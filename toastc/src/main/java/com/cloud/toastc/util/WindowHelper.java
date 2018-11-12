package com.cloud.toastc.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

/**
 * @author xb.zou
 * @date 2018/11/12
 * @desc WindowManager辅助类，
 * 使用Application来绑定Activity生命周期的方法来让Toast可以显示在所有Activity上，
 * 而不局限于当前Activity。
 **/
public class WindowHelper implements Application.ActivityLifecycleCallbacks{
    /** 当前Activity的WindowManager*/
    private WindowManager mWindowManager;
    /** 当前Activity的对象标记*/
    private String mCurrentTag;

    public WindowHelper(Application application){
        application.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mCurrentTag = getObjectTag(activity);
        mWindowManager = getActivityWindowManager(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) { }

    @Override
    public void onActivityResumed(Activity activity) {
        mCurrentTag = getObjectTag(activity);
        mWindowManager = getActivityWindowManager(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) { }

    @Override
    public void onActivityStopped(Activity activity) { }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) { }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (getObjectTag(activity).equals(mCurrentTag)){
            //防止内存泄漏
            //移除当前Activity的WindowManager
            mWindowManager = null;
            mCurrentTag = null;
        }
    }

    /**
     * 获取当前Activity的WindowManager
     */
    public WindowManager getWindowManager(){
        return mWindowManager;
    }

    /**
     * 获取一个对象的独立无二的标记
     */
    private String getObjectTag(Object object) {
        // 对象所在的包名 + 对象的内存地址
        return object.getClass().getName() + Integer.toHexString(object.hashCode());
    }

    /**
     * 根据Activity来获取WindowManager
     * 如果该WindowManger对象不是当前Activity的，将会报错
     */
    private WindowManager getActivityWindowManager(Activity activity){
        return (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
    }
}
