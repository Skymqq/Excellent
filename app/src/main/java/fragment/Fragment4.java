package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.siso.edu.mqq.ArticleActivity;
import com.siso.edu.mqq.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import adapter.MyExpandBaseAdapter;
import utils.Constant;
import utils.Utils;

public class Fragment4 extends Fragment {
    //控件
    private View mView;
    private ExpandableListView mExpandableListView;

    //数据
    private String[] mStringsGroup = new String[]{"年级", "题材", "字数", "等级"};
    public static String[] gradeName = new String[15];//年级name
    public static String[] typeName = new String[22];//题材name
    public static String[] wordsName = new String[11];//字数name
    public static String[] levelName = new String[4];//等级name

    public static int[] gradeId = new int[15];//年级id
    public static int[] typeId = new int[22];//题材id
    public static int[] wordsId = new int[11];//字数id
    public static int[] levelId = new int[4];//等级id


    private String[][] mStringsChild =
            new String[][]{{"一年级", "二年级", "三年级", "四年级", "五年级", "六年级", "小升初", "初一", "初二", "初三", "中考", "高一", "高二", "高三", "高考"},
                    {"叙事", "写景", "状物", "议论文", "说明文", "书信", "日记", "读后感", "演讲稿", "小说", "童话", "散文", "寓言", "诗歌", "游记", "读书笔记", "看图", "想象", "话题", "应用文", "其他"},
                    {"100字", "200字", "300字", "400字", "500字", "600字", "700字", "800字", "1000字", "1200字", "1200字以上"},
                    {"差", "中", "良", "优"}};
    private MyExpandBaseAdapter mMyExpandBaseAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment4, container, false);
        initView();//初始化UI视图控件
        initData();//初始化数据
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Utils.showToast(getActivity(), mStringsChild[groupPosition][childPosition]);
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                intent.putExtra(Constant.CATEGORY, mStringsChild[groupPosition][childPosition]);//将分类传递到下一个activity
                startActivity(intent);

                return false;
            }
        });
    }

    private void initView() {
        mExpandableListView = (ExpandableListView) mView.findViewById(R.id.expandableListView);
    }

    private void initData() {
        String url_myClass = "http://zuowen.api.juhe.cn/zuowen/typeList?key=3b2c2b4226982ab5d4d2d96f2c870478&id=1";
        String url_myType = "http://zuowen.api.juhe.cn/zuowen/typeList?key=3b2c2b4226982ab5d4d2d96f2c870478&id=2";
        String url_myWords = "http://zuowen.api.juhe.cn/zuowen/typeList?key=3b2c2b4226982ab5d4d2d96f2c870478&id=3";
        String url_myLevel = "http://zuowen.api.juhe.cn/zuowen/typeList?key=3b2c2b4226982ab5d4d2d96f2c870478&id=4";

        Utils.initMyClass(getActivity(),url_myClass);//初始化MyClass
        Utils.initMyType(getActivity(),url_myType);//初始化MyType
        Utils.initMyWords(getActivity(),url_myWords);//初始化MyWords
        Utils.initMyLevel(getActivity(),url_myLevel);//初始化MyLevel


        mMyExpandBaseAdapter = new MyExpandBaseAdapter(mStringsGroup, mStringsChild);
        mExpandableListView.setAdapter(mMyExpandBaseAdapter);
    }


}

