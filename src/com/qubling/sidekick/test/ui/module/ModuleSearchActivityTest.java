package com.qubling.sidekick.test.ui.module;

import com.jayway.android.robotium.solo.Solo;
import com.qubling.sidekick.R;
import com.qubling.sidekick.instance.Module;
import com.qubling.sidekick.ui.module.ModuleSearchActivity;
import com.qubling.sidekick.ui.module.ModuleViewActivity;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ModuleSearchActivityTest extends ActivityInstrumentationTestCase2<ModuleSearchActivity> {

	private Activity activity;
	private EditText textSearch; // TODO Need to support the search view in the action bar too
	private ImageButton buttonSearch;
	private ListView listSearchResults;
	
	private Solo solo;
	
	public ModuleSearchActivityTest() {
		super("com.qubling.sidekick", ModuleSearchActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		solo = new Solo(getInstrumentation(), getActivity());
		
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
	
	public void testSearchTestMoreRobotium() throws Throwable {
	    solo.enterText(0, "Test::More");
	    solo.clickOnImageButton(0);
	    
	    Thread.sleep(10000);
	    
	    ListView listSearchResults = (ListView) solo.getView(R.id.list_search_results);
	    ListAdapter searchResultsAdapter = listSearchResults.getAdapter();
	    assertTrue(searchResultsAdapter.getCount() > 10);
	    
	    Module testMoreModule = null;
	    int testMoreIndex = -1;
	    
	    for (int i = 0; i < searchResultsAdapter.getCount(); i++) {
	        Object o = searchResultsAdapter.getItem(i);
	        
	        if (i + 1 == searchResultsAdapter.getCount()) {
	            assertNull(o);
	        }
	        else {
	            assertTrue(o instanceof Module);
	            
	            Module m = (Module) o;
	            if ("Test::More".equals(m.getName())) {
	                testMoreModule = m;
	                testMoreIndex = i;
	            }
	        }
	    }
        
	    assertTrue(testMoreIndex >= 0);
        assertNotNull(testMoreModule);
        
        solo.clickInList(testMoreIndex);
        
        assertTrue(solo.waitForActivity("ModuleViewActivity", 2000));
        
        ModuleViewActivity moduleViewActivity = (ModuleViewActivity) solo.getCurrentActivity();
        assertEquals(moduleViewActivity.getTitle(), "Test::More");
	}

}
