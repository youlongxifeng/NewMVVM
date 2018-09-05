package com.android.youlong.mvvm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @Title: ${FILE_NAME}
 * @Package com.android.youlong.mvvm.bean
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @author: xiezhenggen
 * @date: 2018/9/5 11:30
 * @Copyright: 2018  xzg All rights reserved.
 */


public class Gank implements Parcelable {
    private boolean error;

    /**
     * _id : 58006bb3421aa95dd351b12a
     * createdAt : 2016-10-14T13:22:59.462Z
     * desc : 效果超棒的微笑下拉刷新。这是在 SwipeRefreshLayout基础上修改的下拉刷新库。
     * images : ["http://img.gank.io/616cb9ae-2d42-4f97-a7c9-28c291c7b66f"]
     * publishedAt : 2016-10-28T11:29:36.510Z
     * source : web
     * type : Android
     * url : https://github.com/songixan/SmileRefresh
     * used : true
     * who : null
     */

    private List<Result> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.error ? (byte) 1 : (byte) 0);
        dest.writeList(this.results);
    }

    public Gank() {
    }

    protected Gank(Parcel in) {
        this.error = in.readByte() != 0;
        this.results = new ArrayList<Result>();
        in.readList(this.results, Result.class.getClassLoader());
    }

    public static final Creator<Gank> CREATOR = new Creator<Gank>() {
        @Override
        public Gank createFromParcel(Parcel source) {
            return new Gank(source);
        }

        @Override
        public Gank[] newArray(int size) {
            return new Gank[size];
        }
    };

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Gank{");
        sb.append("error=").append(error);
        sb.append(", results=").append(results);
        sb.append('}');
        return sb.toString();
    }
}
