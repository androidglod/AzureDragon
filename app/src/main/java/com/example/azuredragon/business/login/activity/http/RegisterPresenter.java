package com.example.azuredragon.business.login.activity.http;

import android.content.Context;

import com.example.azuredragon.http.base.BaseEntry;
import com.example.azuredragon.http.base.BaseObserver;
import com.example.azuredragon.http.bean.BookDetailBean;
import com.example.azuredragon.http.bean.LoginBean;
import com.example.azuredragon.http.utils.MainUtil;
import com.example.azuredragon.http.utils.RetrofitUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */

public class RegisterPresenter implements RegisterContract.presenter {

    private Context context;
    private RegisterContract.View view;

    public RegisterPresenter(Context context, RegisterContract.View view) {
        this.context = context;
        this.view = view;
    }



    @Override
    public void goRegister(HashMap map) {
        RetrofitUtil.getInstance().initRetrofit().userRegister(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<LoginBean>(context,MainUtil.loadLogin) {
                    @Override
                    protected void onSuccees(BaseEntry<LoginBean> t) throws Exception {
                       if(t.isStatus()){
                           view.success(t.getData());
                       }else {
                           view.fail("----->"+t.getMessage());
                       }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.fail("失败了----->"+e.getMessage());
                    }
                });
    }

    @Override
    public void checkPhone(HashMap map) {
        RetrofitUtil.getInstance().initRetrofit().getCheckPhone(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver(context,"") {
                    @Override
                    protected void onSuccees(BaseEntry t) throws Exception {
                        if (t.isStatus()) {
                            view.checkPhonesuccess(t.getMessage());

                        }else{
                            view.checkPhonefail(t.getMessage());
                        }

                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.checkPhonefail("失败了----->"+e.getMessage());
                    }
                });

    }

}
