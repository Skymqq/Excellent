package fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.siso.edu.mqq.R;

import java.util.ArrayList;
import java.util.List;

import adapter.MsgAdapter;
import bean.Msg;
import utils.Utils;

public class Fragment3 extends Fragment {
    //控件
    private RecyclerView mRecyclerView;
    private EditText mEditTextInputMsg;
    private Button mButtonSendMsg;
    private View mView;

    //数据
    private MsgAdapter mMsgAdapter;
    private List<Msg> mMsgList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment3, container, false);
        initView();//初始化UI视图控件
        initData();//初始化数据
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mButtonSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEditTextInputMsg.getText().toString();
                sendMsg(content);//发送消息
            }
        });
    }

    private void sendMsg(String content) {
        if (!"".equals(content)) {
            Msg msg = new Msg(content, Msg.TYPE_SEND);
            mMsgList.add(msg);
            mMsgAdapter.notifyItemInserted(mMsgList.size() - 1);//当有新消息时，刷新RecycleView中的显示
            mRecyclerView.scrollToPosition(mMsgList.size() - 1);//将RecyclerView定位到最后一行
            mEditTextInputMsg.setText("");
        }else {
            Utils.showToast(getActivity(),"输入不能为空");
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        mEditTextInputMsg = (EditText) mView.findViewById(R.id.et_inputMsg);
        mButtonSendMsg = (Button) mView.findViewById(R.id.btn_send);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initData() {
        mMsgList = getMsgData();
        mMsgAdapter = new MsgAdapter(mMsgList);
        mRecyclerView.setAdapter(mMsgAdapter);
    }

    private List<Msg> getMsgData() {
        List<Msg> msgList = new ArrayList<>();
        Msg msg1 = new Msg("Hello guy", Msg.TYPE_RECEIVE);
        Msg msg2 = new Msg("Hello Who is that?", Msg.TYPE_SEND);
        Msg msg3 = new Msg("This is Tom. Nice talking to you.", Msg.TYPE_RECEIVE);
        msgList.add(msg1);
        msgList.add(msg2);
        msgList.add(msg3);
        return msgList;
    }
}

