package com.example.azuredragon.http.network;


import com.example.azuredragon.http.ApiAddress;
import com.example.azuredragon.http.base.BaseEntry;
import com.example.azuredragon.http.bean.Banner;
import com.example.azuredragon.http.bean.LibraryBean;
import com.example.azuredragon.http.bean.Login;
import com.example.azuredragon.http.bean.ZiXunAll;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
    Observable<BaseEntry<List<Banner>>> getBanner();

    /**
     * 最新书籍列表
     */
    @GET("works/findWorksList.do")
    Observable<BaseEntry<List<LibraryBean>>> getBookListData();

    /**
     * ces
     */
    @GET("works/findWorksList.do")
    Observable<BaseEntry<List<ZiXunAll>>> getBooData();
    /**
     * 最新资讯
     */
    @GET("mobile/listArticles.do")
    Observable<BaseEntry<List<ZiXunAll>>> getZixunData();
    /**
     * 获取图片验证码
     */
    @GET(ApiAddress.getVerifyCode)
    Observable<ResponseBody> getVerityCode();

    /**
     * 登录
     */
    @POST(ApiAddress.userLogin)
    Observable<BaseEntry<Login>> userLogin(@Body Map<String, String> maps);
}
