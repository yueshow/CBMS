package com.awesome.consumer.cbms;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.awesome.consumer.cbms.ui.activity.GuideActivity;
import com.awesome.consumer.cbms.ui.activity.loginandregister.LoginActivity;

public class Main2Activity extends AppCompatActivity {
    public static final String LOCAL_DB_FILE = "local_consumer_file";

    private final int GUIDE = 0x01;
    private final int LOGIN = 0x02;

    String user_account;
    String user_pwd;



    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch(msg.what){
                case GUIDE:
                    startActivity(new Intent(Main2Activity.this, GuideActivity.class));
                    finish();
                    break;

                case LOGIN:
                    Intent instent2 = new Intent(Main2Activity.this, LoginActivity.class);
                    instent2.putExtra("user_account", user_account);
                    instent2.putExtra("user_pwd", user_pwd);
                    startActivity(instent2);
                    finish();
                    break;

            }

        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences(LOCAL_DB_FILE, Activity.MODE_PRIVATE);
        user_account = sharedPreferences.getString("user_account", "");
        user_pwd = sharedPreferences.getString("user_pwd", "");

        Log.d("temp", "user_account" + user_account);
        Log.d("temp", "pwd" + user_pwd);

        if(null == user_account || user_pwd == null ||
                user_account.equals("") || user_pwd.equals("") ){
            mHandler.sendEmptyMessage(GUIDE);
        }else{
          /*
            Message msg = mHandler.obtainMessage();
            msg.what = LOGIN;
            msg.obj =
            */
            mHandler.sendEmptyMessage(LOGIN);
        }
    }
}
