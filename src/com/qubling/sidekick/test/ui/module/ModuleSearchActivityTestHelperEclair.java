package com.qubling.sidekick.test.ui.module;

import android.app.Instrumentation;

import com.jayway.android.robotium.solo.Solo;

public class ModuleSearchActivityTestHelperEclair extends ModuleSearchActivityTestHelper {
    public ModuleSearchActivityTestHelperEclair(Solo solo, Instrumentation instrumentation) {
        super(solo, instrumentation);
    }
    
    public void doSearch(String query) {
        getSolo().enterText(0, "Test::More");
        getSolo().clickOnImageButton(0);
    }
}
