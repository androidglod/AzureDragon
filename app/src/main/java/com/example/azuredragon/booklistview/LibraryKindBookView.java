package com.example.azuredragon.booklistview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.azuredragon.R;
import com.example.azuredragon.http.bean.BookDetailBean;

import java.util.List;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */
public class LibraryKindBookView extends LinearLayout {
    private TextView tvKindName;
    private TextView tvMore;
    private RecyclerView rvBookLIst;
    private LibraryKindBookAdapter libraryKindBookAdapter;
    public LibraryKindBookView(Context context) {
        super(context);
        init();
    }

    public LibraryKindBookView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LibraryKindBookView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("NewApi")
    public LibraryKindBookView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_library_kindbook, this, true);
        tvKindName = (TextView) findViewById(R.id.tv_kindname);
        tvMore = (TextView) findViewById(R.id.tv_more);
        rvBookLIst = (RecyclerView) findViewById(R.id.rv_booklist);
        libraryKindBookAdapter = new LibraryKindBookAdapter();
        rvBookLIst.setAdapter(libraryKindBookAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvBookLIst.setLayoutManager(linearLayoutManager);

        setVisibility(GONE);
    }


    public void updateData(final List<BookDetailBean> data, final LibraryKindBookListView.OnItemListener itemListener){
        updateData(data,itemListener,false);
    }
    public void updateData(final List<BookDetailBean> data, final LibraryKindBookListView.OnItemListener itemListener,Boolean hasMore){
        if(data==null || data.size()==0){
            setVisibility(GONE);
        }else {
            setVisibility(VISIBLE);
        }
        tvKindName.setText(data.get(0).getFictionTypeName());
        if(hasMore){
            tvMore.setVisibility(VISIBLE);
            tvMore.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if(itemListener!=null) {
//                        itemListener.onClickMore(data.getKindName(),data.getKindUrl());
//                    }
                }
            });
        }else{
            tvMore.setVisibility(GONE);
            tvMore.setOnClickListener(null);
        }
        libraryKindBookAdapter.setItemListener(itemListener);
        libraryKindBookAdapter.updateDataAll(data);
    }
}
