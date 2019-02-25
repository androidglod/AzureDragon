package com.example.azuredragon.business.main;

import android.content.Context;

import com.com.sky.downloader.greendao.BookInfoBeanDao;
import com.com.sky.downloader.greendao.BookShelfBeanDao;
import com.com.sky.downloader.greendao.ChapterListBeanDao;
import com.example.azuredragon.cache.DbHelper;
import com.example.azuredragon.http.base.BaseListEntry;
import com.example.azuredragon.http.base.BaseObserver;
import com.example.azuredragon.http.bean.BookInfoBean;
import com.example.azuredragon.http.bean.BookShelfBean;
import com.example.azuredragon.http.bean.ChaptersBean;
import com.example.azuredragon.http.impl.WebBookModelImpl;
import com.example.azuredragon.http.listener.OnGetChapterListListener;
import com.example.azuredragon.http.observer.SimpleObserver;
import com.example.azuredragon.http.utils.NetworkUtil;
import com.example.azuredragon.http.utils.RetrofitUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author: chz.
 * @date: 2018/11/25
 * @description:书籍列表请求接口
 */

public class BookRackPresenter implements BookRackContract.presenter {

    private Context context;
    private BookRackContract.View view;

    public BookRackPresenter(Context context, BookRackContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getBookList(final Boolean needRefresh) {
//        RetrofitUtil.getInstance().initRetrofit().getBookListData()
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new BaseObserver<List<LibraryBean>>(context,MainUtil.loadTxt) {
//            @Override
//            protected void onSuccees(BaseEntry<List<LibraryBean>> t) throws Exception {
//                view.success(t.getData().get(0));
//            }
//
//            @Override
//            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
//                view.fail("失败了----->"+e.getMessage());
//            }
//        });
        if (needRefresh)
            view.activityRefreshView();
        Observable.create(new ObservableOnSubscribe<List<BookShelfBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<BookShelfBean>> e) throws Exception {
                List<BookShelfBean> bookShelfes = DbHelper.getInstance().getmDaoSession().getBookShelfBeanDao().queryBuilder().orderDesc(BookShelfBeanDao.Properties.FinalDate).list();
                for (int i = 0; i < bookShelfes.size(); i++) {
                    List<BookInfoBean> temp = DbHelper.getInstance().getmDaoSession().getBookInfoBeanDao().queryBuilder().where(BookInfoBeanDao.Properties.NoteUrl.eq(bookShelfes.get(i).getNoteUrl())).limit(1).build().list();
                    if (temp != null && temp.size() > 0) {
                        BookInfoBean bookInfoBean = temp.get(0);
                        bookInfoBean.setChapterlist(DbHelper.getInstance().getmDaoSession().getChapterListBeanDao().queryBuilder().where(ChapterListBeanDao.Properties.NoteUrl.eq(bookShelfes.get(i).getNoteUrl())).orderAsc(ChapterListBeanDao.Properties.DurChapterIndex).build().list());
                        bookShelfes.get(i).setBookInfoBean(bookInfoBean);
                    } else {
                        DbHelper.getInstance().getmDaoSession().getBookShelfBeanDao().delete(bookShelfes.get(i));
                        bookShelfes.remove(i);
                        i--;
                    }
                }
                e.onNext(bookShelfes == null ? new ArrayList<BookShelfBean>() : bookShelfes);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<List<BookShelfBean>>() {
                    @Override
                    public void onNext(List<BookShelfBean> value) {
                        if (null != value) {
                            view.refreshBookShelf(value);
                            if (needRefresh) {
                                startRefreshBook(value);
                            } else {
                                view.refreshFinish();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.refreshError(NetworkUtil.getErrorTip(NetworkUtil.ERROR_CODE_ANALY));
                    }
                });
    }

    @Override
    public void getBookListDetail(HashMap map,int index) {
        RetrofitUtil.getInstance().initRetrofit().getShowChapterList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseListEntry<ChaptersBean>>(context,null) {


                    @Override
                    protected void onSuccees(BaseListEntry<ChaptersBean> mChapterListBean) throws Exception {
                        view.bookDetailSuccess(mChapterListBean.getData(), index);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.bookDetailFail("失败了----->"+e.getMessage());
                    }
                });

    }

    public void startRefreshBook(List<BookShelfBean> value){
        if (value != null && value.size() > 0){
            view.setRecyclerMaxProgress(value.size());
            refreshBookShelf(value,0);
        }else{
            view.refreshFinish();
        }
    }

    private void refreshBookShelf(final List<BookShelfBean> value, final int index) {
        if (index<=value.size()-1) {
            WebBookModelImpl.getInstance().getChapterList(value.get(index), new OnGetChapterListListener() {
                @Override
                public void success(BookShelfBean bookShelfBean) {
                    saveBookToShelf(value,index);
                }

                @Override
                public void error() {
                    view.refreshError(NetworkUtil.getErrorTip(NetworkUtil.ERROR_CODE_NONET));
                }
            });
        } else {
            getBookList(false);
        }
    }
    private void saveBookToShelf(final List<BookShelfBean> datas, final int index){
        Observable.create(new ObservableOnSubscribe<BookShelfBean>() {
            @Override
            public void subscribe(ObservableEmitter<BookShelfBean> e) throws Exception {
                DbHelper.getInstance().getmDaoSession().getChapterListBeanDao().insertOrReplaceInTx(datas.get(index).getBookInfoBean().getChapterlist());
                e.onNext(datas.get(index));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<BookShelfBean>() {
                    @Override
                    public void onNext(BookShelfBean value) {
                        view.refreshRecyclerViewItemAdd();
                        refreshBookShelf(datas,index+1);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.refreshError(NetworkUtil.getErrorTip(NetworkUtil.ERROR_CODE_NONET));
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
