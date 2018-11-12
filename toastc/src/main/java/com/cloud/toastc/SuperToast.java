package com.cloud.toastc;

import android.app.Application;

import com.cloud.toastc.base.BaseToast;

/**
 * @author xb.zou
 * @date 2018/11/12
 * @desc 在通知栏权限打开下所使用的Toast
 **/
public class SuperToast extends BaseToast {

    public SuperToast(Application application) {
        super(application);
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
        super.show();
    }

    @Override
    public void cancel() {
        //移除显示的任务
        getHandler().removeCallbacks(this);
        //取消显示
        super.cancel();
    }
}
