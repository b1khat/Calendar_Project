package com.example.calendar;

//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;		//tut
import android.widget.Button;	//part of tut
import android.widget.LinearLayout;
import android.widget.TextView;	//tut

import android.widget.RelativeLayout;


public class DailyViewActivity extends Activity { //ActionBarActivity //CompatActivity

	RelativeLayout dailyLayout;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_view);
        
        dailyLayout = (RelativeLayout) findViewById(R.id.ScrollingLayout);
 
        displayEvents();
    }
    
    private void displayEvents(){
    	//possibly utilize clipping? clip_vertical in RelativeLayout api
    	//add a loop through all elements in teh given day
    	
    	Button b1 = new Button(this);
    	
    	RelativeLayout.LayoutParams b1params =  new RelativeLayout.LayoutParams(
													RelativeLayout.LayoutParams.MATCH_PARENT,
													RelativeLayout.LayoutParams.MATCH_PARENT);
       	
    	b1params.addRule(RelativeLayout.RIGHT_OF, R.id.hour3);
    	b1params.addRule(RelativeLayout.ALIGN_TOP, R.id.hour3);
    	b1params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.hour7);  //3 to 8
    	b1.setMinHeight(0);
    	b1.setPadding(2, 2, 2, 2); //tentative
    
    	b1.setText("HEY MAN THIS IS THE FIRST EVENT");
    	
    	b1.setLayoutParams(b1params);
    	
    	dailyLayout.addView(b1);
    }

    //can use android:onClick="methodName" instead of OnClickListeners, the method must take only a View
    //use android:minHeight="0dp" to override inherited min values in Buttons?
    //call finish(); somewhere? maybe not
}