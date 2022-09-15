package com.fintech.potstest.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fintech.potstest.Model.ProductWiseModel;
import com.fintech.potstest.R;

import java.util.List;

public class ProductWiseAdpater extends RecyclerView.Adapter<ProductWiseAdpater.ViewHolder> {
    private final List<ProductWiseModel> listdata;
    private final Activity activity;

    public ProductWiseAdpater(List<ProductWiseModel> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.product_wise_row, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final ProductWiseModel myListData = listdata.get(position);

        holder.sub_title.setText(listdata.get(position).getSubTitle());
        holder.title.setText(listdata.get(position).getTitle());
        holder.price.setText(listdata.get(position).getPrice());
        holder.image.setImageResource(listdata.get(position).getImageView());


    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title,sub_title,price;
        ImageView image;
        CardView card_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.sub_title=itemView.findViewById(R.id.sub_title);
            this.title=itemView.findViewById(R.id.title);
            this.price=itemView.findViewById(R.id.price);
            this.image = itemView.findViewById(R.id.image);
            this.card_layout=itemView.findViewById(R.id.card_layout);
        }

    }

}
