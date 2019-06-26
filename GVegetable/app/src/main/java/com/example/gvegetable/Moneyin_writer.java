package com.example.gvegetable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Moneyin_writer extends AppCompatActivity implements View.OnClickListener {
    private TextView money_in,to_result;
    private ImageView back;
    private Button moneyin_writer;
    private EditText moneyin_pin1,moneyin_pin2,moneyin_pin3,moneyin_pin4,moneyin_pin5,moneyin_price1,moneyin_price2,moneyin_price3,moneyin_price4,
            moneyin_price5,moneyin_shuliang1,moneyin_shuliang2,moneyin_shuliang3,moneyin_shuliang4,moneyin_shuliang5;
    private String price1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moneyin_writer);
        money_in   = (TextView) findViewById(R.id.gs_money_in);
        back = (ImageView) findViewById(R.id.gs_return);
        moneyin_writer =(Button) findViewById(R.id.moneyin_writer);
        moneyin_pin1   = (EditText) findViewById(R.id.gs_moneyin_pinzhong1);
        moneyin_pin2   = (EditText) findViewById(R.id.gs_moneyin_pinzhong2);
        moneyin_pin3   = (EditText) findViewById(R.id.gs_moneyin_pinzhong3);
        moneyin_pin4   = (EditText) findViewById(R.id.gs_moneyin_pinzhong4);
        moneyin_pin5   = (EditText) findViewById(R.id.gs_moneyin_pinzhong5);
        moneyin_price1   = (EditText) findViewById(R.id.gs_price1);
        moneyin_price2   = (EditText) findViewById(R.id.gs_price2);
        moneyin_price3  = (EditText) findViewById(R.id.gs_price3);
        moneyin_price4   = (EditText) findViewById(R.id.gs_price4);
        moneyin_price5   = (EditText) findViewById(R.id.gs_price5);
        moneyin_shuliang1   = (EditText) findViewById(R.id.gs_shuliang1);
        moneyin_shuliang2   = (EditText) findViewById(R.id.gs_shuliang2);
        moneyin_shuliang3   = (EditText) findViewById(R.id.gs_shuliang3);
        moneyin_shuliang4   = (EditText) findViewById(R.id.gs_shuliang4);
        moneyin_shuliang5   = (EditText) findViewById(R.id.gs_shuliang5);
        to_result =(TextView) findViewById(R.id.to_result);
        money_in.setOnClickListener(this);
        back.setOnClickListener(this);
        moneyin_writer.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gs_money_in:  //登录按钮
                Intent intent = new Intent(this,Money_in.class);
                startActivity(intent);
                break;
            case R.id.gs_return:
                Intent intent1 = new Intent(this,Money_in.class);
                startActivity(intent1);
                break;
            case R.id.moneyin_writer:
               moneyin();
                break;


        }
    }//注册按钮
    private void moneyin() {
        final String pin1 = moneyin_pin1.getText().toString().trim(); //获取用户名
        final String pin2 = moneyin_pin2.getText().toString().trim(); //获取用户名
        final String pin3 = moneyin_pin3.getText().toString().trim(); //获取用户名
        final String pin4 = moneyin_pin4.getText().toString().trim(); //获取用户名
        final String pin5 = moneyin_pin5.getText().toString().trim(); //获取
        price1 = moneyin_price1.getText().toString().trim();
        final String price2 = moneyin_price2.getText().toString().trim(); //获取时间
        final String price3 = moneyin_price3.getText().toString().trim(); //获取时间
        final String price4 = moneyin_price4.getText().toString().trim(); //获取时间
        final String price5 = moneyin_price5.getText().toString().trim(); //获取时间
        String shuliang1 = moneyin_shuliang1.getText().toString().trim();//获取手机号
        String shuliang2 = moneyin_shuliang2.getText().toString().trim();//获取手机号
        String shuliang3 = moneyin_shuliang3.getText().toString().trim();//获取手机号
        String shuliang4 = moneyin_shuliang4.getText().toString().trim();//获取手机号
        String shuliang5 = moneyin_shuliang5.getText().toString().trim();//获取手机号
        final ProgressDialog pd = new ProgressDialog(this); //等待动画
        pd.setMessage("正在保存···");
        pd.show();


        //模拟后台
        Intent intent =new Intent(this,Money_in.class);
        intent.putExtra("pin1",pin1);
        intent.putExtra("pin2",pin2);
        intent.putExtra("pin3",pin3);
        intent.putExtra("pin4",pin4);
        intent.putExtra("pin5",pin5);
        intent.putExtra("price1", price1);
        intent.putExtra("price2",price2);
        intent.putExtra("price3",price3);
        intent.putExtra("price4",price4);
        intent.putExtra("price5",price5);
        intent.putExtra("shuliang1",shuliang1);
        intent.putExtra("shuliang2",shuliang2);
        intent.putExtra("shuliang3",shuliang3);
        intent.putExtra("shuliang4",shuliang4);
        intent.putExtra("shuliang5",shuliang5);
        startActivity(intent);
        pd.dismiss();
        to_result.setText("保存成功");
    }

}
