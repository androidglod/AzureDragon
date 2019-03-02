package com.example.azuredragon.business.read;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azuredragon.ActivityManager;
import com.example.azuredragon.BookContentView;
import com.example.azuredragon.MBaseActivity;
import com.example.azuredragon.R;
import com.example.azuredragon.R2;
import com.example.azuredragon.business.bookdetail.ContentSwitchView;
import com.example.azuredragon.business.bookdetail.IBookReadPresenter;
import com.example.azuredragon.business.bookdetail.IBookReadView;
import com.example.azuredragon.business.bookdetail.PremissionCheck;
import com.example.azuredragon.business.bookdetail.ReadBookPresenterImpl;
import com.example.azuredragon.business.main.MainActivity;
import com.example.azuredragon.business.read.modialog.MoProgressHUD;
import com.example.azuredragon.cache.RxBusTag;
import com.example.azuredragon.http.bean.BookChapterContentBean;
import com.example.azuredragon.http.bean.BookShelfBean;
import com.example.azuredragon.http.bean.ChaptersBean;
import com.example.azuredragon.http.bean.DownloadChapterBean;
import com.example.azuredragon.http.bean.DownloadChapterListBean;
import com.hwangjr.rxbus.RxBus;
import com.monke.mprogressbar.MHorProgressBar;
import com.monke.mprogressbar.OnProgressListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.grantland.widget.AutofitTextView;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:阅读类
 */
public class ReadBookActivity extends MBaseActivity<IBookReadPresenter> implements IBookReadView{
    @BindView(R2.id.fl_content)
     FrameLayout flContent;
    @BindView(R2.id.csv_book)
     ContentSwitchView csvBook;

    //主菜单
    @BindView(R2.id.fl_menu)
     FrameLayout flMenu;
    @BindView(R2.id.v_menu_bg)
     View vMenuBg;
    @BindView(R2.id.ll_menu_top)
     LinearLayout llMenuTop;
    @BindView(R2.id.ll_menu_bottom)
     LinearLayout llMenuBottom;
    @BindView(R2.id.iv_return)
     ImageView ivReturn;
//    @BindView(R2.id.iv_more)
//     ImageView ivMenuMore;
    @BindView(R2.id.atv_title)
     AutofitTextView atvTitle;
    @BindView(R2.id.tv_pre)
     TextView tvPre;
    @BindView(R2.id.tv_next)
     TextView tvNext;
    @BindView(R2.id.hpb_read_progress)
     MHorProgressBar hpbReadProgress;
    @BindView(R2.id.ll_catalog)
     LinearLayout llCatalog;
    @BindView(R2.id.ll_light)
     LinearLayout llLight;
    @BindView(R2.id.ll_font)
     LinearLayout llFont;
    @BindView(R2.id.ll_setting)
     LinearLayout llSetting;
    //主菜单动画
    private Animation menuTopIn;
    private Animation menuTopOut;
    private Animation menuBottomIn;
    private Animation menuBottomOut;
    @BindView(R2.id.clp_chapterlist)
     ChapterListView chapterListView;
    private CheckAddShelfPop checkAddShelfPop;
    private WindowLightPop windowLightPop;
    private FontPop fontPop;
    private MoreSettingPop moreSettingPop;
    private ArrayList<ChaptersBean> library;
//    private BookShelfBean bookShelf;
    private MoProgressHUD moProgressHUD;
    @Override
    protected IBookReadPresenter initInjector() {
        return new ReadBookPresenterImpl();
    }

    @Override
    protected void onCreateActivity() {
        setContentView(R.layout.activity_bookread);
    }

    @Override
    protected void initData() {
//        bookShelf = getIntent().getParcelableExtra("data");
        mPresenter.saveProgress();
        menuTopIn = AnimationUtils.loadAnimation(this, R.anim.anim_readbook_top_in);
        menuTopIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                vMenuBg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llMenuTop.startAnimation(menuTopOut);
                        llMenuBottom.startAnimation(menuBottomOut);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        menuBottomIn = AnimationUtils.loadAnimation(this, R.anim.anim_readbook_bottom_in);

        menuTopOut = AnimationUtils.loadAnimation(this, R.anim.anim_readbook_top_out);
        menuTopOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                vMenuBg.setOnClickListener(null);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                flMenu.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        menuBottomOut = AnimationUtils.loadAnimation(this, R.anim.anim_readbook_bottom_out);
    }

    @Override
    protected void bindView() {
        moProgressHUD = new MoProgressHUD(this);
        initCsvBook();
    }

    @Override
    public void setHpbReadProgressMax(int count) {
        hpbReadProgress.setMaxProgress(count);
    }

