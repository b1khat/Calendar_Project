package com.example.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import android.widget.Toast;

public class EventEditActivity extends Activity {

	private Globals global;
	private Event displayedEvent;
	private EditText 	nameField,
						locationField,
						descriptionField,
						weeklyRepetitionField;
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
        
        weeklyRepetitionField = (EditText)findViewById(R.id.editTextWeeklyRepetition);
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
			
	//	if(!currentInfoIsValid()){
	//		return;	//abort save, don't exit the event edit
	//	}
		
		displayedEvent.setName( nameField.getText().toString());
		displayedEvent.setLocation( locationField.getText().toString());
		displayedEvent.setDescription( descriptionField.getText().toString());

		System.out.println(displayedEvent);
		displayedEvent.setStartTime(startDateField.getYear(), startDateField.getMonth(), startDateField.getDayOfMonth(), 
																	startTimeField.getHour(), startTimeField.getMinute());
		displayedEvent.setEndTime(endDateField.getYear(), endDateField.getMonth(), endDateField.getDayOfMonth(),
																	endTimeField.getHour(), endTimeField.getMinute());
		displayedEvent.setCategory((Category)categorySpinner.getSelectedItem());
		
		System.out.println(displayedEvent);
		
		for(int repetition = 1; repetition <= Integer.parseInt(weeklyRepetitionField.getText().toString()); repetition++){
			GregorianCalendar newStart = new GregorianCalendar(startDateField.getYear(), startDateField.getMonth(), startDateField.getDayOfMonth() + 7*repetition,	//7 days ahead (1 week) 
															startTimeField.getHour(), startTimeField.getMinute());
			GregorianCalendar newEnd = new GregorianCalendar(endDateField.getYear(), endDateField.getMonth(), endDateField.getDayOfMonth() + 7*repetition,
															endTimeField.getHour(), endTimeField.getMinute() );
			Event newEvent = new Event(displayedEvent.getName(), newStart, newEnd, displayedEvent.getCategory());
			EventManager.addEvent(newEvent);
			System.out.println("added repetition for week " + repetition + " after original");
		}
		
		//MIGHT NEED TO DELETE AND READD EVENT FROM EVENTMANAGER TO RETAIN CHRONOLOGICAL ORDER (or call new sort method in EM) if it even matters
		finish();
	}

	/**
	 * OnClick method for the Delete Event button
	 * 
	 **/
	public void deleteEvent(View view){
		//if(event.isWeekly())
		//removeAllInstancesOfEvent(displayedEvent);
		EventManager.removeEvent(displayedEvent);
		finish();
	}
	
	private boolean currentInfoIsValid(){
		//check for null/empty string name field
		//make sure event starts before it ends LOL can be done by setting min/max for the pickers
		String newName = nameField.getText().toString();
		if(newName == null || newName.isEmpty()){
			Toast.makeText(getApplicationContext(), "Please enter a valid name; Changes not saved.", Toast.LENGTH_LONG).show();
			return false;
		}
		String numberOfWeeksToRepeat = weeklyRepetitionField.getText().toString();
		if(numberOfWeeksToRepeat == null || numberOfWeeksToRepeat.isEmpty()){
			Toast.makeText(getApplicationContext(), "Please enter a valid number of weeks to repeat; Changes not saved.", Toast.LENGTH_LONG).show();
			return false;
		}
		int numberOfWeeks = Integer.parseInt(numberOfWeeksToRepeat);
		if(numberOfWeeks >= 521){	//if more than 10 years worth of repetition
			Toast.makeText(getApplicationContext(), "Please enter a lower number of weeks to repeat; Changes not saved.", Toast.LENGTH_LONG).show();
			return false;
		}

		System.out.println("I GOT TO THE WEEKLY C HECK");
		//COULD REFACTOR THIS CHECK TO A NEW METHOD THAT RETURNS ARRAYLIST OF EVENTS TO ADD (SIZE==0 IF INFO INVALID)
		//ArrayList<Event> eventsToAdd = new ArrayList<Event>();
		Category tempCat = (Category)categorySpinner.getSelectedItem();
		Event tempEvent;
		ArrayList<Event> eventList = EventManager.getEvents();
		tempEvent = new Event(newName, 
								new GregorianCalendar(startDateField.getYear(), startDateField.getMonth(), startDateField.getDayOfMonth(), 
														startTimeField.getHour(), startTimeField.getMinute()), 
								new GregorianCalendar(endDateField.getYear(), endDateField.getMonth(), endDateField.getDayOfMonth(),
														endTimeField.getHour(), endTimeField.getMinute() ), 
														tempCat);
		//eventList.remove(displayedEvent);	//no need to compare with the pre-edited version of itself
											//need to reinsert this after the check though!
		for(Event event: eventList){
			if(tempEvent.inConflict(event)){
				if(tempEvent.equals(event)){
					System.out.println("In conflict yup");
					System.out.println(tempEvent + " \n "+ event + " IN CONFLICT ");
					Toast.makeText(getApplicationContext(), "Event is in time conflict with another; Changes not saved.", Toast.LENGTH_LONG).show();
					return false;
				}
			}
		}
		System.out.println("PAST THE FIRST CHECK NOPE");
		for(int repetition = 1; repetition <= Integer.parseInt(weeklyRepetitionField.getText().toString()); repetition++){
			tempEvent = new Event("", 
					new GregorianCalendar(startDateField.getYear(), startDateField.getMonth(), startDateField.getDayOfMonth() + 7*repetition,	//7 days ahead (1 week) 
							startTimeField.getHour(), startTimeField.getMinute()), 
					new GregorianCalendar(endDateField.getYear(), endDateField.getMonth(), endDateField.getDayOfMonth() + 7*repetition,
							endTimeField.getHour(), endTimeField.getMinute() ), 
					tempCat);
			for(Event event: eventList){
				if(tempEvent.inConflict(event)){
					Toast.makeText(getApplicationContext(), "Event in conflict upon weekly repetition " + repetition + "; Changes not saved.", Toast.LENGTH_LONG).show();
					return false;
				}
			}
		}
		
		return true;
	}
	
}//end class EventEditActivity