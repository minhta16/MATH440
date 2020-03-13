import java.util.Arrays;

/**
 * 
 * @author: Minh Ta
 * @class: MATH440 - Numerical Methods 
 *
 */

public class EstimateIntegral {
	public static enum IntegralType {
		LEFT,
		RIGHT,
	}
	
	public static void main(String[] args) {
		double[] leftEst = estimateIntegral(0, Math.PI/2, 11, IntegralType.LEFT);
		System.out.println(leftEst[0]);
		for (int i = 1; i < leftEst.length; i++) {
			System.out.println("terms=" + Math.pow(2, i) + " " + leftEst[i] + " diff=" + (leftEst[i] - leftEst[i - 1]));
		}
	}
	
	public static double[] estimateIntegral(double left, double right, int steps, IntegralType type) {
		double[] est = new double[steps];
		for (int i = 0; i < steps; i++) {
			int numberOfSteps = (int) Math.pow(2, i);
			double sum = 0;
			for (int j = 0; j < numberOfSteps; j++) {
				double location = j;
				if (type == IntegralType.RIGHT) {
					location += 1;
				}
				double width = (right - left) / numberOfSteps;
				double x = left + location * width;
				sum += width * getFuncAt(x);
			}
			est[i] = sum;
		}
		return est;
	}
	
	
	// Modify this function according to f(x)
	public static double getFuncAt(double x) {
//		return 3;
//		return x;
//		return Math.exp(x*x);
//		return 4 / (1 + x * x);
//		return 1 / (2 + Math.cos(x));
		return Math.cos(256 * x);
//		return 1 / (1 + Math.pow(x, 1.8));
	}
}
