package com.Shane.calendard;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class DailyViewActivity extends Activity {
	
	
	
	DailyAdapter dailyAdp;
	GridView dailyGridView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.daily_view);
		
		dailyAdp = new DailyAdapter(this, getResources(), new Date());
		setGridView(R.id.dailyGridView);
		dailyGridView.setAdapter(dailyAdp);
		
	}

	private void setGridView(int id) {
		dailyGridView = (GridView) findViewById(id);
	}
	
}