package numericalMethods;

import static java.lang.Math.exp;
import static java.lang.Math.log;

/**
 * A class to find polynomial functions that best estimate an exponential
 * function, given the upper and lower boundaries. To use this class, tweak the
 * variable a1 until you find a polynomial function that best represents the
 * exponential function.
 * 
 * @author: Minh Ta
 * @class: MATH440 - Numerical Methods
 *
 */

public class MyApprox {
	public static void main(String[] args) {
		/*
		 * Estimate a2 based on a1 to pin the start and end points to the actual start
		 * and end points of the exponential function
		 */
//		Uncomment to use coefficient approximation
//		double a1 = -0.96966999;
//		double a2 = (-0.5 / log(2) - a1) / log(2);
//
//		double[] result = testApprox(new RealPolynomial(new double[] { 1, a1, a2 }), -1, 0, log(2), 1000);
//		System.out.printf("Max diff=%.10f, at x=%.10f, a1=%.10f, a2=%.10f", result[0], result[1], a1, a2);
		
//		Interpolating points approximation
//		double[] x = {0.0166, 0.1402, 0.34259, 0.5476, 0.6758};
//		double[] y = {exp(-x[0]), exp(-x[1]), exp(-x[2]), exp(-x[3]), exp(-x[4])};
//		double[] result = testApprox(x, y, -1, 0, log(2), 100);
//		System.out.printf("Max diff=%.10f, at x=%.10f", result[0], result[1]);
		
		// Lab11 ex2
//		double[] x = {10, 13, 16, 19, 22};
//		double[] y = {7.616, 8.617, 9.217, 9.562, 9.757};
//		RealPolynomial rp = new RealPolynomial(x, y);
//		System.out.println(rp.createString());
//		
		// Lab11 ex3
		double[] x = {125, 150, 200};
		double[] y = {5.9, 10.3, 13.8};
		RealPolynomial rp = new RealPolynomial(x, y);
		System.out.println(rp.createString());
		System.out.println(rp.evaluateAt(200));
		
	}

	/**
	 * This method finds the max difference and the point where it occurs between
	 * the exponential function of x and a polynomial estimation. The polynomial
	 * estimation is calculated with Horner's method while the exponential function
	 * is estimated with Java's exp() function.
	 * 
	 * @param f        A RealPolynomial object which contains information about the
	 *                 polynomial function used for the estimation
	 * @param expCoeff The coefficient of the exponential function
	 * @param start    The start boundary of the estimation
	 * @param end      The end boundary of the estimation
	 * @param steps    The number of steps in the estimation
	 * @return An array which have two elements: [0]: The max difference and [1]:
	 *         the x position where is max difference occurs
	 */
	public static double[] testApprox(RealPolynomial f, int expCoeff, double start, double end, int steps) {
		double maxDiff = 0;
		double at = start - 1;
		double width = (end - start);
		for (int i = 0; i < steps; i++) {
			double x = ((double) i / steps) * width + start;
			double diff = Math.abs(f.evaluateAt(x) - exp(expCoeff * x));
			if (diff > maxDiff) {
				maxDiff = diff;
				at = x;
			}
			System.out.printf("x=%.10f, diff=%.10f\n", x, diff);
		}

		return new double[] { maxDiff, at };
	}
	
	public static double[] testApprox(double[] x, double[] y, int expCoeff, double start, double end, int steps) {
		return testApprox(new RealPolynomial(x, y), expCoeff, start, end, steps);
	}
}
