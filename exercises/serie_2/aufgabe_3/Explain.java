public class A {
	private int a = 3;
	public void increment() { a++; }
	public String toString() { return "" + a; }
}

public class B {
	public static void main(String args[]) {
		// Initialize new instance of A class, assign to 'a1'
		A a1 = new A();
		// Initialize new instance of A class, assign to 'a2'
		A a2 = new A();
		// Call 'increment' method on 'a1', thereby incrementing its 'a' instance variable.
		a1.increment();
		//
		System.out.println(a1 + "/" + a2);
		// Assign the object *referenced by* 'a1' to 'a2' - both a1 and a2 now reference the *same* object
		a2 = a1;
		// Call 'increment' method on 'a2', thereby incrementing its 'a' instance variable.
		a2.increment();
		//
		System.out.println(a1 + "/" + a2);
		// Initialize new instance of String class, initialize to "ROCK", assign to 's1'
		String s1 = "ROCK";
		// Assign *a copy* of the string referenced by 's1' to 's2', as strings are immutable.
		String s2 = s1;
		// Call toLowerCase method on s2, and assign result to s2
		s2 = s2.toLowerCase();
		//
		System.out.println(s1 + "/" + s2);
		// Declare j as int, initialize to 1
		int j = 1;
		// Declare i as int, initialize to *value* of j - they do *not* reference the same integer.
		int i = j;
		// Increment j - this does *not* increment i
		j++;
		//
		System.out.println(j + "/" + i);
	}
}

/* 
 * This leads to the expected output of:
 *
 * 4/3
 * 5/5
 * ROCK/rock
 * 2/1
 */
