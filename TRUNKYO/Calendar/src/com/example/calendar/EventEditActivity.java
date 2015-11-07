package com.example.calendar;

//import android.support.v7.app.ActionBarActivity;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class EventEditActivity extends Activity {	//ActionBarActivity gives the option menu up there

	private Globals global;
	private Event displayedEvent;
	private EditText 	nameField;
	private TextView	TVeventStartDate;
	
	private DatePicker	startDateField, 
						endDateField;		//DateFields are for accessing Year Month DayOfMonth on DatePickers
	private TimePicker  startTimeField,		//TimeFields are for accessing Hour Minute on TimePickers
						endTimeField;

	private SimpleDateFormat shortForm = new SimpleDateFormat("d E MMM y");  //format date as Day# Weekday(abbreviated) Month(abbrev.) YYYY
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_edit);
		
		global = (Globals)getApplicationContext();
		displayedEvent = global.getSelectedEvent();
		
		setupViews();
	}
	
	private void setupViews(){
		nameField = (EditText)findViewById(R.id.editTextEventName);
		nameField.setText(displayedEvent.getName());
		
		TVeventStartDate = (TextView)findViewById(R.id.textViewEventStartDate);			//could omit the TextViews
        TVeventStartDate.setText(shortForm.format(displayedEvent.getStartTime()));     //unfortunate collision of date-time terms (cause by date time pickers being separate API)   
		
		Date startTime = displayedEvent.getStartTime();
		startDateField = (DatePicker)findViewById(R.id.datePickerStartDate);
		startDateField.updateDate(startTime.getYear() + 1900, startTime.getMonth(), startTime.getDate());
		
		startTimeField = (TimePicker)findViewById(R.id.timePickerStartTime);
	}
	
	
	
	public void saveChanges(View view){
		//Implement safety checks (if there is a conflict, send a long toast
			//check for null string fields
			//CALL A METHOD TO VERIFY INFORMATION AND CALL GETEVENTS if empty, go ahead and save changes
			//make sure event starts before it ends LOL can be done by setting min/max for the pickers
		displayedEvent.setName( nameField.getText().toString());

		System.out.println(displayedEvent);
		displayedEvent.setStartTime(startDateField.getYear(), startDateField.getMonth(), startDateField.getDayOfMonth(), 6, 30);
		System.out.println(startDateField.getYear() + "  " + startDateField.getMonth() + "  " + startDateField.getDayOfMonth());	//DEBUGGING
		System.out.println(displayedEvent);
		
		//MIGHT NEED TO DELETE AND READD EVENT FROM EVENTMANAGER TO RETAIN CHRONOLOGICAL ORDER (or call new sort method in EM)
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_edit, menu);
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
		return super.onOptionsItemSelected(item);
	}

//	public void onDestroy(){super.onDestroy(); finish();	}
}