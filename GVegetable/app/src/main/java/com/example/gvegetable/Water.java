package com.example.gvegetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Water extends AppCompatActivity implements View.OnClickListener {
    private TextView main,water1,water2,war1;
    private ImageView back;
    private Button w1,w2;
    private List<UserBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water);



        main  = (TextView) findViewById(R.id.gs_main);
        back = (ImageView) findViewById(R.id.gs_return);
        water1  = (TextView) findViewById(R.id.gs_water1);
        water2  = (TextView) findViewById(R.id.gs_water2);
        w1 = (Button) findViewById(R.id.gs_w1);
        w2 = (Button) findViewById(R.id.gs_w2);
        war1 = (TextView) findViewById(R.id.gs_war1);
        main.setOnClickListener(this);
        back.setOnClickListener(this);
        w1.setOnClickListener(this);
        w2.setOnClickListener(this);
        water2.setOnClickListener(this);
        war1.setOnClickListener(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 新建一个URL对象
                    String baseUrl = "http://10.0.2.2:8080/GVegetableServer/servlet/WaterServlet";

                    // 新建一个URL对象
                    URL url = new URL(baseUrl);
                    // 打开一个HttpURLConnection连接
                    final HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
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
                        String result = streamToString(urlConn.getInputStream());
                        Gson gson = new Gson();
                        JsonArray jsonObiect = new JsonParser().parse(result).getAsJsonArray();

                        list = gson.fromJson(jsonObiect,new TypeToken<List<UserBean>>(){}.getType());
                        int i=list.size();
                         String name =list.get(i-1).getName();
                          String time =list.get(i-1).getTime();
                          String water = name +time;
                            water1.setText(water);


                    } else {

                    }
                    // 关闭连接
                    urlConn.disconnect();
                } catch (ProtocolException e1) {
                    e1.printStackTrace();
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }).start();

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gs_return:  //按钮
                Intent intent = new Intent(this,Main.class);
                startActivity(intent);
                break;
            case R.id.gs_main:
                Intent intent1 = new Intent(this,Main.class);
                startActivity(intent1);
                break;
            case R.id.gs_w1:
                Intent intent2 = new Intent(this,Water_order.class);
                startActivity(intent2);
                break;
            case R.id.gs_w2:
                Intent intent3 = new Intent(this,Water_order1.class);
                startActivity(intent3);
                break;
            case R.id.gs_water2:
                Intent intent4 =getIntent();
                String water = intent4.getStringExtra("water");
                water2.setText(water);
                break;
            case R.id.gs_war1:
                Intent intent5 =new Intent(this,War1.class);
                startActivity(intent5);
                break;

        }
    }//注册按钮

    private String streamToString(InputStream is) {

        {
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
                return null;
            }
        }



    }

}

