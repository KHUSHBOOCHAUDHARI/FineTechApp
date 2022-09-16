package com.fintech.potstest.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fintech.potstest.R;
import com.fintech.potstest.Utils.Method;

import java.util.List;

public class JackpotAdapter extends RecyclerView.Adapter<JackpotAdapter.ViewHolder> {
    private List<PodcastModel> listdata;
    private Activity activity;
    Method method;

    public JackpotAdapter(List<PodcastModel> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.jackpot_raw, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        method=new Method(activity);
        holder.image_log.setImageResource(listdata.get(position).getLogo());
        holder.image_log_back.setBackgroundTintList(ColorStateList.valueOf(listdata.get(position).getColorcode()));
        holder.discount_image_tag.setImageResource(listdata.get(position).getDiscount());
        holder.image_product.setImageResource(listdata.get(position).getProduct());
        holder.image_background.setImageResource(listdata.get(position).getBackground());

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        ImageView image_log,discount_image_tag,image_product,image_background;
        RelativeLayout image_log_back;
        CardView card_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.card_layout = (CardView) itemView.findViewById(R.id.card_layout);
            this.image_log_back = (RelativeLayout) itemView.findViewById(R.id.image_log_back);
            this.image_log = (ImageView) itemView.findViewById(R.id.image_log);
            this.discount_image_tag = (ImageView) itemView.findViewById(R.id.discount_image_tag);
            this.image_product = (ImageView) itemView.findViewById(R.id.image_product);
            image_background=(ImageView) itemView.findViewById(R.id.image_background);
        }

    }

}