    private void initCsvBook() {
        csvBook.bookReadInit(new ContentSwitchView.OnBookReadInitListener() {
            @Override
            public void success() {
                ;
                BookShelfBean mBookShelfBean = new  BookShelfBean();
                mBookShelfBean.setTag(MainActivity.bookShelfBeans.getTag());
                mBookShelfBean.setDurChapter(MainActivity.bookShelfBeans.getDurChapter());
                mBookShelfBean.setDurChapter(MainActivity.bookShelfBeans.getDurChapter());
                mBookShelfBean.setNoteUrl(MainActivity.bookShelfBeans.getNoteUrl());
                mBookShelfBean.setBookInfoBean(MainActivity.bookShelfBeans.getBookInfoBean());
                mBookShelfBean.getBookInfoBean().setChapterlist(MainActivity.bookShelfBeans.getBookInfoBean().getChapterlist());

                mPresenter.initData(ReadBookActivity.this,mBookShelfBean);
            }
        });
    }

    @Override
    public void initPop() {
        checkAddShelfPop = new CheckAddShelfPop(this, MainActivity.bookShelfBeans.getBookInfoBean().getName(), new CheckAddShelfPop.OnItemClickListener() {
            @Override
            public void clickExit() {
                finish();
            }

            @Override
            public void clickAddShelf() {
                mPresenter.addToShelf(null);
                checkAddShelfPop.dismiss();
            }
        });
        chapterListView.setData(MainActivity.bookShelfBeans, new ChapterListView.OnItemClickListener() {
            @Override
            public void itemClick(int index) {
                csvBook.setInitData(index,MainActivity.bookShelfBeans.getBookInfoBean().getChapterlist().size(), BookContentView.DURPAGEINDEXBEGIN);
            }
        });

        windowLightPop = new WindowLightPop(this);
        windowLightPop.initLight();

        fontPop = new FontPop(this, new FontPop.OnChangeProListener() {
            @Override
            public void textChange(int index) {
                csvBook.changeTextSize();
            }

            @Override
            public void bgChange(int index) {
                csvBook.changeBg();
            }
        });



        moreSettingPop = new MoreSettingPop(this);
    }

    @Override
    protected void bindEvent() {
        hpbReadProgress.setProgressListener(new OnProgressListener() {
            @Override
            public void moveStartProgress(float dur) {

            }

            @Override
            public void durProgressChange(float dur) {

            }

            @Override
            public void moveStopProgress(float dur) {
                int realDur = (int) Math.ceil(dur);
                if (realDur < 1) {
                    realDur = 1;
                }
                if ((realDur - 1) != mPresenter.getBookShelf().getDurChapter()) {
                    csvBook.setInitData(realDur - 1, mPresenter.getBookShelf().getBookInfoBean().getChapterlist().size(), BookContentView.DURPAGEINDEXBEGIN);
                }
                if (hpbReadProgress.getDurProgress() != realDur)
                    hpbReadProgress.setDurProgress(realDur);
            }

            @Override
            public void setDurProgress(float dur) {
                if (hpbReadProgress.getMaxProgress() == 1) {
                    tvPre.setEnabled(false);
                    tvNext.setEnabled(false);
                } else {
                    if (dur == 1) {
                        tvPre.setEnabled(false);
                        tvNext.setEnabled(true);
                    } else if (dur == hpbReadProgress.getMaxProgress()) {
                        tvPre.setEnabled(true);
                        tvNext.setEnabled(false);
                    } else {
                        tvPre.setEnabled(true);
                        tvNext.setEnabled(true);
                    }
                }
            }
        });
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backKey2();
            }
        });
