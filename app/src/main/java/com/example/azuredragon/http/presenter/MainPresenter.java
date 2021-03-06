package com.example.azuredragon.http.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.azuredragon.http.base.BaseEntry;
import com.example.azuredragon.http.base.BaseListEntry;
import com.example.azuredragon.http.base.BaseObserver;
import com.example.azuredragon.http.bean.Banner;
import com.example.azuredragon.http.bean.LoginBean;
import com.example.azuredragon.http.bean.ZiXunAll;
import com.example.azuredragon.http.module.MainContract;
import com.example.azuredragon.http.utils.MainUtil;
import com.example.azuredragon.http.utils.RetrofitUtil;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */

public class MainPresenter implements MainContract.presenter {

    private Context context;
    private MainContract.View view;

    public MainPresenter(Context context, MainContract.View view) {
        this.context = context;
        this.view = view;
    }

    /**
     * 图片验证码
     */
    @Override
    public void getCode() {
        RetrofitUtil.getInstance().initRetrofit().getVerityCode().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ResponseBody value) {
                InputStream is=value.byteStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                view.setCode(bitmap);
            }

            @Override
            public void onError(Throwable e) {
                view.setContent("失败了----->"+e.getMessage());
            }

            @Override
            public void onComplete() {
            }
        });
    }

    /**
     * 登录
     * @param user
     * @param pwd
     * @param code
     */
    @Override
    public void userLogin(String user, String pwd, String code) {
        Map<String,String> map=new HashMap<>();
        map.put("userName",user);
        map.put("userPwd",pwd);
        map.put("verifyCode",code);
        RetrofitUtil.getInstance().initRetrofit().userLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseEntry<LoginBean>>(context,MainUtil.loadLogin) {
                    @Override
                    protected void onSuccees(BaseEntry<LoginBean> t) throws Exception {
                       if(t.isStatus()){
                           view.setContent("Hello---->"+t.getMessage());
                       }else {
                           view.setContent("----->"+t.getMessage());
                       }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setContent("失败了----->"+e.getMessage());
                    }
                });
    }

    /**
     * 获取banner
     */
    @Override
    public void getBannerData() {
        RetrofitUtil.getInstance().initRetrofit().getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseListEntry<Banner>>(context,MainUtil.loadTxt) {
                    @Override
                    protected void onSuccees(BaseListEntry<Banner> t) throws Exception {
                        MainUtil.printLogger(t.getData().get(0).getTitle());
                        view.setContent(t.getData().get(0).getContent());
                    }


                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setContent("失败了----->"+e.getMessage());
                    }
                });
    }

    /**
     * 获取资讯
     */
    @Override
    public void getLiveData() {
        RetrofitUtil.getInstance().initRetrofit().getZixunData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseListEntry<ZiXunAll>>(context,MainUtil.loadTxt) {

                    @Override
                    protected void onSuccees(BaseListEntry<ZiXunAll> ziXunAllBaseListEntry) throws Exception {
                        view.setContent("标题：" + ziXunAllBaseListEntry.getData().get(0).getTitle());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setContent("失败了----->"+e.getMessage());
                    }
                });
    }
}
