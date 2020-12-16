package com.adalbero.complex;

public class Complex {
	public static final double PRECISION = 0.1;

	public static final Complex ZERO = new Complex(0, 0);
	public static final Complex ONE = new Complex(1);
	public static final Complex I = new Complex(0, 1);

	private double re;
	private double im;

	public Complex(double a, double b) {
		this.re = a;
		this.im = b;
	}

	public Complex(double a) {
		this(a, 0);
	}

	public Complex(Complex z) {
		this(z.re, z.im);
	}

	public double getRe() {
		return this.re;
	}

	public double getIm() {
		return this.im;
	}

	public Complex add(Complex z) {
		double a = this.re;
		double b = this.im;
		double c = z.re;
		double d = z.im;

		double u = a + c;
		double v = b + d;

		return new Complex(u, v);
	}

	public Complex add(double x) {
		double a = this.re;
		double b = this.im;

		double u = a + x;
		double v = b;

		return new Complex(u, v);
	}

	public Complex sub(Complex z) {
		double a = this.re;
		double b = this.im;
		double c = z.re;
		double d = z.im;

		double u = a - c;
		double v = b - d;

		return new Complex(u, v);
	}

	public Complex sub(double x) {
		double a = this.re;
		double b = this.im;

		double u = a - x;
		double v = b;

		return new Complex(u, v);
	}

	public Complex mult(Complex z) {
		double a = this.re;
		double b = this.im;
		double c = z.re;
		double d = z.im;

		double u = (a * c - b * d);
		double v = (a * d + b * c);

		return new Complex(u, v);
	}

	public Complex div(Complex z) {
		double a = this.re;
		double b = this.im;
		double c = z.re;
		double d = z.im;

		double u = (a * c + b * d) / (c * c + d * d);
		double v = (b * c - a * d) / (c * c + d * d);

		return new Complex(u, v);
	}

	public double mod() {
		double a = this.re;
		double b = this.im;

		return Math.sqrt(a * a + b * b);
	}

	public Complex conj() {
		double a = this.re;
		double b = -this.im;

		return new Complex(a, b);
	}

	public double arg() {
		double a = this.re;
		double b = this.im;

		double r = this.mod();

		if (a == 0 && b == 0) {
			return Double.NaN;
		} else if (a < 0 && b == 0) {
			return Math.PI;
		} else {
			return Math.atan2(b, a);
		}
	}

	@Override
	public String toString() {
		double a = this.re;
		double b = this.im;
		String sign = (b < 0 ? "-" : "+");

		return String.format("(%.1f %s %.1fi)", a, sign, Math.abs(b));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Complex && obj != null) {
			Complex z = (Complex) obj;
			double a = this.re;
			double b = this.im;
			double c = z.re;
			double d = z.im;

			return eq(a, c) && eq(b, d);
		} else {
			return false;
		}
	}

	static boolean eq(double x, double y) {
		return Math.abs(x - y) <= PRECISION;
	}

	static Complex[] rootsOfQuadratic(double a, double b, double c) {
		Complex[] roots = new Complex[2];

		double delta = b * b - 4 * a * c;

		if (delta >= 0) {
			roots[0] = new Complex((-b + Math.sqrt(delta)) / (2 * a));
			roots[1] = new Complex((-b - Math.sqrt(delta)) / (2 * a));
		} else {
			roots[0] = new Complex(-b / (2 * a), +Math.sqrt(-delta) / (2 * a));
			roots[1] = new Complex(-b / (2 * a), -Math.sqrt(-delta) / (2 * a));
		}

		return roots;
	}
}
