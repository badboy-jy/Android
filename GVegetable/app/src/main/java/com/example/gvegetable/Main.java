package com.example.gvegetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



public class Main extends AppCompatActivity implements View.OnClickListener {
    private TextView login;
    private ImageView back,helper,land,warter,sales,money,pricetrend,news,store,plantexperience,myself;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        login = (TextView) findViewById(R.id.gs_login);
        helper = (ImageView) findViewById(R.id.gs_helper);
        land = (ImageView) findViewById(R.id.gs_land);
        warter = (ImageView) findViewById(R.id.gs_warter);
        sales = (ImageView) findViewById(R.id.gs_sales);
        money = (ImageView) findViewById(R.id.gs_money);
        news = (ImageView) findViewById(R.id.gs_news);
        store = (ImageView) findViewById(R.id.gs_store);
        plantexperience = (ImageView) findViewById(R.id.gs_plantexprience);
        myself = (ImageView) findViewById(R.id.gs_myself);
        back = (ImageView) findViewById(R.id.gs_return);
        pricetrend = (ImageView) findViewById(R.id.gs_pricetrend);
        login.setOnClickListener(this);
        back.setOnClickListener(this);
        helper.setOnClickListener(this);
        land.setOnClickListener(this);
        warter.setOnClickListener(this);
        sales.setOnClickListener(this);
        money.setOnClickListener(this);
        news.setOnClickListener(this);
        store.setOnClickListener(this);
        plantexperience.setOnClickListener(this);
        myself.setOnClickListener(this);
        pricetrend.setOnClickListener(this);
//
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gs_login:  //退出登录
                Intent intent = new Intent(this,Login.class);
                startActivity(intent);
                break;
            case R.id.gs_return:  //
                Intent intent1 = new Intent(this,Login.class);
                startActivity(intent1);
                break;
            case R.id.gs_helper:  //登录按钮
                Intent intent2 =new Intent(this,Helper.class);
                startActivity(intent2);
                break;
            case R.id.gs_land:  //登录按钮
                Intent intent3 = new Intent(this,Land.class);
                startActivity(intent3);
                break;
            case R.id.gs_warter:  //登录按钮
                Intent intent4 = new Intent(this,Water.class);
                startActivity(intent4);
                break;
            case R.id.gs_sales:  //登录按钮
                Intent intent5 = new Intent(this,Sales.class);
                startActivity(intent5);
                break;
            case R.id.gs_money:  //登录按钮
                Intent intent6 = new Intent(this,Money.class);
                startActivity(intent6);
                break;
             case R.id.gs_pricetrend:  //登录按钮
                Intent intent7 = new Intent(this,Pricetrend.class);
                startActivity(intent7);
                break;
            case R.id.gs_news:  //登录按钮
                Intent intent8 = new Intent(this,News.class);
                startActivity(intent8);
                break;
            case R.id.gs_store:  //登录按钮
                Intent intent9 = new Intent(this,Store.class);
                startActivity(intent9);
                break;
            case R.id.gs_plantexprience:  //登录按钮
                Intent intent10 = new Intent(this, Plantjilu.class);
                startActivity(intent10);
                break;
            case R.id.gs_myself:  //登录按钮
                Intent intent11 = new Intent(this,Myself.class);
                startActivity(intent11);
                break;
        }
    }//注册按钮

}
