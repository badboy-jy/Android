package com.example.gvegetable;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Pricetrend extends AppCompatActivity implements View.OnClickListener {
    private TextView main,pricetrecd;
    private ImageView back,trend;
    private Spinner spinnerp;
    private List<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pricetrend);
        main  = (TextView) findViewById(R.id.gs_main);
        pricetrecd  = (TextView) findViewById(R.id.pricetrend);
        back = (ImageView) findViewById(R.id.gs_return);
        trend = (ImageView) findViewById(R.id.gs_trend);
        main.setOnClickListener(this);
        back.setOnClickListener(this);
        spinnerp=(Spinner)findViewById(R.id.spinnerp);
        list=new ArrayList<String>();
        list.add("ganlan");
        list.add("baicai");
        list.add("lajiao");
        list.add("qiezi");
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerp.setAdapter(adapter);

        spinnerp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String pinzhong=adapter.getItem(position);
                //String cityName=list.get(position);
                pricetrecd.setText(pinzhong+"的价格走势");
                if (pinzhong.equals("ganlan")){
                    Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.one);
                    trend.setImageBitmap(bitmap);
                }else if  (pinzhong.equals("baicai")){
                    Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.two);
                    trend.setImageBitmap(bitmap);
                }else if (pinzhong.equals("lajiao")){
                    Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.three);
                    trend.setImageBitmap(bitmap);
                }else if (pinzhong.equals("qiezi")){
                    Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.four);
                    trend.setImageBitmap(bitmap);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

        }
    }//注册按钮
}
