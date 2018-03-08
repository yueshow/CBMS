package com.awesome.consumer.cbms.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.awesome.consumer.cbms.ui.item.CountryItem;

public class CountryAdapter extends BaseAdapter {

	private Context context;
	private String[] countryList;
	
	public CountryAdapter(Context context, String[] counrtyArray){
		this.context = context;
		this.countryList = counrtyArray;
		Log.i("YukiCEO", "length = " + counrtyArray.length);
	}
	
	public int getCount() {
		return countryList == null ? 0 : countryList.length;
	}

	public String getItem(int position) {
		return countryList[position];
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		CountryItem listViewItem;
		if (convertView == null) {
			listViewItem = new CountryItem(context);
		}else { 
			listViewItem = (CountryItem) convertView;
		}
		listViewItem.updataView(countryList[position]);
		return listViewItem;
	}
	
}
