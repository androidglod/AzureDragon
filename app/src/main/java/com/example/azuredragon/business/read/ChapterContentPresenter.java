package com.example.azuredragon.business.read;

import android.content.Context;

import com.example.azuredragon.business.bookdetail.ChapterListContract;
import com.example.azuredragon.http.base.BaseEntry;
import com.example.azuredragon.http.base.BaseObserver;
import com.example.azuredragon.http.bean.BookChapterContentBean;
import com.example.azuredragon.http.bean.ChapterListBean;
import com.example.azuredragon.http.utils.MainUtil;
import com.example.azuredragon.http.utils.RetrofitUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author: chz.
 * @date: 2018/11/25
 * @description:章节内容请求接口
 */

public class ChapterContentPresenter implements ChapterContentContract.presenter {

    private Context context;
    private ChapterContentContract.View view;

    public ChapterContentPresenter(Context context, ChapterContentContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getChapterContent(HashMap map) {
        RetrofitUtil.getInstance().initRetrofit().getBookContentData(map)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new BaseObserver<BaseEntry>(context,null) {


            @Override
            protected void onSuccees(BaseEntry mChapterListBean) throws Exception {
                view.success(mChapterListBean.getData().toString());
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                view.fail("失败了----->"+e.getMessage());
            }
        });

    }

}
