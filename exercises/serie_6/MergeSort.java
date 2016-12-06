import java.util.Arrays;
// Heavily based on pseudo-code in Wikipedia article
public class MergeSort
{
	/**
	 * Sort an array of objects implementing the Comparable interfacec 
	 * using MergeSort.
	 *
	 * Modifies supplied array.
	 */
	public static void sort(Comparable[] input) {
		Comparable[] sortedInput = mergeSort(input);
		// Hacky solution to modify array in-place.
		for (int i = 0; i < sortedInput.length; i++) {
			input[i] = sortedInput[i];
		}
	}

	/**
	 * Sort an array of objects implementing the Comparable interfacec 
	 * using MergeSort.
	 *
	 * Returns new sorted array.
	 */
	private static Comparable[] mergeSort(Comparable[] input) {
		// Lists of length 1 are sorted.
		if (input.length <= 1) {
			return input;
		}

		// Split list (roughly) in half.
		// No - 1 needed. Arrays.copyOfRange copies up to, but not
		// including, the supplied index
		int mid = input.length / 2;
		Comparable[] leftList = Arrays.copyOfRange(input, 0, mid);
		Comparable[] rightList = Arrays.copyOfRange(input, mid, input.length);

		leftList = mergeSort(leftList);
		rightList = mergeSort(rightList);

		return merge(leftList, rightList);
	}

	/**
	 * Merge two sorted arrays into one sorted array.
	 *
	 * Important: Both inputs must be sorted themselves.
	 */
	private static Comparable[] merge(Comparable[] leftInput, Comparable[] rightInput) {
		Comparable[] output = new Comparable[leftInput.length + rightInput.length];

		int indexLeft = 0;
		int indexRight = 0;
		while (indexLeft < leftInput.length && indexRight < rightInput.length) {
			// < 1 => left > right
			// 0   => left == right
			// > 1 => left < right
			if (leftInput[indexLeft].compareTo(rightInput[indexRight]) >= 1)  {
				output[indexLeft + indexRight] = rightInput[indexRight];
				indexRight++;
			} else {
				output[indexLeft + indexRight] = leftInput[indexLeft];
				indexLeft++;
			}
		}

		// Now at least one list is empty - append remaining items of
		// other list to output.
		while (indexLeft < leftInput.length) {
			output[indexLeft + indexRight] = leftInput[indexLeft];
			indexLeft++;
		}
		while (indexRight < rightInput.length) {
			output[indexLeft + indexRight] = rightInput[indexRight];
			indexRight++;
		}

		return output;
	}
}
