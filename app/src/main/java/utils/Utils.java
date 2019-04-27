package utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.jinrishici.sdk.android.JinrishiciClient;
import com.jinrishici.sdk.android.listener.JinrishiciCallback;
import com.jinrishici.sdk.android.model.JinrishiciRuntimeException;
import com.jinrishici.sdk.android.model.PoetySentence;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.MyClass;
import bean.MyLevel;
import bean.MyType;
import bean.MyWords;
import bean.OnResponse;
import fragment.Fragment2;
import okhttp3.Call;
import okhttp3.Callback;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static fragment.Fragment4.gradeId;
import static fragment.Fragment4.gradeName;
import static fragment.Fragment4.levelId;
import static fragment.Fragment4.levelName;
import static fragment.Fragment4.typeId;
import static fragment.Fragment4.typeName;
import static fragment.Fragment4.wordsId;
import static fragment.Fragment4.wordsName;

/**
 * 此类为工具类
 */
public class Utils {
    public static SharedPreferences sSharedPreferences;

    //初始化SharedPreferences
    public static void initSP(Context context){
        if (sSharedPreferences==null){
            sSharedPreferences=context.getSharedPreferences(Constant.SP_KEY,Context.MODE_PRIVATE);
        }
    }

    //将第一次进入GuideActivity的boolean类型的值存放在本地
    public static void putBooleanFirst(Context context,String key,boolean value){
        if (sSharedPreferences==null){
            sSharedPreferences=context.getSharedPreferences(Constant.SP_KEY,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sSharedPreferences.edit();
            editor.putBoolean(key,value);
            editor.commit();
        }else {
            SharedPreferences.Editor editor=sSharedPreferences.edit();
            editor.putBoolean(key,value);
            editor.commit();
        }
    }

    //取出本地boolean类型的值
    public static boolean getBooleanFirst(Context context,String key){
        boolean value=false;
        if (sSharedPreferences==null){
            sSharedPreferences=context.getSharedPreferences(Constant.SP_KEY,Context.MODE_PRIVATE);
            value=sSharedPreferences.getBoolean(key,false);
        }else {
            value=sSharedPreferences.getBoolean(key,false);
        }
        return value;
    }


    //全局Toast提示
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    //打印日志
    public static void showLog(String msg) {
        Log.e(Constant.TAG, msg);
    }

    //网络请求（异步）获取今日诗词（Fragment2中获取的）
    public static void requestPoetry() {

        JinrishiciClient client = new JinrishiciClient();
        client.getOneSentenceBackground(new JinrishiciCallback() {
            @Override
            public void done(PoetySentence poetySentence) {
                //TODO do something
                //在这里进行你的逻辑处理
                getData();

            }

            @Override
            public void error(JinrishiciRuntimeException e) {
                Log.w(Constant.TAG, "error: code = " + e.getCode() + " message = " + e.getMessage());
                //TODO do something else
            }
        });

    }

    //获取诗歌数据
    private static void getData() {
        String url = "https://v2.jinrishici.com/token";

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        //Get异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(Constant.TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString = response.body().string();
                Log.e(Constant.TAG, "onResponse: " + responseString);
                try {
                    JSONObject jsonObject = new JSONObject(responseString);
                    String data = jsonObject.getString("data");
                    Log.e(Constant.TAG, "status: " + jsonObject.getString("status"));
                    Log.e(Constant.TAG, "status: " + data);
                    setToken(data);//将网络请求返回的Token字符串，设置进入HTTP协议的Header中
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    //将网络请求返回的Token字符串，设置进入HTTP协议的Header中
    public static void setToken(String token) {
        String url = "https://v2.jinrishici.com/sentence";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .header("X-User-Token", token)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(Constant.TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString = response.body().string();
                Log.e(Constant.TAG, "onResponse: " + responseString);
                if (responseString != null) {
                    OnResponse onResponse = parsePoetryData(responseString);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Fragment2.getResponse(onResponse);//将网络请求返回的数据封装成一个OnResponse对象，并返回给Fragment2中的静态变量

                }

            }
        });

    }

    //解析数据
    public static OnResponse parsePoetryData(String responseString) {
        OnResponse onResponse = new OnResponse();
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            Log.e(Constant.TAG, "status: " + jsonObject.getString("status"));
            Log.e(Constant.TAG, "data: " + jsonObject.getString("data"));
            Log.e(Constant.TAG, "token: " + jsonObject.getString("token"));
            Log.e(Constant.TAG, "ipAddress: " + jsonObject.getString("ipAddress"));
            Log.e(Constant.TAG, "warning: " + jsonObject.getString("warning"));


            onResponse.setStatus(jsonObject.getString("status"));
            onResponse.setToken(jsonObject.getString("token"));
            onResponse.setIpAddress(jsonObject.getString("ipAddress"));
            onResponse.setWarning(jsonObject.getString("warning"));


            //解析data中的数据
            String data = jsonObject.getString("data");
            JSONObject jsonObject1 = new JSONObject(data);
            Log.e(Constant.TAG, "id: " + jsonObject1.getString("id"));
            Log.e(Constant.TAG, "content: " + jsonObject1.getString("content"));
            Log.e(Constant.TAG, "popularity: " + jsonObject1.getString("popularity"));
            Log.e(Constant.TAG, "origin: " + jsonObject1.getString("origin"));
            Log.e(Constant.TAG, "matchTags: " + jsonObject1.getString("matchTags"));
            Log.e(Constant.TAG, "cacheAt: " + jsonObject1.getString("cacheAt"));

            List<String> cacheAtList = new ArrayList<>();
            cacheAtList.add(jsonObject1.getString("cacheAt"));


            OnResponse.DataBean dataBean = new OnResponse.DataBean();
            dataBean.setId(jsonObject1.getString("id"));
            dataBean.setContent(jsonObject1.getString("content"));
            dataBean.setPopularity(Integer.valueOf(jsonObject1.getString("popularity")));

            dataBean.setMatchTags(cacheAtList);
            dataBean.setCacheAt(jsonObject1.getString("cacheAt"));


            //解析origin中的数据
            String origin = jsonObject1.getString("origin");
            JSONObject jsonObject2 = new JSONObject(origin);
            Log.e(Constant.TAG, "title: " + jsonObject2.getString("title"));
            Log.e(Constant.TAG, "dynasty: " + jsonObject2.getString("dynasty"));
            Log.e(Constant.TAG, "author: " + jsonObject2.getString("author"));
            Log.e(Constant.TAG, "content: " + jsonObject2.getString("content"));
            Log.e(Constant.TAG, "translate: " + jsonObject2.getString("translate"));


            String[] strings = jsonObject2.getString("content").split("\\p{Punct}");
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : strings) {
                stringBuilder.append(s).append("\n");
            }
            String jsonArrayContent = stringBuilder.toString();
            String jsonArrayTranslate = jsonObject2.getString("translate");
            List<String> content = new ArrayList<>();
            List<String> translate = new ArrayList<>();
            content.add(jsonArrayContent);
            translate.add(jsonArrayTranslate);


            OnResponse.DataBean.OriginBean originBean = new OnResponse.DataBean.OriginBean();
            originBean.setTitle(jsonObject2.getString("title"));//参数String
            originBean.setDynasty(jsonObject2.getString("dynasty"));//参数String
            originBean.setAuthor(jsonObject2.getString("author"));//参数String
            originBean.setContent(content);//参数List<String>
            originBean.setTranslate(translate);//参数List<String>

            dataBean.setOrigin(originBean);
            onResponse.setData(dataBean);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onResponse;
    }

