package com.example.calendar;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;	//for starting new activities
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;		//tut
import android.widget.Button;	//part of tut
import android.widget.TextView;	//tut
//import android.graphics.Color;
import java.awt.Color;

public class MainActivity extends ActionBarActivity {

	private int counter;	//vars part of tutorial
	Button add, sub;		
	TextView display;		
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // res/layout/activity_main.xml
    
        setupTutorialButtons();
        setupTestFixture();
        setupNavigationButton();
    }

    
    private void setupTutorialButtons(){
        counter = 0;
        add = (Button) findViewById(R.id.buttonAdd);	//Button is subclass of TextView
        sub = (Button) findViewById(R.id.buttonSub);	//Typecasting View to Button
        display = (TextView) findViewById(R.id.tvDisplay);
        add.setOnClickListener(new View.OnClickListener(){
       		public void onClick(View v){
       			counter++;
       			display.setText("Your total is " + counter);
        	}
        });
        sub.setOnClickListener(new View.OnClickListener(){
       		public void onClick(View v){
       			counter--;
       			display.setText("Your total is " + counter);
        	}
        });
    }
    
    private void setupNavigationButton(){
    	Button toDailyView = (Button) findViewById(R.id.buttonToDailyView);
    	toDailyView.setOnClickListener(new View.OnClickListener(){
    		@Override
    		public void onClick(View v){
    			startActivity(new Intent(MainActivity.this, DailyViewActivity.class));
    			//Learned from https://www.youtube.com/watch?v=hB3AqKy8QME
    		}
    	});
    }
    
    private void setupTestFixture(){
    	Category defaultCat = new Category("Default Category");
    	EventManager.addEvent(new Event("Event 1", new Date(104, 0, 1, 15, 45), new Date(104, 0, 1, 17,45), defaultCat ));
    	EventManager.addEvent(new Event("Birthday", new Date(1995-1900, 6, 27, 7, 30), new Date(1995-1900, 6, 27, 8, 42), defaultCat));
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
