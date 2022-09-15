package com.fintech.potstest.Adapter;

import static com.fintech.potstest.Activity.ListOfStockActivity.desc;
import static com.fintech.potstest.Activity.ListOfStockActivity.potId;
import static com.fintech.potstest.Activity.ListOfStockActivity.potName;
import static com.fintech.potstest.Activity.ListOfStockActivity.spend_limit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fintech.potstest.Activity.StockBuyActivity;
import com.fintech.potstest.Activity.StockDetailActivity;
import com.fintech.potstest.Activity.StockSellActivity;
import com.fintech.potstest.Model.ListOfStockModel;
import com.fintech.potstest.R;
import com.fintech.potstest.Utils.Constant;
import com.fintech.potstest.Utils.Method;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;
import de.hdodenhof.circleimageview.CircleImageView;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphFloatingActionButton;

public class ShopListOfStockAdpater extends RecyclerView.Adapter<ShopListOfStockAdpater.ViewHolder> {
    private final List<ListOfStockModel> listdata;
    private final Activity activity;
    private Method method;
    public String stock_price,stockpriceId;
    public ShopListOfStockAdpater(List<ListOfStockModel> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.shop_listofstock_layout, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        method = new Method(activity);
        final ListOfStockModel myListData = listdata.get(position);
        if (Method.isNetworkAvailable(activity)) {
            GetPrice(myListData.getId(),holder);
        } else {
            method.alertBox(activity.getResources().getString(R.string.internet_connection));
        }
        holder.title.setText(listdata.get(position).getTitle());
//        holder.nsc_subtitle.setText(listdata.get(position).getNsc_subtitle());
//        holder.bsc_subtitle.setText(listdata.get(position).getBsc_subtitle());
        Glide.with(activity).load(listdata.get(position).getImage()).into(holder.imageView);
        holder.price.setText(listdata.get(position).getPrice());
        holder.discount_price.setText(listdata.get(position).getDicountprice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String image = String.valueOf(listdata.get(position).getImage());
                String title = listdata.get(position).getTitle();
                String stockId = listdata.get(position).getId();
                if (Method.isNetworkAvailable(activity)) {
                    GetPrice(stockId,holder);
                } else {
                    method.alertBox(activity.getResources().getString(R.string.internet_connection));
                }
                StocksSlideBottomDialog(stockId,image,title,potId,potName,desc);
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String image = String.valueOf(listdata.get(position).getImage());
                String title = listdata.get(position).getTitle();
                String stockId = listdata.get(position).getId();

                Intent intent=new Intent(activity, StockDetailActivity.class);
                intent.putExtra("stockId",stockId);
                intent.putExtra("stockpriceId",stockpriceId);
                intent.putExtra("logo",image);
                intent.putExtra("title",title);
                intent.putExtra("Send_type","FromPots");
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
        public TextView title, nsc_subtitle, bsc_subtitle, price, sub_price, discount_price;
        ImageView star, star_yellow;
        NeumorphCardView plushbutton;
        CircleImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.nsc_subtitle = itemView.findViewById(R.id.nst_subtitle);
            this.bsc_subtitle = itemView.findViewById(R.id.bts_subtitle);
            this.imageView = itemView.findViewById(R.id.logo);
            this.price = itemView.findViewById(R.id.price);
            this.sub_price = itemView.findViewById(R.id.sub_price);
            this.discount_price = itemView.findViewById(R.id.discount_price);
            this.star = itemView.findViewById(R.id.start);
            this.star_yellow = itemView.findViewById(R.id.start_yello);
            this.plushbutton = itemView.findViewById(R.id.plushbutton);
        }
    }

    public void GetPrice(String id, ViewHolder holder){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = Constant.BASEURL + activity.getString(R.string.get_stocks_prices_api)+id;
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
                String res = new String(responseBody);
                try {
                    JSONObject jsonObject=new JSONObject(res);
                    stockpriceId=jsonObject.getString("_id");
                    String stock=jsonObject.getString("stock");
                    stock_price=jsonObject.getString("price");
                    String createdAt=jsonObject.getString("createdAt");
                    String updatedAt=jsonObject.getString("updatedAt");
                    String __v=jsonObject.getString("__v");
                    holder.sub_price.setText(stock_price);
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

    private void StocksSlideBottomDialog(String StockId,String image,String title,String PotId,String PotName,String Potdesc) {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        bottomSheetDialog.setContentView(R.layout.stock_detail_botoom);

        NeumorphCardView buy_btn = bottomSheetDialog.findViewById(R.id.buy_btn);
        NeumorphCardView sell_btn = bottomSheetDialog.findViewById(R.id.sell_btn);
        ImageView img_mf =  bottomSheetDialog.findViewById(R.id.logo);
        TextView txt_title =  bottomSheetDialog.findViewById(R.id.name_txt);
        NeumorphFloatingActionButton logo_btn =  bottomSheetDialog.findViewById(R.id.logo_btn);
        TextView balance_txt =  bottomSheetDialog.findViewById(R.id.balance_txt);

        Glide.with(activity).load(image).into(img_mf);
        txt_title.setText(title);
        balance_txt.setText(stock_price);

        img_mf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity, StockDetailActivity.class);
                intent.putExtra("stockId",StockId);
                intent.putExtra("stockpriceId",stockpriceId);
                intent.putExtra("logo",image);
                intent.putExtra("title",title);
                intent.putExtra("Send_type","FromPots");
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.right_enter, R.anim.left_out);
            }
        });

        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                Intent intent=new Intent(activity, StockBuyActivity.class);
                intent.putExtra("stockId",StockId);
                intent.putExtra("stockpriceId",stockpriceId);
                intent.putExtra("type","Stock");
                intent.putExtra("image",image);
                intent.putExtra("title",title);
                intent.putExtra("Send_type","FromPots");
                intent.putExtra("send_potId",PotId);
                intent.putExtra("Send_potDesc",Potdesc);
                intent.putExtra("Send_PotName",PotName);
                intent.putExtra("Send_SpendLimit",spend_limit);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.right_enter, R.anim.left_out);
                activity.finish();


                Log.e("------------>","PotId : "+PotId);
                Log.e("------------>","PotName : "+PotName);
                Log.e("------------>","PotDesc : "+Potdesc);

            }
        });

        sell_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                Intent intent=new Intent(activity, StockSellActivity.class);
                intent.putExtra("stockId",StockId);
                intent.putExtra("stockpriceId",stockpriceId);
                intent.putExtra("type","Stock");
                intent.putExtra("image",image);
                intent.putExtra("title",title);
                intent.putExtra("Send_type","FromPots");
                intent.putExtra("send_potId",PotId);
                intent.putExtra("Send_potDesc",Potdesc);
                intent.putExtra("Send_PotName",PotName);
                intent.putExtra("Send_SpendLimit",spend_limit);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.right_enter, R.anim.left_out);
                activity.finish();

                Log.e("------------>","PotId : "+PotId);
                Log.e("------------>","PotName : "+PotName);
                Log.e("------------>","PotDesc : "+Potdesc);
            }
        });
        bottomSheetDialog.show();
    }
}
