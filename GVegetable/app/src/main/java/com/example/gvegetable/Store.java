package com.example.gvegetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Store extends AppCompatActivity implements View.OnClickListener {
    private TextView main;
    private ImageView back,store_all,store_sell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);
        main  = (TextView) findViewById(R.id.gs_main);
        back = (ImageView) findViewById(R.id.gs_return);
        store_all = (ImageView) findViewById(R.id.gs_store_all);
        store_sell = (ImageView) findViewById(R.id.gs_store_sell);
        main.setOnClickListener(this);
        back.setOnClickListener(this);
        store_sell.setOnClickListener(this);
        store_all.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gs_main:  //登录按钮
                Intent intent = new Intent(this,Main.class);
                startActivity(intent);
                break;
            case R.id.gs_return:
                Intent intent1 = new Intent(this,Main.class);
                startActivity(intent1);
                break;
            case R.id.gs_store_all:
                Intent intent2 = new Intent(this,Store_all.class);
                startActivity(intent2);
                break;
            case R.id.gs_store_sell:
                Intent intent4 = new Intent(this,Store_sell.class);
                startActivity(intent4);
                break;

        }
    }//注册按钮
}
