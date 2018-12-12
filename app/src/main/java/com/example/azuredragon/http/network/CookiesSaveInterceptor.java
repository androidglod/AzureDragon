package com.example.azuredragon.http.network;


import com.example.azuredragon.http.app.MyApp;
import com.example.azuredragon.http.utils.SharePreferencesUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:读取cookie
 */

public class CookiesSaveInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            String header =originalResponse.header("Set-Cookie");
            SharePreferencesUtils.setString(MyApp.myApp,"cookiess",header);
        }
        return originalResponse;
    }

}