//        ivMenuMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                readBookMenuMorePop.showAsDropDown(ivMenuMore, 0, DensityUtil.dp2px(ReadBookActivity.this, -3.5f));
//            }
//        });
        csvBook.setLoadDataListener(new ContentSwitchView.LoadDataListener() {
            @Override
            public void loaddata(BookContentView bookContentView, long qtag, int chapterIndex, int pageIndex) {
                mPresenter.loadContent(bookContentView, qtag, chapterIndex, pageIndex);
            }

            @Override
            public void updateProgress(int chapterIndex, int pageIndex) {
                mPresenter.updateProgress(chapterIndex, pageIndex);

                if (mPresenter.getBookShelf().getBookInfoBean().getChapterlist().size() > 0)
                    atvTitle.setText(mPresenter.getBookShelf().getBookInfoBean().getChapterlist().get(mPresenter.getBookShelf().getDurChapter()).getDurChapterName());
                else
                    atvTitle.setText("无章节");
                if (hpbReadProgress.getDurProgress() != chapterIndex + 1)
                    hpbReadProgress.setDurProgress(chapterIndex + 1);
            }

            @Override
            public String getChapterTitle(int chapterIndex) {
                return mPresenter.getChapterTitle(chapterIndex);
            }

            @Override
            public void initData(int lineCount) {
                mPresenter.setPageLineCount(lineCount);
                mPresenter.initContent();
            }

            @Override
            public void showMenu() {
                flMenu.setVisibility(View.VISIBLE);
                llMenuTop.startAnimation(menuTopIn);
                llMenuBottom.startAnimation(menuBottomIn);
            }
        });

        tvPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                csvBook.setInitData(mPresenter.getBookShelf().getDurChapter() - 1, mPresenter.getBookShelf().getBookInfoBean().getChapterlist().size(), BookContentView.DURPAGEINDEXBEGIN);
            }
        });
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                csvBook.setInitData(mPresenter.getBookShelf().getDurChapter() + 1, mPresenter.getBookShelf().getBookInfoBean().getChapterlist().size(), BookContentView.DURPAGEINDEXBEGIN);
            }
        });

        llCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llMenuTop.startAnimation(menuTopOut);
                llMenuBottom.startAnimation(menuBottomOut);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        chapterListView.show(mPresenter.getBookShelf().getDurChapter());
                    }
                }, menuTopOut.getDuration());
            }
        });

        llLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llMenuTop.startAnimation(menuTopOut);
                llMenuBottom.startAnimation(menuBottomOut);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        windowLightPop.showAtLocation(flContent, Gravity.BOTTOM, 0, 0);
                    }
                }, menuTopOut.getDuration());
            }
        });

        llFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llMenuTop.startAnimation(menuTopOut);
                llMenuBottom.startAnimation(menuBottomOut);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fontPop.showAtLocation(flContent, Gravity.BOTTOM, 0, 0);
                    }
                }, menuTopOut.getDuration());
            }
        });

        llSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llMenuTop.startAnimation(menuTopOut);
                llMenuBottom.startAnimation(menuBottomOut);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        moreSettingPop.showAtLocation(flContent, Gravity.BOTTOM, 0, 0);
                    }
                }, menuTopOut.getDuration());
            }
        });
    }

    @Override
    public Paint getPaint() {
        return csvBook.getTextPaint();
    }

    @Override
    public int getContentWidth() {
        return csvBook.getContentWidth();
    }

    @Override
    public void initContentSuccess(int durChapterIndex, int chapterAll, int durPageIndex) {
        csvBook.setInitData(durChapterIndex, chapterAll, durPageIndex);
    }

    @Override
    public void startLoadingBook() {
        csvBook.startLoading();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.saveProgress();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Boolean mo = moProgressHUD.onKeyDown(keyCode, event);
        if (mo)
            return mo;
        else {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (flMenu.getVisibility() == View.VISIBLE) {
                    llMenuTop.startAnimation(menuTopOut);
                    llMenuBottom.startAnimation(menuBottomOut);
                    return true;
                } else {
                    if (!mPresenter.getAdd() && checkAddShelfPop != null && !checkAddShelfPop.isShowing()){
                        checkAddShelfPop.showAtLocation(flContent, Gravity.CENTER, 0, 0);
                        return true;
                    }else{
                        Boolean temp2 = chapterListView.dimissChapterList();
                        if (temp2)
                            return true;
                        else {
                            finish();
                            return true;
                        }
                    }
                }
            } else {
                Boolean temp = csvBook.onKeyDown(keyCode, event);
                if (temp)
                    return true;
                else{
                    return true;
                }
            }
        }
    }


    public void backKey2(){
        if (!mPresenter.getAdd() && checkAddShelfPop != null && !checkAddShelfPop.isShowing()) {
            checkAddShelfPop.showAtLocation(flContent, Gravity.CENTER, 0, 0);
        } else {
            Boolean temp2 = chapterListView.dimissChapterList();
            if (temp2){

            }else {
                finish();
            }
        }
    }
//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
////        Boolean temp = csvBook.onKeyUp(keyCode, event);
////        if (temp)
////            return true;
////        return super.onKeyUp(keyCode, event);
//    }

    @Override
    public void showLoadBook() {
        moProgressHUD.showLoading("文本导入中...");
    }

    @Override
    public void dimissLoadBook() {
        moProgressHUD.dismiss();
    }

    @Override
    public void loadLocationBookError() {
        csvBook.loadError();
    }

    private Boolean showCheckPremission = false;

    @SuppressLint("NewApi")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0x11) {
            if (grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && PremissionCheck.checkPremission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                mPresenter.openBookFromOther(ReadBookActivity.this);
            } else {
                if (!this.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    showCheckPremission = true;
                    moProgressHUD.showTwoButton("去系统设置打开SD卡读写权限？", "取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    }, "设置", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PremissionCheck.requestPermissionSetting(ReadBookActivity.this);
                        }
                    });
                } else {
                    Toast.makeText(this, "未获取SD卡读取权限", Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (showCheckPremission && mPresenter.getOpen_from() == ReadBookPresenterImpl.OPEN_FROM_OTHER && !(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !PremissionCheck.checkPremission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
            showCheckPremission = true;
            mPresenter.openBookFromOther(this);
        }
    }

//    @Override
//    public void finish() {
//        if (!ActivityManager.getInstance().isExist(MainActivity.class)) {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }
//        super.finish();
//    }

}