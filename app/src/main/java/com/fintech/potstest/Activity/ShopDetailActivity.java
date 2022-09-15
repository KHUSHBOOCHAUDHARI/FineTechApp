package com.fintech.potstest.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.fintech.potstest.R;
import com.fintech.potstest.Utils.Method;
import com.fintech.potstest.databinding.ActivityShopDetailBinding;

import soup.neumorphism.NeumorphFloatingActionButton;

public class ShopDetailActivity extends AppCompatActivity {
private ActivityShopDetailBinding binding;
    int number=1;
    int MinLimit=01;
    private Method method;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(ShopDetailActivity.this,R.layout.activity_shop_detail);
        method=new Method(ShopDetailActivity.this);
        method.Shopfullscreen();
        binding.productImage.setImageResource((Integer) getIntent().getExtras().get("image"));
        //Glide.with(ShopCreateGoalActivity.this).load(getIntent().getStringArrayExtra("image")).into(binding.productImage);
        binding.name.setText(getIntent().getStringExtra("name"));
        binding.price.setText("₹"+getIntent().getStringExtra("price"));
        binding.itemCount.setText("0"+String.valueOf(number));
        binding.backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.right_out, R.anim.left_enter);
            }
        });
        binding.readMor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShopDetailActivity.this, ProductDetailShowActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_enter,R.anim.left_out);
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
        binding.createNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShopDetailActivity.this, ShopCreateGoalActivity.class);
                intent.putExtra("image",(Integer) getIntent().getExtras().get("image"));
                intent.putExtra("name",binding.name.getText().toString());
                intent.putExtra("price",getIntent().getStringExtra("price"));
                startActivity(intent);
                overridePendingTransition(R.anim.right_enter,R.anim.left_out);
            }
        });
        binding.buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertSuccessPopup();
            }
        });
    }
    public void AlertSuccessPopup() {
        final Dialog dialog = new Dialog(ShopDetailActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
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