package fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siso.edu.mqq.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import adapter.FruitAdapter;
import bean.Fruit;
import utils.Utils;

public class Fragment1 extends Fragment {
    //声明UI视图控件
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private View mView;


    //声明数组、集合容器
    private Fruit[] fruits = {new Fruit("Apple", R.drawable.ic_apple), new Fruit("Banana", R.drawable.ic_banana),
            new Fruit("Orange", R.drawable.ic_orange), new Fruit("Watermelon", R.drawable.ic_watermelon),
            new Fruit("Pear", R.drawable.ic_pear), new Fruit("Grape", R.drawable.ic_grape),
            new Fruit("Pineapple", R.drawable.ic_pineapple), new Fruit("Strawberry", R.drawable.ic_strawberry),
            new Fruit("Cherry", R.drawable.ic_cherry), new Fruit("Mango", R.drawable.ic_mango)};
    private List<Fruit> mFruits;
    private FruitAdapter mFruitAdapter;


    //Fragment碎片的生命周期
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment1, container, false);
        initView();//初始化UI视图控件
        initData();//初始化数据
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Data deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Utils.showToast(getActivity(), "Data restored");
                            }
                        }).show();
            }
        });


        mSwipeRefreshLayout.setColorSchemeResources(R.color.refreshColor);//设置刷新进度条的颜色
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();//刷新水果
            }
        });




    }

    //刷新水果，耗时操作需要使用子线线程，休眠2s之后初始化水果List集合，然后再将适配器中的数据更新
    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();//初始化水果
                        mFruitAdapter.notifyDataSetChanged();//刷新适配器
                        mSwipeRefreshLayout.setRefreshing(false);//刷新事件结束，并隐藏进度条
                    }
                });

            }
        }).start();
    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        mFloatingActionButton = (FloatingActionButton) mView.findViewById(R.id.fab);
    }

    private void initData() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mFruits = new ArrayList<>();
        mFruitAdapter = new FruitAdapter(initFruits());
        mRecyclerView.setAdapter(mFruitAdapter);

    }

    private List<Fruit> initFruits() {
        mFruits.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            mFruits.add(fruits[index]);
        }
        return mFruits;
    }
}

