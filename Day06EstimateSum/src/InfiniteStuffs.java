public class InfiniteStuffs {
	public static void main(String[] args) {
		// exercise1();
		// exercise2();
		// exercise3b();
		lab08();
//		lab09();
//		System.out.println(evaluateSumLab09(0.5, 9));
	}

	public static void exercise1() {
		int terms = 25;
		for (int i = 0; i < 10; i++) {
			System.out.printf("f(3), terms = %d = %.8f\n", terms, evaluateSum(3, terms));
			terms *= 2;
		}
	}

	public static void exercise2() {
		double s = 1.68;
		int terms = 6400;
		System.out.printf("f(%f), terms = %d = %.8f\n", s, terms, evaluateSum(s, terms));
	}

	public static void exercise3a() {
		int terms = 25;
		for (int i = 0; i < 10; i++) {
			System.out.printf("g(3.5), terms = %d = %.8f\n", terms, evaluateProduct(3.5, terms));
			terms *= 2;
		}
	}

	public static void exercise3b() {
		double s = 3.79508;
		int terms = 3200;
		System.out.printf("g(%f), terms = %d = %.8f\n", s, terms, evaluateProduct(s, terms));
	}

	public static void lab07() {
		for (int i = 400; i <= 6400; i *= 2) {
			System.out.println(i + " " + evaluateSum(1.5393750, i));
		}
	}

	public static void lab08() {
		double target = 2.47;
		int terms = 6400;
		double a = 1.53878906;
		double b = 1.539375;
		int steps = 5;

		double[] interval = estimateSumBisection(target, terms, a, b, steps);
		for (int i = 200; i <= 64000; i *= 2) {
			System.out.println(i + " f(" + interval[0] + ")=" + evaluateSum(interval[0], i));
		}
		System.out.printf("Root: %.6f, error = %.6f", (interval[0] + interval[1]) / 2, (interval[0] - interval[1]) / 2);
	}

	public static void lab08b() {
		double start = 1.51;
		double end = 1.6;
		for (int i = 400; i <= 6400; i *= 2) {
			estimateSumBisection(2.47, i, start, end, 5);
		}
	}

	public static void lab09() {
		double target = 2.47;
		double a = 0.3;
		double b = 0.5;
		int steps = 5;

		for (int i = 2; i <= 70; i *= 2) {
			double[] result = estimateSumSecantLab09(target, i, a, b, steps);
			System.out.println("Terms = " + i + " f(x) = " + evaluateSumLab09(result[1], i));
		}
	}

	/**
	 * Estimate x where f(x) = target using the secant method
	 * 
	 * @param target the target
	 * @param terms  number of terms to estimate the sum of the series
	 * @param leftX  the left end of the search interval
	 * @param rightX the right end of the search interval
	 * @param steps  number of search steps
	 * @return an estimation of x
	 */
	public static double[] estimateSumSecant(double target, int terms, double leftX, double rightX, int steps) {
		double x0 = leftX;
		double x1 = rightX;
		double y0 = 0, y1 = 0;
		for (int i = 0; i < steps; i++) {
			y0 = evaluateSum(x0, terms) - target;
			y1 = evaluateSum(x1, terms) - target;
			double xNew = x1 - y1 * (x1 - x0) / (y1 - y0);
			double fNew = evaluateSum(xNew, terms) - target;
			System.out.printf("f(%.08f)=%.08f, f(%.08f)=%.08f", x0, y0, x1, y1);
			x0 = x1;
			y0 = y1;
			x1 = xNew;
			y1 = fNew;
		}
		System.out.println("Result: f(" + x1 + ") = " + y1);
		return new double[] { x0, x1 };
	}

	/**
	 * Estimate x where f(x) = target using the secant method
	 * 
	 * @param target the target
	 * @param terms  number of terms to estimate the sum of the series
	 * @param leftX  the left end of the search interval
	 * @param rightX the right end of the search interval
	 * @param steps  number of search steps
	 * @return an estimation of x
	 */
	public static double[] estimateSumSecantLab09(double target, int terms, double leftX, double rightX, int steps) {
		double x0 = leftX;
		double x1 = rightX;
		double y0 = 0, y1 = 0;
		for (int i = 0; i < steps; i++) {
			y0 = evaluateSumLab09(x0, terms) - target;
			y1 = evaluateSumLab09(x1, terms) - target;
			double xNew = x1 - y1 * (x1 - x0) / (y1 - y0);
			double fNew = evaluateSumLab09(xNew, terms) - target;
			System.out.printf("f(%.08f)=%.08f, f(%.08f)=%.08f\n", x0, y0, x1, y1);
			x0 = x1;
			y0 = y1;
			x1 = xNew;
			y1 = fNew;
		}
		System.out.println("Result: f(" + x1 + ") = " + y1);
		return new double[] { x0, x1 };
	}


	/**
	 * Estimate x where f(x) = target using the bisection method
	 * 
	 * @param target the target
	 * @param terms  number of terms to estimate the sum of the series
	 * @param leftX  the left end of the search interval
	 * @param rightX the right end of the search interval
	 * @param steps  number of search steps
	 * @return an estimation of x
	 */
	public static double[] estimateSumBisection(double target, int terms, double leftX, double rightX, int steps) {
		double leftX1 = leftX;
		double rightX1 = rightX;
		double fLeft = 0, fRight = 0;
		for (int i = 0; i < steps; i++) {
			double avg = (leftX1 + rightX1) / 2;
			double fAvg = evaluateSum(avg, terms);
			fLeft = evaluateSum(leftX1, terms);
			fRight = evaluateSum(rightX1, terms);
			System.out.printf("f(%.08f)=%.08f, f(%.08f)=%.08f\n", leftX1, fLeft, rightX1, fRight, avg);
			if ((fLeft - target) * (fAvg - target) < 0) {
				rightX1 = avg;
			} else if ((fRight - target) * (fAvg - target) < 0) {
				leftX1 = avg;
			}
		}
		System.out.printf("error=%.08f\n", evaluateSum(leftX1, terms) - evaluateSum(rightX1, terms));
		return new double[] { leftX1, rightX1 };
	}

	/**
	 * Evaluate sum of the series 1/(n^s), where n ranges from 1 to 'terms', and s
	 * is 'power'
	 * 
	 * @param power the value of s
	 * @param terms the value of n
	 * @return an approximation of the sum of the series 1/(n^s)
	 */
	public static double evaluateSum(double power, int terms) {
		double sum = 0;
		for (int i = terms; i >= 1; i--) {
			sum += 1 / (Math.exp(Math.log(i) * power));
		}
		/*
		 * This error is the integral of f(x) from the last term to infinity to
		 * approximate that portion that we missed This is equals to 1/((m^(s-1))*(s-1))
		 */
		double error = 1 / ((power - 1) * (Math.exp(Math.log(terms) * (power - 1))));
		return sum + error;
	}

	/**
	 * Evaluate sum of the series n*(s^n), where n ranges from 1 to 'terms', and s
	 * is 'power'
	 * 
	 * @param s the value of s
	 * @param terms the value of n
	 * @return an approximation of the sum of the series 1/(n^s)
	 */
	public static double evaluateSumLab09(double s, int terms) {
		double sum = 1;
		for (int i = 1; i <= terms; i++) {
			sum += i * (Math.exp(Math.log(s) * i));
		}
		return sum;
	}

	/**
	 * Evaluate product of the series (1+1/(n^s)), where n ranges from 1 to 'terms',
	 * and s is 'power'
	 * 
	 * @param power the value of s
	 * @param terms the value of n
	 * @return an approximation of the product of the series (1+1/(n^s))
	 */
	public static double evaluateProduct(double power, int terms) {
		double prod = 1;
		for (int i = terms; i >= 1; i--) {
			prod *= 1 / (Math.exp(Math.log(i) * power)) + 1;
		}
		return prod;
	}
}