package com.richard.buttonview.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.richard.buttonview.ButtonView;

public class MainActivity extends AppCompatActivity {

    private ButtonView bv_confirm_pay;
    private ButtonView bv_confirm_upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bv_confirm_pay = findViewById(R.id.bv_confirm_pay);
        bv_confirm_upload = findViewById(R.id.bv_confirm_upload);

        bv_confirm_pay.setEnabled(false);
        bv_confirm_pay.showLoading("正在支付...");

        bv_confirm_upload.setOnClickListener((v) -> {
            bv_confirm_upload.setEnabled(false);
            bv_confirm_upload.showLoading("正在上传");

            v.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bv_confirm_upload.setEnabled(true);
                    bv_confirm_upload.hideLoading();
                    v.removeCallbacks(this);
                }
            }, 1000);
        });
    }

    public void clickButton(View view) {
        Toast.makeText(this, "点击了按钮", Toast.LENGTH_SHORT).show();
    }
}
