import java.math.BigInteger;

public class Factorial
{
	public static void main(String[] args) {
		try {
			int i = Integer.parseInt(args[0]);
			System.out.println(String.format("%s! = %s", i, factorial(BigInteger.valueOf(i))));
		} catch (ArrayIndexOutOfBoundsException e) {
			usage();
		} catch (NumberFormatException e) {
			System.err.println(String.format("%s not a valid integer!", args[0]));
			System.exit(1);
		}
	}

	private static BigInteger factorial(BigInteger i) {
		BigInteger zero = BigInteger.valueOf(0);
		BigInteger one = BigInteger.valueOf(1);
		if (i.equals(zero) || i.equals(one)) {
			return one;

		} else {
			return i.multiply(factorial(i.subtract(one)));
		}
	}

	private static void usage() {
		System.err.println("Usage: java Factorial <n>");
		System.exit(2);
	}
}
