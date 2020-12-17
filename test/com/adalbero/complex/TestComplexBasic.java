package com.adalbero.complex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestComplexBasic {
	@Test
	public void testString() {
		assertEquals("(0.0 + 0.0i)", new Complex(0, 0).toString());
		assertEquals("(1.0 + 0.0i)", new Complex(1, 0).toString());
		assertEquals("(0.0 + 1.0i)", new Complex(0, 1).toString());
		assertEquals("(1.0 + 2.0i)", new Complex(1, 2).toString());

		assertEquals("(0.0 + 0.0i)", new Complex(0).toString());
		assertEquals("(1.0 + 0.0i)", new Complex(1).toString());
		assertEquals("(2.0 + 0.0i)", new Complex(2).toString());

		assertEquals("(1.0 - 2.0i)", new Complex(1, -2).toString());
	}

	@Test
	public void testAdd() {
		assertEquals(new Complex(4, 6), new Complex(1, 2).add(new Complex(3, 4)));
		assertEquals(new Complex(4, 2), new Complex(1, 2).add(new Complex(3)));
	}

	@Test
	public void testSub() {
		assertEquals(new Complex(-2, -2), new Complex(1, 2).sub(new Complex(3, 4)));
		assertEquals(new Complex(0, 0), new Complex(1, 2).sub(new Complex(1, 2)));
		assertEquals(new Complex(1, 2), new Complex(3, 2).sub(new Complex(2)));
	}

	@Test
	public void testMult() {
		Complex i = new Complex(0 ,1);
		
		assertEquals(new Complex(4, 7), new Complex(2, 1).mult(new Complex(3, 2)));
		assertEquals(new Complex(0, 1), new Complex(1).mult(i));
		assertEquals(new Complex(-1, 0), new Complex(0, 1).mult(i));
		assertEquals(new Complex(0, -1), new Complex(-1, 0).mult(i));
		assertEquals(new Complex(1), new Complex(0, -1).mult(i));
	}

	@Test
	public void testDiv() {
		assertEquals(new Complex(3, 2), new Complex(4, 7).div(new Complex(2, 1)));
		assertEquals(new Complex(0, 1), new Complex(0, 1).div(new Complex(1)));
	}
}
