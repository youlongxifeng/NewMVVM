package com.android.youlong.mvvm.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @version V1.0
 * @Title: ${FILE_NAME}
 * @Package com.android.youlong.mvvm.utils
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @author: xiezhenggen
 * @date: 2018/9/5 11:36
 * @Copyright: 2018  xzg All rights reserved.
 */

public class NetWorkUtil {
    public   NetWorkUtil() {/* cannot be instantiated */}

    /**
     * 判断网络是否连接
     *
     * @param context Context
     * @return 网络是否连接
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }
}
