package com.awesome.consumer.cbms.ui.item;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awesome.consumer.cbms.R;
import com.awesome.consumer.cbms.beans.OrderBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class OrderItem extends LinearLayout{
	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private TextView tv_money;
	private TextView tv_date;
	private TextView tv_explain1;
	private TextView tv_explain2;
	private ImageView imgCheck;

	public OrderItem(Context context) {
		super(context);
		initView();
	}

	private void initView() {
		View itemView = LayoutInflater.from(this.getContext()).inflate(R.layout.order_item, null);
		addView(itemView, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		tv_money = itemView.findViewById(R.id.tv_money);
		tv_date = itemView.findViewById(R.id.tv_date);
		tv_explain1 = itemView.findViewById(R.id.tv_explain1);
		tv_explain2 = itemView.findViewById(R.id.tv_explain2);
	}
	

	public void updataView(OrderBean bean){
		if (null != bean.date) {
			tv_date.setText(format.format(bean.date));
		}

        Log.i("YukiCEO", bean.toString());

		if (0 > bean.money) {
			tv_money.setText(String.valueOf("-$" + Math.abs(bean.money)));
			tv_money.setTextColor(Color.RED);
		}else{
			tv_money.setText(String.valueOf("$" + bean.money));
			tv_money.setTextColor(Color.BLUE);
		}

		tv_explain1.setText(bean.explain1);
		tv_explain2.setText(bean.explain2);
	}
}
