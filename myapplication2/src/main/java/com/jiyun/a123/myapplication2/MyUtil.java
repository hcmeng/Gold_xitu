package com.jiyun.a123.myapplication2;

import android.util.Log;
import android.widget.Toast;

import com.jiyun.a123.myapplication2.bean.ResultBean;
import com.jiyun.a123.myapplication2.dao.DaoSession;
import com.jiyun.a123.myapplication2.dao.ResultBeanDao;

import java.util.List;

/*
 Created by 郝超梦-H1810B on ${date}
*/public class MyUtil {

    //    private static DaoSession daoSession;
    private static final String TAG = "MyUtil";

    public static void insetOne(ResultBean resultBean) {
        DaoSession daoSession = MyApp.getDaoSession();
        if (queryOne(resultBean) == null) {
            daoSession.insert(resultBean);
            Log.d(TAG, "insetOne: 收藏成功");
        }
    }

    public static List <ResultBean> queryAll() {
        DaoSession daoSession = MyApp.getDaoSession();
        List <ResultBean> resultBeans = daoSession.loadAll(ResultBean.class);
        return resultBeans;
    }


    public static ResultBean queryOne(ResultBean resultBean) {
        DaoSession daoSession = MyApp.getDaoSession();
        ResultBean unique = daoSession.queryBuilder(ResultBean.class)
                .where(ResultBeanDao.Properties.Pic_small.eq(resultBean.getPic_small()))
                .build()
                .unique();

        return unique;
    }
}
