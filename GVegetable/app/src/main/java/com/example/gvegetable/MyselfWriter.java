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

public class MyselfWriter extends AppCompatActivity implements View.OnClickListener {
    private EditText land_geren,land_out,land_in,land_now,pinzhong1,pinzhong2,pinzhong3,pinzhong4,gongshi,pin1,pin2,pin3,pin4;//
    private ImageView back;
    private Button writer;
    private TextView te_result,myself;
    private HashMap<String, String> stringHashMap;
    String TAG = Register.class.getCanonicalName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myself_write); //绑定布局
        init(); //初始化控件
    }

    private void init() {
       writer = (Button) findViewById(R.id.gs_myself_write);
        myself = (TextView) findViewById(R.id.gs_myself);
        te_result = (TextView) findViewById(R.id.gs_result);
        back = (ImageView) findViewById(R.id.gs_return);
        land_geren = (EditText) findViewById(R.id.gs_landgeren);
        land_out = (EditText) findViewById(R.id.gs_landrent_out);
        land_in = (EditText) findViewById(R.id.gs_landrent_in);
        land_now = (EditText) findViewById(R.id.gs_landnow);
        pinzhong1 = (EditText) findViewById(R.id.gs_pinzhong1);
        pinzhong2 = (EditText) findViewById(R.id.gs_pinzhong2);
        pinzhong3 = (EditText) findViewById(R.id.gs_pinzhong3);
        pinzhong4 = (EditText) findViewById(R.id.gs_pinzhong4);
        gongshi = (EditText) findViewById(R.id.gongshi);
        pin1 = (EditText) findViewById(R.id.gs_pin1);
        pin2 = (EditText) findViewById(R.id.gs_pin2);
        pin3 = (EditText) findViewById(R.id.gs_pin3);
        pin4 = (EditText) findViewById(R.id.gs_pin4);

        writer.setOnClickListener(this); //点击事件
        myself.setOnClickListener(this);
        back.setOnClickListener(this);
        stringHashMap = new HashMap<>();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gs_myself_write:
                myselfwriter(); //发布方法
                break;
            case R.id.gs_myself:
                Intent intent = new Intent(this,Myself.class); //跳转页面
                startActivity(intent);
                break;
            case R.id.gs_return:
                Intent intent1 = new Intent(this,Main.class); //跳转页面
                startActivity(intent1);
                break;

        }

    }

    private void myselfwriter() {
        stringHashMap.put("land_geren",land_geren.getText().toString());
        stringHashMap.put("land_out",land_out.getText().toString());
        stringHashMap.put("land_in",land_in.getText().toString());
        stringHashMap.put("land_now",land_now.getText().toString());
        stringHashMap.put("pinzhong1",pinzhong1.getText().toString());
        stringHashMap.put("pinzhong2",pinzhong2.getText().toString());
        stringHashMap.put("pinzhong3",pinzhong3.getText().toString());
        stringHashMap.put("pinzhong4",pinzhong4.getText().toString());
        stringHashMap.put("gongshi",gongshi.getText().toString());
        stringHashMap.put("pin1",pin1.getText().toString());
        stringHashMap.put("pin2",pin2.getText().toString());
        stringHashMap.put("pin3",pin3.getText().toString());
        stringHashMap.put("pin4",pin4.getText().toString());


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
                    String baseUrl = "http://10.0.2.2:8080/GVegetableServer/servlet/MyselfWriterServlet?";
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

