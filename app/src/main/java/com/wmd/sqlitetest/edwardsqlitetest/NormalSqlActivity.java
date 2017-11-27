package com.wmd.sqlitetest.edwardsqlitetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.wmd.sqlitetest.edwardsqlitetest.nomalsql.DAO.ContantsBean;
import com.wmd.sqlitetest.edwardsqlitetest.nomalsql.DAO.DBModel;
import com.wmd.sqlitetest.edwardsqlitetest.adapter.ShowDataAdapter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NormalSqlActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "TAG";
    private Button btnAdd;
    private Button btnShow;
    private EditText id;
    private EditText name;
    private EditText age;
    private ListView listview;
    private ContantsBean.Builder builder;

    private void findViews() {
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnShow = (Button) findViewById(R.id.btn_show);
        id = (EditText) findViewById(R.id.et_id);
        name = (EditText) findViewById(R.id.et_name);
        age = (EditText) findViewById(R.id.et_age);
        listview = (ListView) findViewById(R.id.listview);
        btnAdd.setOnClickListener(this);
        btnShow.setOnClickListener(this);
        builder = new ContantsBean.Builder();
    }

    @Override
    public void onClick(View v) {
        if (v == btnAdd) {
            //判断输入范围
            Pattern p = Pattern.compile("[0-9]*");
            Matcher mId = p.matcher(id.getText().toString());
            Matcher mAge = p.matcher(age.getText().toString());
            if (mId.matches() && mAge.matches()) {
                //new ContantsBean(Integer.parseInt(id.getText().toString()), name.getText().toString(), Integer.parseInt(age.getText().toString()))
                DBModel.getInstance().getmDbManager().getContactDao().addContantInfo(builder.
                        id(Integer.parseInt(id.getText().toString())).
                        name(name.getText().toString()).
                        age(Integer.parseInt(age.getText().toString())).
                        build());
            } else {
                Toast.makeText(NormalSqlActivity.this, "请输入数字", Toast.LENGTH_SHORT).show();
            }


        } else if (v == btnShow) {
            final List<ContantsBean> allContantsInfo = DBModel.getInstance().getmDbManager().getContactDao().getAllContantsInfo();
            final ShowDataAdapter ad = new ShowDataAdapter(this);
            listview.setAdapter(ad);
            ad.setData(allContantsInfo);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

}
