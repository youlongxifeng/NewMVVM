package com.android.youlong.mvvm.api;

import com.android.youlong.mvvm.api.cookie.CookiesManager;
import com.android.youlong.mvvm.api.interceptor.NetworkInterceptor;
import com.android.youlong.mvvm.base.BaseApplication;
import com.android.youlong.mvvm.common.Constant;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @version V1.0
 * @Title: ${FILE_NAME}
 * @Package com.android.youlong.mvvm.api
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @author: xiezhenggen
 * @date: 2018/9/5 11:28
 * @Copyright: 2018  xzg All rights reserved.
 */

public class ApiEngine {
    private volatile static ApiEngine apiEngine;
    private Retrofit retrofit;

    private ApiEngine() {
        HttpLoggingInterceptor.Logger mLog = new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //Log.i("TAG", "message==" + message);
            }
        };

        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //缓存
        int size = 1024 * 1024 * 100;
        File cacheFile = new File(BaseApplication.getContext().getCacheDir(), "OkHttpCache");
        Cache cache = new Cache(cacheFile, size);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .cookieJar(new CookiesManager())//在OkHttpClient创建时，传入这个CookieJar的实现，就能完成对Cookie的自动管理了
                .addNetworkInterceptor(new NetworkInterceptor())// 将有网络拦截器当做网络拦截器添加
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public static ApiEngine getInstance() {
        if (apiEngine == null) {
            synchronized (ApiEngine.class) {
                if (apiEngine == null) {
                    apiEngine = new ApiEngine();
                }
            }
        }
        return apiEngine;
    }

    public ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }
}
