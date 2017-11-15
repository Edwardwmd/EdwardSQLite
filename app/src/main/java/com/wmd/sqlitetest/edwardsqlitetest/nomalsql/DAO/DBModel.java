package com.wmd.sqlitetest.edwardsqlitetest.nomalsql.DAO;

import android.content.Context;

/**
 * 时间：2017/10/25/15：17
 * 作者：吴明德
 * 邮箱：1732141816@qq.com
 * 作用：标准数据库管理类
 * 声明：版权归作者所有
 */

public class DBModel {
    private DBManager mDbManager;
    private static DBModel instance;

    public static DBModel getInstance() {
        if (instance == null) {
            synchronized (DBModel.class) {
                if (instance == null) {
                    instance = new DBModel();
                }
            }
        }
        return instance;
    }

    public void inint(Context mContext) {
        if (mDbManager != null) {
            mDbManager.close();
        }
        mDbManager = new DBManager(mContext);
    }

    public DBManager getmDbManager() {
        return mDbManager;
    }
}
