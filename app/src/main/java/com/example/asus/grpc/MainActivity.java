package com.example.asus.grpc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.asus.grpc.common.RegisterActivity;
import com.example.asus.grpc.common.loginActivity;


public class MainActivity extends AppCompatActivity {

    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 隐藏 系统自带的标题栏
         */
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        //找到登录和注册控件
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
    }
    //登录方法
    /**
     * login方法
     */
    public void login(View view) {
        //跳到登录页面
        Intent intent=new Intent(MainActivity.this, loginActivity.class);
        startActivity(intent);

    }
    /**
     * register方法
     */
    public void register(View view) {
        //跳到注册页面
        Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
