package com.example.azuredragon.http.presenter;

import android.content.Context;

import com.example.azuredragon.http.base.BaseEntry;
import com.example.azuredragon.http.base.BaseObserver;
import com.example.azuredragon.http.bean.ZiXunAll;
import com.example.azuredragon.http.contract.LoginContract;
import com.example.azuredragon.http.utils.MainUtil;
import com.example.azuredragon.http.utils.RetrofitUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */

public class LoginPresenter implements LoginContract.presenter {

    private Context context;
    private LoginContract.View view;

    public LoginPresenter(Context context, LoginContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getBookList() {
                RetrofitUtil.getInstance().initRetrofit().getBooData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<ZiXunAll>>(context,MainUtil.loadTxt) {
                    @Override
                    protected void onSuccees(BaseEntry<List<ZiXunAll>> t) throws Exception {
                        view.success("标题：" + t.getData().get(0).getTitle());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.fail("失败了----->"+e.getMessage());
                    }
                });

//                .subscribe(new BaseObserver<List<ZiXunAll>>(context,MainUtil.loadTxt) {
//
//                    @Override
//                    protected void onSuccees(BaseEntry<List<ZiXunAll>> t) throws Exception {
//                        view.setContent("标题：" + t.getData().get(0).getTitle());
//                    }
//
//                    @Override
//                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
//                        view.setContent("失败了----->"+e.getMessage());
//                    }
//                });
    }

//    public void userLogin(String user, String pwd, String code) {
//        Map<String,String> map=new HashMap<>();
//        map.put("userName",user);
//        map.put("userPwd",pwd);
//        map.put("verifyCode",code);
//        RetrofitUtil.getInstance().initRetrofit().userLogin(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseObserver<Login>(context,MainUtil.loadLogin) {
//                    @Override
//                    protected void onSuccees(BaseEntry<Login> t) throws Exception {
//                       if(t.isSuccess()){
//                           view.setContent("Hello---->"+t.getData().getName());
//                       }else {
//                           view.setContent("----->"+t.getMessage());
//                       }
//                    }
//
//                    @Override
//                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
//                        view.setContent("失败了----->"+e.getMessage());
//                    }
//                });
//    }
//
//    /**
//     * 获取banner
//     */
//    @Override
//    public void getBannerData() {
//        RetrofitUtil.getInstance().initRetrofit().getBanner()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseObserver<List<Banner>>(context,MainUtil.loadTxt) {
//                    @Override
//                    protected void onSuccees(BaseEntry<List<Banner>> t) throws Exception {
//                        MainUtil.printLogger(t.getData().get(0).getTitle());
//                        view.setContent(t.getData().get(0).getContent());
//                    }
//
//
//                    @Override
//                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
//                        view.setContent("失败了----->"+e.getMessage());
//                    }
//                });
//    }
//
    /**
     * 获取资讯
     */
    @Override
    public void getLiveData() {
        RetrofitUtil.getInstance().initRetrofit().getZixunData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<ZiXunAll>>(context,MainUtil.loadTxt) {
                    @Override
                    protected void onSuccees(BaseEntry<List<ZiXunAll>> t) throws Exception {
                        view.success("标题：" + t.getData().get(0).getTitle());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.fail("失败了----->"+e.getMessage());
                    }
                });
    }


}
