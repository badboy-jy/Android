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

public class Moneyout_writer extends AppCompatActivity implements View.OnClickListener {
    private TextView money_out,to_result;
    private ImageView back;
    private Button moneyout_writer;
    private EditText money_to1,money_to2,money_to3,money_to4,money_to5,moneyout_price1,moneyout_price2,moneyout_price3,moneyout_price4,
            moneyout_price5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moneyout_writer);
        money_out   = (TextView) findViewById(R.id.gs_money_out);
        back = (ImageView) findViewById(R.id.gs_return);
        moneyout_writer =(Button) findViewById(R.id.moneyout_writer);
        money_to1   = (EditText) findViewById(R.id.gs_money_to1);
        money_to2   = (EditText) findViewById(R.id.gs_money_to2);
        money_to3   = (EditText) findViewById(R.id.gs_money_to3);
        money_to4   = (EditText) findViewById(R.id.gs_money_to4);
        money_to5   = (EditText) findViewById(R.id.gs_money_to5);
        moneyout_price1   = (EditText) findViewById(R.id.gs_price1);
        moneyout_price2   = (EditText) findViewById(R.id.gs_price2);
        moneyout_price3  = (EditText) findViewById(R.id.gs_price3);
        moneyout_price4   = (EditText) findViewById(R.id.gs_price4);
        moneyout_price5   = (EditText) findViewById(R.id.gs_price5);
        to_result =(TextView) findViewById(R.id.to_result);
        money_out.setOnClickListener(this);
        back.setOnClickListener(this);
        moneyout_writer.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gs_money_out:  //登录按钮
                Intent intent = new Intent(this,Money_out.class);
                startActivity(intent);
                break;
            case R.id.gs_return:
                Intent intent1 = new Intent(this,Money_out.class);
                startActivity(intent1);
                break;
            case R.id.moneyout_writer:
                moneyout();
                break;


        }
    }//注册按钮
    private void moneyout() {
        final String pin1 = money_to1.getText().toString().trim(); //获取用户名
        final String pin2 = money_to2.getText().toString().trim(); //获取用户名
        final String pin3 = money_to3.getText().toString().trim(); //获取用户名
        final String pin4 = money_to4.getText().toString().trim(); //获取用户名
        final String pin5 = money_to5.getText().toString().trim(); //获取
        final String price1 = moneyout_price1.getText().toString().trim(); //获取时间
        final String price2 = moneyout_price2.getText().toString().trim(); //获取时间
        final String price3 = moneyout_price3.getText().toString().trim(); //获取时间
        final String price4 = moneyout_price4.getText().toString().trim(); //获取时间
        final String price5 = moneyout_price5.getText().toString().trim(); //获取时间
        final ProgressDialog pd = new ProgressDialog(this); //等待动画
        pd.setMessage("正在保存···");
        pd.show();

        //模拟后台
        Intent intent =new Intent(this,Money_out.class);
        intent.putExtra("to1",pin1);
        intent.putExtra("to2",pin2);
        intent.putExtra("to3",pin3);
        intent.putExtra("to4",pin4);
        intent.putExtra("to5",pin5);
        intent.putExtra("pric1",price1);
        intent.putExtra("pric2",price2);
        intent.putExtra("pric3",price3);
        intent.putExtra("pric4",price4);
        intent.putExtra("pric5",price5);
        startActivity(intent);
        pd.dismiss();
        to_result.setText("保存成功");
    }

}
