package com.android.youlong.mvvm.api;

import com.android.youlong.mvvm.bean.Gank;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @version V1.0
 * @Title: ${FILE_NAME}
 * @Package com.android.youlong.mvvm.api
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @author: xiezhenggen
 * @date: 2018/9/5 11:29
 * @Copyright: 2018  xzg All rights reserved.
 */

public interface ApiService {
    //http://gank.io/api/data/Android/10/1
    @GET("api/data/Android/10/{page}")
    Observable<Gank> getGank(@Path("page") String page);
}
