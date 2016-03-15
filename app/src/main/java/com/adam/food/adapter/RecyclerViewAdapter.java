package com.adam.food.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adam.food.R;
import com.adam.food.domain.TgClassify;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by adamlee on 2016/3/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_FOOTER = 1;

    private List<TgClassify> tgClassifies;
    private Typeface typeface;
    private Context mContext;

    public RecyclerViewAdapter(List<TgClassify> tgClassifies, Context context) {
        this.tgClassifies = tgClassifies;
        this.mContext = context;
        typeface = Typeface.createFromAsset(context.getAssets(), "cdm.otf");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_recyclerview, parent, false);
            return new ItemHolder(view);
        } else {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_footer, parent, false);

            return new FooterHolder(item);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ITEM) {
            ((ItemHolder) holder).tvitem.setTypeface(typeface);
            ((ItemHolder) holder).tvitem.setText(tgClassifies.get(position).getName() + "");
        } else {
            ((FooterHolder) holder).tvfooter.setTypeface(typeface);
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (position == tgClassifies.size()) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return tgClassifies.size() + 1;
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_item)
        TextView tvitem;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class FooterHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_footer)
        TextView tvfooter;

        public FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
