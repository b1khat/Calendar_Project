import static java.lang.System.*;
import java.util.Date;

public class EventDriver{
	public static void main(String [] args){
		Event event1 = new Event();
		out.println(event1.getName());
		out.println(event1.getStartTime());
		out.println(event1.getEndTime());
		out.println(event1);

		Event event2 = new Event("Birthday", new Date(1995-1900, 6, 27, 7, 30), new Date(1995-1900, 6, 27, 8, 42));
		out.println(event2);

		Event event3 = new Event("BirthdayBro", new Date(1995-1900, 6, 27, 7, 30), new Date(1995-1900, 6, 27, 8, 42), "Mesquite, TX");
		out.println(event3);

		Event event4 = new Event("Birthday", new Date(1995-1900, 6, 27, 7, 30), new Date(1995-1900, 6, 27, 8, 42), "Masskqweet, Tejas", "Best time to be alive");
		out.println(event4);

	}
}
