/**

 *For now, this class hosts the management of the event list.
 *It includes management of categories as well for the time being.
 */
package com.example.calendar;

import static java.lang.System.*;
//import java.awt.Color;
//import android.graphics.Color;
import java.util.*;//for arraylists


public class EventManager{			//does this need to be all static?
	
	private static ArrayList<Event> eventList = new ArrayList<Event>();
	private static ArrayList<Category> categoryList =  new ArrayList<Category>();	//collection?

	public static void addEvent(Event newEvent){
		//need to check for uniqueness? ->Repeating events?

		if(newEvent.getName() == null || newEvent.getName().isEmpty()){
			return;	//if name is invalid (Event is invalid), do not insert element. We can replace with exception handling or forced names in Event class
		}
		//System.out.println("I'm here, valid name");//debugging
		//Upon insertion, sort chronologically by startTime
		int lcv;	//loop control variable
		if(eventList.isEmpty()){
			eventList.add(newEvent);
		}else{
			lcv = eventList.size();	//fetch list size to prevent loop from constant reiteration upon element insertion (which increases size before next
									//iteration of the loop)
			for(int i = 0; i<lcv; i++){
				if(newEvent.getStartTime().before(eventList.get(i).getStartTime())){
					eventList.add(i, newEvent);//insert at this index, shifting other elements to the right
					return;		//events beginning at same time will end up in the reverse order they were inserted
				}else{
					if(newEvent.getStartTime().equals(eventList.get(i).getStartTime())){	//if two events begin at same time
						out.println("YOU HIT ME i=" + i + " lcv="+lcv);
						eventList.add(i, newEvent);
						return;
					}
					if(i+1==lcv){	//if we are at end of list
						eventList.add(newEvent);//add to end of list
						//return;
					}
				}
			}
		}
	}

	public static void removeEvent(Event delEvent){ //Event to be deleted
		//probably best to change it to just the name for faster searching (problematic in case of repeated events)
		//unless it goes by memory address
		//search for delEvent in eventList, then remove

		/**In the case of repeating events (sharing every field except for start and endtimes ?) Do they need to be tied together?
		 * Only delete the perfectly matching event.
		 */
		categoryList.remove(delEvent);
	}

	//Delete all instances of a repeating event.
	public static void removeAllInstancesOfEvent(Event delEvent){
		/**Traverse the list, deleting any event that matches @Param's attributes (except for start/end time ? see comment in removeEvent)
		 	Look into  removeAll(Collection<?> c)
		 	any cryptic checks in java?
			
		 */

	}

	public static void addCategory(Category newCategory){
		categoryList.add(newCategory);
	}

	public static void removeCategory(Category category){

		//check for events in this category still existing, prompt change
		/*
		 for(Event e: eventList){
		 		if(e.getCategory() == category){ //or e.getCategory.getName() = category.getName() //or .isEqual()? .compareTo()==0?

		 			prompt user to reassign e to a new category? or option to dump all events into another category

		 			e.setCategory(user's choice from existing categories);
		 		}
		 }
		 *
		 **/
		 //After verification, it is safe to delete category from the list
		 categoryList.remove(category);
	}

	public static ArrayList<Event> getEvents(){	//PROBABLY ONLY FOR TESTING/DEBUGGING
		return eventList;//agenda view
	}

	public static ArrayList<Event> getEvents(Date startPeriod, Date endPeriod){
		ArrayList<Event> eventsInPeriod = new ArrayList<Event>();

		//Search through events list and find the events that start
		//in between startPeriod and endPeriod
		Date startTime; //, endTime;
		for(Event e: eventList){
			startTime = e.getStartTime();
			//endTime = e.getEndTime();
			if( startTime.compareTo(startPeriod) >= 0 ){	//event starts after the period begins (or at the exact moment)
				out.println("YOU GOT HERE LAYER 1");	//if( endTime.compareTo(startPeriod) < )  //If we have extra time, add an option in settings to show events that started on days prior
				if( startTime.compareTo(endPeriod) < 0 ){	//event starts before the period ends
					out.println("YOU GOT HERE LAYER 2");
					eventsInPeriod.add(e);
				}
			}
		}
		return eventsInPeriod;
	}
	
	public static ArrayList<Category> getCategories(){
		return categoryList;
	}
}