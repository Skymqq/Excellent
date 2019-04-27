package com.siso.edu.mqq;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import adapter.M_ViewPagerAdapter;

public class GuideActivity extends AppCompatActivity {
    //控件
    private ViewPager mViewPager;
    private TextView mTextViewTime;


    //数据
    private List<View> mList;
    private M_ViewPagerAdapter mAdapter;
    private View mView1, mView2, mView3, mView4, mView5, mView6, mView7;
    private Timer timer;
    private int second = 30;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //得到当前界面的装饰视图
        View decorView = getWindow().getDecorView();
//        SYSTEM_UI_FLAG_FULLSCREEN表示全屏的意思，也就是会将状态栏隐藏
        //设置系统UI元素的可见性
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        initView();
        initData();
    }


    protected void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTextViewTime = (TextView) findViewById(R.id.tv_time);
        mList = new ArrayList<>();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = second;
                second--;
                handler.sendMessage(message);
            }
        }, 0, 1000);
    }


    protected void initData() {

        LayoutInflater inflater = LayoutInflater.from(GuideActivity.this);
        mView1 = inflater.inflate(R.layout.view1, null);
        mView2 = inflater.inflate(R.layout.view2, null);
        mView3 = inflater.inflate(R.layout.view3, null);
        mView4 = inflater.inflate(R.layout.view4, null);
        mView5 = inflater.inflate(R.layout.view5, null);
        mView6 = inflater.inflate(R.layout.view6, null);
        mView7 = inflater.inflate(R.layout.view7, null);
        View[] mViews = new View[]{mView1, mView2, mView3, mView4, mView5, mView6, mView7};
        for (View view : mViews) {
            mList.add(view);
        }
        mAdapter = new M_ViewPagerAdapter(GuideActivity.this, mList);
        mViewPager.setAdapter(mAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mTextViewTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                timer.cancel();
                finish();
            }
        });
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                    timer.cancel();
                    finish();
                    break;
                default:
                    mTextViewTime.setText(String.valueOf(msg.what) + "秒 | 跳转");
                    break;
            }

        }
    };
}
