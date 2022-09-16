package com.fintech.potstest.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fintech.potstest.Model.ShopNameCategoryModel;
import com.fintech.potstest.R;
import com.fintech.potstest.Utils.Method;

import java.util.List;

public class ShopNewCategoryAdapter extends RecyclerView.Adapter<ShopNewCategoryAdapter.ViewHolder> {
    private List<ShopNameCategoryModel> listdata;
    private Activity activity;
    Method method;
    private static int lastClickedPosition = -1;
    private int selectedItem;
    public ShopNewCategoryAdapter(List<ShopNameCategoryModel> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;
        selectedItem=0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.shop_name_category_row, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        method=new Method(activity);
        holder.name.setText(listdata.get(position).getName());
        holder.name.setTextColor(activity.getResources().getColor(R.color.txtxt));
        if (selectedItem==position)
        {
            holder.name.setTextColor(activity.getResources().getColor(R.color.white));
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int previousItem = selectedItem;
                selectedItem = position;
                notifyItemChanged(previousItem);
                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;

        RelativeLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.layout = (RelativeLayout) itemView.findViewById(R.id.layout);



        }

    }

}
