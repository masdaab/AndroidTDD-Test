package com.ulhack.tc.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.ulhack.tc.TemperatureConverter;

import junit.framework.TestCase;

public class TemperatureConvetertTest extends TestCase {


	private static final HashMap<Double, Double> sConversionTableDouble =
			new HashMap<Double, Double>();
	
	static {
		sConversionTableDouble.put(0.0, 32.0);
		sConversionTableDouble.put(100.0, 212.0);
		sConversionTableDouble.put(-1.0, 30.20);
		sConversionTableDouble.put(-100.0, -148.0);
		sConversionTableDouble.put(32.0, 89.60);
		sConversionTableDouble.put(-40.0, -40.0);
		sConversionTableDouble.put(-273.0, -459.40);
	}

	public TemperatureConvetertTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFahrenheitToCelsius() {
		for (double c: sConversionTableDouble.keySet()) {
			final double f = sConversionTableDouble.get(c);
			final double ca = TemperatureConverter.fahrenheitToCelsius(f);
			final double delta = Math.abs(ca - c);
			assertTrue("delta=" + delta + " for c=" + c + " ca=" + ca, delta < 0.005);
		}
	}

	public void testCelsiusToFahrenheit() {
		for (double c: sConversionTableDouble.keySet()) {
			final double f = sConversionTableDouble.get(c);
			final double fa = TemperatureConverter.celsiusToFahrenheit(c);
			final double delta = Math.abs(fa - f);
			assertTrue("delta=" + delta + " for f=" + f + " fa=" + fa, delta < 0.005);
		}
	}
	
	public final void testPrivateConstructor() throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, InstantiationException, IllegalAccessException,
			InvocationTargetException {
		Constructor<TemperatureConverter> ctor =
			TemperatureConverter.class.getDeclaredConstructor();
		ctor.setAccessible(true);
		TemperatureConverter tc = ctor.newInstance((Object[])null);
		assertNotNull(tc);
	}

}
