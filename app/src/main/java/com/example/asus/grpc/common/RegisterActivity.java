package com.example.asus.grpc.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.grpc.R;

/**
 * Created by Asus on 2017/11/5.
 */

public class RegisterActivity extends AppCompatActivity {

    private Button bt_register;
    private EditText name;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        /**
         * 隐藏 系统自带的标题栏
         */
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        //找到注册按钮
        bt_register = (Button) findViewById(R.id.bt_register);
        //找到用户名输入框
        name= (EditText) findViewById(R.id.uname);
        password = (EditText) findViewById(R.id.upwd);
    }

    /**
     * 登录方法
     * @param view
     */
    public void RegisterIndex(View view) {

        String  userName=  name.getText().toString().trim();
        String  userPwd=  password.getText().toString().trim();
        //判断用户输入
        if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(userPwd)) {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        } else if ("188795975061".equals(userName)){
            Toast.makeText(this, "用户已经存在，请换个用户名再试...", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            //调到登录页面
            Intent intent=new Intent(RegisterActivity.this,loginActivity.class);
            startActivity(intent);
        }

    }
}
