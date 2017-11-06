package com.example.asus.grpc.common;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asus.grpc.R;

import java.util.ArrayList;

public class IndexActivity extends AppCompatActivity {

    private ImageView me;
    private ImageView wechat;
    private ImageView contact;

    private ListView list;

    //a.设置viewPaper
    ViewPager pager = null;
    ArrayList<View> viewContainter = new ArrayList<View>();


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
        //b.实例化ViewPaper
        pager = (ViewPager) this.findViewById(R.id.viewpager);
        

        //1.找到图标微信
        wechat = (ImageView) findViewById(R.id.ib_weixin);
        //设置监听事件
        wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent=new Intent(IndexActivity.this,WechatActivity.class);
                startActivity(intent);*/
                //c.跳到第一个页面
                pager.setCurrentItem(0);

            }
        });

        //2.找到图标通讯录
        contact = (ImageView) findViewById(R.id.ib_contact_list);
        //设置监听事件
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent=new Intent(IndexActivity.this,ContactActivity.class);
                startActivity(intent);*/
                //d.第二个页面
                pager.setCurrentItem(1);
            }
        });

        //3.找到图标我
        me = (ImageView) findViewById(R.id.ib_profile);
        //设置点击监听事件
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent=new Intent(IndexActivity.this,MyInfoActivity.class);
                startActivity(intent);*/
                //e.第三个页面
                pager.setCurrentItem(2);
            }
        });


        //为viewPaper设置内容
        //view是我们放进viewPaper里面的东西，要为它设置好布局，再放进去
        View view1 = LayoutInflater.from(this).inflate(R.layout.fragment_contactlist, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.fragment_contactlist, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.activity_myinfo, null);

        //这是个ArrayList,加进去了3个view
        viewContainter.add(view1);
        viewContainter.add(view2);
        viewContainter.add(view3);


        //设置适配器
        pager.setAdapter(new PagerAdapter() {


            //viewpager中的组件数量
            @Override
            public int getCount() {
                return viewContainter.size();
            }

            //滑动切换的时候销毁当前的组件

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                ((ViewPager) container).removeView(viewContainter.get(position));
            }

            //每次滑动的时候生成的组件
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(viewContainter.get(position));
                //想用到每个view里面的控件的话，在主界面上是找不到这些控件的，都是空的。
                // 必须在instantiateItem这个函数里面指定。
                switch (position){

                    case 0: {
                        //在第一个页面中
                        list = (ListView) findViewById(R.id.list);
                    }
                   /* case 1: {
                        //在第二个页面中
                        list = (ListView) findViewById(R.id.list);
                    }
                    case 2: {
                        //在第三个页面中
                        list = (ListView) findViewById(R.id.list);
                    }*/
                }
                return viewContainter.get(position);
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }


        });

        //页面变化时的监听 改变按钮
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                switch (i) {

                    //如果是第一个页面
                    case 0:
                        list.setAdapter(new ListViewAdapter());
                        break;

                    //如果是第二个页面
                    case 1:
                        list.setAdapter(new ListViewAdapter());
                        break;

                    //如果是第三个页面
                    case 2:
                        list.setAdapter(new ListViewAdapter());
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });



    }


    /**
     * listView的适配器
     */
    private class ListViewAdapter  extends BaseAdapter {
        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv;
            if (convertView == null) {
                tv = new TextView(IndexActivity.this);
                System.out.println("创建新的view:" + position);
            } else {
                tv = (TextView) convertView;
                System.out.println("使用回收的view:" + position);
            }
            tv.setText("Java交流群：" + position);
            tv.setTextColor(Color.RED);
            tv.setTextSize(20);
            return tv;

        }
    }

}
