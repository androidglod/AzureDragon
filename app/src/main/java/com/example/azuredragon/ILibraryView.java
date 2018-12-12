package com.example.azuredragon;


import com.example.azuredragon.http.bean.LibraryBean;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:Activity管理器,管理项目中Activity的状态
 */
public interface ILibraryView extends IView{

    /**
     * 书城书籍获取成功  更新UI
     * @param library
     */
    void updateUI(LibraryBean library);

    /**
     * 书城数据刷新成功 更新UI
     */
    void finishRefresh();
}
