package com.awesome.consumer.cbms.ui.activity.loginandregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.awesome.consumer.cbms.R;
import com.awesome.consumer.cbms.config.Config;
import com.awesome.consumer.cbms.ui.activity.BaseActivity;
import com.awesome.consumer.cbms.ui.activity.loginandregister.company.RegisterCompanyStep1Activity;
import com.awesome.consumer.cbms.ui.activity.loginandregister.company.RegisterCompanyStep2Activity;
import com.awesome.consumer.cbms.ui.activity.loginandregister.person.RegisterPersonStep1Activity;

/**
 * Create: 28/12/17 , 下午4:47
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 28/12/17)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：用户注册时，选择用户类型页面（机构用户／个人用户）
 * -----------------------------------------------------------------
 */
public class ChoiceAccountTypeActivity extends BaseActivity implements View.OnClickListener{
    private boolean isCompanyType = true;
    private RelativeLayout rlCompany = null, rlPersonal = null;
    private TextView tvCompany = null, tvPersonal = null;
    private ImageView imgCompany = null, imgPersonal = null;
    private Button btnNext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account_type);

        rlCompany = findViewById(R.id.rl_company);
        rlPersonal = findViewById(R.id.rl_personal);
        tvCompany = findViewById(R.id.tv_company_type);
        tvPersonal = findViewById(R.id.tv_person_type);
        imgCompany = findViewById(R.id.img_company_type);
        imgPersonal = findViewById(R.id.img_personal_type);
        btnNext = findViewById(R.id.btn_next);

        btnNext.setOnClickListener(this);
        rlCompany.setOnClickListener(this);
        rlPersonal.setOnClickListener(this);

        setPageTitle(R.string.choice_account_type);
        showBackButton(true);

        display();
    }

    private void display(){
        if (null == tvCompany || null == tvPersonal
                || null == imgCompany || null == imgPersonal){
            return;
        }
        if (isCompanyType) {
            tvCompany.setTextColor(getResources().getColor(R.color.blue));
            tvPersonal.setTextColor(getResources().getColor(R.color.black));
            imgCompany.setVisibility(View.VISIBLE);
            imgPersonal.setVisibility(View.GONE);
        }else{
            tvCompany.setTextColor(getResources().getColor(R.color.black));
            tvPersonal.setTextColor(getResources().getColor(R.color.blue));
            imgCompany.setVisibility(View.GONE);
            imgPersonal.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_company:
                if (!isCompanyType) {
                    isCompanyType = !isCompanyType;
                    display();
                }
                break;
            case R.id.rl_personal:
                if (isCompanyType) {
                    isCompanyType = !isCompanyType;
                    display();
                }
                break;
            case R.id.btn_next:
                Intent intent = new Intent(ChoiceAccountTypeActivity.this, RegisterCompanyStep1Activity.class);
                intent.putExtra(Config.CONSUMER_TYPE, isCompanyType);
                startActivity(intent);
                break;
        }
    }
}
