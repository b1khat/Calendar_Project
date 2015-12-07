/**
 *For now, this class hosts the management of the event list.
 *It includes management of categories as well for the time being.
 */
import static java.lang.System.*;
import java.awt.Color;
import java.util.*;//for arraylists


public class EventManager{			//does this need to be all static?

	private ArrayList<Event> eventList = new ArrayList<Event>();
	private ArrayList<Category> categoryList =  new ArrayList<Category>();	//collection?

	public void addEvent(Event newEvent){
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

	public void removeEvent(Event delEvent){ //Event to be deleted
		//probably best to change it to just the name for faster searching (problematic in case of repeated events)
		//unless it goes by memory address
		//search for delEvent in eventList, then remove

		/**In the case of repeating events (sharing every field except for start and endtimes ?) Do they need to be tied together?
		 * Only delete the perfectly matching event.
		 */
		categoryList.remove(delEvent);
	}

	//Delete all instances of a repeating event.
	public void removeAllInstancesOfEvent(Event delEvent){
		/**Traverse the list, deleting any event that matches @Param's attributes (except for start/end time ? see comment in removeEvent)
		 	Look into  removeAll(Collection<?> c)
		 */

	}

	public void addCategory(Category newCategory){
		categoryList.add(newCategory);
	}

	public void removeCategory(Category category){
		categoryList.remove(category);
	}

	public ArrayList<Event> getEvents(){	//PROBABLY ONLY FOR TESTING/DEBUGGING
		return eventList;
	}
}
