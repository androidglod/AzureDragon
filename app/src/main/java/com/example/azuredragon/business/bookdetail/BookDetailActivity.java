package com.example.azuredragon.business.bookdetail;

import android.content.Intent;
import android.os.Build;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.azuredragon.MBaseActivity;
import com.example.azuredragon.R;
import com.example.azuredragon.R2;
import com.example.azuredragon.business.main.MainActivity;
import com.example.azuredragon.business.read.ReadBookActivity;
import com.example.azuredragon.business.read.modialog.MoProgressHUD;
import com.example.azuredragon.cache.DbHelper;
import com.example.azuredragon.cache.RxBusTag;
import com.example.azuredragon.http.bean.BookDetailBean;
import com.example.azuredragon.http.bean.BookInfoBean;
import com.example.azuredragon.http.bean.BookShelfBean;
import com.example.azuredragon.http.bean.ChapterListBean;
import com.example.azuredragon.http.bean.ChaptersBean;
import com.hwangjr.rxbus.RxBus;
import com.ta.utdid2.android.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class BookDetailActivity extends MBaseActivity<IBookDetailPresenter> implements  ChapterListContract.View  {

    @BindView(R2.id.ifl_content)
    FrameLayout iflContent;
    @BindView(R2.id.iv_blur_cover)
     ImageView ivBlurCover;
    @BindView(R2.id.iv_cover)
     ImageView ivCover;
    @BindView(R2.id.tv_name)
     TextView tvName;
    @BindView(R2.id.tv_author)
     TextView tvAuthor;
    @BindView(R2.id.tv_origin)
     TextView tvOrigin;
    @BindView(R2.id.tv_chapter)
     TextView tvChapter;
    @BindView(R2.id.tv_intro)
     TextView tvIntro;
    @BindView(R2.id.tv_shelf)
     TextView tvShelf;
    @BindView(R2.id.tv_read)
     TextView tvRead;
//    @BindView(R2.id.tv_loading)
//     TextView tvLoading;
    private BookDetailBean searchBook;
    private Animation animHideLoading;
    private Animation animShowInfo;
    private ChapterListPresenter presenter;
    private MoProgressHUD moProgressHUD;
    private boolean isLocakBookShelf;
    @Override
    protected IBookDetailPresenter initInjector() {
        return new BookDetailPresenterImpl(getIntent());
    }

    @Override
    protected void onCreateActivity() {
        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void initData() {
        moProgressHUD = new MoProgressHUD(this);
        presenter = new ChapterListPresenter(this,this);
        HashMap map = new HashMap();
        map.put("worksId",searchBook.getWorksId());
//        map.put("pageNo",1);


        animHideLoading = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        animHideLoading.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                tvLoading.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        moProgressHUD.showLoading();
        presenter.getChapterList(map);
    }


    @Override
    protected void bindView() {

        searchBook = getIntent().getParcelableExtra("data");
        tvIntro.setMovementMethod(ScrollingMovementMethod.getInstance());
        initView();


    }

    public void updateView() {

        if (isLocakBookShelf){
            tvShelf.setText("移出书架");
            tvRead.setText("继续阅读");
            tvShelf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BookShelfBean mBookShelfBean = new  BookShelfBean();
                    mBookShelfBean.setTag(MainActivity.bookShelfBeans.getTag());
                    mBookShelfBean.setDurChapter(MainActivity.bookShelfBeans.getDurChapter());
                    mBookShelfBean.setDurChapter(MainActivity.bookShelfBeans.getDurChapter());
                    mBookShelfBean.setNoteUrl(MainActivity.bookShelfBeans.getNoteUrl());
                    mBookShelfBean.setBookInfoBean(MainActivity.bookShelfBeans.getBookInfoBean());
                    mBookShelfBean.getBookInfoBean().setChapterlist(MainActivity.bookShelfBeans.getBookInfoBean().getChapterlist());
                    //从书架移出
                    if (!isLocakBookShelf){
                        //放入书架
                        DbHelper.getInstance().getmDaoSession().getChapterListBeanDao().insertOrReplaceInTx(mBookShelfBean.getBookInfoBean().getChapterlist());
                        DbHelper.getInstance().getmDaoSession().getBookInfoBeanDao().insertOrReplace(mBookShelfBean.getBookInfoBean());
                        //网络数据获取成功  存入BookShelf表数据库
                        DbHelper.getInstance().getmDaoSession().getBookShelfBeanDao().insertOrReplace(mBookShelfBean);
                        tvShelf.setText("移出书架");
                        isLocakBookShelf = true;
                    }else{
                        DbHelper.getInstance().getmDaoSession().getBookShelfBeanDao().deleteByKey(mBookShelfBean.getNoteUrl());
                        DbHelper.getInstance().getmDaoSession().getBookInfoBeanDao().deleteByKey(mBookShelfBean.getBookInfoBean().getNoteUrl());
                        List<String> keys = new ArrayList<String>();
                        if(mBookShelfBean.getBookInfoBean().getChapterlist().size()>0){
                            for(int i=0;i<mBookShelfBean.getBookInfoBean().getChapterlist().size();i++){
                                keys.add(mBookShelfBean.getBookInfoBean().getChapterlist().get(i).getDurChapterUrl());
                            }
                        }
                        DbHelper.getInstance().getmDaoSession().getBookContentBeanDao().deleteByKeyInTx(keys);
                        DbHelper.getInstance().getmDaoSession().getChapterListBeanDao().deleteInTx(mBookShelfBean.getBookInfoBean().getChapterlist());
                        tvShelf.setText("放入书架");
                        isLocakBookShelf = false;
                    }

//                    RxBus.get().post(RxBusTag.HAD_REMOVE_BOOK, mBookShelfBean);
                }
            });
        } else {
            tvShelf.setText("放入书架");
            tvRead.setText("开始阅读");
            tvShelf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //从书架移出
                    BookShelfBean mBookShelfBean = new  BookShelfBean();
                    mBookShelfBean.setTag(MainActivity.bookShelfBeans.getTag());
                    mBookShelfBean.setDurChapter(MainActivity.bookShelfBeans.getDurChapter());
                    mBookShelfBean.setDurChapter(MainActivity.bookShelfBeans.getDurChapter());
                    mBookShelfBean.setNoteUrl(MainActivity.bookShelfBeans.getNoteUrl());
                    mBookShelfBean.setBookInfoBean(MainActivity.bookShelfBeans.getBookInfoBean());
                    mBookShelfBean.getBookInfoBean().setChapterlist(MainActivity.bookShelfBeans.getBookInfoBean().getChapterlist());
                    if (!isLocakBookShelf){
                        //放入书架
                        DbHelper.getInstance().getmDaoSession().getChapterListBeanDao().insertOrReplaceInTx(mBookShelfBean.getBookInfoBean().getChapterlist());
                        DbHelper.getInstance().getmDaoSession().getBookInfoBeanDao().insertOrReplace(mBookShelfBean.getBookInfoBean());
                        //网络数据获取成功  存入BookShelf表数据库
                        DbHelper.getInstance().getmDaoSession().getBookShelfBeanDao().insertOrReplace(mBookShelfBean);
                        tvShelf.setText("移出书架");
                        isLocakBookShelf = true;
                    }else{
                        DbHelper.getInstance().getmDaoSession().getBookShelfBeanDao().deleteByKey(mBookShelfBean.getNoteUrl());
                        DbHelper.getInstance().getmDaoSession().getBookInfoBeanDao().deleteByKey(mBookShelfBean.getBookInfoBean().getNoteUrl());
                        List<String> keys = new ArrayList<String>();
                        if(mBookShelfBean.getBookInfoBean().getChapterlist().size()>0){
                            for(int i=0;i<mBookShelfBean.getBookInfoBean().getChapterlist().size();i++){
                                keys.add(mBookShelfBean.getBookInfoBean().getChapterlist().get(i).getDurChapterUrl());
                            }
                        }
                        DbHelper.getInstance().getmDaoSession().getBookContentBeanDao().deleteByKeyInTx(keys);
                        DbHelper.getInstance().getmDaoSession().getChapterListBeanDao().deleteInTx(mBookShelfBean.getBookInfoBean().getChapterlist());
                        tvShelf.setText("放入书架");
                        isLocakBookShelf = false;
                    }

                }
            });
        }
        if (tvIntro.getText().toString().trim().length() == 0) {
                tvIntro.setText(mPresenter.getBookShelf().getBookInfoBean().getIntroduce());
         }
        if (tvIntro.getVisibility() != View.VISIBLE) {
            tvIntro.setVisibility(View.VISIBLE);
            tvIntro.startAnimation(animShowInfo);
        }
    }

