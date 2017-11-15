package com.wmd.sqlitetest.edwardsqlitetest.nomalsql.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.wmd.sqlitetest.edwardsqlitetest.nomalsql.DAO.SqliteTab.DB_VERSION;

/**
 * 时间：2017/10/25/14：30
 * 作者：
 * 邮箱：1732141816@qq.com
 * 作用：数据库建表
 * 声明：版权归作者所有
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, SqliteTab.DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SqliteTab.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
