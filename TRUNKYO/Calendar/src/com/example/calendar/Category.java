/*addEvent still needs conflict management
 *or should it be implemented in another class?
 *
 *
 **/
package com.example.calendar;

//import static java.lang.System.*;
//import java.awt.Color;
//import android.graphics.Color; //COLORS ARE REFERENCES TO RES FOLDER, NOT DYNAMICALLY ASSIGNABLE, java.awt.Color off limits,
								//will implement different way for user to choose color once the rest is running properly

public class Category{

	String name; //CategoryID?
	int color = 0xFFABCDEF;	//this color is a cool-light blue
		//colors are defined in hexadecimal as AARRGGBB (A is alpha, all values are 0-255 -> 00-FF)

	public Category(){
		name = null;	//redundant?
	}
	public Category(String name){
		this.name = name;
	}
	public Category(String name, int color){
		this.name = name;
		this.color = color;
	}

	public String toString(){
	//	return "Category Name:\t" + name +
		//	"\nCategory Color:\t" + color;	//debugging
		return name;	//makes the use in spinner easier
	}

	public void setName(String name){
		this.name = name;
	}
	public void setColor(int color){
		this.color = color;
	}

	public String getName(){
		return name;
	}
	public int getColor(){
		return color;
	}
}//end Category class