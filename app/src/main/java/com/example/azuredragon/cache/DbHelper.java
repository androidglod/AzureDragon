//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.example.azuredragon.cache;

import android.database.sqlite.SQLiteDatabase;

import com.com.sky.downloader.greendao.DaoMaster;
import com.com.sky.downloader.greendao.DaoSession;
import com.example.azuredragon.http.app.MyApp;

public class DbHelper {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private DbHelper(){
        mHelper = new DaoMaster.DevOpenHelper(MyApp.getInstance(), "monkebook_db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    private static DbHelper instance;

    public static DbHelper getInstance(){
        if(null == instance){
            synchronized (DbHelper.class){
                if(null == instance){
                    instance = new DbHelper();
                }
            }
        }
        return instance;
    }

    public DaoSession getmDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
