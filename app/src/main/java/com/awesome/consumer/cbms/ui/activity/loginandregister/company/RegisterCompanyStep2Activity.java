package com.awesome.consumer.cbms.ui.activity.loginandregister.company;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awesome.consumer.cbms.R;
import com.awesome.consumer.cbms.beans.JsonGenericsSerializator;
import com.awesome.consumer.cbms.beans.test.Member;
import com.awesome.consumer.cbms.config.Config;
import com.awesome.consumer.cbms.config.URLs;
import com.awesome.consumer.cbms.okhttputil.OkHttpUtils;
import com.awesome.consumer.cbms.okhttputil.ResultBean;
import com.awesome.consumer.cbms.okhttputil.builder.PostFormBuilder;
import com.awesome.consumer.cbms.okhttputil.callback.GenericsCallback;
import com.awesome.consumer.cbms.ui.activity.BaseActivity;
import com.awesome.consumer.cbms.ui.activity.loginandregister.AddressActivity;
import com.awesome.consumer.cbms.ui.activity.loginandregister.PreviewActivity;
import com.awesome.consumer.cbms.ui.widget.BottomMenuDialog;
import com.awesome.consumer.cbms.utils.PhotoUtils;
import com.awesome.consumer.cbms.utils.StringUtils;
import com.awesome.consumer.cbms.utils.ToastUtils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import okhttp3.Call;

/**
 * Create: 28/12/17 , 下午4:47
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 28/12/17)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：注册第二步骤，提交名称、证件类型、证件名称、证件号、地址、证件图片（证件图片模式）、证明人信息（证明人模式）
 * -----------------------------------------------------------------
 */
public class RegisterCompanyStep2Activity extends BaseActivity implements View.OnClickListener {
    private boolean isCompanyType = true; //注册类型，默认为机构注册（机构、个人）
    private boolean isWitness = false; //是否是证明人模式
    private int currentComapnyTypeIndex = 0;
    private int currentCertificatesTypeIndex = -1;
    private int currentPresonCertificatesTypeIndex = -1;
    private int[] companyTypeIdArray = {R.string.register_company_type_faren, R.string.register_company_type_team, R.string.register_company_type_other};//机构类型
    private int[] certificatesTypeIdArray = {R.string.register_certificates_type_photo, R.string.other};//机构证件类型
    private int[] personCertificatesTypeIdArray = {R.string.person_idcard, R.string.person_passport, R.string.other};//个人证件类型

    private ImageView[] imageViews = new ImageView[5];
    private int[] imageViewsId = {R.id.img1, R.id.img2, R.id.img3, R.id.img4, R.id.img5};
    private int photoIndex = 0;

    private LinearLayout llCertificatesPhoto = null;//上传证件图片布局
    private LinearLayout llCertificatesName = null;//填写证件名称布局
    private LinearLayout llCertificatesCode = null;//填写证件号布局
    private LinearLayout llWitness = null;//填写证明人布局
    private LinearLayout llCompanyCertificatesTitle = null;//填写机构类型的：机构名称、机构类型的布局。个人注册不需要时隐藏

    private TextView tvCompanyTypeSelect = null;//机构类型
    private TextView tvCertificatesTypeSelect = null;//证件类型
    private EditText etCompanyName = null;//机构名称
    private EditText etCertificatesName = null;//
    private EditText etCertificatesCode = null;//
    private EditText etWitness1Name = null;//
    private EditText etWitness1Account = null;//
    private EditText etWitness2Name = null;//
    private EditText etWitness2Account = null;//
    private TextView tvAddress = null;//地址
    private TextView tvAddressLabel = null;//地址标签