    public static void requestArticle(final Context context, final String key, String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(Constant.TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString = response.body().string();
                Log.e(Constant.TAG, "onResponse: " + responseString);
                if (responseString != null) {
                    saveDataToSP(context, key, responseString);//将数据保存到本地SP
                }
            }
        });
    }

    //将数据保存到本地SP
    public static void saveDataToSP(Context context, String key, String value) {
        if (sSharedPreferences == null) {
            sSharedPreferences = context.getSharedPreferences(Constant.SP_KEY, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putString(key, value);
            editor.commit();
        } else {
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putString(key, value);
            editor.commit();
        }
    }

    //从本地SP中取出数据
    public static String getDataFromSP(Context context, String key) {
        String data;
        if (sSharedPreferences == null) {
            sSharedPreferences = context.getSharedPreferences(Constant.SP_KEY, Context.MODE_PRIVATE);

        }
        data = sSharedPreferences.getString(key, "");
        return data;
    }

    //解析MyClass数据
    public static List<Map<String, String>> parseArticleClass(Context context, String key, String url) {

        List<Map<String, String>> mapList = new ArrayList<>();
        String data = getDataFromSP(context, key);
        if (data != null) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                String result = jsonObject.getString("result");
                JSONArray jsonArray = new JSONArray(result);
                if (jsonArray.length() > 0) {
                    List<MyClass.ResultBean> resultBeanList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = null;
                        try {
                            jsonObject1 = jsonArray.getJSONObject(i);
                            String name = jsonObject1.get("name").toString();
                            String id = jsonObject1.get("id").toString();
                            MyClass.ResultBean resultBean = new MyClass.ResultBean();
                            resultBean.setName(name);
                            resultBean.setId(Integer.parseInt(id));
                            resultBeanList.add(resultBean);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    if (resultBeanList.size() > 0) {
                        Collections.sort(resultBeanList, new Comparator<MyClass.ResultBean>() {
                            @Override
                            public int compare(MyClass.ResultBean o1, MyClass.ResultBean o2) {
                                return o1.getId() - o2.getId();//按照id从小到大排序
                            }
                        });
                        for (MyClass.ResultBean resultBean : resultBeanList) {
                            Map<String, String> map = new HashMap<>();
                            map.put("name", resultBean.getName());
                            map.put("id", String.valueOf(resultBean.getId()));
                            mapList.add(map);
                            Log.e(Constant.TAG, "name=" + resultBean.getName());
                            Log.e(Constant.TAG, "id=" + resultBean.getId());
                        }
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return mapList;
    }

    //解析MyType数据
    public static List<Map<String, String>> parseArticleType(Context context, String key, String url) {
        List<Map<String, String>> mapList = new ArrayList<>();
        String data = getDataFromSP(context, key);
        if (data != null) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                String error_code = jsonObject.get("error_code").toString();
                String reason = jsonObject.get("reason").toString();
                String result = jsonObject.get("result").toString();
                Log.e(Constant.TAG, "result===> " + result);
                JSONArray jsonArray = new JSONArray(result);
                if (jsonArray.length() > 0) {
                    List<MyType.ResultBean> resultBeanList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String name = jsonObject1.get("name").toString();
                        String id = jsonObject1.get("id").toString();
                        MyType.ResultBean resultBean = new MyType.ResultBean();
                        resultBean.setName(name);
                        resultBean.setId(Integer.parseInt(id));
                        resultBeanList.add(resultBean);
                    }

                    if (resultBeanList.size() > 0) {
                        Collections.sort(resultBeanList, new Comparator<MyType.ResultBean>() {
                            @Override
                            public int compare(MyType.ResultBean o1, MyType.ResultBean o2) {
                                return o1.getId() - o2.getId();
                            }
                        });
                        for (MyType.ResultBean resultBean : resultBeanList) {
                            Map<String, String> map = new HashMap<>();
                            map.put("name", resultBean.getName());
                            map.put("id", String.valueOf(resultBean.getId()));
                            mapList.add(map);
                            Log.e(Constant.TAG, "name=" + resultBean.getName());
                            Log.e(Constant.TAG, "id=" + resultBean.getId());
                        }


                    }


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return mapList;
    }

    //解析MyWords数据
    public static List<Map<String, String>> parseArticleWords(Context context, String key, String url) {
        List<Map<String, String>> mapList = new ArrayList<>();
        String data = getDataFromSP(context, key);
        if (data != null) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                String error_code = jsonObject.get("error_code").toString();
                String reason = jsonObject.get("reason").toString();
                String result = jsonObject.get("result").toString();
                Log.e(Constant.TAG, "result===> " + result);
                JSONArray jsonArray = new JSONArray(result);
                if (jsonArray.length() > 0) {
                    List<MyWords.ResultBean> resultBeanList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String name = jsonObject1.get("name").toString();
                        String id = jsonObject1.get("id").toString();
                        MyWords.ResultBean resultBean=new MyWords.ResultBean();
                        resultBean.setName(name);
                        resultBean.setId(Integer.parseInt(id));
                        resultBeanList.add(resultBean);
                    }

                    if (resultBeanList.size()>0){
                        Collections.sort(resultBeanList, new Comparator<MyWords.ResultBean>() {
                            @Override
                            public int compare(MyWords.ResultBean o1, MyWords.ResultBean o2) {
                                return o1.getId()-o2.getId();//按照id从小到大排序
                            }
                        });
                        for (MyWords.ResultBean resultBean:resultBeanList){
                            Map<String, String> map = new HashMap<>();
                            map.put("name",resultBean.getName());
                            map.put("id",String.valueOf(resultBean.getId()));
                            mapList.add(map);
                            Log.e(Constant.TAG, "name="+resultBean.getName() );
                            Log.e(Constant.TAG, "id="+resultBean.getId() );
                        }
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mapList;
    }

    //解析MyLevel数据
    public static List<Map<String, String>> parseArticleLevel(Context context, String key, String url) {
        List<Map<String, String>> mapList = new ArrayList<>();
        String data = getDataFromSP(context, key);
        if (data != null) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                String result = jsonObject.get("result").toString();
                JSONArray jsonArray = new JSONArray(result);
                if (jsonArray.length() > 0) {
                    List<MyLevel.ResultBean> resultBeanList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String name = jsonObject1.get("name").toString();
                        String id = jsonObject1.get("id").toString();
                        MyLevel.ResultBean resultBean = new MyLevel.ResultBean();
                        resultBean.setName(name);
                        resultBean.setId(Integer.parseInt(id));
                        resultBeanList.add(resultBean);
                    }

                    if (resultBeanList.size() > 0) {
                        Collections.sort(resultBeanList, new Comparator<MyLevel.ResultBean>() {
                            @Override
                            public int compare(MyLevel.ResultBean o1, MyLevel.ResultBean o2) {
                                return o1.getId() - o2.getId();//按照id从小到大排序
                            }
                        });
                        for (MyLevel.ResultBean resultBean : resultBeanList) {
                            Map<String, String> map = new HashMap<>();
                            map.put("name", resultBean.getName());
                            map.put("id", String.valueOf(resultBean.getId()));
                            mapList.add(map);
                            Log.e(Constant.TAG, "name=" + resultBean.getName());
                            Log.e(Constant.TAG, "id=" + resultBean.getId());
                        }

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return mapList;
    }



    //MyClass
    public static void initMyClass(Context context,String url_myClass) {
        String myClassSP = Utils.getDataFromSP(context, Constant.MYClASS_KEY);
        if (TextUtils.isEmpty(myClassSP)) {
            Utils.requestArticle(context, Constant.MYClASS_KEY, url_myClass);//请求MyClass数据
            getArticleData(context,Constant.MYClASS_KEY, url_myClass, gradeName, gradeId);
        } else {
            getArticleData(context,Constant.MYClASS_KEY, url_myClass, gradeName, gradeId);
        }
    }

    //MyType
    public static void initMyType(Context context,String url_myType) {
        String myTypeSP = Utils.getDataFromSP(context, Constant.MYTYPE_KEY);
        if (myTypeSP.isEmpty()) {
            Utils.requestArticle(context, Constant.MYTYPE_KEY, url_myType);//MyType数据
            getArticleData(context,Constant.MYTYPE_KEY, url_myType, typeName, typeId);
        } else {
            getArticleData(context,Constant.MYTYPE_KEY, url_myType, typeName, typeId);
        }
    }

    //MyWords
    public static void initMyWords(Context context,String url_myWords) {
        String myWords = Utils.getDataFromSP(context, Constant.MYWORDS_KEY);
        if (myWords.isEmpty()) {
            Utils.requestArticle(context, Constant.MYWORDS_KEY, url_myWords);//MyWords数据
            getArticleData(context,Constant.MYWORDS_KEY, url_myWords, wordsName, wordsId);
        } else {
            getArticleData(context,Constant.MYWORDS_KEY, url_myWords, wordsName, wordsId);
        }
    }

    //MyLevel
    public static void initMyLevel(Context context,String url_myLevel) {
        String myLevel = Utils.getDataFromSP(context, Constant.MYLEVEL_KEY);
        if (myLevel.isEmpty()) {
            Utils.requestArticle(context, Constant.MYLEVEL_KEY, url_myLevel);//MyLevel数据
            getArticleData(context,Constant.MYLEVEL_KEY, url_myLevel, levelName, levelId);
        } else {
            getArticleData(context,Constant.MYLEVEL_KEY, url_myLevel, levelName, levelId);
        }
    }

    //获取文章数据
    public static void getArticleData(Context context,String key, String url, String[] name, int[] id) {
        List<Map<String, String>> mapList = new ArrayList<>();
        if (key.equals(Constant.MYClASS_KEY)) {
            mapList = Utils.parseArticleClass(context, key, url);//解析MyClass数据
        } else if (key.equals(Constant.MYTYPE_KEY)) {
            mapList = Utils.parseArticleType(context, key, url);//解析MyType数据
        } else if (key.equals(Constant.MYWORDS_KEY)) {
            mapList = Utils.parseArticleWords(context, key, url);//解析MyWords数据
        } else if (key.equals(Constant.MYLEVEL_KEY)) {
            mapList = Utils.parseArticleLevel(context, key, url);//解析MyLevel数据
        }

        for (int i = 0; i < mapList.size(); i++) {
            Map<String, String> map = mapList.get(i);
            name[i] = map.get("name");
            id[i] = Integer.parseInt(map.get("id"));
        }
    }
}
