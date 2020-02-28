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
class TestRealPolynomial2 {
	@Nested
	@DisplayName("Tests for addPoly()")
	class TestAddPoly {
		@Test
		void testCase1() {
			double[] coefficients = { 0 };
			// rp(x) = 0.0
			RealPolynomial rp = new RealPolynomial(coefficients);
			// otherRp1(x) = 0.0
			RealPolynomial otherRp1 = new RealPolynomial(coefficients);
			// otherRp2(x) = 3.0x^2 + 2.0x + 1.0
			RealPolynomial otherRp2 = new RealPolynomial(new double[] {1, 2, 3});
			// the test case is passed when rp(x) + otherRp1(x) is 0.0
			assertEquals("0.0", rp.addPoly(otherRp1).createString());
			// the test case is passed  the test case when rp(x) + otherRp2(x) is 3.0x^2+2.0x+1.0
			assertEquals("3.0x^2+2.0x+1.0", rp.addPoly(otherRp2).createString());

			// Test if addPoly() changes the original RealPolynomial
			assertEquals("0.0", rp.createString());
		}

		@Test
		void testCase2() {
			double[] coefficients = { 0, 3 };
			// rp(x) = 3.0x + 0.0
			RealPolynomial rp = new RealPolynomial(coefficients);
			// otherRp1(x) = 3.0x + 0.0
			RealPolynomial otherRp1 = new RealPolynomial(coefficients);
			// otherRp2(x) = 3.0x^2 - 4.0x + 1.0
			RealPolynomial otherRp2 = new RealPolynomial(new double[] {1, -4, 3});
			// otherRp3(x) = 0.0
			RealPolynomial otherRp3 = new RealPolynomial(new double[] {0});

			// the test case is passed when rp(x) + otherRp1(x) is 6.0x
			assertEquals("6.0x", rp.addPoly(otherRp1).createString());
			// the test case is passed when rp(x) + otherRp2(x) is 3.0x^2-1.0x+1.0
			assertEquals("3.0x^2-1.0x+1.0", rp.addPoly(otherRp2).createString());
			// the test case is passed when rp(x) + otherRp3(x) is 3.0x
			assertEquals("3.0x", rp.addPoly(otherRp3).createString());
		}

		@Test
		void testCase3() {
			double[] coefficients = { 1, 6, 9, 5, 6, -10, 0 };
			// rp(x) = -10.0x^5+6.0x^4+5.0x^3+9.0x^2+6.0x+1.0
			RealPolynomial rp = new RealPolynomial(coefficients);
			// otherRp1(x) = 0.0x^3 + 0.0x^2 + 0.0x + 0.0
			RealPolynomial otherRp1 = new RealPolynomial(new double[] {0, 0, 0, 0});
			// otherRp2(x) = 3.0x^2-4.0x-1.0
			RealPolynomial otherRp2 = new RealPolynomial(new double[] {-1, -4, 3});
			// otherRp3(x) = 0.0
			RealPolynomial otherRp3 = new RealPolynomial(new double[] {0});
			// the test case is passed when rp(x) + otherRp1(x) is -10.0x^5+6.0x^4+5.0x^3+9.0x^2+6.0x+1.0
			assertEquals("-10.0x^5+6.0x^4+5.0x^3+9.0x^2+6.0x+1.0", rp.addPoly(otherRp1).createString());
			// the test case is passed when rp(x) + otherRp1(x) is -10.0x^5+6.0x^4+5.0x^3+12.0x^2+2.0x
			assertEquals("-10.0x^5+6.0x^4+5.0x^3+12.0x^2+2.0x", rp.addPoly(otherRp2).createString());
			// the test case is passed when rp(x) + otherRp1(x) is -10.0x^5+6.0x^4+5.0x^3+9.0x^2+6.0x+1.0
			assertEquals("-10.0x^5+6.0x^4+5.0x^3+9.0x^2+6.0x+1.0", rp.addPoly(otherRp3).createString());
		
		}

