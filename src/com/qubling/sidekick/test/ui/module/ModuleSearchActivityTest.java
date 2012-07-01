package com.qubling.sidekick.test.ui.module;

import com.jayway.android.robotium.solo.Solo;
import com.qubling.sidekick.R;
import com.qubling.sidekick.instance.Module;
import com.qubling.sidekick.ui.module.ModuleSearchActivity;
import com.qubling.sidekick.ui.module.ModuleViewActivity;
import com.qubling.sidekick.ui.module.ModuleViewFragment;

import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;
import android.util.DisplayMetrics;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ModuleSearchActivityTest extends ActivityInstrumentationTestCase2<ModuleSearchActivity> {
    
	private Solo solo;
	
	public ModuleSearchActivityTest() {
		super("com.qubling.sidekick", ModuleSearchActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public boolean isPhone() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels / metrics.density < 600;
	}
	
	public boolean isHoneycomb() {
	    return Build.VERSION.SDK_INT >= 11;
	}
	
	public void testSearchTestMoreRobotium() throws Throwable {
	    
	    ModuleSearchActivityTestHelper helper = ModuleSearchActivityTestHelper.createInstance(solo, getInstrumentation());
	    helper.doSearch("Test::More");
        
//        Thread.sleep(10000);
	    getInstrumentation().waitForIdleSync();
        
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
	    
	    if (isPhone()) {
            
            assertTrue(solo.waitForActivity("ModuleViewActivity", 2000));
            
            ModuleViewActivity moduleViewActivity = (ModuleViewActivity) solo.getCurrentActivity();
            assertEquals(moduleViewActivity.getTitle(), "Test::More");
            
        }
        
        else {
             ModuleSearchActivity moduleSearchActivity = (ModuleSearchActivity) solo.getCurrentActivity();
             
             FragmentManager fragmentManager = moduleSearchActivity.getSupportFragmentManager();
             ModuleViewFragment moduleViewFragment = (ModuleViewFragment) fragmentManager.findFragmentById(R.id.module_view_fragment_container);

             assertNotNull(moduleViewFragment);
             
             assertEquals(moduleViewFragment.getModule().getName(), "Test::More");
	    }
	}

}
