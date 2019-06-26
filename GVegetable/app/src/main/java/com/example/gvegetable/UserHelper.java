package com.example.gvegetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UserHelper extends AppCompatActivity implements View.OnClickListener {
    private TextView retur;
    private ImageView retu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhelper);
        retur = (TextView) findViewById(R.id.gs_retur);
        retu = (ImageView) findViewById(R.id.gs_return);
        retu.setOnClickListener(this);
        retur.setOnClickListener(this);

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gs_retur:  //登录按钮
            returnLogin();
                break;
            case R.id.gs_return:
                returnLogin();
                break;
        }
    }//注册按钮

    private void returnLogin() {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
}