		@Test
		void testCase4() {
			double[] coefficients = { -1, -6, -9 };
			// rp(x) = -9.0x^2 - 6.0x - 1
			RealPolynomial rp = new RealPolynomial(coefficients);
			// otherRp1(x) = 0.0x^3 + 0.0x^2 + 0.0x + 0.0
			RealPolynomial otherRp1 = new RealPolynomial(new double[] {0, 0, 0, 0});
			// otherRp2(x) = 3.0x^2-4.0x-1.0
			RealPolynomial otherRp2 = new RealPolynomial(new double[] {-1, -4, 3});
			// otherRp3(x) = 0.0
			RealPolynomial otherRp3 = new RealPolynomial(new double[] {0});
			assertEquals("-9.0x^2-6.0x-1.0", rp.addPoly(otherRp1).createString());
			assertEquals("-6.0x^2-10.0x-2.0", rp.addPoly(otherRp2).createString());
			assertEquals("-9.0x^2-6.0x-1.0", rp.addPoly(otherRp3).createString());
		}
	}
	
	@Nested
	@DisplayName("Tests for Interpolating Polynomial")
	class TestInterpolatingPolynomial {
		@Test
		void testCase1() {
			double[] x = { 0 };
			double[] y = { 0 };
			// rp(x) interpolates through (0,0)
			RealPolynomial rp = new RealPolynomial(x, y);
			// test passes when rp(x) = 0.0
			assertEquals("0.0", rp.createString());
		}
		
		@Test
		void testCase2() {
			double[] x = { 0 };
			double[] y = { 5.0 };
			// rp(x) interpolates through (0,5)
			RealPolynomial rp = new RealPolynomial(x, y);
			// test passes when rp(x) = 5.0
			assertEquals("5.0", rp.createString());
		}

		
		@Test
		void testCase3() {
			double[] x = { 0, 1 };
			double[] y = { 0, 1 };
			// rp(x) interpolates through (0,1), (0,1)
			RealPolynomial rp = new RealPolynomial(x, y);
			// test passes when rp(x) = 1.0x
			assertEquals("1.0x", rp.createString());
		}
		
		@Test
		void testCase4() {
			double[] x = { 0, 1 };
			double[] y = { 1, 2 };
			// rp(x) interpolates through (0,1), (1,2)
			RealPolynomial rp = new RealPolynomial(x, y);
			// test passes when rp(x) = 1.0x+1.0
			assertEquals("1.0x+1.0", rp.createString());
		}
		
		@Test
		void testCase5() {
			double[] x = { 0, 1, 2 };
			double[] y = { 1, 2, 3 };
			// rp(x) interpolates through (0,1), (1,2), (2,3)
			RealPolynomial rp = new RealPolynomial(x, y);
			// test passes when rp(x) = 1.0x+1.0
			assertEquals("1.0x+1.0", rp.createString());
		}
		

		@Test
		void testCase6() {
			double[] x = { 0, 1, 2 };
			double[] y = { 1, 2, 4 };
			// rp(x) interpolates through (0,1), (1,2), (2,4)
			RealPolynomial rp = new RealPolynomial(x, y);
			// test passes when rp(x) = 0.5x^2+0.5x+1.0
			assertEquals("0.5x^2+0.5x+1.0", rp.createString());
		}
		

		@Test
		void testCase7() {
			double[] x = { 1, 3, 6, 7 };
			double[] y = { 2, 4, 1, 7 };
			// rp(x) interpolates through (1,2), (3,4), (6,1), (7,7)
			RealPolynomial rp = new RealPolynomial(x, y);
			// test passes when coefficients of rp(x) is equal to the coefficients found by hand with the error of
			// 1 * 10^-5
			assertEquals(0, Math.round((rp.getCoefficient(3) - 43.0/120))*100000/100000.0);
			assertEquals(0, Math.round((rp.getCoefficient(2) + 239.0/60))*100000/100000.0);
			assertEquals(0, Math.round((rp.getCoefficient(1) - 491.0/40))*100000/100000.0);
			assertEquals(0, Math.round((rp.getCoefficient(0) + 133.0/20))*100000/100000.0);
		}
	}
}
