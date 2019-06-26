package com.example.gvegetable;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Money_in extends AppCompatActivity implements View.OnClickListener {
    private TextView money_out;
    private  Integer pr1,pr2,pr3,pr4,pr5,sh1,sh2,sh3,sh4,sh5;
    private String a;
    private ImageView back;
    private Button moneyin_writer;
    private TextView moneyin_pin1,moneyin_pin2,moneyin_pin3,moneyin_pin4,moneyin_pin5,moneyin_price1,moneyin_price2,moneyin_price3,moneyin_price4,
            moneyin_price5,moneyin_shuliang1,moneyin_shuliang2,moneyin_shuliang3,moneyin_shuliang4,moneyin_shuliang5,priceadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money_in);
        money_out   = (TextView) findViewById(R.id.gs_money_out);
        back = (ImageView) findViewById(R.id.gs_return);
        moneyin_writer =(Button) findViewById(R.id.moneyin_writer);
        moneyin_pin1   = (TextView) findViewById(R.id.gs_moneyin_pinzhong1);
        moneyin_pin2   = (TextView) findViewById(R.id.gs_moneyin_pinzhong2);
        moneyin_pin3   = (TextView) findViewById(R.id.gs_moneyin_pinzhong3);
        moneyin_pin4   = (TextView) findViewById(R.id.gs_moneyin_pinzhong4);
        moneyin_pin5   = (TextView) findViewById(R.id.gs_moneyin_pinzhong5);
        moneyin_price1   = (TextView) findViewById(R.id.gs_price1);
        moneyin_price2   = (TextView) findViewById(R.id.gs_price2);
        moneyin_price3  = (TextView) findViewById(R.id.gs_price3);
        moneyin_price4   = (TextView) findViewById(R.id.gs_price4);
        moneyin_price5   = (TextView) findViewById(R.id.gs_price5);
        moneyin_shuliang1   = (TextView) findViewById(R.id.gs_shuliang1);
        moneyin_shuliang2   = (TextView) findViewById(R.id.gs_shuliang2);
        moneyin_shuliang3   = (TextView) findViewById(R.id.gs_shuliang3);
        moneyin_shuliang4   = (TextView) findViewById(R.id.gs_shuliang4);
        moneyin_shuliang5   = (TextView) findViewById(R.id.gs_shuliang5);
        priceadd   = (TextView) findViewById(R.id.gs_priceadd);
        money_out.setOnClickListener(this);
        back.setOnClickListener(this);
        moneyin_writer.setOnClickListener(this);
        Intent intent =getIntent();
        String pin1 = intent.getStringExtra("pin1");
        String pin2 = intent.getStringExtra("pin2");
        String pin3 = intent.getStringExtra("pin3");
        String pin4 = intent.getStringExtra("pin4");
        String pin5 = intent.getStringExtra("pin5");
        String price1 = intent.getStringExtra("price1");
        String price2 = intent.getStringExtra("price2");
        String price3 = intent.getStringExtra("price3");
        String price4 = intent.getStringExtra("price4");
        String price5 = intent.getStringExtra("price5");
        String shu1 = intent.getStringExtra("shuliang1");
        String shu2 = intent.getStringExtra("shuliang2");
        String shu3 = intent.getStringExtra("shuliang3");
        String shu4 = intent.getStringExtra("shuliang4");
        String shu5 = intent.getStringExtra("shuliang5");
        moneyin_pin1.setText(pin1);
        moneyin_pin2.setText(pin2);
        moneyin_pin3.setText(pin3);
        moneyin_pin4.setText(pin4);
        moneyin_pin5.setText(pin5);
        moneyin_price1.setText(price1);
        moneyin_price2.setText(price2);
        moneyin_price3.setText(price3);
        moneyin_price4.setText(price4);
        moneyin_price5.setText(price5);
        moneyin_shuliang1.setText(shu1);
        moneyin_shuliang2.setText(shu2);
        moneyin_shuliang3.setText(shu3);
        moneyin_shuliang4.setText(shu4);
        moneyin_shuliang5.setText(shu5);
        if (price1==null ||  price1.equals("")|| shu1==null||shu1.equals("")){
           pr1=0;
           sh1=0;
       }
       else{
           pr1 = Integer.valueOf(price1);
           sh1 = Integer.valueOf(shu1);
        }
     if (price2==null || price2.equals("")|| shu2==null||shu2.equals("")){
            pr2=0;
            sh2=0;
        }else{
           pr2 = Integer.valueOf(price2);
           sh2 = Integer.valueOf(shu2);
        }
        if (price3==null || price3.equals("")||  shu3==null||shu3.equals("")){
            pr3=0;
            sh3 =0;
        }else{
            sh3 = Integer.valueOf(shu3);
            pr3 = Integer.valueOf(price3);
        }
        if (price4==null || price4.equals("")|| shu4==null||shu4.equals("")){
            pr4=0;
            sh4=0;
        }else{
            pr4 = Integer.valueOf(price4);
            sh4 = Integer.valueOf(shu4);

        }
        if (price5==null || price5.equals("")|| shu5==null||shu5.equals("")){
            pr5=0;
            sh5=0;
        }else {
            pr5 = Integer.valueOf(price5);
            sh5 = Integer.valueOf(shu5);
        }
            int add1 = pr1 * sh1;
            int add2 = pr2 * sh2;
            int add3 = pr3 * sh3;
            int add4 = pr4 * sh4;
            int add5 = pr5 * sh5;
            int add = add1 + add2 + add3 + add4 + add5;
           a = Integer.toString(add);
           priceadd.setText(a);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gs_money_out:  //登录按钮
                Intent intent = new Intent(this,Money_out.class);
                startActivity(intent);
                break;
            case R.id.gs_return:
                Intent intent1 = new Intent(this,Money.class);
                startActivity(intent1);
                break;
            case R.id.moneyin_writer:
                Intent intent2 = new Intent(this,Moneyin_writer.class);
                startActivity(intent2);
                break;


        }
    }//注册按钮
}
