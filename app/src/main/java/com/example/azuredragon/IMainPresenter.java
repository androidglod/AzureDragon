package com.example.azuredragon;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:Activity管理器,管理项目中Activity的状态
 */
public interface IMainPresenter extends IPresenter{
    void queryBookShelf(Boolean needRefresh);
}
