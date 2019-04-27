package com.siso.edu.mqq;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.ArticleAdapter;
import bean.Article;
import fragment.Fragment4;
import okhttp3.internal.Util;
import utils.Constant;
import utils.Utils;

public class ArticleActivity extends SingleActivity {
    //控件
    private TextView mTextViewCategory;
    private RecyclerView mRecyclerView;


    //数据
    private String category;
    private ArticleAdapter mArticleAdapter;
    private String url;


    @Override
    protected void initView() {
        mTextViewCategory = (TextView) findViewById(R.id.tv_category);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {
        category= getIntent().getStringExtra(Constant.CATEGORY);//文章类型
        mTextViewCategory.setText(category);//将所选文章类型显示

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (mArticleAdapter!=null){
            mRecyclerView.setAdapter(mArticleAdapter);
        }else {
            mArticleAdapter=getArticleData(category);
            mRecyclerView.setAdapter(mArticleAdapter);
        }

    }

    private ArticleAdapter getArticleData(String category) {

        ArticleAdapter adapter=null;
        String url1="http://zuowen.api.juhe.cn/zuowen/baseList?key=3b2c2b4226982ab5d4d2d96f2c870478&gradeId=";
        String url2="http://zuowen.api.juhe.cn/zuowen/baseList?key=3b2c2b4226982ab5d4d2d96f2c870478&typeId=";
        String url3="http://zuowen.api.juhe.cn/zuowen/baseList?key=3b2c2b4226982ab5d4d2d96f2c870478&wordId=";
        String url4="http://zuowen.api.juhe.cn/zuowen/baseList?key=3b2c2b4226982ab5d4d2d96f2c870478&levelId=";
        ArticleAdapter adapter1 = getId(category, Fragment4.gradeName, Fragment4.gradeId,url1);
        ArticleAdapter adapter2 = getId(category, Fragment4.typeName, Fragment4.typeId,url2);
        ArticleAdapter adapter3 = getId(category, Fragment4.wordsName, Fragment4.wordsId,url3);
        ArticleAdapter adapter4 = getId(category, Fragment4.levelName, Fragment4.levelId,url4);
        if (adapter1 != null) {
            adapter = adapter1;
        } else if (adapter2 != null) {
            adapter = adapter2;
        } else if (adapter3 != null) {
            adapter = adapter3;
        } else if (adapter4 != null) {
            adapter = adapter4;
        }

        return adapter;
    }


    private ArticleAdapter getId(String category, String[] name, int[] id,String url) {
        int mId;
        for (int i = 0; i < name.length; i++) {
            String categoryName=name[i];
            if (category.equals(categoryName)) {
                mId = id[i];
                Utils.showLog("id=" + id);
                url = url + mId;
                String myData = Utils.getDataFromSP(this, name[i]);//获取本地数据

                //如果数据为空，就网络请求获取数据
                if (TextUtils.isEmpty(myData)){
                    Utils.requestArticle(this,name[i], url);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    String data = Utils.getDataFromSP(this, name[i]);
                    if (data!=null){
                        Utils.showLog("myData=" + myData);//如果获取到数据，就可以解析了
                        Article.ResultBean resultBean=parseArticleList(data);//将解析好的数据封装成一个实体类
                        if (resultBean!=null){
                            mArticleAdapter=new ArticleAdapter(resultBean);
                        }else {
                            Utils.showLog("resultBean is null");
                        }
                    }else {
                        Utils.showLog("data is null");
                    }



                }else {
                    Utils.showLog("myData=" + myData);//如果获取到数据，就可以解析了
                    Article.ResultBean resultBean=parseArticleList(myData);
                    if (resultBean!=null){
                        mArticleAdapter=new ArticleAdapter(resultBean);
                    }else {
                        Utils.showLog("resultBean is null");
                    }
                }

            }
        }
        return mArticleAdapter;
    }

    private Article.ResultBean parseArticleList(String data) {
        Article.ResultBean resultBean = new Article.ResultBean();
        List<Article.ResultBean.ListBean> listBeans = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            JSONObject jsonObject1 = new JSONObject(result);
            int totalCount = jsonObject1.getInt("totalCount");
            int page = jsonObject1.getInt("page");
            int size = jsonObject1.getInt("size");
            String list = jsonObject1.getString("list");
            Utils.showLog("result= " + result);
            Utils.showLog("totalCount= " + totalCount);
            Utils.showLog("page= " + page);
            Utils.showLog("size= " + size);
            Utils.showLog("list= " + list);


            JSONArray jsonArray = new JSONArray(list);
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    int gradeId = jsonObject2.getInt("gradeId");
                    int level = jsonObject2.getInt("level");
                    String name = jsonObject2.getString("name");
                    String time = jsonObject2.getString("time");
                    int typeId = jsonObject2.getInt("typeId");
                    int wordId = jsonObject2.getInt("wordId");
                    String writer = jsonObject2.getString("writer");
                    int id = jsonObject2.getInt("id");
                    Utils.showLog("gradeId= " + gradeId);
                    Utils.showLog("level= " + level);
                    Utils.showLog("name= " + name);
                    Utils.showLog("time= " + time);
                    Utils.showLog("typeId= " + typeId);
                    Utils.showLog("wordId= " + wordId);
                    Utils.showLog("writer= " + writer);
                    Utils.showLog("id= " + id);


                    Article.ResultBean.ListBean listBean = new Article.ResultBean.ListBean();
                    listBean.setGradeId(gradeId);
                    listBean.setLevel(level);
                    listBean.setName(name);
                    listBean.setTime(time);
                    listBean.setTypeId(typeId);
                    listBean.setWordId(wordId);
                    listBean.setWriter(writer);
                    listBean.setId(id);

                    listBeans.add(listBean);

                }
            }

            resultBean.setTotalCount(totalCount);
            resultBean.setPage(page);
            resultBean.setSize(size);
            resultBean.setList(listBeans);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultBean;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_article;
    }
}
