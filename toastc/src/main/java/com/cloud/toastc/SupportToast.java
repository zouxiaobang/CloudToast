package com.cloud.toastc;

import android.app.Application;

import com.cloud.toastc.base.BaseToast;
import com.cloud.toastc.util.ToastHelper;

/**
 * @author xb.zou
 * @date 2018/11/12
 * @desc 在没有通知栏权限情况下所使用的Toast
 **/
public class SupportToast extends BaseToast {
    /**
     * Toast辅助类
     */
    private ToastHelper mToastHelper;

    public SupportToast(Application application) {
        super(application);
        mToastHelper = new ToastHelper(this, application);
    }

    @Override
    public void show() {
        //移除上一个显示的任务
        getHandler().removeCallbacks(this);
        //添加新的显示任务
        getHandler().postDelayed(this, SHOW_DELAY_MILLIS);
    }

    @Override
    public void run() {
        //将文本显示到View上
        getMessageView().setText(getMessage());
        //开始显示
        mToastHelper.show();
    }

    @Override
    public void cancel() {
        //移除显示的任务
        getHandler().removeCallbacks(this);
        //取消显示
        mToastHelper.cancel();
    }
}

