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

public class loginActivity extends AppCompatActivity {

    private Button bt_login;
    private EditText name;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /**
         * 隐藏 系统自带的标题栏
         */
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        //找到登录按钮
        bt_login = (Button) findViewById(R.id.bt_login);
        //找到用户名输入框
        name= (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.pwd);
    }

    /**
     * 登录方法
     * @param view
     */
    public void loginIndex(View view) {

        String  userName=  name.getText().toString().trim();
        String  userPwd=  password.getText().toString().trim();
        //判断用户输入
        if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(userPwd)) {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        } else if ("18879597506".equals(userName)&&"123456".equals(userPwd)){
            Toast.makeText(this, "正在登录...", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(loginActivity.this,IndexActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "用户名或密码输入错误", Toast.LENGTH_SHORT).show();
        }

    }
}
