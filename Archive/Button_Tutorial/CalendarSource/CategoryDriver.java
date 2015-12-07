/**Tests the Category and EventManager classes
 */

import static java.lang.System.*;
import java.util.List;
import java.util.Date;
import java.awt.Color;

public class CategoryDriver{
	public static void main(String [] args){

		//TESTING THE CATEGORY CLASS

		Category c1 = new Category();
/*		out.println(c1 + "\n");
		out.println(c1.getName() + "   " + c1.getColor());

		c1 = new Category("Bald man");
		out.println(c1);
		c1.setColor(new Color(0xBEEF));
		out.println(c1);
		c1.setName("Hairy man");
		out.println(c1);
		out.println(c1.getName() + "   " + c1.getColor());
*/
		c1 = new Category("Sasquatch", Color.CYAN);
//		out.println(c1);

		//TESTING THE EVENTMANAGER CLASS

		EventManager em = new EventManager();

		//em.addEvent(new Event());
		//em.addEvent(new Event());//without proper handling, these two will cause numerous problems in addEvent method

		em.addEvent(new Event("Birthday", new Date(1995-1900, 6, 27, 7, 30), new Date(1995-1900, 6, 27, 8, 42), c1));
		em.addEvent(new Event("Birthday", new Date(1995-1900, 6, 27, 7, 30), new Date(1995-1900, 6, 27, 8, 42), c1));	//perfect dupes shouldn't get inserted?
		em.addEvent(new Event("Birthday", new Date(1995-1900, 6, 27, 8, 45), new Date(1995-1900, 6, 27, 8, 42), c1));
		em.addEvent(new Event("Birthday", new Date(1995-1900, 6, 27, 5, 23), new Date(1995-1900, 6, 27, 8, 42), c1));

		List<Event> events= em.getEvents();
		for(Event e : events){
			out.println("At index " + events.indexOf(e) + "\n" + e);
		}
	}
}//end CategoryDriver class
