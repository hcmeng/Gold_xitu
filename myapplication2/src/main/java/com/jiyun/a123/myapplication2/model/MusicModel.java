package com.jiyun.a123.myapplication2.model;

import android.util.Log;

import com.jiyun.a123.myapplication2.ApiService;
import com.jiyun.a123.myapplication2.bean.MusicBean;
import com.jiyun.a123.myapplication2.contract.MusicContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 Created by 郝超梦-H1810B on ${date}
*/   public class MusicModel implements MusicContract.Model {
    @Override
    public void modelData(final MusicContract.MusicCallBack musicCallBack) {
        new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiService.url)
                .build()
                .create(ApiService.class)
                .getMusic()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer <MusicBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MusicBean musicBean) {
                        musicCallBack.success(musicBean.getResult());
                        Log.d(TAG, "onNext: "+musicBean.getMessage());
                    }

                    @Override
                    public void onError(Throwable e) {
                        musicCallBack.failed(e.getMessage());
                        Log.d(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private static final String TAG = "MusicModel";
}
