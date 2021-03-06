package com.example.azuredragon.business.search;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.azuredragon.R;
import com.example.azuredragon.http.bean.SearchHistoryBean;
import com.example.azuredragon.widget.flowlayout.FlowLayout;
import com.example.azuredragon.widget.flowlayout.TagAdapter;

import java.util.ArrayList;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */
public class SearchHistoryAdapter extends TagAdapter<SearchHistoryBean> {
    public SearchHistoryAdapter() {
        super(new ArrayList<SearchHistoryBean>());
    }

    public interface OnItemClickListener{
        void itemClick(SearchHistoryBean searchHistoryBean);
    }
    private SearchHistoryAdapter.OnItemClickListener onItemClickListener;

    public OnItemClickListener getListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public View getView(FlowLayout parent, int position, final SearchHistoryBean searchHistoryBean) {
        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_searchhistory_item,
                parent, false);
        tv.setText(searchHistoryBean.getContent());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != onItemClickListener){
                    onItemClickListener.itemClick(searchHistoryBean);
                }
            }
        });
        return tv;
    }

    public SearchHistoryBean getItemData(int position){
        return mTagDatas.get(position);
    }

    public int getDataSize(){
        return mTagDatas.size();
    }
}
