package com.example.gvegetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PlantHelper extends AppCompatActivity implements View.OnClickListener {
    private TextView landhelper;
    private ImageView back,planthelper_all,planthelper_fabu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planthelper);
        landhelper  = (TextView) findViewById(R.id.gs_landhelper);
        back = (ImageView) findViewById(R.id.gs_return);
        planthelper_all = (ImageView) findViewById(R.id.gs_planthelper_all);
        planthelper_fabu = (ImageView) findViewById(R.id.gs_planthelper_fabu);
        landhelper.setOnClickListener(this);
        back.setOnClickListener(this);
        planthelper_fabu.setOnClickListener(this);
        planthelper_all.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gs_landhelper:  //登录按钮
                Intent intent = new Intent(this,LandHelper.class);
                startActivity(intent);
                break;
            case R.id.gs_return:
                Intent intent1 = new Intent(this,Helper.class);
                startActivity(intent1);
                break;
            case R.id.gs_planthelper_all:
                Intent intent2 = new Intent(this,PlantHelper_all.class);
                startActivity(intent2);
                break;
            case R.id.gs_planthelper_fabu:
                Intent intent4 = new Intent(this,PlantHelper_fabu.class);
                startActivity(intent4);
                break;

        }
    }//注册按钮
}
