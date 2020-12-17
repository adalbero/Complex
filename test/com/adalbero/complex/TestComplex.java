package com.adalbero.complex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestComplex {

	@Test
	public void testEq() {
		assertTrue(Complex.eq(0, 0));
		assertTrue(Complex.eq(1, 1));
		assertTrue(Complex.eq(1.2, 1.2));
		assertTrue(Complex.eq(1.20, 1.21));
	}

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
	public void testConstants() {
		assertEquals(new Complex(0, 0), Complex.ZERO);
		assertEquals(new Complex(1, 0), Complex.ONE);
		assertEquals(new Complex(0, 1), Complex.I);
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
		assertEquals(new Complex(4, 7), new Complex(2, 1).mult(new Complex(3, 2)));
		assertEquals(new Complex(0, 1), new Complex(1).mult(Complex.I));
		assertEquals(new Complex(-1, 0), new Complex(0, 1).mult(Complex.I));
		assertEquals(new Complex(0, -1), new Complex(-1, 0).mult(Complex.I));
		assertEquals(new Complex(1), new Complex(0, -1).mult(Complex.I));
		
		assertEquals(new Complex(2, 2), new Complex(1, 1).mult(2));
	}

	@Test
	public void testDiv() {
		assertEquals(new Complex(3, 2), new Complex(4, 7).div(new Complex(2, 1)));
		assertEquals(new Complex(0, 1), new Complex(0, 1).div(new Complex(1)));
	}

	@Test
	public void testMod() {
		assertEquals(5.0, new Complex(3, 4).mod(), Complex.PRECISION);
	}

	@Test
	public void testPolar() {
		Complex z1, z2, z3;
		
		assertEquals(Complex.I, Complex.polar(1, Math.PI/2));
		
		z1 = new Complex(2, 1);
		z2 = new Complex(3, 2);
		z3 = z1.mult(z2);
		
		assertEquals(z3.mod(), z1.mod() * z2.mod(), Complex.PRECISION);
		assertEquals(z3.arg(), z1.arg() + z2.arg(), Complex.PRECISION);

		z1 = new Complex(4, 7);
		z2 = new Complex(2, 1);
		z3 = z1.div(z2);
		
		assertEquals(z3.mod(), z1.mod() / z2.mod(), Complex.PRECISION);
		assertEquals(z3.arg(), z1.arg() - z2.arg(), Complex.PRECISION);
	}
	@Test
	public void testArg() {
		assertEquals(0, new Complex(1, 0).arg(), Complex.PRECISION);
		assertEquals(Math.PI / 4, new Complex(1, 1).arg(), Complex.PRECISION);
		assertEquals(Math.PI / 2, new Complex(0, 1).arg(), Complex.PRECISION);
		assertEquals(Math.PI, new Complex(-1, 0).arg(), Complex.PRECISION);
		assertEquals(-Math.PI / 2, new Complex(0, -1).arg(), Complex.PRECISION);
		assertEquals(Double.NaN, new Complex(0, 0).arg(), Complex.PRECISION);
	}
	
	@Test
	public void testPow() {
		assertEquals(new Complex(-1), Complex.I.pow(2));
		
		assertTrue(Complex.I.pow(2).equals(-1));
	}
	
	@Test
	public void testSqrt() {
		assertEquals(Complex.I, new Complex(-1).sqrt());
		assertEquals(Complex.I.sqrt(), Complex.I.pow(0.5));
	}

	@Test
	public void testExp() {
		assertEquals(new Complex(-1, 0), Complex.I.mult(Complex.PI).exp());
		
		Complex z1 = new Complex(2, 1);
		Complex z2 = new Complex(3, 1);
		
		assertEquals(z1.add(z2).exp(), z1.exp().mult(z2.exp()));
	}
	
	@Test
	public void testLog() {
		Complex z = new Complex(2, 1);
		
		assertEquals(z, z.log().exp());
	}
	
	@Test
	public void testRoots() {
		Complex[] roots;

		// x^2 + 1 = 0 -> i and -i
		roots = Complex.rootsOfQuadratic(1, 0, 1);
		assertEquals(Complex.I, roots[0]);
		assertEquals(Complex.I.conj(), roots[1]);

		// x^2 - 5x + 6 = 0 -> 2 and 3
		roots = Complex.rootsOfQuadratic(1, -5, 6);
		assertEquals(new Complex(3), roots[0]);
		assertEquals(new Complex(2), roots[1]);

		// x^2 + x + 1 = 0 -> (-1/2 + sqrt(3)/2 i) and (-1/2 -sqrt(3)/2 i)
		roots = Complex.rootsOfQuadratic(1, 1, 1);
		assertEquals(new Complex(-0.5, Math.sqrt(3) / 2), roots[0]);
		assertEquals(new Complex(-0.5, -Math.sqrt(3) / 2), roots[1]);

		// x^2 - x - 1 = 0 -> (1/2 + sqrt(5)/2 i) and (1/2 -sqrt(5)/2 i)
		roots = Complex.rootsOfQuadratic(1, -1, -1);
		assertEquals(new Complex(0.5 + Math.sqrt(5) / 2), roots[0]);
		assertEquals(new Complex(0.5 - Math.sqrt(5) / 2), roots[1]);
	}
}
