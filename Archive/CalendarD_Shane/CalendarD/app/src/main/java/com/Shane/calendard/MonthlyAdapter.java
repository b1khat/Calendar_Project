package com.Shane.calendard;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MonthlyAdapter extends BaseAdapter {

	private String[] dayNumber = new String[42];
	private String sysDate = "";
	private String sys_year = "";
	private String sys_month = "";
	private String sys_day = "";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
	private Context context;
	private Resources res;
	private SpecialCalendar sc;
	private String currentYear = "";
	private String currentMonth = "";
	private String currentDay = "";
	private boolean isLeapyear = false;  
	private int daysOfMonth = 0;      	
	private int dayOfWeek = 0;        	
	private int lastDaysOfMonth = 0;
	private String showYear = "";
	private String showMonth = "";
	
	
	MonthlyAdapter () {
		Date date = new Date();
		sysDate = sdf.format(date);
		sys_year = sysDate.split("-")[0];
		sys_month = sysDate.split("-")[1];
		sys_day = sysDate.split("-")[2];
	}
	
	public MonthlyAdapter(Context context,
			Resources rs,
			int year_c,int month_c,int day_c){
		this();
		this.context = context;
		sc = new SpecialCalendar();
		this.res = rs;
		
		currentYear = String.valueOf(year_c);
		currentMonth = String.valueOf(month_c);
		currentDay = String.valueOf(day_c);
		getCalendar(year_c,	month_c);
	}
	
	private void getCalendar(int year, int month) {
		isLeapyear = sc.isLeapYear(year);
		daysOfMonth = sc.getDaysOfMonth(isLeapyear, month);
		dayOfWeek = sc.getWeekdayOfMonth(year, month);
		lastDaysOfMonth = sc.getDaysOfMonth(isLeapyear, month-1);
		getweek(year,month);
	}

	private void getweek(int year, int month) {
		int j = 1;
		
		for (int i = 0; i< dayNumber.length; i++){
			if(i < dayOfWeek){  //前一个月
				int temp = lastDaysOfMonth - dayOfWeek+1;
				dayNumber[i] = Integer.toString(temp+i);
				
			}else if(i < daysOfMonth + dayOfWeek){   //本月
				dayNumber[i] = Integer.toString(i-dayOfWeek+1);
				
				setShowYear(String.valueOf(year));
				setShowMonth(String.valueOf(month));
			}else{   //下一个月
				dayNumber[i] = Integer.toString(j);
				j++;
			}
		}
	}

	private void setShowYear(String showYear) {
		this.showYear = showYear;
		
	}
	private void setShowMonth(String showMonth) {
		this.showMonth = showMonth;
		
	}
	
	public Object getShowYear() {
		return showYear;
	}
	
	public Object getShowMonth() {
		return showMonth;
	}

	@Override
	public int getCount() {
		return dayNumber.length;
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
		if (dayNumber[position] == currentDay && position < daysOfMonth + dayOfWeek && position >= dayOfWeek) textView.setBackgroundColor(Color.rgb(35, 146, 217));
		textView.setText(dayNumber[position]);
		textView.setTextColor(Color.GRAY);
		if (position < daysOfMonth + dayOfWeek && position >= dayOfWeek) {
			textView.setTextColor(Color.BLACK);
		}
		return convertView;
	}



}
