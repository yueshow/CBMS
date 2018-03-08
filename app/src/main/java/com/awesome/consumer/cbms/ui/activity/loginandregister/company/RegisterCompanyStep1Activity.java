package com.awesome.consumer.cbms.ui.activity.loginandregister.company;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.awesome.consumer.cbms.R;
import com.awesome.consumer.cbms.beans.JsonGenericsSerializator;
import com.awesome.consumer.cbms.beans.test.Member;
import com.awesome.consumer.cbms.config.Config;
import com.awesome.consumer.cbms.config.URLs;
import com.awesome.consumer.cbms.okhttputil.OkHttpUtils;
import com.awesome.consumer.cbms.okhttputil.ResultBean;
import com.awesome.consumer.cbms.okhttputil.callback.GenericsCallback;
import com.awesome.consumer.cbms.ui.activity.BaseActivity;
import com.awesome.consumer.cbms.ui.widget.BottomMenuDialog;
import com.awesome.consumer.cbms.utils.StringUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;

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
public class RegisterCompanyStep1Activity extends BaseActivity implements View.OnClickListener {
    private boolean isCompanyType;//注册类型，默认为机构注册（机构、个人）
    private String username, password, rePassword,
            reserve,//预留信息
            parentName,//上线姓名
            parentId;//上线户口号


    private int currentLanguageIndex = -1;
    private int[] languageArray = {R.string.language_zh, R.string.language_tw, R.string.language_en, R.string.language_km};
    private String[] languageArrayString = {"zh-cn", "zh_tw", "en="};
    private BottomMenuDialog bottomMenuDialog = null;
    private TextView tvLanguage = null;
    private EditText etAdminAccount = null;
    private EditText etLoginPassword = null;
    private EditText etReLoginPassword = null;
    private EditText etReserve = null;
    private EditText etParentAccount = null;
    private EditText etParentName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_company_step1);

        findViewById(R.id.btn_next).setOnClickListener(this);
        tvLanguage = findViewById(R.id.tv_language);
        tvLanguage.setOnClickListener(this);

        etAdminAccount = findViewById(R.id.et_admin_account);
        etLoginPassword = findViewById(R.id.et_login_password);
        etReLoginPassword = findViewById(R.id.et_login_re_password);
        etReserve = findViewById(R.id.et_reserve);
        etParentAccount = findViewById(R.id.et_parent_account);
        etParentName = findViewById(R.id.et_parent_name);

        setPageTitle(R.string.register);
        showBackButton(true);

        isCompanyType = getIntent().getBooleanExtra(Config.CONSUMER_TYPE, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //恢复语言
        currentLanguageIndex = savedInstanceState.getInt("currentLanguageIndex");
        if (0 < currentLanguageIndex) {
            sendUpdateLanguageMessage(currentLanguageIndex);
        }

        //恢复账号
        username = savedInstanceState.getString("username");
        if (null != username) {
            etAdminAccount.setText(username);
        }

        //恢复预留信息
        reserve = savedInstanceState.getString("reserve");
        if (null != reserve) {
            etReserve.setText(reserve);
        }

        //恢复上线户口号
        parentId = savedInstanceState.getString("parentId");
        if (null != parentId) {
            etReserve.setText(parentId);
        }

        //恢复上线姓名
        parentName = savedInstanceState.getString("parentName");
        if (null != parentName) {
            etParentName.setText(parentName);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //获取页面所有用户数据
//        getAllPageData();

        //保存当前选择的语言
        if (0 < currentLanguageIndex) {
            outState.putInt("currentLanguageIndex", currentLanguageIndex);
        }

        //保存账号
        username = etAdminAccount.getText().toString();
        if (null != username) {
            outState.putString("username", username);
        }

        //密码信息不保存

        //保存预留信息
        reserve = etReserve.getText().toString();
        if (null != reserve) {
            outState.putString("reserve", reserve);
        }

        //保存上线户口号
        parentId = etParentAccount.getText().toString();
        if (null != parentId) {
            outState.putString("parentId", parentId);
        }

        //保存上线姓名
        parentName = etParentName.getText().toString();
        if (null != parentName) {
            outState.putString("parentName", parentName);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                if (checkParams()) {
                    parentId = etParentAccount.getText().toString();
                    parentName = etParentName.getText().toString();
                    verifyRegister();
                }
                break;
            case R.id.tv_language:
                showBottomMenuDialog();
                break;
        }
    }

    /**
     * "选择语言"对话框
     */
    private void showBottomMenuDialog() {
        if (bottomMenuDialog == null) {
            bottomMenuDialog = new BottomMenuDialog.Builder(RegisterCompanyStep1Activity.this)
                    .setTitle(null)
                    .addMenu(R.string.language_zh, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomMenuDialog.dismiss();
                            sendUpdateLanguageMessage(0);
                        }
                    }).addMenu(R.string.language_tw, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomMenuDialog.dismiss();
                            sendUpdateLanguageMessage(1);
                        }
                    }).addMenu(R.string.language_en, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomMenuDialog.dismiss();
                            sendUpdateLanguageMessage(2);
                        }
                    }).addMenu(R.string.language_km, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomMenuDialog.dismiss();
                            sendUpdateLanguageMessage(3);
                        }
                    }).create();

            bottomMenuDialog.show();
        } else {
            bottomMenuDialog.show();
        }
    }


    /**
     * 官方语言更改时，发送更新信息
     *
     * @param index
     */
    private void sendUpdateLanguageMessage(int index) {
        if (0 <= index && languageArray.length > index) {
            Message msg = mHandler.obtainMessage();
            msg.what = UPDATE_LANGUAGE;
            msg.arg1 = index;
            mHandler.sendMessage(msg);

            currentLanguageIndex = index;
        }
    }

    private static final int UPDATE_LANGUAGE = 0x01;
    private Handler mHandler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_LANGUAGE:
                    if (0 <= msg.arg1 && languageArray.length > msg.arg1) {
                        tvLanguage.setText(languageArray[msg.arg1]);
                    }
                    break;
            }
        }
    };

    /**
     * 检查页面参数
     *
     * @return
     */
    private boolean checkParams() {
        if (checkLanguage()             //检查是否已选择语言
                && checkUsername()      //检查输入的账号
                && checkPassword()      //检查输入的密码
                && checkRePassword()      //检查确认密码
                && checkReserve()      //检查输入的预留信息
                ) {
            return true;
        }
        return false;
    }

    /**
     * 检查是否已选择语言，已选择返回 true
     *
     * @return
     */
    private boolean checkLanguage() {
        if (0 <= currentLanguageIndex && languageArray.length > currentLanguageIndex) {
            return true;
        }
        showToast(R.string.toast_select_language);
        return false;
    }

    /**
     * 检查是否已正确填写登录账号，已正确填写返回 true
     *
     * @return
     */
    private boolean checkUsername() {
        username = etAdminAccount.getText().toString();
        if (StringUtils.isSafeString(username)) {
            if (StringUtils.checkLengthSafe(username, StringUtils.LENGTH_30)) {
                return true;
            }
            showToast(R.string.toast_account_length);
        }else{
            showToast(R.string.toast_input_account);
        }
        return false;
    }

    /**
     * 检查登录密码，已正确填写返回 true
     *
     * @return
     */
    private boolean checkPassword() {
        password = etLoginPassword.getText().toString();
        if (!StringUtils.isSafeString(password)) {
            showToast(R.string.toast_input_password);
            return false;
        }
        if (8 > password.length() || 20 < password.length()) {
            showToast(R.string.toast_input_password_length);
            return false;
        }
        if (!checkPasswordRegex(password)) {
            showToast(R.string.toast_input_password_regex);
            return false;
        }
        return true;
    }

    /**
     * 检查登录密码是否符合服务器要求
     * @param password
     * @return
     */
    private boolean checkPasswordRegex(String password) {
        Pattern pLower = Pattern.compile("[a-z]+");
        Pattern pCapital = Pattern.compile("[A-Z]+");
        Pattern pNumber = Pattern.compile("[0-9]+");
        Matcher m = pLower.matcher(password);           //匹配小写字母
        if (m.find()
                && m.reset().usePattern(pCapital).find()//匹配大写字母
                && m.reset().usePattern(pNumber).find()){//匹配数字
            return true;
        }else{
            return false;
        }
    }

    /**
     * 检查登录密码，已正确填写返回 true
     *
     * @return
     */
    private boolean checkRePassword() {
        rePassword = etReLoginPassword.getText().toString();
        if (password.equals(rePassword)) {
            return true;
        }
        showToast(R.string.toast_re_password);
        return false;
    }

    /**
     * 检查预留信息，已正确填写返回 true
     *
     * @return
     */
    private boolean checkReserve() {
        reserve = etReserve.getText().toString();
        if (StringUtils.isSafeString(reserve)) {
            if (StringUtils.checkLengthSafe(reserve, StringUtils.LENGTH_30)) {
                return true;
            }
            showToast(R.string.toast_reserve_length);
        }else{
            showToast(R.string.toast_input_reserve);
        }
        return false;
    }

