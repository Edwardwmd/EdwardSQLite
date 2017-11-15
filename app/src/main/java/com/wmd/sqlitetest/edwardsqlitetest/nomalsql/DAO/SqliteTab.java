package com.wmd.sqlitetest.edwardsqlitetest.nomalsql.DAO;

/**
 * 时间：2017/10/24/18：10
 * 作者：吴明德
 * 邮箱：1732141816@qq.com
 * 作用：数据库表
 * 声明：版权归作者所有
 */

public class SqliteTab {
    public static final String DB_NAME = "contants_info.db";
    public static final String TAB_NAME = "contants_info_tab";
    public static final int DB_VERSION = 1;
    public static final String COL_CONTANTS_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_AGE = "age";


    public static final String CREATE_TABLE = "create table "
            + TAB_NAME + " ("
            + COL_NAME + " text, "
            + COL_AGE + " integer, "
            + COL_CONTANTS_ID + " integer primary key );";
}
