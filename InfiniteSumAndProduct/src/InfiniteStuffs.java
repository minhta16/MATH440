
public class InfiniteStuffs {
	public static void main(String[] args) {
//		exercise1();
//		exercise2();
//		exercise3b();
		lab08();
		
		
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
	
	public static void lab08() {
		double target = 2.47;
		int terms = 6400;
		double a = 1.53875;
		double b = 1.54;
		int steps = 5;
		
		double[] interval = bisectionSum(target, terms, a, b, steps);
		for (int i = 200; i <= 64000; i *= 2) {
			System.out.println(i + " " + evaluateSum(interval[1], i));
		}
	}
	
	public static double evaluateSum(double power, int terms) {
		double sum = 0;
		for (int i = terms; i >= 1; i--) {
			sum += 1 / (Math.exp(Math.log(i) * power));
		}
		
		double error = 1 / ((power - 1) * (Math.exp(Math.log(terms)*(power - 1))));
		return sum + error;
	}
	
	public static double evaluateProduct(double power, int terms) {
		double prod = 1;
		for (int i = terms; i >= 1; i--) {
			prod *= 1 / (Math.exp(Math.log(i) * power)) + 1;
		}
		return prod;
	}
	
	public static double[] bisectionSum(double target, int terms, double leftX, double rightX, int steps) {
		double leftX1 = leftX;
		double rightX1 = rightX;
		double fLeft = 0, fRight = 0;
		for (int i = 0; i < steps; i++) {
			double avg = (leftX1 + rightX1) / 2;
			double fAvg = evaluateSum(avg, terms);
			fLeft = evaluateSum(leftX1, terms);
			fRight = evaluateSum(rightX1, terms);
			System.out.printf("f(%.8f)=%.8f, f(%.8f)=%.8f\n", leftX1, fLeft, rightX1, fRight, avg);
			if ((fLeft - target) * (fAvg - target) < 0) {
				rightX1 = avg;
			} else {
				leftX1 = avg;
			}
		}
		System.out.printf("error=%.08f\n", fLeft - fRight);
		return new double[] {leftX1, rightX1};
	}
}