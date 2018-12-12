package com.example.azuredragon.http.impl.module;


import com.example.azuredragon.cache.ACache;
import com.example.azuredragon.http.bean.LibraryBean;
import com.example.azuredragon.http.bean.SearchBookBean;

import java.util.List;

import io.reactivex.Observable;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */
public interface IGxwztvBookModel extends IStationBookModel {

    Observable<List<SearchBookBean>> getKindBook(String url, int page);

    /**
     * 获取主页信息
     */
    Observable<LibraryBean> getLibraryData(ACache aCache);

    /**
     * 解析主页数据
     */
    Observable<LibraryBean> analyLibraryData(String data);
}
