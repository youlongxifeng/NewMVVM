package com.android.youlong.mvvm.base;

import android.app.Application;

/**
 * @version V1.0
 * @Title: ${FILE_NAME}
 * @Package com.android.youlong.mvvm.base
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @author: xiezhenggen
 * @date: 2018/9/5 11:32
 * @Copyright: 2018  xzg All rights reserved.
 */

public class BaseApplication extends Application {
    private static BaseApplication mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
    public static BaseApplication getInstance() {
        return mContext;
    }
    public static BaseApplication getContext() {
        return mContext;
    }
}
