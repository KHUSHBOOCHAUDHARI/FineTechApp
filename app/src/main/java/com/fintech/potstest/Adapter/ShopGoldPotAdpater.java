package com.fintech.potstest.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fintech.potstest.Model.GoldPotModel;
import com.fintech.potstest.R;
import com.fintech.potstest.Utils.Constant;
import com.fintech.potstest.Utils.Method;
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

public class ShopGoldPotAdpater extends RecyclerView.Adapter<ShopGoldPotAdpater.ViewHolder> {
    private final List<GoldPotModel> listdata;
    private final Activity activity;
    private Method method;
    public ShopGoldPotAdpater(List<GoldPotModel> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.shop_onetime_gold_layout, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        method = new Method(activity);

        if (Method.isNetworkAvailable(activity)) {
            GoldBalance(holder);
        } else {
            method.alertBox(activity.getResources().getString(R.string.internet_connection));
        }
        final GoldPotModel myListData = listdata.get(position);
        holder.title.setText(listdata.get(position).getTitle());
        holder.subtitle.setText(listdata.get(position).getSubtitle());
        holder.imageView.setImageResource(Integer.parseInt(listdata.get(position).getImage()));
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title,subtitle,gold_price;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.subtitle = itemView.findViewById(R.id.subtitle);
            this.imageView = itemView.findViewById(R.id.logo);
            this.gold_price = itemView.findViewById(R.id.sub_price);

        }

    }

    public void GoldBalance(ViewHolder holder) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        String url = Constant.BASEURL + activity.getString(R.string.gold_price);
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
                    String _id=jsonObject.getString("_id");
                    String price=jsonObject.getString("price");
                    String createdAt=jsonObject.getString("createdAt");
                    String updatedAt=jsonObject.getString("updatedAt");
                    String __v=jsonObject.getString("__v");
                    holder.gold_price.setText(price);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                method.alertBox(activity.getResources().getString(R.string.something_went_wrong));
            }
        });
    }

}
