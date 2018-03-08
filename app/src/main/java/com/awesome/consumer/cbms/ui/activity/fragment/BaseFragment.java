package com.awesome.consumer.cbms.ui.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.awesome.consumer.cbms.R;

/**
 * Create: 11/01/18 , 上午9:40
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 11/01/18)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */
public class BaseFragment extends Fragment implements View.OnClickListener {
    protected View fragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_subordinate, container, false);
    }

    protected void setPageTitle(int resourceId) {
        TextView tvTitle = fragmentView.findViewById(R.id.tv_title);
        if (0 < resourceId && null != tvTitle) {
            tvTitle.setText(resourceId);
        }
    }

    protected void showBackButton(boolean flag) {
        View view = fragmentView.findViewById(R.id.btn_back);
        if (null != view) {
            if (flag) {
                view.setVisibility(View.VISIBLE);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        finish();
                    }
                });
            }else{
                view.setVisibility(View.GONE);
            }
        }
    }

    protected void setRightMenu(String displayString){
        View btnView = fragmentView.findViewById(R.id.btn_right);
        if (null != btnView) {
            btnView.setOnClickListener(this);
        }
        TextView tvView = fragmentView.findViewById(R.id.tv_right);
        if (null != tvView) {
            tvView.setText(displayString);
            tvView.setVisibility(View.VISIBLE);
            fragmentView.findViewById(R.id.img_right).setVisibility(View.GONE);
        }
    }

    protected void setRightMenu(int resourceId){
        setRightMenu(getResources().getString(resourceId));
    }

    protected void setRightImage(int resourceId){
        View btnView = fragmentView.findViewById(R.id.btn_right);
        if (null != btnView) {
            btnView.setOnClickListener(this);
        }
        ImageView imgView = fragmentView.findViewById(R.id.img_right);
        if (null != imgView) {
            imgView.setVisibility(View.VISIBLE);
            fragmentView.findViewById(R.id.tv_right).setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
