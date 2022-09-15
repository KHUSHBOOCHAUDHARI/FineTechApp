package com.fintech.potstest.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fintech.potstest.Model.ShopCategoryModel;
import com.fintech.potstest.R;
import com.fintech.potstest.Utils.Method;

import java.util.List;

public class ShopCategoryAdapter extends RecyclerView.Adapter<ShopCategoryAdapter.ViewHolder> {
    private List<ShopCategoryModel> listdata;
    private Activity activity;
    Method method;

    public ShopCategoryAdapter(List<ShopCategoryModel> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.shop_category_row, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        method=new Method(activity);
        holder.name.setText(listdata.get(position).getName());
        holder.imageView.setImageResource(listdata.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        ImageView imageView;
        RelativeLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.layout = (RelativeLayout) itemView.findViewById(R.id.layout);
            this.imageView = (ImageView) itemView.findViewById(R.id.image);


        }

    }

}
