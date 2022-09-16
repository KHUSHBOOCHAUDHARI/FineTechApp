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

import com.fintech.potstest.R;
import com.fintech.potstest.Utils.Method;

import java.util.List;

public class JackpotActivateAdapter extends RecyclerView.Adapter<JackpotActivateAdapter.ViewHolder> {
    private List<JackpotActivateModel> listdata;
    private Activity activity;
    Method method;

    public JackpotActivateAdapter(List<JackpotActivateModel> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.reward_activate_row, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        method=new Method(activity);
        holder.main_image.setImageResource(listdata.get(position).getMain_image());
        holder.sub_image.setImageResource(listdata.get(position).getSub_image());
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        ImageView main_image,sub_image;
        RelativeLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.main_image = (ImageView) itemView.findViewById(R.id.main_image);
            this.sub_image = (ImageView) itemView.findViewById(R.id.sub_image);
            this.layout = (RelativeLayout) itemView.findViewById(R.id.layout);

        }

    }

}
