package com.awesome.consumer.cbms.ui.activity.loginandregister.person;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.awesome.consumer.cbms.R;
import com.awesome.consumer.cbms.ui.activity.BaseActivity;

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
public class RegisterPersonStep1Activity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_person_step1);
/*
        autoAccount = (AutoCompleteTextView) findViewById(R.id.auto_et_account);
        etPassword = (EditText) findViewById(R.id.et_password);
        tvLoginMessage = (TextView) findViewById(R.id.tv_login_message);
        btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);
        findViewById(R.id.tv_register).setOnClickListener(this);
        */

        setPageTitle(R.string.register);
        showBackButton(true);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, RegisterPersonStep2Activity.class));
    }
}
