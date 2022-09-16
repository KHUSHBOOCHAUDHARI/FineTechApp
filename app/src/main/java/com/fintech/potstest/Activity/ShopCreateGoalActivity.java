package com.fintech.potstest.Activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.SeekBar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fintech.potstest.Adapter.ShopGoldPotAdpater;
import com.fintech.potstest.Adapter.ShopListOfStockAdpater;
import com.fintech.potstest.Adapter.ShopMutualFundAdpater;
import com.fintech.potstest.Adapter.ShopPortfolioGoalsSecondAdpater;
import com.fintech.potstest.Model.GoldPotModel;
import com.fintech.potstest.Model.ListOfMfModel;
import com.fintech.potstest.Model.ListOfStockModel;
import com.fintech.potstest.Model.PortfolioSecondGoalModel;
import com.fintech.potstest.R;
import com.fintech.potstest.Utils.Method;
import com.fintech.potstest.Utils.SwipeRecycleview;
import com.fintech.potstest.databinding.ActivityShopCreateGoalBinding;

import java.util.ArrayList;
import java.util.List;

public class ShopCreateGoalActivity extends AppCompatActivity {
    List<GoldPotModel> list = new ArrayList<>();
    ShopGoldPotAdpater adapter;
    List<ListOfMfModel> mflist;
    ShopMutualFundAdpater mfadapter;
    List<ListOfStockModel> stocklist;
    ShopListOfStockAdpater stockadapter;
    private List<PortfolioSecondGoalModel> portfolioGoalModels;
    ShopPortfolioGoalsSecondAdpater stock_adapter;
    private ActivityShopCreateGoalBinding binding;
    private LayoutAnimationController animation;

    private InputMethodManager imm;
    private Method method;
    int number=1;
    int MinLimit=01;
    private boolean is_GoldEnabled,is_MutualFundEnabled,is_StockEnabled;
    SwipeRecycleview touchListener;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(ShopCreateGoalActivity.this,R.layout.activity_shop_create_goal);
        method=new Method(ShopCreateGoalActivity.this);
        method.Shopfullscreen();
        mflist = new ArrayList<>();
        stocklist = new ArrayList<>();
        list = new ArrayList<>();
        list = getData();
        mflist=getMutualFundData();
        binding.productImage.setImageResource((Integer) getIntent().getExtras().get("image"));
        //Glide.with(ShopCreateGoalActivity.this).load(getIntent().getStringArrayExtra("image")).into(binding.productImage);
        binding.name.setText(getIntent().getStringExtra("name"));
        binding.price.setText("₹"+getIntent().getStringExtra("price"));
        int max= Integer.parseInt(getIntent().getStringExtra("price"));

        binding.dailySeekBar.setMax(max);
        binding.dailySeekBar.setMin(5000);
        binding.dailySeekBar.setProgress(5000,true);
        binding.monthlySeekBar.setMax(180);
        binding.monthlySeekBar.setMin(1);
        binding.monthCount.setText("1");

        binding.backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.right_out, R.anim.left_enter);
            }
        });
        binding.dailySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // Display the current progress of SeekBar
               if(i>=1) {
                   binding.dailyAmount.setText("" + i);

                   int FinalPrice = Integer.parseInt(getIntent().getStringExtra("price"));
                   int DailyPrice = Integer.parseInt(binding.dailyAmount.getText().toString());

                   int Month = FinalPrice / DailyPrice;
                   Log.e("payment", "" + Month);

               }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        binding.monthlySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // Display the current progress of SeekBar
                if(i<=30) {
                    if (i>=1) {
                        binding.monthCount.setText("" + (i) + " Days");
                    }

                }
                else
                {
                    binding.monthCount.setText("" + (i/30)+" Month");
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        binding.plushLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number++;
                binding.itemCount.setText(String.valueOf(number));
                String i= String.valueOf(number);
                if (i.length()<=1) {
                    binding.itemCount.setText("0" + String.valueOf(number));
                }
                else
                {
                    binding.itemCount.setText(String.valueOf(number));
                }
            }
        });
        binding.minushLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (number == MinLimit) {
                    binding.itemCount.setText("0"+String.valueOf(number));

                } else {
                    number--;

                    String i = String.valueOf(number);
                    if (i.length() <= 1) {
                        binding.itemCount.setText("0" + String.valueOf(number));
                    } else {
                        binding.itemCount.setText(String.valueOf(number));
                    }
                }


            }
        });

        adapter = new ShopGoldPotAdpater(list, ShopCreateGoalActivity.this);
        binding.goldRecyclerview.setAdapter(adapter);
        binding.goldRecyclerview.setLayoutManager(new LinearLayoutManager(ShopCreateGoalActivity.this));
        binding.goldRecyclerview.setLayoutAnimation(animation);

        mfadapter = new ShopMutualFundAdpater(mflist, ShopCreateGoalActivity.this);
        binding.mfRecyclerview.setAdapter(mfadapter);
        binding.mfRecyclerview.setLayoutManager(new LinearLayoutManager(ShopCreateGoalActivity.this));
        binding.mfRecyclerview.setLayoutAnimation(animation);


    }
    private List<GoldPotModel> getData() {
        List<GoldPotModel> list = new ArrayList<>();
        list.add(new GoldPotModel("1", String.valueOf(R.drawable.gold), "24k Digital Gold", "100% secure . 99.9% pure gold"));
        return list;
    }
    private List<ListOfMfModel> getMutualFundData() {
        List<ListOfMfModel> mflist = new ArrayList<>();
        mflist.add(new ListOfMfModel( String.valueOf(R.drawable.axis),"","Axis Bluechip","Hybrid","","","3Y","CAGR:6.25%","4"));
        return mflist;
    }


}