package com.awesome.consumer.cbms.ui.activity.loginandregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.awesome.consumer.cbms.R;
import com.awesome.consumer.cbms.beans.City;
import com.awesome.consumer.cbms.config.Config;
import com.awesome.consumer.cbms.ui.activity.BaseActivity;

import java.io.Serializable;

public class AddressActivity extends BaseActivity {
    private TextView sp_country, sp_area, sp_city;
    private EditText et_address;
    private City country, province, city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        setPageTitle(R.string.register_company_address);
        showBackButton(true);
        setRightMenu(R.string.save);

        sp_country = findViewById(R.id.sp_country);
        sp_area = findViewById(R.id.sp_area);
        sp_city = findViewById(R.id.sp_city);
        et_address = findViewById(R.id.et_address);

        sp_country.setOnClickListener(this);
        sp_area.setOnClickListener(this);
        sp_city.setOnClickListener(this);
        findViewById(R.id.btn_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = getAddress();
                if (null != address){
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(Config.ADDRESS, address);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }

    private String getAddress(){
        StringBuilder sb = new StringBuilder();
        if (null == country || "".equals(sp_country.getText().toString())){
            showToast(R.string.please_select_country);
        }else
        if (null == province || "".equals(sp_area.getText().toString())){
            showToast(R.string.please_select_province);
        }else
        if (null == city || "".equals(sp_city.getText().toString())){
            showToast(R.string.please_select_city);
        }else
        if ("".equals(et_address.getText().toString())){
            showToast(R.string.please_input_address);
        }else{
            sb.append(country.zh);
            sb.append(province.zh);
            sb.append(city.zh);
            sb.append(et_address.getText().toString());
            return sb.toString();
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SelectCityActivity.class);
        int request_code = Config.REQUEST_CODE_COUNTRY;
        int parentId = 0;
        switch (v.getId()) {
            case R.id.sp_country:
                request_code = Config.REQUEST_CODE_COUNTRY;
                break;
            case R.id.sp_area:
                request_code = Config.REQUEST_CODE_AREA;
                parentId = null != country ? country.id : 1;
                break;
            case R.id.sp_city:
                request_code = Config.REQUEST_CODE_CITY;
                parentId = null != province ? province.id : 1;
                break;
        }
        intent.putExtra(Config.AREA, request_code);
        intent.putExtra(Config.AREA_PARENT, parentId);
        startActivityForResult(intent, request_code);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        City returnCity = (City) data.getSerializableExtra(Config.ADDRESS);
        if (null == returnCity){
            return;
        }
        switch(requestCode){
            case Config.REQUEST_CODE_COUNTRY:
                country = returnCity;
                sp_country.setText(returnCity.zh);
                sp_area.setText(null);
                sp_city.setText(null);
                break;

            case Config.REQUEST_CODE_AREA:
                province = returnCity;
                sp_area.setText(returnCity.zh);
                sp_city.setText(null);
                break;

            case Config.REQUEST_CODE_CITY:
                city = returnCity;
                sp_city.setText(returnCity.zh);
                break;

        }
    }
}
