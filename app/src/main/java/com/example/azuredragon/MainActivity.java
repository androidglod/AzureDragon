package com.example.azuredragon;

import android.content.Intent;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.azuredragon.adapter.BookShelfAdapter;
import com.example.azuredragon.http.bean.BookShelfBean;
import com.example.azuredragon.login.activity.LoginActivity;
import com.example.azuredragon.refreshview.RefreshRecyclerView;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends  MBaseActivity<IMainPresenter> implements IMainView  {
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
    private RefreshRecyclerView rfRvShelf;
    private BookShelfAdapter bookShelfAdapter;

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

        // create menu items;
        itemHome  = new ResideMenuItem(this, R.drawable.icon_home,     "Home");


        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

//        findViewById(R.id.textSpacerNoTitle).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
//            }
//        });

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

    }

    @Override
    public void activityRefreshView() {

    }

    @Override
    public void refreshFinish() {

    }

    @Override
    public void refreshError(String error) {

    }

    @Override
    public void refreshRecyclerViewItemAdd() {

    }

    @Override
    public void setRecyclerMaxProgress(int x) {

    }
}


