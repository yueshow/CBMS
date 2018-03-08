package com.awesome.consumer.cbms.ui.activity.loginandregister.company;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.awesome.consumer.cbms.R;
import com.awesome.consumer.cbms.config.Config;
import com.awesome.consumer.cbms.ui.activity.BaseActivity;
import com.awesome.consumer.cbms.ui.widget.BottomMenuDialog;

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
public class RegisterCompanyStep3Activity extends BaseActivity implements View.OnClickListener{
    private boolean isCompanyType = true;
    private int currentGenderIndex = 0;
    private int[] companyTypeIdArray = {R.string.man, R.string.woman};
    private BottomMenuDialog bottomMenuDialog = null;

    private EditText etContactName = null;
    private TextView tvNameLable = null;
    private TextView tvRegisterContactWays = null;
    private TextView tvRegisterGender = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_company_step3);

        etContactName = findViewById(R.id.et_contact_name);
        tvNameLable = findViewById(R.id.tv_contact_name);
        tvRegisterContactWays = findViewById(R.id.et_register_contact_ways);
        tvRegisterGender = findViewById(R.id.tv_register_gender);

        setPageTitle(R.string.register);
        showBackButton(true);

        tvRegisterContactWays.setOnClickListener(this);
        tvRegisterGender.setOnClickListener(this);

        isCompanyType = getIntent().getBooleanExtra(Config.CONSUMER_TYPE, true);
        if (isCompanyType) {
            tvNameLable.setText(R.string.register_contact);
        }else{
            tvNameLable.setText(R.string.name);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:

                break;

            case R.id.et_register_contact_ways:
                startActivity(new Intent(RegisterCompanyStep3Activity.this, InputSNSActivity.class));
                break;

            case R.id.tv_register_gender:
                showGenderialog();
                break;
        }
    }

    private void showGenderialog(){
        if (bottomMenuDialog == null) {
            bottomMenuDialog = new BottomMenuDialog.Builder(RegisterCompanyStep3Activity.this)
                    .setTitle(null)
                    .addMenu(R.string.man, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomMenuDialog.dismiss();
                            sendUpdateGenderMessage(0);
                        }
                    }).addMenu(R.string.woman, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomMenuDialog.dismiss();
                            sendUpdateGenderMessage(1);
                        }
                    }).create();

            bottomMenuDialog.show();
        }else{
            bottomMenuDialog.show();
        }
    }

    private void sendUpdateGenderMessage(int index){
        if (0 <= index && companyTypeIdArray.length > index) {
            currentGenderIndex = index;

            Message msg = mHandler.obtainMessage();
            msg.what = UPDATE_CERTIFICATES_TYPE;
            msg.arg1 = index;
            mHandler.sendMessage(msg);
        }
    }

    private static final int UPDATE_CERTIFICATES_TYPE = 0x01;
    private Handler mHandler = new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_CERTIFICATES_TYPE:
                    if (0 <= msg.arg1 && companyTypeIdArray.length > msg.arg1) {
                        tvRegisterGender.setText(companyTypeIdArray[msg.arg1]);
                    }
                    break;
            }
        }
    };
}
