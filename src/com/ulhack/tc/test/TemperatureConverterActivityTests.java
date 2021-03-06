package com.ulhack.tc.test;

import com.ulhack.tc.EditNumber;
import com.ulhack.tc.TemperatureConverter;
import com.ulhack.tc.TemperatureConverterActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.Gravity;
import android.view.View;

public class TemperatureConverterActivityTests extends
		ActivityInstrumentationTestCase2<TemperatureConverterActivity> {

	private TemperatureConverterActivity mActivity;
	private EditNumber mCelsius;
	private EditNumber mFahrenheit;

	public TemperatureConverterActivityTests(String name) {
		super(TemperatureConverterActivity.class);
		setName(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		mActivity = getActivity();
		assertNotNull(mActivity);
		
		mCelsius = (EditNumber)mActivity.findViewById(com.ulhack.tc.R.id.celsius);
		assertNotNull(mCelsius);
		mFahrenheit = (EditNumber)mActivity.findViewById(com.ulhack.tc.R.id.fahrenheit);
		assertNotNull(mFahrenheit);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@SmallTest
	public void testFieldsOnScreen() {
	    final View origin = 
            mActivity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(origin, mCelsius);
	    ViewAsserts.assertOnScreen(origin, mFahrenheit);
	}
	
	@SmallTest
	public void testAlignment() {
		ViewAsserts.assertRightAligned(mCelsius, mFahrenheit);
		ViewAsserts.assertLeftAligned(mCelsius, mFahrenheit);
	}
	
	@SmallTest
	public void testFieldsShouldStartEmpty() {
		assertTrue("".equals(mCelsius.getText().toString()));
		assertTrue("".equals(mFahrenheit.getText().toString()));
	}
	
	@SmallTest
	public void testJustification() {
		final int expected = Gravity.RIGHT|Gravity.CENTER_VERTICAL;
		assertEquals(expected, mCelsius.getGravity());
		assertEquals(expected, mFahrenheit.getGravity());
	}
	
	@UiThreadTest
	public void testFahrenheitToCelsiusConversion() {
		mCelsius.clear();
		mFahrenheit.clear();
		final double f = 32.5;
		mFahrenheit.requestFocus();
		mFahrenheit.setNumber(f);
		mCelsius.requestFocus();
		final double expected = TemperatureConverter.fahrenheitToCelsius(f);
		final double actual = mCelsius.getNumber();
		final double delta = Math.abs(expected - actual);
		 assertTrue("delta=" + delta, delta < 0.005);
	}
	
	@UiThreadTest
	public void testCelsiusToFahrenheitConversion() {
		mCelsius.clear();
		mFahrenheit.clear();
		final double c = 32.5;
		mCelsius.requestFocus();
		mCelsius.setNumber(c);
		mFahrenheit.requestFocus();
		final double expected = TemperatureConverter.celsiusToFahrenheit(c);
		final double actual = mFahrenheit.getNumber();
		final double delta = Math.abs(expected - actual);
		assertTrue("delta=" + delta, delta < 0.005);
	}
}
