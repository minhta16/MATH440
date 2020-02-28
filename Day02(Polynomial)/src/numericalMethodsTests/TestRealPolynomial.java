package numericalMethodsTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import numericalMethods.RealPolynomial;

/**
 * A JUnit test class to test cases of RealPolynomial. The class is using JUnit
 * test and is thoroughly tested, which also means the output of the function
 * matches the expected output.
 * 
 * @author Minh Ta
 *
 */
class TestRealPolynomial {
	@Nested
	@DisplayName("Tests for evaluateAt() and getDegree()")
	class TestEvaluateAtAndGetDegree {

		@Test
		void testCase1() {
			double[] coefficients = { 3, 1 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals(8.0, rp.evaluateAt(5));
			assertEquals(1, rp.getDegree());
		}

		@Test
		void testCase2() {
			double[] coefficients = { 3, 2, 1 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals(3.0, rp.evaluateAt(0));
			assertEquals(6.0, rp.evaluateAt(1));
			assertEquals(2, rp.getDegree());
			coefficients[1] = 7.0;
			assertEquals(6.0, rp.evaluateAt(1));
			assertEquals(2, rp.getDegree());
		}

		@Test
		void testCase3() {
			double[] coefficients = { 0, -7, 0, 4 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals(18.0, rp.evaluateAt(2));
			assertEquals(-18.0, rp.evaluateAt(-2));
			assertEquals(3, rp.getDegree());
		}

		@Test
		void testCase4() {
			double[] coefficients = { 1, 1, 1, 1, 1, 1, 1 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals(7.0, rp.evaluateAt(1));
			assertEquals(1.111111, rp.evaluateAt(0.1));
			assertEquals(6, rp.getDegree());
		}

		@Test
		void testCase5() {
			double[] coefficients = { 4.2 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals(4.2, rp.evaluateAt(9.1));
			assertEquals(0, rp.getDegree());
		}

		@Test
		void testCase6() {
			double[] coefficients = { 3, 99, 0, 0 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals(3.0, rp.evaluateAt(0));
			assertEquals(102.0, rp.evaluateAt(1));
			assertEquals(1, rp.getDegree());
		}

		@Test
		void testCase7() {
			double[] coefficients = { 0, 0, 0, 0 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals(0.0, rp.evaluateAt(0));
			assertEquals(0.0, rp.evaluateAt(3));
			assertEquals(0.0, rp.evaluateAt(6));
			assertEquals(0, rp.getDegree());
		}

		@Test
		void testCase8() {
			double[] coefficients = { 0 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals(0.0, rp.evaluateAt(0));
			assertEquals(0.0, rp.evaluateAt(1));
			assertEquals(0, rp.getDegree());
		}
	}

	@Nested
	@DisplayName("Tests for createString()")
	class TestCreateString {
		@Test
		void testCase1() {
			double[] coefficients = { 0 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals("0.0", rp.createString());
		}

		@Test
		void testCase2() {
			double[] coefficients = { 0, 3 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals("3.0x", rp.createString());
		}

		@Test
		void testCase3() {
			double[] coefficients = { 1, 6, 9 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals("9.0x^2+6.0x+1.0", rp.createString());
		}

		@Test
		void testCase4() {
			double[] coefficients = { -1, -6, -9 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals("-9.0x^2-6.0x-1.0", rp.createString());
		}
	}

	@Nested
	@DisplayName("Tests for scalarMult()")
	class TestScalarMult {
		@Test
		void testCase1() {
			double[] coefficients = { 0 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals("0.0", rp.scalarMult(9).createString());
			assertEquals("0.0", rp.scalarMult(-9).createString());

			// Test if scalarMult() changes the original RealPolynomial
			assertEquals("0.0", rp.createString());
		}

		@Test
		void testCase2() {
			double[] coefficients = { 0, 3 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals("6.0x", rp.scalarMult(2).createString());
			assertEquals("-6.0x", rp.scalarMult(-2).createString());

			// Test if scalarMult() changes the original RealPolynomial
			assertEquals("3.0x", rp.createString());
		}

		@Test
		void testCase3() {
			double[] coefficients = { 1, 6, 9 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals("18.0x^2+12.0x+2.0", rp.scalarMult(2).createString());
			assertEquals("-18.0x^2-12.0x-2.0", rp.scalarMult(-2).createString());

			// Test if scalarMult() changes the original RealPolynomial
			assertEquals("9.0x^2+6.0x+1.0", rp.createString());
		}

		@Test
		void testCase4() {
			double[] coefficients = { -1, -6, -9 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals("-18.0x^2-12.0x-2.0", rp.scalarMult(2).createString());
			assertEquals("18.0x^2+12.0x+2.0", rp.scalarMult(-2).createString());

			// Test if scalarMult() changes the original RealPolynomial
			assertEquals("-9.0x^2-6.0x-1.0", rp.createString());
		}
	}

	@Nested
	@DisplayName("Tests for newRoot()")
	class TestNewRoot {
		@Test
		void testCase1() {
			double[] coefficients = { 0 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals("0.0", rp.newRoot(3).createString());
			assertEquals("0.0", rp.newRoot(-3).createString());

			// Test if newRoot() changes the original RealPolynomial
			assertEquals("0.0", rp.createString());
		}

		@Test
		void testCase2() {
			double[] coefficients = { 0, 3 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals("3.0x^2-9.0x", rp.newRoot(3).createString());
			assertEquals("3.0x^2+9.0x", rp.newRoot(-3).createString());

			// Test if newRoot() changes the original RealPolynomial
			assertEquals("3.0x", rp.createString());
		}

		@Test
		void testCase3() {
			double[] coefficients = { 1, 6, 9 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals("9.0x^3-21.0x^2-17.0x-3.0", rp.newRoot(3).createString());
			assertEquals("9.0x^3+33.0x^2+19.0x+3.0", rp.newRoot(-3).createString());

			// Test if newRoot() changes the original RealPolynomial
			assertEquals("9.0x^2+6.0x+1.0", rp.createString());
		}

		@Test
		void testCase4() {
			double[] coefficients = { -1, -6, -9 };
			RealPolynomial rp = new RealPolynomial(coefficients);
			assertEquals("-9.0x^3+21.0x^2+17.0x+3.0", rp.newRoot(3).createString());
			assertEquals("-9.0x^3-33.0x^2-19.0x-3.0", rp.newRoot(-3).createString());

			// Test if newRoot() changes the original RealPolynomial
			assertEquals("-9.0x^2-6.0x-1.0", rp.createString());
		}
	}
}
