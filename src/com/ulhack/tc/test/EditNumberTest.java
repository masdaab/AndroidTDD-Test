package com.ulhack.tc.test;

import com.ulhack.tc.EditNumber;

import android.test.AndroidTestCase;

public class EditNumberTest extends AndroidTestCase {
	private EditNumber mEditNumber;
	public EditNumberTest(String name) {
		setName(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		mEditNumber = new EditNumber(mContext);
		mEditNumber.setFocusable(true);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testEditNumberContext() {
		assertNotNull(mEditNumber);
	}

	public final void testClear() {
		final String value = "123.45";
		mEditNumber.setText(value);
		mEditNumber.clear();
		final String expected = "";
		final String actual = mEditNumber.getText().toString();
		assertEquals(expected, actual);
	}

	public final void testSetNumber() {
		final double d = 123.45;
		mEditNumber.setNumber(d);
		final String expected = Double.toString(d);
		final String actual = mEditNumber.getText().toString();
		assertEquals(expected, actual);
	}
	public final void testGetNumber() {
		final double expected = 123.45;
		mEditNumber.setNumber(expected);
		final double actual = mEditNumber.getNumber();
		assertEquals(expected, actual);
	}

}
