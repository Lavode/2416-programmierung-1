import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
// Heavily based on pseudo-code in Wikipedia article
public class MergeSort
{
	public static void sort(Comparable[] input) {
		System.out.println("------");
		input = mergeSort(input);
		for (Comparable comp : input) {
			System.out.println(comp.toString());
		}
		System.out.println("------");
	}

	private static Comparable[] mergeSort(Comparable[] input) {
		// Lists of length 1 are sorted.
		if (input.length <= 1) {
			return input;
		}

		int mid = input.length / 2;
		Comparable[] leftList = Arrays.copyOfRange(input, 0, mid);
		Comparable[] rightList = Arrays.copyOfRange(input, mid, input.length);
		leftList = mergeSort(leftList);
		rightList = mergeSort(rightList);

		return merge(leftList, rightList);
	}

	private static Comparable[] merge(Comparable[] input1, Comparable[] input2) {
		// List<Comparable<?>> output = new ArrayList<Comparable<?>>();
		Comparable[] output = new Comparable[input1.length + input2.length];

		int indexLeft = 0;
		int indexRight = 0;
		while (indexLeft < input1.length && indexRight < input2.length) {
			// < 1 => left > right
			// 0   => left == right
			// > 1 => left < right
			if (input1[indexLeft].compareTo(input2[indexRight]) >= 1)  {
				output[indexLeft + indexRight] = input2[indexRight];
				indexRight++;
			} else {
				output[indexLeft + indexRight] = input1[indexLeft];
				indexLeft++;
			}
		}

		// Now at least one list is empty - append remaining items of other list to output.
		while (indexLeft < input1.length) {
			output[indexLeft + indexRight] = input1[indexLeft];
			indexLeft++;
		}
		while (indexRight < input2.length) {
			output[indexLeft + indexRight] = input2[indexRight];
			indexRight++;
		}

		return output;
	}
}
