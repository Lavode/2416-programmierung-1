public class RecursiveLoop
{
	public static void main(String[] args) {
		System.out.println("Iterative loop:");
		iterative();
		System.out.println("---------------:");
		System.out.println("Recursive loop:");
		recursive(0);
	}

	private static void iterative() {
		for (int i = 0; i <= 30; i += 3) {
			System.out.println(i);
		}
	}

	private static void recursive(int i) {
		if (i <= 30) {
			System.out.println(i);
			i += 3;
			recursive(i);
		}
	}
}
