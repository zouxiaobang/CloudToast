package com.cloud.cloudtoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cloud.toastc.ToastUtil;
import com.cloud.toastc.base.IToastStyle;
import com.cloud.toastc.style.ToastBlackStyle;
import com.cloud.toastc.style.ToastWhiteStyle;

public class MainActivity extends AppCompatActivity {
    private int mStyle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDefault(View view) {
        mStyle = 0;
        ToastUtil.initStyle(new ToastBlackStyle());
        ToastUtil.show("我是默认样式");
    }

    public void showWhite(View view) {
        mStyle = 1;
        ToastUtil.initStyle(new ToastWhiteStyle());
        ToastUtil.show("我是白色底样式");
    }

    public void showCustom(View view) {
        ToastUtil.setView(this, R.layout.toast_custom);
        ToastUtil.show("我是自定义样式");
    }

    public void showInThread(View view) {
        new Thread(){
            @Override
            public void run() {
                ToastUtil.show("我是在子线程中显示的");
            }
        }.start();
    }

    public void showDynamic(View view) {
        if (mStyle == 0){
            mStyle = 1;
            ToastUtil.initStyle(new ToastWhiteStyle());
        } else {
            mStyle = 0;
            ToastUtil.initStyle(new ToastBlackStyle());
        }
        ToastUtil.show("动态切换样式");
    }
}
