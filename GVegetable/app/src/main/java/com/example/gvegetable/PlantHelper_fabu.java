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

public class PlantHelper_fabu extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextN,editTextR,editTextPrice,editTextW,editTextPhone,editTextT; //用户名，人数，价格，微信，手机号，工时
    private Button buttonFaBu; //发布按钮
    private ImageView returnImage; //返回按钮
    private TextView te_result,planthelper_all;
    private HashMap<String, String> stringHashMap;
    String TAG = Register.class.getCanonicalName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planthelper_fabu); //绑定布局
        init(); //初始化控件
    }

    private void init() {
        editTextN = (EditText) findViewById(R.id.gs_name); //绑定控件
        editTextR = (EditText) findViewById(R.id.gs_renshu);
        editTextPrice = (EditText) findViewById(R.id.gs_price);
        editTextPhone = (EditText) findViewById(R.id.gs_phone);
        editTextW = (EditText) findViewById(R.id.gs_weixin);
        editTextT = (EditText) findViewById(R.id.gs_time);
        buttonFaBu = (Button) findViewById(R.id.gs_planthelper_fabu);
        planthelper_all = (TextView) findViewById(R.id.gs_planthelper_all);
        te_result = (TextView) findViewById(R.id.gs_result);
        returnImage = (ImageView) findViewById(R.id.gs_return);
        buttonFaBu.setOnClickListener(this); //点击事件
        planthelper_all.setOnClickListener(this);
        returnImage.setOnClickListener(this);
        stringHashMap = new HashMap<>();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gs_planthelper_fabu:
                planthelper_fabu(); //发布方法
                break;
            case R.id.gs_planthelper_all:
                Intent intent = new Intent(this,PlantHelper_all.class); //跳转页面
                startActivity(intent);
                break;
            case R.id.gs_return:
                Intent intent1 = new Intent(this,PlantHelper.class); //跳转页面
                startActivity(intent1);
                break;

        }

    }

    private void planthelper_fabu() {
        String name = editTextN.getText().toString().trim(); //获取用户名
        String renshu = editTextR.getText().toString().trim(); //获取人数
        String price = editTextPrice.getText().toString().trim();//获取价格
        String phone = editTextPhone.getText().toString().trim();//获取手机号
        String weixin = editTextW.getText().toString().trim();//获取微信
        String time = editTextT.getText().toString().trim();//获取工时

        stringHashMap.put("name",editTextN.getText().toString());
        stringHashMap.put("renshu", editTextR.getText().toString());
        stringHashMap.put("price", editTextPrice.getText().toString());
        stringHashMap.put("phone", editTextPhone.getText().toString());
        stringHashMap.put("weixin", editTextW.getText().toString());
        stringHashMap.put("time", editTextT.getText().toString());
        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            editTextN.requestFocus(); //对输入框失去焦点
            return;
        }else if (TextUtils.isEmpty(renshu)){
            Toast.makeText(this, "人数不能为空", Toast.LENGTH_SHORT).show();
            editTextR.requestFocus();
            return;

        }else if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            editTextPhone.requestFocus();
            return;
        }else if (TextUtils.isEmpty(price)){
            Toast.makeText(this, "价格不能为空", Toast.LENGTH_SHORT).show();
            editTextPrice.requestFocus();
            return;
        }else if (TextUtils.isEmpty(time)){
            Toast.makeText(this, "工时不能为空", Toast.LENGTH_SHORT).show();
            editTextT.requestFocus();
            return;
        }
        final ProgressDialog pd = new ProgressDialog(this); //等待动画
        pd.setMessage("正在发布···");
        pd.show();

        //模拟后台
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                    requestGet(stringHashMap);
                }catch (Exception e){
                    Log.e(TAG, e.toString());
                }
                pd.dismiss();

            }

            private void requestGet(HashMap<String, String> paramsMap) {
                try {
                    String baseUrl = "http://10.0.2.2:8080/GVegetableServer/servlet/PlantHelper_fabuServlet?";
                    StringBuilder tempParams = new StringBuilder();
                    int pos = 0;

                    for (String key : paramsMap.keySet()) {
                        if (pos > 0) {
                            tempParams.append("&");
                        }
                        tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                        pos++;
                    }

                    Log.e(TAG,"params--get-->>"+tempParams.toString());
                    String requestUrl = baseUrl + tempParams.toString();
                    // 新建一个URL对象
                    URL url = new URL(requestUrl);
                    // 打开一个HttpURLConnection连接
                    HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                    // 设置连接主机超时时间
                    urlConn.setConnectTimeout(5 * 1000);
                    //设置从主机读取数据超时
                    urlConn.setReadTimeout(5 * 1000);
                    // 设置是否使用缓存  默认是true
                    urlConn.setUseCaches(true);
                    // 设置为Post请求
                    urlConn.setRequestMethod("GET");
                    //urlConn设置请求头信息
                    //设置请求中的媒体类型信息。
                    urlConn.setRequestProperty("Content-Type", "application/json");
                    //设置客户端与服务连接类型
                    urlConn.addRequestProperty("Connection", "Keep-Alive");

                    // 开始连接
                    urlConn.connect();
                    // 判断请求是否成功
                    if (urlConn.getResponseCode() == 200) {
                        // 获取返回的数据
                        String result = streamToString(urlConn.getInputStream());
                        te_result.setText(result);
                        Log.e(TAG, "Get方式请求成功，result--->" + result);
                    } else {
                        te_result.setText("Get方式请求失败");
                        Log.e(TAG, "Get方式请求失败");
                    }
                    // 关闭连接
                    urlConn.disconnect();
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
            public String streamToString(InputStream is) {
                try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = is.read(buffer)) != -1) {
                        baos.write(buffer, 0, len);
                    }
                    baos.close();
                    is.close();
                    byte[] byteArray = baos.toByteArray();
                    return new String(byteArray);
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                    return null;
                }
            }
        }).start();
    }


}

