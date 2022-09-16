package com.fintech.potstest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.fintech.potstest.R;
import com.fintech.potstest.Utils.Method;
import com.fintech.potstest.databinding.ActivityProductDetailShowBinding;

public class ProductDetailShowActivity extends AppCompatActivity {
    private ActivityProductDetailShowBinding binding;
    private Method method;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(ProductDetailShowActivity.this,R.layout.activity_product_detail_show);
        method = new Method(ProductDetailShowActivity.this);
        method.Shopfullscreen();
        binding.backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.right_out, R.anim.left_enter);
            }
        });
        binding.buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ProductActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_enter,R.anim.left_out);
            }
        });

    }
}