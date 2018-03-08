package com.awesome.consumer.cbms.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.awesome.consumer.cbms.R;
import com.awesome.consumer.cbms.ui.activity.loginandregister.LoginActivity;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {
    private ArrayList<ImageView> images = new ArrayList<ImageView>();;
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;
    private int imageIds[] = {
            R.drawable.step1,
            R.drawable.step2,
            R.drawable.step3 };


    private View skip_btn;

    private ArrayList<View> dots;
    private int oldPosition = 0;// 记录上一次点的位置
    private int currentItem; // 当前页面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        skip_btn= findViewById(R.id.skip_btn);
        skip_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent instent3 = new Intent(GuideActivity.this, LoginActivity.class);
//                instent3.putExtra("login_type", (LoginType) getIntent().getSerializableExtra("login_type"));
                startActivity(instent3);
                finish();
            }
        });


        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);

            images.add(imageView);
        }


        dots = new ArrayList<View>();
        dots.add(findViewById(R.id.dot_0));
        dots.add(findViewById(R.id.dot_1));
        dots.add(findViewById(R.id.dot_2));
//        dots.add(findViewById(R.id.dot_3));
//        dots.add(findViewById(R.id.dot_4));

        initViewPager();

    }




    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter();
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                if(4==position){
                    skip_btn.setVisibility(View.VISIBLE);
                }else{
                    skip_btn.setVisibility(View.INVISIBLE);
                }


                dots.get(oldPosition).setBackgroundResource(
                        R.drawable.dot_normal);
                dots.get(position)
                        .setBackgroundResource(R.drawable.dot_focused);
                oldPosition = position;
                currentItem = position;
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

}