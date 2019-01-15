package com.example.azuredragon.business.main;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.com.sky.downloader.greendao.BookInfoBeanDao;
import com.com.sky.downloader.greendao.BookShelfBeanDao;
import com.com.sky.downloader.greendao.ChapterListBeanDao;
import com.example.azuredragon.IMainPresenter;
import com.example.azuredragon.MBaseActivity;
import com.example.azuredragon.R;
import com.example.azuredragon.R2;
import com.example.azuredragon.adapter.BookRackAdapter;
import com.example.azuredragon.business.bookList.BookListActivity;
import com.example.azuredragon.business.Localfile.ImportBookActivity;
import com.example.azuredragon.business.bookdetail.BitIntentDataManager;
import com.example.azuredragon.business.bookdetail.BookDetailActivity;
import com.example.azuredragon.business.bookdetail.BookDetailPresenterImpl;
import com.example.azuredragon.business.bookdetail.ReadBookPresenterImpl;
import com.example.azuredragon.business.read.ReadBookActivity;
import com.example.azuredragon.cache.DbHelper;
import com.example.azuredragon.cache.PreferencesUtils;
import com.example.azuredragon.http.bean.BookInfoBean;
import com.example.azuredragon.http.bean.BookShelfBean;
import com.example.azuredragon.http.bean.LibraryBean;
import com.example.azuredragon.http.bean.LoginBean;
import com.example.azuredragon.http.observer.SimpleObserver;
import com.example.azuredragon.refreshview.OnRefreshWithProgressListener;
import com.example.azuredragon.refreshview.RefreshRecyclerView;
import com.example.azuredragon.residemenu.ResideMenu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description 主activity
 */
public class MainActivity extends MBaseActivity implements BookRackContract.View{
    @BindView(R2.id.rf_rv_shelf)
    RefreshRecyclerView rfRvShelf;
    @BindView(R2.id.iv_add)
    ImageView username;

    @OnClick(R2.id.iv_booklist)
    public void selectBook(){
        resideMenu.closeMenu();
        //点击更多
        startActivityByAnim(new Intent(MainActivity.this, BookListActivity.class), 0, 0);

    }
    @OnClick(R2.id.iv_add)
    public void addItem(){
        resideMenu.closeMenu();
        //查看本地文件
        startActivityByAnim(new Intent(MainActivity.this, ImportBookActivity.class), 0, 0);

    }
    @OnClick(R2.id.iv_logo)
    public void openMenu(){
        resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
    }
    private ResideMenu resideMenu;

    private BookRackPresenter presenter;
    private BookRackAdapter bookRackAdapter;


    @Override
    protected void bindView() {
        super.bindView();
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
                if (null == value || value.size()==0) {
                    startActivityByAnim(new Intent(MainActivity.this, BookListActivity.class), android.R.anim.fade_in, android.R.anim.fade_out);
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected IMainPresenter initInjector() {
        return null;
    }

    @Override
    protected void onCreateActivity() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {
        presenter = new BookRackPresenter(this,this);
        bindRvShelfEvent();


    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMenu();
        presenter.getBookList(true);
    }

    private void setUpMenu() {
        LoginBean mLoginBeans = PreferencesUtils.getUser(this,"user","user_info");
        resideMenu = new ResideMenu(this,mLoginBeans);
        resideMenu.setUse3D(true);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        resideMenu.setScaleValue(0.6f);
    }

    private void bindRvShelfEvent() {
        bookRackAdapter = new BookRackAdapter();
        bookRackAdapter.setItemClickListener(new BookRackAdapter.OnItemClickListener() {
            @Override
            public void toSearch() {
                //点击去选书
                startActivityByAnim(new Intent(MainActivity.this, BookListActivity.class), 0, 0);
            }

            @Override
            public void onClick(BookShelfBean bookShelfBean, int index) {
                Intent intent = new Intent(MainActivity.this, ReadBookActivity.class);
                intent.putExtra("from", ReadBookPresenterImpl.OPEN_FROM_APP);
                String key = String.valueOf(System.currentTimeMillis());
                intent.putExtra("data_key", key);
                try {
                    BitIntentDataManager.getInstance().putData(key, bookShelfBean.clone());
                } catch (CloneNotSupportedException e) {
                    BitIntentDataManager.getInstance().putData(key, bookShelfBean);
                    e.printStackTrace();
                }
                startActivityByAnim(intent, android.R.anim.fade_in, android.R.anim.fade_out);
            }

            @Override
            public void onLongClick(View animView, BookShelfBean bookShelfBean, int index) {
                Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
                intent.putExtra("from", BookDetailPresenterImpl.FROM_BOOKSHELF);
                String key = String.valueOf(System.currentTimeMillis());
                intent.putExtra("data_key", key);
                BitIntentDataManager.getInstance().putData(key, bookShelfBean);
                startActivityByAnim(intent, animView, "img_cover", android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        rfRvShelf.setRefreshRecyclerViewAdapter(bookRackAdapter, new LinearLayoutManager(this));
        rfRvShelf.setBaseRefreshListener(new OnRefreshWithProgressListener() {
            @Override
            public int getMaxProgress() {
                return bookRackAdapter.getBooks().size();
            }

            @Override
            public void startRefresh() {
                presenter.getBookList(true);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
        }

        @Override
        public void closeMenu() {
        }
    };

    public ResideMenu getResideMenu(){
        return resideMenu;
    }

    @Override
    public void refreshBookShelf(List<BookShelfBean> bookShelfBeanList) {
        bookRackAdapter.replaceAll(bookShelfBeanList);
    }

    @Override
    public void activityRefreshView() {
        //执行刷新响应
        rfRvShelf.startRefresh();
    }

    @Override
    public void refreshFinish() {
        rfRvShelf.finishRefresh(false, true);
    }

    @Override
    public void refreshError(String error) {
        refreshFinish();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshRecyclerViewItemAdd() {
        rfRvShelf.getRpb().setDurProgress(rfRvShelf.getRpb().getDurProgress() + 1);
    }

    @Override
    public void setRecyclerMaxProgress(int x) {
        rfRvShelf.getRpb().setMaxProgress(x);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private long exitTime = 0;

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void success(LibraryBean library) {

    }

    @Override
    public void fail(String content) {

    }

    @Override
    public LinkedHashMap<String, String> getLinked() {
        return null;
    }
}


