public class Fibonacci
{
	public static void main(String[] args) {
		for (int i = 1; i <= 50; i++) {
			long localFib = fib(i);
			System.out.println(String.format("Fibonacci %s = %s", i, localFib));
		}
	}

	/** 
	 * Calculates Fibonacci number x recursively.
	 *
	 * Mind, horrible complexity O(2^n)?
	 */
	private static long fib(int count) {
		if (count == 0) {
			return 0;
		} else if (count == 1) {
			return 1;
		} else {
			return fib(count - 1) + fib(count - 2);
		}
	}

	/** Calculates Fibonacci number x.
	 *
	 * O(n) complexity.
	 */
	private static long fibLinear(int count) {
		if (count == 0 || count == 1) {
			return count;
		}

		long f0 = 0;
		long f1 = 1;

		for (int i = 2; i <= count; i++) {
			long f;
			f = f0 + f1;
			f0 = f1;
			f1 = f;
		}

		return f1;
	}
}
