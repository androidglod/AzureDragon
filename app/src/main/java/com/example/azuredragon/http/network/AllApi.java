package com.example.azuredragon.http.network;


import com.example.azuredragon.http.ApiAddress;
import com.example.azuredragon.http.base.BaseEntry;
import com.example.azuredragon.http.base.BaseListEntry;
import com.example.azuredragon.http.bean.Banner;
import com.example.azuredragon.http.bean.BookDetailBean;
import com.example.azuredragon.http.bean.LoginBean;
import com.example.azuredragon.http.bean.ZiXunAll;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */

public interface AllApi {

    /**
     * 获取banner
     */
    @GET(ApiAddress.getBannerList)
    Observable<BaseListEntry<Banner>> getBanner();

    /**
     * 最新书籍列表
     */
    @GET("works/findWorksList.do")
    Observable<BaseListEntry<BookDetailBean>> getBookListData(@QueryMap() Map<String, String> maps);

    /**
     * 最新资讯
     */
    @GET("mobile/listArticles.do")
    Observable<BaseListEntry<ZiXunAll>> getZixunData();
    /**
     * 获取图片验证码
     */
    @GET(ApiAddress.getVerifyCode)
    Observable<ResponseBody> getVerityCode();

    /**
     * 登录
     */
    @POST(ApiAddress.userLogin)
    Observable<BaseEntry<LoginBean>> userLogin(@QueryMap Map<String, String> maps);

    /**
     * 注册
     */
    @POST(ApiAddress.userRegister)
    Observable<BaseEntry<LoginBean>> userRegister(@QueryMap Map<String, String> maps);

    /**
     * 最新书籍列表
     */
    @GET("reader/checkReader.do")
    Observable<BaseEntry> getCheckPhone(@QueryMap() Map<String, String> maps);


}
