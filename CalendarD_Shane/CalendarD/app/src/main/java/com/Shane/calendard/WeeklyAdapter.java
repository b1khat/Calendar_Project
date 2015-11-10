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

public class WeeklyAdapter extends BaseAdapter{
	
		private Context context;
		private Resources res;
		private int weekday;
		
	public WeeklyAdapter(Context context,	Resources rs,  int weekday){
		this.context = context;
		this.res = rs;
		this.weekday = weekday;
	}

	@Override
	public int getCount() {
		return 12*8;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = LayoutInflater.from(context).
					inflate(R.layout.item, null);
		 }
		TextView textView = (TextView) convertView.findViewById(R.id.tvtext);
		textView.setGravity(Gravity.CENTER_HORIZONTAL);
		if ( position % 8 == 0) textView.setText(Integer.toString(2*position/8));
		if ( position % 8 -1  == weekday) textView.setBackgroundColor(Color.rgb(200, 200, 200));
		textView.setTextColor(Color.BLACK);
		return convertView;
	}

}
