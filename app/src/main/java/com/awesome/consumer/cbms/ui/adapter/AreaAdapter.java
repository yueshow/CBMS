package com.awesome.consumer.cbms.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.awesome.consumer.cbms.beans.City;
import com.awesome.consumer.cbms.ui.item.AreaItem;
import com.awesome.consumer.cbms.ui.item.CountryItem;

import java.util.List;

public class AreaAdapter extends BaseAdapter {

	private Context context;
	private List<City> countryList;

	public AreaAdapter(Context context, List<City> counrtyArray){
		this.context = context;
		this.countryList = counrtyArray;
	}
	
	public int getCount() {
		return countryList == null ? 0 : countryList.size();
	}

	public City getItem(int position) {
		return countryList.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		AreaItem listViewItem;
		if (convertView == null) {
			listViewItem = new AreaItem(context);
		}else { 
			listViewItem = (AreaItem) convertView;
		}
		listViewItem.updataView(countryList.get(position).zh);
		return listViewItem;
	}
	
}