//    @Override
//    public void getBookShelfError() {
//        tvLoading.setVisibility(View.VISIBLE);
//        tvLoading.setText("加载失败,点击重试");
//        tvLoading.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvLoading.setText("加载中...");
//                tvLoading.setOnClickListener(null);
//                mPresenter.getBookShelfInfo();
//            }
//        });
//    }

    @Override
    protected void firstRequest() {
        super.firstRequest();
//        if (mPresenter.getOpenfrom() == BookDetailPresenterImpl.FROM_SEARCH && mPresenter.getBookShelf() == null) {
//            //网络请求
//            mPresenter.getBookShelfInfo();
//        }
    }

    private void initView() {
        String coverUrl;
        String name;
        String author;
//        if (mPresenter.getOpenfrom() == BookDetailPresenterImpl.FROM_BOOKSHELF) {
//            coverUrl = mPresenter.getBookShelf().getBookInfoBean().getCoverUrl();
//            name = mPresenter.getBookShelf().getBookInfoBean().getName();
//            author = mPresenter.getBookShelf().getBookInfoBean().getAuthor();
//            if (mPresenter.getBookShelf().getBookInfoBean().getOrigin() != null && mPresenter.getBookShelf().getBookInfoBean().getOrigin().length() > 0) {
//                tvOrigin.setVisibility(View.VISIBLE);
//                tvOrigin.setText("来源:" + mPresenter.getBookShelf().getBookInfoBean().getOrigin());
//            } else {
//                tvOrigin.setVisibility(View.GONE);
//            }
//        } else {
//            coverUrl = mPresenter.getSearchBook().getWorksCoverPic();
//            name = mPresenter.getSearchBook().getWorksName();
//            author = mPresenter.getSearchBook().getWriter();
////            if (mPresenter.getSearchBook().getOrigin() != null && mPresenter.getSearchBook().getOrigin().length() > 0) {
////                tvOrigin.setVisibility(View.VISIBLE);
////                tvOrigin.setText("来源:" + mPresenter.getSearchBook().getOrigin());
////            } else {
////                tvOrigin.setVisibility(View.GONE);
////            }
//        }
        if(searchBook !=null && !StringUtils.isEmpty(searchBook.getWriter()))

        Glide.with(this).load(searchBook.getWorksCoverPic()).dontAnimate().diskCacheStrategy(DiskCacheStrategy.RESULT).centerCrop().placeholder(R.drawable.img_cover_default).into(ivCover);
//        Glide.with(this).load(coverUrl).dontAnimate().diskCacheStrategy(DiskCacheStrategy.RESULT).centerCrop().bitmapTransform(new BlurTransformation(this, 6)).into(ivBlurCover);
        if(!StringUtils.isEmpty(searchBook.getWorksName())){
            tvName.setText(searchBook.getWorksName());
        }

        tvAuthor.setText(searchBook.getWriter());
        tvIntro.setText(searchBook.getWorksDes());
        animShowInfo = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        tvIntro.startAnimation(animShowInfo);
    }

    @Override
    protected void bindEvent() {
        iflContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if(getStart_share_ele()){
                        finishAfterTransition();
                    }else{
                        finish();
                        overridePendingTransition(0,android.R.anim.fade_out);
                    }
                } else {
                    finish();
                    overridePendingTransition(0,android.R.anim.fade_out);
                }
            }
        });

        tvRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入阅读
                Intent intent = new Intent(BookDetailActivity.this, ReadBookActivity.class);
                intent.putExtra("from", ReadBookPresenterImpl.OPEN_FROM_APP);
                String key = String.valueOf(System.currentTimeMillis());
                intent.putExtra("data_key", key);
