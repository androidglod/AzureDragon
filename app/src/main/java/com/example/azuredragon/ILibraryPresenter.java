package com.example.azuredragon;

import java.util.LinkedHashMap;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:Activity管理器,管理项目中Activity的状态
 */
public interface ILibraryPresenter extends IPresenter{

    LinkedHashMap<String, String> getKinds();

    void getLibraryData();
}
