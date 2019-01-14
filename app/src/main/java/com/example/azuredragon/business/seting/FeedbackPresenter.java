package com.example.azuredragon.business.seting;

import android.content.Context;

import com.example.azuredragon.http.base.BaseEntry;
import com.example.azuredragon.http.base.BaseObserver;
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

public class FeedbackPresenter implements FeedbackContract.presenter {

    private Context context;
    private FeedbackContract.View view;

    public FeedbackPresenter(Context context, FeedbackContract.View view) {
        this.context = context;
        this.view = view;
    }



    @Override
    public void goFeedback(HashMap map) {
        RetrofitUtil.getInstance().initRetrofit().getFeedbackData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseEntry>(context,MainUtil.loadLogin) {
                    @Override
                    protected void onSuccees(BaseEntry t) throws Exception {
                       if(t.isStatus()){
                           view.success();
                       }else {
                           view.fail();
                       }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.fail();
                    }
                });
    }



}
