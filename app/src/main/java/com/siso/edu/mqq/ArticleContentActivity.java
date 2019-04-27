package com.siso.edu.mqq;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import utils.Constant;
import utils.Utils;

public class ArticleContentActivity extends SingleActivity {
    //控件
    private TextView mTextViewTitle, mTextViewComment, mTextViewContent, mTextViewSchool, mTextViewTeacher;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    //数据
    private String title = null;
    private String comment = null;
    private String content = null;
    private String school = null;
    private String teacher = null;


    @Override
    protected void initView() {
        mTextViewTitle = (TextView) findViewById(R.id.tv_articleTitle);
        mTextViewComment = (TextView) findViewById(R.id.tv_articleComment);
        mTextViewContent = (TextView) findViewById(R.id.tv_articleContent);
        mTextViewSchool = (TextView) findViewById(R.id.tv_articleSchool);
        mTextViewTeacher = (TextView) findViewById(R.id.tv_articleTeacher);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        title = intent.getStringExtra("title");
        String url = "http://zuowen.api.juhe.cn/zuowen/content?&key=3b2c2b4226982ab5d4d2d96f2c870478&id=" + id;
        String spData = Utils.getDataFromSP(this, title);
        if (TextUtils.isEmpty(spData)) {
            Utils.requestArticle(this, title, url);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String mData = Utils.getDataFromSP(this, title);
            parseData(mData);
        } else {
            parseData(spData);
        }

    }

    private void parseData(String spData) {
        if (TextUtils.isEmpty(spData)) {
            return;
        } else {
            try {
                JSONObject jsonObject = new JSONObject(spData);
                String result = jsonObject.getString("result");
                JSONObject jsonObject1 = new JSONObject(result);
                comment = jsonObject1.getString("comment");
                content = jsonObject1.getString("content");
                school = jsonObject1.getString("school");
                teacher = jsonObject1.getString("teacher");
                Utils.showLog("result= " + result);
                Utils.showLog("comment= " + comment);
                Utils.showLog("content= " + content);
                Utils.showLog("school= " + school);
                Utils.showLog("teacher= " + teacher);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextViewTitle.setText(title);
                        mTextViewComment.setText("评论: " + "\n" + comment);
                        mTextViewContent.setText(content);
                        mTextViewSchool.setText("学校: " + "\n" + school);
                        mTextViewTeacher.setText("老师: " + "\n" + teacher);
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_content_article;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }



                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                mTextViewTitle.setText(title);
                                mTextViewComment.setText("评论: " + "\n" + comment);
                                mTextViewContent.setText(content);
                                mTextViewSchool.setText("学校: " + "\n" + school);
                                mTextViewTeacher.setText("老师: " + "\n" + teacher);

                                mSwipeRefreshLayout.setColorSchemeResources(R.color.refreshColor);
                                mSwipeRefreshLayout.setRefreshing(false);//刷新事件结束，并隐藏进度条
                            }

                        });



                    }
                }).start();


            }
        });
    }
}
