package com.example.gvegetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LandHelper extends AppCompatActivity implements View.OnClickListener {
    private TextView planthelper;
    private ImageView back,landhelper_all,landhelper_fabu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landhelper);
        planthelper  = (TextView) findViewById(R.id.gs_planthelper);
        back = (ImageView) findViewById(R.id.gs_return);
        landhelper_all = (ImageView) findViewById(R.id.gs_landhelper_all);
        landhelper_fabu = (ImageView) findViewById(R.id.gs_landhelper_fabu);
        planthelper.setOnClickListener(this);
        back.setOnClickListener(this);
        landhelper_all.setOnClickListener(this);
        landhelper_fabu.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gs_planthelper:  //登录按钮
                Intent intent = new Intent(this,PlantHelper.class);
                startActivity(intent);
                break;
            case R.id.gs_return:
                Intent intent1 = new Intent(this,Helper.class);
                startActivity(intent1);
                break;
            case R.id.gs_landhelper_all:
                Intent intent2 = new Intent(this,LandHelper_all.class);
                startActivity(intent2);
                break;
            case R.id.gs_landhelper_fabu:
                Intent intent4 = new Intent(this,LandHelper_fabu.class);
                startActivity(intent4);
                break;

        }
    }//注册按钮
}
