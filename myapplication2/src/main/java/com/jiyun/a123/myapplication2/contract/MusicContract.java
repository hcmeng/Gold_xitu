package com.jiyun.a123.myapplication2.contract;

import com.jiyun.a123.myapplication2.bean.ResultBean;

import java.util.List;

/*
 Created by 郝超梦-H1810B on ${date}
*/ public interface MusicContract {
    interface Model {
        void modelData(MusicCallBack musicCallBack);
    }

    interface View {
        void success(List<ResultBean> resultBeans);
        void failed(String string);
    }

    interface Presenter {
        void preData();
    }

    interface MusicCallBack{
        void success(List<ResultBean> resultBeans);
        void failed(String string);
    }
}
