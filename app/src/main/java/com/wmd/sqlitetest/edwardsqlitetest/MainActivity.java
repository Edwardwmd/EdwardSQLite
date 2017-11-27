package com.wmd.sqlitetest.edwardsqlitetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button greendao,normaldao,rxdao;

    private void findViews() {
        greendao = (Button) findViewById(R.id.greendao);
        normaldao = (Button) findViewById(R.id.normaldao);
        rxdao = (Button) findViewById(R.id.rxdao);
        greendao.setOnClickListener(this);
        normaldao.setOnClickListener(this);
        rxdao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == greendao) {
            Intent intent = new Intent(this, GreenDaoTActivity.class);
            startActivity(intent);
        } else if (v == normaldao) {
            Intent intent = new Intent(this, NormalSqlActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, RxSqlActivity.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViews();
    }


}