//                try {
//                    BitIntentDataManager.getInstance().putData(key, mPresenter.getBookShelf().clone());
//                } catch (CloneNotSupportedException e) {
//                    BitIntentDataManager.getInstance().putData(key, mPresenter.getBookShelf());
//                    e.printStackTrace();
//                }
                startActivityByAnim(intent, android.R.anim.fade_in, android.R.anim.fade_out);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if(getStart_share_ele()){
                        finishAfterTransition();
                    }else{
                        finish();
                        overridePendingTransition(0,android.R.anim.fade_out);
                    }
                } else {
                    finish();
                    overridePendingTransition(0,android.R.anim.fade_out);
                }
            }
        });
    }

    @Override
    public void success(List<ChaptersBean> library) {
        Log.d("ss", "success: ");

        BookInfoBean mBookInfoBean = new BookInfoBean();
        List<ChapterListBean> chapterlist = new ArrayList<>();
        BookInfoBean localBookInfoBean = new BookInfoBean();
        List<BookShelfBean> temp = DbHelper.getInstance().getmDaoSession().getBookShelfBeanDao().queryBuilder().list();
        if (null != temp && temp.size()>0){
            for(int i=0;i<temp.size();i++){
                if(temp.get(i).getNoteUrl().equals(searchBook.getWorksId()+"")){
                    MainActivity.bookShelfBeans.setDurChapter(temp.get(i).getDurChapter());
                    MainActivity.bookShelfBeans.setDurChapterPage(temp.get(i).getDurChapterPage());
                    localBookInfoBean= temp.get(i).getBookInfoBean();
                    isLocakBookShelf = true;
                    break;
                }
            }
        }

        for (int i = 0; i < library.size(); i++) {
            ChapterListBean mChapterListBean = new ChapterListBean();
            mChapterListBean.setDurChapterName(library.get(i).getWorksName());
            mChapterListBean.setDurChapterId(library.get(i).getChapterId());
            mChapterListBean.setDurChapterIndex(i);
            mChapterListBean.setDurChapterUrl(library.get(i).getChapterId()+"");
            mChapterListBean.setNoteUrl(library.get(i).getChapterId()+"");
            mChapterListBean.setTag(library.get(i).getChapterId()+"");
            chapterlist.add(mChapterListBean);
        }


        mBookInfoBean.setChapterlist(chapterlist);
        mBookInfoBean.setAuthor(searchBook.getWriter());
        mBookInfoBean.setName(searchBook.getWorksName());
        mBookInfoBean.setIntroduce(searchBook.getWorksDes());
        mBookInfoBean.setNoteUrl(searchBook.getWorksId()+"");
        mBookInfoBean.setCoverUrl(searchBook.getWorksCoverPic()+"");
        MainActivity.bookShelfBeans.setNoteUrl(searchBook.getWorksId()+"");
        MainActivity.bookShelfBeans.setTag(searchBook.getWorksId()+"");
        MainActivity.bookShelfBeans.setBookInfoBean(mBookInfoBean);

        moProgressHUD.dismiss();
        updateView();
    }

    @Override
    public void fail(String content) {
        moProgressHUD.dismiss();
    }

}
