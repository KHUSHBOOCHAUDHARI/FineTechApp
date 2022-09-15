package com.fintech.potstest.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fintech.potstest.Activity.MainActivity;
import com.fintech.potstest.Activity.MutualFundDetailActivity;
import com.fintech.potstest.Activity.OneTimeInvestmentActivity;
import com.fintech.potstest.Model.ListOfMfModel;
import com.fintech.potstest.R;
import com.fintech.potstest.Utils.Constant;
import com.fintech.potstest.Utils.Method;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;
import de.hdodenhof.circleimageview.CircleImageView;

public class ShopMutualFundAdpater extends RecyclerView.Adapter<ShopMutualFundAdpater.ViewHolder> {
    private List<ListOfMfModel> listdata;
    private Activity activity;
    Method method;
    public String mutualfundprice_id,nav_price;
    public ShopMutualFundAdpater(List<ListOfMfModel> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.shop_row_mutualfund, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        method=new Method(activity);
        final ListOfMfModel myListData = listdata.get(position);

        if (Method.isNetworkAvailable(activity)) {
            GetMutualFundPrice(myListData.getId(),holder);
        } else {
            method.alertBox(activity.getResources().getString(R.string.internet_connection));
        }

        holder.title.setText(listdata.get(position).getTitle());
        holder.txt_ratingcounts.setText(listdata.get(position).getRatingcount());
        holder.txt_years.setText(listdata.get(position).getMf_years()+"Y");
        Glide.with(activity).load(listdata.get(position).getImage()).into(holder.logo);
        holder.sub_title.setText(listdata.get(position).getSubtitle());

        if (MainActivity.IMAGE_SHOW_HIDE.equalsIgnoreCase("1"))
        {
            holder.pause_button.setVisibility(View.GONE);
            holder.outline_star.setVisibility(View.VISIBLE);
            holder.outline_star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.outline_star.setVisibility(View.GONE);
                    holder.fill_star.setVisibility(View.VISIBLE);

                }
            });
            holder.fill_star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.fill_star.setVisibility(View.GONE);
                    holder.outline_star.setVisibility(View.VISIBLE);
                }
            });
        }
        else
        {
            holder.pause_button.setVisibility(View.VISIBLE);
            holder.outline_star.setVisibility(View.GONE);
            holder.fill_star.setVisibility(View.GONE);
        }

        holder.pause_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListOfMfModel likerecord = listdata.get(position);
                if (!likerecord.getIsLike()) {
                    holder.MainLayout.setAlpha(0.3F);
                    holder.row_slide.setVisibility(View.GONE);
                } else {
                    holder.row_slide.setVisibility(View.VISIBLE);
                    holder.MainLayout.setAlpha(1F);
                }
                likerecord.setIsLike(!(likerecord.getIsLike()));
            }
        });

        holder.row_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String image = String.valueOf(listdata.get(position).getImage());
                String title = listdata.get(position).getTitle();

                Intent intent=new Intent(activity, MutualFundDetailActivity.class);
                intent.putExtra("logo",image);
                intent.putExtra("name",title);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.right_enter, R.anim.left_out);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title,price,sub_title,txt_years,txt_ratingcounts;
        ImageView outline_star,add_fav_yellow,fill_star,poused;
        RelativeLayout pause_button,MainLayout;
        RelativeLayout layout;
        CircleImageView logo;
        LinearLayout row_slide,row_main;
        public ViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.logo = (CircleImageView) itemView.findViewById(R.id.logo);
            this.sub_title = (TextView) itemView.findViewById(R.id.subtitle);
            this.txt_ratingcounts = (TextView) itemView.findViewById(R.id.txt_ratingcounts);
            this.txt_years = (TextView) itemView.findViewById(R.id.txt_years);
            this.pause_button=(RelativeLayout) itemView.findViewById(R.id.pause_button);
            this.fill_star=(ImageView) itemView.findViewById(R.id.fill_star);
            this.outline_star=(ImageView) itemView.findViewById(R.id.outline_star);
            this.pause_button=(RelativeLayout) itemView.findViewById(R.id.pause_button);
            this.MainLayout = itemView.findViewById(R.id.main_layout);
            this.row_slide = itemView.findViewById(R.id.rowBG);
            this.row_main = itemView.findViewById(R.id.rowFG);
        }
    }


    // Mutual Fund Slide Bottomup
    private void MutualFundSlideBottomDialog(String id,String image,String title) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        bottomSheetDialog.setContentView(R.layout.mf_botoom);
        RelativeLayout Monthly_Sip = bottomSheetDialog.findViewById(R.id.monthlysip_btn);
        RelativeLayout OneTime = bottomSheetDialog.findViewById(R.id.onetime_btn);
        ImageView img_mf =  bottomSheetDialog.findViewById(R.id.logo);
        TextView txt_title =  bottomSheetDialog.findViewById(R.id.name_txt);
        RelativeLayout logo_btn =  bottomSheetDialog.findViewById(R.id.logo_button);
        TextView price_txt =  bottomSheetDialog.findViewById(R.id.balance_txt);

        Glide.with(activity).load(image).into(img_mf);
        txt_title.setText(title);
        price_txt.setText(nav_price);

        logo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                Intent intent=new Intent(activity, MutualFundDetailActivity.class);
                intent.putExtra("logo",image);
                intent.putExtra("name",title);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.right_enter, R.anim.left_out);
            }
        });

        Monthly_Sip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                Intent intent=new Intent(activity, OneTimeInvestmentActivity.class);
                intent.putExtra("mutualfund_id",id);
                intent.putExtra("type","MonthlySIP");
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.right_enter, R.anim.left_out);
            }
        });

        OneTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                Intent intent=new Intent(activity, OneTimeInvestmentActivity.class);
                intent.putExtra("mutualfund_id",id);
                intent.putExtra("type","OneTime");
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.right_enter, R.anim.left_out);
            }
        });
        bottomSheetDialog.show();
    }

    public void GetMutualFundPrice(String id, ViewHolder holder){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        String url = Constant.BASEURL + activity.getString(R.string.get_mutualfund_prices_api)+id;
        Log.e("Url------->", url);
        String someData = "{}";
        StringEntity entity = null;
        try {
            entity = new StringEntity(someData);
            entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        } catch(Exception e) {

        }
        client.addHeader("authorization",method.pref.getString(method.Token, null));
        Log.e("Url------->", url.toString());
        client.get(activity,url, entity,"application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("Profile_Detail_Response", new String(responseBody));
                String res = new String(responseBody);
                Log.e("Url------->", res.toString());
                try {
                    JSONObject jsonObject=new JSONObject(res);
                    mutualfundprice_id=jsonObject.getString("_id");
                    String mutualfund=jsonObject.getString("mutualfund");
                    nav_price=jsonObject.getString("nav");
                    String createdAt=jsonObject.getString("createdAt");
                    String updatedAt=jsonObject.getString("updatedAt");
                    String __v=jsonObject.getString("__v");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }
}
