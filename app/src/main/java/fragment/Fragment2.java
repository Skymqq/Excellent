package fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.siso.edu.mqq.R;

import bean.OnResponse;
import utils.Utils;


public class Fragment2 extends Fragment {
    private View mView;
    private TextView mTextView1, mTextView2, mTextView3, mTextView4, mTextView5, mTextView6, mTextView7;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private static OnResponse mOnResponse;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment2, container, false);
        initView();//初始化UI视图控件
        initData();//初始化数据
        return mView;
    }

    @Override
    public void onResume() {
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

                        if (mOnResponse != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    for (String content : mOnResponse.getData().getOrigin().getContent()) {
                                        stringBuilder.append(content);
                                    }
                                    mTextView1.setText(mOnResponse.getData().getOrigin().getTitle());//标题
                                    mTextView2.setText("作者: " + mOnResponse.getData().getOrigin().getAuthor());//作者
                                    mTextView3.setText("朝代: " + mOnResponse.getData().getOrigin().getDynasty());//朝代
                                    mTextView4.setText(stringBuilder.toString());//内容
                                    mTextView5.setText("翻译: " + "\n" + mOnResponse.getData().getOrigin().getTranslate());//翻译
                                    mTextView6.setText("");
                                    mTextView7.setText("点击量:  " + mOnResponse.getData().getPopularity());

                                    mSwipeRefreshLayout.setColorSchemeResources(R.color.refreshColor);
                                    mSwipeRefreshLayout.setRefreshing(false);//刷新事件结束，并隐藏进度条
                                }
                            });


                        }

                    }
                }).start();

            }
        });


    }

    private void initView() {
        mTextView1 = (TextView) mView.findViewById(R.id.tv_1);
        mTextView2 = (TextView) mView.findViewById(R.id.tv_2);
        mTextView3 = (TextView) mView.findViewById(R.id.tv_3);
        mTextView4 = (TextView) mView.findViewById(R.id.tv_4);
        mTextView5 = (TextView) mView.findViewById(R.id.tv_5);
        mTextView6 = (TextView) mView.findViewById(R.id.tv_6);
        mTextView7 = (TextView) mView.findViewById(R.id.tv_7);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipeRefreshLayout);


    }

    private void initData() {
        Utils.requestPoetry();//网络请求获取数据
    }

    //用于获取共享数据对象，例（获取从Utils类中网络请求返回数据的封装对象OnResponse）
    public static void getResponse(OnResponse onResponse) {
        mOnResponse = onResponse;
    }


}

