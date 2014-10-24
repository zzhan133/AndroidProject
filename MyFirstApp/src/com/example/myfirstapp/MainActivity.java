package com.example.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	private ImageButton[] buttonArray;

	// false represent empty; true represents occupied
	// There is only one place to change the status; in function: sendMessage
	private Boolean colorStatus[] = { false, false, false, false, false,
			false, false, false, false, false };

	// 记录停车开始时间
	private long startTime[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

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

		for (int i = 0; i < 10; i++) {
			buttonArray[i] = (ImageButton) findViewById(resourceId[i]);
			textPriceView[i] = (TextView) findViewById(priceResourceId[i]);
		}

		showButtonColor();
	}

	private void showButtonColor() {
		int remaining = 10;
		for (int i = 0; i < 10; i++) {
			if (colorStatus[i]) {
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
		if (colorStatus[num - 1]) {
			colorStatus[num - 1] = false;
			startTime[num - 1] = 0;
		} else {
			colorStatus[num - 1] = true;
			startTime[num - 1] = SystemClock.uptimeMillis();
		}

		showButtonColor();

	}

	public void showTime(View view) {
		// ImageButton tmpButton = (ImageButton) view;

		// tmpButton.getBackground().setColorFilter(0xFFFF0000,
		// PorterDuff.Mode.MULTIPLY);
	}

	private void showPrice() {
		for (int i = 0; i < 10; i++) {
			if (colorStatus[i]) {
				long timeInMilliseconds = SystemClock.uptimeMillis()
						- startTime[i];
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
