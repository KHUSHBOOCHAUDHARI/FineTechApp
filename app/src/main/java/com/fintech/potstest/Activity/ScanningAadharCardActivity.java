package com.fintech.potstest.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.fintech.potstest.R;
import com.fintech.potstest.Utils.Method;
import com.fintech.potstest.databinding.ActivityScanningAadharCardBinding;
import com.gaurav.kyccamera.camera.KYCCamera;

public class ScanningAadharCardActivity extends AppCompatActivity {
    private ActivityScanningAadharCardBinding binding;
    Method method;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(ScanningAadharCardActivity.this,R.layout.activity_scanning_aadhar_card);
        method=new Method(ScanningAadharCardActivity.this);
        method.fullscreen();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.right_out, R.anim.left_enter);
            }
        });
        binding.scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KYCCamera.create(ScanningAadharCardActivity.this).openCamera(KYCCamera.TYPE_AADHAARCARD_FRONT);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == KYCCamera.RESULT_CODE) {
            //Get image path，display image
            final String path = KYCCamera.getImagePath(data);
            if (!TextUtils.isEmpty(path)) {
                if (requestCode == KYCCamera.TYPE_AADHAARCARD_FRONT) { //Front of AADHAAR card
                    binding.image.setImageBitmap(BitmapFactory.decodeFile(path));
                }

            }
        }
    }
}