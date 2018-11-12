package com.cloud.toastc.base;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cloud.toastc.R;
import com.cloud.toastc.util.ViewUtil;

/**
 * @author xb.zou
 * @date 2018/11/12
 * @desc Toast的基类
 **/
public abstract class BaseToast extends Toast implements Runnable {
    /**
     * 避免重复点击所使用的延迟时间
     **/
    protected static final int SHOW_DELAY_MILLIS = 300;

    /**
     * Toast显示都在主线程中显示
     **/
    private Handler mHandler = new Handler(Looper.getMainLooper());
    /**
     * Toast显示内容的View
     **/
    private TextView mTvMessage;
    /**
     * Toast显示的文本
     **/
    private CharSequence mMessage;

    /**
     * 必备的构造方法
     */
    public BaseToast(Application application) {
        super(application);
    }

    @Override
    public void setView(View view) {
        super.setView(view);

        if (view instanceof TextView) {
            mTvMessage = (TextView) view;
            return;
        } else if (view.findViewById(R.id.toast_main_text_view_id) instanceof TextView) {
            mTvMessage = view.findViewById(R.id.toast_main_text_view_id);
            return;
        } else if (view instanceof ViewGroup) {
            if ((mTvMessage = ViewUtil.findTextView((ViewGroup) view)) != null) {
                return;
            }
        }

        throw new IllegalArgumentException("Toast布局中必须包含一个TextView。");
    }

    @Override
    public void setText(CharSequence sequence) {
        mMessage = sequence;
    }

    /**
     * 获取当前的Handler
     */
    public Handler getHandler() {
        return mHandler;
    }

    /**
     * 获取当前显示文本的View
     */
    public TextView getMessageView() {
        return mTvMessage;
    }

    /**
     * 获取显示的文本
     */
    public CharSequence getMessage() {
        return mMessage;
    }
}
