package com.example.classhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class Main extends AppCompatActivity implements View.OnClickListener {
    private ImageView banji,activity,jilu,activityout;;
    private TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        banji = (ImageView) findViewById(R.id.banji);
        activity = (ImageView) findViewById(R.id.activity);
        jilu = (ImageView) findViewById(R.id.jilu);
        activityout = (ImageView) findViewById(R.id.activityout);
        back = (TextView) findViewById(R.id.back);
        banji.setOnClickListener(this);
        back.setOnClickListener(this);
        jilu.setOnClickListener(this);
        activity.setOnClickListener(this);
        activityout.setOnClickListener(this);
//
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:  //退出登录
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                break;
            case R.id.banji:  //
                Intent intent1 = new Intent(this, Banji.class);
                startActivity(intent1);
                break;
            case R.id.activity:  //登录按钮
                Intent intent2 = new Intent(this, Huodong.class);
                startActivity(intent2);
                break;
            case R.id.jilu:  //登录按钮
                Intent intent3 = new Intent(this, Jilu.class);
                startActivity(intent3);
                break;
            case R.id.activityout:  //登录按钮
                Intent intent4 = new Intent(this, Activityo.class);
                startActivity(intent4);
                break;
        }
    }//注册按钮

}
