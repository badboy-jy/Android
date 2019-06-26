package com.example.gvegetable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class Water_order1 extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextN,editTextT,editTextW,editTextPhone; //用户名，时间，微信，手机号
    private Button buttonOrder; //发布按钮
    private ImageView returnImage; //返回按钮
    private TextView te_result,water;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_order1); //绑定布局
        init(); //初始化控件
    }

    private void init() {
        editTextN = (EditText) findViewById(R.id.gs_name); //绑定控件
        editTextT = (EditText) findViewById(R.id.gs_time);
        editTextPhone = (EditText) findViewById(R.id.gs_phone);
        editTextW = (EditText) findViewById(R.id.gs_weixin);
        buttonOrder = (Button) findViewById(R.id.gs_warter_order);
        water = (TextView) findViewById(R.id.gs_warter);
        returnImage = (ImageView) findViewById(R.id.gs_return);
        te_result = (TextView) findViewById(R.id.gs_result);
        buttonOrder.setOnClickListener(this); //点击事件
        water.setOnClickListener(this);
        returnImage.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gs_warter_order:
                waterorder(); //发布方法
                break;
            case R.id.gs_warter:
                Intent intent = new Intent(this,Water.class); //跳转页面
                startActivity(intent);
                break;
            case R.id.gs_return:
                Intent intent1 = new Intent(this,Water.class); //跳转页面
                startActivity(intent1);
                break;

        }

    }

    private void waterorder() {
        final String name = editTextN.getText().toString().trim(); //获取用户名
        final String time = editTextT.getText().toString().trim(); //获取时间
        String phone = editTextPhone.getText().toString().trim();//获取手机号
        String weixin = editTextW.getText().toString().trim();//获取微信
        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            editTextN.requestFocus(); //对输入框失去焦点
            return;
        }else if (TextUtils.isEmpty(time)){
            Toast.makeText(this, "时间不能为空", Toast.LENGTH_SHORT).show();
            editTextT.requestFocus();
            return;

        }else if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            editTextPhone.requestFocus();
            return;
        }
        final ProgressDialog pd = new ProgressDialog(this); //等待动画
        pd.setMessage("正在预定···");
        pd.show();

        //模拟后台

            String water = name + time;
            Intent intent =new Intent(this,Water.class);
            intent.putExtra("water",water);
            startActivity(intent);
           pd.dismiss();
            te_result.setText("预定成功");
        }
    }






