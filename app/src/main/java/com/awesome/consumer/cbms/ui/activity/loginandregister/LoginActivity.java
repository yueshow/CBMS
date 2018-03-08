package com.awesome.consumer.cbms.ui.activity.loginandregister;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.awesome.consumer.cbms.R;
import com.awesome.consumer.cbms.beans.test.Member;
import com.awesome.consumer.cbms.config.Config;
import com.awesome.consumer.cbms.config.URLs;
import com.awesome.consumer.cbms.okhttputil.OkHttpUtils;
import com.awesome.consumer.cbms.okhttputil.ResultBean;
import com.awesome.consumer.cbms.okhttputil.callback.StringCallback;
import com.awesome.consumer.cbms.ui.activity.MainActivity;
import com.awesome.consumer.cbms.utils.StringUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Create: 26/12/17 , 上午10:38
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 26/12/17)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：登录页面
 * -----------------------------------------------------------------
 */
public class LoginActivity extends Activity implements View.OnClickListener{
    private EditText etPassword = null, etAccount = null;
    private TextView tvLoginMessage = null;
    private Button btnLogin = null;
    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoginPreferences();
        if(getIntent().getBooleanExtra(Config.AUTO_LOGIN, false)){//暂时先不要自动登录
//        if(getIntent().getBooleanExtra(Config.AUTO_LOGIN, true)){//自动登录
            login(username, password);
        }else{
            setContentView(R.layout.activity_login);

            etAccount = findViewById(R.id.auto_et_account);
            etPassword = findViewById(R.id.et_password);
            tvLoginMessage = findViewById(R.id.tv_login_message);
            btnLogin = findViewById(R.id.btn_login);

            btnLogin.setOnClickListener(this);
            findViewById(R.id.tv_register).setOnClickListener(this);
            etAccount.setText(username);
            etPassword.setText(password);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                if(tvLoginMessage.isShown()){
                    tvLoginMessage.setVisibility(View.INVISIBLE);
                }
//                if (login()) {
//                    if(tvLoginMessage.isShown()){
//                        tvLoginMessage.setVisibility(View.INVISIBLE);
//                    }
////                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                }else{
//                    if(!tvLoginMessage.isShown()){
//                        tvLoginMessage.setVisibility(View.VISIBLE);
//                    }
//                    tvLoginMessage.setText(R.string.login_message1);
//                }
                String username = etAccount.getText().toString().trim();
                String password = etPassword.getText().toString().trim();


                if (checkParams(username, password)){
                    login(username, password);
                }else{
                    tvLoginMessage.setText(R.string.login_message1);
                    tvLoginMessage.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_register:
                if(tvLoginMessage.isShown()){
                    tvLoginMessage.setVisibility(View.INVISIBLE);
                }
                startActivity(new Intent(LoginActivity.this, ChoiceAccountTypeActivity.class));
                break;
        }
    }

    private boolean checkParams(String username, String password){
        return StringUtils.isSafeString(username) && StringUtils.isSafeString(password);
    }

    private void login(final String username, final String password){
        String url = URLs.getLoginUrl();
        OkHttpUtils
                .post()//
                .url(url)//
                .addParams("username", username)
                .addParams("password", password)
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(Config.TAG, "------------ Error ------------");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ResultBean bean = ResultBean.getResultBean(response);
                        if (0 == bean.code){
                            try {
                                JSONObject jsonObject = new JSONObject(bean.data.toString());
                                Config.globalUser.token = jsonObject.getString("_token");
                                String memberStr = jsonObject.getString("member");
                                String memberInfoStr = jsonObject.getString("member_info");
                                Gson gson = new Gson();
                                Config.globalUser.member = gson.fromJson(memberStr, Member.class);
                                Config.globalUser.memberInfo = gson.fromJson(memberInfoStr, Member.class);
                                saveLoginPreferences(username, password);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            tvLoginMessage.setText(bean.msg);
                            tvLoginMessage.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    private void saveLoginPreferences(String username, String password){
        SharedPreferences preferences = getSharedPreferences("login.xml", Context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
    }

    private void getLoginPreferences(){
        SharedPreferences preferences = getSharedPreferences("login.xml", Context.MODE_WORLD_READABLE);
        username = preferences.getString("username", null);
        password = preferences.getString("password", null);
    }
}

