package com.fintech.potstest.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.fintech.potstest.Adapter.ProductWiseAdpater;
import com.fintech.potstest.Model.ProductWiseModel;
import com.fintech.potstest.R;
import com.fintech.potstest.Utils.Method;
import com.fintech.potstest.databinding.ActivityProductBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    private ActivityProductBinding binding;
    List<ProductWiseModel> productWiseModels;
    ProductWiseAdpater productWiseAdpater;
    private LayoutAnimationController animation;
    private Method method;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(ProductActivity.this,R.layout.activity_product);
        method = new Method(ProductActivity.this);
        method.Shopfullscreen();
        productWiseModels = new ArrayList<>();
        productWiseModels=getProdect();
        int resId = R.anim.layout_animation_fall_down;
        animation = AnimationUtils.loadLayoutAnimation(ProductActivity.this, resId);
        binding.backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.right_out, R.anim.left_enter);
            }
        });
        binding.recy.setHasFixedSize(false);
        productWiseAdpater = new ProductWiseAdpater(productWiseModels, ProductActivity.this);
        binding.recy.setAdapter(productWiseAdpater);
        StaggeredGridLayoutManager trendlayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        binding.recy.setLayoutManager(trendlayoutManager);
        binding.recy.setLayoutAnimation(animation);
    }

    private List<ProductWiseModel> getProdect()
    {
        List<ProductWiseModel> list = new ArrayList<>();
        list.add(new ProductWiseModel(R.drawable.watchone,"Apple Watch","Series 6.Red","350"));
        list.add(new ProductWiseModel(R.drawable.watchone,"Apple Watch","Series 6.Red","350"));
        list.add(new ProductWiseModel(R.drawable.watchone,"Apple Watch","Series 6.Red","350"));
        list.add(new ProductWiseModel(R.drawable.watchone,"Apple Watch","Series 6.Red","350"));
        list.add(new ProductWiseModel(R.drawable.watchone,"Apple Watch","Series 6.Red","350"));
        list.add(new ProductWiseModel(R.drawable.watchone,"Apple Watch","Series 6.Red","350"));
        list.add(new ProductWiseModel(R.drawable.watchone,"Apple Watch","Series 6.Red","350"));
        list.add(new ProductWiseModel(R.drawable.watchone,"Apple Watch","Series 6.Red","350"));
        list.add(new ProductWiseModel(R.drawable.watchone,"Apple Watch","Series 6.Red","350"));
        list.add(new ProductWiseModel(R.drawable.watchone,"Apple Watch","Series 6.Red","350"));
        list.add(new ProductWiseModel(R.drawable.watchone,"Apple Watch","Series 6.Red","350"));
        list.add(new ProductWiseModel(R.drawable.watchone,"Apple Watch","Series 6.Red","350"));
        list.add(new ProductWiseModel(R.drawable.watchone,"Apple Watch","Series 6.Red","350"));
        return list;
    }
}