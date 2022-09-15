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
import com.fintech.potstest.Model.ShopEarPhoneModel;
import com.fintech.potstest.R;

import java.util.List;

import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphFloatingActionButton;

public class ShopEarphoneAdpater extends RecyclerView.Adapter<ShopEarphoneAdpater.ViewHolder> {
    private final List<ShopEarPhoneModel> listdata;
    private final Activity activity;

    public ShopEarphoneAdpater(List<ShopEarPhoneModel> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.shop_earphone_row, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final ShopEarPhoneModel myListData = listdata.get(position);
        holder.discount_pre.setText(listdata.get(position).getDiscount());
        holder.discount_pre_title.setText(listdata.get(position).getDiscount_txt());
        holder.newdiscount_pre.setText(listdata.get(position).getNewDiscount());
        holder.new_discount_pre_title.setText(listdata.get(position).getNewDiscount_txt());
        holder.name.setText(listdata.get(position).getName());
        holder.price.setText("From ₹"+listdata.get(position).getPrice()+".00");
        holder.image.setImageResource(listdata.get(position).getImage());
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
        holder.careate_goal_card.setOnClickListener(new View.OnClickListener() {
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
        holder.buy_now_card.setOnClickListener(new View.OnClickListener() {
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

        public TextView discount_pre,discount_pre_title,newdiscount_pre,new_discount_pre_title,name,price;
        ImageView image;
        NeumorphCardView careate_goal_card,buy_now_card;
        CardView card_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.discount_pre = itemView.findViewById(R.id.discount_pre);
            this.discount_pre_title = itemView.findViewById(R.id.discount_pre_title);
            this.newdiscount_pre = itemView.findViewById(R.id.newdiscount_pre);
            this.new_discount_pre_title=itemView.findViewById(R.id.new_discount_pre_title);
            this.careate_goal_card = itemView.findViewById(R.id.careate_goal_card);
            this.buy_now_card=itemView.findViewById(R.id.buy_now_card);
            this.name=itemView.findViewById(R.id.name);
            this.price=itemView.findViewById(R.id.price);
            this.image = itemView.findViewById(R.id.image);
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
