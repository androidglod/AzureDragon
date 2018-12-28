package com.example.azuredragon.business.BookList;

import android.content.Context;

import com.example.azuredragon.http.base.BaseEntry;
import com.example.azuredragon.http.base.BaseListEntry;
import com.example.azuredragon.http.base.BaseObserver;
import com.example.azuredragon.http.bean.BookDetailBean;
import com.example.azuredragon.http.utils.MainUtil;
import com.example.azuredragon.http.utils.RetrofitUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author: chz.
 * @date: 2018/11/25
 * @description:书籍列表请求接口
 */

public class BookListPresenter implements BookListContract.presenter {

    private Context context;
    private BookListContract.View view;

    public BookListPresenter(Context context, BookListContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getBookList(HashMap map) {
        RetrofitUtil.getInstance().initRetrofit().getBookListData(map)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new BaseObserver<BaseListEntry<BookDetailBean>>(context,MainUtil.loadTxt) {


            @Override
            protected void onSuccees(BaseListEntry<BookDetailBean> bookDetailBeanBaseListEntry) throws Exception {
                view.success(bookDetailBeanBaseListEntry.getData());
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                view.fail("失败了----->"+e.getMessage());
            }
        });

    }

    private final LinkedHashMap<String,String> kinds = new LinkedHashMap<>();
    public LinkedHashMap<String,String>  bookListTabelPresenterImpl() {
        kinds.put("东方玄幻","http://www.gxwztv.com/xuanhuanxiaoshuo/");
        kinds.put("西方奇幻","http://www.gxwztv.com/qihuanxiaoshuo/");
        kinds.put("热血修真","http://www.gxwztv.com/xiuzhenxiaoshuo/");
        kinds.put("武侠仙侠","http://www.gxwztv.com/wuxiaxiaoshuo/");
        kinds.put("都市爽文","http://www.gxwztv.com/dushixiaoshuo/");
        kinds.put("言情暧昧","http://www.gxwztv.com/yanqingxiaoshuo/");
        kinds.put("灵异悬疑","http://www.gxwztv.com/lingyixiaoshuo/");
        kinds.put("运动竞技","http://www.gxwztv.com/jingjixiaoshuo/");
        kinds.put("历史架空","http://www.gxwztv.com/lishixiaoshuo/");
        kinds.put("审美","http://www.gxwztv.com/danmeixiaoshuo/");
        kinds.put("科幻迷航","http://www.gxwztv.com/kehuanxiaoshuo/");
        kinds.put("游戏人生","http://www.gxwztv.com/youxixiaoshuo/");
        kinds.put("军事斗争","http://www.gxwztv.com/junshixiaoshuo/");
        kinds.put("商战人生","http://www.gxwztv.com/shangzhanxiaoshuo/");
        kinds.put("校园爱情","http://www.gxwztv.com/xiaoyuanxiaoshuo/");
        kinds.put("官场仕途","http://www.gxwztv.com/guanchangxiaoshuo/");
        kinds.put("娱乐明星","http://www.gxwztv.com/zhichangxiaoshuo/");
        kinds.put("其他","http://www.gxwztv.com/qitaxiaoshuo/");
        return kinds;
//        mCache = ACache.get(MApplication.getInstance());
    }

}
