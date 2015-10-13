import static java.lang.System.*;
import java.awt.Color;
import java.util.*;//for arraylists

public class Category{

	String name; //CategoryID?
	Color color;//could change to r,g,b, and maybe a(alpha)
				//need to define the default color
	ArrayList<Event> eventList = new ArrayList<Event>();

	public Category(String name){
		this.name = name;
		//requires default color to be defined
	}
	public Category(String name, Color color){
		this.name = name;
		this.color = color;
	}

	public void addEvent(Event newEvent){
		eventList.add(newEvent);
	}

	public void removeEvent(Event delEvent){ //Event to be deleted
		//probably best to change it to just the name for faster searching
		//unless it goes by memory address
		//search for delEvent in eventList, then remove
	}

}