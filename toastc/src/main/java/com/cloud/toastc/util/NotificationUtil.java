package com.cloud.toastc.util;

import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author xb.zou
 * @date 2018/11/12
 * @desc 通知类工具类
 **/
public class NotificationUtil {
    /**
     * 检测通知栏权限是否已经开启
     */
    public static boolean isNotificationEnable(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE))
                    .areNotificationsEnabled();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //通过反射来获取通知栏权限是否开启
            AppOpsManager appOpsManager
                    = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            ApplicationInfo appInfo = context.getApplicationInfo();
            String pkg = context.getApplicationContext().getPackageName();
            int uid = appInfo.uid;
            try {
                Class<?> appOpsClazz = Class.forName(AppOpsManager.class.getName());
                Method checkOpNoThrowMethod
                        = appOpsClazz.getMethod("checkOpNoThrow",
                        Integer.TYPE, Integer.TYPE, String.class);
                Field opPostNotificationValue
                        = appOpsClazz.getDeclaredField("OP_POST_NOTIFICATION");
                int value = (Integer) opPostNotificationValue.get(Integer.class);
                return (Integer)checkOpNoThrowMethod.invoke(appOpsManager, value, uid, pkg) == 0;
            } catch (Exception e) {
                return true;
            }
        }

        return true;
    }
}
