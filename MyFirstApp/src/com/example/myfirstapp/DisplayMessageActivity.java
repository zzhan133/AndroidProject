package com.example.myfirstapp;


//import course.examples.Threading.ThreadingAsyncTask.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.PorterDuff;

public class DisplayMessageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_message);
		
		
		//Zhe Zhang add button showing
				final Button buttonOne = (Button) findViewById(R.id.slotOne);
				buttonOne.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
//				final Button buttonTwo = (Button) findViewById(R.id.slotTwo);
//				buttonTwo.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
				final Button buttonThree = (Button) findViewById(R.id.slotThree);
				buttonThree.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
//				final Button buttonFour = (Button) findViewById(R.id.slotFour);
//				buttonFour.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
				final Button buttonFive = (Button) findViewById(R.id.slotFive);
				buttonFive.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
//				final Button buttonSix = (Button) findViewById(R.id.slotSix);
//				buttonSix.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
		
		
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		
		int num = Integer.parseInt(message);
		
		Button tmp = buttonOne;
		switch(num){
		case 1: tmp = buttonOne;
			break;
//		case 2: tmp = buttonTwo;
//			break;
		case 3: tmp = buttonThree;
			break;
//		case 4: tmp = buttonFour;
//			break;
		case 5: tmp = buttonFive;
			break;
//		case 6: tmp = buttonSix;
//			break;
		default:
			break;
		}
		
		tmp.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
		
//		TextView textView = new TextView(this);
//		textView.setTextSize(40);
//		textView.setText(message);
		//Set the text view as the activity layout
//		setContentView(textView);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_message, menu);
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
		else if(id == R.id.action_search){
			openSearch();
			return true;
		}
		else
			return super.onOptionsItemSelected(item);
	}

	private void openSearch() {
		// TODO Auto-generated method stub
		
	}
	
	public void sendMessage(View view){
		
	}
}
