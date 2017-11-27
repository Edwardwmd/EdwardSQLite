package com.wmd.sqlitetest.edwardsqlitetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wmd.adapter.baserecyadapter.listener.OnItemClickListener;
import com.wmd.sqlitetest.edwardsqlitetest.adapter.RxAdapter;
import com.wmd.sqlitetest.edwardsqlitetest.gen.PersonBean;
import com.wmd.sqlitetest.edwardsqlitetest.gen.RxDaoOption;
import com.wmd.sqlitetest.edwardsqlitetest.utils.ToolsUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxSqlActivity extends AppCompatActivity implements View.OnClickListener, OnItemClickListener {

    private EditText name, sex, age;
    private Button insertone, insertmulit, delall, queryall;
    private RecyclerView recy;
    private RxAdapter adapter;
    private List<PersonBean> mPs = new ArrayList<>();

    private void findViews() {
        name = (EditText) findViewById(R.id.name);
        sex = (EditText) findViewById(R.id.sex);
        age = (EditText) findViewById(R.id.age);
        insertone = (Button) findViewById(R.id.insertone);
        insertmulit = (Button) findViewById(R.id.insertmulit);
        delall = (Button) findViewById(R.id.delall);
        queryall = (Button) findViewById(R.id.queryall);
        recy = (RecyclerView) findViewById(R.id.recy);
        RxDaoOption.getInstance().init(this);
        adapter = new RxAdapter(this);
        recy.setLayoutManager(new LinearLayoutManager(this));
        adapter.initLayout();
        recy.setAdapter(adapter);
        insertone.setOnClickListener(this);
        insertmulit.setOnClickListener(this);
        delall.setOnClickListener(this);
        queryall.setOnClickListener(this);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == insertone) {
            insertOneData();

        } else if (v == insertmulit) {
            insertMulitData();

        } else if (v == delall) {
            delAllData();

        } else if (v == queryall) {
            queryAllData();

        }
    }

    private void insertMulitData() {
        mPs.add(new PersonBean(ToolsUtils.dateToLong(new Date()), name.getText().toString(), sex.getText().toString(), Integer.parseInt(age.getText().toString())));

        RxDaoOption
                .getInstance()
                .insertMuilt(mPs)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Toast.makeText(RxSqlActivity.this, aBoolean == true ? "添加多条数据成功" : "删除多条数据失败", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void queryAllData() {

        RxDaoOption
                .getInstance()
                .queryAllData()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<PersonBean>>() {
                    @Override
                    public void accept(List<PersonBean> personBeans) throws Exception {
                        adapter.setData(personBeans);
                    }
                });

    }

    private void delAllData() {
        RxDaoOption
                .getInstance()
                .delAllData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Toast.makeText(RxSqlActivity.this, aBoolean == true ? "删除数据成功" : "删除数据失败", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void insertOneData() {
        if (ToolsUtils.isNumByKeyBoard(age)) {

            RxDaoOption
                    .getInstance()
                    .insertOne(new PersonBean(ToolsUtils.dateToLong(new Date()), name.getText().toString(), sex.getText().toString(), Integer.parseInt(age.getText().toString())))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            Toast.makeText(RxSqlActivity.this, aBoolean == true ? "添加数据成功" : "添加数据失败", Toast.LENGTH_SHORT).show();

                        }
                    });
        } else {
            Toast.makeText(RxSqlActivity.this, "请输入数字", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_sql);
        findViews();
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, final int position) {
        final List<PersonBean> datas = adapter.getDatas();
        RxDaoOption
                .getInstance()
                .delByID(datas.get(position).getId())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Toast.makeText(RxSqlActivity.this, aBoolean == true ? "删除(" + datas.get(position).getId() + ")数据成功" : "删除数据失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
