package com.fintech.potstest.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fintech.potstest.Model.PortfolioSecondGoalModel;
import com.fintech.potstest.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import soup.neumorphism.NeumorphCardView;

public class ShopPortfolioGoalsSecondAdpater extends RecyclerView.Adapter<ShopPortfolioGoalsSecondAdpater.ViewHolder> {
    private final List<PortfolioSecondGoalModel> listdata;
    private final Activity activity;

    public ShopPortfolioGoalsSecondAdpater(List<PortfolioSecondGoalModel> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.portfolio_goal_second_layout, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final PortfolioSecondGoalModel myListData = listdata.get(position);
        holder.title.setText(listdata.get(position).getTitle());
        holder.investrdvalue.setText("₹"+listdata.get(position).getInvested_value());

        Glide.with(activity).load(myListData.getImage()).into(holder.imageView);

        holder.currentvalue.setText("₹"+listdata.get(position).getCurrent_value());
        holder.profitvalur.setText(listdata.get(position).getProfit_value());
        holder.discount_price.setText(listdata.get(position).getDiscount_value()+"%");
        holder.newdiscount_price.setText("("+listdata.get(position).getNew_discount_value()+"%)");

    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title,investrdvalue,currentvalue,profitvalur,discount_price,newdiscount_price;
        RelativeLayout layout;
        NeumorphCardView card_Layout;
        CircleImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.investrdvalue = itemView.findViewById(R.id.invested_value_txt);
            this.imageView = itemView.findViewById(R.id.logo);
            this.currentvalue = itemView.findViewById(R.id.current_value_txt);
            this.profitvalur = itemView.findViewById(R.id.ltp_value_txt);
            this.card_Layout= itemView.findViewById(R.id.card_Layout);
            this.discount_price= itemView.findViewById(R.id.discount_price);
            this.newdiscount_price= itemView.findViewById(R.id.newdiscount_price);
        }

    }

    public String date_formatnew(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date varDate = dateFormat.parse(strDate);
//            dateFormat=new SimpleDateFormat("dd MMM EEE yyyy");
            dateFormat = new SimpleDateFormat("dd MMM yyyy");
            System.out.println("Date :" + dateFormat.format(varDate));
            return dateFormat.format(varDate);
        } catch (Exception e) {
            e.printStackTrace();
            return strDate;
        }


    }

}
