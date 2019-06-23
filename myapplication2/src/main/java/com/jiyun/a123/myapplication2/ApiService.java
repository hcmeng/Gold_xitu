package com.jiyun.a123.myapplication2;

import com.jiyun.a123.myapplication2.bean.MusicBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/*
 Created by 郝超梦-H1810B on ${date}
*/public interface ApiService {
    String url = "https://api.apiopen.top/";
    @GET("musicRankingsDetails?type=1")
    Observable<MusicBean> getMusic();
}
