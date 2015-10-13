import java.util.Date;	//deprecated! TimeStamp, Calendar, GregorianCalendar, Time

public class Event{

	private
		String name,
		location,
		description;	//add categoryID

	private Date 	startTime,
					endTime;

	public Event(){	//default constructor
		name = null;	// "" ?
		location = null;
		description = null;
		startTime = null;
		endTime = null;
	}
	public Event(String newName, Date startTime, Date endTime){
		name = newName;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public Event(String newName, Date startTime, Date endTime, String newLocation){
		name = newName;
		this.startTime = startTime;
		this.endTime = endTime;
		location = newLocation;
	}
	public Event(String newName, Date startTime, Date endTime, String newLocation, String newDescription){
		name = newName;
		this.startTime = startTime;
		this.endTime = endTime;
		location = newLocation;
		description = newDescription;
	}

	public String toString(){
		return 	"Name: " + name +
				"\nStart:\t" + startTime +
				"\nEnd:  \t" + endTime +
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
		startTime = new Date(year-1900, month, day, hour, minute);	//the year minus 1900 (the start year from Date API)
	}
	public void setEndTime(int year, int month, int day, int hour, int minute){
		endTime = new Date(year-1900, month, day, hour, minute);
	}

	public String getName(){
		if(name!=null && !name.isEmpty())
			return name;
		return "NO SET NAME ERROR";	//can change to exception
	}
	public String getLocation(){
		if(location!=null && !location.isEmpty())
			return location;
		return "NO SET LOCATION ERROR";	//can change to exception
	}
	public String getDescription(){
		if(description!=null && !description.isEmpty())
			return description;
		return "NO SET DESCRIPTION ERROR";	//can change to exception
	}
	public Date	getStartTime(){
		return startTime;	//Date API returns null and "null" if toString
	}
	public Date	getEndTime(){
		return endTime;
	}

}