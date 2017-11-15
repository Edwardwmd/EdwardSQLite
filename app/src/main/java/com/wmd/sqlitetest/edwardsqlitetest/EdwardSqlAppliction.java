package com.wmd.sqlitetest.edwardsqlitetest;

import android.app.Application;
import android.content.Context;

import com.wmd.sqlitetest.edwardsqlitetest.nomalsql.DAO.DBModel;

/**
 * 时间：2017/10/24/18：03
 * 作者：吴明德
 * 邮箱：1732141816@qq.com
 * 作用：应用
 * 声明：版权归作者所有
 */

public class EdwardSqlAppliction extends Application {
    private static Context mContext;
    private final static float TARGET_HEAP_UTILIZATION = 0.75f;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = EdwardSqlAppliction.this;
        DBModel.getInstance().inint(mContext);
    }

}
