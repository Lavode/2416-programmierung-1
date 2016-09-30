// FIXME
// Michael Senn, FIXME

public class Problem
{
	public static int a = 17;
	public static void main(String[] args) {
		int b = 24;
		double c = 3.41;

		System.out.println("a = " + a);

		a = a + b;
		System.out.println("a = " + a);

		// Unsure what the idea here was - either integer arithmetics -
		// at the risk of overflow. Alternatively one could go with b
		// as a double, and round down to nearest whole number.
		b = (int)(c / 2);
		System.out.println("b = " + b);
	}
}

