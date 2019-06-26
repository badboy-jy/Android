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

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText editName,editPwd; //用户名、密码的输入框
    private TextView textViewR; //注册按钮
    private Button btn; //登录按钮
    private TextView te_result;//结果
    private TextView userhelper;
    private Button forget_password;
    private HashMap<String, String> stringHashMap;
    String TAG = Login.class.getCanonicalName();

    private  String currentUserName,currentPassword; //用于加载输入完成后的用户名和密码
    private ImageView qq,weixin,weibo; //QQ，微信，微博三方登录按钮
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); //绑定布局
        init(); //初始化方法
    }
    public void init(){
        btn = (Button) findViewById(R.id.gs_login);  //绑定按钮
        btn.setOnClickListener(this); // 按钮点击事件
        editName = (EditText) findViewById(R.id.gs_username) ;
        editPwd = (EditText) findViewById(R.id.gs_password);
        textViewR = (TextView) findViewById(R.id.gs_register);
        te_result = (TextView)findViewById(R.id.gs_result);
        userhelper = (TextView) findViewById(R.id.gs_userhelper);
        forget_password = (Button) findViewById(R.id.gs_forget_password);
        qq = (ImageView) findViewById(R.id.gs_qq_login);
        weixin = (ImageView) findViewById(R.id.gs_weixin_login);
        weibo = (ImageView) findViewById(R.id.gs_weibo_login);
        stringHashMap = new HashMap<>();
        qq.setOnClickListener(this);
        weibo.setOnClickListener(this);
        weixin.setOnClickListener(this);
        textViewR.setOnClickListener(this);
        btn.setOnClickListener(this);
       userhelper.setOnClickListener(this);
        forget_password.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gs_login:  //登录按钮
                login();
                break;
            case  R.id.gs_register: //注册按钮
                Intent intent = new Intent(Login.this, Register.class); //跳转注册页面
                startActivity(intent);
                finish();//销毁当前页面
                break;
            case R.id.gs_qq_login:
                Toast.makeText(this, "qq登录", Toast.LENGTH_SHORT).show();//qq登录
                break;
            case R.id.gs_weixin_login:
                Toast.makeText(this, "微信登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.gs_weibo_login:
                Toast.makeText(this, "微博登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.gs_userhelper:
                Intent intent1 = new Intent(Login.this,UserHelper.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.gs_forget_password:
                Intent intent2 = new Intent(Login.this,ForgetPassword.class);
                startActivity(intent2);
                finish();
                break;

        }
    }

    private void login() {
        stringHashMap.put("username",editName.getText().toString());
        stringHashMap.put("password", editPwd.getText().toString());
        currentUserName = editName.getText().toString().trim(); //获取用户名
        currentPassword = editPwd.getText().toString().trim(); //获取密码
        if (TextUtils.isEmpty(currentUserName)){ //判断用户名是否为空
            Toast.makeText(this, "手机号不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(currentPassword)){
            Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog pd = new ProgressDialog(Login.this); //等待动画
        pd.setMessage("正在登录····"); //显示信息
        pd.show(); //显示等待条

        //模拟后台数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                Thread.sleep(2000);
                    requestGet(stringHashMap);

                }catch  (Exception e){
                    Log.e(TAG, e.toString());
                }
                pd.dismiss();  //消失等待条

                finish(); //销毁当前界面
            }

            private void requestGet(HashMap<String, String> paramsMap) {
                try {
                    String baseUrl = "http://10.0.2.2:8080/GVegetableServer/servlet/LoginServlet?";
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
                        String abc = (String) result;
                        if (abc.equals("failure")){
                            Intent intent = new Intent(Login.this, Login.class);//跳转页面
                            startActivity(intent);}
                        else{
                            Intent intent = new Intent(Login.this, Main.class); //跳转页面
                            startActivity(intent);}
                        Log.e(TAG, "登录成功，result--->" + result);
                    } else {

                    }
                    // 关闭连接
                    urlConn.disconnect();
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }

            private String streamToString(InputStream is) {
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
        }).start(); //开启线程
    }
}

