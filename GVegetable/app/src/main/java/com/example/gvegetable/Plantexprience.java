package com.example.gvegetable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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

public class Plantexprience extends AppCompatActivity implements View.OnClickListener {
    private TextView shemgzhang,to_result;
    private ImageView back;
    private EditText editJilu;
    private Button baocun;
    private List<UserBean> list;
    private HashMap<String, String> stringHashMap;
    String TAG = Register.class.getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantexperience);
        shemgzhang  = (TextView) findViewById(R.id.gs_shengzhang);
        to_result  = (TextView) findViewById(R.id.gs_result);
        back = (ImageView) findViewById(R.id.gs_return);
        editJilu=(EditText) findViewById(R.id.gs_jilu);
        baocun = (Button) findViewById(R.id.gs_newsout);
        shemgzhang.setOnClickListener(this);
        back.setOnClickListener(this);
        baocun.setOnClickListener(this);
        stringHashMap = new HashMap<>();
        ListView lv = (ListView) findViewById(R.id.gs_show);
        final List<Map<String,Object>> list1 = new ArrayList<Map <String,Object>>();
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list1,
                R.layout.itemjilu,
                new String[]{"logo","jilu","time"},
                new int []{R.id.logo,R.id.list_content,R.id.list_time}

        );
        lv.setAdapter(adapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 新建一个URL对象
                    String baseUrl = "http://10.0.2.2:8080/GVegetableServer/servlet/EXJiLuAllServlet";

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
                            map.put("logo",R.drawable.name);
                            map.put("jilu",list.get(i).getJilu());
                            map.put("time",getTime());
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

            private String getTime() {
                SimpleDateFormat format = new SimpleDateFormat("yyyy年mm月dd日 HH：mm：ss");
                Date date = new Date();
                String str = format.format(date);
                return str;

            }
        }).start();

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gs_experice:  //登录按钮
                Intent intent = new Intent(this,Plantshengzhang.class);
                startActivity(intent);
                break;
            case R.id.gs_return:
                Intent intent1 = new Intent(this,Main.class);
                startActivity(intent1);
                break;
            case R.id.gs_newsout:
                newsfasong();
                break;



        }
    }//注册按钮

    private void newsfasong() {
        String name = editJilu.getText().toString().trim(); //获取用户名
        stringHashMap.put("jilu",editJilu.getText().toString());


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

            }

            private void requestGet(HashMap<String, String> paramsMap) {
                try {
                    String baseUrl = "http://10.0.2.2:8080/GVegetableServer/servlet/EXJiLuOutServlet?";
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
                        to_result.setText(result);
                        Log.e(TAG, "Get方式请求成功，result--->" + result);
                    } else {
                        to_result.setText("Get方式请求失败");
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
