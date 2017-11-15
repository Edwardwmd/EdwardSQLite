package com.wmd.sqlitetest.edwardsqlitetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button greendao;
    private Button normaldao;

    private void findViews() {
        greendao = (Button) findViewById(R.id.greendao);
        normaldao = (Button) findViewById(R.id.normaldao);
        greendao.setOnClickListener(this);
        normaldao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == greendao) {
            Intent intent = new Intent(this, GreenDaoTActivity.class);
            startActivity(intent);
        } else if (v == normaldao) {
            Intent intent = new Intent(this, NormalSqlActivity.class);
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
