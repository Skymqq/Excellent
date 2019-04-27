package com.siso.edu.mqq;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.MainFragmentPagerAdapter;
import fragment.Fragment1;
import fragment.Fragment2;
import fragment.Fragment3;
import fragment.Fragment4;
import utils.Utils;

public class MainActivity extends SingleActivity {
    //声明全局UI视图控件变量
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton1, mRadioButton2, mRadioButton3, mRadioButton4;

    //声明数组、集合容器
    private Fragment[] mFragments = new Fragment[]{new Fragment1(), new Fragment2(), new Fragment3(), new Fragment4()};
    private List<Fragment> mFragmentList;
    private MainFragmentPagerAdapter mMainFragmentPagerAdapter;




    //初始化视图控件
    @Override
    protected void initView() {
        //局部控件初始化
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//将ActionBar设置成Toolbar

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.list);
        }

        //全局视图控件初始化
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg);
        mRadioButton1 = (RadioButton) findViewById(R.id.rb1);
        mRadioButton2 = (RadioButton) findViewById(R.id.rb2);
        mRadioButton3 = (RadioButton) findViewById(R.id.rb3);
        mRadioButton4 = (RadioButton) findViewById(R.id.rb4);


    }


    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        for (Fragment fragment : mFragments) {
            mFragmentList.add(fragment);
        }
        mMainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mMainFragmentPagerAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mNavigationView.setCheckedItem(R.id.nav_profile);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_profile:
                        Utils.showToast(MainActivity.this, "" + menuItem.getTitle());
                        break;
                    case R.id.nav_people:
                        Utils.showToast(MainActivity.this, "" + menuItem.getTitle());
                        break;
                    case R.id.nav_location:
                        Utils.showToast(MainActivity.this, "" + menuItem.getTitle());
                        break;
                    case R.id.nav_event:
                        Utils.showToast(MainActivity.this, "" + menuItem.getTitle());
                        break;
                    case R.id.setting:
                        Utils.showToast(MainActivity.this, "" + menuItem.getTitle());
                        break;
                    default:
                        break;
                }
                return true;
            }
        });


        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mViewPager.setCurrentItem(mRadioGroup.indexOfChild(findViewById(checkedId)));////点击rb会切换到相应的fragment
            }
        });


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mRadioGroup.check(mRadioGroup.getChildAt(i).getId());//rb会跟着滑动而切换
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    //返回布局文件的资源id
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }


    //创建菜单栏
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    //对于菜单栏的选择操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.search:
                Utils.showToast(MainActivity.this, "search");
                break;
            case R.id.add:
                Utils.showToast(MainActivity.this, "add");
                break;
            case R.id.setting:
                Utils.showToast(MainActivity.this, "setting");
                break;
            default:
                break;
        }
        return true;
    }

}
