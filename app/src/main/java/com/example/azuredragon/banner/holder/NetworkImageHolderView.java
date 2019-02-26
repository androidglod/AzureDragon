package com.example.azuredragon.banner.holder;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.azuredragon.R;
import com.example.azuredragon.http.bean.BannerBean;


/**
 *
 * @author chenhaizhen
 * @date 19/1/4
 * 网络图片加载例子
 */
public class NetworkImageHolderView extends Holder<BannerBean> {
    private ImageView imageView;

    public NetworkImageHolderView(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        imageView = itemView.findViewById(R.id.ivBanner);
    }

    @Override
    public void updateUI(BannerBean entity) {
        if(imageView !=null && imageView.getContext() !=null ){
            Glide.with(imageView.getContext().getApplicationContext())
                    .load( entity.getAdAttUrl())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .fitCenter()
                    .dontAnimate()
                    .placeholder(R.drawable.img_cover_default)
                    .into(imageView);
        }
    }
}
