package com.example.calendar;

//import java.util.Calendar;
import java.util.Date;	//deprecated! TimeStamp, Calendar, GregorianCalendar, Time
import java.util.GregorianCalendar;

public class Event{

	private
		String name,
		location,
		description;


	private GregorianCalendar 	startTime,
								endTime;	//does this need to be Calendar for typecasting?

	private Category category;

	/*Pre-Condition for constructors:	startTime is actually before the endTime!
	 **/
	public Event(){	//default constructor needs protection against referencing made to objects with null attributes?
		name = null;	// "" ?
		location = null;
		description = null;
		startTime = null;
		endTime = null;
		//category = ?? define default category
	}
	public Event(String newName, GregorianCalendar startTime, GregorianCalendar endTime, Category cat){
		name = newName;
		this.startTime = startTime;
		this.endTime = endTime;
		category = cat;
	}
	public Event(String newName, GregorianCalendar startTime, GregorianCalendar endTime, Category cat, String newLocation){
		name = newName;
		this.startTime = startTime;
		this.endTime = endTime;
		category = cat;
		location = newLocation;
	}
	public Event(String newName, GregorianCalendar startTime, GregorianCalendar endTime, Category cat, String newLocation, String newDescription){
		name = newName;
		this.startTime = startTime;
		this.endTime = endTime;
		category = cat;
		location = newLocation;
		description = newDescription;
	}

	public String toString(){
		return 	"Name: " + name +
				"\nStart:\t" + startTime.getTime() +
				"\nEnd:  \t" + endTime.getTime() +
				"\n" + category +
				"\nLocation: \t\t" + location +
				"\nDescription: \t" + description;
	}

	public void setName(String newName){
		if(newName!=null && !newName.isEmpty())//prevent null pointer exception (order matters)
			name = newName;						//insert exception handling for all
	}
	public void setLocation(String newLocation){
		if(newLocation!=null && !newLocation.isEmpty()) //prevent null pointer
		location = newLocation;
	}
	public void setDescription(String newDescription){
		if(newDescription!=null && !newDescription.isEmpty())
		description = newDescription;
	}
	public void setStartTime(int year, int month, int day, int hour, int minute){	//perhaps change this to setStartTime(Date newStartTime)
		//startTime = new GregorianCalendar(year-1970, month, day, hour, minute);	//the year minus 1970 (the start year from Date API)
		startTime = new GregorianCalendar(year, month, day, hour, minute);	//verify this in all similar methods
	}
	public void setEndTime(int year, int month, int day, int hour, int minute){
		//endTime = new GregorianCalendar(year-1970, month, day, hour, minute);
		endTime = new GregorianCalendar(year, month, day, hour, minute);
	}
	public void setCategory(Category cat){
		category = cat;
	}

	public String getName(){
	//	if(name!=null && !name.isEmpty())
			return name;
	//	return "NO SET NAME";// ERROR";	//can change to exception
	}
	public String getLocation(){
		if(location!=null && !location.isEmpty())
			return location;
		return "NO SET LOCATION";// ERROR";	//can change to exception
	}
	public String getDescription(){
		if(description!=null && !description.isEmpty())
			return description;
		return "NO SET DESCRIPTION";// ERROR";	//can change to exception
	}

	public Date getStartTime(){
		return startTime.getTime();	//Calendar API? //Date API returns null and "null" if toString
	}
	public Date getEndTime(){
		return endTime.getTime();	//Calendar.getTime() returns a Date object
	}
	public Category getCategory(){
		return category;
	}

	public boolean inConflict(Event otherEvent){
		//Doesn't check for when they are at the exact same time!
		Date start1 = startTime.getTime();
		Date start2 = otherEvent.getStartTime();
		Date end1 = endTime.getTime();
		Date end2 = otherEvent.getEndTime();
		if(start1.after(start2) && start1.before(end2)){
			//This event starts between the beginning and end of otherEvent
			return true;
		}
		if(end1.after(start2) && end1.before(end2)){
			//This event ends between the beginning and end of otherEvent
			return true;
		}
		if(start1.equals(start2) || end2.equals(end2)){
			return true;
		}
		return false;
	}
	public boolean equals(Event otherEvent){
		if(name.equals(otherEvent.getName())){
			return false;
		}
	/*	if(location.equals(otherEvent.getLocation())){ //nullptr?
			return false;
		}
		if(description.equals(otherEvent.getDescription())){
			return false;
		}*/
		if(startTime.getTime().equals(otherEvent.getStartTime())){
			return false;
		}
		if(endTime.getTime().equals(otherEvent.getEndTime())){
			return false;
		}//no need to check non-Time fields -> can't have multiple events at same time anyways
		return true;
	}

}//end Event class