    private BottomMenuDialog companyTypeDialog = null;
    private BottomMenuDialog companyCertificatesTypeDialog = null;
    private BottomMenuDialog photoDialog = null;

//    private BottomMenuDialog personTypeDialog = null;
    private BottomMenuDialog personCertificatesTypeDialog = null;

//    private int type,
//            certificatesType;
    private String name,
            certificatesName,
            certificatesCode,
            witness1Name,
            witness2Name,
            witness1Code,
            witness2Code,
            address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_company_step2);

        setPageTitle(R.string.register);
        showBackButton(true);

        isCompanyType = getIntent().getBooleanExtra(Config.CONSUMER_TYPE, true);
        initView();

        llCertificatesName.setVisibility(View.GONE);
        if (isCompanyType) {
            llCompanyCertificatesTitle.setVisibility(View.VISIBLE);
            tvAddressLabel.setText(R.string.register_company_address);
        } else {
            llCompanyCertificatesTitle.setVisibility(View.GONE);
            llCertificatesPhoto.setVisibility(View.VISIBLE);
            tvAddressLabel.setText(R.string.register_address);
        }
        Config.globalUser.member = new Member();
    }

    private void initView() {
        llCertificatesPhoto = findViewById(R.id.ll_certificates_photo);
        llCertificatesName = findViewById(R.id.ll_certificates_name);
        llCertificatesCode = findViewById(R.id.ll_certificates_code);
        llWitness = findViewById(R.id.ll_witness);
        llCompanyCertificatesTitle = findViewById(R.id.ll_company_certificates_title);

        tvCompanyTypeSelect = findViewById(R.id.tv_company_type_select);
        tvCertificatesTypeSelect = findViewById(R.id.tv_certificates_type_select);
        for (int i = 0; i < imageViewsId.length; ++i) {
            imageViews[i] = findViewById(imageViewsId[i]);
            imageViews[i].setTag(i);
            imageViews[i].setOnClickListener(this);
        }

        etCompanyName = findViewById(R.id.et_company_name);
        etCertificatesName = findViewById(R.id.et_certificates_name);
        etCertificatesCode = findViewById(R.id.et_certificates_code);
        etWitness1Name = findViewById(R.id.et_witness1_name);
        etWitness1Account = findViewById(R.id.et_witness1_account);
        etWitness2Name = findViewById(R.id.et_witness2_name);
        etWitness2Account = findViewById(R.id.et_witness2_account);
        tvAddress = findViewById(R.id.et_address);
        tvAddressLabel = findViewById(R.id.tv_address);


        tvCompanyTypeSelect.setOnClickListener(this);
        tvCertificatesTypeSelect.setOnClickListener(this);
        findViewById(R.id.btn_next).setOnClickListener(this);
        tvAddress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                if (checkParams()) {//验证参数
                    addParams();    //加入列表
                    verifyRegister();//服务端验证
                }
                break;
            case R.id.tv_company_type_select://机构类型
                showCompanyTypeDialog();
                break;

            case R.id.tv_certificates_type_select://证件类型
                if (isCompanyType) {
                    showCertificatesTypeDialog();
                } else {
                    showPersonCertificatesTypeDialog();
                }

                break;

            case R.id.et_address://地址
                Intent addressIntent = new Intent(this, AddressActivity.class);
                addressIntent.putExtra(Config.ADDRESS, Config.globalUser.member.address);
                startActivityForResult(addressIntent, CODE_RESULT_ADDRESS);
                break;

//            case R.id.btn_back://返回
//                Log.i(Config.TAG, "inner class back onckick------------------------------>");
//                Config.globalUser.member.destoryCertifyPhotoFileList();
//                break;

            case R.id.img1:
            case R.id.img2:
            case R.id.img3:
            case R.id.img4:
            case R.id.img5:
                int tag = -1;
                try {
                    tag = Integer.valueOf(v.getTag().toString());
                } catch (NumberFormatException e) {
                }
                if (0 <= tag && tag < 5){
                    if (tag < photoIndex){
                        Intent intent = new Intent(this, PreviewActivity.class);
                        intent.putExtra(Config.PHOTO_INDEX, photoIndex);
                        startActivity(intent);
                    }else{
                        showPhotoDialog();
                    }
                }
                break;
        }
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//    }

    private void showCompanyTypeDialog() {
        if (companyTypeDialog == null) {
            companyTypeDialog = new BottomMenuDialog.Builder(RegisterCompanyStep2Activity.this)
                    .setTitle(null)
                    .addMenu(R.string.register_company_type_faren, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            companyTypeDialog.dismiss();
                            sendUpdateCompanyTypeMessage(0);
                        }
                    }).addMenu(R.string.register_company_type_team, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            companyTypeDialog.dismiss();
                            sendUpdateCompanyTypeMessage(1);
                        }
                    }).addMenu(R.string.register_company_type_other, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            companyTypeDialog.dismiss();
                            sendUpdateCompanyTypeMessage(2);
                        }
                    }).create();

            companyTypeDialog.show();
        } else {
            companyTypeDialog.show();
        }
    }

    private void showCertificatesTypeDialog() {
        if (companyCertificatesTypeDialog == null) {
            companyCertificatesTypeDialog = new BottomMenuDialog.Builder(RegisterCompanyStep2Activity.this)
                    .setTitle(null)
                    .addMenu(R.string.register_certificates_type_photo, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            companyCertificatesTypeDialog.dismiss();
                            sendUpdateCertificatesTypeMessage(0);
                        }
                    }).addMenu(R.string.other, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            companyCertificatesTypeDialog.dismiss();
                            sendUpdateCertificatesTypeMessage(1);
                        }
                    }).create();

            companyCertificatesTypeDialog.show();
        } else {
            companyCertificatesTypeDialog.show();
        }
    }

    private void showPhotoDialog() {
        if (photoDialog == null) {
            photoDialog = new BottomMenuDialog.Builder(this)
                    .setTitle(null)
                    .addMenu(R.string.register_photo_camera, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            photoDialog.dismiss();
                            autoObtainCameraPermission();
                        }
                    }).addMenu(R.string.register_photo_album, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            photoDialog.dismiss();
                            autoObtainStoragePermission();
                        }
                    }).create();

            photoDialog.show();
        } else {
            photoDialog.show();
        }
    }

    private void showPersonCertificatesTypeDialog() {
        if (personCertificatesTypeDialog == null) {
            personCertificatesTypeDialog = new BottomMenuDialog.Builder(RegisterCompanyStep2Activity.this)
                    .setTitle(null)
                    .addMenu(R.string.person_idcard, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            personCertificatesTypeDialog.dismiss();
                            sendUpdatePersonCertificatesTypeMessage(0);
                        }
                    }).addMenu(R.string.person_passport, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            personCertificatesTypeDialog.dismiss();
                            sendUpdatePersonCertificatesTypeMessage(1);
                        }
                    }).addMenu(R.string.other, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            personCertificatesTypeDialog.dismiss();
                            sendUpdatePersonCertificatesTypeMessage(2);
                        }
                    }).create();

            personCertificatesTypeDialog.show();
        } else {
            personCertificatesTypeDialog.show();
        }
    }

    private void sendUpdateCompanyTypeMessage(int index) {
        if (0 <= index && companyTypeIdArray.length > index) {
            currentComapnyTypeIndex = index;

            Message msg = mHandler.obtainMessage();
            msg.what = UPDATE_COMPANAY_TYPE;
            msg.arg1 = index;
            mHandler.sendMessage(msg);
        }
    }

    private void sendUpdateCertificatesTypeMessage(int index) {
        if (0 <= index && certificatesTypeIdArray.length > index) {
            currentCertificatesTypeIndex = index;

            Message msg = mHandler.obtainMessage();
            msg.what = UPDATE_CERTIFICATES_TYPE;
            msg.arg1 = index;
            mHandler.sendMessage(msg);
        }
    }

    private void sendUpdatePersonCertificatesTypeMessage(int index) {
        if (0 <= index && personCertificatesTypeIdArray.length > index) {
            currentCertificatesTypeIndex = index;

            Message msg = mHandler.obtainMessage();
            msg.what = UPDATE_PERSON_CERTIFICATES_TYPE;
            msg.arg1 = index;
            mHandler.sendMessage(msg);
        }
    }

    private static final int UPDATE_COMPANAY_TYPE = 0x01;
    private static final int UPDATE_CERTIFICATES_TYPE = 0x02;
    private static final int UPDATE_PERSON_CERTIFICATES_TYPE = 0x03;
    private static final int UPDATE_ADDRESS = 0x04;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_COMPANAY_TYPE:
                    if (0 <= msg.arg1 && companyTypeIdArray.length > msg.arg1) {
                        tvCompanyTypeSelect.setText(companyTypeIdArray[msg.arg1]);
                    }
                    break;
                case UPDATE_CERTIFICATES_TYPE:
                    if (0 <= msg.arg1 && certificatesTypeIdArray.length > msg.arg1) {
                        tvCertificatesTypeSelect.setText(certificatesTypeIdArray[msg.arg1]);
                        if (1 == currentCertificatesTypeIndex) {
                            isWitness = true;
                            llWitness.setVisibility(View.VISIBLE);
                            llCertificatesName.setVisibility(View.VISIBLE);
                            llCertificatesPhoto.setVisibility(View.GONE);
                        } else {
                            isWitness = false;
                            llWitness.setVisibility(View.GONE);
                            llCertificatesName.setVisibility(View.GONE);
                            llCertificatesPhoto.setVisibility(View.VISIBLE);
                        }
                    }
                    break;
                case UPDATE_PERSON_CERTIFICATES_TYPE:
                    if (0 <= msg.arg1 && personCertificatesTypeIdArray.length > msg.arg1) {
                        tvCertificatesTypeSelect.setText(personCertificatesTypeIdArray[msg.arg1]);
                        if (2 == currentCertificatesTypeIndex) {
                            isWitness = true;
                            llWitness.setVisibility(View.VISIBLE);
                            llCertificatesName.setVisibility(View.VISIBLE);
                            llCertificatesPhoto.setVisibility(View.GONE);
                        } else {
                            isWitness = false;
                            llWitness.setVisibility(View.GONE);
                            llCertificatesName.setVisibility(View.GONE);
                            llCertificatesPhoto.setVisibility(View.VISIBLE);
                        }
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
        if (checkName()                     //名称
                && checkCertificatesType()  //证件类型
                && checkCertificatesName() //证件名称
                && checkCertificatesCode() //证件号
                && checkPicture()        //证件照片
                && checkWitness1Name()      //证明人1姓名
                && checkWitness1Code()      //证明人1户口号
                && checkWitness2Name()      //证明人2姓名
                && checkWitness2Code()      //证明人2户口号
                && checkAddress()           //地址
                ) {
            return true;
        }
/**
 *
 *  name	是	string	机构名称，最多60字
 company_type	是	int	机构类型，1=法人，2=事业单位，3=其他团体


 type	        是	int	注册类型，1=机构，2=个人
 card_type	    是	int	证件类型，2=营业执照，3=其它
 card_code	    是	string	证件号，最多60字

 card_name	    当cart_type=3时必填	string	证件名称，最多30字
 bondsman1_name	当cart_type=3时必填	string	证明人1名称，最多30字
 bondsman1_code	当cart_type=3时必填	string	证明人1户口号，最多30字
 bondsman2_name	当cart_type=3时必填	string	证明人2名称，最多30字
 bondsman2_code	当cart_type=3时必填	string	证明人2户口号，最多30字
 address


 type	        是	int	注册类型，1=机构，2=个人
 card_type	    是	int	证件类型，1=身份证，2=护照，3=其它
 card_code	    是	string	证件号

 card_name	    当cart_type=3时必填	string	证件名称
 bondsman1_name	当cart_type=3时必填	string	证明人1名称，最多30字
 bondsman1_code	当cart_type=3时必填	string	证明人1户口号，最多30字
 bondsman2_name	当cart_type=3时必填	string	证明人2名称，最多30字
 bondsman2_code	当cart_type=3时必填	string	证明人2户口号，最多30字
 address	        否	string	家庭地址，最多60个字
 */

        return false;
    }

    /**
     * 检查是否已选择机构证件类型，已选择返回 true
     *
     * @return
     */
    private boolean checkCertificatesType() {
        if (0 <= currentCertificatesTypeIndex && certificatesTypeIdArray.length > currentCertificatesTypeIndex) {
            return true;
        }
        showToast(R.string.toast_select_certificate_type);
        return false;
    }

    /**
     * 检查是否已选择用户证件类型，已选择返回 true
     *
     * @return
     */
    private boolean checkPresonCertificatesType() {
        if (0 <= currentPresonCertificatesTypeIndex && personCertificatesTypeIdArray.length > currentPresonCertificatesTypeIndex) {
            return true;
        }
        showToast(R.string.toast_select_certificate_type);
        return false;
    }

    /**
     * type,
     * certificatesType;
     * certificatesName,
     * certificatesCode,
     * witness1Name,
     * witness2Name,
     * witness1Code,
     * witness2Code,
     * address;
     * 检查是否已正确填写机构名称，已正确填写返回 true
     *
     * @return
     */
    private boolean checkName() {
        name = etCompanyName.getText().toString();
        if (StringUtils.isSafeString(name)) {
            if (StringUtils.checkLengthSafe(name, StringUtils.LENGTH_60)) {
                return true;
            }
            showToast(R.string.toast_company_name_length);
        } else {
            showToast(R.string.toast_company_name);
        }
        return false;
    }

    /**
     * 检查证件名称，已正确填写返回 true
     *
     * @return
     */
    private boolean checkCertificatesName() {
        Log.i(Config.TAG, "isWitness = " + isWitness);
        if (!isWitness) {//如果非证明人模式，不需要填写证件名称，直接返回 true
            return true;
        }
        certificatesName = etCertificatesName.getText().toString();
        if (StringUtils.isSafeString(certificatesName)) {
            if (StringUtils.checkLengthSafe(certificatesName, StringUtils.LENGTH_30)) {
                return true;
            }
            showToast(R.string.toast_certificate_name_length);
            return false;
        }
        showToast(R.string.toast_certificate_name);
        return false;
    }

    /**
     * 检查证件号，已正确填写返回 true
     *
     * @return
     */
    private boolean checkCertificatesCode() {
        certificatesCode = etCertificatesCode.getText().toString();
        if (StringUtils.isSafeString(certificatesCode)) {
            if (StringUtils.checkLengthSafe(certificatesCode, StringUtils.LENGTH_60)) {
                return true;
            }
            showToast(R.string.toast_certificate_code_length);
            return false;
        }
        showToast(R.string.toast_certificate_code);
        return false;
    }

    /**
     * 检查证件照片，已正确填写返回 true
     *
     * @return
     */
    private boolean checkPicture() {
        if (isWitness) {//如果是证明人模式，不需要照片，直接返回 true
            return true;
        }
        LinkedList list = null;
        try {
            Config.globalUser.member.getCertifyPhotoFileList();
        } catch (Exception e) {
        }
        if(null == list || 0 == list.size()){
            showToast(R.string.toast_photo);
            return false;
        }
        return true;
    }

    /**
     * 检查地址，已正确填写返回 true
     *
     * @return
     */
    private boolean checkAddress() {
        address = tvAddress.getText().toString();
        if (StringUtils.isSafeString(address)) {
            if (StringUtils.checkLengthSafe(address, StringUtils.LENGTH_60)) {
                return true;
            }
            showToast(R.string.toast_address_length);
            return false;
        }
        showToast(R.string.toast_address);
        return false;
    }

    /**
     * 检查证明人1，已正确填写返回 true
     *
     * @return
     */
    private boolean checkWitness1Name() {
        if (!isWitness) {//如果非证明人模式，不需要填写证明人1名称，直接返回 true
            return true;
        }
        witness1Name = etWitness1Name.getText().toString();
        if (StringUtils.isSafeString(witness1Name)) {
            return true;
        }
        showToast(R.string.toast_witness1_name);
        return false;
    }

    /**
     * 检查户口号2，已正确填写返回 true
     *
     * @return
     */
    private boolean checkWitness2Name() {
        if (!isWitness) {//如果非证明人模式，不需要填写证明人2名称，直接返回 true
            return true;
        }
        witness2Name = etWitness2Name.getText().toString();
        if (StringUtils.isSafeString(witness2Name)) {
            return true;
        }
        showToast(R.string.toast_witness2_name);
        return false;
    }

    /**
     * 检查户口号1，已正确填写返回 true
     *
     * @return
     */
    private boolean checkWitness1Code() {
        if (!isWitness) {//如果非证明人模式，不需要填写证明人1户口号，直接返回 true
            return true;
        }
        witness1Code = etWitness1Account.getText().toString();
        if (StringUtils.isSafeString(witness1Code)) {
            return true;
        }
        showToast(R.string.toast_witness1_code);
        return false;
    }

    /**
     * 检查户口号2，已正确填写返回 true
     *
     * @return
     */
    private boolean checkWitness2Code() {
        if (!isWitness) {//如果非证明人模式，不需要填写证明人2户口号，直接返回 true
            return true;
        }
        witness2Code = etWitness2Account.getText().toString();
        if (StringUtils.isSafeString(witness2Code)) {
            if (!witness2Code.equals(witness1Code)) {
                return true;
            } else {
                showToast(R.string.toast_witness_code_same);
            }
        } else {
            showToast(R.string.toast_witness2_code);
        }
        return false;
    }

    private Map<String, String> map = new HashMap<>();

    private void addParams() {
        map.put("type", isCompanyType ? "1" : "2");
        map.put("name", name);
        map.put("company_type", String.valueOf(currentCertificatesTypeIndex + 1));
        map.put("card_type", isWitness ? "3" : "2");//card_type	是	int	证件类型，2=营业执照，3=其它
        map.put("card_code", certificatesCode);
        map.put("card_name", certificatesName);
        map.put("bondsman1_name", witness1Name);
        map.put("bondsman1_code", witness1Code);
        map.put("bondsman2_name", witness2Name);
        map.put("bondsman2_code", witness2Code);
        map.put("address", address);
    }

    private void verifyRegister() {
        OkHttpUtils
                .post()//
                .url(URLs.getVerifyRegisterStep2Url())
                .params(map)
                .build()
                .execute(new GenericsCallback<ResultBean>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(Config.TAG, "----------------onError------------");
                    }

                    @Override
                    public void onResponse(ResultBean response, int id) {
                        if (Config.RESULT_OK == response.code) {
                            saveRegisterInfo();
                            Intent intent = new Intent(RegisterCompanyStep2Activity.this, RegisterCompanyStep3Activity.class);
                            intent.putExtra(Config.CONSUMER_TYPE, isCompanyType);
                            startActivity(intent);
                        } else {
                            showToast(response.msg);
                        }
                    }
                });
    }

    private void saveRegisterInfo() {
        if (null == Config.globalUser.member) {
            Config.globalUser.member = new Member();
        }
//        Config.globalUser.member.parentname = parentName;
    }


    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CODE_RESULT_ADDRESS = 0xa3;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x04;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x05;
    private static final String[] arrayPhotoName = {"p1.jpg", "cp1.jpg", "p2.jpg", "cp2.jpg", "p3.jpg", "cp3.jpg", "p4.jpg", "cp4.jpg", "p5.jpg", "cp5.jpg"};
    private String PATH = Environment.getExternalStorageDirectory().getPath() + "/";
    private Uri imageUri;
    private Uri cropImageUri;

    private File getFile(boolean isCrop) {
        File file = null;
        if (-1 < photoIndex && photoIndex << 1 < arrayPhotoName.length) {
            if (!isCrop) {
                file = new File(PATH + arrayPhotoName[photoIndex << 1]);
            } else {
                file = new File(PATH + arrayPhotoName[(photoIndex << 1) + 1]);
            }
        }
        if (null == file) {
            file = new File(PATH + "tmp.jpg");
        }
        return file;
    }

    /**
     * 自动获取相机权限
     */
    private void autoObtainCameraPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                ToastUtils.showShort(this, "您已经拒绝过一次");
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
        } else {//有权限直接调用系统相机拍照
            if (hasSdcard()) {
                imageUri = Uri.fromFile(getFile(false));
                //通过FileProvider创建一个content类型的Uri
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    imageUri = FileProvider.getUriForFile(this, "com.awesome.camera", getFile(false));
                }
                PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
            } else {
                ToastUtils.showShort(this, "设备没有SD卡！");
            }
        }
    }

    private void showImageView() {
        if (imageViews.length > photoIndex) {
            imageViews[photoIndex].setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            //调用系统相机申请拍照权限回调
            case CAMERA_PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasSdcard()) {
                        imageUri = Uri.fromFile(getFile(false));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            imageUri = FileProvider.getUriForFile(this, "com.awesome.camera", getFile(false));//通过FileProvider创建一个content类型的Uri
                        }
                        PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
                    } else {
                        ToastUtils.showShort(this, "设备没有SD卡！");
                    }
                } else {

                    ToastUtils.showShort(this, "请允许打开相机！！");
                }
                break;


            }
            //调用系统相册申请Sdcard权限回调
            case STORAGE_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
                } else {
                    ToastUtils.showShort(this, "请允许打操作SDCard！！");
                }
                break;
            default:
        }
    }

    private static final int OUTPUT_X = 480;
    private static final int OUTPUT_Y = 480;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //拍照完成回调
                case CODE_CAMERA_REQUEST:
                    File file1 = getFile(true);
                    Config.globalUser.member.addCertifyPhotoFile(file1);
                    cropImageUri = Uri.fromFile(file1);
                    PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                    break;
                //访问相册完成回调
                case CODE_GALLERY_REQUEST:
                    if (hasSdcard()) {
                        File file2 = getFile(true);
                        Config.globalUser.member.addCertifyPhotoFile(file2);
                        cropImageUri = Uri.fromFile(file2);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            newUri = FileProvider.getUriForFile(this, "com.awesome.camera", new File(newUri.getPath()));
                        }
                        PhotoUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                    } else {
                        ToastUtils.showShort(this, "设备没有SD卡！");
                    }
                    break;
                case CODE_RESULT_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
                    if (bitmap != null) {
                        showImages(bitmap);
                    }
                    break;
                case CODE_RESULT_ADDRESS:
                    tvAddress.setText(data.getStringExtra(Config.ADDRESS));
                    break;
                default:
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * 自动获取sdk权限
     */
    private void autoObtainStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
        }

    }

    private void showImages(Bitmap bitmap) {
        if (imageViews.length > photoIndex) {
            imageViews[photoIndex++].setImageBitmap(bitmap);
            showImageView();
        }
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}