//    /**
//     *
//     * @param test  检查传入的test，长度是否在30以内
//     * @return 0 < test < 30 返回  true
//     */
////    private boolean checkLength30(String test){
////        if (null != test
//////                && 0 < test.trim().length()
////                && 30 >= test.trim().length()) {
////            return true;
////        }
////        return false;
////    }

    private void verifyRegister(){
        OkHttpUtils
                .post()//
                .url(URLs.getVerifyRegisterStep1Url())//
//                .addParams("os", Config.globalUser.token)
//                .addParams("_token", Config.globalUser.token)
                .addParams("type", isCompanyType ? "1" : "2")
                .addParams("lang", 0<=currentLanguageIndex&&currentLanguageIndex<languageArrayString.length?languageArrayString[currentLanguageIndex] : "zh-cn")
                .addParams("username", username)
                .addParams("password", password)
                .addParams("reserve_information", reserve)
                .addParams("parentcode", parentId)
                .addParams("parentname", parentName)
                .build()
                .execute(new GenericsCallback<ResultBean>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(Config.TAG, "----------------onError------------");
                    }

                    @Override
                    public void onResponse(ResultBean response, int id) {
                        if (Config.RESULT_OK == response.code){
                            saveRegisterInfo();
                            Intent intent = new Intent(RegisterCompanyStep1Activity.this, RegisterCompanyStep2Activity.class);
                            intent.putExtra(Config.CONSUMER_TYPE, isCompanyType);
                            startActivity(intent);
                        }else{
                            showToast(response.msg);
                        }
                    }
                });
    }

    private void saveRegisterInfo(){
        if (null == Config.globalUser.member){
            Config.globalUser.member = new Member();
        }
        Config.globalUser.member.memberType = isCompanyType ? 1 : 2;
        Config.globalUser.member.language = 0<=currentLanguageIndex&&currentLanguageIndex<languageArrayString.length?languageArrayString[currentLanguageIndex] : "zh-cn";
        Config.globalUser.member.username = username;
        Config.globalUser.member.reserve_information = reserve;
        Config.globalUser.member.parentcode = parentId;
        Config.globalUser.member.parentname = parentName;
    }
}
