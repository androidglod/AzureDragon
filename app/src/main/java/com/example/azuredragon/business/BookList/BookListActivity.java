package com.example.azuredragon.business.BookList;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.azuredragon.IPresenter;
import com.example.azuredragon.MBaseActivity;
import com.example.azuredragon.R;
import com.example.azuredragon.R2;
import com.example.azuredragon.booklistview.LibraryKindBookListView;
import com.example.azuredragon.business.bookdetail.BookDetailActivity;
import com.example.azuredragon.business.bookdetail.BookDetailPresenterImpl;
import com.example.azuredragon.business.search.SearchActivity;
import com.example.azuredragon.http.bean.BookDetailBean;
import com.example.azuredragon.http.bean.BookListBean;
import com.example.azuredragon.http.utils.DensityUtil;
import com.example.azuredragon.refreshview.BaseRefreshListener;
import com.example.azuredragon.refreshview.RefreshProgressBar;
import com.example.azuredragon.refreshview.RefreshScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:图书列表
 */
public class BookListActivity extends MBaseActivity implements BookListContract.View {

    @BindView(R2.id.rscv_content)
     RefreshScrollView rscvContent;
    @BindView(R2.id.rpb_progress)
     RefreshProgressBar rpbProgress;
    @BindView(R2.id.ll_content)
     LinearLayout llContent;
    @BindView(R2.id.ib_return)
     ImageView ibReturn;
    @BindView(R2.id.fl_search)
     FrameLayout flSearch;
    @BindView(R2.id.kind_ll)
     LinearLayout kindLl;
//    @BindView(R2.id.lav_hotauthor)
//     LibraryNewBooksView lavHotauthor;
    @BindView(R2.id.lkbv_kindbooklist)
     LibraryKindBookListView lkbvKindbooklist;

    private BookListPresenter presenter;
    List<List<BookDetailBean>> allBookList = new ArrayList<>();
    private Animation animIn;
    private Animation animOut;
    @Override
    protected void onCreateActivity() {
        setContentView(R.layout.activity_library);
    }

    @Override
    protected void firstRequest() {
        llContent.startAnimation(animIn);
    }

    @Override
    protected void initData() {
        animIn = AnimationUtils.loadAnimation(this, R.anim.anim_act_importbook_in);
        animOut = AnimationUtils.loadAnimation(this, R.anim.anim_act_importbook_out);
    }

    @Override
    protected void bindView() {
        rscvContent.setRpb(rpbProgress);
        initKind();
    }

    @Override
    protected IPresenter initInjector() {
        return null;
    }

    private void initKind() {

        presenter = new BookListPresenter(this,this);
        int columnCout = 4;
        Iterator iterator = presenter.bookListTabelPresenterImpl().entrySet().iterator();
        int temp = 0;
        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = null;
        LinearLayout.LayoutParams tvLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tvLp.weight = 1;
        while (iterator.hasNext()) {
            final Map.Entry<String, String> resultTemp = (Map.Entry<String, String>) iterator.next();
            if (temp % columnCout == 0) {
                linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(l);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                kindLl.addView(linearLayout);
            }
            TextView textView = new TextView(this);
            textView.setLayoutParams(tvLp);
            textView.setText(resultTemp.getKey());
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(14);
            textView.setPadding(0, DensityUtil.dp2px(this, 5), 0, DensityUtil.dp2px(this, 5));
            textView.setLines(1);
            textView.setTextColor(getResources().getColorStateList(R.color.color_737373));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ChoiceBookActivity.startChoiceBookActivity(BookListActivity.this, resultTemp.getKey(),resultTemp.getValue());
                }
            });
            linearLayout.addView(textView);
            temp++;
        }
        int viewCount = presenter.bookListTabelPresenterImpl().size() % columnCout == 0?0:(columnCout-presenter.bookListTabelPresenterImpl().size() % columnCout);
        for(int i=0;i<viewCount;i++){
            View v = new View(this);
            v.setLayoutParams(tvLp);
            linearLayout.addView(v);
        }
    }

    @Override
    protected void bindEvent() {
        animIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rscvContent.startRefresh();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                BookListActivity.super.finish();
                overridePendingTransition(0, 0);
                isExiting = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ibReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        flSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击搜索
                startActivityByAnim(new Intent(BookListActivity.this, SearchActivity.class), flSearch, "to_search", android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        rscvContent.setBaseRefreshListener(new BaseRefreshListener() {
            @Override
            public void startRefresh() {
                HashMap map = new HashMap();

                map.put("worksId","48");
                presenter.getBookList(map);
            }
        });
    }

    private Boolean isExiting = false;

    @Override
    public void finish() {
        if (!isExiting) {
            isExiting = true;
            llContent.startAnimation(animOut);
        }
    }


    public void updateUI(final BookListBean library) {
        allBookList.clear();
        allBookList.add(library.getCazpList());
        allBookList.add(library.getErCiYuanWorkList());
        allBookList.add(library.getGdyqList());
        allBookList.add(library.getLsjsList());
        allBookList.add(library.getMenWorkList());
        allBookList.add(library.getMxtxList());
        allBookList.add(library.getNdsnList());
        allBookList.add(library.getWomenWorkList());
        allBookList.add(library.getXylyList());
        allBookList.add(library.getXjqhList());
        allBookList.add(library.getXddsList());

        lkbvKindbooklist.updateData(allBookList, new LibraryKindBookListView.OnItemListener() {
            @Override
            public void onClickMore(String title, String url) {
//                Log.e("", "onClickMore: ",null);
//                ChoiceBookActivity.startChoiceBookActivity(BookListActivity.this,title,url);
            }

            @Override
            public void onClickBook(ImageView animView, BookDetailBean mBookDetailBean) {
                Intent intent = new Intent(BookListActivity.this, BookDetailActivity.class);
                intent.putExtra("from", BookDetailPresenterImpl.FROM_SEARCH);
                intent.putExtra("data", mBookDetailBean);
                startActivityByAnim(intent, animView, "img_cover", android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }


    @Override
    public void success(BookListBean library) {
        rscvContent.finishRefresh();
        updateUI(library);
    }

    @Override
    public void fail(String content) {

    }

    @Override
    public LinkedHashMap<String, String> getLinked() {
        return null;
    }
}