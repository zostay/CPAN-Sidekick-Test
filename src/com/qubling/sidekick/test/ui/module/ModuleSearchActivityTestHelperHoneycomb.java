package com.qubling.sidekick.test.ui.module;

import android.app.Instrumentation;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;

import com.jayway.android.robotium.solo.Solo;

public class ModuleSearchActivityTestHelperHoneycomb extends ModuleSearchActivityTestHelper {
    public ModuleSearchActivityTestHelperHoneycomb(Solo solo, Instrumentation instrumentation) {
        super(solo, instrumentation);
    }

    public void doSearch(String query) {
        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_SEARCH);
        
        KeyCharacterMap keymap = KeyCharacterMap.load(KeyCharacterMap.VIRTUAL_KEYBOARD);
        for (KeyEvent key : keymap.getEvents(query.toCharArray())) {
            getInstrumentation().sendKeySync(key);
        }
        
        getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_ENTER);
    }

}
