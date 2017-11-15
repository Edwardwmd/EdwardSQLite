package com.wmd.sqlitetest.edwardsqlitetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wmd.sqlitetest.edwardsqlitetest.gen.PersonBean;
import com.wmd.sqlitetest.edwardsqlitetest.gen.PersonDaoUtils;
import com.wmd.sqlitetest.edwardsqlitetest.utils.GreenDaoTAdapter;
import com.wmd.sqlitetest.edwardsqlitetest.utils.ToolsUtils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GreenDaoTActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name, sex, age;
    private Button add, del, query;
    private RecyclerView recy;
    private GreenDaoTAdapter adapter;
    private PersonDaoUtils personDaoUtils;

    private void findViews() {
        name = (EditText) findViewById(R.id.name);
        sex = (EditText) findViewById(R.id.sex);
        age = (EditText) findViewById(R.id.age);
        add = (Button) findViewById(R.id.add);
        query = (Button) findViewById(R.id.query);
        del = (Button) findViewById(R.id.del);
        recy = (RecyclerView) findViewById(R.id.recy);

        add.setOnClickListener(this);
        del.setOnClickListener(this);
        query.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_t);
        findViews();
        personDaoUtils = new PersonDaoUtils(this);
        adapter = new GreenDaoTAdapter(this);
        recy.setLayoutManager(new LinearLayoutManager(this));
        recy.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        if (v == add) {
            Pattern p = Pattern.compile("[0-9]*");
            Matcher mAge = p.matcher(age.getText().toString());
            if (mAge.matches()) {
                final boolean b = personDaoUtils.insertPerson(new PersonBean(ToolsUtils.dateToLong(new Date()), name.getText().toString(), sex.getText().toString(), Integer.parseInt(age.getText().toString())));
                if (b == true) {
                    Toast.makeText(GreenDaoTActivity.this, "添加数据成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GreenDaoTActivity.this, "添加数据失败", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(GreenDaoTActivity.this, "请输入数字", Toast.LENGTH_SHORT).show();
            }


        } else if (v == del) {
            final boolean b = personDaoUtils.delAllPerson();
            if (b == true) {
                Toast.makeText(GreenDaoTActivity.this, "删除数据成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(GreenDaoTActivity.this, "删除数据失败", Toast.LENGTH_SHORT).show();
            }
        } else if (v == query) {
            adapter.setData(personDaoUtils.queryAlPerson());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
