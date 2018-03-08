package com.awesome.consumer.cbms.ui.activity.loginandregister;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
public class RegisterActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
/*
        autoAccount = (AutoCompleteTextView) findViewById(R.id.auto_et_account);
        etPassword = (EditText) findViewById(R.id.et_password);
        tvLoginMessage = (TextView) findViewById(R.id.tv_login_message);
        btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);
        findViewById(R.id.tv_register).setOnClickListener(this);
        */
    }

    @Override
    public void onClick(View v) {

    }
}
