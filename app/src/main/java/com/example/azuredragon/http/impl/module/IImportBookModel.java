package com.example.azuredragon.http.impl.module;


import com.example.azuredragon.http.bean.LocBookShelfBean;

import java.io.File;

import io.reactivex.Observable;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */
public interface IImportBookModel {

    Observable<LocBookShelfBean> importBook(File book);
}
