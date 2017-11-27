package com.wmd.sqlitetest.edwardsqlitetest.gen.impl;

import com.wmd.sqlitetest.edwardsqlitetest.gen.PersonBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * 时间：2017/11/27/15：16
 * 作者：MingDe_Wu
 * 网站：https://github.com/Edwardwmd
 * 作用：数据库增删改查接口
 * 声明：版权归作者所有
 */

public interface RxDaoManager {
    Observable<Boolean> insertOne(PersonBean personBean);

    Observable<Boolean> insertMuilt(List<PersonBean> personBeans);

    Observable<List<PersonBean>> queryAllData();

    Observable<Boolean> delAllData();

    Observable<Boolean> delByID(Long id);


}
