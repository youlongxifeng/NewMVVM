package com.android.youlong.mvvm.ui.main;

import com.android.youlong.mvvm.api.ApiEngine;
import com.android.youlong.mvvm.bean.Gank;

import io.reactivex.Observable;

/**
 * @version V1.0
 * @Title: ${FILE_NAME}
 * @Package com.android.youlong.mvvm.ui.main
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @author: xiezhenggen
 * @date: 2018/9/5 11:39
 * @Copyright: 2018  xzg All rights reserved.
 */

public class MainModel {

    public static Observable<Gank> getGank() {
        return ApiEngine.getInstance().getApiService()
                .getGank("1");

    }
}
