package com.qubling.sidekick.test.ui.module;

import com.qubling.sidekick.R;
import com.qubling.sidekick.ui.module.ModuleSearchActivity;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class ModuleSearchActivityTest extends ActivityInstrumentationTestCase2<ModuleSearchActivity> {

	private Activity activity;
	private EditText textSearch; // TODO Need to support the search view in the action bar too
	private ImageButton buttonSearch;
	private ListView listSearchResults;
	
	public ModuleSearchActivityTest() {
		super("com.qubling.sidekick", ModuleSearchActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		setActivityInitialTouchMode(false);
		
		activity = getActivity();
		
		textSearch = (EditText) activity.findViewById(R.id.text_search);
		buttonSearch = (ImageButton) activity.findViewById(R.id.button_search);
		listSearchResults = (ListView) activity.findViewById(R.id.list_search_results);
	}
	
	public void testSearchTestMore() throws Throwable {
		runTestOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				textSearch.setText("Test::More");
				buttonSearch.performClick();
			}
		});
		
		Thread.sleep(10000);
		
		runTestOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				assertTrue(listSearchResults.getAdapter().getCount() > 10);
			}
		});
	}

}
