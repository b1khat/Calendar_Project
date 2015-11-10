package com.Shane.calendard;

import java.util.Date;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DailyAdapter extends BaseAdapter{
	Context context;
	Resources res;
	
	DailyAdapter(Context context, Resources rs, Date date) {
		this.context = context;
		this.res = rs;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 12;
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = LayoutInflater.from(context).
					inflate(R.layout.item, null);
		 }
		TextView textView = (TextView) convertView.findViewById(R.id.tvtext);
		textView.setGravity(Gravity.LEFT);
		textView.setText("    "+ 2*position);
		textView.setTextColor(Color.BLACK);
		return convertView;
	}
	
	
	
}
