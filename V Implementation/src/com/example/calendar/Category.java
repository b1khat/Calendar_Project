/*addEvent still needs conflict management
 *or should it be implemented in another class?
 *
 *
 **/
package com.example.calendar;

import static java.lang.System.*;
import android.graphics.Color; //COLORS ARE REFERENCES TO RES FOLDER, NOT DYNAMICALLY ASSIGNABLE, java.awt.Color off limits,
								//will implement different way for user to choose color once the rest is running properly

public class Category{

	String name; //CategoryID?
	Color color;//could change to r,g,b, and maybe a(alpha)
				//need to define the default color

	public Category(){
		name = null;	//redundant?
		//Color = ?
	}
	public Category(String name){
		this.name = name;
		//requires default color to be defined
	}
	public Category(String name, Color color){
		this.name = name;
		this.color = color;
	}

	public String toString(){
		return "Category Name:\t" + name +
			"\nCategory Color:\t" + color;
	}

	public void setName(String name){
		this.name = name;
	}
	public void setColor(Color color){
		this.color = color;
	}

	public String getName(){
		return name;
	}
	public Color getColor(){
		return color;
	}
}//end Category class