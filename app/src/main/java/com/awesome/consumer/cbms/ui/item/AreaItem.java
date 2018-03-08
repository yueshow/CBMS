package com.awesome.consumer.cbms.ui.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awesome.consumer.cbms.R;

public class AreaItem extends LinearLayout{

	private TextView tv_ctrl;

	public AreaItem(Context context) {
		super(context);
		initView();
	}

	private void initView() {
		View itemView = LayoutInflater.from(this.getContext()).inflate(R.layout.country_item, null);
		addView(itemView);

		tv_ctrl = itemView.findViewById(R.id.tv_country);
	}
	

	public void updataView(String str){
		tv_ctrl.setText(str);
	}
}
