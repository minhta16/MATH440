package day14;

import numericalMethods.RealPolynomial;

/**
 * 
 * @author: Minh Ta
 * @class: MATH440 - Numerical Methods 
 *
 */

public class FailBisection {
	public static void main(String[] args) {
		part3();
	}
	
	public static void part1() {
		double a = 0.999995;
		double b = 1.000005;
		double steps = 100;
		
		double width = (b- a);
		for (int i = 0; i <= steps; i++) {
			double x = ((double) i / steps) * width + a;
			System.out.println("x=" + x + " f=" + evaluatePolyNaive(x));
		}
	}
	
	public static void part2() {
		double a = 0.999995;
		double b = 1.000005;
		double steps = 100;
		RealPolynomial rp = new RealPolynomial(new double[] {-1, 3, -3, 1});
		
		double width = (b- a);
		for (int i = 0; i <= steps; i++) {
			double x = ((double) i / steps) * width + a;
			System.out.println("x=" + x + " f=" + rp.evaluateAt(x));
		}
	}

	public static void part3() {
		double a = 0.999995;
		double b = 1.000005;
		double steps = 100;
		
		double width = (b- a);
		for (int i = 0; i <= steps; i++) {
			double x = ((double) i / steps) * width + a;
			System.out.println("x=" + x + " f=" + evaluatePolyFactored(x));
		}
	}

	public static double evaluatePolyNaive(double x) {
		return x*x*x - 3*x*x + 3*x - 1;
	}
	
	public static double evaluatePolyFactored(double x) {
		return (x-1)*(x*x + x + 1);
	}
}
