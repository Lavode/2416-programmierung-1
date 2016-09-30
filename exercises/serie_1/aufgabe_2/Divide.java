// FIXME
// Michael Senn, FIXME

import java.util.Scanner;
import java.lang.Math;

public class Divide {
	public static void main(String[] args) {
		int i = 0;
		int j = 0;

		Scanner scanner = new Scanner(System.in);

		System.out.println("Preparing to calculate i^2 / j");
		try {
			System.out.print("Enter an integer for i: ");
			i = scanner.nextInt();

			System.out.print("Enter an integer for j: ");
			j = scanner.nextInt();
		} catch (java.util.InputMismatchException e) {
			System.out.println("Invalid input received - Aborting.");
			System.exit(1);
		}


		// Commented out, as exercise asks to 'observe behaviour in
		// case of 0'. 
		// if (j == 0) {
		// 	System.out.println("j may not be 0 - Aborting.");
		// 	System.exit(1);
		// }

		System.out.println("Calculating " + i + "^2 / " + j);

		// Typecast safe, as we raise a signed int (-2^31..2^31-1) to
		// the power of two, leading to (-2^62..2^62-1) - which fits
		// well within a signed long (-2^63..2^63-1).
		long iSquare = (long)Math.pow(i, 2);
		// Cast to double to get floating-point result.
		// Has the (amusing) side-effect of employing floating-point
		// arithmetics - meaning x / 0 := Infinity
		// BigDecimal might be a better fit.
		double resultFloat = (double)iSquare / j;

		System.out.println("Exact result: " + resultFloat);

		long resultWhole = iSquare / j;
		long remainder = iSquare % j;

		System.out.println("Integer result: " + resultWhole);
		System.out.println("Remainder: " + remainder);
	}
}
