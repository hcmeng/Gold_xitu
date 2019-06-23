package com.jiyun.a123.myapplication2.presenter;

import com.jiyun.a123.myapplication2.MainActivity;
import com.jiyun.a123.myapplication2.bean.ResultBean;
import com.jiyun.a123.myapplication2.contract.MusicContract;
import com.jiyun.a123.myapplication2.model.MusicModel;

import java.util.List;

/*
 Created by 郝超梦-H1810B on ${date}
*/   public class MusicPresenter implements MusicContract.Presenter {
    MusicContract.View view;
    public MusicPresenter(MusicContract.View mainActivity) {
        this.view = mainActivity;
    }



    @Override
    public void preData() {
        new MusicModel().modelData(new MusicContract.MusicCallBack() {
            @Override
            public void success(List<ResultBean> resultBeans) {
                view.success(resultBeans);
            }

            @Override
            public void failed(String string) {
                view.failed(string);
            }
        });
    }
}
