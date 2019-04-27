package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.siso.edu.mqq.FruitActivity;
import com.siso.edu.mqq.R;

import org.w3c.dom.Text;

import java.util.List;

import bean.Fruit;

import static utils.Constant.FRUIT_IMAGE_ID;
import static utils.Constant.FRUIT_NAME;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context context;
    private List<Fruit> mFruits;

    public FruitAdapter(List<Fruit> fruits) {
        mFruits = fruits;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (context == null) {
            context = viewGroup.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.fruit_item, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Fruit fruit = mFruits.get(position);
                Intent intent = new Intent(context, FruitActivity.class);
                String name = fruit.getName();
                int id = fruit.getImageId();
                intent.putExtra(FRUIT_NAME, name);
                intent.putExtra(FRUIT_IMAGE_ID, id);
                context.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Fruit fruit = mFruits.get(i);
        viewHolder.mFruitTextView.setText(fruit.getName());
        Glide.with(context).load(fruit.getImageId()).into(viewHolder.mFruitImageView);
    }

    @Override
    public int getItemCount() {
        return mFruits.size();
    }


    //FruitAdapter中的内部类，用于初始化RecyclerView中列表项的UI视图控件
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        ImageView mFruitImageView;
        TextView mFruitTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardView = (CardView) itemView;
            mFruitImageView = (ImageView) itemView.findViewById(R.id.iv_fruitPhoto);
            mFruitTextView = (TextView) itemView.findViewById(R.id.tv_fruitName);
        }
    }
}
