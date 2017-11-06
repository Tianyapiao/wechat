package com.example.asus.grpc.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.asus.grpc.R;

public class IndexActivity extends AppCompatActivity {

    private ImageView me;
    private ImageView wechat;
    private ImageView contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        /**
         * 隐藏 系统自带的标题栏
         */
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }

        //1.找到图标微信
        wechat = (ImageView) findViewById(R.id.ib_weixin);
        //设置监听事件
        wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IndexActivity.this,WechatActivity.class);
                startActivity(intent);
            }
        });

        //2.找到图标通讯录
        contact = (ImageView) findViewById(R.id.ib_contact_list);
        //设置监听事件
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IndexActivity.this,ContactActivity.class);
                startActivity(intent);
            }
        });

        //3.找到图标我
        me = (ImageView) findViewById(R.id.ib_profile);
        //设置点击监听事件
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IndexActivity.this,MyInfoActivity.class);
                startActivity(intent);
            }
        });


    }
}
