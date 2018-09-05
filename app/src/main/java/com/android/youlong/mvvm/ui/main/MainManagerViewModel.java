package com.android.youlong.mvvm.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.android.youlong.mvvm.bean.Gank;
import com.android.youlong.mvvm.bean.Result;
import com.android.youlong.mvvm.rx.RxSchedulers;
import com.android.youlong.mvvm.utils.LogUtils;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * @version V1.0
 * @Title: ${FILE_NAME}
 * @Package com.android.youlong.mvvm
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @author: xiezhenggen
 * @date: 2018/9/5 9:46
 * @Copyright: 2018  xzg All rights reserved.
 */

public class MainManagerViewModel extends ViewModel{
    /**
     *
     */
    private MutableLiveData<List<Result>> mData = new MutableLiveData<>();

    /**
     *
     */
    public ObservableList<Result> communities= new ObservableArrayList<>();


    public void getGank(){
        DisposableObserver<Gank> mObserver = getDisposableObserver();
        MainModel.getGank().compose(RxSchedulers.<Gank>switchObservableThread())
                .subscribe(mObserver);
        addSubscribe(mObserver);
    }

    private CompositeDisposable mCompositeSubscription;// 每当我们得到一个Disposable时就调用CompositeDisposable.add()将它添加到容器中,
    // 在退出的时候, 调用CompositeDisposable.clear() 即可切断所有的水管.

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeDisposable();
        }
        mCompositeSubscription.add(subscription);
    }


    private DisposableObserver<Gank> getDisposableObserver() {
        return new DisposableObserver<Gank>() {
            @Override
            public void onNext(Gank aBoolean) {
                LogUtils.i("TAG","aBoolean==="+aBoolean);
                mData.setValue(aBoolean.getResults());
                communities.addAll(aBoolean.getResults());


            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
