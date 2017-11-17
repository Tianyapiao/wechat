package com.example.asus.grpc.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.grpc.R;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.helloworld.GreeterRegisterGrpc;
import io.grpc.examples.helloworld.RegisterReply;
import io.grpc.examples.helloworld.RegisterRequest;

/**
 * Created by Asus on 2017/11/5.
 */

public class RegisterActivity extends AppCompatActivity {

    private Button bt_register;
    private EditText name;
    private EditText password;
    private TextView mResultText;

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
        password = (EditText) findViewById(R.id.password);

        mResultText=(TextView) findViewById(R.id.tv_register);
    }

    /**
     * 注册方法
     * @param view
     */
    public void RegisterIndex(View view) {

        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(name.getWindowToken(), 0);

        bt_register.setEnabled(false);
        new GrpcTask().execute();
        //获取用户输入信息
        String  userName=  name.getText().toString().trim();
        String  userPwd=  password.getText().toString().trim();
        //获取服务端返回的信息


        //判断用户输入
        if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(userPwd)) {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();

        } /*else if ("1".equals(userName)){
            Toast.makeText(this, "用户已经存在，请换个用户名再试...", Toast.LENGTH_SHORT).show();

        }*//*else {
            //Toast.makeText(this, ""+mResultText, Toast.LENGTH_SHORT).show();
            //调到登录页面
            Intent intent=new Intent(RegisterActivity.this,loginActivity.class);
            startActivity(intent);
        }*/

    }

    //内部类,处理grpc任务
    private class GrpcTask extends AsyncTask<Void, Void, String> {

        private String mHost="10.0.2.2";//主机ip
        private int mPort=50051;//端口号
        private ManagedChannel mChannel;//信道
        private String userName;//客户端发送的用户名
        private String userPwd;//客户端发送的密码


        @Override
        protected void onPreExecute() {
            name= (EditText) findViewById(R.id.uname);
            userName =name.getText().toString().trim();//获取用户输入的信息
            userPwd=password.getText().toString().trim();
            mResultText.setText("");
        }

        @Override
        protected String doInBackground(Void... nothing) {
            try {
                mChannel = ManagedChannelBuilder.forAddress(mHost, mPort)
                        .usePlaintext(true)
                        .build();
                GreeterRegisterGrpc.GreeterRegisterBlockingStub stub =
                        GreeterRegisterGrpc.newBlockingStub(mChannel);
                //发送请求
                RegisterRequest message = RegisterRequest.newBuilder().setName(userName)
                        .setPwd(userPwd).build();
                //回复请求
                RegisterReply reply = stub.sayRegister(message);

                return reply.getMessage();
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                pw.flush();
                System.out.println("请求发送失败");
                return String.format("Failed... : %n%s", sw);
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                mChannel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            mResultText.setText(result);
            bt_register.setEnabled(true);
        }
    }
}
