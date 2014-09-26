package com.example.myfirstapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.PorterDuff;

public class MainActivity extends Activity {

	public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
	private Button[] buttonArray;
	
	//false represent empty; true represents occupied
	private static Boolean colorStatus[] = {false, false, false, false, false, false, false, false, false, false};
	
	private static int resourceId[] = {R.id.slotOne, R.id.slotTwo,
			R.id.slotThree,R.id.slotFour,R.id.slotFive,R.id.slotSix,R.id.slotSeven,R.id.slotEight,R.id.slotNine,R.id.slotTen};
	
	private TextView textView;
	
//	private Calendar parkingTime[10] = {0,0,0,0,0,0,0,0,0,0};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		buttonArray = new Button[10];
		
		for(int i = 0; i < 10; i++){
			buttonArray[i] = (Button) findViewById(resourceId[i]);
		}
		
		showButtonColor();
	}
	
	private void showButtonColor(){
		int remaining = 10;
		for(int i = 0; i < 10; i++){
			if(colorStatus[i]){
				buttonArray[i].setBackgroundColor(Color.RED);
				remaining--;
			}
			else{
				buttonArray[i].setBackgroundColor(Color.GREEN);
			}
		}
		
		//Context context = new Context();
		textView = (TextView)findViewById(R.id.remainingSlots);
		String str = Integer.toString(remaining);
		textView.setTextSize(40);
		//Set the text view as the activity layout
		textView.setText(str);
		textView.setGravity(Gravity.CENTER_HORIZONTAL);
		
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
	
	/**Called when the user clicks the Send button */
	public void sendMessage(View view){
		//Do something in response to button
		//Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText)findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		//intent.putExtra(EXTRA_MESSAGE, message);
		//startActivity(intent);
		
		int num = Integer.parseInt(message);
		colorStatus[num-1] = true;
		showButtonColor();
		
	}
	
	public void changeColor(View view){
		Button tmpButton = (Button) view;
		tmpButton.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);		
	}
}
