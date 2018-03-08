package com.awesome.consumer.cbms.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.awesome.consumer.cbms.beans.OrderBean;
import com.awesome.consumer.cbms.ui.item.OrderItem;

import java.util.List;

public class OrderAdapter extends BaseAdapter {

	private Context context;
	private List<OrderBean> orderList;

	public OrderAdapter(Context context, List<OrderBean> orderList){
		this.context = context;
		this.orderList = orderList;
		Log.i("YukiCEO", "list.size = " + orderList.size());
	}
	
	public int getCount() {
		return orderList == null ? 0 : orderList.size();
	}

	public OrderBean getItem(int position) {
		return orderList.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		OrderItem listViewItem;
		if (convertView == null) {
			listViewItem = new OrderItem(context);
		}else { 
			listViewItem = (OrderItem) convertView;
		}
		listViewItem.updataView(orderList.get(position));
		return listViewItem;
	}
	
}
