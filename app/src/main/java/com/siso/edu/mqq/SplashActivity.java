package com.siso.edu.mqq;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import utils.Constant;
import utils.Utils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //得到当前界面的装饰视图
        View decorView = getWindow().getDecorView();

        //设置系统UI元素的可见性
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);//SYSTEM_UI_FLAG_FULLSCREEN表示全屏的意思，也就是会将状态栏隐藏
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        Utils.initSP(SplashActivity.this);//初始化SP

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean value=Utils.getBooleanFirst(SplashActivity.this, Constant.FIRST_BOOLEAN);
                if (value==false){
                    //第一次
                    try {
                        Thread.sleep(3000);
                        startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                        Utils.putBooleanFirst(SplashActivity.this,Constant.FIRST_BOOLEAN,true);
                        finish();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        Thread.sleep(3000);
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();
    }


}
