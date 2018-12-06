//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.example.azuredragon.http.impl.module;


import com.example.azuredragon.http.bean.LocBookShelfBean;

import java.io.File;

import io.reactivex.Observable;

public interface IImportBookModel {

    Observable<LocBookShelfBean> importBook(File book);
}
