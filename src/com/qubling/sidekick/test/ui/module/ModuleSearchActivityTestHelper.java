package com.qubling.sidekick.test.ui.module;

import android.app.Instrumentation;
import android.os.Build;

import com.jayway.android.robotium.solo.Solo;

public abstract class ModuleSearchActivityTestHelper {
    public static ModuleSearchActivityTestHelper createInstance(Solo solo, Instrumentation instrumentation) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return new ModuleSearchActivityTestHelperHoneycomb(solo, instrumentation);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_MR1) {
            return new ModuleSearchActivityTestHelperEclair(solo, instrumentation);
        } else {
            throw new RuntimeException("CPAN Sidekick does not support versions of Android before 2.1.");
        }
    }
    
    private Solo solo;
    private Instrumentation instrumentation;
    
    public ModuleSearchActivityTestHelper(Solo solo, Instrumentation instrumentation) {
        this.solo = solo;
        this.instrumentation = instrumentation;
    }
    
    public Solo getSolo() {
        return solo;
    }
    
    public Instrumentation getInstrumentation() {
        return instrumentation;
    }
    
    public abstract void doSearch(String query);
}
