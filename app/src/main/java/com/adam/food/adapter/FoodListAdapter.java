package com.adam.food.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adam.food.R;
import com.adam.food.base.Constant;
import com.adam.food.domain.foodlist.TgFoodList;
import com.adam.food.utils.ColorUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by adamlee on 2016/3/16.
 */
public class FoodListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_FOOTER = 1;
    private List<TgFoodList> tgClassifies;
    private Typeface typeface;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public FoodListAdapter(List<TgFoodList> tgClassifies, Context mContext) {
        this.tgClassifies = tgClassifies;
        this.mContext = mContext;
        typeface = Typeface.createFromAsset(mContext.getAssets(), "eo.ttf");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_food_list, parent, false);
            return new ItemHolder(view);
        } else {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_footer, parent, false);

            return new FooterHolder(item);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_ITEM) {
//            ((ItemHolder) holder).tvitem.setTypeface(typeface);
            ((ItemHolder) holder).tvname.setText(tgClassifies.get(position).getName() + "");
            Picasso.with(mContext).load(Constant.TGIMG_PREFIX+tgClassifies.get(position).getImg())
                    .placeholder(new ColorDrawable(ColorUtils.getRandomColor(mContext)))
                    .into(((ItemHolder) holder).img);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.onItemClick(v,position);
                    }
                }
            });

        } else {
            ((FooterHolder) holder).tvfooter.setTypeface(typeface);
        }
    }

    @Override
    public int getItemCount() {
        return tgClassifies.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == tgClassifies.size()) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    class FooterHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_footer)
        TextView tvfooter;

        public FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.img)
        ImageView img;
        @Bind(R.id.tv_name)
        TextView tvname;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

}
