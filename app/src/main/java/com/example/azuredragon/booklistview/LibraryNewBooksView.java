package com.example.azuredragon.booklistview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.azuredragon.R;
import com.example.azuredragon.http.bean.LibraryNewBookBean;
import com.example.azuredragon.widget.flowlayout.TagFlowLayout;

import java.util.List;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */
public class LibraryNewBooksView extends LinearLayout {
    private TagFlowLayout tflBooks;
    private LibraryNewBooksAdapter libraryNewBooksAdapter;

    public interface OnClickAuthorListener {
        public void clickNewBook(LibraryNewBookBean libraryNewBookBean);
    }

    public LibraryNewBooksView(Context context) {
        super(context);
        init();
    }

    public LibraryNewBooksView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LibraryNewBooksView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("NewApi")
    public LibraryNewBooksView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_library_hotauthor, this, true);
        setOrientation(VERTICAL);
        setVisibility(GONE);
        libraryNewBooksAdapter = new LibraryNewBooksAdapter();
        tflBooks = (TagFlowLayout) findViewById(R.id.tfl_author);
        tflBooks.setAdapter(libraryNewBooksAdapter);
    }

    public void updateData(List<LibraryNewBookBean> datas, OnClickAuthorListener clickAuthorListener) {
        if (datas != null && datas.size() > 0) {
            setVisibility(VISIBLE);
            libraryNewBooksAdapter.setClickNewBookListener(clickAuthorListener);
            libraryNewBooksAdapter.replaceAll(datas);
        } else {
            setVisibility(GONE);
        }
    }
}
