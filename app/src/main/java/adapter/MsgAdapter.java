package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.siso.edu.mqq.R;

import java.util.List;

import bean.Msg;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<Msg> mMsgList;//Msg数据集合

    //MsgAdapter构造器
    public MsgAdapter(List<Msg> msgList) {
        mMsgList = msgList;
    }

    //静态内部类ViewHolder用于在被构造时初始化视图控件
    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout leftLayout, rightLayout;

        TextView leftMsg, rightMsg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //根据传进来的View对象来初始化Ui视图控件
            leftLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout_left);
            rightLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout_right);
            leftMsg = (TextView) itemView.findViewById(R.id.tv_left);
            rightMsg = (TextView) itemView.findViewById(R.id.tv_right);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.msg_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Msg msg = mMsgList.get(i);
        if (msg.getType() == Msg.TYPE_RECEIVE) {
            //如果是收到的消息，则显示左边的消息布局，并且将右边的消息布局给隐藏
            viewHolder.leftLayout.setVisibility(View.VISIBLE);//显示左边消息布局
            viewHolder.rightLayout.setVisibility(View.GONE);//隐藏右边的消息布局
            viewHolder.leftMsg.setText(msg.getContent());//设置左边消息内容
        } else if (msg.getType() == Msg.TYPE_SEND) {
            //如果是发送的消息，则隐藏左边的消息布局，并且将右边的消息布局给显示
            viewHolder.leftLayout.setVisibility(View.GONE);//隐藏左边的消息布局
            viewHolder.rightLayout.setVisibility(View.VISIBLE);//显示右边的消息布局
            viewHolder.rightMsg.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }


}
