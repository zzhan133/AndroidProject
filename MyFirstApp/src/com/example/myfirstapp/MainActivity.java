package com.example.myfirstapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.example.myfirstapp.android.IntentIntegrator;
import com.example.myfirstapp.android.IntentResult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	
	private static final String FILE_NAME = "ParkingInfo.txt";

	public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
	private static final String TAG = "ScanResult"; 
	
	static final int SLOT_NUM = 10;
	
	//mData is used to record the parking data
	private final List<ParkingItem> mItems = new ArrayList<ParkingItem>();
	

	private ImageButton[] buttonArray;

	private static int resourceId[] = { R.id.slotOne, R.id.slotTwo,
			R.id.slotThree, R.id.slotFour, R.id.slotFive, R.id.slotSix,
			R.id.slotSeven, R.id.slotEight, R.id.slotNine, R.id.slotTen };

	private static int priceResourceId[] = { R.id.price_one, R.id.price_two,
			R.id.price_three, R.id.price_four, R.id.price_five, R.id.price_six,
			R.id.price_seven, R.id.price_eight, R.id.price_nine, R.id.price_ten };

	private TextView[] textPriceView;

	private TextView textView;

	// private Calendar parkingTime[10] = {02};

	// add price area by zz 2014年10月25:17
	private Handler priceShowingHandler = new Handler();

	// end by zz 2014年10月2日16:25:53

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView = (TextView) findViewById(R.id.remainingSlots);

		buttonArray = new ImageButton[10];
		textPriceView = new TextView[10];
		
		for (int i = 0; i < SLOT_NUM; i++) {
			mItems.add(new ParkingItem(resourceId[i], priceResourceId[i]));
			buttonArray[i] = (ImageButton) findViewById(resourceId[i]);
			textPriceView[i] = (TextView) findViewById(priceResourceId[i]);
		}

		saveItems();
	}

	private void showButtonColor() {
		int remaining = SLOT_NUM;
		for (int i = 0; i < 10; i++) {
			if (mItems.get(i).getbStatus() == true) {
				buttonArray[i].setVisibility(View.VISIBLE); // 0 represent
															// "visible"
				remaining--;
			} else {
				// buttonArray[i].setBackgroundColor(Color.GREEN);
				buttonArray[i].setVisibility(View.INVISIBLE); // 1 represent
																// "invisible"
			}
		}

		// Context context = new Context();

		String str = Integer.toString(remaining);
		// Set the text view as the activity layout
		textView.setText(str);

		priceShowingHandler.postDelayed(updatePrice, 0);

	}

	/** Called when the user clicks the Send ImageButton */
	public void sendMessage(View view) {
		// Do something in response to ImageButton
		// Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		// intent.putExtra(EXTRA_MESSAGE, message);
		// startActivity(intent);

		int num;

		try {
			num = Integer.parseInt(message);
		} catch (NumberFormatException e) {
			return;
		}

		if (!(num > 0 && num < 10)) {
			return;
		}
		changeSlotState(num);
	}
	public void changeSlotState(int num){
		num -= 1;
		if (mItems.get(num).getbStatus() == true) {
			mItems.get(num).setbStatus(false);
			mItems.get(num).setParkingStartTime(0);
		} else {
			mItems.get(num).setbStatus(true);
			mItems.get(num).setParkingStartTime(SystemClock.uptimeMillis());
		}

		saveItems();
		showButtonColor();
		
	}

	public void showTime(View view) {
		// ImageButton tmpButton = (ImageButton) view;

		// tmpButton.getBackground().setColorFilter(0xFFFF0000,
		// PorterDuff.Mode.MULTIPLY);
	}
	
	public void scanCode(View view){
		IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
		integrator.initiateScan();
	}
	
	@Override
	  public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
	    if (result != null) {
	      String contents = result.getContents();
//	      Log.i(TAG, contents); 
	      if (contents != null) {
	        if(contents.equals("slotOne")){
	        	changeSlotState(1);
	        }
	        else if(contents.equals("slotTwo")){
	        	changeSlotState(2);
	        }
	        else if(contents.equals("slotThree")){
	        	changeSlotState(3);
	        }
	        else if(contents.equals("slotFour")){
	        	changeSlotState(4);
	        }
	        else if(contents.equals("slotFive")){
	        	changeSlotState(5);
	        }
	        else if(contents.equals("slotSix")){
	        	changeSlotState(6);
	        }
	        else if(contents.equals("slotSeven")){
	        	changeSlotState(7);
	        }
	        else if(contents.equals("slotEight")){
	        	changeSlotState(8);
	        }
	        else if(contents.equals("slotNine")){
	        	changeSlotState(9);
	        }
	        else if(contents.equals("slotTen")){
	        	changeSlotState(10);
	        }
	        else
	        	return;
	      } else {
	    	  return;
	      }
	    }
	  }
	

	private void showPrice() {
		for (int i = 0; i < 10; i++) {
			if (mItems.get(i).getbStatus()) {
				long timeInMilliseconds = SystemClock.uptimeMillis()
						- mItems.get(i).getParkingStartTime();
				int secs = (int) (timeInMilliseconds / 1000);
				int mins = secs / 60;
				String str = Integer.toString(mins);
				str = "￥" + str + ".0";
				// Set the text view as the activity layout
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
	protected void onPause() {
		super.onPause();

		// Save ToDoItems

		saveItems();

	}
	
	
	public void saveItems(){
		PrintWriter writer = null;
		try {
			FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					fos)));

			for (int idx = 0; idx < mItems.size(); idx++) {

				writer.println(mItems.get(idx));

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != writer) {
				writer.close();
			}
		}
	}
	
	
	public void onResume() {
		super.onResume();

		// Load saved ToDoItems, if necessary

		loadItems();
		showButtonColor();
	}
	
	public void loadItems(){
		BufferedReader reader = null;
		try {
			FileInputStream fis = openFileInput(FILE_NAME);
			reader = new BufferedReader(new InputStreamReader(fis));

			String strStatus = null;
			String strTime = null;
			
			for (int idx = 0; idx < mItems.size() && null != (strStatus = reader.readLine()); idx++) {
				mItems.get(idx).setbStatus(Boolean.parseBoolean(strStatus));
				strTime = reader.readLine();
				mItems.get(idx).setParkingStartTime(Long.parseLong(strTime));				
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		 }finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
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
		// automatically handle clicks on the Home/Up ImageButton, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
