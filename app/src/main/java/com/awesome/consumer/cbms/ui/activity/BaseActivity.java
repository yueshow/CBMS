package com.awesome.consumer.cbms.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.awesome.consumer.cbms.R;

/**
 * Create: 28/12/17 , 下午4:47
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 28/12/17)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener {
    protected void setPageTitle(int resourceId) {
        TextView tvTitle = findViewById(R.id.tv_title);
        if (0 < resourceId && null != tvTitle) {
            tvTitle.setText(resourceId);
        }
    }

    protected void showBackButton(boolean flag) {
        View view = findViewById(R.id.btn_back);
        if (null != view) {
            if (flag) {
                view.setVisibility(View.VISIBLE);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }else{
                view.setVisibility(View.GONE);
            }
        }
    }

    protected void setRightMenu(String displayString){
        View btnView = findViewById(R.id.btn_right);
        if (null != btnView) {
            btnView.setOnClickListener(this);
        }
        TextView tvView = findViewById(R.id.tv_right);
        if (null != tvView) {
            tvView.setText(displayString);
            tvView.setVisibility(View.VISIBLE);
            findViewById(R.id.img_right).setVisibility(View.GONE);
        }
    }

    protected void setRightMenu(int resourceId){
        setRightMenu(getResources().getString(resourceId));
    }

    protected void setRightImage(int resourceId){
        View btnView = findViewById(R.id.btn_right);
        if (null != btnView) {
            btnView.setOnClickListener(this);
        }
        ImageView imgView = findViewById(R.id.img_right);
        if (null != imgView) {
            imgView.setVisibility(View.VISIBLE);
            findViewById(R.id.tv_right).setVisibility(View.GONE);
        }
    }

    protected void showToast(String showText){
        Toast.makeText(this, showText, Toast.LENGTH_LONG).show();
    }

    protected void showToast(int resourceId){
        Toast.makeText(this, resourceId, Toast.LENGTH_LONG).show();
    }
}
