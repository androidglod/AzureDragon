package com.example.azuredragon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenhaizhen.azuredragon.Bean.BookShelfBean;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends  MBaseActivity<IMainPresenter> implements IMainView , View.OnClickListener {
    @BindView(R2.id.textSpacerNoTitle)
    TextView username;
    @OnClick(R2.id.textSpacerNoTitle)
    public void open(){
        resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
    }

    private ResideMenu resideMenu;
    private ResideMenuItem itemHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpMenu();
    }

    @Override
    protected IMainPresenter initInjector() {
        return null;
    }

    @Override
    protected void onCreateActivity() {

    }

    @Override
    protected void initData() {

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
        itemHome     = new ResideMenuItem(this, R.drawable.icon_home,     "Home");
//        itemProfile  = new ResideMenuItem(this, R.drawable.icon_profile,  "Profile");
//        itemCalendar = new ResideMenuItem(this, R.drawable.icon_calendar, "Calendar");
//        itemSettings = new ResideMenuItem(this, R.drawable.icon_settings, "Settings");

        itemHome.setOnClickListener(this);
//        itemProfile.setOnClickListener(this);
//        itemCalendar.setOnClickListener(this);
//        itemSettings.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
//        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
//        resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_RIGHT);
//        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_RIGHT);

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

    @Override
    public void onClick(View view) {
//        if (view == itemHome){
//            changeFragment(new HomeFragment());
//        }

        resideMenu.closeMenu();
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


