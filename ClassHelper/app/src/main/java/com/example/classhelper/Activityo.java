package com.example.classhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.FillEventHistory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import java.util.List;

public class Activityo extends AppCompatActivity implements View.OnClickListener {
    private TextView back,activityname,activityplace,activityperson,activityphone,activitycontent;
    private Button history,fabu;
    private List<UserBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);



        back  = (TextView) findViewById(R.id.back);
        activityname  = (TextView) findViewById(R.id.activityname);
        activityplace  = (TextView) findViewById(R.id.activityplace);
        activityperson  = (TextView) findViewById(R.id.activityperson);
        activityphone  = (TextView) findViewById(R.id.activityphone);
        activitycontent  = (TextView) findViewById(R.id.activitycontent);
        history = (Button) findViewById(R.id.history);
        fabu = (Button) findViewById(R.id.fabu);


        history.setOnClickListener(this);
        back.setOnClickListener(this);
        fabu.setOnClickListener(this);

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
                        int i=list.size();
                        String aname =list.get(i-1).getName();
                        String aplace =list.get(i-1).getPlace();
                        String aperson =list.get(i-1).getPerson();
                        String aphone =list.get(i-1).getPhone();
                        String acontent =list.get(i-1).getContent();


                        activityname.setText(aname);
                        activityplace.setText(aplace);
                        activityperson.setText(aperson);
                        activityphone.setText(aphone);
                        activitycontent.setText(acontent);


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
            case R.id.history:  //按钮
                Intent intent = new Intent(this, History.class);
                startActivity(intent);
                break;
            case R.id.fabu:
                Intent intent1 = new Intent(this,Fabu.class);
                startActivity(intent1);
                break;
            case R.id.back:
                Intent intent2 = new Intent(this, Main.class);
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
