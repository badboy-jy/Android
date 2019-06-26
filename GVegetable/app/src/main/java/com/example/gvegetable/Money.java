package com.example.gvegetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Money extends AppCompatActivity implements View.OnClickListener {
    private TextView main;
    private ImageView back,money_in,money_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money);
        main   = (TextView) findViewById(R.id.gs_main);
        back = (ImageView) findViewById(R.id.gs_return);
        money_in = (ImageView) findViewById(R.id.gs_money_in);
        money_out = (ImageView) findViewById(R.id.gs_money_out);
        main.setOnClickListener(this);
        back.setOnClickListener(this);
        money_out.setOnClickListener(this);
        money_in.setOnClickListener(this);

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
            case R.id.gs_money_in:
                Intent intent2 = new Intent(this,Money_in.class);
                startActivity(intent2);
                break;
            case R.id.gs_money_out:
                Intent intent4 = new Intent(this,Money_out.class);
                startActivity(intent4);
                break;

        }
    }//注册按钮
}
