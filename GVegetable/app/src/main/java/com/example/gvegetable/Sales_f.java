package com.example.gvegetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Sales_f extends AppCompatActivity implements View.OnClickListener {
    private TextView sales_c;
    private ImageView retu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_f);
        sales_c = (TextView) findViewById(R.id.gs_sales_c);
        retu = (ImageView) findViewById(R.id.gs_return);
        retu.setOnClickListener(this);
        sales_c.setOnClickListener(this);

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gs_sales_c:  //登录按钮
                Intent intent = new Intent(this,Sales_c.class);
                startActivity(intent);
                break;
            case R.id.gs_return:

                Intent intent1 = new Intent(this,Sales.class);
                startActivity(intent1);
                break;
        }
    }//注册按钮



}
