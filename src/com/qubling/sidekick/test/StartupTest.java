package com.qubling.sidekick.test;

import android.test.ActivityInstrumentationTestCase2;

import com.qubling.sidekick.SidekickLauncher;

public class StartupTest extends ActivityInstrumentationTestCase2<SidekickLauncher> {

	public StartupTest() {
		super("com.qubling.sidekick", SidekickLauncher.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testOkay() {
		assertTrue(true);
	}
}
