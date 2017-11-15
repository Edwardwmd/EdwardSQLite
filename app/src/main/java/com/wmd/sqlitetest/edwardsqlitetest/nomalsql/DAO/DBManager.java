package com.wmd.sqlitetest.edwardsqlitetest.nomalsql.DAO;

import android.content.Context;

/**
 * 时间：2017/10/25/15：08
 * 作者：吴明德
 * 邮箱：1732141816@qq.com
 * 作用：数据操作管理类
 * 声明：版权归作者所有
 */

public class DBManager {
    private final DBHelper mDbHelper;
    private final SQLiteDao contactDao;

    public DBManager(Context context) {

        mDbHelper = new DBHelper(context);

        contactDao = new SQLiteDao(mDbHelper);
    }

    // 获取联系人操作类
    public SQLiteDao getContactDao(){
        return contactDao;
    }

    public void close() {
        mDbHelper.close();
    }
}
