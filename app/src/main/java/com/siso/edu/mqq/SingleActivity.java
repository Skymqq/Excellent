package com.siso.edu.mqq;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.jinrishici.sdk.android.factory.JinrishiciFactory;

import cn.bmob.v3.Bmob;

public abstract class SingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        JinrishiciFactory.init(this);//今日诗词API的初始化
        initView();//初始化UI视图控件
        initData();//初始化数据
    }


    //初始化UI视图控件
    protected abstract void initView();

    //初始化数据
    protected abstract void initData();

    //获取布局资源
    protected abstract int getLayoutResId();


}
