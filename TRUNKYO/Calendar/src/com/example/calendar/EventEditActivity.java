package com.example.calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class EventEditActivity extends Activity {

	private Globals global;
	private Event displayedEvent;
	private EditText 	nameField,
						locationField,
						descriptionField;
	private TextView	TVeventStartDate, 
						TVeventEndDate;
	private DatePicker	startDateField, 
						endDateField;		//DateFields are for accessing Year Month DayOfMonth on DatePickers
	private TimePicker  startTimeField,		//TimeFields are for accessing Hour Minute on TimePickers
						endTimeField;
	private Spinner categorySpinner;
	
	private SimpleDateFormat shortForm = new SimpleDateFormat("d E MMM y\nhh:mm a");  //format date as Day# Weekday(abbreviated) Month(abbrev.) YYYY
																					//then \n Hour:Minute AM/PM
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_edit);
		
		global = (Globals)getApplicationContext();
		displayedEvent = global.getSelectedEvent();
		
		setupTextViews();
		setupCategorySpinner();
		setupPickers();
	}
	
	private void setupTextViews(){
		nameField = (EditText)findViewById(R.id.editTextEventName);
		nameField.setText(displayedEvent.getName());
		
		locationField = (EditText)findViewById(R.id.editTextEventLocation);
		locationField.setText(displayedEvent.getLocation());
		
		descriptionField = (EditText)findViewById(R.id.editTextEventDescription);
		descriptionField.setText(displayedEvent.getDescription());
		
		TVeventStartDate = (TextView)findViewById(R.id.textViewEventStartDate);			//could omit the TextViews
        TVeventStartDate.setText(shortForm.format(displayedEvent.getStartTime()));     //unfortunate collision of date-time terms (cause by date time pickers being separate API)   
		
        TVeventEndDate = (TextView)findViewById(R.id.textViewEventEndDate);
        TVeventEndDate.setText(shortForm.format(displayedEvent.getEndTime()));
	}
	
	private void setupCategorySpinner(){
		categorySpinner = (Spinner)findViewById(R.id.spinnerEventCategory);
		ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_item); //, arrayOfData
		adapter.addAll(EventManager.getCategories());
		categorySpinner.setAdapter(adapter);
		categorySpinner.setSelection(adapter.getPosition(displayedEvent.getCategory())); 
	}
	
	private void setupPickers(){
		//Initialize Pickers to the event's start date and time
		Date startTime = displayedEvent.getStartTime();
				
		startDateField = (DatePicker)findViewById(R.id.datePickerStartDate);
		startDateField.updateDate(startTime.getYear() + 1900, startTime.getMonth(), startTime.getDate());
				
		startTimeField = (TimePicker)findViewById(R.id.timePickerStartTime);
		startTimeField.setHour(startTime.getHours());
		startTimeField.setMinute(startTime.getMinutes());
				
		//Initialize other Pickers to event's end date and time	
		Date endTime = displayedEvent.getEndTime();
				
		endDateField = (DatePicker)findViewById(R.id.datePickerEndDate);
		endDateField.updateDate(endTime.getYear() + 1900, endTime.getMonth(), endTime.getDate());
			
		endTimeField = (TimePicker)findViewById(R.id.timePickerEndTime);
		endTimeField.setHour(endTime.getHours());
		endTimeField.setMinute(endTime.getMinutes());		
	}
	
	public void saveChanges(View view){
		//Implement safety checks (if there is a conflict, send a long toast
			//check for null string fields
			//CALL A METHOD TO VERIFY INFORMATION AND CALL GETEVENTS if empty, go ahead and save changes
			//make sure event starts before it ends LOL can be done by setting min/max for the pickers
		displayedEvent.setName( nameField.getText().toString());
		displayedEvent.setLocation( locationField.getText().toString());
		displayedEvent.setDescription( descriptionField.getText().toString());

		System.out.println(displayedEvent);
		displayedEvent.setStartTime(startDateField.getYear(), startDateField.getMonth(), startDateField.getDayOfMonth(), 
																	startTimeField.getHour(), startTimeField.getMinute());
		//System.out.println(startDateField.getYear() + "  " + startDateField.getMonth() + "  " + startDateField.getDayOfMonth());	//DEBUGGING
		displayedEvent.setEndTime(endDateField.getYear(), endDateField.getMonth(), endDateField.getDayOfMonth(),
																	endTimeField.getHour(), endTimeField.getMinute());
		//System.out.println(endDateField.getYear() + "  " + endDateField.getMonth() + "  " + endDateField.getDayOfMonth());	//DEBUGGING
		displayedEvent.setCategory((Category)categorySpinner.getSelectedItem());
		
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