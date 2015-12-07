package com.example.calendar;

import java.util.Date;

import android.app.Application;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

public class Globals extends Application{
	
	private Date selectedDate;// = Calendar.getInstance().getTime();
	
	protected Date getSelectedDate(){
		return selectedDate;
	}
	
	protected void setSelectedDate(Date newDate){
		selectedDate = newDate;
	}

	protected void setupEventButtonFormat(Button eventButton, Event event){
		eventButton.setMinHeight(0);
		eventButton.setPadding(15, 10, 20, 5); //this changes padding for things contained inside the button (space from the edges) L T R B
		eventButton.setTextSize(10);
		eventButton.setText(event.getName());
		eventButton.setEllipsize(android.text.TextUtils.TruncateAt.END);	//cuts the text off when it doesn't fit and adds ellipses
		eventButton.setSingleLine();// restricts text to a single line
		eventButton.setGravity(Gravity.LEFT); //text inside button will be left aligned
		eventButton.setBackgroundColor(event.getCategory().getColor());
    	
		//eventButton.setId(Integer.parseInt(string));	//need to find this out for later deleting? nah, cause the displayEvents method is called fresh each update
		
		eventButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					//set selectedEvent as e -> use intent to open event_details_view activity and stuff 
			}
		});
	}
	
}//end Globals class
