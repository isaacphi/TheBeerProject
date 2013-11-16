package com.example.thebeerproject;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.thebeerproject.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu item for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	// Handle action bar button presses
	/*
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items 
		switch (item.getItemId()) {
		case R.id.action_search:
			openSearch();
			return true;
		case R.id.action_settings:
			openSettings();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	*/
	
	// Called when someone clicks the Send button: 
	// First check Internet connection and navigate user to settings if it's not available
	// Otherwise get message contents and put in an intent -> open new activity
	public void sendMessage(View view) {
		
		ConnectivityManager connMgr = (ConnectivityManager) 
		        getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		    if (networkInfo != null && networkInfo.isConnected()) {
		        // fetch data from LCBOapi, open new activity with intent containing message string
		    	Intent intent = new Intent(this, DisplayMessageActivity.class);
				EditText editText = (EditText) findViewById(R.id.edit_message);
				String message = editText.getText().toString();
				intent.putExtra(EXTRA_MESSAGE, message);
				startActivity(intent);
				
		    } else {
		        // open settings, allow user to connect
		    	DialogFragment networkDialog = new NetworkConnectDialogFragment();
		    	networkDialog.show(getFragmentManager(), "network");
//		    	startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
		    }
	}
}
