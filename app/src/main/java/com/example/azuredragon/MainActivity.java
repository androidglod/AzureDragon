package com.example.azuredragon;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.azuredragon.adapter.BookShelfAdapter;
import com.example.azuredragon.business.bookdetail.BitIntentDataManager;
import com.example.azuredragon.business.bookdetail.BookDetailActivity;
import com.example.azuredragon.business.bookdetail.BookDetailPresenterImpl;
import com.example.azuredragon.http.bean.BookShelfBean;
import com.example.azuredragon.login.activity.LoginActivity;
import com.example.azuredragon.refreshview.OnRefreshWithProgressListener;
import com.example.azuredragon.refreshview.RefreshRecyclerView;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends  MBaseActivity<IMainPresenter> implements IMainView  {
    @BindView(R2.id.rf_rv_shelf)
    RefreshRecyclerView rfRvShelf;
    @BindView(R2.id.iv_add)
    ImageView username;
    @OnClick(R2.id.iv_add)
    public void addItem(){
        resideMenu.closeMenu();
        startActivityByAnim(new Intent(MainActivity.this, LoginActivity.class), android.R.anim.fade_in, android.R.anim.fade_out);

    }
    @OnClick(R2.id.iv_logo)
    public void openMenu(){
        resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
    }
    private ResideMenu resideMenu;
    private ResideMenuItem itemHome;


    private BookShelfAdapter bookShelfAdapter;


    @Override
    protected void bindView() {
        super.bindView();
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
        setUpMenu();
        bindRvShelfEvent();
    }

    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setUse3D(true);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.6f);
        itemHome  = new ResideMenuItem(this, R.drawable.icon_home,     "Home");
        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
    }

    private void bindRvShelfEvent() {
        bookShelfAdapter = new BookShelfAdapter();
        bookShelfAdapter.setItemClickListener(new BookShelfAdapter.OnItemClickListener() {
            @Override
            public void toSearch() {
                //点击去选书
                startActivityByAnim(new Intent(MainActivity.this, LibraryActivity.class), 0, 0);
            }

            @Override
            public void onClick(BookShelfBean bookShelfBean, int index) {
//                Intent intent = new Intent(MainActivity.this, ReadBookActivity.class);
//                intent.putExtra("from", ReadBookPresenterImpl.OPEN_FROM_APP);
//                String key = String.valueOf(System.currentTimeMillis());
//                intent.putExtra("data_key", key);
//                try {
//                    BitIntentDataManager.getInstance().putData(key, bookShelfBean.clone());
//                } catch (CloneNotSupportedException e) {
//                    BitIntentDataManager.getInstance().putData(key, bookShelfBean);
//                    e.printStackTrace();
//                }
//                startActivityByAnim(intent, android.R.anim.fade_in, android.R.anim.fade_out);
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
        rfRvShelf.setRefreshRecyclerViewAdapter(bookShelfAdapter, new LinearLayoutManager(this));
        rfRvShelf.setBaseRefreshListener(new OnRefreshWithProgressListener() {
            @Override
            public int getMaxProgress() {
                return bookShelfAdapter.getBooks().size();
            }

            @Override
            public void startRefresh() {
                mPresenter.queryBookShelf(true);
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
            Toast.makeText(MainActivity.this, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            Toast.makeText(MainActivity.this, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };
//
//    private void changeFragment(Fragment targetFragment){
//        resideMenu.clearIgnoredViewList();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.main_fragment, targetFragment, "fragment")
//                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                .commit();
//    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }

    @Override
    public void refreshBookShelf(List<BookShelfBean> bookShelfBeanList) {
        bookShelfAdapter.replaceAll(bookShelfBeanList);
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
}


