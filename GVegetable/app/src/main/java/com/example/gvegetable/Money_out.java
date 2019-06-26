package com.example.gvegetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Money_out extends AppCompatActivity implements View.OnClickListener {
    private TextView money_in;
    private  Integer pr1,pr2,pr3,pr4,pr5;
    private ImageView back;
    private Button moneyout_writer;
    private TextView money_to1,money_to2,money_to3,money_to4,money_to5,moneyout_price1,moneyout_price2,moneyout_price3,moneyout_price4,
            moneyout_price5,priceadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money_out);
        money_in   = (TextView) findViewById(R.id.gs_money_in);
        back = (ImageView) findViewById(R.id.gs_return);
        moneyout_writer =(Button) findViewById(R.id.moneyout_writer);
        money_to1   = (TextView) findViewById(R.id.gs_money_to1);
        money_to2   = (TextView) findViewById(R.id.gs_money_to2);
        money_to3   = (TextView) findViewById(R.id.gs_money_to3);
        money_to4   = (TextView) findViewById(R.id.gs_money_to4);
        money_to5   = (TextView) findViewById(R.id.gs_money_to5);
        moneyout_price1   = (TextView) findViewById(R.id.gs_price1);
        moneyout_price2   = (TextView) findViewById(R.id.gs_price2);
        moneyout_price3  = (TextView) findViewById(R.id.gs_price3);
        moneyout_price4   = (TextView) findViewById(R.id.gs_price4);
        moneyout_price5   = (TextView) findViewById(R.id.gs_price5);
        priceadd   = (TextView) findViewById(R.id.gs_priceadd);
        money_in.setOnClickListener(this);
        back.setOnClickListener(this);
        moneyout_writer.setOnClickListener(this);
        Intent intent =getIntent();
        String to1 = intent.getStringExtra("to1");
        String to2 = intent.getStringExtra("to2");
        String to3 = intent.getStringExtra("to3");
        String to4 = intent.getStringExtra("to4");
        String to5 = intent.getStringExtra("to5");
        String price1 = intent.getStringExtra("pric1");
        String price2 = intent.getStringExtra("pric2");
        String price3 = intent.getStringExtra("pric3");
        String price4 = intent.getStringExtra("pric4");
        String price5 = intent.getStringExtra("pric5");
        money_to1.setText(to1);
        money_to2.setText(to2);
        money_to3.setText(to3);
        money_to4.setText(to4);
        money_to5.setText(to5);
        moneyout_price1.setText(price1);
        moneyout_price2.setText(price2);
        moneyout_price3.setText(price3);
        moneyout_price4.setText(price4);
        moneyout_price5.setText(price5);
        if (  price1==null||price1.equals("") ){
            pr1=0;
        }
        else{
            pr1 = Integer.valueOf(price1);
        }
        if (price2==null ||price2.equals("")){
            pr2=0;
        }else{
            pr2 = Integer.valueOf(price2);
        }
        if (price3==null||price3.equals("")){
            pr3=0;
        }else{
            pr3 = Integer.valueOf(price3);
        }
        if (price4==null ||price4.equals("")){
            pr4=0;
        }else{
            pr4 = Integer.valueOf(price4);
        }
        if (price5==null||price5.equals("") ){
            pr5=0;
        }else {
            pr5 = Integer.valueOf(price5);
        }

        int add = pr1 + pr2 + pr3 + pr4 + pr5;
       String a = Integer.toString(add);
        priceadd.setText(a);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gs_money_in:  //登录按钮
                Intent intent = new Intent(this,Money_in.class);
                startActivity(intent);
                break;
            case R.id.gs_return:
                Intent intent1 = new Intent(this,Money.class);
                startActivity(intent1);
                break;
            case R.id.moneyout_writer:
                Intent intent2 = new Intent(this,Moneyout_writer.class);
                startActivity(intent2);
                break;


        }
    }//注册按钮
}
