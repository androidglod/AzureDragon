package com.example.azuredragon.banner.holder;

import android.view.View;

import com.example.azuredragon.R;


/**
 * Description:
 *
 * @author zhangchangan
 * date: 2018/5/25 17:32
 */
public class CBViewHolderCreatorImpl implements CBViewHolderCreator {
    @Override
    public Holder createHolder(View itemView) {
        return new NetworkImageHolderView(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.common_banner;
    }
}
