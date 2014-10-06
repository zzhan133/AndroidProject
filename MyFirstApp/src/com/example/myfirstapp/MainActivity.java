package com.example.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.PorterDuff;

public class MainActivity extends Activity {

	public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
	private Button[] buttonArray;
	
	//false represent empty; true represents occupied
	private static Boolean colorStatus[] = {false, false, false, false, false, false, false, false, false, false};
	
	//记录停车开始时间
	private long startTime[] = {0,0,0,0,0,0,0,0,0,0};
	
	private static int resourceId[] = {R.id.slotOne, R.id.slotTwo,
			R.id.slotThree,R.id.slotFour,R.id.slotFive,R.id.slotSix,R.id.slotSeven,R.id.slotEight,R.id.slotNine,R.id.slotTen};
	
	private static int priceResourceId[] = {R.id.priceOne, R.id.priceTwo, R.id.priceThree,R.id.priceFour,R.id.priceFive
				,R.id.priceSix,R.id.priceSeven,R.id.priceEight,R.id.priceNine,R.id.priceTen};
	
	
	private TextView[] textPriceView;
	
	private TextView textView;
	
//	private Calendar parkingTime[10] = {02};
	
	//add price area by zz 2014年10月25:17	
	private Handler priceShowingHandler = new Handler();
	//end by zz 2014年10月2日16:25:53
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView = (TextView)findViewById(R.id.remainingSlots);
		
		buttonArray = new Button[10];
		textPriceView = new TextView[10];
		
		for(int i = 0; i < 10; i++){
			buttonArray[i] = (Button) findViewById(resourceId[i]);
			textPriceView[i] = (TextView) findViewById(priceResourceId[i]);
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
				//buttonArray[i].setBackgroundColor(Color.GREEN);
				buttonArray[i].getBackground().setAlpha(65);
			}
		}
		
		//Context context = new Context();
		
		String str = Integer.toString(remaining);
		textView.setTextSize(40);
		//Set the text view as the activity layout
		textView.setText(str);
		
		priceShowingHandler.postDelayed(updatePrice, 0);
		
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
		if(!(num > 0 && num < 10)){
			return;
		}
		if(colorStatus[num-1])
		{
			colorStatus[num-1] = false;
			startTime[num-1] = 0;
		}
		else{
			colorStatus[num-1] = true;
			startTime[num-1] = SystemClock.uptimeMillis();
		}
			
		showButtonColor();
		
	}
	
	public void showTime(View view){
		Button tmpButton = (Button) view;
		
		tmpButton.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);		
	}
	
	
	private void showPrice(){
		for(int i = 0; i < 10; i++){
			if(colorStatus[i]){
				long timeInMilliseconds = SystemClock.uptimeMillis() - startTime[i];
				int secs = (int) (timeInMilliseconds / 1000);
				int mins = secs / 60;
				String str = Integer.toString(mins);
				textPriceView[i].setTextSize(40);
				//Set the text view as the activity layout
				textPriceView[i].setText(str);
			}
		}
	}
	
	
	private Runnable updatePrice = new Runnable() {

		public void run() {
			showPrice();
			priceShowingHandler.postDelayed(this, 1000);
		}

	};
	
	
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
