package com.awesome.consumer.cbms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.awesome.consumer.cbms.R;
import com.awesome.consumer.cbms.ui.activity.loginandregister.LoginActivity;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {
    private ArrayList<ImageView> images = new ArrayList<>();
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;
    private int imageIds[] = {
            R.drawable.step1,
            R.drawable.step2,
            R.drawable.step3,
            R.drawable.step4 };


    private View skip_btn;

    private ArrayList<View> dots;
    private int oldPosition = 0;// 记录上一次点的位置

    private View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View arg0) {
            startApp();
        }
    };

    private void startApp(){
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getLoginPreferences()){
            startApp();
        }else{
            setContentView(R.layout.activity_wellcome);

            skip_btn = findViewById(R.id.btn_skip);
            skip_btn.setOnClickListener(listener);
            findViewById(R.id.btn_experience).setOnClickListener(listener);

            for (int i :imageIds) {
                ImageView imageView = new ImageView(this);
                imageView.setBackgroundResource(i);
                images.add(imageView);
            }

            dots = new ArrayList();
            dots.add(findViewById(R.id.dot_0));
            dots.add(findViewById(R.id.dot_1));
            dots.add(findViewById(R.id.dot_2));
            dots.add(findViewById(R.id.dot_3));

            initViewPager();
        }
    }

    private void initViewPager() {
        mViewPager = findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter();
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if(imageIds.length-1 == position){
//                    skip_btn.setVisibility(View.VISIBLE);
                }else{
                    skip_btn.setVisibility(View.INVISIBLE);
                }

                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                oldPosition = position;

                skip_btn.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            view.addView(images.get(position));
            return images.get(position);
        }
    }

    private boolean getLoginPreferences(){
        boolean flag = false;
        SharedPreferences preferences = getSharedPreferences("login.xml", Context.MODE_WORLD_READABLE);
        String username = preferences.getString("username", null);
        if (null != username){
            flag = true;
        }
        return flag;
    }
}
