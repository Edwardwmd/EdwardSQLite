package com.wmd.sqlitetest.edwardsqlitetest.nomalsql.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间：2017/10/25/14：35
 * 作者：吴明德
 * 邮箱：1732141816@qq.com
 * 作用：数据库增删改查操作类
 * 声明：版权归作者所有
 */

public class SQLiteDao {
    private DBHelper mHelper;
    private int mConnectionCount;
    private SQLiteDatabase db;
    private static String TAG = SQLiteDao.class.getSimpleName();

    public SQLiteDao(DBHelper helper) {
        mHelper = helper;
    }

    public void open() {
        try {
            if (mConnectionCount == 0) {
                db = mHelper.getReadableDatabase();//打开数据库
                Log.i(TAG, "数据库打开---->");
            }
            mConnectionCount++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        mConnectionCount--;
        if (mConnectionCount == 0) {
            db.close();//关闭数据库
            Log.i(TAG, "数据库关闭---->");
        }
    }


    public void addContantInfo(ContantsBean dhb) {
        // 获取数据库链接
        open();

        ContentValues values = new ContentValues();
        values.put(SqliteTab.COL_CONTANTS_ID, dhb.getContantId());
        values.put(SqliteTab.COL_NAME, dhb.getContantName());
        values.put(SqliteTab.COL_AGE, dhb.getAge());
        db.replace(SqliteTab.TAB_NAME, null, values);
        close();
    }

    /*
   *
   *获取联系人数据
    */
    public List<ContantsBean> getAllContantsInfo() {
        // 获取数据库链接
        open();
        String sql = new StringBuilder("select * from ")
                .append(SqliteTab.TAB_NAME)
//                .append(" where ")
//                .append(SqliteTab.COL_CONTANTS_ID).append(" =?")
                .toString();
        Cursor cursor = db.rawQuery(sql, new String[]{});
        // 创建返回数据的集合
        List<ContantsBean> contants = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                ContantsBean info = new ContantsBean();
                info.setContantId(cursor.getInt(cursor.getColumnIndex(SqliteTab.COL_CONTANTS_ID)));
                info.setContantName(cursor.getString(cursor.getColumnIndex(SqliteTab.COL_NAME)));
                info.setAge(cursor.getInt(cursor.getColumnIndex(SqliteTab.COL_AGE)));
                contants.add(info);
            }
            cursor.close();
        } catch (Exception e) {
            return null;
        }
        close();
        return contants;
    }


}
