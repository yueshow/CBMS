package com.awesome.consumer.cbms.ui.activity.loginandregister;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.awesome.consumer.cbms.R;
import com.awesome.consumer.cbms.beans.City;
import com.awesome.consumer.cbms.config.Config;
import com.awesome.consumer.cbms.db.services.AddressService;
import com.awesome.consumer.cbms.ui.activity.BaseActivity;
import com.awesome.consumer.cbms.ui.adapter.AreaAdapter;
import com.awesome.consumer.cbms.ui.adapter.CountryAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectCityActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private ListView listView = null;
    private AreaAdapter adapter = null;
    private List<City> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);

        Intent intent = getIntent();
        int area = intent.getIntExtra(Config.AREA, Config.PROEINCE);
        int parentId = intent.getIntExtra(Config.AREA_PARENT, 1);

        setPageTitle(Config.PROEINCE == area ? R.string.select_province : R.string.select_city);
        showBackButton(true);
//        setRightMenu(R.string.save);

        findViewById(R.id.tv_search).setOnClickListener(this);

        listView = findViewById(R.id.listview);
        listView.setOnItemClickListener(this);

        Message msg = handler.obtainMessage();
        msg.what = GET_DATA;
        msg.arg1 = parentId;
        handler.sendMessage(msg);
    }

    final int GET_DATA = 0x01;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case GET_DATA:
                    int parentId = msg.arg1;
                    List<City> result = new AddressService(SelectCityActivity.this).getCity(parentId);
                    if (null != result){
                        list.clear();
                        list.addAll(result);
                    }
                    if (null == adapter){
                        adapter = new AreaAdapter(SelectCityActivity.this, list);
                        listView.setAdapter(adapter);
                    }else{
                        adapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_right:
                finish();
                break;

            case R.id.tv_search:

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(Config.ADDRESS, (Serializable) list.get(position));
        setResult(position, resultIntent);
        finish();
    }
}
