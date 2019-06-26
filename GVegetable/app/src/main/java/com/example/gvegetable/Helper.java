package com.example.gvegetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Helper extends AppCompatActivity implements View.OnClickListener {
    private TextView main;
    private ImageView back,landhelper,planthelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper);
        main  = (TextView) findViewById(R.id.gs_main);
        back = (ImageView) findViewById(R.id.gs_return);
        landhelper = (ImageView) findViewById(R.id.gs_landhelper);
        planthelper = (ImageView) findViewById(R.id.gs_planthelper);
        main.setOnClickListener(this);
        back.setOnClickListener(this);
        landhelper.setOnClickListener(this);
        planthelper.setOnClickListener(this);

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
            case R.id.gs_landhelper:
                Intent intent2 = new Intent(this,LandHelper.class);
                startActivity(intent2);
                break;
            case R.id.gs_planthelper:
                Intent intent4 = new Intent(this,PlantHelper.class);
                startActivity(intent4);
                break;

        }
    }//注册按钮
}
