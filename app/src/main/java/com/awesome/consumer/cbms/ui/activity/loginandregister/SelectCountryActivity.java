package com.awesome.consumer.cbms.ui.activity.loginandregister;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.awesome.consumer.cbms.R;
import com.awesome.consumer.cbms.ui.activity.BaseActivity;
import com.awesome.consumer.cbms.ui.adapter.CountryAdapter;

public class SelectCountryActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private ListView listView = null;
    private CountryAdapter adapter = null;
    private String[] list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);

        setPageTitle(R.string.select_country);
        showBackButton(true);
        setRightMenu(R.string.save);

        findViewById(R.id.tv_search).setOnClickListener(this);

        listView = findViewById(R.id.listview);
        list = getResources().getStringArray(R.array.country);
        adapter = new CountryAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

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
        Toast.makeText(this, list[position] , Toast.LENGTH_LONG).show();
    }
}
