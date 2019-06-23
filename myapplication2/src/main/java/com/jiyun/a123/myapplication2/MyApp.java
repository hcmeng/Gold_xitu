package com.jiyun.a123.myapplication2;

import android.app.Application;

import com.jiyun.a123.myapplication2.dao.DaoMaster;
import com.jiyun.a123.myapplication2.dao.DaoSession;

/*
 Created by 郝超梦-H1810B on ${date}
*/public class MyApp extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "my.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
//        daoSession
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
