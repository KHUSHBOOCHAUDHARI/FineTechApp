package com.fintech.potstest.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fintech.potstest.Activity.ShopCreateGoalActivity;
import com.fintech.potstest.Activity.ShopDetailActivity;
import com.fintech.potstest.Model.ShopeWatchModel;
import com.fintech.potstest.R;

import java.util.List;

import soup.neumorphism.NeumorphFloatingActionButton;

public class ShopWatchAdpater extends RecyclerView.Adapter<ShopWatchAdpater.ViewHolder> {
    private final List<ShopeWatchModel> listdata;
    private final Activity activity;

    public ShopWatchAdpater(List<ShopeWatchModel> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.shop_watch_row, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final ShopeWatchModel myListData = listdata.get(position);
        holder.name.setText(listdata.get(position).getName());
        holder.price.setText("From ₹"+listdata.get(position).getPrice()+".00");
        holder.image.setImageResource(listdata.get(position).getImage());
        holder.image_background.setImageResource(listdata.get(position).getImage_Background());

        holder.card_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity, ShopDetailActivity.class);
                intent.putExtra("image",listdata.get(position).getImage());
                intent.putExtra("name",listdata.get(position).getName());
                intent.putExtra("price",listdata.get(position).getPrice());
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.right_enter,R.anim.left_out);
            }
        });
        holder.creat_goal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity, ShopCreateGoalActivity.class);
                intent.putExtra("image",listdata.get(position).getImage());
                intent.putExtra("name",listdata.get(position).getName());
                intent.putExtra("price",listdata.get(position).getPrice());
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.right_enter,R.anim.left_out);
            }
        });
        holder.buy_now_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertSuccessPopup();
            }
        });

    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name,price;
        ImageView image,image_background;
        RelativeLayout creat_goal_btn,buy_now_btn;
        CardView card_layout;
        public ViewHolder(View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.name);
            this.price = itemView.findViewById(R.id.price);

            this.creat_goal_btn = itemView.findViewById(R.id.creat_goal_btn);
            this.buy_now_btn = itemView.findViewById(R.id.buy_now_btn);
            this.image_background=itemView.findViewById(R.id.image_background);
            this.image=itemView.findViewById(R.id.image);
            this.card_layout=itemView.findViewById(R.id.card_layout);
        }

    }
    public void AlertSuccessPopup() {
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setContentView(R.layout.shop_cancel_popup);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        RelativeLayout btn = dialog.findViewById(R.id.btn);
        NeumorphFloatingActionButton btn_cancel = dialog.findViewById(R.id.btn_cancel);
        TextView sub_title = dialog.findViewById(R.id.sub_title);
        RelativeLayout rl_cancel = dialog.findViewById(R.id.layout);

        rl_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(activity, MainActivity.class);
//                activity.startActivity(intent);
//                activity.finishAffinity();
//                activity.overridePendingTransition(R.anim.right_enter, R.anim.left_out);
            }
        });
        dialog.show();
    }
}
