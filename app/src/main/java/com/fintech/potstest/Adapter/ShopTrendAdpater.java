package com.fintech.potstest.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fintech.potstest.Model.ShopTrendModel;
import com.fintech.potstest.R;

import java.util.List;

public class ShopTrendAdpater extends RecyclerView.Adapter<ShopTrendAdpater.ViewHolder> {
    private final List<ShopTrendModel> listdata;
    private final Activity activity;

    public ShopTrendAdpater(List<ShopTrendModel> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.shop_trend_row, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final ShopTrendModel myListData = listdata.get(position);
        holder.discount_pre.setText(listdata.get(position).getDiscounr());
        holder.discount_pre_title.setText(listdata.get(position).getDiscounttitle());
        holder.extradiscount_pre.setText(listdata.get(position).getExtra());

        holder.image.setImageResource(listdata.get(position).getImage());


    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView discount_pre,discount_pre_title,extradiscount_pre;
        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            this.discount_pre = itemView.findViewById(R.id.discount_pre);
            this.discount_pre_title = itemView.findViewById(R.id.discount_pre_title);
            this.extradiscount_pre = itemView.findViewById(R.id.extradiscount_pre);
            this.image = itemView.findViewById(R.id.image);
        }

    }
}
