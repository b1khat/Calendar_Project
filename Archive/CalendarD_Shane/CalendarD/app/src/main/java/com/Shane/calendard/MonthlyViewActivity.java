package com.Shane.calendard;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.TextView;

public class MonthlyViewActivity extends Activity{

	private String currentDate = "";
	private int year_c ;
	private int month_c;
	private int day_c ;
	MonthlyAdapter adp;
	GridView gridView;
	TextView thisMonthTextView;
	TextView lastMonthTextView;
	TextView nextMonthTextView;
	SimpleDateFormat sdf;
	
	Date tempDate;
	
	
	public MonthlyViewActivity() {
		Date date = new Date();
    	sdf = new SimpleDateFormat("yyyy-M-d");
    	currentDate = sdf.format(date); 
    	year_c = Integer.parseInt(currentDate.split("-")[0]);
    	month_c = Integer.parseInt(currentDate.split("-")[1]);
    	day_c = Integer.parseInt(currentDate.split("-")[2]);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.monthly_view);
		
		// set Last/Next OnClickListener
		setLastNextOnClickListener();
		
//		// TODO this is a debug snippet
//		thisMonthTextView = (TextView) findViewById(R.id.thisMonthTextView);
//		thisMonthTextView.setOnClickListener(new OnClickListener () {
//
//			@Override
//			public void onClick(View v) {
//				Intent intend = new Intent();
//				intend.setClass(MonthlyViewActivity.this, WeeklyViewActivity.class);
//				startActivity(intend);
//			}
//			
//		});
		
		// fill grid_view with items
        adp = new MonthlyAdapter(this,getResources(),year_c,month_c,day_c);
        addGridView();
        gridView.setAdapter(adp);
        
        // initiate thisMonthTextView 
        thisMonthTextView = (TextView) findViewById(R.id.thisMonthTextView);
		addTextToTopTextView(thisMonthTextView);
	}

	private void setLastNextOnClickListener() {
		lastMonthTextView = (TextView) findViewById(R.id.lastMonthTextView);
		lastMonthTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 
			}
		});

	}

	private void addTextToTopTextView(TextView tv) {
		StringBuffer textDate = new StringBuffer();
		textDate.append(adp.getShowYear()).append(" - ").append(adp.getShowMonth());
		tv.setText(textDate);
	}

	private void addGridView() {
		gridView =(GridView)findViewById(R.id.monthlyGridView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == R.id.intent_weekly) {
			Intent intent = new Intent();
			intent.setClass(MonthlyViewActivity.this, WeeklyViewActivity.class);
			intent.putExtra("weekday", 2); 
			startActivity(intent);
			return true;
		}
		
		if (id == R.id.intent_daily) {
			Intent intent = new Intent();
			intent.setClass(MonthlyViewActivity.this, DailyViewActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
