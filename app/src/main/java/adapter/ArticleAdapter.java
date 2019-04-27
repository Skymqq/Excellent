package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.siso.edu.mqq.ArticleContentActivity;
import com.siso.edu.mqq.R;

import java.util.ArrayList;
import java.util.List;

import bean.Article;
import utils.Utils;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<Article.ResultBean.ListBean> mListBeans;
    private Context mContext;
    private Article.ResultBean mResultBean;


    public ArticleAdapter(Article.ResultBean resultBean) {
        mResultBean = resultBean;
        mListBeans = mResultBean.getList();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_articleName);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null) {
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_article, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Article.ResultBean.ListBean listBean = mListBeans.get(i);
        viewHolder.mTextView.setText(listBean.getName());
        viewHolder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ArticleContentActivity.class);
                intent.putExtra("title", listBean.getName());
                intent.putExtra("writer", listBean.getWriter());
                intent.putExtra("id", listBean.getId());
                Utils.showLog("title="+listBean.getName());
                Utils.showLog("writer="+listBean.getWriter());
                Utils.showLog("id="+listBean.getId());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mListBeans.size();
    }


}
