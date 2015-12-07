package com.example.calendar;

import java.text.SimpleDateFormat;
import java.util.*;	//Date, Calendar, & GregorianCalendar
import android.app.Activity;
import android.os.Bundle;	
import android.widget.Button;	
import android.widget.TextView;	
import android.widget.RelativeLayout;


public class DailyViewActivity extends Activity { //ActionBarActivity //CompatActivity

	private RelativeLayout dailyLayout;
	private Globals global;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_view);
        
        dailyLayout = (RelativeLayout) findViewById(R.id.ScrollingLayout);
        global = (Globals)getApplicationContext();
        
        setDisplayedDate();  
        displayEvents();
    }
    
    private void displayEvents(){
    	//possibly utilize clipping? clip_vertical in RelativeLayout api
    	//add a loop through all elements in teh given day
    	
    	//Fetch selectedDate, fetch getEvents(selectedDate, endOfSelectedDate) -> loop (setupEventParams + addView)
    	Date selectedDate = ((Globals)getApplicationContext()).getSelectedDate();
    	Date startOfSelectedDate = new Date(selectedDate.getYear(), selectedDate.getMonth(), selectedDate.getDate());
    	Date endOfSelectedDate = new Date(selectedDate.getYear(), selectedDate.getMonth(), selectedDate.getDate()+1);	//should be midnight (24 hours after start)
    	
    	ArrayList<Event> eventsToDisplay = EventManager.getEvents(startOfSelectedDate, endOfSelectedDate);
    	for(Event e: eventsToDisplay){

    		Button eventButton = new Button(this);

    		RelativeLayout.LayoutParams eventButtonParams = getEventButtonParams(e);	
    		global.setupEventButtonFormat(eventButton, e);
    		eventButton.setLayoutParams(eventButtonParams);
    		
    		dailyLayout.addView(eventButton);	//PUT THE BUTTON IN THE DAILYVIEW LAYOUT
    	}
    	
    	
   /* 	Button b1 = new Button(this);		//for immediate testing (doesn't require event added in EventManager
    	
    	RelativeLayout.LayoutParams b1params =  new RelativeLayout.LayoutParams(
													RelativeLayout.LayoutParams.MATCH_PARENT,
													RelativeLayout.LayoutParams.MATCH_PARENT);	//SEE WHAT THIS DOES TO events in same hour vs WRAP_CONTENT
       	
    	b1params.addRule(RelativeLayout.RIGHT_OF, R.id.hour1);
    	b1params.addRule(RelativeLayout.ALIGN_TOP, R.id.hour15);
    	b1params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour18);  //3 to 6
    
    	b1.setMinHeight(0);
    	b1.setPadding(15, 10, 20, 5); //this changes padding for things contained inside the button (space from the edges) L T R B
    	b1.setTextSize(10);
    	b1.setText("HEY MAN THIS IS THE FIRST EVENT");
    	b1.setEllipsize(android.text.TextUtils.TruncateAt.END);	//cuts the text off when it doesn't fit and adds ellipses
    	b1.setSingleLine();// restricts text to a single line
    							//maybe setScrollHorizontally false? if needed
    	b1.setGravity(Gravity.LEFT); //text inside button will be left aligned

    	b1.setLayoutParams(b1params);
    	dailyLayout.addView(b1);
    */
    }

    private RelativeLayout.LayoutParams getEventButtonParams(Event event){
    	RelativeLayout.LayoutParams eventButtonParams = new RelativeLayout.LayoutParams(
																			RelativeLayout.LayoutParams.MATCH_PARENT,
																			RelativeLayout.LayoutParams.MATCH_PARENT);
    	int eventStartHour = event.getStartTime().getHours();
    	int eventEndHour = event.getEndTime().getHours();
    	
    	eventButtonParams.addRule(RelativeLayout.RIGHT_OF, R.id.hour1);	//eventButtons will always be to the right of the hour column
    	
    	switch(eventStartHour){
    		case 0: eventButtonParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);			break;
    		case 1: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour1); 	break;
    		case 2: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour2); 	break;
    		case 3: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour3); 	break;
    		case 4: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour4); 	break;
    		case 5: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour5);	break;
    		case 6: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour6); 	break;
    		case 7: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour7); 	break;
    		case 8: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour8); 	break;
    		case 9: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour9); 	break;
    		case 10: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour10); 	break;
    		case 11: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour11); 	break;
    		case 12: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour12); 	break;
    		case 13: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour13); 	break;
    		case 14: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour14); 	break;
    		case 15: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour15); 	break;
    		case 16: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour16); 	break;
    		case 17: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour17); 	break;
    		case 18: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour18); 	break;
    		case 19: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour19); 	break;
    		case 20: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour20); 	break;
    		case 21: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour21); 	break;
    		case 22: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour22); 	break;
    		case 23: eventButtonParams.addRule(RelativeLayout.ALIGN_TOP, R.id.hour23); 	break;
    	}

    	//if(event.getEndTime().getDate().compareTo(selectedDate) != 0){ //if it doesn't end on this same day
    	//		eventButtonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM); maybe add align parent right as well if it works
    	//}else{
    	switch(eventEndHour){
			case 0: 
			case 1: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour1); break;
			case 2: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour2); break;
			case 3: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour3); break;
			case 4: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour4); break;
			case 5: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour5); break;
			case 6: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour6); break;
			case 7: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour7); break;
			case 8: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour8); break;
			case 9: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour9); break;
			case 10: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour10); break;
			case 11: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour11); break;
			case 12: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour12); break;
			case 13: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour13); break;
			case 14: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour14); break;
			case 15: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour15); break;
			case 16: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour16); break;
			case 17: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour17); break;
			case 18: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour18); break;
			case 19: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour19); break;
			case 20: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour20); break;
			case 21: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour21); break;
			case 22: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour22); break;
			case 23: eventButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour23); break;
    	}
    	return eventButtonParams;
    }
    //can use android:onClick="methodName" instead of OnClickListeners, the method must take only a View
    //use android:minHeight="0dp" to override inherited min values in Buttons?
    //call finish(); somewhere? maybe not
    
    private void setDisplayedDate(){
    	//can make this an app-wide method
    	TextView currentDateTV = (TextView)findViewById(R.id.theDate);
    	Date selectedDate = ((Globals)getApplicationContext()).getSelectedDate();
        String theDateText = "";

        SimpleDateFormat shortForm = new SimpleDateFormat("d E MMM y");	//format date as Day# Weekday(abbreviated) Month(abbrev.) YYYY
        theDateText += shortForm.format(selectedDate);        
        
        currentDateTV.setText(theDateText);
    }
  
}//end DailyViewActivity class