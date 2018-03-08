package com.awesome.consumer.cbms.ui.activity.loginandregister;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.awesome.consumer.cbms.R;
import com.awesome.consumer.cbms.config.Config;

public class PreviewActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageView;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        imageView = findViewById(R.id.imageview);
        tvTitle = findViewById(R.id.tv_title);
        findViewById(R.id.btn_back).setOnClickListener(this);
        findViewById(R.id.btn_right).setOnClickListener(this);
        initView();
    }

    private void initView(){
        int photoIndex = getIntent().getIntExtra(Config.PHOTO_INDEX, -1);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_back:
                finish();
                break;

            case R.id.btn_right:
                break;
        }
    }
}
