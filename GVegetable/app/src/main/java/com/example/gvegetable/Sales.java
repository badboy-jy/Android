package com.example.gvegetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Sales extends AppCompatActivity implements View.OnClickListener {
    private TextView main;
    private ImageView back,sales_f,sales_c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales);
        main  = (TextView) findViewById(R.id.gs_main);
        back = (ImageView) findViewById(R.id.gs_return);
        sales_c = (ImageView) findViewById(R.id.gs_sales_c);
        sales_f = (ImageView) findViewById(R.id.gs_sales_f);
        main.setOnClickListener(this);
        back.setOnClickListener(this);
        sales_f.setOnClickListener(this);
        sales_c.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gs_main:  //
                Intent intent = new Intent(this,Main.class);
                startActivity(intent);
                break;
            case R.id.gs_return:
                Intent intent1 = new Intent(this,Main.class);
                startActivity(intent1);
                break;
            case R.id.gs_sales_f:
                Intent intent2 = new Intent(this,Sales_f.class);
                startActivity(intent2);
                break;
            case R.id.gs_sales_c:
                Intent intent4 = new Intent(this,Sales_c.class);
                startActivity(intent4);
                break;

        }
    }//注册按钮
}
