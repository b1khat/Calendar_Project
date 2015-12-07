package com.Shane.calendard;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;


public class WeeklyViewActivity extends Activity{
	
	WeeklyAdapter weeklyAdp;
	GridView weeklyGridView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weekly_view);
		
		Intent intent = getIntent();
		int weekday = intent.getIntExtra("weekday", 0);
		
		// fill grid view
		weeklyAdp = new WeeklyAdapter(this, getResources(), weekday);	
		setGridView();
		weeklyGridView.setAdapter(weeklyAdp);
		// TODO 
		
		
		// test for filling with adapter
//		MonthlyAdapter adp = new MonthlyAdapter(this,getResources(),2015,10,1);
//		setGridView();
//		weeklyGridView.setAdapter(adp);
		
	}

	private void setGridView() {
		weeklyGridView = (GridView) findViewById(R.id.weeklyGridView);
	}
	

}
