package numericalMethods;

import java.util.Arrays;

/**
 * RealPolynomial takes in an array of polynomials and outputs the plugged in
 * value of the polynomial with the method evaluateAt(x) using the Horner's
 * method
 * 
 * @author Minh Ta
 * @class: MATH440 - Numerical Methods
 *
 */
public class RealPolynomial {
	private double[] coefficients;
	private int degree;

	/**
	 * Construct a RealPolynomial object
	 * 
	 * @param coefficients an array of type double of all coefficients. Note: the
	 *                     i-th element of the array corresponds to the i-th degree
	 *                     coefficient
	 */
	public RealPolynomial(double[] coefficients) {
		/*
		 * Discard leading zeroes, if there is any, and copy that to the coefficients
		 * array. Note: this process leaves at least one element in the array, so {0, 0,
		 * 0} -> {0}
		 */
		int i = coefficients.length - 1;
		while (coefficients[i] == 0 && i != 0) {
			i--;
		}

		this.coefficients = Arrays.copyOfRange(coefficients, 0, i + 1);

		degree = this.coefficients.length - 1;
	}

	/**
	 * Construct a RealPolynomial object that represents a polynomial that
	 * interpolates through given points
	 * 
	 * @param xCoefficients an array of x values of the points
	 * @param yCoefficients an array of y values of the points (Note: an index
	 *                      represents a point that have the coordinates of x[i] and
	 *                      y[i]
	 */
	public RealPolynomial(double[] xCoefficients, double[] yCoefficients) {
		// Finds the polynomial that interpolates at the given points
		RealPolynomial w = new RealPolynomial(new double[] { 1 });
		RealPolynomial P = new RealPolynomial(new double[] { yCoefficients[0] });
		for (int i = 1; i < xCoefficients.length; i++) {
			double x = xCoefficients[i];
			double y = yCoefficients[i];
			w = w.newRoot(xCoefficients[i - 1]);
			double a = (y - P.evaluateAt(x)) / w.evaluateAt(x);
			P = P.addPoly(w.scalarMult(a));
		}

		// Copy the data of P to the fields of the current polynomial
		degree = P.getDegree();
		coefficients = new double[degree + 1];
		for (int i = 0; i <= degree; i++) {
			coefficients[i] = P.getCoefficient(i);
		}
	}

	/**
	 * Get the degree of the polynomial
	 * 
	 * @return the degree of the polynomial
	 */
	public int getDegree() {
		return degree;
	}

	/**
	 * 
	 * @param degreeOfTerm The degree of term
	 * @return The coefficient with that degree of term. If degree is out of range,
	 *         return 0.0 instead
	 */
	public double getCoefficient(int degreeOfTerm) {
		if (degreeOfTerm > degree || degreeOfTerm < 0) {
			return 0.0;
		}
		return coefficients[degreeOfTerm];
	}

	/**
	 * Evaluate the polynomial with the Horner's method
	 * 
	 * @param x the value of x that needs to be evaluated
	 * @return the result of plugging in x to the polynomial
	 */
	public double evaluateAt(double x) {
		double result = coefficients[degree];
		for (int i = degree - 1; i >= 0; i--) {
			result = result * x + coefficients[i];
		}
		return result;
	}

	/**
	 * @return A string representation of the polynomial. E.g.: 4x^3-x+1
	 */
	public String createString() {
		// Handling the edge case of the polynomial f(x) = 0
		if (degree == 0 && coefficients[0] == 0) {
			return "0.0";
		}

		String polyString = "";
		for (int i = degree; i >= 0; i--) {
			if (coefficients[i] != 0) {

				/*
				 * Process the sign of the current coefficient since it would not normally
				 * display + when it's positive. Exclude the first coefficient.
				 */
				if (coefficients[i] > 0 && i < degree) {
					polyString += "+";
				}

				// Process special degrees, including the 1st and the 0th.
				if (i == 1) {
					// Not displaying "^1"
					polyString += coefficients[i] + "x";
				} else if (i == 0) {
					// Not displaying "x^0"
					polyString += coefficients[i];
				} else {
					// Displaying ax^i
					polyString += coefficients[i] + "x^" + i;
				}
			}
		}
		return polyString;
	}

	/**
	 * Creates a new RealPolynomial which is the scalar product of the original
	 * RealPolynomial and scalar
	 * 
	 * @param scalar A real number to multiply with the array
	 * @return A same-degree new polynomial multiplied with the scalar
	 */
	public RealPolynomial scalarMult(double scalar) {
		// Cloning the coefficients to avoid changing the original array
		double[] newCoeffs = coefficients.clone();

		// Multiplying all coefficients by the scalar
		for (int i = 0; i <= degree; i++) {
			newCoeffs[i] *= scalar;
		}

		return new RealPolynomial(newCoeffs);
	}

	/**
	 * Generates a new polynomial with one additional root
	 * 
	 * @param root The root wanted
	 * @return A new polynomial, which in fact is the original polynomial multiplies
	 *         with (x-root)
	 */
	public RealPolynomial newRoot(double root) {
		/*
		 * Initialize a new coefficients array with length degree + 2 and set the
		 * highest degree's coefficient to that of the original array
		 */
		double[] newCoeffs = new double[degree + 2];
		newCoeffs[degree + 1] = coefficients[degree];

		/*
		 * Each coefficient is the difference of the coefficient of one less degree and
		 * the product of the root with the same-degree coefficient
		 */
		for (int i = degree; i >= 1; i--) {
			newCoeffs[i] = coefficients[i - 1] - root * coefficients[i];
		}

		/*
		 * Process the 0th degree coefficient
		 */
		newCoeffs[0] = -coefficients[0] * root;

		return new RealPolynomial(newCoeffs);
	}

	/**
	 * This method adds two polynomial and return a new polynomial which is a sum of
	 * the original polynomial and the polynomial in the parameter
	 * 
	 * @param otherPoly The new polynomial to be added to the original polynomial
	 * @return A new RealPolynomial which is a sum of the two polynomials
	 */
	public RealPolynomial addPoly(RealPolynomial otherPoly) {
		// Find the highest degree
		int highestDegree = Math.max(degree, otherPoly.getDegree());
		double[] newCoeffs = new double[highestDegree + 1];
		for (int i = 0; i <= highestDegree; i++) {
			// If there exists a coefficient with the current degree, then add that
			// coefficient to the new array
			if (i <= degree) {
				newCoeffs[i] += coefficients[i];
			}
			if (i <= otherPoly.getDegree()) {
				newCoeffs[i] += otherPoly.getCoefficient(i);
			}
		}
		return new RealPolynomial(newCoeffs);
	}
}
