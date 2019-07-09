package com.example.classhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class History extends AppCompatActivity implements View.OnClickListener {
    private TextView back;
    private List<UserBean> list;
    String TAG = Register.class.getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        back  = (TextView) findViewById(R.id.back);
        back.setOnClickListener(this);
        ListView lv = (ListView) findViewById(R.id.gs_show);
        final List<Map<String,Object>> list1 = new ArrayList<Map <String,Object>>();
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list1,
                R.layout.itemhistory,
                new String[]{"activityname","activityplace","activityperson","activityphone"},
                new int []{R.id.activityname,R.id.activityplace,R.id.activityperson,R.id.activityphone}

        );
        lv.setAdapter(adapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 新建一个URL对象
                    String baseUrl = "http://10.0.2.2:8080/ClassHelper/servlet/ActivityServlet";

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
                        for (int i=0;i<list.size();i++) {
                            Map<String,Object> map = new HashMap<String, Object>();
                            map.put("activityname",list.get(i).getName());
                            map.put("activityplace",list.get(i).getPlace());
                            map.put("activityperson",list.get(i).getPerson());
                            map.put("activityphone",list.get(i).getPhone());
                            map.put("activitycontent",list.get(i).getContent());

                            list1.add(map);
                        }

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
            case R.id.back:  //登录按钮
                Intent intent = new Intent(this, Activityo.class);
                startActivity(intent);
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
