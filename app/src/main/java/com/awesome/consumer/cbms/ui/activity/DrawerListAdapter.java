package com.awesome.consumer.cbms.ui.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.awesome.consumer.cbms.R;

import java.util.List;

public class DrawerListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<DrawerListItem> mItems;
	
	public DrawerListAdapter(Context context, List<DrawerListItem> data) {
		this.mInflater = LayoutInflater.from(context);
		this.mItems = data;
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public DrawerListItem getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DrawerListItem item = getItem(position);
		TextView itemTitle = null;
		ImageView itemIcon = null;
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.item, null);
		}
//		itemTitle = (TextView) convertView.findViewById(R.id.item_title);
//		itemIcon = (ImageView) convertView.findViewById(R.id.item_icon);
//		itemTitle.setText(item.getTitle());
//		itemIcon.setBackground(item.getIcon());
		return convertView;
	}

}
