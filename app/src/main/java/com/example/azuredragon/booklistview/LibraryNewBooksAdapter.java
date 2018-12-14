package com.example.azuredragon.booklistview;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.azuredragon.R;
import com.example.azuredragon.http.bean.LibraryNewBookBean;
import com.example.azuredragon.widget.flowlayout.FlowLayout;
import com.example.azuredragon.widget.flowlayout.TagAdapter;

import java.util.ArrayList;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */
public class LibraryNewBooksAdapter extends TagAdapter<LibraryNewBookBean> {
    private LibraryNewBooksView.OnClickAuthorListener clickNewBookListener;

    public LibraryNewBooksAdapter() {
        super(new ArrayList<LibraryNewBookBean>());
    }

    @Override
    public View getView(FlowLayout parent, int position, final LibraryNewBookBean libraryNewBookBean) {
        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_library_hotauthor_item,
                parent, false);
        tv.setText(libraryNewBookBean.getName());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != clickNewBookListener){
                    clickNewBookListener.clickNewBook(libraryNewBookBean);
                }
            }
        });
        return tv;
    }

    public LibraryNewBookBean getItemData(int position){
        return mTagDatas.get(position);
    }

    public int getDataSize(){
        return mTagDatas.size();
    }

    public void setClickNewBookListener(LibraryNewBooksView.OnClickAuthorListener clickNewBookListener) {
        this.clickNewBookListener = clickNewBookListener;
    }
}
