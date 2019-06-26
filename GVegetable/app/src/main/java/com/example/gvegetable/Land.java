package com.example.gvegetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Land extends AppCompatActivity implements View.OnClickListener {
    private TextView main;
    private ImageView back,rent_all,rent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landrent);
        rent_all  = (ImageView) findViewById(R.id.gs_rent_all);
        back = (ImageView) findViewById(R.id.gs_return);
        rent = (ImageView) findViewById(R.id.gs_rent);
        main = (TextView) findViewById(R.id.gs_main);
        main.setOnClickListener(this);
        back.setOnClickListener(this);
        rent.setOnClickListener(this);
        rent_all.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gs_planthelper:  //主页按钮
                Intent intent = new Intent(this,Main.class);
                startActivity(intent);
                break;
            case R.id.gs_return:
                Intent intent1 = new Intent(this,Main.class);
                startActivity(intent1);
                break;
            case R.id.gs_rent:
                Intent intent2 = new Intent(this,Rent.class);
                startActivity(intent2);
                break;
            case R.id.gs_rent_all:
                Intent intent4 = new Intent(this,Rent_all.class);
                startActivity(intent4);
                break;

        }
    }//注册按钮
}
