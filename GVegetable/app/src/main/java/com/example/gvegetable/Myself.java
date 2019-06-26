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

public class Myself extends AppCompatActivity implements View.OnClickListener {
    private TextView main,land_geren,land_out,land_in,land_now,pinzhong1,pinzhong2,pinzhong3,pinzhong4,gongshi,pin1,pin2,pin3,pin4;
    private ImageView back;
    private Button writer;
    private List<UserBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myself);



        main  = (TextView) findViewById(R.id.gs_main);
        back = (ImageView) findViewById(R.id.gs_return);
        land_geren  = (TextView) findViewById(R.id.gs_landgeren);
        land_out  = (TextView) findViewById(R.id.gs_landrent_out);
        land_in  = (TextView) findViewById(R.id.gs_landrent_in);
        land_now  = (TextView) findViewById(R.id.gs_landnow);
        pinzhong1  = (TextView) findViewById(R.id.gs_pinzhong1);
        pinzhong2  = (TextView) findViewById(R.id.gs_pinzhong2);
        pinzhong3  = (TextView) findViewById(R.id.gs_pinzhong3);
        pinzhong4  = (TextView) findViewById(R.id.gs_pinzhong4);
        pin1  = (TextView) findViewById(R.id.gs_pin1);
        pin2  = (TextView) findViewById(R.id.gs_pin2);
        pin3  = (TextView) findViewById(R.id.gs_pin3);
        pin4  = (TextView) findViewById(R.id.gs_pin4);
        gongshi  = (TextView) findViewById(R.id.gongshi);
        writer = (Button) findViewById(R.id.gs_myself_write);


        main.setOnClickListener(this);
        back.setOnClickListener(this);
        writer.setOnClickListener(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 新建一个URL对象
                    String baseUrl = "http://10.0.2.2:8080/GVegetableServer/servlet/MyselfServlet";

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
                        String land_ger =list.get(i-1).getLandgeren();
                        String land_o =list.get(i-1).getLandOut();
                        String land_i =list.get(i-1).getLandIn();
                        String land_n =list.get(i-1).getLandNow();
                        String p1 =list.get(i-1).getPin1();
                        String p2 =list.get(i-1).getPin2();
                        String p3 =list.get(i-1).getPin3();
                        String p4 =list.get(i-1).getPin4();
                        String pz1 =list.get(i-1).getPinzhong1();
                        String pz2 =list.get(i-1).getPinzhong2();
                        String pz3 =list.get(i-1).getPinzhong3();
                        String pz4 =list.get(i-1).getPinzhong4();
                        String gongsh =list.get(i-1).getGongshi();

                        land_geren.setText(land_ger);
                        land_out.setText(land_o);
                        land_in.setText(land_i);
                        land_now.setText(land_n);
                        pin1.setText(p1);
                        pin2.setText(p2);
                        pin3.setText(p3);
                        pin4.setText(p4);
                        pinzhong1.setText(pz1);
                        pinzhong2.setText(pz2);
                        pinzhong3.setText(pz3);
                        pinzhong4.setText(pz4);
                        gongshi.setText(gongsh);



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
            case R.id.gs_myself_write:
                Intent intent2 = new Intent(this,MyselfWriter.class);
                startActivity(intent2);
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

