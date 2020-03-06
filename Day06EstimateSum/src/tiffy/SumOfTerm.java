package tiffy;

import numericalmethods.RealPolynomial;

/***
 * This class helps approximate the value of x where f(x) = 2.46. The class has
 * the estimation of 2 functions of x. f1(x) is the the sum of 1/(n^x) where n
 * goes from 1 to infinity. f2(x) is the the product of (1 + 1/(n^x)) where n
 * goes from 1 to infinity.
 * 
 * @author Tiffany Nguyen
 *
 */

public class SumOfTerm {
	public static void main(String[] args) {
		// Test Sum
//		for (int i = 1000; i <= 4000; i *= 2) {
//			System.out.println(i + " " + evaluateSum(i, 1.676));
//		}
		// Test Product
//		for (int i = 1000; i <= 4000; i *= 2) {
//			System.out.println(i + " " + evaluateProduct(1, i, 3.7951));
//		}

		// Test Bisection Sum
//		bisectionSum(2.46, 1.53906, 1.54224, 5, 6400);
//		for (int i = 4; i <= 64; i *= 2) {
//			System.out.println("i=" + i);
//			secantSum(2.46, 0.4, 0.5, 5, i);
//		}

//		for (int i = 800; i <= 6400; i *= 2) {
//			System.out.println("at term" + i + " : f(1.54204) = " + evaluateSumImproved(i, 1.54204));
//		}

		// Test Hybrid Estimation
		System.out.println(evaluateSum3(2.1700864866260338));
		System.out.println(evaluateSum3(2.1700864866260339));
		hybridFunc(2.17, 2.171, 5, 6400);
		System.out.println();

	}

	// method helps evaluate the sum of 1/(n^s)
	public static double evaluateSum(int numOfTerms, double root) {
		double sum = 0;
		for (int i = numOfTerms; i >= 1; i--) {
			sum += 1 / (Math.exp((root) * Math.log(i)));
		}
		return sum;
	}

	// This method helps evaluate the sum of 1/(n^s), adding the integral of f(x)
	// from the last term to infinity to improve the estimation
	public static double evaluateSumImproved(int numOfTerms, double power) {
		return (evaluateSum(numOfTerms, power) + (1 / ((power - 1) * Math.exp(Math.log(numOfTerms) * (power - 1)))));
	}

	// This method helps evaluate the sum of 1 + n*(s^n), which is f(x) of Lab 09
	public static double evaluateSumL9(int numOfTerms, double power) {
		double sum = 1;
		for (int i = numOfTerms; i >= 1; i--) {
			sum += i * Math.exp(Math.log(power) * i);
		}
		return sum;
	}

	// This method helps evaluate the product of 1 + 1/(n^s)
	public static double evaluateProduct(int start, int end, double root) {
		double result = 1;
		for (int i = end; i >= start; i--) {
			result *= (1 + (1 / (Math.exp((root) * Math.log(i)))));
		}
		return result;
	}
	
	// Evaluate f(x) = x^3 + x^2 - 20x + 1
	public static double evaluateSum1(double x) {
		double[] coeff = {1, -20, 1, 1};
		RealPolynomial rp = new RealPolynomial(coeff);
		return rp.evaluateAt(x);
	}
	
	// Evaluate g(x) = 1 + x + (x^3)/3 + (x^5)/5 +...
	public static double evaluateSum2(double x, int numOfTerms) {
		double result = 1;
		for (int i = numOfTerms * 2 - 1; i >= 1; i-=2) {
			result += Math.pow(x, i) / i;
		}
		return result;
	}
	
	// Evaluate h(x) = x^3 - x^2 -3x + 1 
	public static double evaluateSum3(double x) {
		double[] coeff = {1, -3, -1, 1};
		RealPolynomial rp = new RealPolynomial(coeff);
		return rp.evaluateAt(x);
	}

	// This method helps evaluate the sum of 1/(n^s) using the bisection method,
	// given the interval between
	public static void bisectionSum(double target, double leftX, double rightX, int steps, int numOfTerms) {
		double fLeft = 0, fRight = 0;
		for (int i = 0; i < steps; i++) {
			double avg = (leftX + rightX) / 2;
			double fAvg = evaluateSumImproved(numOfTerms, avg);
			fLeft = evaluateSumImproved(numOfTerms, leftX);
			fRight = evaluateSumImproved(numOfTerms, rightX);
			System.out.println("f(" + leftX + ") = " + fLeft + " f(" + rightX + ") = " + fRight);
			if ((fLeft - target) * (fAvg - target) < 0) {
				rightX = avg;
			} else {
				leftX = avg;
			}
		}
		System.out.println("error=" + (fLeft - fRight));
	}

	// This method helps evaluate the sum of n*(s^n) using the secant method, given
	// the interval between
	public static void secantSum(double target, double x1, double x2, int steps, int numOfTerms) {
		double y1 = evaluateSumL9(numOfTerms, x1) - target;
		double y2 = evaluateSumL9(numOfTerms, x2) - target;
		for (int i = 0; i < steps; i++) {
			double x3 = x2 - y2 * (x2 - x1) / (y2 - y1);
			double y3 = evaluateSumL9(numOfTerms, x3) - target;
			x1 = x2;
			y1 = y2;
			x2 = x3;
			y2 = y3;
			System.out.println("x1 = " + x1 + " y1 = " + y1 + " x2 = " + x2 + " y2 = " + y2);
		}
	}

	// The hybrid algorithm that uses the secant method wherever possible and use
	// bisection when secant doesn't work
	public static void hybridFunc(double x1, double x2, int steps, int numOfTerms) {
		for (int i = 0; i < steps; i++) {
			double y1 = evaluateSum3(x1);
			double y2 = evaluateSum3(x2);
			double c = x2 - y2 * (x2 - x1) / (y2 - y1);
			double fc = evaluateSum3(c);
			if ((c < x1) || (x2 < c)) {
				c = (x1 + x2) / 2;
				if (fc * y1 < 0) {
					x2 = c;
				} else {
					x1 = c;
				}
				System.out.print("Used bisection ");
			} else {
				if (y1 * fc < 0) {
					x2 = c;
				} else {
					x1 = c;
				}
				System.out.print("Used secant ");
			}
			System.out.print("f(" + x1 + ") = " + y1 + " ");
			System.out.println("f(" + x2 + ") = " + y2);
		}
	}

}
