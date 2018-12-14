package com.example.azuredragon.business.bookdetail;

import android.content.Intent;
import android.os.Build;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.azuredragon.MBaseActivity;
import com.example.azuredragon.R;
import com.example.azuredragon.R2;
import com.example.azuredragon.business.read.ReadBookActivity;

import butterknife.BindView;

public class BookDetailActivity extends MBaseActivity<IBookDetailPresenter> implements IBookDetailView {

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
    @BindView(R2.id.tv_loading)
     TextView tvLoading;

    private Animation animHideLoading;
    private Animation animShowInfo;

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
        animShowInfo = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        animHideLoading = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        animHideLoading.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tvLoading.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    @Override
    protected void bindView() {


        tvIntro.setMovementMethod(ScrollingMovementMethod.getInstance());
        initView();

        updateView();
    }

    @Override
    public void updateView() {
        if (null != mPresenter.getBookShelf()) {
            if (mPresenter.getInBookShelf()) {
                if (mPresenter.getBookShelf().getBookInfoBean().getChapterlist().size() > 0)
                    tvChapter.setText(String.format(getString(R.string.tv_read_durprogress), mPresenter.getBookShelf().getBookInfoBean().getChapterlist().get(mPresenter.getBookShelf().getDurChapter()).getDurChapterName()));
                else
                    tvChapter.setText("无章节");
                tvShelf.setText("移出书架");
                tvRead.setText("继续阅读");
                tvShelf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //从书架移出
                        mPresenter.removeFromBookShelf();
                    }
                });
            } else {
                if (mPresenter.getBookShelf().getBookInfoBean().getChapterlist().size() == 0) {
                    tvChapter.setText("无章节");
                } else {
                    tvChapter.setText(String.format(getString(R.string.tv_searchbook_lastest), mPresenter.getBookShelf().getBookInfoBean().getChapterlist().get(mPresenter.getBookShelf().getBookInfoBean().getChapterlist().size() - 1).getDurChapterName()));
                }
                tvShelf.setText("放入书架");
                tvRead.setText("开始阅读");
                tvShelf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //放入书架
                        mPresenter.addToBookShelf();
                    }
                });
            }
            if (tvIntro.getText().toString().trim().length() == 0) {
                tvIntro.setText(mPresenter.getBookShelf().getBookInfoBean().getIntroduce());
            }
            if (tvIntro.getVisibility() != View.VISIBLE) {
                tvIntro.setVisibility(View.VISIBLE);
                tvIntro.startAnimation(animShowInfo);
                tvLoading.startAnimation(animHideLoading);
            }
            if (mPresenter.getBookShelf().getBookInfoBean().getOrigin() != null && mPresenter.getBookShelf().getBookInfoBean().getOrigin().length() > 0) {
                tvOrigin.setVisibility(View.VISIBLE);
                tvOrigin.setText("来源:" + mPresenter.getBookShelf().getBookInfoBean().getOrigin());
            } else {
                tvOrigin.setVisibility(View.GONE);
            }
        } else {
            tvChapter.setText(String.format(getString(R.string.tv_searchbook_lastest), mPresenter.getSearchBook().getLastChapter()));
            tvShelf.setText("放入书架");
            tvRead.setText("开始阅读");
            tvRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //放入书架
                }
            });
            tvIntro.setVisibility(View.INVISIBLE);
            tvLoading.setVisibility(View.VISIBLE);
            tvLoading.setText("加载中...");
        }
        tvLoading.setOnClickListener(null);
    }

    @Override
    public void getBookShelfError() {
        tvLoading.setVisibility(View.VISIBLE);
        tvLoading.setText("加载失败,点击重试");
        tvLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvLoading.setText("加载中...");
                tvLoading.setOnClickListener(null);
                mPresenter.getBookShelfInfo();
            }
        });
    }

    @Override
    protected void firstRequest() {
        super.firstRequest();
        if (mPresenter.getOpenfrom() == BookDetailPresenterImpl.FROM_SEARCH && mPresenter.getBookShelf() == null) {
            //网络请求
            mPresenter.getBookShelfInfo();
        }
    }

    private void initView() {
        String coverUrl;
        String name;
        String author;
        if (mPresenter.getOpenfrom() == BookDetailPresenterImpl.FROM_BOOKSHELF) {
            coverUrl = mPresenter.getBookShelf().getBookInfoBean().getCoverUrl();
            name = mPresenter.getBookShelf().getBookInfoBean().getName();
            author = mPresenter.getBookShelf().getBookInfoBean().getAuthor();
            if (mPresenter.getBookShelf().getBookInfoBean().getOrigin() != null && mPresenter.getBookShelf().getBookInfoBean().getOrigin().length() > 0) {
                tvOrigin.setVisibility(View.VISIBLE);
                tvOrigin.setText("来源:" + mPresenter.getBookShelf().getBookInfoBean().getOrigin());
            } else {
                tvOrigin.setVisibility(View.GONE);
            }
        } else {
            coverUrl = mPresenter.getSearchBook().getCoverUrl();
            name = mPresenter.getSearchBook().getName();
            author = mPresenter.getSearchBook().getAuthor();
            if (mPresenter.getSearchBook().getOrigin() != null && mPresenter.getSearchBook().getOrigin().length() > 0) {
                tvOrigin.setVisibility(View.VISIBLE);
                tvOrigin.setText("来源:" + mPresenter.getSearchBook().getOrigin());
            } else {
                tvOrigin.setVisibility(View.GONE);
            }
        }

        Glide.with(this).load(coverUrl).dontAnimate().diskCacheStrategy(DiskCacheStrategy.RESULT).centerCrop().placeholder(R.drawable.img_cover_default).into(ivCover);
        Glide.with(this).load(coverUrl).dontAnimate().diskCacheStrategy(DiskCacheStrategy.RESULT).centerCrop().bitmapTransform(new BlurTransformation(this, 6)).into(ivBlurCover);
        tvName.setText(name);
        tvAuthor.setText(author);
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
                try {
                    BitIntentDataManager.getInstance().putData(key, mPresenter.getBookShelf().clone());
                } catch (CloneNotSupportedException e) {
                    BitIntentDataManager.getInstance().putData(key, mPresenter.getBookShelf());
                    e.printStackTrace();
                }
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
}
