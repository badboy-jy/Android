package com.example.gvegetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sales_orderAllf extends AppCompatActivity implements View.OnClickListener {
    private TextView sales_c,salesorder_f;
    private ImageView back;
    private List<UserBean> list;
    private Spinner spinner;
    private List<String> list2;
    private ArrayAdapter<String> adapter2;
    private HashMap<String, String> stringHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_orderallf);
        ListView lv = (ListView) findViewById(R.id.gs_show);
        stringHashMap = new HashMap<>();
        final List<Map<String,Object>> list1 = new ArrayList<Map <String,Object>>();
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list1,
                R.layout.itemsalesf,
                new String[]{"logo","name","shuliang","phone"},
                new int []{R.id.logo,R.id.gs_name,R.id.gs_shuliang,R.id.gs_phone}

        );
        lv.setAdapter(adapter);
        sales_c  = (TextView) findViewById(R.id.gs_sales_c);
        back = (ImageView) findViewById(R.id.gs_return);
        salesorder_f = (TextView) findViewById(R.id.gs_salesorder_f);
        spinner=(Spinner)findViewById(R.id.spinnerf);
        sales_c.setOnClickListener(this);
        back.setOnClickListener(this);
        salesorder_f.setOnClickListener(this);
        list2=new ArrayList<String>();
        list2.add("ganlan");
        list2.add("baicai");
        list2.add("lajiao");
        list2.add("qiezi");
        adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String pinzhong=adapter2.getItem(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 新建一个URL对象
                    String baseUrl = "http://10.0.2.2:8080/GVegetableServer/servlet/SalesAllFServlet?pinzhong=ganlan";

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
                            map.put("name",list.get(i).getName());
                            map.put("shuliang",list.get(i).getShuliang());
                            map.put("price",list.get(i).getPrice());
                            map.put("phone",list.get(i).getPhone());

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
            case R.id.gs_return:  //登录按钮
                Intent intent = new Intent(this,Sales_c.class);
                startActivity(intent);
                break;
            case R.id.gs_sales_c:
                Intent intent1 = new Intent(this,Sales_c.class);
                startActivity(intent1);
                break;
            case R.id.gs_salesorder_f:
                Intent intent3 = new Intent(this,SalesOrderF.class);
                startActivity(intent3);
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

