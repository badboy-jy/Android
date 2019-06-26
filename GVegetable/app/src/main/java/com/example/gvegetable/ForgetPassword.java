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

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextN,editTextP,editSMS; //名字，电话，密码框
    private Button buttonPWD,SMSBtn; //密码获取，验证码按钮
    private ImageView returnImage; //返回按钮
    private TextView enter; //登录按钮
    private TextView te_result;//结果
    private HashMap<String, String> stringHashMap;
    String TAG = Register.class.getCanonicalName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpassword); //绑定布局
        init(); //初始化控件
    }

    private void init() {
        editTextP = (EditText) findViewById(R.id.gs_phone_num); //绑定控件
        editSMS = (EditText) findViewById(R.id.gs_code);
        editTextN = (EditText) findViewById(R.id.gs_username);
        buttonPWD = (Button) findViewById(R.id.gs_getpwd);
        enter = (TextView) findViewById(R.id.gs_enter);
        returnImage = (ImageView) findViewById(R.id.gs_return);
        SMSBtn = (Button) findViewById(R.id.gs_sms_code);
        te_result = (TextView)findViewById(R.id.gs_result);
        buttonPWD.setOnClickListener(this); //点击事件
        SMSBtn.setOnClickListener(this);
        returnImage.setOnClickListener(this);
        enter.setOnClickListener(this);
        stringHashMap = new HashMap<>();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gs_getpwd:
                getpwd(); //获取密码
                break;
            case R.id.gs_enter:
                returnEnter();
                break;
            case R.id.gs_return:
                returnEnter();
                break;
            case R.id.gs_sms_code:
                String  phone = editTextP.getText().toString().trim();
                if (phone.length() != 11){
                    Toast.makeText(this, "电话号码不合理", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(this, "654321", Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }

    private void getpwd() {
        String username = editTextN.getText().toString().trim(); //获取用户名
        String confirmpassword = editSMS.getText().toString().trim(); //获取验证码
        String phone = editTextP.getText().toString().trim(); //获取手机号
        stringHashMap.put("username", editTextN.getText().toString());
        stringHashMap.put("phone", editTextP.getText().toString());
        if (TextUtils.isEmpty(username)){
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            editTextP.requestFocus(); //对输入框失去焦点
            return;
        }else if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            editTextP.requestFocus();
            return;

        } else if (TextUtils.isEmpty(confirmpassword)){
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            editSMS.requestFocus();
            return;

        }else if (!confirmpassword.equals("654321")){
            Toast.makeText(this, "验证码不对", Toast.LENGTH_SHORT).show();
            editSMS.requestFocus();
            return;
        }
        final ProgressDialog pd = new ProgressDialog(this); //等待动画
        pd.setMessage("正在获取···");
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
                    String baseUrl = "http://10.0.2.2:8080/GVegetableServer/servlet/GetPasswordServlet?";
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
                        te_result.setText("该用户密码为："+result);//显示密码
                        Log.e(TAG, "Get方式请求成功，result--->" + result);
                    } else {

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
    private void returnEnter() {
        Intent intent = new Intent(this,Login.class); //跳转页面
        startActivity(intent);
    }
}

