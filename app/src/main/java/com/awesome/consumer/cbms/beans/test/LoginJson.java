package com.awesome.consumer.cbms.beans.test;

import com.awesome.consumer.cbms.okhttputil.ResultBean;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Create: 22/01/18 , 下午3:23
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 22/01/18)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */
public class LoginJson extends ResultBean {
    @SerializedName("data")
    public LoginJson.Login login;

    public static class Login {
        @SerializedName("_token")
        public String _token;

        @SerializedName("username")
        public String username;


        @SerializedName("view_permissions")
        public String view_permissions;


        @SerializedName("edit_permissions")
        public String edit_permissions;


        @SerializedName("is_set_pay_password")
        public String is_set_pay_password;

        @SerializedName("member")
        public Member member;

        @SerializedName("member_info")
        public Member memberInfo;

        public String toString(){
            return "Login : [_token : " + _token
                    + "\n, username : " + username
                    + "\n, view_permissions : " + view_permissions
                    + "\n, edit_permissions : " + edit_permissions
                    + "\n, is_set_pay_password : " + is_set_pay_password
                    + "\n"
                    + member.toString()
                    + "\n"
                    + memberInfo.toString()
                    + "\n]";
        }

        public void jsonToObject(String json){
            try {
                JSONObject jsonObject = new JSONObject(json);
                _token = jsonObject.getString("_token");
                username = jsonObject.getString("username");
                view_permissions = jsonObject.getString("view_permissions");
                edit_permissions = jsonObject.getString("edit_permissions");
                is_set_pay_password = jsonObject.getString("is_set_pay_password");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String toString(){

        return "[ code : " + code
                + ",\n  msg : " + msg
                + ",\ndata : " + login.toString()
                + ",\n]";
    }

    public void jsonToObject(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getInt("code");
            msg = jsonObject.getString("msg");
            String dataString = jsonObject.getString("data");
            login = new Login();
            login.jsonToObject(dataString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
