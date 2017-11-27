package com.wmd.sqlitetest.edwardsqlitetest.gen;

import android.content.Context;

import com.wmd.sqlitetest.edwardsqlitetest.gen.impl.RxDaoManager;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * 时间：2017/11/27/15：02
 * 作者：MingDe_Wu
 * 网站：https://github.com/Edwardwmd
 * 作用：
 * 声明：版权归作者所有
 */

public class RxDaoOption implements RxDaoManager {
    private DaoManager manager;
    private static RxDaoOption instance;

    public static RxDaoOption getInstance() {
        if (instance == null) {
            instance = new RxDaoOption();
        }
        return instance;
    }

    public void init(Context context) {
        manager = DaoManager.getInstance();
        manager.init(context);
        manager.setDebug();
    }


    @Override
    public synchronized Observable<Boolean> insertOne(final PersonBean personBean) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                try {
                    e.onNext(manager.getDaoSession().insert(personBean) == -1 ? false : true);
                    e.onComplete();
                } finally {
                    //关闭数据库
                    manager.closeConnection();
                }

            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public synchronized Observable<Boolean> insertMuilt(final List<PersonBean> personBeans) {

        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                boolean flag = false;
                try {
                    //遍历插入数据
                    for (PersonBean personBean : personBeans) {
                        manager.getDaoSession().insertOrReplace(personBean);
                    }
                    flag = true;
                } finally {
                    e.onNext(flag);
                    e.onComplete();
                    //关闭数据库
                    manager.closeConnection();
                }
            }
        });
    }

    @Override
    public synchronized Observable<List<PersonBean>> queryAllData() {
        return Observable.create(new ObservableOnSubscribe<List<PersonBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<PersonBean>> e) throws Exception {
                e.onNext(manager.getDaoSession().loadAll(PersonBean.class));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> delAllData() {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                boolean flag = false;
                try {
                    if (manager.getDaoSession().loadAll(PersonBean.class).size() > 0) {
                        manager.getDaoSession().deleteAll(PersonBean.class);
                        flag = true;
                    } else {
                        flag = false;
                    }

                } finally {
                    e.onNext(flag);
                    e.onComplete();
                    manager.closeConnection();
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> delByID(final Long id) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                boolean flag = false;
                try {
                    PersonBeanDao personBeanDao = manager.getDaoSession().getPersonBeanDao();
                    personBeanDao.deleteByKey(id);
                    flag = true;
                } finally {
                    e.onNext(flag);
                    e.onComplete();
                    manager.closeConnection();
                }
            }
        });
    }


}
